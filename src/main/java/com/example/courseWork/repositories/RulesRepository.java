package com.example.courseWork.repositories;

import com.example.courseWork.models.DynamicRules;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RulesRepository extends JpaRepository<DynamicRules, Long> {


}