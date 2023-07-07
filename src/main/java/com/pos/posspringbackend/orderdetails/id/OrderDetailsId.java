package com.pos.posspringbackend.orderdetails.id;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Getter
public class OrderDetailsId implements Serializable {
    private Integer orderId;
    private Integer productId;
}
