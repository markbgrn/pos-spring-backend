package com.pos.posspringbackend.category.document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "category")
public class CategoryDocument {
    @Id
    private String id;
    private String name;
    private String description;
}
