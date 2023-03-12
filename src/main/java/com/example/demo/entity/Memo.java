package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.ToString;

@Entity
@Getter
@ToString
public class Memo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    // @ManyToOne 과 @JoinColumn 은 외래키 설정이다.
    // 실제 데이터는 category_id로 Category의 ID가 저장되지만 객체로 가져올 수 있게 해준다.
    // fetch = FetchType.EAGER 는 언제 데이터를 가져오냐에 대한 설정으로
    // 만약 Memo를 가져올 때 마다 Category의 데이터가 필요하다면 EAGER로 설정해주는게 좋고
    // 항상 필요하지 않으면 LAZY로 설정하는게 좋다.
    // Eager는 첫 로딩시 같이 가져오고 LAZY는 나중에 getCategory를 사용하면 가져온다.
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id")
    Category category;

    @Column(lengh = 100)
    String name;

    // columnDefinition 은 타입을 지정해 줄 떄 사용한다. TEXT 는 사이즈 제한이 없는 텍스트를 저장할떄 사용된다.
    @Column(columnDefinition = "TEXT")
    String content;
}
