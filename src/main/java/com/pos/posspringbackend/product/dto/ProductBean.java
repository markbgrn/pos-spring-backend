package com.pos.posspringbackend.product.dto;

import com.pos.posspringbackend.category.entity.Category;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;

@Data
@Builder
public class ProductBean {
    private Integer prodId;
    private String prodName;
    private String prodDesc;
    private Double unitPrice;
    private Integer quantity;
    private Integer categoryId;
}
