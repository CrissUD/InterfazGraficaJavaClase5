# Interfaz Gráfica en Java

Curso propuesto por el grupo de trabajo Semana de Ingenio y Diseño (**SID**) de la Universidad Distrital Francisco Jose de Caldas.

## Monitor

**Cristian Felipe Patiño Cáceres** - Estudiante de Ingeniería de Sistemas de la Universidad Distrital Francisco Jose de Caldas

# Clase 5

## Objetivos

* Comprender el termino general de componente gráfico, como identificarlos para su construcción en una interfaz gráfica y de que partes esta compuesto dicho componente.
* Aprender el uso de eventos por acción para generar interactividad a las interfaces gráficas de forma general.
* Generar una discriminación de eventos para una especificación de acciones y explorar las distintas formas de hacerse.
* Explicar y realizar preparativos previos para la implementación de una aplicación **Single-Page App**.

# Antes de comenzar

En la sesión anterior se realizó la incorporación de nuevos servicios encargados proveer una lógica que ayudan a que el código este optimizado y exista un control con la creación de objetos compartidos. 

### **Ajustes en la clase VistaPrincipal**

En la clase **VistaPrincipal** ya se esta haciendo uso del servicio **RecursosService**, ahora se va a obtener el servicio **ObjGraficosService** ya que se va a utilizar en el futuro:

**Declaración:**
```javascript
private ObjGraficosService sObjGraficos;
```

**Obtención de Servicio:**
```javascript
sObjGraficos = ObjGraficosService.getService(); // dentro del constructor
```

### **Ajustes en el servicio Recursos Service**

Se va a crear un nuevo color en el servicio **RecursosService** este será el colorMorado y se usará en varios componentes gráficos en el futuro.

**Declaración**
```javascript
// Al inicio de la clase
private Color colorMorado;
```
**Ejemplificación**
```javascript
// Dentro del método crearColores
colorMorado = new Color(151, 0, 158);
```
**Método get**
```javascript
public Color getColorMorado(){ return colorMorado; }
```
### **Estructura de la aplicación**

La estructura de la aplicación puede observarse mediante un diagrama de clases y este hasta el momento se representa así:

<div align="center">
  <img  src="https://i.imgur.com/LFI29Pt.png">
  <p>Modelo Estructural de clases aproximado del proyecto hasta el momento</p>
</div>

El anterior modelo estructural muestra de forma general y aproximada como esta conformado el proyecto, ademas de resaltar que paquetes y dependencias existen hasta el momento.

### **Recordatorio**

Recordando un poco el recorrido se creo la clase **LoginTemplate** donde su código ya se modularizó y optimizó. La interfaz gráfica se ve asi:

<div align="center">
  <img  src="https://i.imgur.com/LoVLul5.png">
  <p>Login de usuario del proyecto</p>
</div>

También se tiene la clase **VistaPrincipalTemplate** que esta vacía y tiene un color de fondo así:

<div align="center">
  <img  src="https://i.imgur.com/LKlaTvJ.png">
  <p>Vista Principal del proyecto</p>
</div>

# Componentes Gráficos y Eventos por Acción 

En esta sesión se van a abarcar 3 Temas principales cada uno con sus respectivos items, relacionados con componentes gráficos, eventos y preparación de una Single Page App:

## Temas
* **Componentes Gráficos**.
  * **Concepto general de Componente Gráfico**.
  * **Creación de un componente Gráfico y sus partes**.
  * **Explicación Inyección de dependencias**.
* **Eventos: ActionListener (Evento por acción)**.
  * **Discriminación por Texto o Comando**.
  * **Discriminación por Objeto**.
  * **Obtención de información desde formularios**.
  * **Abriendo otras ventanas**.
* **Preparación para construcción de una Single-Page App**.

# Componentes Gráficos

## Concepto general de Componente Gráfico

Muchos proyectos normalmente necesitan bastantes interfaces gráficas para soportar todos los requerimientos, funcionalidades y datos que un cliente necesita en la aplicación. Se podría crear una  ventana separada por cada vista que se quiere mostrar al cliente, sin embargo, las aplicaciones actuales se caracterizan por manejar toda su información, acciones y vistas a traves de una sola ventana. Esto se conoce como una aplicación **Single-Page App**.

<div align="center">
  <img  src="https://i.imgur.com/nke8SN0.png">
  <p>Spotify ejemplo de una aplicación Single-Page App</p>
</div>

