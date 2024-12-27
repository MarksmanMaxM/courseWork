package com.example.courseWork.models;

import org.springframework.cache.annotation.Cacheable;

import java.util.List;
import java.util.UUID;

@Cacheable
public class UserRecom {
    private UUID userId;
    private List<Recommendations> recomed;

    public UserRecom(UUID userId, List<Recommendations> recomed) {
        this.userId = userId;
        this.recomed = recomed;
    }

    public UserRecom() {

    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public List<Recommendations> getRecomed() {
        return recomed;
    }

    public void setRecomed(List<Recommendations> recomed) {
        this.recomed = recomed;
    }

    @Override
    public String toString() {
        return "user_id:" + userId.toString() + '\n' +
                "recommendations:" + recomed;
    }
}
