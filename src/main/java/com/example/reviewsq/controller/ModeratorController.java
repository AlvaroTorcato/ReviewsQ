package com.example.reviewsq.controller;


import com.example.reviewsq.model.ChangeStatus;
import com.example.reviewsq.model.ReviewDTO;
import com.example.reviewsq.service.ReviewService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Tag(name = "Moderator", description = "Endpoints for managing reviews")
@RequestMapping("/moderator")
@RestController
@Controller
public class ModeratorController {
    @Autowired
    ReviewService service;

    @Operation(summary = "Get all pending reviews")
    @GetMapping(value = "/pending")
    List<ReviewDTO> findAllReviewsPending(@RequestParam Integer pageNo, @RequestParam Integer pageSize, HttpServletRequest request){
        return service.findAllReviewsPending(pageNo,pageSize,request);
    }

}
