package com.kongtiantou.jerk.web.rest.util;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.apache.commons.beanutils.PropertyUtils;

import org.elasticsearch.common.text.Text;
import org.elasticsearch.search.aggregations.AbstractAggregationBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
//import org.elasticsearch.search.aggregations.AbstractAggregationBuilder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.ElasticsearchException;
import org.springframework.data.elasticsearch.core.DefaultEntityMapper;
import org.springframework.data.elasticsearch.core.EntityMapper;
//import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
//import org.springframework.data.elasticsearch.core.aggregation.impl.AggregatedPageImpl;
import org.springframework.data.elasticsearch.core.mapping.ElasticsearchPersistentEntity;
import org.springframework.data.elasticsearch.core.mapping.ElasticsearchPersistentProperty;
import org.springframework.data.elasticsearch.core.mapping.SimpleElasticsearchMappingContext;
import org.springframework.data.mapping.context.MappingContext;
import org.springframework.util.StringUtils;

import com.github.vanroy.springdata.jest.aggregation.AggregatedPage;
import com.github.vanroy.springdata.jest.aggregation.impl.AggregatedPageImpl;
import com.github.vanroy.springdata.jest.internal.ExtendedSearchResult;
import com.github.vanroy.springdata.jest.internal.MultiDocumentResult;
import com.github.vanroy.springdata.jest.internal.SearchScrollResult;
import com.github.vanroy.springdata.jest.mapper.DefaultJestResultsMapper;
import com.google.gson.JsonObject;
import com.kongtiantou.jerk.domain.HighLightEntity;

import io.searchbox.client.JestResult;
import io.searchbox.core.SearchResult;

public class ExtJestSearchResultMapper extends DefaultJestResultsMapper {

	private EntityMapper entityMapper;
	private MappingContext<? extends ElasticsearchPersistentEntity<?>, ElasticsearchPersistentProperty> mappingContext;

	public ExtJestSearchResultMapper() {
		this.entityMapper = new DefaultEntityMapper();
		this.mappingContext = new SimpleElasticsearchMappingContext();
	}

	public ExtJestSearchResultMapper(
			MappingContext<? extends ElasticsearchPersistentEntity<?>, ElasticsearchPersistentProperty> mappingContext,
			EntityMapper entityMapper) {
		this.entityMapper = entityMapper;
		this.mappingContext = mappingContext;
	}

	public EntityMapper getEntityMapper() {
		return entityMapper;
	}

	public void setEntityMapper(EntityMapper entityMapper) {
		this.entityMapper = entityMapper;
	}
	
	

	@Override
	public <T> AggregatedPage<T> mapResults(SearchResult response, Class<T> clazz, Pageable pageable) {
		 return mapResults(response, clazz, null,pageable);
	}

    private <T>  void populateHighLightedFields(T result, Map<String, List<String>> highlightFields) {
        for (String field : highlightFields.keySet()) {
            try {
                System.out.print(field);
                PropertyUtils.setProperty(result, field, concat(highlightFields.get(field)));
            } catch (InvocationTargetException | IllegalAccessException | NoSuchMethodException e) {
                throw new ElasticsearchException("failed to set highlighted value for field: " + field
                       , e);
            }
        }
    }

    private String concat(List<String> texts) {
        StringBuffer sb = new StringBuffer();
        for (String text : texts) {
            sb.append(text.toString());
        }
        return sb.toString();
    }

	@Override
	public <T> AggregatedPage<T> mapResults(SearchResult response, Class<T> clazz,
			List<AbstractAggregationBuilder> aggregations, Pageable pageable) {
		// return super.mapResults(response, clazz, aggregations, pageable);
		LinkedList<T> results = new LinkedList<>();
		for (SearchResult.Hit<JsonObject, Void> hit : response.getHits(JsonObject.class)) {
			if (hit != null) {
				T result = mapSource(hit.source, clazz);
				HighLightEntity highLightEntity = (HighLightEntity) result;
				highLightEntity.setHighlight(hit.highlight);
                populateHighLightedFields(result, hit.highlight);
                results.add((T) highLightEntity);
			}
		}

		String scrollId = null;
		if (response instanceof ExtendedSearchResult) {
			scrollId = ((ExtendedSearchResult) response).getScrollId();
		}

		return new AggregatedPageImpl<>(results, pageable, response.getTotal(), response.getAggregations(), scrollId);	
	}

	private <T> T mapSource(JsonObject source, Class<T> clazz) {
		String sourceString = source.toString();
		T result = null;
		if (!StringUtils.isEmpty(sourceString)) {
			result = mapEntity(sourceString, clazz);
			setPersistentEntityId(result, source.get(JestResult.ES_METADATA_ID).getAsString(), clazz);
		} else {
			// TODO(Fields results) : Map Fields results
			// result = mapEntity(hit.getFields().values(), clazz);
		}
		return result;
	}

	private <T> T mapEntity(String source, Class<T> clazz) {
		if (StringUtils.isEmpty(source)) {
			return null;
		}
		try {
			return entityMapper.mapToObject(source, clazz);
		} catch (IOException e) {
			throw new ElasticsearchException("failed to map source [ " + source + "] to class " + clazz.getSimpleName(),
					e);
		}
	}

	private <T> void setPersistentEntityId(Object entity, String id, Class<T> clazz) {

		ElasticsearchPersistentEntity<?> persistentEntity = mappingContext.getRequiredPersistentEntity(clazz);
		ElasticsearchPersistentProperty idProperty = persistentEntity.getIdProperty();

		// Only deal with text because ES generated Ids are strings !
		if (idProperty != null) {
			if (idProperty.getType().isAssignableFrom(String.class)) {
				persistentEntity.getPropertyAccessor(entity).setProperty(idProperty, id);
			}
		}
	}
}