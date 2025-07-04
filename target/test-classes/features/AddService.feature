Feature: Servicio SOAP Add
  Como consumidor del servicio SOAP de suma
  Quiero enviar dos valores enteros
  Para obtener como respuesta la suma correcta de ambos

  # Este escenario valida que el servicio pueda sumar dos números positivos correctamente
  Scenario: Suma de dos valores positivos
    # Se definen los valores de entrada
    Given los valores 3 y 5
    # Se envía la petición SOAP al servicio Add
    When se ejecuta la petición Add
    # Se espera como resultado la suma correcta
    Then el resultado debe ser 8

  # Este escenario prueba un caso donde uno de los valores es cero
  Scenario: Suma de cero y un valor
    # Se define un valor cero y otro positivo
    Given los valores 0 y 7
    # Se ejecuta la misma operación
    When se ejecuta la petición Add
    # Se espera como resultado el otro valor
    Then el resultado debe ser 7

   # Este escenario prueba un caso donde uno de los valores es negativo
  Scenario: Suma de un valor entero positivo y un entero negativo
    # Se define un valor cero y otro positivo
    Given los valores 9 y -7
    # Se ejecuta la misma operación
    When se ejecuta la petición Add
    # Se espera como resultado el otro valor
    Then el resultado debe ser 2

   # Este escenario prueba un caso de error donde el resultado de la suma no corresponde
  Scenario: Suma de dos valores positivos con resultado de error
    # Se define un valor cero y otro positivo
    Given los valores 9 y 9
    # Se ejecuta la misma operación
    When se ejecuta la petición Add
    # Se espera como resultado el otro valor
    Then el resultado debe ser 20