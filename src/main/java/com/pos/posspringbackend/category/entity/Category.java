package com.pos.posspringbackend.category.entity;

import com.pos.posspringbackend.product.entity.Product;
import lombok.*;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

//@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "tbl_category")
public class Category {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Integer id;
    private String categoryName;
    private String categoryDesc;
}
