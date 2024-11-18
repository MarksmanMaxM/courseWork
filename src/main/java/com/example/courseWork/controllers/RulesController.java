package com.example.courseWork.controllers;

import com.example.courseWork.models.Recommendations;
import com.example.courseWork.models.UserRecom;
import com.example.courseWork.services.UserRecomService;
import org.apache.catalina.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@RequestMapping("/user")
@RestController
public class RulesController {

        private final UserRecomService userRecomService;


    public RulesController(UserRecomService userRecomService, List<Recommendations> recommendations) {
        this.userRecomService = userRecomService;

    }


//       public StudentController(StudentService studentService) {
  //          this.studentService = studentService;
 //       }


        @GetMapping("{id}")
        public ResponseEntity<UserRecom> getUser(@PathVariable UUID id) {
            List<Recommendations> recommendations = userRecomService.setRecommendations();
            UserRecom user = userRecomService.getRecom(id);
            if(user == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(user);
        }







        }
