package com.example.courseWork.services;

import com.example.courseWork.models.DynamicRules;
import com.example.courseWork.repositories.RulesRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RulesService {
    private RulesRepository rulesRepository;

    public RulesService(RulesRepository rulesRepository) {
        this.rulesRepository = rulesRepository;
    }


    public List<DynamicRules> getAll() {
        return rulesRepository.findAll();
    }

    public void addRule(DynamicRules rule) {
        rulesRepository.save(rule);
    }


}
