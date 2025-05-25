package simulations

import io.gatling.core.Predef._
import io.gatling.http.Predef._

import java.nio.file.{Files, Paths}

/**
 * Clase SoapSimulation
 *
 * Esta simulación realiza una prueba de carga al servicio SOAP "Add" expuesto en el endpoint
 * http://www.dneonline.com/calculator.asmx. Utiliza Gatling para enviar múltiples peticiones concurrentes. Se ejecuta desde maven con: mvn gatling:test
 */
class SoapSimulation extends Simulation {

  // Carga y personaliza el cuerpo de la petición SOAP desde un archivo XML, reemplazando los valores intA e intB.
  val requestBody = new String(Files.readAllBytes(Paths.get("src/test/resources/bodies/AddRequest.xml")))
    .replace("${intA}", "10")
    .replace("${intB}", "20")

  // Configuración del protocolo HTTP para las peticiones SOAP
  val httpProtocol = http
    .baseUrl("http://www.dneonline.com") // URL base del servicio SOAP
    .header("Content-Type", "text/xml; charset=utf-8") // Cabecera típica para peticiones SOAP
    .header("SOAPAction", "http://tempuri.org/Add") // Acción SOAP específica para el método Add

  // Definición del escenario de carga
  val scn = scenario("SOAP Add Load Test") // Nombre del escenario
    .exec(
      http("Add SOAP Request") // Nombre de la solicitud
        .post("/calculator.asmx") // Ruta del servicio SOAP (path o complemento del endpoint)
        .body(StringBody(requestBody)).asXml // Cuerpo de la solicitud en formato XML
        .check(status.is(200)) // Validación: respuesta HTTP debe ser 200 OK
    )

  // Configuración de la simulación para inyectar 10 usuarios simultáneamente
  setUp(
    scn.inject(atOnceUsers(10)) // Inyecta 10 usuarios al mismo tiempo
  ).protocols(httpProtocol) // Aplica la configuración del protocolo HTTP
}