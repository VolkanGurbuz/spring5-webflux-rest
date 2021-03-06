package com.volkangurbuz.spring5webfluxrest.controller;

import com.volkangurbuz.spring5webfluxrest.domain.Vendor;
import com.volkangurbuz.spring5webfluxrest.repositories.VendorRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.reactivestreams.Publisher;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.mockito.ArgumentMatchers.any;

public class VendorControllerTest {

  WebTestClient webTestClient;
  VendorRepository vendorRepository;
  VendorController vendorController;

  @Before
  public void setUp() {
    vendorRepository = Mockito.mock(VendorRepository.class);
    vendorController = new VendorController(vendorRepository);
    webTestClient = WebTestClient.bindToController(vendorController).build();
  }

  @Test
  public void list() {

    BDDMockito.given(vendorRepository.findAll())
        .willReturn(
            Flux.just(
                Vendor.builder().firstName("Fred").lastName("Flintstone").build(),
                Vendor.builder().firstName("Barney").lastName("Rubble").build()));

    webTestClient.get().uri("/api/v1/vendors").exchange().expectBodyList(Vendor.class).hasSize(2);
  }

  @Test
  public void getById() {
    BDDMockito.given(vendorRepository.findById("someid"))
        .willReturn(Mono.just(Vendor.builder().firstName("Jimmy").lastName("Johns").build()));

    webTestClient.get().uri("/api/v1/vendors/someid").exchange().expectBody(Vendor.class);
  }

  @Test
  public void createVendor() {
    BDDMockito.given(vendorRepository.saveAll(any(Publisher.class)))
        .willReturn(Flux.just(Vendor.builder().firstName("hey").lastName("try").build()));

    Mono<Vendor> vendorToSaveMono = Mono.just(Vendor.builder().firstName("test").build());

    webTestClient
        .post()
        .uri("/api/v1/vendors/")
        .body(vendorToSaveMono, Vendor.class)
        .exchange()
        .expectStatus()
        .isCreated();
  }

  @Test
  public void updateVendor() {

    BDDMockito.given(vendorRepository.save(any(Vendor.class)))
        .willReturn(Mono.just(Vendor.builder().build()));

    Mono<Vendor> vendorToMono = Mono.just(Vendor.builder().firstName("some vendor").build());

    webTestClient
        .put()
        .uri("/api/v1/vendors/vendor1")
        .body(vendorToMono, Vendor.class)
        .exchange()
        .expectStatus()
        .isOk();
  }
}
