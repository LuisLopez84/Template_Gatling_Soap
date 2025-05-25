package simulations

import io.gatling.core.Predef._
import io.gatling.http.Predef._

import java.nio.file.{Files, Paths}

class SoapSimulation extends Simulation {

  val requestBody = new String(Files.readAllBytes(Paths.get("src/test/resources/bodies/AddRequest.xml")))
    .replace("${intA}", "10")
    .replace("${intB}", "20")

  val httpProtocol = http
    .baseUrl("http://www.dneonline.com")
    .header("Content-Type", "text/xml; charset=utf-8")
    .header("SOAPAction", "http://tempuri.org/Add")

  val scn = scenario("SOAP Add Load Test")
    .exec(
      http("Add SOAP Request")
        .post("/calculator.asmx")
        .body(StringBody(requestBody)).asXml
        .check(status.is(200))
    )

  setUp(
    scn.inject(atOnceUsers(10))
  ).protocols(httpProtocol)
}