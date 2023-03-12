// 사용자로 부터 받은 데이터를 그대로 리턴해주는 API
package com.example.demo.controller;

import com.example.demo.controller.dto.CategoryDTO;
import com.example.demo.entity.Category;
import com.example.demo.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// @RestController
// - Restful API를 만들때 사용하는 Controller 어노테이션
//    - 모든 데이터는 요청과 응답의 body는 json으로 전송하도록 한다.
//    - 별다른 어노테이션 없이 메서드에서 객체를 응답하면 json으로 응답을 준다.
@RestController
// @RequestMapping("/categories")
// - `/categories` 로 요청할때 해당 Controller 밑에 있는 메서드들을 실행하게 한다.
// - 해당 Controller 아래에 있는 API들은 기본적으로 `/categories` 하위에 매핑된다.
@RequestMapping("/categories")
@AllArgsConstructor
public class UserController {
    private final CategoryService categoryService;
    // @Postmapping("")
    // - POST `/categories` 로 요청하면 요청을 받는다. (RequestMapping의 URL을 상속)
    // - HTTP의 메서드는 GET, POST, PUT, PATCH, DELETE 등이 있는데 RestAPI에서는
    // POST는 create, GET은 조회, PUT PATCH는 수정, DELETE는 삭제를 의미하게 사용한다.
    //    - POST `/categories` 새로운 유저를 생성해라
    @PostMapping("")
    // @RequestBody
    // - Controller의 메서드(API 핸들러) 에서는 HTTP 요청 전문이나, 전문의 일부분을 받을 수 있다.
    // - `@RequestBody` 는 HTTP 요청의 body부분을 가져온다.
    // - Body의 내용을 UserDTO에 담아서 처리해준다.
//    public CategoryDTO createCategory(@RequestBody CategoryDTO categoryDTO){
//        // return 데이터는 자동으로 json으로 변해서 응답한다.
//        return categoryDTO;
//    }
    public Category createCategory(@RequestBody CategoryDTO categoryDTO){
        return categoryService.createCategory(categoryDTO);
    }

    // id처럼 URL에 `{}` 로 감싸져 있는것을 PathVariable이라 부르고 Controller 에서
    // `@PathVariable` 을 통해 가져올 수 있다.
    //
    // 보낼때 `/categories/1`  요런식으로 보내면 id로 1이 올 것이다.
    @GetMapping("/{id}")
    public Category getCategoryById(@PathVariable Long id){
        return categoryService.getCategoryById(id);
    }

    @GetMapping("")
    public List<Category> getCategoryAll(){
        return categoryService.getCategoryAll();
    }

    @DeleteMapping("/{id}")
    public void deleteCategoryById(@PathVariable Long id) {
        categoryService.deleteCategory(id);
    }
}
// POST http://localhost:8080/categories 로 메세지를 보내 확인해보자.
// 데이터는 body에 raw application/json 으로 보낸다.:
// {
//	"name": "newCategory"
// }