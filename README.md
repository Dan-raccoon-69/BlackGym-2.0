# BlackGym Manager

## Descripción
BlackGym Manager es una aplicación de gestión diseñada para facilitar el manejo eficiente de socios, planes, productos y ventas en el gimnasio BlackGym.

## Objetivos
1. Automatizar la gestión de socios y sus planes.
2. Registrar y supervisar las ventas de planes y productos.
3. Facilitar la administración de productos y su inventario.

## Características Principales
- Gestión de socios: Agregar, modificar y controlar la información de los socios.
- Ventas y transacciones: Generar reportes detallados de ventas de planes y productos.
- Productos: Administrar la lista de productos, incluyendo agregar y modificar.
- Compras: Realizar compras de productos existentes.
- Administrador: Visualizar datos del recepcionista logueado.
- Generación de Tickets: Crear tickets en formato PDF para las ventas de planes y productos.

## Tecnologías Utilizadas
- **Lenguajes de Programación:** Java (JDK 17) - Java EE (JSP, Servlets), JavaScript
- HTML
- CSS
- **Librerías - Frameworks - :**
  - JSTL 1.2.1
  - gson 2.10.1
  - itextpdf 5.5.13.3
- **Servidor:** Apache TomCat o TomEE
- **Base de Datos:** MySQL con el conector Java 8.0.30

## Arquitectura
Se utilizo el modelo vista controlador (MVC).

## Capturas de Pantalla

### Interfaz de logueo
![Página de Inicio](.//web/img/Login.png)

### Interfaz del administrador logueado
![Administrador Logueado](.//web/img/admin.png)

### Interfaz principal
![Administrador Listo](.//web/img/Home.png)

### Busqueda y recomendaciones de Usuarios
![Administrador Busqueda Usuarios](.//web/img/busqueda1.png)
![Administrador Busqueda Usuarios](.//web/img/busqueda2.png)

### Interfaz de Socios y Venta de Planes
- El formulario contiene ciertas validaciones, una de ellas es el correo:
    - ![Validación formulario](.//web/img/validacion.png)
- Agregar Socios
    - ![Agregar Socios](.//web/img/AgregarSocio.png)
- Despues de agregar al cliente, se realiza la venta con datos definidos en base al sistema y al cliente
    - ![Venta de Planes](.//web/img/ventaPlan.png)
- El administrador solo se encarga de escoger la forma de Pago, los demas datos no se pueden modificar. 

- Si no hay ningun problema, se realiza la venta y accedemos a la interfaz de Reporte de Venta de Planes
### Reportes de Venta de Planes
![Reportes de Ventas de Planes](.//web/img/ReportePlanes.png)
- Podemos generar el ticket de la venta que acabamos de realizar

### Generación de Ticket PDF - Venta de Planes
![Ticket](.//web/img/ticket.png)

### Interfaz de Venta de Productos
- La persona encargada de usar el software puede colocar que productos se venderan
    - ![Venta de Productos](.//web/img/ventaProductos.png)
    - ![Venta de Productos](.//web/img/ventaProductos2.png)
- Al momento de agregar productos, el sistema coloca de manera automatica la cantidad, la descripción, realiza el calculo del costo, asigna la fecha del dia en que se encuentre, la persona encargada solo tendra que colocar la forma de pago del cliente.
- Si no hay ningun problema, se realiza la venta y accedemos a la interfaz de Reporte de Venta de Productos.

### Reportes de Venta de Productos
    - ![Reporte de Productos](.//web/img/ReporteProductos.png)
- De igual forma podemos descargar el ticket de la compra de nuestros productos

### Generación de Ticket PDF - Venta de Productos
![Ticket](.//web/img/ticketProductos.png)

### Interfaz de Productos
- Agregar Productos
  - ![Agregar Productos](.//web/img/agregarProductos.png)
- Modificar Productos
  - ![Agregar Productos](.//web/img/verProductos.png)
  - ![Agregar Productos](.//web/img/modificarProductos.png)

## Contacto
¡Estaria encantado de recibir tus preguntas, comentarios y propuestas de colaboración! No dudes en ponerte en contacto con nosotros a través de los siguientes medios:

- **Correo Electrónico:** [bautistadanielgustavo@gmail.com](mailto:bautistadanielgustavo@gmail.com)
- **Redes Sociales:**
  - [LinkedIn](https://www.linkedin.com/in/daniel-gustavo-de-la-cruz-bautista-655127299)