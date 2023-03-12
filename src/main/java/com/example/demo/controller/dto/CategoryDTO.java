// Data Transfer Object의 약자로 API로 데이터 전송시 사용하는 데이터와,
// Spring 프로젝트 내에서 Component끼리 데이터를 보낼때 사용하는 데이터를 의미
// 사용자의 회원가입 요청을 받고 리턴해주기 위해 생성
package com.example.demo.controller.dto;

import lombok.Data;

// - lombok라이브러리에서 제공하는 어노테이션
// - 모든 Property에 대해 `Getter` 와 `Setter` , `toString` , `hash`  를 생성해준다.
//    - getId, getName, setId, setName
@Data
public class CategoryDTO {
    Long id;
    String name;
}
