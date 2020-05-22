package com.volkangurbuz.spring5webfluxrest.controller;

import com.volkangurbuz.spring5webfluxrest.domain.Category;
import com.volkangurbuz.spring5webfluxrest.domain.Vendor;
import com.volkangurbuz.spring5webfluxrest.repositories.VendorRepository;
import org.reactivestreams.Publisher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class VendorController {

  private final VendorRepository vendorRepository;

  public VendorController(VendorRepository vendorRepository) {
    this.vendorRepository = vendorRepository;
  }

  @GetMapping("api/v1/vendors")
  Flux<Vendor> listVendors() {
    return vendorRepository.findAll();
  }

  // mono is zero one one element
  @GetMapping("/api/v1/vendors/{id}")
  Mono<Vendor> getById(@PathVariable String id) {
    return vendorRepository.findById(id);
  }

  @ResponseStatus(HttpStatus.CREATED)
  @PostMapping("/api/v1/vendors/")
  Mono<Void> createVendor(@RequestBody Publisher<Vendor> vendorStream) {
    return vendorRepository.saveAll(vendorStream).then();
  }
}