Un ejemplo es la aplicación de escritorio de **Spotify**, puede notar que cuando se usa este programa en el computador este no esta abriendo una ventana nueva por cada botón que se oprime. La aplicación se encarga de gestionar qué elementos mostrar en la misma ventana y qué elementos ocultar dependiendo de la petición del usuario.

Si todo el código de la aplicación estuviera contenido en un solo archivo que representa la única ventana sería realmente desastroso, habría una cantidad inmensa de objetos gráficos en una sola clase, una cantidad de lineas de código incalculable y el entendimiento y  mantenibilidad del código serían muy difícil. 

Una buena forma de tener un código organizado, con una responsabilidad clara de cada parte del proyecto y una modularidad coherente es mediante la implementación de **Componentes gráficos**. De esta forma las aplicaciones estarán separadas adecuadamente y manteniendo una coherencia repartida que será mucho mas fácil de entender. Por ejemplo una posible separación por componentes para la aplicación de Spotify seria la siguiente: 

<div align="center">
  <img  src="https://i.imgur.com/lNlkwny.png">
  <p>Spotify posible componetización de la aplicación</p>
</div>

De la anterior imágen se puede observar varias particularidades:
* Un Componente gráfico puede contener a otros componentes, por ejemplo el **Componente Panel Principal** contiene varios componentes mas.
* Un Componente puede cambiar de contenido, por ejemplo si el usuario escoge una lista o si oprime cualquier botón es muy probable que el **Componente Panel Principal** remueva su contenido y llame a otros componentes para satisfacer lo que quiera ver el usuario.
* Un componente puede ser **reutilizable**, note por ejemplo que el **componente Recomendaciones** se esta llamando varias veces y no se necesita crear distintas clases para mostrarlo en pantalla las veces que sea necesario, simplemente se puede reutilizar. Incluso si en otra parte de la aplicación se requiera este componente podría fácilmente ser llamado y usarse nuevamente.
* Cada componente cumple una función especifica lo que le da la cualidad a la aplicación de dividir responsabilidades y ademas es mucho mas fácil asi comprender el código de una sola parte en especifico que de toda la aplicación junta.

La componetización de una aplicación depende del programador, se podría ser demasiado riguroso y crear un componente gráfico por cada aspecto, o se podrían crear algunos grandes componentes gráficos que conformen toda la aplicación. 

A continuación se verá como esta conformado un **componente gráfico**.

## Creación de un componente Gráfico y sus partes.

Un componente gráfico en Java se conforma principalmente de 2 clases:
* **Clase Template:** Que se encarga unicamente de la muestra de objetos gráficos por pantalla ademas de la muestra de información que el usuario requiera y es el medio por el cual el usuario podrá interactuar, mostrar y recolectar información.
* **Clase Component:** Esta clase es la encargada de soportar la lógica del componente. Esta incluye el manejo de eventos, el manejo de la información a mostrar o recolectar en la clase Template, el llamado a servicios lógicos necesarios para la traer o entregar información externa etc.

Para crear el primer componente gráfico se crea la clase **LoginComponent** que estará ubicada dentro del paquete **login**:

<div align="center">
  <img  src="https://i.imgur.com/WSPIUBk.png">
  <p>Creación clase Component dentro del paquete vistaPrincipal</p>
</div>

A continuación se explican ciertas características de cada una de las clases que conforman un componente.

***Nota:** Aunque ya se ha trabajado con clases Template habrán nuevas características que serán necesarias e importantes.* 

### **Clase Template**

Esta clase se encargara unicamente de mostrar en pantalla los diferentes objetos gráficos con las que el usuario interactuará, ademas mostrará los valores obtenidos de la lógica realizada por la clase **Component**. 

Se caracteriza por:

- Hereda de una clase que le da propiedades gráficas y ademas tenga la capacidad de contener objetos gráficos, estas podría ser por ejemplo **JFrame**, **JPanel**, **Canvas** etc. Esta es la clase que va a ser mostrada al usuario.
<div align="center">
  <img  src="https://i.imgur.com/t57VxhN.png">
  <p>Ejemplo Herencia en clase Template</p>
</div>

Note que aunque en este ejemplo y hasta el momento se han creado clases **Template** que heredan de un **JFrame** también puede heredar de un **JPanel** por ejemplo.
- Importa las librerías necesarias para crear objetos gráficos y mostrarlos en pantalla.

- Recibe como parámetro en el constructor un objeto de la clase **Component** y la iguala a un objeto declarado de la misma referencia, esta técnica es llamada **inyección de dependencia** y se explica más adelante en esta lección.

