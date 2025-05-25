Feature: Servicio SOAP Add

  Scenario: Suma de dos valores positivos
    Given los valores 3 y 5
    When se ejecuta la petición Add
    Then el resultado debe ser 8

  Scenario: Suma de cero y un valor
    Given los valores 0 y 7
    When se ejecuta la petición Add
    Then el resultado debe ser 7