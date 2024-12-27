package com.example.courseWork.controllers;

import com.example.courseWork.models.Recommendations;
import com.example.courseWork.models.UserRecom;
import com.example.courseWork.services.UserRecomService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@RequestMapping("/user")
@RestController
@RequiredArgsConstructor
public class RulesController {

    private final UserRecomService userRecomService;

    @Operation(summary = "Получение пользователя")
    @GetMapping("{id}")
    public ResponseEntity<UserRecom> getUser(@PathVariable UUID id) {
        UserRecom user;
        UserRecom userAdd;
        List<Recommendations> recs;
        user = userRecomService.getRecom(id);
        userAdd = userRecomService.getAdditionaltRecommendation(id);
        recs = userAdd.getRecomed();
        recs.addAll(user.getRecomed());
        user.setRecomed(recs);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }
}