<div align="center">
  <img  src="https://i.imgur.com/8yoICZU.png">
  <p>Inyección de la clase Component</p>
</div>
Algo que se debe aclarar es que una vez es recibido el objeto desde el constructor se realiza esa igualdad para que el objeto recibido sea conocido por toda la clase (atributo) de lo contrario el objeto solo existiría para el constructor y no para los otros métodos que puedan necesitar una comunicación con la lógica del componente. 

- Para que la clase **Component** pueda realizar acciones sobre los objetos gráficos, la clase **Template** deben contener métodos **get** de los objetos gráficos que serán necesarios manipular en la parte lógica. Por ejemplo para la clase **LoginTemplate** se generan los siguientes:

```javascript
public JButton getBCerrar(){ return this.bCerrar; }

public JButton getBEntrar(){ return this.bEntrar; }

public JButton getBRegistrarse(){ return this.bRegistrarse; }

public JButton getBOpcion1(){ return this.bOpcion1; }

public JTextField getTNombreUsuario(){ return this.tNombreUsuario; }

public JPasswordField getTClaveUsuario(){ return this.tClaveUsuario; }

public JComboBox<String> getCbTipoUsuario(){ return this.cbTipoUsuario; }

public JCheckBox getCheckSi(){ return this.checkSi; }

public JCheckBox getCheckNo(){ return this.checkNo; }
```

### **Clase Component**

Esta clase se encarga de manejar toda la lógica que la clase **Template** podría necesitar, esta puede incluir manejo de eventos (cuando se oprime un botón con el mouse, cuando se oprime una tecla, cuando se pasa por un objeto gráfico etc.) manejo de servicios para traer información externa, manejo y validación de información etc.

Tiene ciertas Características como:

- Puede implementar las interfaces (interfaz es diferente a interfaz Gráfica) que proporcionan la escucha de eventos en caso de ser necesario, un ejemplo de estas interfaces es **ActionListener** que se encarga de gestionar eventos de acciones como cuando el usuario oprime un botón. Puede implementar también otras interfaces que gestionan otro tipo de eventos como **MouseListener**, **MouseMotionListener**, **KeyListener** etc. Ademas se pueden implementar dos o mas interfaces desde una misma clase **Component**.
<div align="center">
  <img  src="https://i.imgur.com/NDQGSyP.png">
  <p>Ejemplo de implementación de una interfaz</p>
</div>

- Cuando se implemente cualquier interfaz esta pedirá que por obligación se implementen también los métodos de dicha interfaz. En este ejemplo la Interfaz **ActionListener** exige la implementación del método **actionPerformed**, por lo general el IDE o editor de código informará la advertencia y se podrá implementar de forma automática los métodos que se exigen.

<div align="center">
  <img  src="https://i.imgur.com/dCqjHZg.png">
  <p>Método implementado de la interfaz ActionListener</p>
</div>

***Nota:** El decorador **@Override** indica que una clase esta implementando y sobre-escribiendo un método que proporciona cierta interfaz, este decorador es clave ya que el compilador sabe a traves de este que el método fue implementado y dará ciertas capacidades.*

- Se tiene un objeto del tipo de la clase **template** y para ejemplificar este objeto se debe enviar como parámetro una referencia de si misma, para eso se utiliza la palabra **this**, que indica que se esta enviando como parámetro a la clase misma y asi completar la inyección de dependencia.

<div align="center">
  <img  src="https://i.imgur.com/9oreOuu.png">
  <p>Inyección desde la clase Component</p>
</div>

* La comunicación entre componentes se realiza exclusivamente mediante el llamado de las clase **Component** de los respectivos componentes, debido a esto, para que se pueda obtener la parte gráfica del componente es necesario crear un método **get** que retorne la clase **Template** y asi se pueda obtener desde otro componente:

<div align='center'>
    <img  src='https://i.imgur.com/fXMHXzq.png'>
    <p>Método get para que desde otro componente se pueda obtener la clase Template que contiene las características gráficas</p>
</div>

**IMPORTANTE!!** 

Como la clase **Template** ahora necesita un objeto en su constructor, dentro de la clase ejecutora **App** se va a llamar a la clase **Component** en su lugar.

<div align="center">
  <img  src="https://i.imgur.com/P45S7Ld.png">
  <p>Llamada a la clase LoginComponent desde App</p>
</div>

## Otras particularidades

Algunas particularidades adicionales que hay que explicar son:

