package com.group8.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BuyerQueryParam {
    private Integer page;
    private Integer limit;
    private String keyword;
    private Integer categoryId;

}
