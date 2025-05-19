package com.vic.lovelytrip.common.enums;

import lombok.Getter;

import java.util.regex.Pattern;

@Getter
public enum PatternEnum {

    EMAIL("^0-1"),
    // 🔹 Rules:
    //
    //Must be 1–100 characters
    //Only allow letters, numbers, spaces, dashes, commas, dots, etc.
    //No special symbols like <, >, $, or emojis
    //No leading/trailing spaces
    TITLE("^(?!\\s)([\\w\\d\\s\\-\\.,!?()&']{1,100})(?<!\\s)$"),

//    letters, digits, spaces
//    basic punctuation
//    line breaks (\n, \r)
//    up to 500 characters
    DESCRIPTION("^[\\w\\d\\s\\-\\.,!?()&'\"\\n\\r]{0,500}$");

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
