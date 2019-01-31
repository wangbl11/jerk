package com.kongtiantou.jerk.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;
import java.util.Objects;

import com.kongtiantou.jerk.domain.enumeration.AuthStatusEnum;

/**
 * A Jerk.
 */
@Entity
@Table(name = "jerk")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "jerk")
public class Jerk extends HighLightEntity {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 100)
    @Column(name = "username", length = 100, nullable = false)
    private String username;

    @NotNull
    @Size(max = 60)
    @Column(name = "passwd", length = 60, nullable = false)
    private String passwd;

    @NotNull
    @Size(max = 100)
    @Column(name = "displayname", length = 100, nullable = false)
    private String displayname;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "auth_status", nullable = false)
    private AuthStatusEnum authStatus;

    @NotNull
    @Column(name = "created_date", nullable = false)
    private Long createdDate;

    @Column(name = "modified_date")
    private Long modifiedDate;

    @OneToOne(fetch = FetchType.EAGER,cascade =  CascadeType.ALL)
    @JoinColumn(unique = true)
    @Field(type = FieldType.Nested)
    private Registration jerkInfo;

    @OneToOne    @JoinColumn(unique = true)
    private Preference preference;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public Jerk username(String username) {
        this.username = username;
        return this;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswd() {
        return passwd;
    }

    public Jerk passwd(String passwd) {
        this.passwd = passwd;
        return this;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public String getDisplayname() {
        return displayname;
    }

    public Jerk displayname(String displayname) {
        this.displayname = displayname;
        return this;
    }

    public void setDisplayname(String displayname) {
        this.displayname = displayname;
    }

    public AuthStatusEnum getAuthStatus() {
        return authStatus;
    }

    public Jerk authStatus(AuthStatusEnum authStatus) {
        this.authStatus = authStatus;
        return this;
    }

    public void setAuthStatus(AuthStatusEnum authStatus) {
        this.authStatus = authStatus;
    }

    public Long getCreatedDate() {
        return createdDate;
    }

    public Jerk createdDate(Long createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public void setCreatedDate(Long createdDate) {
        this.createdDate = createdDate;
    }

    public Long getModifiedDate() {
        return modifiedDate;
    }

    public Jerk modifiedDate(Long modifiedDate) {
        this.modifiedDate = modifiedDate;
        return this;
    }

    public void setModifiedDate(Long modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public Registration getJerkInfo() {
        return jerkInfo;
    }

    public Jerk jerkInfo(Registration registration) {
        this.jerkInfo = registration;
        return this;
    }

    public void setJerkInfo(Registration registration) {
        this.jerkInfo = registration;
    }

    public Preference getPreference() {
        return preference;
    }

    public Jerk preference(Preference preference) {
        this.preference = preference;
        return this;
    }

    public void setPreference(Preference preference) {
        this.preference = preference;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Jerk jerk = (Jerk) o;
        if (jerk.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), jerk.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Jerk{" +
            "id=" + getId() +
            ", username='" + getUsername() + "'" +
            ", passwd='" + getPasswd() + "'" +
            ", displayname='" + getDisplayname() + "'" +
            ", authStatus='" + getAuthStatus() + "'" +
            ", createdDate=" + getCreatedDate() +
            ", modifiedDate=" + getModifiedDate() +
            "}";
    }
}
