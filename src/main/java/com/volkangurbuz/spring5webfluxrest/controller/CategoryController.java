package com.volkangurbuz.spring5webfluxrest.controller;

import com.volkangurbuz.spring5webfluxrest.domain.Category;
import com.volkangurbuz.spring5webfluxrest.repositories.CategoryRepository;
import org.reactivestreams.Publisher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
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

  @ResponseStatus(HttpStatus.CREATED)
  @PostMapping("/api/v1/categories")
  Mono<Void> createCategory(@RequestBody Publisher<Category> categoryStream) {

    return categoryRepository.saveAll(categoryStream).then();
  }

  @PutMapping("/api/v1/categories/{id}")
  Mono<Category> updateCategory(@PathVariable String id, @RequestBody Category category) {
    category.setId(id);
    return categoryRepository.save(category);
  }
}
