package steps;

import io.cucumber.java.en.*;
import org.junit.jupiter.api.Assertions;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.*;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.w3c.dom.Document;

/**
 * Clase de definición de pasos para pruebas BDD del servicio SOAP Add.
 */
public class AddSteps {

    int intA, intB;
    String requestBody;
    int actualResult;

    @Given("los valores {int} y {int}")
    public void los_valores(int a, int b) throws Exception {
        intA = a;
        intB = b;

        requestBody = new String(Files.readAllBytes(Paths.get("src/test/resources/bodies/AddRequest.xml")))
                .replace("${intA}", String.valueOf(intA))
                .replace("${intB}", String.valueOf(intB));
    }

    @When("se ejecuta la petición Add")
    public void se_ejecuta_la_peticion() throws Exception {
        URL url = new URL("http://www.dneonline.com/calculator.asmx");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        // Configura la conexión
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "text/xml; charset=utf-8");
        connection.setRequestProperty("SOAPAction", "http://tempuri.org/Add");
        connection.setDoOutput(true);

        // Envia la petición SOAP
        try (OutputStream os = connection.getOutputStream()) {
            byte[] input = requestBody.getBytes("utf-8");
            os.write(input, 0, input.length);
        }

        // Captura la respuesta
        InputStream responseStream = connection.getInputStream();

        // Parsear XML de respuesta para obtener AddResult
        Document responseDoc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(responseStream);
        XPath xpath = XPathFactory.newInstance().newXPath();
        String resultString = xpath.evaluate("//*[local-name()='AddResult']/text()", responseDoc);

        actualResult = Integer.parseInt(resultString);
    }

    @Then("el resultado debe ser {int}")
    public void el_resultado_debe_ser(int expected) {
        Assertions.assertEquals(expected, actualResult, "El resultado de la suma no es correcto.");
    }
}