package com.levil.mongo.config;

import groovy.transform.CompileStatic;
import groovy.transform.TypeChecked;
import org.codehaus.groovy.ast.stmt.Statement;
import org.codehaus.groovy.ast.stmt.WhileStatement;
import org.codehaus.groovy.control.CompilerConfiguration;
import org.codehaus.groovy.control.customizers.ASTTransformationCustomizer;
import org.codehaus.groovy.control.customizers.SecureASTCustomizer;
import org.codehaus.groovy.syntax.Types;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.Collections.singletonList;
import static java.util.Collections.singletonMap;

@Configuration
public class GroovyConfig {

    @Bean
    public SecureASTCustomizer secureASTCustomizer() {
        final SecureASTCustomizer secure = new SecureASTCustomizer();
        secure.setClosuresAllowed(true);

        List<Integer> tokensBlacklist = new ArrayList<>();
        tokensBlacklist.add(Types.KEYWORD_WHILE);
        tokensBlacklist.add(Types.KEYWORD_GOTO);
        secure.setDisallowedTokens(tokensBlacklist);
        List<Class<? extends Statement>> statementBlacklist = new ArrayList<>();
        statementBlacklist.add(WhileStatement.class);
        secure.setDisallowedStatements(statementBlacklist);
        secure.setIndirectImportCheckEnabled(false);
        secure.setDisallowedStaticImports(Arrays.asList("System", "Runtime", "Class"));
        secure.setDisallowedImports(Arrays.asList("org.codehaus.groovy.runtime.*", "groovy.json.*"));
        return secure;
    }

    @Bean
    public CompilerConfiguration compilerConfiguration(SecureASTCustomizer secure) {
        final CompilerConfiguration config = new CompilerConfiguration();
            ASTTransformationCustomizer astcz = new ASTTransformationCustomizer(
                    singletonMap("extensions", singletonList("groovy/SandboxTypeCheckingExtension.groovy")),
                    TypeChecked.class);
        config.addCompilationCustomizers(secure,astcz);
        config.setSourceEncoding("UTF-8");
        return config;
    }
}
