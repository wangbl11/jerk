package com.kongtiantou.jerk.repository.search;

import com.kongtiantou.jerk.domain.Jerk;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the Jerk entity.
 */
public interface JerkSearchRepository extends ElasticsearchRepository<Jerk, Long> {
}
