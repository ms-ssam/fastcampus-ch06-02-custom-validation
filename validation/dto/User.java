package com.example.validation.dto;

import com.example.validation.annotation.YearMonth;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class User {
    @NotBlank
    private String name;

    @Max(value = 90)
    private int age;

    @Email  // email 양식 검사 validation
    private String email;

    @JsonProperty("phone_number")
    @Pattern(regexp = "^\\d{2,3}-\\d{3,4}-\\d{4}$", message = "핸드폰 번호의 양식과 맞지 않습니다. xxx-xxx(x)-xxxx")  // 해당 정규식 아니면 X
    // valid annotation들에는 message 설정 가능. 이 경우 안해주면 "해당 정규식"이 아닙니다. 출력
    private String phoneNumber;

    // @Size(min = 6, max = 6)  // size는 무조건 6으로 들어와야 함
    @JsonProperty("req_year_month")
    @YearMonth  // default 패턴 외 설정 필요하면 (pattern = "...")
    private String reqYearMonth;  // yyyyMM 형식 (default)

    // 특정 클래스나 변수 검사하려면 @Valid 붙여야 함. controller에서 User 검사하기 위해 붙였듯이
    // User의 필드인 "cars (List의 원소인 Car) -> 객체" 를 검사해야 하니 @Valid 붙여줘야 검사가 동작함.
    @Valid
    private List<Car> cars;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getReqYearMonth() {
        return reqYearMonth;
    }

    public void setReqYearMonth(String reqYearMonth) {
        this.reqYearMonth = reqYearMonth;
    }

    // yearMonth의 @Size와 이 메서드로 yearMonth의 유효성 검사 but 이 메서드는 재사용성이 X
    // -> 동일한 기능의 custom annotation을 만들면 재사용 가능 (@YearMonth)
//    @AssertTrue(message = "yyyyMM의 형식에 맞지 않습니다.")
//    public boolean isReqYearMonthValidation() {  // @AssertTrue 메서드의 이름은 항상 is-로 시작
//        try {
//            // LocalDate의 기본 format은 yyyyMMdd이니 format 맞춰주기 위해 dd로 01 추가해서 yyyyMM 형식 검사
//            LocalDate localDate = LocalDate.parse(getReqYearMonth() + "01", DateTimeFormatter.ofPattern("yyyyMMdd"));
//        } catch (Exception e) {
//            return false;  // 형식 일치 X여서 예외 시 return false
//        }
//        return true;
//    }

    public List<Car> getCars() {
        return cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", reqYearMonth='" + reqYearMonth + '\'' +
                ", cars=" + cars +
                '}';
    }
}
