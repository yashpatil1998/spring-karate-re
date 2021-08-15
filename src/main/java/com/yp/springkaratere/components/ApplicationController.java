package com.yp.springkaratere.components;

import com.intuit.karate.Runner;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class ApplicationController {

    @PostMapping(path = "/new")
    public boolean controller(@RequestBody Model model) {
        return checkRules(model);
    }

    public boolean checkRules(Model model) {
        String rulesPath = "classpath:karaterules/rules.feature";
        Map<String, Object> featureFileParams = new HashMap<>();
        featureFileParams.put("model", model);
        Map<String, Object> result = Runner.runFeature(rulesPath, featureFileParams, false);
        return (boolean) result.get("condition");
    }

}
