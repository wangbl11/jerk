package com.kongtiantou.jerk.repository.search;

import com.kongtiantou.jerk.domain.Registration;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the Registration entity.
 */
public interface RegistrationSearchRepository extends ElasticsearchRepository<Registration, Long> {
}
