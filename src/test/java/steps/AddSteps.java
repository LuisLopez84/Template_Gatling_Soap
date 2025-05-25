package steps;

import io.cucumber.java.en.*;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Clase de definición de pasos para pruebas BDD del servicio SOAP Add.
 * Utiliza Cucumber para mapear pasos definidos en el archivo .feature.
 */
public class AddSteps {

    // Valores a sumar enviados en la petición SOAP
    int intA, intB;

    // Cuerpo de la petición SOAP con los valores reemplazados
    String requestBody;

    /**
     * Paso Given: Captura dos valores enteros y reemplaza las variables en el template XML.
     *
     * @param a Primer valor entero a enviar en la petición
     * @param b Segundo valor entero a enviar en la petición
     * @throws Exception si ocurre un error al leer el archivo XML
     */
    @Given("los valores {int} y {int}")
    public void los_valores(int a, int b) throws Exception {
        intA = a;
        intB = b;

        // Lee el archivo de plantilla XML y reemplaza las variables ${intA} y ${intB}
        requestBody = new String(Files.readAllBytes(Paths.get("src/test/resources/bodies/AddRequest.xml")))
                .replace("${intA}", String.valueOf(intA))
                .replace("${intB}", String.valueOf(intB));
    }

    /**
     * Paso When: Simula el envío de la petición SOAP.
     * En esta implementación solo imprime el cuerpo, útil para pruebas manuales.
     */
    @When("se ejecuta la petición Add")
    public void se_ejecuta_la_peticion() {
        System.out.println("Simulando envío SOAP con cuerpo:");
        System.out.println(requestBody);
    }

    /**
     * Paso Then: Muestra por consola el valor esperado como resultado.
     * La validación aún es manual; se puede mejorar parseando la respuesta real.
     *
     * @param expected Valor esperado de la suma
     */
    @Then("el resultado debe ser {int}")
    public void el_resultado_debe_ser(int expected) {
        System.out.println("Validar manualmente que el resultado esperado sea: " + expected);
    }
}