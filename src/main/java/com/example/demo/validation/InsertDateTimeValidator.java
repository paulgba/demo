package com.example.demo.validation;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;

public class InsertDateTimeValidator {

    /**
     * 檢查新增時間
     * @param name 新增時間
     * @throws Exception Exception
     * @author User 2022年4月2日
     */
    public static void validate(LocalDateTime insertDateTime) throws Exception {
        if (!Pattern.matches("\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}",
                insertDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))) {
            throw new Exception("新增時間異常");
        }
    }
}
