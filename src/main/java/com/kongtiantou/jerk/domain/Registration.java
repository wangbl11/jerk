package com.kongtiantou.jerk.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import org.springframework.data.elasticsearch.annotations.Document;
import java.io.Serializable;
import java.util.Objects;

import com.kongtiantou.jerk.domain.enumeration.Decision;

import com.kongtiantou.jerk.domain.enumeration.HxjslyEnum;

import com.kongtiantou.jerk.domain.enumeration.KjcgzhEnum;

import com.kongtiantou.jerk.domain.enumeration.JmlyqkEnum;

import com.kongtiantou.jerk.domain.enumeration.XbEnum;

import com.kongtiantou.jerk.domain.enumeration.AgeEnum;

import com.kongtiantou.jerk.domain.enumeration.RzjhgkfwEnum;

import com.kongtiantou.jerk.domain.enumeration.RzmbEnum;

/**
 * A Registration.
 */
@Entity
@Table(name = "registration")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "registration")
public class Registration implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "regist_type", nullable = false)
    private Integer registType;

    @NotNull
    @Size(max = 60)
    @Column(name = "dwqc", length = 60, nullable = false)
    private String dwqc;

    @NotNull
    @Size(max = 120)
    @Column(name = "hxcpmc", length = 120, nullable = false)
    private String hxcpmc;

    @Column(name = "zztjdw")
    private String zztjdw;

    @NotNull
    @Size(max = 100)
    @Column(name = "dwhgrdz", length = 100, nullable = false)
    private String dwhgrdz;

    @NotNull
    @Column(name = "szqylx", nullable = false)
    private String szqylx;

    @NotNull
    @Column(name = "ssly", nullable = false)
    private String ssly;

    @NotNull
    @Size(max = 360)
    @Column(name = "gscpjj", length = 360, nullable = false)
    private String gscpjj;

    @NotNull
    @Column(name = "mbkhsc", nullable = false)
    private String mbkhsc;

    @Column(name = "dqzykh")
    private String dqzykh;

    @Size(max = 500)
    @Column(name = "gnwhjjx", length = 500)
    private String gnwhjjx;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "zljs", nullable = false)
    private Decision zljs;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "hxjsly", nullable = false)
    private HxjslyEnum hxjsly;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "kjcgzh", nullable = false)
    private KjcgzhEnum kjcgzh;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "jmlyqk", nullable = false)
    private JmlyqkEnum jmlyqk;

    @Size(max = 120)
    @Column(name = "jscsd", length = 120)
    private String jscsd;

    @Size(max = 500)
    @Column(name = "jzmsylqk", length = 500)
    private String jzmsylqk;

    @NotNull
    @Size(max = 500)
    @Column(name = "jzysjs", length = 500, nullable = false)
    private String jzysjs;

    @NotNull
    @Size(max = 20)
    @Column(name = "fzrdh", length = 20, nullable = false)
    private String fzrdh;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "xb", nullable = false)
    private XbEnum xb;

    @NotNull
    @Size(max = 36)
    @Column(name = "lxfs", length = 36, nullable = false)
    private String lxfs;

    @NotNull
    @Size(max = 36)
    @Column(name = "email", length = 36, nullable = false)
    private String email;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "fzrnl", nullable = false)
    private AgeEnum fzrnl;

    @Enumerated(EnumType.STRING)
    @Column(name = "tdpjnl")
    private AgeEnum tdpjnl;

    @NotNull
    @Column(name = "gjrcs", nullable = false)
    private Integer gjrcs;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "sfgjrzgxjsqy", nullable = false)
    private Decision sfgjrzgxjsqy;

    @NotNull
    @Size(max = 500)
    @Column(name = "tdysjs", length = 500, nullable = false)
    private String tdysjs;

    @NotNull
    @Size(max = 24)
    @Column(name = "xycz", length = 24, nullable = false)
    private String xycz;

    @NotNull
    @Size(max = 36)
    @Column(name = "wlxwhdzclx", length = 36, nullable = false)
    private String wlxwhdzclx;

    @Size(max = 100)
    @Column(name = "wlxwhdzclx_1", length = 100)
    private String wlxwhdzclx1;

    @Size(max = 100)
    @Column(name = "sfxyxc", length = 100)
    private String sfxyxc;

    @Enumerated(EnumType.STRING)
    @Column(name = "rzjhgkfw")
    private RzjhgkfwEnum rzjhgkfw;

    @Enumerated(EnumType.STRING)
    @Column(name = "rzmb")
    private RzmbEnum rzmb;

    @NotNull
    @Size(max = 100)
    @Column(name = "lxrzw", length = 100, nullable = false)
    private String lxrzw;

    @NotNull
    @Column(name = "lxdh", nullable = false)
    private String lxdh;

    @NotNull
    @Size(max = 60)
    @Column(name = "lxyx", length = 60, nullable = false)
    private String lxyx;

    @NotNull
    @Size(max = 120)
    @Column(name = "lxdz", length = 120, nullable = false)
    private String lxdz;

    @Size(max = 60)
    @Column(name = "ssly_1", length = 60)
    private String ssly1;

    @NotNull
    @Column(name = "created_date")
    private Long createdDate;

    @Column(name = "modified_date")
    private Long modifiedDate;

    @OneToOne(fetch = FetchType.LAZY,mappedBy = "jerkInfo")
    private Jerk jerk;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getRegistType() {
        return registType;
    }

    public Registration registType(Integer registType) {
        this.registType = registType;
        return this;
    }

    public void setRegistType(Integer registType) {
        this.registType = registType;
    }

    public String getDwqc() {
        return dwqc;
    }

    public Registration dwqc(String dwqc) {
        this.dwqc = dwqc;
        return this;
    }

    public void setDwqc(String dwqc) {
        this.dwqc = dwqc;
    }

    public String getHxcpmc() {
        return hxcpmc;
    }

    public Registration hxcpmc(String hxcpmc) {
        this.hxcpmc = hxcpmc;
        return this;
    }

    public void setHxcpmc(String hxcpmc) {
        this.hxcpmc = hxcpmc;
    }

    public String getZztjdw() {
        return zztjdw;
    }

    public Registration zztjdw(String zztjdw) {
        this.zztjdw = zztjdw;
        return this;
    }

    public void setZztjdw(String zztjdw) {
        this.zztjdw = zztjdw;
    }

    public String getDwhgrdz() {
        return dwhgrdz;
    }

    public Registration dwhgrdz(String dwhgrdz) {
        this.dwhgrdz = dwhgrdz;
        return this;
    }

    public void setDwhgrdz(String dwhgrdz) {
        this.dwhgrdz = dwhgrdz;
    }

    public String getSzqylx() {
        return szqylx;
    }

    public Registration szqylx(String szqylx) {
        this.szqylx = szqylx;
        return this;
    }

    public void setSzqylx(String szqylx) {
        this.szqylx = szqylx;
    }

    public String getSsly() {
        return ssly;
    }

    public Registration ssly(String ssly) {
        this.ssly = ssly;
        return this;
    }

    public void setSsly(String ssly) {
        this.ssly = ssly;
    }

    public String getGscpjj() {
        return gscpjj;
    }

    public Registration gscpjj(String gscpjj) {
        this.gscpjj = gscpjj;
        return this;
    }

    public void setGscpjj(String gscpjj) {
        this.gscpjj = gscpjj;
    }

    public String getMbkhsc() {
        return mbkhsc;
    }

    public Registration mbkhsc(String mbkhsc) {
        this.mbkhsc = mbkhsc;
        return this;
    }

    public void setMbkhsc(String mbkhsc) {
        this.mbkhsc = mbkhsc;
    }

    public String getDqzykh() {
        return dqzykh;
    }

    public Registration dqzykh(String dqzykh) {
        this.dqzykh = dqzykh;
        return this;
    }

    public void setDqzykh(String dqzykh) {
        this.dqzykh = dqzykh;
    }

    public String getGnwhjjx() {
        return gnwhjjx;
    }

    public Registration gnwhjjx(String gnwhjjx) {
        this.gnwhjjx = gnwhjjx;
        return this;
    }

    public void setGnwhjjx(String gnwhjjx) {
        this.gnwhjjx = gnwhjjx;
    }

    public Decision getZljs() {
        return zljs;
    }

    public Registration zljs(Decision zljs) {
        this.zljs = zljs;
        return this;
    }

    public void setZljs(Decision zljs) {
        this.zljs = zljs;
    }

    public HxjslyEnum getHxjsly() {
        return hxjsly;
    }

    public Registration hxjsly(HxjslyEnum hxjsly) {
        this.hxjsly = hxjsly;
        return this;
    }

    public void setHxjsly(HxjslyEnum hxjsly) {
        this.hxjsly = hxjsly;
    }

    public KjcgzhEnum getKjcgzh() {
        return kjcgzh;
    }

    public Registration kjcgzh(KjcgzhEnum kjcgzh) {
        this.kjcgzh = kjcgzh;
        return this;
    }

    public void setKjcgzh(KjcgzhEnum kjcgzh) {
        this.kjcgzh = kjcgzh;
    }

    public JmlyqkEnum getJmlyqk() {
        return jmlyqk;
    }

    public Registration jmlyqk(JmlyqkEnum jmlyqk) {
        this.jmlyqk = jmlyqk;
        return this;
    }

    public void setJmlyqk(JmlyqkEnum jmlyqk) {
        this.jmlyqk = jmlyqk;
    }

    public String getJscsd() {
        return jscsd;
    }

    public Registration jscsd(String jscsd) {
        this.jscsd = jscsd;
        return this;
    }

    public void setJscsd(String jscsd) {
        this.jscsd = jscsd;
    }

    public String getJzmsylqk() {
        return jzmsylqk;
    }

    public Registration jzmsylqk(String jzmsylqk) {
        this.jzmsylqk = jzmsylqk;
        return this;
    }

    public void setJzmsylqk(String jzmsylqk) {
        this.jzmsylqk = jzmsylqk;
    }

    public String getJzysjs() {
        return jzysjs;
    }

    public Registration jzysjs(String jzysjs) {
        this.jzysjs = jzysjs;
        return this;
    }

    public void setJzysjs(String jzysjs) {
        this.jzysjs = jzysjs;
    }

    public String getFzrdh() {
        return fzrdh;
    }

    public Registration fzrdh(String fzrdh) {
        this.fzrdh = fzrdh;
        return this;
    }

    public void setFzrdh(String fzrdh) {
        this.fzrdh = fzrdh;
    }

    public XbEnum getXb() {
        return xb;
    }

    public Registration xb(XbEnum xb) {
        this.xb = xb;
        return this;
    }

    public void setXb(XbEnum xb) {
        this.xb = xb;
    }

    public String getLxfs() {
        return lxfs;
    }

    public Registration lxfs(String lxfs) {
        this.lxfs = lxfs;
        return this;
    }

    public void setLxfs(String lxfs) {
        this.lxfs = lxfs;
    }

    public String getEmail() {
        return email;
    }

    public Registration email(String email) {
        this.email = email;
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public AgeEnum getFzrnl() {
        return fzrnl;
    }

    public Registration fzrnl(AgeEnum fzrnl) {
        this.fzrnl = fzrnl;
        return this;
    }

    public void setFzrnl(AgeEnum fzrnl) {
        this.fzrnl = fzrnl;
    }

    public AgeEnum getTdpjnl() {
        return tdpjnl;
    }

    public Registration tdpjnl(AgeEnum tdpjnl) {
        this.tdpjnl = tdpjnl;
        return this;
    }

    public void setTdpjnl(AgeEnum tdpjnl) {
        this.tdpjnl = tdpjnl;
    }

    public Integer getGjrcs() {
        return gjrcs;
    }

    public Registration gjrcs(Integer gjrcs) {
        this.gjrcs = gjrcs;
        return this;
    }

    public void setGjrcs(Integer gjrcs) {
        this.gjrcs = gjrcs;
    }

    public Decision getSfgjrzgxjsqy() {
        return sfgjrzgxjsqy;
    }

    public Registration sfgjrzgxjsqy(Decision sfgjrzgxjsqy) {
        this.sfgjrzgxjsqy = sfgjrzgxjsqy;
        return this;
    }

    public void setSfgjrzgxjsqy(Decision sfgjrzgxjsqy) {
        this.sfgjrzgxjsqy = sfgjrzgxjsqy;
    }

    public String getTdysjs() {
        return tdysjs;
    }

    public Registration tdysjs(String tdysjs) {
        this.tdysjs = tdysjs;
        return this;
    }

    public void setTdysjs(String tdysjs) {
        this.tdysjs = tdysjs;
    }

    public String getXycz() {
        return xycz;
    }

    public Registration xycz(String xycz) {
        this.xycz = xycz;
        return this;
    }

    public void setXycz(String xycz) {
        this.xycz = xycz;
    }

    public String getWlxwhdzclx() {
        return wlxwhdzclx;
    }

    public Registration wlxwhdzclx(String wlxwhdzclx) {
        this.wlxwhdzclx = wlxwhdzclx;
        return this;
    }

    public void setWlxwhdzclx(String wlxwhdzclx) {
        this.wlxwhdzclx = wlxwhdzclx;
    }

    public String getWlxwhdzclx1() {
        return wlxwhdzclx1;
    }

    public Registration wlxwhdzclx1(String wlxwhdzclx1) {
        this.wlxwhdzclx1 = wlxwhdzclx1;
        return this;
    }

    public void setWlxwhdzclx1(String wlxwhdzclx1) {
        this.wlxwhdzclx1 = wlxwhdzclx1;
    }

    public String getSfxyxc() {
        return sfxyxc;
    }

    public Registration sfxyxc(String sfxyxc) {
        this.sfxyxc = sfxyxc;
        return this;
    }

    public void setSfxyxc(String sfxyxc) {
        this.sfxyxc = sfxyxc;
    }

    public RzjhgkfwEnum getRzjhgkfw() {
        return rzjhgkfw;
    }

    public Registration rzjhgkfw(RzjhgkfwEnum rzjhgkfw) {
        this.rzjhgkfw = rzjhgkfw;
        return this;
    }

    public void setRzjhgkfw(RzjhgkfwEnum rzjhgkfw) {
        this.rzjhgkfw = rzjhgkfw;
    }

    public RzmbEnum getRzmb() {
        return rzmb;
    }

    public Registration rzmb(RzmbEnum rzmb) {
        this.rzmb = rzmb;
        return this;
    }

    public void setRzmb(RzmbEnum rzmb) {
        this.rzmb = rzmb;
    }

    public String getLxrzw() {
        return lxrzw;
    }

    public Registration lxrzw(String lxrzw) {
        this.lxrzw = lxrzw;
        return this;
    }

    public void setLxrzw(String lxrzw) {
        this.lxrzw = lxrzw;
    }

    public String getLxdh() {
        return lxdh;
    }

    public Registration lxdh(String lxdh) {
        this.lxdh = lxdh;
        return this;
    }

    public void setLxdh(String lxdh) {
        this.lxdh = lxdh;
    }

    public String getLxyx() {
        return lxyx;
    }

    public Registration lxyx(String lxyx) {
        this.lxyx = lxyx;
        return this;
    }

    public void setLxyx(String lxyx) {
        this.lxyx = lxyx;
    }

    public String getLxdz() {
        return lxdz;
    }

    public Registration lxdz(String lxdz) {
        this.lxdz = lxdz;
        return this;
    }

    public void setLxdz(String lxdz) {
        this.lxdz = lxdz;
    }

    public String getSsly1() {
        return ssly1;
    }

    public Registration ssly1(String ssly1) {
        this.ssly1 = ssly1;
        return this;
    }

    public void setSsly1(String ssly1) {
        this.ssly1 = ssly1;
    }

    public Long getCreatedDate() {
        return createdDate;
    }

    public Registration createdDate(Long createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public void setCreatedDate(Long createdDate) {
        this.createdDate = createdDate;
    }

    public Long getModifiedDate() {
        return modifiedDate;
    }

    public Registration modifiedDate(Long modifiedDate) {
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
        Registration registration = (Registration) o;
        if (registration.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), registration.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Registration{" +
            "id=" + getId() +
            ", registType=" + getRegistType() +
            ", dwqc='" + getDwqc() + "'" +
            ", hxcpmc='" + getHxcpmc() + "'" +
            ", zztjdw='" + getZztjdw() + "'" +
            ", dwhgrdz='" + getDwhgrdz() + "'" +
            ", szqylx='" + getSzqylx() + "'" +
            ", ssly='" + getSsly() + "'" +
            ", gscpjj='" + getGscpjj() + "'" +
            ", mbkhsc='" + getMbkhsc() + "'" +
            ", dqzykh='" + getDqzykh() + "'" +
            ", gnwhjjx='" + getGnwhjjx() + "'" +
            ", zljs='" + getZljs() + "'" +
            ", hxjsly='" + getHxjsly() + "'" +
            ", kjcgzh='" + getKjcgzh() + "'" +
            ", jmlyqk='" + getJmlyqk() + "'" +
            ", jscsd='" + getJscsd() + "'" +
            ", jzmsylqk='" + getJzmsylqk() + "'" +
            ", jzysjs='" + getJzysjs() + "'" +
            ", fzrdh='" + getFzrdh() + "'" +
            ", xb='" + getXb() + "'" +
            ", lxfs='" + getLxfs() + "'" +
            ", email='" + getEmail() + "'" +
            ", fzrnl='" + getFzrnl() + "'" +
            ", tdpjnl='" + getTdpjnl() + "'" +
            ", gjrcs=" + getGjrcs() +
            ", sfgjrzgxjsqy='" + getSfgjrzgxjsqy() + "'" +
            ", tdysjs='" + getTdysjs() + "'" +
            ", xycz='" + getXycz() + "'" +
            ", wlxwhdzclx='" + getWlxwhdzclx() + "'" +
            ", wlxwhdzclx1='" + getWlxwhdzclx1() + "'" +
            ", sfxyxc='" + getSfxyxc() + "'" +
            ", rzjhgkfw='" + getRzjhgkfw() + "'" +
            ", rzmb='" + getRzmb() + "'" +
            ", lxrzw='" + getLxrzw() + "'" +
            ", lxdh='" + getLxdh() + "'" +
            ", lxyx='" + getLxyx() + "'" +
            ", lxdz='" + getLxdz() + "'" +
            ", ssly1='" + getSsly1() + "'" +
            ", createdDate=" + getCreatedDate() +
            ", modifiedDate=" + getModifiedDate() +
            "}";
    }
}