* Ambas clases deben estar en un único paquete y deben tener una comunicación única bidireccional ya que la clase **Template** necesitará conocer partes de la clase **Component** y viceversa. 
* La clase **Template** es una clase aislada y solo es conocida directamente por su clase **Component** que la acompaña.
* Para la comunicación entre varios componentes se realiza siempre llamando a la clase **Component**.

<div align="center">
  <img  src="https://i.imgur.com/oT6Bc2l.png">
  <p>Ejemplo de comunicación entre componentes</p>
</div>

<div align="center">
  <img  src="https://i.imgur.com/vVNx41P.png">
  <p>Ejemplo de comunicación errónea entre componentes</p>
</div>

***Nota:** Aunque se explico previamente que la clase **Component** es la encargada de la comunicación con servicios, existe una excepción con los servicios creados anteriormente ( **ObjGraficosService** y **RecursosService** ) ya que están orientados a la construcción de las clases template y se conocen como **Servicios Gráficos**. Por otra parte los **Servicios Lógicos** están orientados al manejo de datos e información que puede ser obtenida o enviada a entidades externas y esos son los que podrán comunicarse con las clases **Component** de ser necesario. Sobre los servicios existirá una clase que se enfocará en explicar esto con más detalle.*

## Explicación Inyección de dependencias

La inyección de dependencias se realiza para que exista una comunicación permanente y bidireccional entre las dos clases que conforman un componente gráfico. Asi cuando la clase **Template** necesite algo de la lógica de la clase **Component** podrá hacerlo a traves de la referencia que obtiene desde el constructor y de igual manera cuando la clase **Component** quiera enviar o recibir información de la interfaz gráfica podrá hacerlo a traves del objeto que referencia a la clase **Template**.
A continuación se puede ver un esquema general de un componente Gráfico y en especial como se manifiesta la inyección de dependencias.

<div align="center">
  <img  src="https://i.imgur.com/b7rVRer.png">
  <p>Diagrama de clases de un componente Gráfico</p>
</div>


Visto esto en objetos en memoria se ve de la siguiente manera, y se puede denotar una comunicación directa entre los objetos de forma bidireccional:
<div align="center">
  <img  src="https://i.imgur.com/GmE0xlV.png">
  <p>Comunicación bidireccional entre objetos</p>
</div>

Una forma diferente (pero errónea) de hacer esto, sería la de la ejemplificación del objeto de la clase contraria de forma individual de la siguiente manera:

- En la clase **LoginComponent**:

<div align="center">
  <img  src="https://i.imgur.com/HlC1n6r.png">
  <p>Ejemplificación común desde la clase LoginComponent</p>
</div>

Se puede notar que ya no se envía el **this** por parámetro.

- En la clase **LoginTemplate**:

<div align="center">
  <img  src="https://i.imgur.com/ILVUggj.png">
  <p>Ejemplificación común desde la clase LoginTemplate</p>
</div>
Se puede notar que en este caso ya no recibe nada por parámetro y ejemplifica el objeto como se haría normalmente.

De esta forma se podría pensar que se realizo de manera correcta un canal de comunicación entre ambas clases, sin embargo, si se observa el modelo de objetos es posible darse cuenta que no es asi:

<div align="center">
  <img  src="https://i.imgur.com/7wJYvTw.png">
  <p>Comunicación unidireccional entre objetos</p>
</div>

Lo que en realidad sucede es que la clase **LoginComponent** crea un objeto de tipo **LoginTemplate** y la clase **LoginTemplate** crea a su vez **otro** objeto de tipo **LoginComponent** por lo que ahora en tiempo de ejecución hay dos objetos de la misma clase (LoginComponent) y no existe una sola comunicación directa entra ambos objetos.

***Nota:** El anterior proceso de creación de clase **Component**, implementación de inyección de dependencia se debe realizar también con **VistaPrincipal**.*

# ActionListener (Evento por acción)

Un evento ocurre cuando el usuario realiza alguna acción sobre la interfaz gráfica y desencadena un proceso que al final devolverá un resultado (traer información requerida, guardar información, mostrar una parte de la aplicación, cerrar la aplicación etc). 
Un **ActionListener** es el evento más básico pero a la vez mas importante, se puede realizar con una gran variedad de objetos gráficos aunque lo ideal es que se use con los botones de la interfaz gráfica.

La clase **LoginTemplate** contiene ciertos botones en la ventana, y la clase **LoginComponent** esta implementando la interfaz **ActionListener** y esta lista para recibir eventos. Para poder crear estas acciones se debe realizar una configuración adicional a los botones, esta será **addActionListener**.

