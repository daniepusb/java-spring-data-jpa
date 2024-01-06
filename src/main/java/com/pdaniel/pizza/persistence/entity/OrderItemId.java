package com.pdaniel.pizza.persistence.entity;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class OrderItemId implements Serializable {
    private Integer idOrder;
    private Integer idItem;
}
