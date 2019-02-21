package com.kongtiantou.jerk.domain.enumeration;

import com.fasterxml.jackson.annotation.JsonValue;

public enum RegistrationTypeEnum {

    CHUANGXIN(0), CHUANGYE(1);

    private final Integer id;

    private RegistrationTypeEnum(Integer id) {
        this.id = id;
    }

    @JsonValue
    public Integer getId() {
        return id;
    }

}