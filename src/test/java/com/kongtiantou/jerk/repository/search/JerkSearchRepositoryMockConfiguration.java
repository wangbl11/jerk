package com.kongtiantou.jerk.repository.search;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Configuration;

/**
 * Configure a Mock version of JerkSearchRepository to test the
 * application without starting Elasticsearch.
 */
@Configuration
public class JerkSearchRepositoryMockConfiguration {

    @MockBean
    private JerkSearchRepository mockJerkSearchRepository;

}
