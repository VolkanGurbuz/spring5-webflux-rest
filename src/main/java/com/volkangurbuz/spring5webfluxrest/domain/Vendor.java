package com.volkangurbuz.spring5webfluxrest.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Document
public class Vendor {

  @Id private String id;
  private String firstName;
  private String lastName;
}
