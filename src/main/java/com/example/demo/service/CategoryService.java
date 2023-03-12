// ## Service 생성
//
// Service는 하나의 기능에대한 실제 동작하는 로직(비즈니스 로직)이 들어가는 곳이다.
// Controller는 요청과 응답 해석과 검증에 집중하고, Service 는 동작에 집중해야한다.
//
// (만약 RESTful API가 아니라, Graphql, GRPC, MessageQueue등 의 다른 API가 추가되었을때
// Controller에 로직이 있으면 로직이 중복되기 때문에 Controller에서는 비즈니스 로직을 처리하지 않는다)
package com.example.demo.service;

import com.example.demo.controller.dto.CategoryDTO;
import com.example.demo.entity.Category;
import com.example.demo.repository.CategoryRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

// @Service 는 서비스 컴포넌트를 만들때 사용한다. Service에서는 @Transactional 을 사용할 수 있다.
@Service
// @AllArgsConstructor 모든 변수를 포함하는 생성자를 만들어 준다.
// Spring에서는 Bean 생성 시 생성자의 파라미터에 다른 Component가 필요하면 자동으로 주입해준다.
@AllArgsConstructor
public class CategoryService {
    // CategoryRepository 는 우리가 생성을 안하였지만 Spring에서 Bean을 검색하여 DI 를 통해 주입해준다.

    private final CategoryRepository categoryRepository;

    // @Transactional 은 Exception이 발생하면 데이터베이스를 롤백해주는 기능을 제공한다.
    @Transactional
    public Category createCategory(CategoryDTO categoryDTO){
        // Repository에서 데이터 가져오기
        Optional<Category> findOne = categoryRepository.findByName(categoryDTO.getName());
        if(findOne.isPresent()){ //데이터가 이미 존재하면 Exception을 발생시키고 종료
            // Repository에서 가져온 데이터가 존재하면  ResponseStatusException 를 리턴해주는데
            // 이는 Controller에서 HTTP 에러 응답을 하게 하는 Exception이다, HTTP code와 메세지를 적으면된다.
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "중복된 이름입니다.");
        }
        Category category = Category.builder().
                name(categoryDTO.getName()).
                build();
        category = categoryRepository.save(category); // 아니면 category 생성하기

        return category;
    }

    // getCateogoryById라는 메서드를 추가해보자.
    //
    //`orElseThrow` 는 optional 객체에서 제공해주는 메서드로
    //
    //데이터가 있다면 데이터를 가져오고
    //
    //없다면 Exception을 발생 시킨다.
    //
    //여기서 NOT_FOUND Exception을 발생시켰다.

    public Category getCategoryById(Long id){
        return categoryRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "카테고리가 존재하지 않습니다."));
    }


    public List<Category> getCategoryAll(){
        return categoryRepository.findAll();
    }
}
