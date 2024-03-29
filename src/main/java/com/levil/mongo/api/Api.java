package com.levil.mongo.api;

import groovy.lang.Binding;
import io.github.code.visual.workflow.WorkflowManager;
import lombok.extern.slf4j.Slf4j;
import org.codehaus.groovy.ast.stmt.Statement;
import org.codehaus.groovy.ast.stmt.WhileStatement;
import org.codehaus.groovy.control.customizers.SecureASTCustomizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@RestController
@Slf4j
public class Api {

    @Autowired
    WorkflowManager workflowManager;


    @GetMapping(value = "/api2")
    public Object sendSingleEmail() throws IOException {
        File file = new File( System.getProperty("user.dir")+"\\src\\main\\java\\com\\levil\\mongo\\groovy\\Test2.groovy");
        ArrayList<File> objects = new ArrayList<>();
        objects.add(file);
        Binding binding = new Binding();
        workflowManager.localTestScript(objects, binding);
        return binding;
    }

    public String api22() {
        return UUID.randomUUID().toString();
    }
}
