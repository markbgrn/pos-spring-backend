package com.pos.posspringbackend.product.entity;

import com.pos.posspringbackend.category.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name= "tbl_product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer prodId;
    @Column(name="product_name", nullable = false, length = 50)
    @NotBlank(message = "Product name is required")
    private String prodName;
    @Column(name="product_desc", nullable = false, length = 100)
    @NotBlank(message = "Product description is required")
    private String prodDesc;
    @Column(name="unitPrice")
    @NotNull(message = "Unit price is required")
    private Double unitPrice;
    @Column(name="quantity")
    @NotNull(message = "Quantity is required")
    private Integer quantity;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id")
    @NotNull(message = "Category is required")
    private Category category;
}
