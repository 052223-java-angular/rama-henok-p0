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
    private String reviewId;
    private int rating;
    private String comment;
    private String userId;
    private int productId;
}