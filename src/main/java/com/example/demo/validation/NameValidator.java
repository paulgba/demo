package com.example.demo.validation;

import java.util.regex.Pattern;

public class NameValidator {

    /**
     * 檢查姓名
     * @param name 姓名
     * @throws Exception Exception
     * @author User 2022年4月2日
     */
    public static void validate(String name) throws Exception {
        if (name == null || name.isBlank()) { // NOTE: isBlank() since java11, use StringUtils.isBlank() if your java version is lower than 11.
            throw new Exception("姓名異常");
        }
        if (Pattern.matches("[+-]?\\d+(\\.\\d+)?([Ee][+-]?\\d+)?", name)) {
            throw new Exception("姓名異常");
        }
    }
}
