package com.example.validation.validator;

import com.example.validation.annotation.YearMonth;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class YearMonthValidator implements ConstraintValidator<YearMonth, String> {
    // param에 String -> YearMonth 애너테이션에서 검사하는 게 String이어서?
    private String pattern;

    @Override
    public void initialize(YearMonth constraintAnnotation) {
        // 초기화 할 때 애너테이션에서 지정한 패턴 가져옴
        this.pattern = constraintAnnotation.pattern();
    }

    @Override  //
    public boolean isValid(String value, ConstraintValidatorContext context) {
        try {
            // LocalDate의 기본 format은 yyyyMMdd이니 format 맞춰주기 위해 dd로 01 추가해서 yyyyMM 형식 검사
            LocalDate localDate = LocalDate.parse(value + "01", DateTimeFormatter.ofPattern(this.pattern));
        } catch (Exception e) {
            return false;  // 형식 일치 X여서 예외 시 return false
        }
        return true;
    }
}
