package com.volkangurbuz.spring5webfluxrest.domain;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Category {

  private String id;
  private String description;
}
