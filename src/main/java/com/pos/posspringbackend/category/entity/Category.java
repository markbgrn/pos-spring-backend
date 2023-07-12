package com.pos.posspringbackend.category.entity;

import lombok.*;

import javax.persistence.*;
//@Data
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "tbl_category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String categoryName;
    private String categoryDesc;
}
