package com.kongtiantou.jerk.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.github.vanroy.springdata.jest.JestElasticsearchTemplate;
import com.kongtiantou.jerk.domain.Setting;
import com.kongtiantou.jerk.repository.SettingRepository;
import com.kongtiantou.jerk.repository.search.SettingSearchRepository;
import com.kongtiantou.jerk.web.rest.errors.BadRequestAlertException;
import com.kongtiantou.jerk.web.rest.util.ExtJestSearchResultMapper;
import com.kongtiantou.jerk.web.rest.util.HeaderUtil;
import com.kongtiantou.jerk.web.rest.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.elasticsearch.index.query.QueryBuilders.*;
import org.elasticsearch.index.query.Operator;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;

/**
 * REST controller for managing Setting.
 */
@RestController
@RequestMapping("/api")
public class SettingResource {

    private final Logger log = LoggerFactory.getLogger(SettingResource.class);

    private static final String ENTITY_NAME = "setting";

    private final SettingRepository settingRepository;

    private final SettingSearchRepository settingSearchRepository;

    private final JestElasticsearchTemplate elasticsearchTemplate;
    
    @Resource
    private ExtJestSearchResultMapper extJestSearchResultMapper;

    public SettingResource(SettingRepository settingRepository, SettingSearchRepository settingSearchRepository,JestElasticsearchTemplate elasticsearchTemplate) {
        this.settingRepository = settingRepository;
        this.settingSearchRepository = settingSearchRepository;
        this.elasticsearchTemplate = elasticsearchTemplate;
    }

    /**
     * POST  /settings : Create a new setting.
     *
     * @param setting the setting to create
     * @return the ResponseEntity with status 201 (Created) and with body the new setting, or with status 400 (Bad Request) if the setting has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/settings")
    @Timed
    public ResponseEntity<Setting> createSetting(@Valid @RequestBody Setting setting) throws URISyntaxException {
        log.debug("REST request to save Setting : {}", setting);
        if (setting.getId() != null) {
            throw new BadRequestAlertException("A new setting cannot already have an ID", ENTITY_NAME, "idexists");
        }
        setting.setCreatedDate(System.currentTimeMillis());
        Setting result = settingRepository.save(setting);
        settingSearchRepository.save(result);
        return ResponseEntity.created(new URI("/api/settings/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /settings : Updates an existing setting.
     *
     * @param setting the setting to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated setting,
     * or with status 400 (Bad Request) if the setting is not valid,
     * or with status 500 (Internal Server Error) if the setting couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/settings")
    @Timed
    public ResponseEntity<Setting> updateSetting(@Valid @RequestBody Setting setting) throws URISyntaxException {
        log.debug("REST request to update Setting : {}", setting);
        if (setting.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        setting.setModifiedDate(System.currentTimeMillis());
        Setting result = settingRepository.save(setting);
        settingSearchRepository.save(result);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, setting.getId().toString()))
            .body(result);
    }

    /**
     * GET  /settings : get all the settings.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of settings in body
     */
    @GetMapping("/settings")
    @Timed
    public ResponseEntity<List<Setting>> getAllSettings(Pageable pageable) {
        log.debug("REST request to get a page of Settings");
        Page<Setting> page = settingRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/settings");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /settings/:id : get the "id" setting.
     *
     * @param id the id of the setting to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the setting, or with status 404 (Not Found)
     */
    @GetMapping("/settings/{id}")
    @Timed
    public ResponseEntity<Setting> getSetting(@PathVariable Long id) {
        log.debug("REST request to get Setting : {}", id);
        Optional<Setting> setting = settingRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(setting);
    }

    /**
     * DELETE  /settings/:id : delete the "id" setting.
     *
     * @param id the id of the setting to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/settings/{id}")
    @Timed
    public ResponseEntity<Void> deleteSetting(@PathVariable Long id) {
        log.debug("REST request to delete Setting : {}", id);

        settingRepository.deleteById(id);
        settingSearchRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/settings?query=:query : search for the setting corresponding
     * to the query.
     *
     * @param query the query of the setting search
     * @param pageable the pagination information
     * @return the result of the search
     */
    @GetMapping("/_search/settings")
    @Timed
    public ResponseEntity<List<Setting>> searchSettings(@RequestParam String query, Pageable pageable) {
        log.debug("REST request to search for a page of Settings for query {}", query);
        // Page<Setting> page = settingSearchRepository.search(queryStringQuery(query), pageable);
        // HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(query, page, "/api/_search/settings");
        // return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);

        HighlightBuilder.Field highlightField =  new HighlightBuilder.Field("name").preTags("<span style='background-color:yellow'>").postTags("</span>");
        QueryBuilder _query= QueryBuilders.matchQuery("name", query).operator(Operator.AND);
        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(_query)
                .withHighlightFields(highlightField).build();
        searchQuery.setPageable(pageable);        
        Page<Setting> page = elasticsearchTemplate.queryForPage(searchQuery, Setting.class, extJestSearchResultMapper);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(query, page, "/api/_search/settings");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

}
