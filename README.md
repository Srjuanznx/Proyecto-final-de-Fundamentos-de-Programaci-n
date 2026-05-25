# Sistema de Reservas de Viajes Espaciales

Proyecto final de **Fundamentos de Programación**  
Universidad EAFIT

---

## Descripción

Este proyecto consiste en el desarrollo de un **Sistema de Reservas de Viajes Espaciales** en Java, con interfaz gráfica (**GUI**), que permite a los usuarios registrarse, explorar destinos espaciales, realizar reservas y consultar información detallada del viaje.

El sistema está diseñado para simular una experiencia futurista de reserva de viajes hacia destinos como la Luna, Marte y estaciones espaciales, aplicando conceptos de programación orientada a objetos, estructuras de datos, manejo de excepciones e interfaces gráficas.

---

## Objetivo del proyecto

Crear una aplicación en Java que permita:

- Registrar usuarios.
- Mostrar destinos espaciales disponibles.
- Realizar reservas de viaje.
- Consultar información del itinerario.
- Presentar una interfaz gráfica, intuitiva y fácil de usar.
- Manejar errores mediante excepciones y mensajes claros al usuario.

---

## Funcionalidades principales

- **Registro de usuarios** con nombre, correo electrónico y preferencias.
- **Listado de destinos** como Luna, Marte y estaciones espaciales.
- **Reservas de viaje** con fecha, destino y observaciones.
- **Información del viaje** con itinerario, recomendaciones y equipo requerido.
- **Interfaz GUI** con ventanas, botones, formularios y paneles visuales.
- **Validaciones y excepciones** para evitar datos incorrectos.
- **Persistencia básica** de información, si se implementa.

---

## Requisitos del sistema

### Funcionales

1. El usuario debe poder registrarse.
2. El usuario debe poder iniciar la reserva de un viaje.
3. El sistema debe mostrar destinos disponibles.
4. El sistema debe permitir seleccionar un destino.
5. El sistema debe mostrar detalles del viaje.
6. El sistema debe validar entradas del usuario.
7. El sistema debe informar errores de forma clara.

### No funcionales

- Desarrollado en **Java**.
- Interfaz gráfica con **Swing** o **JavaFX**.
- Código organizado con enfoque **POO**.
- Fácil de usar y mantener.
- Diseño visual futurista.

---

## Tecnologías sugeridas

- **Java**
- **Swing** o **JavaFX**
- **NetBeans / IntelliJ IDEA / Eclipse**
- **GitHub** para control de versiones

---

## Estructura general del programa

```mermaid
flowchart LR
    A[Inicio] --> B[Registro de usuario]
    B --> C[Visualizar destinos]
    C --> D[Seleccionar destino]
    D --> E[Crear reserva]
    E --> F[Mostrar información del viaje]
    F --> G[Confirmación]
    G --> H[Fin]
    
```

---

## Casos de uso

```mermaid
flowchart LR
    U[Usuario] --> R1[Registrarse]
    U --> R2[Ver destinos]
    U --> R3[Reservar viaje]
    U --> R4[Consultar itinerario]
    U --> R5[Salir]

```

---

## Diseño orientado a objetos

El sistema debe estar organizado usando clases, objetos, constructores, métodos `get` y `set`, e instanciación de objetos.

### Clases sugeridas

- **Usuario**
- **DestinoEspacial**
- **Reserva**
- **Viaje**
- **SistemaReservas**
- **VentanaPrincipal**
- **FormularioRegistro**
- **FormularioReserva**
- **PanelDestinos**

---

## Diagrama de clases

```mermaid
classDiagram
    class Usuario {
        -String nombre
        -String correo
        -String preferencias
        +Usuario(String nombre, String correo, String preferencias)
        +getNombre() String
        +setNombre(String nombre)
        +getCorreo() String
        +setCorreo(String correo)
        +getPreferencias() String
        +setPreferencias(String preferencias)
    }

    class DestinoEspacial {
        -String nombre
        -String descripcion
        -double precio
        -int duracionDias
        +DestinoEspacial(String nombre, String descripcion, double precio, int duracionDias)
        +getNombre() String
        +getDescripcion() String
        +getPrecio() double
        +getDuracionDias() int
    }

    class Reserva {
        -Usuario usuario
        -DestinoEspacial destino
        -String fechaViaje
        -String observaciones
        +Reserva(Usuario usuario, DestinoEspacial destino, String fechaViaje, String observaciones)
        +getUsuario() Usuario
        +getDestino() DestinoEspacial
        +getFechaViaje() String
        +getObservaciones() String
    }

    class Viaje {
        -String itinerario
        -String equipoNecesario
        -String recomendaciones
        +Viaje(String itinerario, String equipoNecesario, String recomendaciones)
        +getItinerario() String
        +getEquipoNecesario() String
        +getRecomendaciones() String
    }

    class SistemaReservas {
        -ArrayList~Usuario~ usuarios
        -ArrayList~DestinoEspacial~ destinos
        -ArrayList~Reserva~ reservas
        +registrarUsuario(...)
        +listarDestinos()
        +crearReserva(...)
        +mostrarInformacionViaje(...)
    }

    class VentanaPrincipal
    class FormularioRegistro
    class FormularioReserva
    class PanelDestinos

    Usuario --> Reserva
    DestinoEspacial --> Reserva
    Reserva --> Viaje
    SistemaReservas --> Usuario
    SistemaReservas --> DestinoEspacial
    SistemaReservas --> Reserva
    VentanaPrincipal --> FormularioRegistro
    VentanaPrincipal --> FormularioReserva
    VentanaPrincipal --> PanelDestinos

```

