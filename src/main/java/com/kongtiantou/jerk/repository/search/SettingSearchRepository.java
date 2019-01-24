package com.kongtiantou.jerk.repository.search;

import com.kongtiantou.jerk.domain.Setting;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the Setting entity.
 */
public interface SettingSearchRepository extends ElasticsearchRepository<Setting, Long> {
}
