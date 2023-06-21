package com.revature.rhshop.models;

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
public class Reviews {
    private int review_id;
    private int rating;
    private String comment;
    private String user_id;
    private int product_id;
}