```javascript
bEntrar.addActionListener(loginComponent);
```
Se puede observar que el método:
* **addActionListener:** Recibe por parámetro un objeto tipo ActionListener.

Como la clase **LoginComponent** implementa de esta interfaz automáticamente se convierte en un objeto de ese tipo, por lo que es posible enviar como argumento del método el objeto que referencia a esta clase.

**Nota:** Como **addActionListener**es un método de la etapa de **Configuración** deberá estar ubicado justo después de la llamada al servicio y antes de que se adicione el botón:

<div align="center">
  <img  src="https://i.imgur.com/PmI1kNw.png">
  <p>Se añade método de configuración addActionListener</p>
</div>

También se va a adicionar acciones al botón de Registrarse, al botón de cerrar y al botón opción 1:

```javascript
bCerrar.addActionListener(loginComponent);
```
```javascript
bRegistrarse.addActionListener(loginComponent);
```
```javascript
bOpcion1.addActionListener(loginComponent);
```

Ahora en la clase **LoginComponent** se puede probar la escucha de estos eventos, para eso se va a mostrar un mensaje en pantalla como primera prueba. Dentro del método implementado **actionPerformed** se escribe:

<div align="center">
  <img  src="https://i.imgur.com/Q1Gp6Fy.png">
  <p>Prueba 1 del evento</p>
</div>

Note que se está llamando un método:

* **JOptionPane.ShowMessageDialog():** Muestra un mensaje de alerta por pantalla a traves de una ventana emergente y recibe por parámetros:
  * **Componente (Ventana):** Recibe como parámetro la ventana relativa donde se quiere mostrar la ventana emergente, si se envía como null, el mensaje se vera en la mitad del monitor, si se enviá el objeto de una ventana, se vera en la mitad de la ventana enviada como parámetro.
  * **Mensaje:** Recibe un String y representa el mensaje que se quiere mostrar en pantalla.
  * **Titulo:** Recibe un String por parámetro y representa el titulo de la ventana emergente.
  * **Tipo de Mensaje:** Recibe un entero donde se le indica el tipo de mensaje representado con un icono, donde:
    * 0: Representa un mensaje de error. 
    * 1: Representa un mensaje Informativo. 
    * 2: Representa un mensaje de Advertencia. 
    * 3: Representa un mensaje de Pregunta.

Una vez se corre la aplicación es posible notar que al dar click sobre cualquiera de los 4 botones a los cuales se les adiciono el **ActionListener** saldrá un mensaje emergente.

<div align="center">
  <img  src="https://i.imgur.com/tgQV63y.png">
  <p>Mensaje emergente al oprimir el botón bEntrar</p>
</div>

Cabe recalcar que si se intenta dar click alguno de los dos botones excluidos (bOpcion2 o bOpcion3) no tendrá  ningún efecto debido a que no se agrego la propiedad de adicionar un **ActionListener**. 

Sin embargo, no se quiere que todos los botones realicen la misma acción, de hecho muchas veces es necesario que cada botón desencadene una acción diferente. Se debe realizar una discriminación de los botónes para separar acciones.

## Discriminación por Texto o Comando

La discriminación por texto o comando, realiza una separación de acciones dependiendo del texto que contiene cada botón, por ejemplo:

<div align="center">
  <img  src="https://i.imgur.com/YHdasHx.png">
  <p>Discriminación por medio de Texto o comando</p>
</div>

Se puede notar que para realizar esta discriminación se debe usar el objeto recibido por defecto **e** de tipo **ActionEvent** y llamar a su método:

* **getActionCommand**: Que devuelve un String del comando del botón oprimido (**El texto del botón**) y se compara con la coincidencia del texto deseada.

Si se corre la aplicación y se da click sobre el botón Cerrar, como este no contiene texto sino una imagen mostrara el mensaje **"Botón Cerrar"**, al dar click sobre el botón entrar mostrara el mensaje **"Botón Entrar"**, sin embargo si se da click sobre el botón bRegistrarse no tendrá ningún efecto asi se le haya añadido la propiedad **addActionListener** ya que no se ha configurado ninguna acción para ese botón.

Sin embargo este enfoque tiene una pequeña particularidad que podría ser una desventaja, si por ejemplo se da click sobre el botón (bOpcion1), saldrá el mismo mensaje que en el botón cerrar, esto debido a que ninguno de los dos tiene texto. 

<div align="center">
  <img  src="https://i.imgur.com/0DM1vZp.png">
  <p>Mismo mensaje de alerta en diferentes botones</p>
</div>

