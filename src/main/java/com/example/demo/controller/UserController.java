// 사용자로 부터 받은 데이터를 그대로 리턴해주는 API
package com.example.demo.controller;

import com.example.demo.controller.dto.CategoryDTO;
import com.example.demo.entity.Category;
import com.example.demo.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

//    @GetMapping("")
//    public List<Category> getCategoryAll(){
//        return categoryService.getCategoryAll();
//    }

    @DeleteMapping("/{id}")
    public Long deleteCategoryById(@PathVariable Long id) {
        return categoryService.deleteCategoryById(id);
    }

    //데이터를 하나씩 가져오기도 하지만 우리는 Data를 여러개를 가져오기도 한다 이때 사용하는 것이 페이징이다.
    //
    //`/categories?keyword={검색어}&page=0&size=10&orderBy=name` 를 통해,
    // Keyword가 넘어온다면 검색어가 있는 데이터만 가져오고, 아니면 전체를 가져와서 페이지로 넘겨주자.

    // `Pageable` 은 페이징 처리를 위한 파라미터이다. JPA에서 DB를 검색할때 사용한다.
    //
    //요청에서 `page=0&size=10&orderBy=name` 형식으로 데이터가 들어오면
    // name으로 데이터를 정렬하고 데이터를 10개씩 가져왔을때 첫번째 페이지에 대한 데이터를 가져오라는 뜻이다.
    // 이 데이터가 Pageable 객체로 바뀌어서 Controller에 들어온다.
    // 이 데이터는 Repository까지 전달해 주어야한다.
    //
    //`@RequestParam` 은 ?keyword={검색어} 처럼 URL에 추가적으로 붙는 것을 의미한다. `@PathVariable` 과 더불어 파라미터를 넘겨줄때 많이 이용한다. (Get 요청에는 Body가 없기 떄문에 Body대신 많이 쓴다)
    //
    //`Page` 는 페이지 정보가 포함된 데이터이다. JPA에서 생성한다.
    @GetMapping("")
    public Page<Category> getCategories(Pageable pageable, @RequestParam(value="keyword", required=false) String keyword){
        return categoryService.getCategories(pageable, keyword);
    }

    @PutMapping("")
    public Category modifyCategories(@RequestBody CategoryDTO categoryDTO) {
        return categoryService.modifyCategories(categoryDTO);
    }
}
// POST http://localhost:8080/categories 로 메세지를 보내 확인해보자.
// 데이터는 body에 raw application/json 으로 보낸다.:
// {
//	"name": "newCategory"
// }

// http://localhost:8080/categories?keyword=Cate&page=0&size=10&orderBy=name
//{
//    "content": [
//        {
//            "id": 1,
//            "name": "newCategory1"
//        },
//        {
//            "id": 2,
//            "name": "newCategory2"
//        },
//        {
//            "id": 3,
//            "name": "newCategory3"
//        },
//        {
//            "id": 4,
//            "name": "newCategory4"
//        }
//    ],
//    "pageable": {
//        "sort": {
//            "empty": true,
//            "sorted": false,
//            "unsorted": true
//        },
//        "offset": 0,
//        "pageSize": 10,
//        "pageNumber": 0,
//        "paged": true,
//        "unpaged": false
//    },
//    "totalPages": 1,
//    "totalElements": 4,
//    "last": true,
//    "size": 10,
//    "number": 0,
//    "sort": {
//        "empty": true,
//        "sorted": false,
//        "unsorted": true
//    },
//    "numberOfElements": 4,
//    "first": true,
//    "empty": false
//}