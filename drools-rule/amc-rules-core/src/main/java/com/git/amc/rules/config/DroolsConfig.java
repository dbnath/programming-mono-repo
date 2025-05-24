package com.git.amc.rules.config;

import com.git.amc.rules.exception.RuleException;
import org.kie.api.KieServices;
import org.kie.api.builder.*;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.internal.io.ResourceFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import java.io.IOException;

@Configuration
public class DroolsConfig {
    private static final String RULES_PATH = "com/git/amc/rules/def/";
    private static final KieServices kieServices = KieServices.Factory.get();

    @Bean
    public KieSession kieSession(KieContainer kieContainer) {
        return kieContainer.newKieSession();
    }

    @Bean
    public KieContainer kieContainer() throws RuleException {
        try {
            configureKieRepository();
            KieBuilder kieBuilder = kieServices.newKieBuilder(kieFileSystem());
            kieBuilder.buildAll();

            KieModule kieModule = kieBuilder.getKieModule();
            return kieServices.newKieContainer(kieModule.getReleaseId());
        } catch (Exception exception) {
            throw new RuleException(exception.getMessage());
        }
    }

    private KieFileSystem kieFileSystem() throws IOException {
        KieFileSystem kieFileSystem = kieServices.newKieFileSystem();
        Resource[] ruleFiles = getRuleFiles();
        for(Resource resource: ruleFiles) {
            if (resource instanceof UrlResource) {
                kieFileSystem.write(ResourceFactory.newUrlResource(((UrlResource) resource).getURL()));
            } else {
                kieFileSystem.write(ResourceFactory.newFileResource(((FileSystemResource) resource).getFile()));
            }
        }
        return kieFileSystem;
    }

    private void configureKieRepository() {
        KieRepository repository = kieServices.getRepository();
        repository.addKieModule(repository::getDefaultReleaseId);
    }

    private Resource[] getRuleFiles() throws IOException {
        ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
        return resourcePatternResolver.getResources("classpath*:" + RULES_PATH + "**/*.*");
    }
}
