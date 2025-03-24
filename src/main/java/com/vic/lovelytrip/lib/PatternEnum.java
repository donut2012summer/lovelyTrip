package com.vic.lovelytrip.lib;

import lombok.Getter;

import java.util.regex.Pattern;

@Getter
public enum PatternEnum {

    EMAIL("^0-1");

    private String regex;
    private Pattern pattern;

    PatternEnum(String regex) {
        this.regex = regex;
        this.pattern = Pattern.compile(regex);
    }

    public boolean matches(String input) {
        return pattern.matcher(input).matches();
    }

}
