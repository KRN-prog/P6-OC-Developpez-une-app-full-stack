package com.openclassrooms.mdd.controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/mdd")
public class ArticleController {

    @GetMapping("/article/{id}")
    public Integer getArticleById(@Valid @RequestBody Integer id) {
        return id;
    }

}
