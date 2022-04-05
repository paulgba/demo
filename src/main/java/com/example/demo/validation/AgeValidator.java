package com.example.demo.validation;

import java.math.BigDecimal;

public class AgeValidator {

    /**
     * 檢查年齡
     * @param age 年齡
     * @throws Exception Exception
     * @author User 2022年4月2日
     */
    public static void validate(BigDecimal age) throws Exception {
        int ageInt = age.intValueExact();
        if (ageInt < 0 || ageInt > 299) {
            throw new Exception("年齡異常");
        }
    }
}
