package com.example.validation.annotation;

import com.example.validation.validator.YearMonthValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Constraint(validatedBy = {YearMonthValidator.class})  // 어떤 클래스로 검사할 것인지 명시
@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE })
@Retention(RUNTIME)
public @interface YearMonth {
    String message() default "yyyyMM 형식에 맞지 않습니다.";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

    // 여기까지는 다른 validation 애너테이션에서 복사해서 가져옴 (세부 설정 제외)
    String pattern() default "yyyyMMdd";
}
