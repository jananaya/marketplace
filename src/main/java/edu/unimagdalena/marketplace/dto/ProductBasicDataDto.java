package edu.unimagdalena.marketplace.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductBasicDataDto {
    private String name;
    private Float price;
    private Integer stock;
}
