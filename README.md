Documentación Parcial 1 Juan Felipe Morales Cadavid

1. Introducción
Este documento detalla el proceso de desarrollo y pruebas realizado en el proyecto, así como los errores encontrados y las soluciones implementadas para su corrección.
2. Configuración del Entorno
•	IDE utilizado: Visual Studio Code
•	Framework: Spring Boot con Maven
•	Lenguaje: Java
•	Herramientas de pruebas: JUnit 5 y MockMvc
•	Se configuraron las dependencias en pom.xml

3. Pruebas realizadas
3.1	Prueba de integración automatizada con API REST:
Para la prueba de integración, usamos la anotación @SpringBootTest junto con MockMvc para enviar una petición HTTP al endpoint /api/hello y verificar su respuesta.
Esta prueba se desarrolló en la clase HelloControllerIT.java
 



3.2 Pruebas unitarias:
Las pruebas unitarias se encuentran en la clase HelloControllerTest.java, donde verificamos que el método sayHello() devuelva el mensaje esperado.
Prueba testSayHello():
Llama al método sayHello() de HelloController y verifica que la respuesta obtenida sea exactamente igual a "¡Hola, Spring Boot con Maven!".
Prueba testSayHelloNotEmpty():
Llama al método sayHello() de HelloController y verifica que la respuesta obtenida no esté vacía.
 









4.	Comandos Maven utilizados:

•	mvn spring-boot:run
Se usa para para ejecutar un proyecto de springboot con Maven. Al ejecutar el comando este usa el Local Host con el port 8080 (http). De esta manera se inicializa la aplicación web.
INICIO de ejecución
 
Al ir a la dirección del puerto identificado con la ejecución del programa aparece el mensaje definido 
 

FIN de ejecución: para finalizar el uso del puerto se cancela la ejecución con ctrl C,
 

•	mvn test
Este comando permite compilar y ejecutar las pruebas definidas en la aplicación para asegurar que el código es correcto y no afectar las siguientes partes del desarrollo
El resultado de la ejecución de este comando es:
 
Dando como resultado el correcto funcionamiento de las pruebas definidas sin errores.
•	mvn clean test
Se usó para realizar pruebas luego de ejecutar el comando mvn test. La definición de la función clean en el comando se usa para eliminar archivos de compilación de pruebas anteriores y ejecutar desde cero, asegurando el funcionamiento correcto de las pruebas. El resultado de este comando es el mismo que mvn test
  



5. Errores Encontrados y Soluciones Aplicadas
5.1. Error: "Failed to clean project"
•	Causa: Un archivo en la carpeta target estaba en uso.
•	Solución: Se eliminó la carpeta creada la cual aparecía en uso y no permitía realizar más pruebas
5.2. Error: Dependencias faltantes
•	Causa: Maven no encontraba las dependencias necesarias para ejecutar las pruebas.
•	Solución: Se agregaron las dependencias correctas en pom.xml
5.3. Error: Prueba de API fallida
•	Causa: La URL del endpoint no coincidía con la definida en HelloController.
•	Solución: Se ajustó la ruta añadiendo /api/hello para que coincidiera con la petición.
6. Resultados Finales
Tras la aplicación de las soluciones, se ejecutaron las pruebas con el comando mvn clean test, obteniendo:
•	Tests run: 3, Failures: 0, Errors: 0, Skipped: 0
•	BUILD SUCCESS
7. Conclusiones
•	Se logró la correcta ejecución de las pruebas unitarias e integración.
•	Se documentaron los errores y sus soluciones, facilitando futuras referencias.
•	Se aseguró el correcto funcionamiento del controlador en un entorno de pruebas automatizadas.

