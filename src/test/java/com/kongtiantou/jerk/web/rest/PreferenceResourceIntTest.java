package com.kongtiantou.jerk.web.rest;

import com.kongtiantou.jerk.JerkkApp;

import com.kongtiantou.jerk.domain.Preference;
import com.kongtiantou.jerk.repository.PreferenceRepository;
import com.kongtiantou.jerk.repository.search.PreferenceSearchRepository;
import com.kongtiantou.jerk.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import java.util.Collections;
import java.util.List;


import static com.kongtiantou.jerk.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.elasticsearch.index.query.QueryBuilders.queryStringQuery;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the PreferenceResource REST controller.
 *
 * @see PreferenceResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = JerkkApp.class)
public class PreferenceResourceIntTest {

    private static final String DEFAULT_WECHAT = "AAAAAAAAAA";
    private static final String UPDATED_WECHAT = "BBBBBBBBBB";

    private static final String DEFAULT_ADDRESS = "AAAAAAAAAA";
    private static final String UPDATED_ADDRESS = "BBBBBBBBBB";

    private static final String DEFAULT_IMAGE_URL = "AAAAAAAAAA";
    private static final String UPDATED_IMAGE_URL = "BBBBBBBBBB";

    private static final String DEFAULT_LANG = "AAAAAAAAAA";
    private static final String UPDATED_LANG = "BBBBBBBBBB";

    private static final Long DEFAULT_CREATED_DATE = 1L;
    private static final Long UPDATED_CREATED_DATE = 2L;

    private static final Long DEFAULT_MODIFIED_DATE = 1L;
    private static final Long UPDATED_MODIFIED_DATE = 2L;

    @Autowired
    private PreferenceRepository preferenceRepository;

    /**
     * This repository is mocked in the com.kongtiantou.jerk.repository.search test package.
     *
     * @see com.kongtiantou.jerk.repository.search.PreferenceSearchRepositoryMockConfiguration
     */
    @Autowired
    private PreferenceSearchRepository mockPreferenceSearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    @Autowired
    private Validator validator;

    private MockMvc restPreferenceMockMvc;

    private Preference preference;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final PreferenceResource preferenceResource = new PreferenceResource(preferenceRepository, mockPreferenceSearchRepository);
        this.restPreferenceMockMvc = MockMvcBuilders.standaloneSetup(preferenceResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter)
            .setValidator(validator).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Preference createEntity(EntityManager em) {
        Preference preference = new Preference()
            .wechat(DEFAULT_WECHAT)
            .address(DEFAULT_ADDRESS)
            .imageUrl(DEFAULT_IMAGE_URL)
            .lang(DEFAULT_LANG)
            .createdDate(DEFAULT_CREATED_DATE)
            .modifiedDate(DEFAULT_MODIFIED_DATE);
        return preference;
    }

    @Before
    public void initTest() {
        preference = createEntity(em);
    }

    @Test
    @Transactional
    public void createPreference() throws Exception {
        int databaseSizeBeforeCreate = preferenceRepository.findAll().size();

        // Create the Preference
        restPreferenceMockMvc.perform(post("/api/preferences")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(preference)))
            .andExpect(status().isCreated());

        // Validate the Preference in the database
        List<Preference> preferenceList = preferenceRepository.findAll();
        assertThat(preferenceList).hasSize(databaseSizeBeforeCreate + 1);
        Preference testPreference = preferenceList.get(preferenceList.size() - 1);
        assertThat(testPreference.getWechat()).isEqualTo(DEFAULT_WECHAT);
        assertThat(testPreference.getAddress()).isEqualTo(DEFAULT_ADDRESS);
        assertThat(testPreference.getImageUrl()).isEqualTo(DEFAULT_IMAGE_URL);
        assertThat(testPreference.getLang()).isEqualTo(DEFAULT_LANG);
        assertThat(testPreference.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testPreference.getModifiedDate()).isEqualTo(DEFAULT_MODIFIED_DATE);