En las interfaces se van a tener varios botones que no contienen texto (Botones que solo contienen una imagen) ¿Como es posible discriminarlos unos con los otros?. 
Quizás se deba tomar otro enfoque.

## Discriminación por Objeto

La discriminación por objeto realiza una separación de acciones de acuerdo al objeto del botón que fue activado, por ejemplo:

<div align="center">
  <img  src="https://i.imgur.com/F49fu04.png">
  <p>Discriminación por medio de Objetos</p>
</div>

De igual forma se debe usar el objeto recibido por parámetro **e** de tipo **ActionEvent** y esta vez se llama al método:

* **getSource**: Que devuelve el objeto del botón el cual fue oprimido y se compara con el objeto deseado para separar la acción.
* Como se debe comparar con el objeto del botón al que se desea configurar su acción, es necesario obtenerlo a traves del objeto de la clase **Template** y su método **get** correspondiente.

Si se ejecuta la aplicación y se da click sobre el botón Cerrar se mostrará el mensaje **"Botón Cerrar"** y al dar click sobre el botón opcion1 mostrará el mensaje **"Botón opción"** por lo que se ha solucionado el problema, igualmente si se da click sobre el botón bRegistrarse no tendrá ningún efecto asi se le haya añadido la propiedad **addActionListener** ya que no se configurado ninguna acción para ese botón aun.

Ahora una buena practica es llamar desde el método **actionPerformed** a otros métodos que realicen los procesos en caso de ser muy extenso el código.

Por ejemplo: 
* El botón **bCerrar** Se encarga de cerrar la aplicación y el código no es para nada extenso asi que se puede mantener adentro del método **actionPerformed**.
* Los botones **bRegistrarse y bOpcion2** solo mostrarán por pantalla un mensaje asi que el código permanece adentro del método **actionPerformed**.
* El botón **bEntrar** va a obtener lo que escriba el usuario en tNombreUsuario, tClaveUsuario, cmbTipoUsuario y en los CheckBox y luego va a ingresar a la vista principal por lo que es preferible escribir un método externo que realice esta acción.

**Agregando mensajes en los botones bRegistrarse y bOpcion2**

```javascript
if(e.getSource()== loginTemplate.getBOpcion1()){
    JOptionPane.showMessageDialog(null, "Boton Opcion", "Advertencia", 1);
}
if(e.getSource()== loginTemplate.getBRegistrarse()){
    JOptionPane.showMessageDialog(null, "Boton Registro", "Advertencia", 1);
}
```

**Cerrando la aplicación a traves del botón bCerrar**
```javascript
if(e.getSource()== loginTemplate.getBCerrar()){
    System.exit(0);
}
```
Se puede observar en el código anterior que la forma para cerrar la aplicación es usando el código **System.exit(0)**.

**Agregando funcionalidad de muestra de datos y entrada a vista principal desde bEntrar**
```javascript
if(e.getSource()== loginTemplate.getBEntrar()){
    this.mostrarDatosUsuario();
    this.entrar();
}
```


Es necesario entonces crear los métodos **mostrarDatosUsuario** y **entrar** en la clase, estos métodos pueden ir al final de la clase, justo por debajo del método **ActionPerformed**:
```javascript
public void mostrarDatosUsuario(){
}

public void entrar(){
}
```

## Obtención de información desde formularios

### **Obtención de información desde un JTextField**

Para obtener el texto desde un JTextField es necesario obtener primero el objeto gráfico, esto se realizara a traves del objeto de la **clase Template** seguido del método **get** correspondiente de la siguiente manera:

```javascript
public void mostrarDatosUsuario(){
    loginTemplate.getTNombreUsuario();
}
```
Una vez obtenido el objeto es posible obtener el valor de lo escrito por el usuario mediante el método:
* **getText:** que retorna el texto escrito por el usuario en forma de String.

```javascript
public void mostrarDatosUsuario(){
    loginTemplate.getTNombreUsuario().getText();
}
```

Sin embargo para usar el texto obtenido se debe igualar a una variable, por lo que se crea una variable de tipo String llamada **nombreUsuario** y se iguala a la sentencia:
```javascript
public void mostrarDatosUsuario(){
    String nombreUsuario = loginTemplate.getTNombreUsuario().getText();
}
```
### **Obtención de información desde un JPasswordField**

El anterior proceso se podría realizar de la misma manera con el JPasswordField:

```javascript
// Dentro del método mostrarDatosUsuario
String claveUsuario = loginTemplate.getTClaveUsuario().getText();
```

