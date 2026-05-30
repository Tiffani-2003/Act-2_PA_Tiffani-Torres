Estos son los directorios principales de un proyecto basado en Gradle: 
src contiene el código fuente de nuestra aplicación, mientras que build almacena automáticamente los archivos generados y compilados tras ejecutar el proyecto.

![Estructura de directorios](1.png)

La capa service contiene la lógica de negocio de la aplicación, donde se procesan y transforman los datos provenientes del repositorio. En este archivo HardwareService.java, se implementan los algoritmos y cálculos necesarios para generar reportes, como el agrupamiento y análisis de hardware por categoría.

![Estructura de directorios](2.png)

La capa repository actúa como el puente de comunicación con la base de datos, permitiendo realizar operaciones de persistencia de forma sencilla. En HardwareRepository.java, al extender JpaRepository, el sistema obtiene automáticamente métodos listos para consultar, guardar o eliminar datos de la entidad HardwareEntity.

![Estructura de directorios](3.png)

La capa controller gestiona las solicitudes HTTP entrantes y define los puntos de acceso (endpoints) de la API. En HardwareController.java, se exponen las funcionalidades del sistema mediante anotaciones @GetMapping, coordinando la interacción entre el cliente y la lógica definida en los servicios.

![Estructura de directorios](4.png)

La capa ai está diseñada para albergar funcionalidades de procesamiento inteligente o análisis avanzado de datos. En este archivo AiService.java, se encapsula la lógica para generar reportes dinámicos y formateados que presentan de forma estructurada los resultados procesados por el sistema.