        // Validate the Preference in Elasticsearch
        verify(mockPreferenceSearchRepository, times(1)).save(testPreference);
    }

    @Test
    @Transactional
    public void createPreferenceWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = preferenceRepository.findAll().size();

        // Create the Preference with an existing ID
        preference.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPreferenceMockMvc.perform(post("/api/preferences")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(preference)))
            .andExpect(status().isBadRequest());

        // Validate the Preference in the database
        List<Preference> preferenceList = preferenceRepository.findAll();
        assertThat(preferenceList).hasSize(databaseSizeBeforeCreate);

        // Validate the Preference in Elasticsearch
        verify(mockPreferenceSearchRepository, times(0)).save(preference);
    }

    @Test
    @Transactional
    public void checkWechatIsRequired() throws Exception {
        int databaseSizeBeforeTest = preferenceRepository.findAll().size();
        // set the field null
        preference.setWechat(null);

        // Create the Preference, which fails.

        restPreferenceMockMvc.perform(post("/api/preferences")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(preference)))
            .andExpect(status().isBadRequest());

        List<Preference> preferenceList = preferenceRepository.findAll();
        assertThat(preferenceList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCreatedDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = preferenceRepository.findAll().size();
        // set the field null
        preference.setCreatedDate(null);

        // Create the Preference, which fails.

        restPreferenceMockMvc.perform(post("/api/preferences")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(preference)))
            .andExpect(status().isBadRequest());

        List<Preference> preferenceList = preferenceRepository.findAll();
        assertThat(preferenceList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllPreferences() throws Exception {
        // Initialize the database
        preferenceRepository.saveAndFlush(preference);

        // Get all the preferenceList
        restPreferenceMockMvc.perform(get("/api/preferences?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(preference.getId().intValue())))
            .andExpect(jsonPath("$.[*].wechat").value(hasItem(DEFAULT_WECHAT.toString())))
            .andExpect(jsonPath("$.[*].address").value(hasItem(DEFAULT_ADDRESS.toString())))
            .andExpect(jsonPath("$.[*].imageUrl").value(hasItem(DEFAULT_IMAGE_URL.toString())))
            .andExpect(jsonPath("$.[*].lang").value(hasItem(DEFAULT_LANG.toString())))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.intValue())))
            .andExpect(jsonPath("$.[*].modifiedDate").value(hasItem(DEFAULT_MODIFIED_DATE.intValue())));
    }
    
    @Test
    @Transactional
    public void getPreference() throws Exception {
        // Initialize the database
        preferenceRepository.saveAndFlush(preference);

        // Get the preference
        restPreferenceMockMvc.perform(get("/api/preferences/{id}", preference.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(preference.getId().intValue()))
            .andExpect(jsonPath("$.wechat").value(DEFAULT_WECHAT.toString()))
            .andExpect(jsonPath("$.address").value(DEFAULT_ADDRESS.toString()))
            .andExpect(jsonPath("$.imageUrl").value(DEFAULT_IMAGE_URL.toString()))
            .andExpect(jsonPath("$.lang").value(DEFAULT_LANG.toString()))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE.intValue()))
            .andExpect(jsonPath("$.modifiedDate").value(DEFAULT_MODIFIED_DATE.intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingPreference() throws Exception {
        // Get the preference
        restPreferenceMockMvc.perform(get("/api/preferences/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePreference() throws Exception {
        // Initialize the database
        preferenceRepository.saveAndFlush(preference);

        int databaseSizeBeforeUpdate = preferenceRepository.findAll().size();

        // Update the preference
        Preference updatedPreference = preferenceRepository.findById(preference.getId()).get();
        // Disconnect from session so that the updates on updatedPreference are not directly saved in db
        em.detach(updatedPreference);
        updatedPreference
            .wechat(UPDATED_WECHAT)
            .address(UPDATED_ADDRESS)
            .imageUrl(UPDATED_IMAGE_URL)
            .lang(UPDATED_LANG)
            .createdDate(UPDATED_CREATED_DATE)
            .modifiedDate(UPDATED_MODIFIED_DATE);

        restPreferenceMockMvc.perform(put("/api/preferences")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedPreference)))
            .andExpect(status().isOk());

        // Validate the Preference in the database
        List<Preference> preferenceList = preferenceRepository.findAll();
        assertThat(preferenceList).hasSize(databaseSizeBeforeUpdate);
        Preference testPreference = preferenceList.get(preferenceList.size() - 1);
        assertThat(testPreference.getWechat()).isEqualTo(UPDATED_WECHAT);
        assertThat(testPreference.getAddress()).isEqualTo(UPDATED_ADDRESS);
        assertThat(testPreference.getImageUrl()).isEqualTo(UPDATED_IMAGE_URL);
        assertThat(testPreference.getLang()).isEqualTo(UPDATED_LANG);
        assertThat(testPreference.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testPreference.getModifiedDate()).isEqualTo(UPDATED_MODIFIED_DATE);

        // Validate the Preference in Elasticsearch
        verify(mockPreferenceSearchRepository, times(1)).save(testPreference);
    }

    @Test
    @Transactional
    public void updateNonExistingPreference() throws Exception {
        int databaseSizeBeforeUpdate = preferenceRepository.findAll().size();

        // Create the Preference

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPreferenceMockMvc.perform(put("/api/preferences")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(preference)))
            .andExpect(status().isBadRequest());

        // Validate the Preference in the database
        List<Preference> preferenceList = preferenceRepository.findAll();
        assertThat(preferenceList).hasSize(databaseSizeBeforeUpdate);

        // Validate the Preference in Elasticsearch
        verify(mockPreferenceSearchRepository, times(0)).save(preference);
    }

    @Test
    @Transactional
    public void deletePreference() throws Exception {
        // Initialize the database
        preferenceRepository.saveAndFlush(preference);

        int databaseSizeBeforeDelete = preferenceRepository.findAll().size();

        // Get the preference
        restPreferenceMockMvc.perform(delete("/api/preferences/{id}", preference.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Preference> preferenceList = preferenceRepository.findAll();
        assertThat(preferenceList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the Preference in Elasticsearch
        verify(mockPreferenceSearchRepository, times(1)).deleteById(preference.getId());
    }

    @Test
    @Transactional
    public void searchPreference() throws Exception {
        // Initialize the database
        preferenceRepository.saveAndFlush(preference);
        when(mockPreferenceSearchRepository.search(queryStringQuery("id:" + preference.getId())))
            .thenReturn(Collections.singletonList(preference));
        // Search the preference
        restPreferenceMockMvc.perform(get("/api/_search/preferences?query=id:" + preference.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(preference.getId().intValue())))
            .andExpect(jsonPath("$.[*].wechat").value(hasItem(DEFAULT_WECHAT)))
            .andExpect(jsonPath("$.[*].address").value(hasItem(DEFAULT_ADDRESS)))
            .andExpect(jsonPath("$.[*].imageUrl").value(hasItem(DEFAULT_IMAGE_URL)))
            .andExpect(jsonPath("$.[*].lang").value(hasItem(DEFAULT_LANG)))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.intValue())))
            .andExpect(jsonPath("$.[*].modifiedDate").value(hasItem(DEFAULT_MODIFIED_DATE.intValue())));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Preference.class);
        Preference preference1 = new Preference();
        preference1.setId(1L);
        Preference preference2 = new Preference();
        preference2.setId(preference1.getId());
        assertThat(preference1).isEqualTo(preference2);
        preference2.setId(2L);
        assertThat(preference1).isNotEqualTo(preference2);
        preference1.setId(null);
        assertThat(preference1).isNotEqualTo(preference2);
    }
}