El anterior código funciona, sin embargo, es posible que el editor de texto lo marque como tachado o saque una advertencia, esto es debido a que en versiones posteriores de Java este código dejará de funcionar, o es una forma insegura de hacerse.

Una alternativa para los objetos gráficos tipo JPasswordField es la siguiente:
```javascript
// Dentro del método mostrarDatosUsuario
String claveUsuario = new String(loginTemplate.getTClaveUsuario().getPassword());
```

Se puede notar varias cosas:
* Se usa el método **getPassword** que devuelve un arreglo de tipo **char** es decir un arreglo donde cada posición es un carácter escrito.
* Como se desea manejar la clave en forma de String es necesario crear un String en forma de objeto (java permite esto) y es necesario ejemplificarlo haciendo **new String()** donde en su constructor obtendrá el arreglo de char y así lo convertirá en un String común.

### **Obtención de información desde un JComboBox**

Para obtener información desde un **JComboBox** es necesario llamar al método:
* **getSelectedItem:** que retorna un objeto de la selección realizada por el usuario. 

Como se quiere obtener el resultado en forma de String se debe realizar un **Cast** de datos a String y de igual modo se iguala con una variable String creada, de la siguiente manera:
```javascript
// Dentro del método mostrarDatosUsuario
String tipoUsuario = ((String) loginTemplate.getCbTipoUsuario().getSelectedItem());
```

### **Obtención de información desde un JCheckBox**

Para saber cual de las dos opciones de los JCheckBox fue escogida primero se crea una variable String que estará vacía:
```javascript
// Dentro del método mostrarDatosUsuario
String check = "";
```
Seguido de eso se va a preguntar si alguno de los dos botones fue seleccionado mediante el método:
* **isSelected:** Retorna un booleano que estará en true si se seleccionó o false en caso de que no.

En caso de ser seleccionado se dará un nuevo valor a la variable **Check** ("Si" en caso de seleccionar el **checkSi** o "No" en caso de seleccionar el **checkNo**).

```javascript
// Dentro del método mostrarDatosUsuario
if(loginTemplate.getCheckSi().isSelected())
    check = "si";
if(loginTemplate.getCheckNo().isSelected())
    check = "no";
```

Ahora se muestra a modo de prueba un mensaje mediante un JOptionPane de la siguiente manera: 

```javascript
// Dentro del método mostrarDatosUsuario
JOptionPane.showMessageDialog(
  null, 
  "Nombre Usuario: " + nombreUsuario +
  "\n Clave Usuario: " + claveUsuario + 
  "\nTipo Usuario: " + tipoUsuario + 
  "\n¿Notificaciones?: " + check, 
  "Advertencia", 
  1
);
```
Pueden notar que la forma de encadenar un texto es mediante el uso de **+** y también recalcar que con **\n** se realiza un salto de linea.

El método esta listo para usarse y se ve asi:

<div align="center">
  <img  src="https://i.imgur.com/kelFPdG.png">
  <p>Método para obtener los datos del usuario</p>
</div>

Si se abre la aplicación y se da click sobre el botón entrar se podrá ver un resultado como el siguiente:

<div align="center">
  <img  src="https://i.imgur.com/PWw3wxt.png">
  <p>Ejemplo de obtención de datos a través de evento de botón</p>
</div>

De esta forma se se comprueba que los métodos para la obtención de información de los objetos gráficos funciona perfectamente.


## Abriendo otras ventanas

Suponiendo que ya se ha creado previamente todo el componente de vista principal (Clase Template y Clase Component con su Inyección de dependencia)

<div align="center">
  <img  src="https://i.imgur.com/FJ702j6.png">
  <p>Creación previa del componente VistaPrincipal</p>
</div>

Ahora en la clase **LoginComponent** se va a declarar un objeto de tipo **VistaPrincipalComponent**

<div align="center">
  <img  src="https://i.imgur.com/UmrMwl3.png">
  <p>Declaración objeto de tipo VistaPrincipalComponent desde LoginComponent</p>
</div>

Aquí se evidencia que la comunicación entre componentes se realizará desde las clases **Component**.

Ahora dentro del método **entrar** se escribe la lógica para entrar a la vista principal:
```javascript
this.vistaPrincipal = new VistaPrincipalComponent();
loginTemplate.setVisible(false);
```
En el anterior código suceden 2 cosas:
* Se ejemplifica el objeto de la clase **VistaPrincipalComponent**, este internamente ejemplifica al objeto de la ventana haciendo que esta se muestre en pantalla.
* Se le indica al objeto de la clase **LoginTemplate** que ya no sea visible mediante el método **setVisible**.