---

## Flujo de reserva

```mermaid
sequenceDiagram
    actor Usuario
    participant GUI as Interfaz Gráfica
    participant Sistema as SistemaReservas

    Usuario->>GUI: Ingresa datos personales
    GUI->>Sistema: Registrar usuario
    Sistema-->>GUI: Confirmación

    Usuario->>GUI: Selecciona destino
    GUI->>Sistema: Solicita destinos disponibles
    Sistema-->>GUI: Muestra lista de destinos

    Usuario->>GUI: Elige fecha y observaciones
    GUI->>Sistema: Crear reserva
    Sistema-->>GUI: Reserva confirmada

    Usuario->>GUI: Solicita información del viaje
    GUI->>Sistema: Obtener itinerario y recomendaciones
    Sistema-->>GUI: Mostrar datos del viaje

```

---

## Interfaz gráfica

La aplicación se desarrollará con una interfaz visual que facilite la navegación del usuario. Se recomienda usar:

- Ventana principal con menú.
- Formularios separados para registro y reserva.
- Panel de destinos con tarjetas o listas.
- Botones claros para confirmar, cancelar y volver.
- Colores oscuros y detalles brillantes para un estilo futurista.

### Componentes sugeridos

- `JFrame` para la ventana principal.
- `JPanel` para organizar secciones.
- `JLabel` para mostrar texto.
- `JTextField` para entrada de datos.
- `JButton` para acciones.
- `JComboBox` para seleccionar destinos.
- `JOptionPane` para mensajes y alertas.

---

## Manejo de excepciones

El sistema debe validar entradas para evitar fallos comunes.

### Ejemplos de validación

- Nombre vacío.
- Correo inválido.
- Fecha mal escrita.
- Destino no seleccionado.
- Campos obligatorios sin completar.

### Ejemplo de errores a controlar

- `IllegalArgumentException`
- `NullPointerException`
- Validaciones personalizadas

---

## Estructura de carpetas sugerida

```md
src/
├── modelo/
│   ├── Usuario.java
│   ├── DestinoEspacial.java
│   ├── Reserva.java
│   └── Viaje.java
├── sistema/
│   └── SistemaReservas.java
├── vista/
│   ├── VentanaPrincipal.java
│   ├── FormularioRegistro.java
│   ├── FormularioReserva.java
│   └── PanelDestinos.java
└── Main.java
```

---

## Cómo ejecutar el proyecto

### Compilación

Desde la carpeta raíz del proyecto, ejecuta:

```bash
javac -d bin src/modelo/*.java src/sistema/*.java src/vistas/*.java src/App.java
```

Esto compilará todos los archivos `.java` y generará los `.class` en la carpeta `bin`.

### Ejecución

Una vez compilado, ejecuta:

```bash
java -cp bin App
```

La interfaz gráfica se abrirá automáticamente.

### Flujo de uso

1. **Registrar usuario**: Click en el botón "Registrar Usuario"
2. **Ver destinos**: Click en "Ver Destinos" para explorar opciones disponibles
3. **Crear reserva**: Click en "Crear Reserva" y selecciona un usuario, destino y fecha
4. **Ver reservas**: Click en "Ver Mis Reservas" para consultar todas las reservas realizadas

---

## Manual de uso

### 1. Registro

El usuario debe ingresar su nombre, correo y preferencias de viaje.

### 2. Selección de destino

El sistema mostrará los destinos disponibles y sus características.

### 3. Reservar viaje

El usuario debe elegir destino, fecha y observaciones adicionales.

### 4. Ver información

El sistema mostrará el itinerario, el equipo necesario y recomendaciones.

### 5. Salir

El usuario puede cerrar el sistema desde la ventana principal.

---

## Capturas o demostración

Se recomienda incluir en este apartado:

- Captura de la ventana principal.
- Captura del formulario de registro.
- Captura del panel de destinos.
- Captura de la confirmación de reserva.
- Video corto demostrando el flujo completo.

---
