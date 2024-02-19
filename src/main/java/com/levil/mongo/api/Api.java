package com.levil.mongo.api;

import groovy.lang.Binding;
import groovy.lang.GroovyClassLoader;
import lombok.extern.slf4j.Slf4j;
import org.codehaus.groovy.runtime.InvokerHelper;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@Slf4j
public class Api {


    @GetMapping(value = "/api2")
    public Object sendSingleEmail() throws IOException {
        File file = new File("E:\\Project-Test\\src\\main\\java\\com\\levil\\mongo\\groovy\\Test2.groovy");
        ArrayList<File> objects = new ArrayList<>();
        objects.add(file);


        testGroovyScript(objects,new Binding());
        return UUID.randomUUID().toString();
    }

    @SuppressWarnings("all")
    public Binding testGroovyScript(List<File> files, Binding binding) throws IOException {

        GroovyClassLoader tempGroovyClassLoader = new GroovyClassLoader(Thread.currentThread().getContextClassLoader());

        for (File file : files) {
            Class aClass = tempGroovyClassLoader.parseClass(file);
            InvokerHelper.createScript(aClass, binding).run();
        }
        return binding;

    }
    public String api22() {
        return UUID.randomUUID().toString();
    }
}
