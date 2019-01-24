package com.kongtiantou.jerk.web.rest;

import com.kongtiantou.jerk.JerkkApp;

import com.kongtiantou.jerk.domain.Registration;
import com.kongtiantou.jerk.repository.RegistrationRepository;
import com.kongtiantou.jerk.repository.search.RegistrationSearchRepository;
import com.kongtiantou.jerk.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
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

import com.kongtiantou.jerk.domain.enumeration.Decision;
import com.kongtiantou.jerk.domain.enumeration.HxjslyEnum;
import com.kongtiantou.jerk.domain.enumeration.KjcgzhEnum;
import com.kongtiantou.jerk.domain.enumeration.JmlyqkEnum;
import com.kongtiantou.jerk.domain.enumeration.XbEnum;
import com.kongtiantou.jerk.domain.enumeration.AgeEnum;
import com.kongtiantou.jerk.domain.enumeration.AgeEnum;
import com.kongtiantou.jerk.domain.enumeration.Decision;
import com.kongtiantou.jerk.domain.enumeration.RzjhgkfwEnum;
import com.kongtiantou.jerk.domain.enumeration.RzmbEnum;
/**
 * Test class for the RegistrationResource REST controller.
 *
 * @see RegistrationResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = JerkkApp.class)
public class RegistrationResourceIntTest {

    private static final Integer DEFAULT_REGIST_TYPE = 1;
    private static final Integer UPDATED_REGIST_TYPE = 2;

    private static final String DEFAULT_DWQC = "AAAAAAAAAA";
    private static final String UPDATED_DWQC = "BBBBBBBBBB";

    private static final String DEFAULT_HXCPMC = "AAAAAAAAAA";
    private static final String UPDATED_HXCPMC = "BBBBBBBBBB";

    private static final String DEFAULT_ZZTJDW = "AAAAAAAAAA";
    private static final String UPDATED_ZZTJDW = "BBBBBBBBBB";

    private static final String DEFAULT_DWHGRDZ = "AAAAAAAAAA";
    private static final String UPDATED_DWHGRDZ = "BBBBBBBBBB";

    private static final String DEFAULT_SZQYLX = "AAAAAAAAAA";
    private static final String UPDATED_SZQYLX = "BBBBBBBBBB";

    private static final String DEFAULT_SSLY = "AAAAAAAAAA";
    private static final String UPDATED_SSLY = "BBBBBBBBBB";

    private static final String DEFAULT_GSCPJJ = "AAAAAAAAAA";
    private static final String UPDATED_GSCPJJ = "BBBBBBBBBB";

    private static final String DEFAULT_MBKHSC = "AAAAAAAAAA";
    private static final String UPDATED_MBKHSC = "BBBBBBBBBB";

    private static final String DEFAULT_DQZYKH = "AAAAAAAAAA";
    private static final String UPDATED_DQZYKH = "BBBBBBBBBB";

    private static final String DEFAULT_GNWHJJX = "AAAAAAAAAA";
    private static final String UPDATED_GNWHJJX = "BBBBBBBBBB";

    private static final Decision DEFAULT_ZLJS = Decision.YES;
    private static final Decision UPDATED_ZLJS = Decision.NO;

    private static final HxjslyEnum DEFAULT_HXJSLY = HxjslyEnum.S1;
    private static final HxjslyEnum UPDATED_HXJSLY = HxjslyEnum.S2;

    private static final KjcgzhEnum DEFAULT_KJCGZH = KjcgzhEnum.S1;
    private static final KjcgzhEnum UPDATED_KJCGZH = KjcgzhEnum.S2;

    private static final JmlyqkEnum DEFAULT_JMLYQK = JmlyqkEnum.K1;
    private static final JmlyqkEnum UPDATED_JMLYQK = JmlyqkEnum.K2;

    private static final String DEFAULT_JSCSD = "AAAAAAAAAA";
    private static final String UPDATED_JSCSD = "BBBBBBBBBB";

    private static final String DEFAULT_JZMSYLQK = "AAAAAAAAAA";
    private static final String UPDATED_JZMSYLQK = "BBBBBBBBBB";

    private static final String DEFAULT_JZYSJS = "AAAAAAAAAA";
    private static final String UPDATED_JZYSJS = "BBBBBBBBBB";

    private static final String DEFAULT_FZRDH = "AAAAAAAAAA";
    private static final String UPDATED_FZRDH = "BBBBBBBBBB";

    private static final XbEnum DEFAULT_XB = XbEnum.MAN;
    private static final XbEnum UPDATED_XB = XbEnum.WOMAN;

    private static final String DEFAULT_LXFS = "AAAAAAAAAA";
    private static final String UPDATED_LXFS = "BBBBBBBBBB";

    private static final String DEFAULT_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL = "BBBBBBBBBB";

    private static final AgeEnum DEFAULT_FZRNL = AgeEnum.A1;
    private static final AgeEnum UPDATED_FZRNL = AgeEnum.A2;

    private static final AgeEnum DEFAULT_TDPJNL = AgeEnum.A1;
    private static final AgeEnum UPDATED_TDPJNL = AgeEnum.A2;

    private static final Integer DEFAULT_GJRCS = 1;
    private static final Integer UPDATED_GJRCS = 2;

    private static final Decision DEFAULT_SFGJRZGXJSQY = Decision.YES;
    private static final Decision UPDATED_SFGJRZGXJSQY = Decision.NO;

    private static final String DEFAULT_TDYSJS = "AAAAAAAAAA";
    private static final String UPDATED_TDYSJS = "BBBBBBBBBB";

    private static final String DEFAULT_XYCZ = "AAAAAAAAAA";
    private static final String UPDATED_XYCZ = "BBBBBBBBBB";

    private static final String DEFAULT_WLXWHDZCLX = "AAAAAAAAAA";
    private static final String UPDATED_WLXWHDZCLX = "BBBBBBBBBB";

    private static final String DEFAULT_WLXWHDZCLX_1 = "AAAAAAAAAA";
    private static final String UPDATED_WLXWHDZCLX_1 = "BBBBBBBBBB";

    private static final String DEFAULT_SFXYXC = "AAAAAAAAAA";
    private static final String UPDATED_SFXYXC = "BBBBBBBBBB";

    private static final RzjhgkfwEnum DEFAULT_RZJHGKFW = RzjhgkfwEnum.O1;
    private static final RzjhgkfwEnum UPDATED_RZJHGKFW = RzjhgkfwEnum.O2;

    private static final RzmbEnum DEFAULT_RZMB = RzmbEnum.R1;
    private static final RzmbEnum UPDATED_RZMB = RzmbEnum.R2;

    private static final String DEFAULT_LXRZW = "AAAAAAAAAA";
    private static final String UPDATED_LXRZW = "BBBBBBBBBB";

    private static final String DEFAULT_LXDH = "AAAAAAAAAA";
    private static final String UPDATED_LXDH = "BBBBBBBBBB";

    private static final String DEFAULT_LXYX = "AAAAAAAAAA";
    private static final String UPDATED_LXYX = "BBBBBBBBBB";

    private static final String DEFAULT_LXDZ = "AAAAAAAAAA";
    private static final String UPDATED_LXDZ = "BBBBBBBBBB";

    private static final String DEFAULT_SSLY_1 = "AAAAAAAAAA";
    private static final String UPDATED_SSLY_1 = "BBBBBBBBBB";

    private static final Long DEFAULT_CREATED_DATE = 1L;
    private static final Long UPDATED_CREATED_DATE = 2L;

    private static final Long DEFAULT_MODIFIED_DATE = 1L;
    private static final Long UPDATED_MODIFIED_DATE = 2L;

    @Autowired
    private RegistrationRepository registrationRepository;

    /**
     * This repository is mocked in the com.kongtiantou.jerk.repository.search test package.
     *
     * @see com.kongtiantou.jerk.repository.search.RegistrationSearchRepositoryMockConfiguration
     */
    @Autowired
    private RegistrationSearchRepository mockRegistrationSearchRepository;

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

    private MockMvc restRegistrationMockMvc;

    private Registration registration;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final RegistrationResource registrationResource = new RegistrationResource(registrationRepository, mockRegistrationSearchRepository);
        this.restRegistrationMockMvc = MockMvcBuilders.standaloneSetup(registrationResource)
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
    public static Registration createEntity(EntityManager em) {
        Registration registration = new Registration()
            .registType(DEFAULT_REGIST_TYPE)
            .dwqc(DEFAULT_DWQC)
            .hxcpmc(DEFAULT_HXCPMC)
            .zztjdw(DEFAULT_ZZTJDW)
            .dwhgrdz(DEFAULT_DWHGRDZ)
            .szqylx(DEFAULT_SZQYLX)
            .ssly(DEFAULT_SSLY)
            .gscpjj(DEFAULT_GSCPJJ)
            .mbkhsc(DEFAULT_MBKHSC)
            .dqzykh(DEFAULT_DQZYKH)
            .gnwhjjx(DEFAULT_GNWHJJX)
            .zljs(DEFAULT_ZLJS)
            .hxjsly(DEFAULT_HXJSLY)
            .kjcgzh(DEFAULT_KJCGZH)
            .jmlyqk(DEFAULT_JMLYQK)
            .jscsd(DEFAULT_JSCSD)
            .jzmsylqk(DEFAULT_JZMSYLQK)
            .jzysjs(DEFAULT_JZYSJS)
            .fzrdh(DEFAULT_FZRDH)
            .xb(DEFAULT_XB)
            .lxfs(DEFAULT_LXFS)
            .email(DEFAULT_EMAIL)
            .fzrnl(DEFAULT_FZRNL)
            .tdpjnl(DEFAULT_TDPJNL)
            .gjrcs(DEFAULT_GJRCS)
            .sfgjrzgxjsqy(DEFAULT_SFGJRZGXJSQY)
            .tdysjs(DEFAULT_TDYSJS)
            .xycz(DEFAULT_XYCZ)
            .wlxwhdzclx(DEFAULT_WLXWHDZCLX)
            .wlxwhdzclx1(DEFAULT_WLXWHDZCLX_1)
            .sfxyxc(DEFAULT_SFXYXC)
            .rzjhgkfw(DEFAULT_RZJHGKFW)
            .rzmb(DEFAULT_RZMB)
            .lxrzw(DEFAULT_LXRZW)
            .lxdh(DEFAULT_LXDH)
            .lxyx(DEFAULT_LXYX)
            .lxdz(DEFAULT_LXDZ)
            .ssly1(DEFAULT_SSLY_1)
            .createdDate(DEFAULT_CREATED_DATE)
            .modifiedDate(DEFAULT_MODIFIED_DATE);
        return registration;
    }

    @Before
    public void initTest() {
        registration = createEntity(em);
    }

    @Test
    @Transactional
    public void createRegistration() throws Exception {
        int databaseSizeBeforeCreate = registrationRepository.findAll().size();

        // Create the Registration
        restRegistrationMockMvc.perform(post("/api/registrations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(registration)))
            .andExpect(status().isCreated());

        // Validate the Registration in the database
        List<Registration> registrationList = registrationRepository.findAll();
        assertThat(registrationList).hasSize(databaseSizeBeforeCreate + 1);
        Registration testRegistration = registrationList.get(registrationList.size() - 1);
        assertThat(testRegistration.getRegistType()).isEqualTo(DEFAULT_REGIST_TYPE);
        assertThat(testRegistration.getDwqc()).isEqualTo(DEFAULT_DWQC);
        assertThat(testRegistration.getHxcpmc()).isEqualTo(DEFAULT_HXCPMC);
        assertThat(testRegistration.getZztjdw()).isEqualTo(DEFAULT_ZZTJDW);
        assertThat(testRegistration.getDwhgrdz()).isEqualTo(DEFAULT_DWHGRDZ);
        assertThat(testRegistration.getSzqylx()).isEqualTo(DEFAULT_SZQYLX);
        assertThat(testRegistration.getSsly()).isEqualTo(DEFAULT_SSLY);
        assertThat(testRegistration.getGscpjj()).isEqualTo(DEFAULT_GSCPJJ);
        assertThat(testRegistration.getMbkhsc()).isEqualTo(DEFAULT_MBKHSC);
        assertThat(testRegistration.getDqzykh()).isEqualTo(DEFAULT_DQZYKH);
        assertThat(testRegistration.getGnwhjjx()).isEqualTo(DEFAULT_GNWHJJX);
        assertThat(testRegistration.getZljs()).isEqualTo(DEFAULT_ZLJS);
        assertThat(testRegistration.getHxjsly()).isEqualTo(DEFAULT_HXJSLY);
        assertThat(testRegistration.getKjcgzh()).isEqualTo(DEFAULT_KJCGZH);
        assertThat(testRegistration.getJmlyqk()).isEqualTo(DEFAULT_JMLYQK);
        assertThat(testRegistration.getJscsd()).isEqualTo(DEFAULT_JSCSD);
        assertThat(testRegistration.getJzmsylqk()).isEqualTo(DEFAULT_JZMSYLQK);
        assertThat(testRegistration.getJzysjs()).isEqualTo(DEFAULT_JZYSJS);
        assertThat(testRegistration.getFzrdh()).isEqualTo(DEFAULT_FZRDH);
        assertThat(testRegistration.getXb()).isEqualTo(DEFAULT_XB);
        assertThat(testRegistration.getLxfs()).isEqualTo(DEFAULT_LXFS);
        assertThat(testRegistration.getEmail()).isEqualTo(DEFAULT_EMAIL);
        assertThat(testRegistration.getFzrnl()).isEqualTo(DEFAULT_FZRNL);
        assertThat(testRegistration.getTdpjnl()).isEqualTo(DEFAULT_TDPJNL);
        assertThat(testRegistration.getGjrcs()).isEqualTo(DEFAULT_GJRCS);
        assertThat(testRegistration.getSfgjrzgxjsqy()).isEqualTo(DEFAULT_SFGJRZGXJSQY);
        assertThat(testRegistration.getTdysjs()).isEqualTo(DEFAULT_TDYSJS);
        assertThat(testRegistration.getXycz()).isEqualTo(DEFAULT_XYCZ);
        assertThat(testRegistration.getWlxwhdzclx()).isEqualTo(DEFAULT_WLXWHDZCLX);
        assertThat(testRegistration.getWlxwhdzclx1()).isEqualTo(DEFAULT_WLXWHDZCLX_1);
        assertThat(testRegistration.getSfxyxc()).isEqualTo(DEFAULT_SFXYXC);
        assertThat(testRegistration.getRzjhgkfw()).isEqualTo(DEFAULT_RZJHGKFW);
        assertThat(testRegistration.getRzmb()).isEqualTo(DEFAULT_RZMB);
        assertThat(testRegistration.getLxrzw()).isEqualTo(DEFAULT_LXRZW);
        assertThat(testRegistration.getLxdh()).isEqualTo(DEFAULT_LXDH);
        assertThat(testRegistration.getLxyx()).isEqualTo(DEFAULT_LXYX);
        assertThat(testRegistration.getLxdz()).isEqualTo(DEFAULT_LXDZ);
        assertThat(testRegistration.getSsly1()).isEqualTo(DEFAULT_SSLY_1);
        assertThat(testRegistration.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testRegistration.getModifiedDate()).isEqualTo(DEFAULT_MODIFIED_DATE);

        // Validate the Registration in Elasticsearch
        verify(mockRegistrationSearchRepository, times(1)).save(testRegistration);
    }

    @Test
    @Transactional
    public void createRegistrationWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = registrationRepository.findAll().size();

        // Create the Registration with an existing ID
        registration.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restRegistrationMockMvc.perform(post("/api/registrations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(registration)))
            .andExpect(status().isBadRequest());

        // Validate the Registration in the database
        List<Registration> registrationList = registrationRepository.findAll();
        assertThat(registrationList).hasSize(databaseSizeBeforeCreate);

        // Validate the Registration in Elasticsearch
        verify(mockRegistrationSearchRepository, times(0)).save(registration);
    }

    @Test
    @Transactional
    public void checkRegistTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = registrationRepository.findAll().size();
        // set the field null
        registration.setRegistType(null);

        // Create the Registration, which fails.

        restRegistrationMockMvc.perform(post("/api/registrations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(registration)))
            .andExpect(status().isBadRequest());

        List<Registration> registrationList = registrationRepository.findAll();
        assertThat(registrationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDwqcIsRequired() throws Exception {
        int databaseSizeBeforeTest = registrationRepository.findAll().size();
        // set the field null
        registration.setDwqc(null);

        // Create the Registration, which fails.

        restRegistrationMockMvc.perform(post("/api/registrations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(registration)))
            .andExpect(status().isBadRequest());

        List<Registration> registrationList = registrationRepository.findAll();
        assertThat(registrationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkHxcpmcIsRequired() throws Exception {
        int databaseSizeBeforeTest = registrationRepository.findAll().size();
        // set the field null
        registration.setHxcpmc(null);

        // Create the Registration, which fails.

        restRegistrationMockMvc.perform(post("/api/registrations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(registration)))
            .andExpect(status().isBadRequest());

        List<Registration> registrationList = registrationRepository.findAll();
        assertThat(registrationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDwhgrdzIsRequired() throws Exception {
        int databaseSizeBeforeTest = registrationRepository.findAll().size();
        // set the field null
        registration.setDwhgrdz(null);

        // Create the Registration, which fails.

        restRegistrationMockMvc.perform(post("/api/registrations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(registration)))
            .andExpect(status().isBadRequest());

        List<Registration> registrationList = registrationRepository.findAll();
        assertThat(registrationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkSzqylxIsRequired() throws Exception {
        int databaseSizeBeforeTest = registrationRepository.findAll().size();
        // set the field null
        registration.setSzqylx(null);

        // Create the Registration, which fails.

        restRegistrationMockMvc.perform(post("/api/registrations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(registration)))
            .andExpect(status().isBadRequest());

        List<Registration> registrationList = registrationRepository.findAll();
        assertThat(registrationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkSslyIsRequired() throws Exception {
        int databaseSizeBeforeTest = registrationRepository.findAll().size();
        // set the field null
        registration.setSsly(null);

        // Create the Registration, which fails.

        restRegistrationMockMvc.perform(post("/api/registrations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(registration)))
            .andExpect(status().isBadRequest());

        List<Registration> registrationList = registrationRepository.findAll();
        assertThat(registrationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkGscpjjIsRequired() throws Exception {
        int databaseSizeBeforeTest = registrationRepository.findAll().size();
        // set the field null
        registration.setGscpjj(null);

        // Create the Registration, which fails.

        restRegistrationMockMvc.perform(post("/api/registrations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(registration)))
            .andExpect(status().isBadRequest());

        List<Registration> registrationList = registrationRepository.findAll();
        assertThat(registrationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkMbkhscIsRequired() throws Exception {
        int databaseSizeBeforeTest = registrationRepository.findAll().size();
        // set the field null
        registration.setMbkhsc(null);

        // Create the Registration, which fails.

        restRegistrationMockMvc.perform(post("/api/registrations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(registration)))
            .andExpect(status().isBadRequest());

        List<Registration> registrationList = registrationRepository.findAll();
        assertThat(registrationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkZljsIsRequired() throws Exception {
        int databaseSizeBeforeTest = registrationRepository.findAll().size();
        // set the field null
        registration.setZljs(null);

        // Create the Registration, which fails.

        restRegistrationMockMvc.perform(post("/api/registrations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(registration)))
            .andExpect(status().isBadRequest());

        List<Registration> registrationList = registrationRepository.findAll();
        assertThat(registrationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkHxjslyIsRequired() throws Exception {
        int databaseSizeBeforeTest = registrationRepository.findAll().size();
        // set the field null
        registration.setHxjsly(null);

        // Create the Registration, which fails.

        restRegistrationMockMvc.perform(post("/api/registrations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(registration)))
            .andExpect(status().isBadRequest());

        List<Registration> registrationList = registrationRepository.findAll();
        assertThat(registrationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkKjcgzhIsRequired() throws Exception {
        int databaseSizeBeforeTest = registrationRepository.findAll().size();
        // set the field null
        registration.setKjcgzh(null);

        // Create the Registration, which fails.

        restRegistrationMockMvc.perform(post("/api/registrations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(registration)))
            .andExpect(status().isBadRequest());

        List<Registration> registrationList = registrationRepository.findAll();
        assertThat(registrationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkJmlyqkIsRequired() throws Exception {
        int databaseSizeBeforeTest = registrationRepository.findAll().size();
        // set the field null
        registration.setJmlyqk(null);

        // Create the Registration, which fails.

        restRegistrationMockMvc.perform(post("/api/registrations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(registration)))
            .andExpect(status().isBadRequest());

        List<Registration> registrationList = registrationRepository.findAll();
        assertThat(registrationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkJzysjsIsRequired() throws Exception {
        int databaseSizeBeforeTest = registrationRepository.findAll().size();
        // set the field null
        registration.setJzysjs(null);

        // Create the Registration, which fails.

        restRegistrationMockMvc.perform(post("/api/registrations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(registration)))
            .andExpect(status().isBadRequest());

        List<Registration> registrationList = registrationRepository.findAll();
        assertThat(registrationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkFzrdhIsRequired() throws Exception {
        int databaseSizeBeforeTest = registrationRepository.findAll().size();
        // set the field null
        registration.setFzrdh(null);

        // Create the Registration, which fails.

        restRegistrationMockMvc.perform(post("/api/registrations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(registration)))
            .andExpect(status().isBadRequest());

        List<Registration> registrationList = registrationRepository.findAll();
        assertThat(registrationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkXbIsRequired() throws Exception {
        int databaseSizeBeforeTest = registrationRepository.findAll().size();
        // set the field null
        registration.setXb(null);

        // Create the Registration, which fails.

        restRegistrationMockMvc.perform(post("/api/registrations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(registration)))
            .andExpect(status().isBadRequest());

        List<Registration> registrationList = registrationRepository.findAll();
        assertThat(registrationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkLxfsIsRequired() throws Exception {
        int databaseSizeBeforeTest = registrationRepository.findAll().size();
        // set the field null
        registration.setLxfs(null);

        // Create the Registration, which fails.

        restRegistrationMockMvc.perform(post("/api/registrations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(registration)))
            .andExpect(status().isBadRequest());

        List<Registration> registrationList = registrationRepository.findAll();
        assertThat(registrationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkEmailIsRequired() throws Exception {
        int databaseSizeBeforeTest = registrationRepository.findAll().size();
        // set the field null
        registration.setEmail(null);

        // Create the Registration, which fails.

        restRegistrationMockMvc.perform(post("/api/registrations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(registration)))
            .andExpect(status().isBadRequest());

        List<Registration> registrationList = registrationRepository.findAll();
        assertThat(registrationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkFzrnlIsRequired() throws Exception {
        int databaseSizeBeforeTest = registrationRepository.findAll().size();
        // set the field null
        registration.setFzrnl(null);

        // Create the Registration, which fails.

        restRegistrationMockMvc.perform(post("/api/registrations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(registration)))
            .andExpect(status().isBadRequest());

        List<Registration> registrationList = registrationRepository.findAll();
        assertThat(registrationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkGjrcsIsRequired() throws Exception {
        int databaseSizeBeforeTest = registrationRepository.findAll().size();
        // set the field null
        registration.setGjrcs(null);

        // Create the Registration, which fails.

        restRegistrationMockMvc.perform(post("/api/registrations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(registration)))
            .andExpect(status().isBadRequest());

        List<Registration> registrationList = registrationRepository.findAll();
        assertThat(registrationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkSfgjrzgxjsqyIsRequired() throws Exception {
        int databaseSizeBeforeTest = registrationRepository.findAll().size();
        // set the field null
        registration.setSfgjrzgxjsqy(null);

        // Create the Registration, which fails.

        restRegistrationMockMvc.perform(post("/api/registrations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(registration)))
            .andExpect(status().isBadRequest());

        List<Registration> registrationList = registrationRepository.findAll();
        assertThat(registrationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTdysjsIsRequired() throws Exception {
        int databaseSizeBeforeTest = registrationRepository.findAll().size();
        // set the field null
        registration.setTdysjs(null);

        // Create the Registration, which fails.

        restRegistrationMockMvc.perform(post("/api/registrations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(registration)))
            .andExpect(status().isBadRequest());

        List<Registration> registrationList = registrationRepository.findAll();
        assertThat(registrationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkXyczIsRequired() throws Exception {
        int databaseSizeBeforeTest = registrationRepository.findAll().size();
        // set the field null
        registration.setXycz(null);

        // Create the Registration, which fails.

        restRegistrationMockMvc.perform(post("/api/registrations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(registration)))
            .andExpect(status().isBadRequest());

        List<Registration> registrationList = registrationRepository.findAll();
        assertThat(registrationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkWlxwhdzclxIsRequired() throws Exception {
        int databaseSizeBeforeTest = registrationRepository.findAll().size();
        // set the field null
        registration.setWlxwhdzclx(null);

        // Create the Registration, which fails.

        restRegistrationMockMvc.perform(post("/api/registrations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(registration)))
            .andExpect(status().isBadRequest());

        List<Registration> registrationList = registrationRepository.findAll();
        assertThat(registrationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkLxrzwIsRequired() throws Exception {
        int databaseSizeBeforeTest = registrationRepository.findAll().size();
        // set the field null
        registration.setLxrzw(null);

        // Create the Registration, which fails.

        restRegistrationMockMvc.perform(post("/api/registrations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(registration)))
            .andExpect(status().isBadRequest());

        List<Registration> registrationList = registrationRepository.findAll();
        assertThat(registrationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkLxdhIsRequired() throws Exception {
        int databaseSizeBeforeTest = registrationRepository.findAll().size();
        // set the field null
        registration.setLxdh(null);

        // Create the Registration, which fails.

        restRegistrationMockMvc.perform(post("/api/registrations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(registration)))
            .andExpect(status().isBadRequest());

        List<Registration> registrationList = registrationRepository.findAll();
        assertThat(registrationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkLxyxIsRequired() throws Exception {
        int databaseSizeBeforeTest = registrationRepository.findAll().size();
        // set the field null
        registration.setLxyx(null);

        // Create the Registration, which fails.

        restRegistrationMockMvc.perform(post("/api/registrations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(registration)))
            .andExpect(status().isBadRequest());

        List<Registration> registrationList = registrationRepository.findAll();
        assertThat(registrationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkLxdzIsRequired() throws Exception {
        int databaseSizeBeforeTest = registrationRepository.findAll().size();
        // set the field null
        registration.setLxdz(null);

        // Create the Registration, which fails.

        restRegistrationMockMvc.perform(post("/api/registrations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(registration)))
            .andExpect(status().isBadRequest());

        List<Registration> registrationList = registrationRepository.findAll();
        assertThat(registrationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCreatedDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = registrationRepository.findAll().size();
        // set the field null
        registration.setCreatedDate(null);

        // Create the Registration, which fails.

        restRegistrationMockMvc.perform(post("/api/registrations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(registration)))
            .andExpect(status().isBadRequest());

        List<Registration> registrationList = registrationRepository.findAll();
        assertThat(registrationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllRegistrations() throws Exception {
        // Initialize the database
        registrationRepository.saveAndFlush(registration);

        // Get all the registrationList
        restRegistrationMockMvc.perform(get("/api/registrations?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(registration.getId().intValue())))
            .andExpect(jsonPath("$.[*].registType").value(hasItem(DEFAULT_REGIST_TYPE)))
            .andExpect(jsonPath("$.[*].dwqc").value(hasItem(DEFAULT_DWQC.toString())))
            .andExpect(jsonPath("$.[*].hxcpmc").value(hasItem(DEFAULT_HXCPMC.toString())))
            .andExpect(jsonPath("$.[*].zztjdw").value(hasItem(DEFAULT_ZZTJDW.toString())))
            .andExpect(jsonPath("$.[*].dwhgrdz").value(hasItem(DEFAULT_DWHGRDZ.toString())))
            .andExpect(jsonPath("$.[*].szqylx").value(hasItem(DEFAULT_SZQYLX.toString())))
            .andExpect(jsonPath("$.[*].ssly").value(hasItem(DEFAULT_SSLY.toString())))
            .andExpect(jsonPath("$.[*].gscpjj").value(hasItem(DEFAULT_GSCPJJ.toString())))
            .andExpect(jsonPath("$.[*].mbkhsc").value(hasItem(DEFAULT_MBKHSC.toString())))
            .andExpect(jsonPath("$.[*].dqzykh").value(hasItem(DEFAULT_DQZYKH.toString())))
            .andExpect(jsonPath("$.[*].gnwhjjx").value(hasItem(DEFAULT_GNWHJJX.toString())))
            .andExpect(jsonPath("$.[*].zljs").value(hasItem(DEFAULT_ZLJS.toString())))
            .andExpect(jsonPath("$.[*].hxjsly").value(hasItem(DEFAULT_HXJSLY.toString())))
            .andExpect(jsonPath("$.[*].kjcgzh").value(hasItem(DEFAULT_KJCGZH.toString())))
            .andExpect(jsonPath("$.[*].jmlyqk").value(hasItem(DEFAULT_JMLYQK.toString())))
            .andExpect(jsonPath("$.[*].jscsd").value(hasItem(DEFAULT_JSCSD.toString())))
            .andExpect(jsonPath("$.[*].jzmsylqk").value(hasItem(DEFAULT_JZMSYLQK.toString())))
            .andExpect(jsonPath("$.[*].jzysjs").value(hasItem(DEFAULT_JZYSJS.toString())))
            .andExpect(jsonPath("$.[*].fzrdh").value(hasItem(DEFAULT_FZRDH.toString())))
            .andExpect(jsonPath("$.[*].xb").value(hasItem(DEFAULT_XB.toString())))
            .andExpect(jsonPath("$.[*].lxfs").value(hasItem(DEFAULT_LXFS.toString())))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL.toString())))
            .andExpect(jsonPath("$.[*].fzrnl").value(hasItem(DEFAULT_FZRNL.toString())))
            .andExpect(jsonPath("$.[*].tdpjnl").value(hasItem(DEFAULT_TDPJNL.toString())))
            .andExpect(jsonPath("$.[*].gjrcs").value(hasItem(DEFAULT_GJRCS)))
            .andExpect(jsonPath("$.[*].sfgjrzgxjsqy").value(hasItem(DEFAULT_SFGJRZGXJSQY.toString())))
            .andExpect(jsonPath("$.[*].tdysjs").value(hasItem(DEFAULT_TDYSJS.toString())))
            .andExpect(jsonPath("$.[*].xycz").value(hasItem(DEFAULT_XYCZ.toString())))
            .andExpect(jsonPath("$.[*].wlxwhdzclx").value(hasItem(DEFAULT_WLXWHDZCLX.toString())))
            .andExpect(jsonPath("$.[*].wlxwhdzclx1").value(hasItem(DEFAULT_WLXWHDZCLX_1.toString())))
            .andExpect(jsonPath("$.[*].sfxyxc").value(hasItem(DEFAULT_SFXYXC.toString())))
            .andExpect(jsonPath("$.[*].rzjhgkfw").value(hasItem(DEFAULT_RZJHGKFW.toString())))
            .andExpect(jsonPath("$.[*].rzmb").value(hasItem(DEFAULT_RZMB.toString())))
            .andExpect(jsonPath("$.[*].lxrzw").value(hasItem(DEFAULT_LXRZW.toString())))
            .andExpect(jsonPath("$.[*].lxdh").value(hasItem(DEFAULT_LXDH.toString())))
            .andExpect(jsonPath("$.[*].lxyx").value(hasItem(DEFAULT_LXYX.toString())))
            .andExpect(jsonPath("$.[*].lxdz").value(hasItem(DEFAULT_LXDZ.toString())))
            .andExpect(jsonPath("$.[*].ssly1").value(hasItem(DEFAULT_SSLY_1.toString())))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.intValue())))
            .andExpect(jsonPath("$.[*].modifiedDate").value(hasItem(DEFAULT_MODIFIED_DATE.intValue())));
    }
    
    @Test
    @Transactional
    public void getRegistration() throws Exception {
        // Initialize the database
        registrationRepository.saveAndFlush(registration);

        // Get the registration
        restRegistrationMockMvc.perform(get("/api/registrations/{id}", registration.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(registration.getId().intValue()))
            .andExpect(jsonPath("$.registType").value(DEFAULT_REGIST_TYPE))
            .andExpect(jsonPath("$.dwqc").value(DEFAULT_DWQC.toString()))
            .andExpect(jsonPath("$.hxcpmc").value(DEFAULT_HXCPMC.toString()))
            .andExpect(jsonPath("$.zztjdw").value(DEFAULT_ZZTJDW.toString()))
            .andExpect(jsonPath("$.dwhgrdz").value(DEFAULT_DWHGRDZ.toString()))
            .andExpect(jsonPath("$.szqylx").value(DEFAULT_SZQYLX.toString()))
            .andExpect(jsonPath("$.ssly").value(DEFAULT_SSLY.toString()))
            .andExpect(jsonPath("$.gscpjj").value(DEFAULT_GSCPJJ.toString()))
            .andExpect(jsonPath("$.mbkhsc").value(DEFAULT_MBKHSC.toString()))
            .andExpect(jsonPath("$.dqzykh").value(DEFAULT_DQZYKH.toString()))
            .andExpect(jsonPath("$.gnwhjjx").value(DEFAULT_GNWHJJX.toString()))
            .andExpect(jsonPath("$.zljs").value(DEFAULT_ZLJS.toString()))
            .andExpect(jsonPath("$.hxjsly").value(DEFAULT_HXJSLY.toString()))
            .andExpect(jsonPath("$.kjcgzh").value(DEFAULT_KJCGZH.toString()))
            .andExpect(jsonPath("$.jmlyqk").value(DEFAULT_JMLYQK.toString()))
            .andExpect(jsonPath("$.jscsd").value(DEFAULT_JSCSD.toString()))
            .andExpect(jsonPath("$.jzmsylqk").value(DEFAULT_JZMSYLQK.toString()))
            .andExpect(jsonPath("$.jzysjs").value(DEFAULT_JZYSJS.toString()))
            .andExpect(jsonPath("$.fzrdh").value(DEFAULT_FZRDH.toString()))
            .andExpect(jsonPath("$.xb").value(DEFAULT_XB.toString()))
            .andExpect(jsonPath("$.lxfs").value(DEFAULT_LXFS.toString()))
            .andExpect(jsonPath("$.email").value(DEFAULT_EMAIL.toString()))
            .andExpect(jsonPath("$.fzrnl").value(DEFAULT_FZRNL.toString()))
            .andExpect(jsonPath("$.tdpjnl").value(DEFAULT_TDPJNL.toString()))
            .andExpect(jsonPath("$.gjrcs").value(DEFAULT_GJRCS))
            .andExpect(jsonPath("$.sfgjrzgxjsqy").value(DEFAULT_SFGJRZGXJSQY.toString()))
            .andExpect(jsonPath("$.tdysjs").value(DEFAULT_TDYSJS.toString()))
            .andExpect(jsonPath("$.xycz").value(DEFAULT_XYCZ.toString()))
            .andExpect(jsonPath("$.wlxwhdzclx").value(DEFAULT_WLXWHDZCLX.toString()))
            .andExpect(jsonPath("$.wlxwhdzclx1").value(DEFAULT_WLXWHDZCLX_1.toString()))
            .andExpect(jsonPath("$.sfxyxc").value(DEFAULT_SFXYXC.toString()))
            .andExpect(jsonPath("$.rzjhgkfw").value(DEFAULT_RZJHGKFW.toString()))
            .andExpect(jsonPath("$.rzmb").value(DEFAULT_RZMB.toString()))
            .andExpect(jsonPath("$.lxrzw").value(DEFAULT_LXRZW.toString()))
            .andExpect(jsonPath("$.lxdh").value(DEFAULT_LXDH.toString()))
            .andExpect(jsonPath("$.lxyx").value(DEFAULT_LXYX.toString()))
            .andExpect(jsonPath("$.lxdz").value(DEFAULT_LXDZ.toString()))
            .andExpect(jsonPath("$.ssly1").value(DEFAULT_SSLY_1.toString()))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE.intValue()))
            .andExpect(jsonPath("$.modifiedDate").value(DEFAULT_MODIFIED_DATE.intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingRegistration() throws Exception {
        // Get the registration
        restRegistrationMockMvc.perform(get("/api/registrations/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateRegistration() throws Exception {
        // Initialize the database
        registrationRepository.saveAndFlush(registration);

        int databaseSizeBeforeUpdate = registrationRepository.findAll().size();

        // Update the registration
        Registration updatedRegistration = registrationRepository.findById(registration.getId()).get();
        // Disconnect from session so that the updates on updatedRegistration are not directly saved in db
        em.detach(updatedRegistration);
        updatedRegistration
            .registType(UPDATED_REGIST_TYPE)
            .dwqc(UPDATED_DWQC)
            .hxcpmc(UPDATED_HXCPMC)
            .zztjdw(UPDATED_ZZTJDW)
            .dwhgrdz(UPDATED_DWHGRDZ)
            .szqylx(UPDATED_SZQYLX)
            .ssly(UPDATED_SSLY)
            .gscpjj(UPDATED_GSCPJJ)
            .mbkhsc(UPDATED_MBKHSC)
            .dqzykh(UPDATED_DQZYKH)
            .gnwhjjx(UPDATED_GNWHJJX)
            .zljs(UPDATED_ZLJS)
            .hxjsly(UPDATED_HXJSLY)
            .kjcgzh(UPDATED_KJCGZH)
            .jmlyqk(UPDATED_JMLYQK)
            .jscsd(UPDATED_JSCSD)
            .jzmsylqk(UPDATED_JZMSYLQK)
            .jzysjs(UPDATED_JZYSJS)
            .fzrdh(UPDATED_FZRDH)
            .xb(UPDATED_XB)
            .lxfs(UPDATED_LXFS)
            .email(UPDATED_EMAIL)
            .fzrnl(UPDATED_FZRNL)
            .tdpjnl(UPDATED_TDPJNL)
            .gjrcs(UPDATED_GJRCS)
            .sfgjrzgxjsqy(UPDATED_SFGJRZGXJSQY)
            .tdysjs(UPDATED_TDYSJS)
            .xycz(UPDATED_XYCZ)
            .wlxwhdzclx(UPDATED_WLXWHDZCLX)
            .wlxwhdzclx1(UPDATED_WLXWHDZCLX_1)
            .sfxyxc(UPDATED_SFXYXC)
            .rzjhgkfw(UPDATED_RZJHGKFW)
            .rzmb(UPDATED_RZMB)
            .lxrzw(UPDATED_LXRZW)
            .lxdh(UPDATED_LXDH)
            .lxyx(UPDATED_LXYX)
            .lxdz(UPDATED_LXDZ)
            .ssly1(UPDATED_SSLY_1)
            .createdDate(UPDATED_CREATED_DATE)
            .modifiedDate(UPDATED_MODIFIED_DATE);

        restRegistrationMockMvc.perform(put("/api/registrations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedRegistration)))
            .andExpect(status().isOk());

        // Validate the Registration in the database
        List<Registration> registrationList = registrationRepository.findAll();
        assertThat(registrationList).hasSize(databaseSizeBeforeUpdate);
        Registration testRegistration = registrationList.get(registrationList.size() - 1);
        assertThat(testRegistration.getRegistType()).isEqualTo(UPDATED_REGIST_TYPE);
        assertThat(testRegistration.getDwqc()).isEqualTo(UPDATED_DWQC);
        assertThat(testRegistration.getHxcpmc()).isEqualTo(UPDATED_HXCPMC);
        assertThat(testRegistration.getZztjdw()).isEqualTo(UPDATED_ZZTJDW);
        assertThat(testRegistration.getDwhgrdz()).isEqualTo(UPDATED_DWHGRDZ);
        assertThat(testRegistration.getSzqylx()).isEqualTo(UPDATED_SZQYLX);
        assertThat(testRegistration.getSsly()).isEqualTo(UPDATED_SSLY);
        assertThat(testRegistration.getGscpjj()).isEqualTo(UPDATED_GSCPJJ);
        assertThat(testRegistration.getMbkhsc()).isEqualTo(UPDATED_MBKHSC);
        assertThat(testRegistration.getDqzykh()).isEqualTo(UPDATED_DQZYKH);
        assertThat(testRegistration.getGnwhjjx()).isEqualTo(UPDATED_GNWHJJX);
        assertThat(testRegistration.getZljs()).isEqualTo(UPDATED_ZLJS);
        assertThat(testRegistration.getHxjsly()).isEqualTo(UPDATED_HXJSLY);
        assertThat(testRegistration.getKjcgzh()).isEqualTo(UPDATED_KJCGZH);
        assertThat(testRegistration.getJmlyqk()).isEqualTo(UPDATED_JMLYQK);
        assertThat(testRegistration.getJscsd()).isEqualTo(UPDATED_JSCSD);
        assertThat(testRegistration.getJzmsylqk()).isEqualTo(UPDATED_JZMSYLQK);
        assertThat(testRegistration.getJzysjs()).isEqualTo(UPDATED_JZYSJS);
        assertThat(testRegistration.getFzrdh()).isEqualTo(UPDATED_FZRDH);
        assertThat(testRegistration.getXb()).isEqualTo(UPDATED_XB);
        assertThat(testRegistration.getLxfs()).isEqualTo(UPDATED_LXFS);
        assertThat(testRegistration.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testRegistration.getFzrnl()).isEqualTo(UPDATED_FZRNL);
        assertThat(testRegistration.getTdpjnl()).isEqualTo(UPDATED_TDPJNL);
        assertThat(testRegistration.getGjrcs()).isEqualTo(UPDATED_GJRCS);
        assertThat(testRegistration.getSfgjrzgxjsqy()).isEqualTo(UPDATED_SFGJRZGXJSQY);
        assertThat(testRegistration.getTdysjs()).isEqualTo(UPDATED_TDYSJS);
        assertThat(testRegistration.getXycz()).isEqualTo(UPDATED_XYCZ);
        assertThat(testRegistration.getWlxwhdzclx()).isEqualTo(UPDATED_WLXWHDZCLX);
        assertThat(testRegistration.getWlxwhdzclx1()).isEqualTo(UPDATED_WLXWHDZCLX_1);
        assertThat(testRegistration.getSfxyxc()).isEqualTo(UPDATED_SFXYXC);
        assertThat(testRegistration.getRzjhgkfw()).isEqualTo(UPDATED_RZJHGKFW);
        assertThat(testRegistration.getRzmb()).isEqualTo(UPDATED_RZMB);
        assertThat(testRegistration.getLxrzw()).isEqualTo(UPDATED_LXRZW);
        assertThat(testRegistration.getLxdh()).isEqualTo(UPDATED_LXDH);
        assertThat(testRegistration.getLxyx()).isEqualTo(UPDATED_LXYX);
        assertThat(testRegistration.getLxdz()).isEqualTo(UPDATED_LXDZ);
        assertThat(testRegistration.getSsly1()).isEqualTo(UPDATED_SSLY_1);
        assertThat(testRegistration.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testRegistration.getModifiedDate()).isEqualTo(UPDATED_MODIFIED_DATE);

        // Validate the Registration in Elasticsearch
        verify(mockRegistrationSearchRepository, times(1)).save(testRegistration);
    }

    @Test
    @Transactional
    public void updateNonExistingRegistration() throws Exception {
        int databaseSizeBeforeUpdate = registrationRepository.findAll().size();

        // Create the Registration

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restRegistrationMockMvc.perform(put("/api/registrations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(registration)))
            .andExpect(status().isBadRequest());

        // Validate the Registration in the database
        List<Registration> registrationList = registrationRepository.findAll();
        assertThat(registrationList).hasSize(databaseSizeBeforeUpdate);

        // Validate the Registration in Elasticsearch
        verify(mockRegistrationSearchRepository, times(0)).save(registration);
    }

    @Test
    @Transactional
    public void deleteRegistration() throws Exception {
        // Initialize the database
        registrationRepository.saveAndFlush(registration);

        int databaseSizeBeforeDelete = registrationRepository.findAll().size();

        // Get the registration
        restRegistrationMockMvc.perform(delete("/api/registrations/{id}", registration.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Registration> registrationList = registrationRepository.findAll();
        assertThat(registrationList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the Registration in Elasticsearch
        verify(mockRegistrationSearchRepository, times(1)).deleteById(registration.getId());
    }

    @Test
    @Transactional
    public void searchRegistration() throws Exception {
        // Initialize the database
        registrationRepository.saveAndFlush(registration);
        when(mockRegistrationSearchRepository.search(queryStringQuery("id:" + registration.getId()), PageRequest.of(0, 20)))
            .thenReturn(new PageImpl<>(Collections.singletonList(registration), PageRequest.of(0, 1), 1));
        // Search the registration
        restRegistrationMockMvc.perform(get("/api/_search/registrations?query=id:" + registration.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(registration.getId().intValue())))
            .andExpect(jsonPath("$.[*].registType").value(hasItem(DEFAULT_REGIST_TYPE)))
            .andExpect(jsonPath("$.[*].dwqc").value(hasItem(DEFAULT_DWQC)))
            .andExpect(jsonPath("$.[*].hxcpmc").value(hasItem(DEFAULT_HXCPMC)))
            .andExpect(jsonPath("$.[*].zztjdw").value(hasItem(DEFAULT_ZZTJDW)))
            .andExpect(jsonPath("$.[*].dwhgrdz").value(hasItem(DEFAULT_DWHGRDZ)))
            .andExpect(jsonPath("$.[*].szqylx").value(hasItem(DEFAULT_SZQYLX)))
            .andExpect(jsonPath("$.[*].ssly").value(hasItem(DEFAULT_SSLY)))
            .andExpect(jsonPath("$.[*].gscpjj").value(hasItem(DEFAULT_GSCPJJ)))
            .andExpect(jsonPath("$.[*].mbkhsc").value(hasItem(DEFAULT_MBKHSC)))
            .andExpect(jsonPath("$.[*].dqzykh").value(hasItem(DEFAULT_DQZYKH)))
            .andExpect(jsonPath("$.[*].gnwhjjx").value(hasItem(DEFAULT_GNWHJJX)))
            .andExpect(jsonPath("$.[*].zljs").value(hasItem(DEFAULT_ZLJS.toString())))
            .andExpect(jsonPath("$.[*].hxjsly").value(hasItem(DEFAULT_HXJSLY.toString())))
            .andExpect(jsonPath("$.[*].kjcgzh").value(hasItem(DEFAULT_KJCGZH.toString())))
            .andExpect(jsonPath("$.[*].jmlyqk").value(hasItem(DEFAULT_JMLYQK.toString())))
            .andExpect(jsonPath("$.[*].jscsd").value(hasItem(DEFAULT_JSCSD)))
            .andExpect(jsonPath("$.[*].jzmsylqk").value(hasItem(DEFAULT_JZMSYLQK)))
            .andExpect(jsonPath("$.[*].jzysjs").value(hasItem(DEFAULT_JZYSJS)))
            .andExpect(jsonPath("$.[*].fzrdh").value(hasItem(DEFAULT_FZRDH)))
            .andExpect(jsonPath("$.[*].xb").value(hasItem(DEFAULT_XB.toString())))
            .andExpect(jsonPath("$.[*].lxfs").value(hasItem(DEFAULT_LXFS)))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL)))
            .andExpect(jsonPath("$.[*].fzrnl").value(hasItem(DEFAULT_FZRNL.toString())))
            .andExpect(jsonPath("$.[*].tdpjnl").value(hasItem(DEFAULT_TDPJNL.toString())))
            .andExpect(jsonPath("$.[*].gjrcs").value(hasItem(DEFAULT_GJRCS)))
            .andExpect(jsonPath("$.[*].sfgjrzgxjsqy").value(hasItem(DEFAULT_SFGJRZGXJSQY.toString())))
            .andExpect(jsonPath("$.[*].tdysjs").value(hasItem(DEFAULT_TDYSJS)))
            .andExpect(jsonPath("$.[*].xycz").value(hasItem(DEFAULT_XYCZ)))
            .andExpect(jsonPath("$.[*].wlxwhdzclx").value(hasItem(DEFAULT_WLXWHDZCLX)))
            .andExpect(jsonPath("$.[*].wlxwhdzclx1").value(hasItem(DEFAULT_WLXWHDZCLX_1)))
            .andExpect(jsonPath("$.[*].sfxyxc").value(hasItem(DEFAULT_SFXYXC)))
            .andExpect(jsonPath("$.[*].rzjhgkfw").value(hasItem(DEFAULT_RZJHGKFW.toString())))
            .andExpect(jsonPath("$.[*].rzmb").value(hasItem(DEFAULT_RZMB.toString())))
            .andExpect(jsonPath("$.[*].lxrzw").value(hasItem(DEFAULT_LXRZW)))
            .andExpect(jsonPath("$.[*].lxdh").value(hasItem(DEFAULT_LXDH)))
            .andExpect(jsonPath("$.[*].lxyx").value(hasItem(DEFAULT_LXYX)))
            .andExpect(jsonPath("$.[*].lxdz").value(hasItem(DEFAULT_LXDZ)))
            .andExpect(jsonPath("$.[*].ssly1").value(hasItem(DEFAULT_SSLY_1)))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.intValue())))
            .andExpect(jsonPath("$.[*].modifiedDate").value(hasItem(DEFAULT_MODIFIED_DATE.intValue())));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Registration.class);
        Registration registration1 = new Registration();
        registration1.setId(1L);
        Registration registration2 = new Registration();
        registration2.setId(registration1.getId());
        assertThat(registration1).isEqualTo(registration2);
        registration2.setId(2L);
        assertThat(registration1).isNotEqualTo(registration2);
        registration1.setId(null);
        assertThat(registration1).isNotEqualTo(registration2);
    }
}