Ahora cuando se oprime el botón entrar primero mostrará el mensaje con los datos proporcionados por el usuario y después abrirá la vista principal ademas que cerrara el login.

# Preparación para construcción de una Single-Page App

Ya es posible abrir desde la aplicación la vista principal, en realidad esta será la ventana que tendrá la característica de ser una **Single-Page App**. 
Se podría haber creado una aplicación con una única ventana para ser mas fiel al concepto y dentro de esta contener el componente de login, sin embargo, en la propuesta del curso se realizo varias ventanas para ver también la gestión de diferentes ventanas en una aplicación. Otra posible ventana extra podría ser la del registro de un usuario, sin embargo, debido a la finalidad del curso esta no será construida.

Como la ventana principal será la ventana que contendrá todos los componentes realizados posteriormente, los componentes de ahora en adelante se van a crear dentro de un paquete llamado **components**, esto para diferenciar los componentes principales (Las ventanas) de los componentes secundarios (los paneles). 

<div align="center">
  <img  src="https://i.imgur.com/e012veH.png">
  <p>Creación de paquete components que contendrá los componentes secundarios dentro de la ventana Single-Page App</p>
</div>

Efectivamente como se acabo de explicar, los componentes que se van a crear de ahora en adelante se caracterizan por que su clase **Template** ahora hereda de un **JPanel**.

## Preparación de la Ventana Principal

Dentro de la Vista principal se van a crear varios paneles, estos paneles cumplen la función de maquetación de la aplicación y posteriormente van a ser reemplazados por componentes que se crearan en clases siguientes.

Primero se debe quitar el color de fondo de la ventana, también se ocultará la barra por defecto de Java y se ajustará su tamaño:
```javascript
setUndecorated(true);
setSize(1100, 650);
```

Se declaran 3 paneles y se crean con ayuda del servicio **objGraficosService**:

**Declaración de paneles**
```javascript
private JPanel pNavegacion, pBarra, pPrincipal;
```

**Creación de método crearJPanels**
```javascript
public void crearJPanels(){
}
```

**Construcción y adición de paneles**
```javascript
// Dentro del método crearJPanels
pNavegacion = sObjGraficos.construirJPanel(0, 0, 250, 700,sRecursos.getColorMorado(), null);
this.add(pNavegacion);

pBarra = sObjGraficos.construirJPanel(250, 0, 850, 50,sRecursos.getColorAzul(), null);
this.add(pBarra);

pPrincipal = sObjGraficos.construirJPanel(250, 50, 850, 600, Color.WHITE, null);
this.add(pPrincipal);
```

**Adición de método en el constructor**
```javascript
this.crearJPanels();
```

Por ultimo se debe agregar los métodos **get** correspondientes a los 3 paneles ya que la clase **Component** de seguro va a necesitar manipularlos en el futuro.

```javascript
public JPanel getPNavegacion() { return this.pNavegacion; }

public JPanel getPPrincipal() { return this.pPrincipal; }

public JPanel getPBarra() { return this.pBarra; }
```

Por ahora la ventana principal se verá así:

<div align="center">
  <img  src="https://i.imgur.com/7vWBw88.png">
  <p>Clase Vista Principal</p>
</div>

La vista Principal esta lista para empezar a ser construida a traves de componentes, los colores se utilizan solo para comprobar que los paneles se crearon correctamente pero serán reemplazados en futuras sesiones.

# Resultados

Si llegaste hasta aquí **¡Felicitaciones!** se ha aprendido qué son los componentes gráficos, se vio una introducción de su concepto y se exploro cada una de sus partes. Se aprendido a utilizar eventos de **ActionListener**, cómo realizar la discriminación de acciones por botones, cómo obtener información de formularios además de cómo mostrar y ocultar ventanas. Tenemos lista la clase **VistaPrincipal** que será construida a través de **Componentes Gráficos**.


Si se explora la estructura del proyecto de nuevo y con la implementación de componentes gráficos se verá de la siguiente manera:

<div align="center">
  <img  src="https://i.imgur.com/33uQ7af.png">
  <p>Diagrama de clases general de la aplicación</p>
</div>


En la siguiente sesión se verá cómo hacer la construcción de la vista principal a través de componentes gráficos y cómo gestionar la vista de componentes mediante el routing o navegación.

# Actividad

Desarrollar los componentes gráficos del login y la vista principal en los proyectos y realizar la gestión de eventos con los botones para que cierre la aplicación y abra la vista principal.
