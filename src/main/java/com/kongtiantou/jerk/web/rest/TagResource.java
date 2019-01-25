package com.kongtiantou.jerk.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.github.vanroy.springdata.jest.JestElasticsearchTemplate;
import com.kongtiantou.jerk.domain.Tag;
import com.kongtiantou.jerk.repository.TagRepository;
import com.kongtiantou.jerk.repository.search.TagSearchRepository;
import com.kongtiantou.jerk.web.rest.errors.BadRequestAlertException;
import com.kongtiantou.jerk.web.rest.util.ExtJestSearchResultMapper;
import com.kongtiantou.jerk.web.rest.util.HeaderUtil;
import com.kongtiantou.jerk.web.rest.util.HighLightJestSearchResultMapper;
import com.kongtiantou.jerk.web.rest.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;

import org.elasticsearch.index.query.Operator;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
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

/**
 * REST controller for managing Tag.
 */
@RestController
@RequestMapping("/api")
public class TagResource {

    private final Logger log = LoggerFactory.getLogger(TagResource.class);

    private static final String ENTITY_NAME = "tag";

    private final TagRepository tagRepository;

    private final TagSearchRepository tagSearchRepository;

    private final JestElasticsearchTemplate elasticsearchTemplate;
    
    @Resource
    private ExtJestSearchResultMapper extJestSearchResultMapper;

    public TagResource(TagRepository tagRepository, TagSearchRepository tagSearchRepository,JestElasticsearchTemplate elasticsearchTemplate) {
        this.tagRepository = tagRepository;
        this.tagSearchRepository = tagSearchRepository;
        this.elasticsearchTemplate=elasticsearchTemplate;
    }

    /**
     * POST  /tags : Create a new tag.
     *
     * @param tag the tag to create
     * @return the ResponseEntity with status 201 (Created) and with body the new tag, or with status 400 (Bad Request) if the tag has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/tags")
    @Timed
    public ResponseEntity<Tag> createTag(@Valid @RequestBody Tag tag) throws URISyntaxException {
        log.debug("REST request to save Tag : {}", tag);
        if (tag.getId() != null) {
            throw new BadRequestAlertException("A new tag cannot already have an ID", ENTITY_NAME, "idexists");
        }
        tag.setCreatedDate(System.currentTimeMillis());
        Tag result = tagRepository.save(tag);
        tagSearchRepository.save(result);
        return ResponseEntity.created(new URI("/api/tags/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /tags : Updates an existing tag.
     *
     * @param tag the tag to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated tag,
     * or with status 400 (Bad Request) if the tag is not valid,
     * or with status 500 (Internal Server Error) if the tag couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/tags")
    @Timed
    public ResponseEntity<Tag> updateTag(@Valid @RequestBody Tag tag) throws URISyntaxException {
        log.debug("REST request to update Tag : {}", tag);
        if (tag.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        tag.setModifiedDate(System.currentTimeMillis());
        Tag result = tagRepository.save(tag);
        tagSearchRepository.save(result);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, tag.getId().toString()))
            .body(result);
    }

    /**
     * GET  /tags : get all the tags.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of tags in body
     */
    @GetMapping("/tags")
    @Timed
    public ResponseEntity<List<Tag>> getAllTags(Pageable pageable) {
        log.debug("REST request to get a page of Tags");
        Page<Tag> page = tagRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/tags");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /tags/:id : get the "id" tag.
     *
     * @param id the id of the tag to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the tag, or with status 404 (Not Found)
     */
    @GetMapping("/tags/{id}")
    @Timed
    public ResponseEntity<Tag> getTag(@PathVariable Long id) {
        log.debug("REST request to get Tag : {}", id);
        Optional<Tag> tag = tagRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(tag);
    }

    /**
     * DELETE  /tags/:id : delete the "id" tag.
     *
     * @param id the id of the tag to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/tags/{id}")
    @Timed
    public ResponseEntity<Void> deleteTag(@PathVariable Long id) {
        log.debug("REST request to delete Tag : {}", id);

        tagRepository.deleteById(id);
        tagSearchRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/tags?query=:query : search for the tag corresponding
     * to the query.
     *
     * @param query the query of the tag search
     * @param pageable the pagination information
     * @return the result of the search
     */
    @GetMapping("/_search/tags")
    @Timed
    public ResponseEntity<List<Tag>> searchTags(@RequestParam String query, Pageable pageable) {
        log.debug("REST request to search for a page of Tags for query {}", query);
        // Page<Tag> page = tagSearchRepository.search(queryStringQuery(query), pageable);
        // HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(query, page, "/api/_search/tags");
        // return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
        HighlightBuilder.Field highlightField =  new HighlightBuilder.Field("name").preTags("<span style='background-color:yellow'>").postTags("</span>");
        QueryBuilder _query= QueryBuilders.matchQuery("name", query).operator(Operator.AND);
        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(_query)
                .withHighlightFields(highlightField).build();
        searchQuery.setPageable(pageable);        
        Page<Tag> page = elasticsearchTemplate.queryForPage(searchQuery, Tag.class, extJestSearchResultMapper);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(query, page, "/api/_search/tags");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

}
