package com.mycompany.app.models;


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
public class Review {
    private String review_id;
    private int rate;
    private String comment;
    private String user_id;
    private String product_id;
}
