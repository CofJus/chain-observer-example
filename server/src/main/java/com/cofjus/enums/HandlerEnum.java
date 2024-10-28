package com.cofjus.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author cofjus
 * @date 10/28/24
 */
@Getter
@AllArgsConstructor
public enum HandlerEnum {

    BLACKLIST("blacklist"),

    FREQUENCY("frequency"),

    ;

    private final String name;
}
