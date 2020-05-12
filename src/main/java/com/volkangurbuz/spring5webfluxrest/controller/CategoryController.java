package com.volkangurbuz.spring5webfluxrest.controller;

import com.volkangurbuz.spring5webfluxrest.domain.Category;
import com.volkangurbuz.spring5webfluxrest.repositories.CategoryRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

// regular spring mvc here
@RestController
public class CategoryController {

  private final CategoryRepository categoryRepository;

  public CategoryController(CategoryRepository categoryRepository) {
    this.categoryRepository = categoryRepository;
  }

  // FLux is zero or many elements
  @GetMapping("/api/v1/categories")
  Flux<Category> listCategory() {
    return categoryRepository.findAll();
  }

  // mono is zero one one element
  @GetMapping("/api/v1/categories/{id}")
  Mono<Category> getById(@PathVariable String id) {
    return categoryRepository.findById(id);
  }
}
