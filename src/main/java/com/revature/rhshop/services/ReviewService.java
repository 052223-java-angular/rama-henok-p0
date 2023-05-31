package com.revature.rhshop.services;

import java.util.List;
import com.revature.rhshop.daos.ReviewDAO;
import com.revature.rhshop.models.Reviews;


import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ReviewService {
    private final ReviewDAO reviewDAO;

    public List<Reviews> getReviewsByProductName(String name) {
        return reviewDAO.getReviewsByProductName(name);
    }    
}
