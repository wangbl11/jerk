package com.kongtiantou.jerk.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import org.springframework.data.elasticsearch.annotations.Document;
import java.io.Serializable;
import java.util.Objects;

/**
 * A Tag.
 */
@Entity
@Table(name = "tag")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "tag")
public class Tag extends HighLightEntity {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 24)
    @Column(name = "name", length = 24, nullable = false)
    private String name;

    @NotNull
    @Column(name = "jhi_type", nullable = false)
    private Integer type;

    @NotNull
    @Column(name = "status", nullable = false)
    private Integer status;

    @NotNull
    @Column(name = "weight", nullable = false)
    private Integer weight;

    @Column(name = "created_date")
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

    public Tag name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getType() {
        return type;
    }

    public Tag type(Integer type) {
        this.type = type;
        return this;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getStatus() {
        return status;
    }

    public Tag status(Integer status) {
        this.status = status;
        return this;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getWeight() {
        return weight;
    }

    public Tag weight(Integer weight) {
        this.weight = weight;
        return this;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Long getCreatedDate() {
        return createdDate;
    }

    public Tag createdDate(Long createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public void setCreatedDate(Long createdDate) {
        this.createdDate = createdDate;
    }

    public Long getModifiedDate() {
        return modifiedDate;
    }

    public Tag modifiedDate(Long modifiedDate) {
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
        Tag tag = (Tag) o;
        if (tag.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), tag.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Tag{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", type=" + getType() +
            ", status=" + getStatus() +
            ", weight=" + getWeight() +
            ", createdDate=" + getCreatedDate() +
            ", modifiedDate=" + getModifiedDate() +
            "}";
    }
}
