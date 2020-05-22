package com.volkangurbuz.spring5webfluxrest.controller;

import com.volkangurbuz.spring5webfluxrest.domain.Category;
import com.volkangurbuz.spring5webfluxrest.domain.Vendor;
import com.volkangurbuz.spring5webfluxrest.repositories.VendorRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
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
}
