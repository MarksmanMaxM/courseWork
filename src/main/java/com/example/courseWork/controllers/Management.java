package com.example.courseWork.controllers;

import com.example.courseWork.models.DynamicRules;
import com.example.courseWork.services.PomXmlParser;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

@RequestMapping("/management")
@RestController
public class Management {
    PomXmlParser pomXmlParser;

    public Management() {
        this.pomXmlParser = new PomXmlParser();
    }

    @PostMapping("/clear-caches")
    @CacheEvict(cacheNames = "recordsCache", allEntries = true)
    public void clearCache() {

    }

    @PostMapping("/info")
    public String getInfo() throws IOException, ParserConfigurationException, SAXException {
        String str = null;
        str = pomXmlParser.getData();
        return str;
    }


}
