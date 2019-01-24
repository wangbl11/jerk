package com.kongtiantou.jerk.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import org.springframework.data.elasticsearch.annotations.Document;
import java.io.Serializable;
import java.util.Objects;

import com.kongtiantou.jerk.domain.enumeration.SettingTypeEnum;

/**
 * A Setting.
 */
@Entity
@Table(name = "setting")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "setting")
public class Setting extends HighLightEntity {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 100)
    @Column(name = "name", length = 100, nullable = false)
    private String name;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "jhi_type", nullable = false)
    private SettingTypeEnum type;

    @NotNull
    @Size(max = 200)
    @Column(name = "jhi_value", length = 200, nullable = false)
    private String value;

    @NotNull
    @Size(max = 200)
    @Column(name = "defvalue", length = 200, nullable = false)
    private String defvalue;

    @NotNull
    @Column(name = "created_date", nullable = false)
    private Long createdDate;

    @Column(name = "modified_date")
    private Long modifiedDate;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Setting name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SettingTypeEnum getType() {
        return type;
    }

    public Setting type(SettingTypeEnum type) {
        this.type = type;
        return this;
    }

    public void setType(SettingTypeEnum type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public Setting value(String value) {
        this.value = value;
        return this;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDefvalue() {
        return defvalue;
    }

    public Setting defvalue(String defvalue) {
        this.defvalue = defvalue;
        return this;
    }

    public void setDefvalue(String defvalue) {
        this.defvalue = defvalue;
    }

    public Long getCreatedDate() {
        return createdDate;
    }

    public Setting createdDate(Long createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public void setCreatedDate(Long createdDate) {
        this.createdDate = createdDate;
    }

    public Long getModifiedDate() {
        return modifiedDate;
    }

    public Setting modifiedDate(Long modifiedDate) {
        this.modifiedDate = modifiedDate;
        return this;
    }

    public void setModifiedDate(Long modifiedDate) {
        this.modifiedDate = modifiedDate;
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
        Setting setting = (Setting) o;
        if (setting.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), setting.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Setting{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", type='" + getType() + "'" +
            ", value='" + getValue() + "'" +
            ", defvalue='" + getDefvalue() + "'" +
            ", createdDate=" + getCreatedDate() +
            ", modifiedDate=" + getModifiedDate() +
            "}";
    }
}
