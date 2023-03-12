package com.example.demo.repository;

import com.example.demo.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

// @Repository 는 데이터를 접근하는 객체를 만들때 생성한다.
@Repository
// class 가 아니라 interface 로 Repository를 생성했는데
// interface로 어떤 메서드를 사용할 지만 선언만 해주면 Spring JPA에서 데이터베이스 접근 코드를 생성하여 DI를 통해 주입해주기 때문이다.

// JpaRepository<Category, Long> 은 Jpa를 사용하겠다는 선언이다.
// 기본적으로 findById findAll save delete 등의 메서드를 제공한다. 왼쪽에는 다루는 데이터의 타입, 오른쪽에는 id의 타입을 적어준다.
public interface CategoryRepository extends JpaRepository<Category, Long> {
    // JPA는 findBy{프로퍼티이름}의 메서드를 만들면 자동으로 쿼리를 생성해 주는데
    // `Optional<Category> findByName(String name);` 은 파라미터 name과 같은 name을 가진 Category를 리턴해준다.
    // Optional Optional 은 Null과 Category를 가질 수 있는 객체이다.
    // Null Check를 위한 메서드들을 제공해 준다. isEmpty, elseOrThrow, isPresent 등
    Optional<Category> findByName(String name);

}
