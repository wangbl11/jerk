package com.kongtiantou.jerk.repository.search;

import com.kongtiantou.jerk.domain.Preference;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the Preference entity.
 */
public interface PreferenceSearchRepository extends ElasticsearchRepository<Preference, Long> {
}
