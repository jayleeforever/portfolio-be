package com.example.demo.controller;

import com.example.demo.dto.MemoDTO;
import com.example.demo.entity.Memo;
import com.example.demo.service.MemoService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/memos")
@AllArgsConstructor
public class MemoController {
    private MemoService memoService;

    @GetMapping("")
    public Page<Memo> getMemos(@RequestParam(value="keyword", required=false) String keyword, Pageable pageable) {
        return memoService.getMemos(pageable, keyword);
    }

    @GetMapping("/{id}")
    public Memo getMemo(@PathVariable Long id) {
        return memoService.getMemo(id);
    }

    @PostMapping("")
    public Memo createMemo(@RequestBody MemoDTO memoDTO){
        return memoService.createMemo(memoDTO);
    }

    @PutMapping("")
    public Memo modifyMemo(@RequestBody MemoDTO memoDTO){
        return memoService.modifyMemo(memoDTO);
    }

    @DeleteMapping("/{id}")
    public String deleteMemo(@PathVariable Long id){
        memoService.deleteMemo(id);
        return "success";
    }
}

//POST http://localhost:8080/categories
//{
//    "id": 1,
//    "name": "newCategory1"
//}

//Body
//{
//    "id": 1,
//    "name": "newCategory1"
//}

//POST http://localhost:8080/memos
//{
//	"name": "memo6",
//    "content": "content1",
//    "category_id": 1
//}

//http://localhost:8080/memos?page=0&size=5&orderBy=name&keyword=
//Body
//{
//    "id": 3,
//    "category": {
//        "id": 1,
//        "name": "newCategory1"
//    },
//    "name": "memo6",
//    "content": "content1"
//}

//{
//    "content": [
//        {
//            "id": 1,
//            "category": {
//                "id": 1,
//                "name": "modCategory1"
//            },
//            "name": "memo1",
//            "content": "content1"
//        },
//        {
//            "id": 2,
//            "category": null,
//            "name": "memo2",
//            "content": "content2"
//        },
//        {
//            "id": 3,
//            "category": {
//                "id": 3,
//                "name": "newCategory3"
//            },
//            "name": "memo3",
//            "content": "content3"
//        }
//    ],
//    "pageable": {
//        "sort": {
//            "empty": true,
//            "sorted": false,
//            "unsorted": true
//        },
//        "offset": 0,
//        "pageSize": 5,
//        "pageNumber": 0,
//        "unpaged": false,
//        "paged": true
//    },
//    "last": true,
//    "totalElements": 3,
//    "totalPages": 1,
//    "size": 5,
//    "number": 0,
//    "sort": {
//        "empty": true,
//        "sorted": false,
//        "unsorted": true
//    },
//    "first": true,
//    "numberOfElements": 3,
//    "empty": false
//}