package com.example.demo.dto;

import lombok.Data;

@Data
public class MemoDTO {
    Long id;
    String name;
    String content;
    Long category_id;
}
