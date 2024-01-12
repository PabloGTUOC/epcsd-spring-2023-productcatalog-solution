package edu.uoc.epcsd.productcatalog.test;

import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import org.springframework.stereotype.Service;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.library.Architectures.onionArchitecture;



@AnalyzeClasses(packages = "edu.uoc.epcsd.productcatalog", importOptions = { ImportOption.DoNotIncludeTests.class,
        ImportOption.DoNotIncludeJars.class })
public class ArchitectureTest {
    @ArchTest
    static final ArchRule domain_service_with_spring_annotation = classes()
            .that().resideInAPackage("..domain.service..")
            .and().areAnnotatedWith(Service.class)
            .should().haveSimpleNameEndingWith("ServiceImpl");

    @ArchTest
    static final ArchRule onion_architecture_is_respected = onionArchitecture()
            .domainModels("..domain..")
            .domainServices("..domain.service..")
            .applicationServices("..application..")
            .adapter("persistence", "..infrastructure.repository..")
            .adapter("rest", "..application.rest..");
}
