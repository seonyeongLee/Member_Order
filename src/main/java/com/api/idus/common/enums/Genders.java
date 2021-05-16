package com.api.idus.common.enums;

public enum Genders {
    MAN("0"),
    WOMEN("1");

    private String code;

    Genders(String code) {
        this.code = code;
    }

    public String getCode() {
        return this.code;
    }

    public static boolean isCorrectValue(String code) {
        for (Genders gender : Genders.values()) {
            if (gender.getCode().equals(code)) {
                return true;
            }
        }

        return false;
    }




}
