package com.kongtiantou.jerk.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.kongtiantou.jerk.domain.Registration;
import com.kongtiantou.jerk.repository.RegistrationRepository;
import com.kongtiantou.jerk.repository.search.RegistrationSearchRepository;
import com.kongtiantou.jerk.web.rest.errors.BadRequestAlertException;
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

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * REST controller for managing Registration.
 */
@RestController
@RequestMapping("/api")
public class RegistrationResource {

    private final Logger log = LoggerFactory.getLogger(RegistrationResource.class);

    private static final String ENTITY_NAME = "registration";

    private final RegistrationRepository registrationRepository;

    private final RegistrationSearchRepository registrationSearchRepository;

    public RegistrationResource(RegistrationRepository registrationRepository,
            RegistrationSearchRepository registrationSearchRepository) {
        this.registrationRepository = registrationRepository;
        this.registrationSearchRepository = registrationSearchRepository;
    }

    /**
     * POST /registrations : Create a new registration.
     *
     * @param registration the registration to create
     * @return the ResponseEntity with status 201 (Created) and with body the new
     *         registration, or with status 400 (Bad Request) if the registration
     *         has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/registrations")
    @Timed
    public ResponseEntity<Registration> createRegistration(@Valid @RequestBody Registration registration)
            throws URISyntaxException {
        log.debug("REST request to save Registration : {}", registration);
        if (registration.getId() != null) {
            throw new BadRequestAlertException("A new registration cannot already have an ID", ENTITY_NAME, "idexists");
        }
        registration.setCreatedDate(System.currentTimeMillis());
        Registration result = registrationRepository.save(registration);
        registrationSearchRepository.save(result);
        return ResponseEntity.created(new URI("/api/registrations/" + result.getId()))
                .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString())).body(result);
    }

    /**
     * PUT /registrations : Updates an existing registration.
     *
     * @param registration the registration to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated
     *         registration, or with status 400 (Bad Request) if the registration is
     *         not valid, or with status 500 (Internal Server Error) if the
     *         registration couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/registrations")
    @Timed
    public ResponseEntity<Registration> updateRegistration(@Valid @RequestBody Registration registration)
            throws URISyntaxException {
        log.debug("REST request to update Registration : {}", registration);
        if (registration.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        registration.setModifiedDate(System.currentTimeMillis());
        Registration result = registrationRepository.save(registration);
        registrationSearchRepository.save(result);
        return ResponseEntity.ok()
                .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, registration.getId().toString())).body(result);
    }

    /**
     * GET /registrations : get all the registrations.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of registrations
     *         in body
     */
    @GetMapping("/registrations")
    @Timed
    public ResponseEntity<List<Registration>> getAllRegistrations(Pageable pageable) {
        log.debug("REST request to get a page of Registrations");
        Page<Registration> page = registrationRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/registrations");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET /registrations/:id : get the "id" registration.
     *
     * @param id the id of the registration to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the
     *         registration, or with status 404 (Not Found)
     */
    @GetMapping("/registrations/{id}")
    @Timed
    public ResponseEntity<Registration> getRegistration(@PathVariable Long id) {
        log.debug("REST request to get Registration : {}", id);
        Optional<Registration> registration = registrationRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(registration);
    }

    /**
     * DELETE /registrations/:id : delete the "id" registration.
     *
     * @param id the id of the registration to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/registrations/{id}")
    @Timed
    public ResponseEntity<Void> deleteRegistration(@PathVariable Long id) {
        log.debug("REST request to delete Registration : {}", id);

        registrationRepository.deleteById(id);
        registrationSearchRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH /_search/registrations?query=:query : search for the registration
     * corresponding to the query.
     *
     * @param query    the query of the registration search
     * @param pageable the pagination information
     * @return the result of the search
     */
    @GetMapping("/_search/registrations")
    @Timed
    public ResponseEntity<List<Registration>> searchRegistrations(@RequestParam String query, Pageable pageable) {
        log.debug("REST request to search for a page of Registrations for query {}", query);
        Page<Registration> page = registrationSearchRepository.search(queryStringQuery(query), pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(query, page,
                "/api/_search/registrations");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

}
