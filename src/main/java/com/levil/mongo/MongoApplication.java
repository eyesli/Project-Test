package com.levil.mongo;


import groovy.transform.CompileStatic;
import groovy.transform.TypeChecked;
import org.codehaus.groovy.ast.stmt.Statement;
import org.codehaus.groovy.ast.stmt.WhileStatement;
import org.codehaus.groovy.control.CompilerConfiguration;
import org.codehaus.groovy.control.customizers.ASTTransformationCustomizer;
import org.codehaus.groovy.control.customizers.CompilationCustomizer;
import org.codehaus.groovy.control.customizers.SecureASTCustomizer;
import org.codehaus.groovy.syntax.Types;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.codehaus.groovy.ast.ClassHelper.CLOSURE_TYPE;


@SpringBootApplication(scanBasePackages = "com.levil",exclude={DataSourceAutoConfiguration.class})
public class MongoApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(MongoApplication.class, args);


    }
//    @Bean
//    public SecureASTCustomizer secureASTCustomizer() {
//        final SecureASTCustomizer secure = new SecureASTCustomizer();
//        secure.setClosuresAllowed(true);
//
//        List<Integer> tokensBlacklist = new ArrayList<>();
////        tokensBlacklist.add(Types.KEYWORD_WHILE);
////        tokensBlacklist.add(Types.KEYWORD_GOTO);
//        secure.setDisallowedTokens(tokensBlacklist);
//        List<Class<? extends Statement>> statementBlacklist = new ArrayList<>();
//        statementBlacklist.add(WhileStatement.class);
//        secure.setDisallowedStatements(statementBlacklist);
//        secure.setIndirectImportCheckEnabled(false);
//        secure.setDisallowedStaticImports(Arrays.asList("System", "Runtime", "Class"));
//        secure.setDisallowedImports(Arrays.asList("org.codehaus.groovy.runtime.*", "groovy.json.*"));
//        return secure;
//    }
//    @Bean
//    public CompilerConfiguration compilerConfiguration(SecureASTCustomizer secure) {
//        CompilerConfiguration config = new CompilerConfiguration();
////        config.setScriptBaseClass("groovy.lang.Script");
////        //
////        ASTTransformationCustomizer astcz = new ASTTransformationCustomizer(Collections.singletonMap("extensions",
////                Collections.singletonList("groovy/SandboxTypeCheckingExtension.groovy")), TypeChecked.class);
//        config.addCompilationCustomizers(secure);
//        config.setSourceEncoding("UTF-8");
//        return config;
//    }

}
