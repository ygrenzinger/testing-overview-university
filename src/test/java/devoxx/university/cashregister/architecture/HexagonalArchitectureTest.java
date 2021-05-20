package devoxx.university.cashregister.architecture;

import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

@AnalyzeClasses(packages = "devoxx.university", importOptions = {ImportOption.DoNotIncludeTests.class})
public class HexagonalArchitectureTest {

    // https://www.archunit.org/use-cases

    //@ArchTest
    public static ArchRule enforceHexagonalArchitechture =
            classes().that()
                    .resideInAPackage("devoxx.university.cashregister.domain..")
                    .should()
                    .onlyAccessClassesThat()
                    .resideInAnyPackage(
                            "devoxx.university.cashregister.domain..",
                            "java.util..",
                            "java.lang.."
                    );

}
