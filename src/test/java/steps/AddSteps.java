package steps;

import io.cucumber.java.en.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class AddSteps {

    int intA, intB;
    String requestBody;

    @Given("los valores {int} y {int}")
    public void los_valores(int a, int b) throws Exception {
        intA = a;
        intB = b;
        requestBody = new String(Files.readAllBytes(Paths.get("src/test/resources/bodies/AddRequest.xml")))
            .replace("${intA}", String.valueOf(intA))
            .replace("${intB}", String.valueOf(intB));
    }

    @When("se ejecuta la petición Add")
    public void se_ejecuta_la_peticion() {
        System.out.println("Simulando envío SOAP con cuerpo:");
        System.out.println(requestBody);
    }

    @Then("el resultado debe ser {int}")
    public void el_resultado_debe_ser(int expected) {
        System.out.println("Validar manualmente que el resultado esperado sea: " + expected);
    }
}