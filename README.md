# SpeedFast_S7
# SpeedFast - Sistema de Gestión de Pedidos

Proyecto desarrollado en Java para la asignatura **Desarrollo Orientado a Objetos II**.

SpeedFast es una aplicación de escritorio que permite registrar y gestionar pedidos, asignar repartidores y procesar entregas. En esta versión se incorporó persistencia de datos utilizando **MySQL y JDBC**, permitiendo almacenar y consultar la información desde una base de datos.

## Tecnologías utilizadas

- Java
- Java Swing
- JDBC
- MySQL
- MySQL Connector/J
- Maven
- IntelliJ IDEA
- Git y GitHub

## Funcionalidades

La aplicación permite:

- Registrar nuevos pedidos.
- Validar los datos ingresados.
- Evitar registros con identificadores duplicados.
- Visualizar los pedidos mediante una tabla (`JTable`).
- Consultar los pedidos almacenados en MySQL.
- Asignar repartidores a los pedidos.
- Asignar repartidores automáticamente durante el procesamiento de la cola.
- Respetar repartidores asignados previamente.
- Procesar pedidos utilizando una cola.
- Actualizar el estado de los pedidos.
- Registrar las entregas realizadas.
- Relacionar pedidos y repartidores mediante la tabla de entregas.
- Mantener la información almacenada después de cerrar la aplicación.

## Estados de un pedido

Los pedidos pueden tener los siguientes estados:

- `PENDIENTE`
- `EN_REPARTO`
- `ENTREGADO`
- `INTERRUMPIDO`

## Base de datos

El proyecto utiliza una base de datos MySQL llamada:

```text
speedfast_db
```

La base de datos contiene las siguientes tablas:

```text
repartidor
pedido
entrega
```

La tabla `entrega` relaciona un pedido con el repartidor encargado de realizar su entrega.

El script para crear la estructura de la base de datos se encuentra en:

```text
database/speedfast_db.sql
```

## Persistencia con JDBC

La conexión con MySQL se realiza mediante JDBC y `DriverManager`.

El acceso a los datos se encuentra separado mediante clases DAO:

```text
ConexionBD
PedidoDAO
RepartidorDAO
EntregaDAO
```

Estas clases utilizan elementos de JDBC como:

- `Connection`
- `PreparedStatement`
- `ResultSet`
- `SQLException`

`PreparedStatement` es utilizado para ejecutar las operaciones SQL de forma parametrizada.

## Estructura general

```text
SpeedFast7
│
├── database
│   └── speedfast_db.sql
│
├── src
│   ├── main
│   │   └── java
│   │       ├── controladores
│   │       ├── dao
│   │       ├── main
│   │       ├── modelo
│   │       └── vista
│   │
│   └── test
│
├── pom.xml
└── README.md
```

## Configuración

### 1. Crear la base de datos

Abrir MySQL Workbench y ejecutar:

```text
database/speedfast_db.sql
```

Esto crea la base de datos y las tablas necesarias para ejecutar el proyecto.

### 2. Configurar la conexión

La configuración JDBC se encuentra en:

```text
src/main/java/dao/ConexionBD.java
```

La aplicación utiliza una URL JDBC con la siguiente estructura:

```text
jdbc:mysql://localhost:3306/speedfast_db
```

Para ejecutar el proyecto se debe contar con un usuario de MySQL con permisos sobre la base de datos `speedfast_db`.

### 3. Dependencia MySQL

El proyecto utiliza Maven para incorporar **MySQL Connector/J**, configurado en el archivo:

```text
pom.xml
```

### 4. Ejecutar la aplicación

Ejecutar la clase:

```text
src/main/java/main/Main.java
```

Desde allí se abrirá la interfaz principal de SpeedFast.

## Funcionamiento general

El flujo principal de la aplicación es:

```text
Registro de pedido
        ↓
PedidoController
        ↓
PedidoDAO
        ↓
MySQL
        ↓
Procesamiento del pedido
        ↓
Asignación de repartidor
        ↓
Actualización del estado
        ↓
EntregaDAO
        ↓
Registro de entrega
        ↓
Visualización en JTable
```

Los pedidos almacenados pueden ser recuperados desde MySQL y visualizados nuevamente en la interfaz, permitiendo mantener la información aunque la aplicación sea cerrada.

## Autor

**Sebastián Ignacio Ávila Sanhueza**

