package runners;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

/**
 * Clase que actúa como ejecutor de pruebas para los escenarios definidos en Gherkin (.feature). Para ejecutar desde maven utilizar el comando mvn test
 * Utiliza Cucumber con JUnit para ejecutar los escenarios y generar reportes compatibles con Allure.
 */
@RunWith(Cucumber.class) // Indica que se ejecutará con el runner de Cucumber
@CucumberOptions(
        plugin = {
                "pretty", // Muestra resultados legibles en consola
                "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm" // Integración con Allure para generación de reportes detallados
        },
        features = "src/test/resources/features", // Ruta donde están los archivos .feature
        glue = "steps" // Paquete donde están las clases que contienen la definición de los pasos (step definitions)
)
public class CucumberTestRunner {
        // Esta clase no necesita cuerpo, actúa como punto de entrada para la ejecución de Cucumber con JUnit
}