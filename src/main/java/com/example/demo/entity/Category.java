package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

// @Entity 를 적용하면 JPA에서는 해당 정보를 통해 데이터베이스 테이블을 생성하고,
// 테이블의 데이터를 가져올 수 있게된다. 각 변수 하나하나가 데이터베이스 Colum이 된다.
@Entity
// @Getter getId, getName 같은 메서드를 자동으로 만들어주는 어노테이션이다.
// Entity에는 Get이 있어야 제대로 동작한다
@Getter
@ToString
public class Category {
    // @Id 는 PrimaryKey를 의미한다. id는 PrimaryKey가 된다.
    @Id
    // @GeneratedValue 는 데이터베이스에서 값을 자동 생성해주게 하는 설정이다.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    // @Column DB Column을 의미한다.
    // String이면 기본적을 255 사이즈의 Varchar를 이용하는데 length를 이용해 사이즈를 조절할 수 있다.
    // unique를 true로 설정하면 중복되는 데이터를 저장하지 못하게 된다.
    @Column(length = 100, unique = true)
    String name;

    // @Builder 는 Builder 패턴을 자동으로 만들어주는 생성자로 다음과 같이 이용할 수 있는 메서드들을 만들어준다.
    // Category category = Category.builder().name("카테고리이름").build();
    // 생성자 파라미터를 한번에 넣을 필요가 없고 순서를 외울 필요가 없다.
    @Builder
    public Category(String name){
        this.name = name;
    }

}
