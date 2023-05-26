package com.revature.rhshop.models;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Categories {
    private int categoryId;
    private String categoryName;
   

    public Categories(String categoryName) {
        this.categoryName = categoryName;
      
    }
}