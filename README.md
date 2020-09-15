# Interfaz Gráfica en Java

Curso propuesto por el grupo de trabajo Semana de Ingenio y Diseño (**SID**) de la Universidad Distrital Francisco Jose de Caldas.

## Monitor

**Cristian Felipe Patiño Cáceres** - Estudiante de Ingeniería de Sistemas de la Universidad Distrital Francisco Jose de Caldas

# Clase 5

## Objetivos

* Comprender el termino general de componente gráfico, como identificarlos para su construcción en una interfaz gráfica y de que partes esta compuesto dicho componente.
* Aprender el uso de eventos por acción para darle interactividad a nuestras interfaces gráficas de forma general y discriminando por acciones.
* Explicar y realizar preparativos previos para la implementación de nuestra aplicación Single-Page App.

# Antes de comenzar

En la clase anterior realizamos la incorporación de nuevos servicios encargados de cosas especificas que ayudan a que nuestro código quede optimizado y con un control en la creación de objetos compartidos. 

En nuestra clase **VistaPrincipal** ya hemos usado el servicio **RecursosService** ahora vamos a obtener el servicio **ObjGraficosService**

**Declaración**
```javascript
private ObjGraficosService sObjGraficos;
```

**Obtención de su ejemplificación**
```javascript
sObjGraficos = ObjGraficosService.getService(); // dentro del constructor
```

Si miramos nuestra aplicación hasta el momento mediante un diagrama de clases puede verse así:

<div align="center">
  <img  src="https://i.imgur.com/LFI29Pt.png">
  <p>Modelo Estructural de clases aproximado de nuestro proyecto hasta el momento</p>
</div>

El anterior modelo estructural muestra de forma general y aproximada como esta conformado nuestro proyecto, que paquetes tenemos y que dependencias hasta el momento existen.

Recordando un poco nuestro recorrido tenemos una clase **LoginTemplate** donde su código ya se modularizó y optimizó. La interfaz gráfica se ve asi:

<div align="center">
  <img  src="https://i.imgur.com/LoVLul5.png">
  <p>Login de usuario de nuestro proyecto</p>
</div>

También tenemos una clase **VistaPrincipalTemplate** que hasta el momento esta vacía y tiene un color de fondo así:

<div align="center">
  <img  src="https://i.imgur.com/LKlaTvJ.png">
  <p>Vista Principal de nuestro proyecto</p>
</div>

# Componentes Gráficos y Eventos por Acción 

En esta clase vamos a abarcar 2 Temas principales y ver 3 items importantes que están corelacionados entre ambos temas:

## Temas
* **Componentes Gráficos**
* **Eventos: ActionListener (Evento por acción)**

### Items
* Concepto general de Componente Gráfico.
* ActionListener (Evento por acción)
* Explicación previa y preparación para construcción de una Single-Page App.

# Concepto general de Componente Gráfico

Muchos proyectos normalmente necesitan bastantes interfaces gráficas para soportar todos los requerimientos y todos los datos que un cliente necesita en la aplicación. Se podría crear una  ventana separada por cada vista que se quiere mostrar al cliente, sin embargo las aplicaciones hoy en dia se caracterizan por manejar toda su información, acciones y vistas a traves de una sola ventana. Esto se conoce como una aplicación **Single-Page App**.

<div align="center">
  <img  src="https://i.imgur.com/nke8SN0.png">
  <p>Spotify ejemplo de una aplicación Single-Page App</p>
</div>

Un ejemplo es la aplicación de escritorio de **Spotify**, pueden notar que cuando se usa este programa en nuestro computador este no esta abriendo una ventana nueva por cada botón que nosotros oprimimos. La aplicación se encarga de gestionar qué elementos mostrar en la misma ventana y qué elementos quitar dependiendo de la petición del usuario.

Si todo el código de la aplicación estuviera contenido en una sola ventana sería realmente desastroso, habría una infinidad de objetos gráficos en una sola clase, una cantidad de lineas de código inmensa y el entendimiento y la mantenibilidad del código seria realmente difícil. 

Una buena forma de tener un código organizado, con una responsabilidad clara de cada parte del proyecto y una modularidad coherente es la implementación de **Componentes gráficos**. De esta forma nuestras aplicaciones estarán separadas adecuadamente. Por ejemplo una posible separación por componentes para la aplicación de Spotify seria la siguiente: 

<div align="center">
  <img  src="https://i.imgur.com/lNlkwny.png">
  <p>Spotify posible componetización de la aplicación</p>
</div>

De la anterior imágen se puede observar varias particularidades:
* Un Componente gráfico puede contener a otro componente por ejemplo el **Componente Panel Principal** contiene varios componentes mas.
* Un Componente puede cambiar de contenido, por ejemplo si el usuario escoge una lista o si oprime cualquier botón es muy probable que el **Componente Panel Principal** remueva su contenido y llame a otros componentes para satisfacer lo que quiera ver el usuario.
* Un componente puede ser **reutilizable**, note por ejemplo que el **componente Recomendaciones** se esta llamando varias veces y no se necesita crear distintas clases para mostrarlo en pantalla las veces que sea necesario, simplemente se puede crear un arreglo de componentes de ese tipo para mostrarlo varias veces. Incluso si en otra parte de la aplicación se requiera este componente podría fácilmente ser llamado y usarse.
* Cada componente cumple una función especifica lo que le da la cualidad a la aplicación de dividir responsabilidades y ademas es mucho mas fácil asi comprender el código de una sola parte en especifico que de toda una aplicación.

La componetización de una aplicación depende del programador, se podría ser demasiado riguroso y crear un componente gráfico por cada aspecto, o se podrían crear algunos componentes gráficos que conformen toda la aplicación. 

A continuación veremos como esta conformado un **componente gráfico**.

## Creación de un componente Gráfico y sus partes.

Un componente gráfico se conforma principalmente de 2 clases:
* **Clase Template:** Que se encarga unicamente de la muestra de objetos gráficos por pantalla ademas de la muestra de información que el usuario requiera y es el medio por el cual el usuario podrá interactuar, mostrar y recolectar información.
* **Clase Component:** Esta clase es la encargada de soportar la lógica del componente. Esta incluye el manejo de eventos, el manejo de la información que se vaya a mostrar o recolectar en la clase Template, el llamado a servicios necesarios para la traer o entregar información externa etc.

Algunas particularidades que hay que explicar son:

* Ambas clases deben estar en un único paquete y deben tener una comunicación única bidireccional ya que la clase **Template** necesitara conocer partes de la clase **Component** y viceversa. 
* La clase **Template** es una clase aislada y solo es conocida unicamente por su clase **Component** que la acompaña, para la comunicación entre varios componentes se realiza siempre llamando a la clase **Component**.

<div align="center">
  <img  src="https://i.imgur.com/oT6Bc2l.png">
  <p>Ejemplo de comunicación entre componentes</p>
</div>

<div align="center">
  <img  src="https://i.imgur.com/vVNx41P.png">
  <p>Ejemplo de comunicación errónea entre componentes</p>
</div>

* Antes se dijo que la clase **Component** es la encargada de la comunicación con servicios sin embargo existe una excepción con los servicios creados anteriormente **ObjGraficosService** y **RecursosService** ya que están orientados a la construcción de las clases template. El resto de servicios estará orientado al manejo de datos y esos son los que gestionara la clase **Component** de ser necesario.

Para crear nuestro primer componente gráfico vamos a posicionarnos en el paquete **login** y crearemos una clase llamada **LoginComponent**.

<div align="center">
  <img  src="https://i.imgur.com/WSPIUBk.png">
  <p>Creación clase Component dentro del paquete vistaPrincipal</p>
</div>

A continuación se explicaran ciertas características de cada una de las clases que conforman un componente.

***Nota:** Aunque ya hemos trabajado con clases Template habrán nuevas características que serán necesarias e importantes.* 

## Clase Template

Esta clase se encargara unicamente de mostrar en pantalla los diferentes objetos gráficos con las que el usuario interactuará y mostrará los valores obtenidos de la lógica realizada por la clase **Component**. Se caracteriza por:

- Heredar de una clase que le da propiedades gráficas, esta podría ser por ejemplo **JFrame**, **JPanel**, **Canvas** etc. Esta es la clase que va a ser mostrada al usuario.
<div align="center">
  <img  src="https://i.imgur.com/t57VxhN.png">
  <p>Ejemplo Herencia en clase Template</p>
</div>

Note que aunque en este ejemplo y hasta el momento hemos creado clases **Template** que heredan de un **JFrame** también podrían heredar de un **JPanel** por ejemplo.
- Importará las librerías necesarias para crear objetos gráficos y mostrarlos en pantalla como hemos visto en clases anteriores.

- Recibe como parámetro en el constructor un objeto de la clase **Component** y la iguala a un objeto declarado de la misma referencia, esta técnica es llamada **inyección de dependencia** y sera explicada mas adelante en esta clase.

<div align="center">
  <img  src="https://i.imgur.com/8yoICZU.png">
  <p>Inyección de la clase Component</p>
</div>
Algo que se debe aclarar es que una vez es recibido el objeto desde el constructor se realiza esa igualdad para que el objeto recibido sea conocido por toda la clase (atributo) de lo contrario el objeto solo existiría para el constructor y no para los otros métodos que creemos en la misma clase. 

- Para que la clase **Component** pueda realizar acciones con los objetos gráficos de la misma, a la clase **Template** se le deben añadir métodos **get** para los objetos que serán necesarios manipular. Por ejemplo para la clase **LoginTemplate** se generan los siguientes:

```javascript
public JButton getBCerrar(){
    return this.bCerrar;
}

public JButton getBEntrar(){
    return this.bEntrar;
}

public JButton getBRegistrarse(){
    return this.bRegistrarse;
}

public JButton getBOpcion1(){
    return this.bOpcion1;
}

public JTextField getTNombreUsuario(){
    return this.tNombreUsuario;
}

public JPasswordField getTClaveUsuario(){
    return this.tClaveUsuario;
}

public JComboBox<String> getCbTipoUsuario(){
    return this.cbTipoUsuario;
}

public JCheckBox getCheckSi(){
    return this.checkSi;
}

public JCheckBox getCheckNo(){
    return this.checkNo;
}
```

## Clase Component

Esta clase se encarga de manejar toda la lógica que la clase **Template** podría necesitar, esta puede incluir manejo de eventos (cuando se oprime un botón, cuando se da click con el Mouse, cuando se oprime una tecla etc.) manejo de servicios, manejo de información etc.
También tiene ciertas Características como:

- Puede implementar las interfaces (interfaz es diferente a interfaz Gráfica) que proporcionan la escucha de eventos (no es obligatorio pero es muy común), por ejemplo un **ActionListener** que se activa cuando el usuario oprime un botón. De ser necesario implementa otras interfaces que gestionan otro tipo de eventos como **MouseListener**, **MouseMotionListener**, **KeyListener** etc.
<div align="center">
  <img  src="https://i.imgur.com/NDQGSyP.png">
  <p>Ejemplo de implementación de una interfaz</p>
</div>

- Cuando se implemente cualquier interfaz esta pedirá que por defecto se implementen también los métodos de esta en la clase, asi que debe realizarse. En este caso la Interfaz **ActionListener** exige la implementación del método **actionPerformed**, por lo general el editor informará del error y se podrá implementar de forma automática. Es en este método donde se gestionará una acción cada vez que el usuario de click a un botón.
<div align="center">
  <img  src="https://i.imgur.com/dCqjHZg.png">
  <p>Método implementado de la interfaz ActionListener</p>
</div>

- Se tiene un objeto del tipo de la clase **template** y para ejemplificar este objeto se debe enviar como parámetro una referencia a esta misma clase, para eso usamos la palabra **this**, que indica que se esta enviando como parametro a ella misma y asi completar la inyeccion de dependencia.

<div align="center">
  <img  src="https://i.imgur.com/9oreOuu.png">
  <p>Inyección desde la clase Component</p>
</div>

* Como la comunicación hacia un componente se realiza mediante al llamado de la clase **Component** para que se pueda obtener la vista del componente es necesario crear un método **get** que retorne la clase **Template** desde la clase **Component** y asi se pueda obtener desde otro componente esta:

<div align='center'>
    <img  src='https://i.imgur.com/fXMHXzq.png'>
    <p>Método get para que desde otro componente se pueda obtener la clase Template que contiene las características gráficas</p>
</div>

**Nota:** Como nuestra clase **Template** ahora necesita un objeto en su constructor, dentro de la clase ejecutora **App** se va a llamar a la clase **Component**.

<div align="center">
  <img  src="https://i.imgur.com/0FnbWjP.png">
  <p>Llamada a la clase LoginComponent desde App</p>
</div>
 
## Explicación Inyección de dependencia

Esta inyeccion se hace de esta forma para tener una comunicación permanente entre las dos clases de forma bidireccional. Asi cuando la clase **Template** necesite algo de la lógica de la clase **Component** podrá hacerlo a traves de su objeto y de igual manera cuando la clase **Component** quiera enviar o recibir información a la interfaz gráfica podrá hacerlo a traves del objeto de esta.
A continuación se puede ver un esquema general de un componente Gráfico.

<div align="center">
  <img  src="https://i.imgur.com/b7rVRer.png">
  <p>Diagrama de clases de un componente Gráfico</p>
</div>


Visto esto en objetos en memoria se ve de la siguiente manera, y se puede denotar una comunicación directa entre los objetos de forma bidireccional:
<div align="center">
  <img  src="https://i.imgur.com/GmE0xlV.png">
  <p>Comunicación bidireccional entre objetos</p>
</div>

Una forma diferente de hacer esto (pero errónea) seria la de la ejemplificación del objeto de la clase contraria de forma individual de la siguiente manera:

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


De esta forma se podría pensar que se realizo de manera correcta un canal de comunicación entre ambas clases, sin embargo si miramos el modelo de objetos podemos darnos cuenta que no es asi:

<div align="center">
  <img  src="https://i.imgur.com/7wJYvTw.png">
  <p>Comunicación unidireccional entre objetos</p>
</div>

Lo que pasa en realidad es que la clase **LoginComponent** crea un objeto de tipo **LoginTemplate** y la clase **LoginTemplate** crea a su vez **otro** objeto de tipo **LoginComponent** por lo que ahora en tiempo de ejecución hay dos objetos de la misma clase y no hay una sola comunicación directa entra ambos objetos.

***Nota:** El anterior proceso de creación de clase **Component**, implementación de inyeccion de dependencia y agregación de método **get** se debe realizar también con VistaPrincipal.*

# ActionListener (Evento por acción)

Un evento ocurre cuando el usuario realiza alguna acción sobre la interfaz gráfica y desencadena un proceso que al final devolverá un resultado (traer información requerida, guardar información, mostrar una parte de la aplicación, cerrar la aplicación etc). 
Un **ActionListener** es el evento mas básico pero a la vez mas importante, se puede realizar con una gran variedad de objetos gráficos aunque lo ideal es que se use con los botones de la interfaz gráfica.

Ya tenemos nuestra clase **LoginTemplate** que contiene todos los botones en la ventana, y tenemos la clase **LoginComponent** que ha implementado la interfaz **ActionListener** y esta lista para recibir eventos. Para poder crear estas acciones debemos darle una configuración adicional a nuestros JButton, esta sera  **addActionListener**.

```javascript
bEntrar.addActionListener(loginComponent);
```
se puede observar que el método:
* **addActionListener:** Recibe por parámetro un objeto tipo ActionListener.

Como nuestra clase **LoginComponent** implementa de esta interfaz automáticamente se convierte en un objeto de ese tipo por lo que podemos ingresar en el objeto que creamos de la clase como argumento en el método.

**Nota:** Como **addActionListener**es un método de Configuración deberá estar ubicado justo después de la llamada al servicio y antes de que se adicione el botón:

<div align="center">
  <img  src="https://i.imgur.com/PmI1kNw.png">
  <p>Se añade método de configuración addActionListener</p>
</div>

También queremos darle acciones al botón de Registrarse, al botón de cerrar y al botón opción 1 por lo que realizaremos la misma configuración.

```javascript
bCerrar.addActionListener(loginComponent);
```
```javascript
bRegistrarse.addActionListener(loginComponent);
```
```javascript
bOpcion1.addActionListener(loginComponent);
```

Ahora en nuestra clase **LoginComponent** vamos a probar si realmente funciona, para eso vamos a mostrar un mensaje en pantalla. Nos ubicamos en el método implementado **actionPerformed** y escribimos:

<div align="center">
  <img  src="https://i.imgur.com/Q1Gp6Fy.png">
  <p>Prueba 1 de nuestro evento</p>
</div>

Note que estamos llamando un método que no habíamos visto antes:

* **JOptionPane.ShowMessageDialog():** Muestra un mensaje de alerta por pantalla a traves de una ventana emergente y recibe por parámetros:
  * **Componente (Ventana):** Recibe como parámetro la ventana relativa donde se quiere mostrar la ventana emergente, si se envía como null, el mensaje se vera en la mitad del monitor, si no, se vera en la mitad de la ventana enviada.
  * **Mensaje:** Recibe un String y representa el mensaje que se quiere mostrar en pantalla.
  * **Titulo:** Recibe un String por parámetro y representa el titulo de la ventana emergente.
  * **Tipo de Mensaje:** Recibe un entero donde se le indica el tipo de mensaje representado con un icono, donde:
    * 0: Representa un mensaje de error. 
    * 1: Representa un mensaje Informativo. 
    * 2: Representa un mensaje de Advertencia. 
    * 3: Representa un mensaje de Pregunta.

Una vez corremos nuestra aplicación podemos notar que al darle click a cualquiera de los 4 botones a los cuales se les adiciono el **ActionListener** saldrá un mensaje emergente.

<div align="center">
  <img  src="https://i.imgur.com/tgQV63y.png">
  <p>Mensaje emergente al oprimir el botón bEntrar</p>
</div>

Podemos observar que si intentamos dar click alguno de los dos botones excluidos (bOpcion2 o bOpcion3) no tendrá  ningún efecto debido a que no se agrego la propiedad de adicionar un **ActionListener**. 

Sin embargo no queremos que todos los botones realicen la misma acción, de hecho necesitamos muchas veces que cada botón haga acciones diferentes. Debemos realizar una discriminación de los botónes para separar acciones.

### Discriminación por Texto

La discriminación por texto, realiza acciones dependiendo del texto que contiene cada botón, por ejemplo:

<div align="center">
  <img  src="https://i.imgur.com/YHdasHx.png">
  <p>Discriminación por medio de Texto</p>
</div>

Se puede notar que para realizar esta discriminación debemos usar el objeto recibido por defecto **e** de tipo **ActionEvent**  y llamar a su método:

* **getActionCommand**: Que devuelve un String del comando del botón oprimido **El texto del botón** y lo comparamos con la coincidencia del texto.

Si corremos la aplicación anterior y damos click en el botón Cerrar como este no contiene texto sino una imagen mostrara el mensaje **"Botón Cerrar"** y al darle click al botón entrar mostrara el mensaje **"Botón Entrar"**, sin embargo si damos click en el botón bRegistrarse no tendrá ningún efecto asi se le haya añadido la propiedad **addActionListener** ya que no hemos configurado ninguna acción para ese botón.

Sin embargo este enfoque tiene una pequeña particularidad que podría ser una desventaja, si por ejemplo damos click al botón (bOpcion1), saldrá el mismo mensaje que en el botón cerrar, esto debido a que ninguno de los dos tiene texto. 

<div align="center">
  <img  src="https://i.imgur.com/0DM1vZp.png">
  <p>Mismo mensaje de alerta en diferentes botones</p>
</div>

En nuestras interfaces vamos a tener varios botones que no contienen texto (Botones que solo contienen una imagen) ¿como vamos a discriminarlos unos con los otros?. 
Quizás debemos tomar otro enfoque.

### Discriminación por Objeto

La discriminación por objeto realiza acciones de acuerdo al objeto del botón que fue activado, por ejemplo:

<div align="center">
  <img  src="https://i.imgur.com/F49fu04.png">
  <p>Discriminación por medio de Objetos</p>
</div>

De igual forma debemos usar el objeto recibido por defecto **e** de tipo **ActionEvent**  y esta vez llamar a su método:

* **getSource**: Que devuelve un objeto del botón el cual fue oprimido y lo comparamos con el objeto al que queremos separar la acción.
* Como debemos compararlo con el objeto del botón al que le queremos configurar su acción, debemos obtenerlo a traves del objeto de la clase **Template** y su método **get** correspondiente.


Si corremos la aplicación anterior y damos click en el botón Cerrar se mostrara el mensaje **"Botón Cerrar"** y al darle click al botón opcion1 mostrara el mensaje **"Botón opción"** por lo que quedara solucionado el problema, igualmente si damos click en el botón bRegistrarse no tendrá ningún efecto asi se le haya añadido la propiedad **addActionListener** ya que no hemos configurado ninguna acción para ese botón.

Ahora una buena practica es llamar desde el método **actionPerformed** a otros métodos que realicen los procesos en caso de ser muy extenso el código.

Por ejemplo: 
* El botón **bCerrar** Se encargara de cerrar la aplicación y el código no es para nada extenso asi que se puede mantener adentro del método **actionPerformed**.
* Los botones **bRegistrarse y bOpcion2** solo mostraran por pantalla un mensaje asi que el codigo permanece adentro del método **actionPerformed**.
* El botón **bEntrar** en cambio va a obtener lo que escriba el usuario en tNombreUsuario, tClaveUsuario, cmbTipoUsuario y en los CheckBox y luego va a ingresar a la vista principal por lo que es preferible escribir un método externo que realice esta acción.

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
Se puede observar en el codigo anterior que la forma para cerrar la aplicación es usando un **System.exit(0)**.

**Agregando funcionalidad de muestra de datos y entrada a vista principal desde bEntrar**
```javascript
if(e.getSource()== loginTemplate.getBEntrar()){
    this.mostrarDatosUsuario();
    this.entrar();
}
```

Es necesario entonces crear nuestros métodos **mostrarDatosUsuario** y **entrar** en nuestra clase, estos métodos pueden ir al final de la clase, justo por debajo del método **ActionPerformed**:
```javascript
public void mostrarDatosUsuario(){

}

public void entrar(){

}
```

### **Obtención de datos desde formularios de interfaz gráfica**

Para poder obtener el texto de alguno de los objetos gráficos es necesario obtener primero el objeto, esto se realizara a traves del objeto de la **clase Template** seguido del método **get** correspondiente de la siguiente manera:

```javascript
public void mostrarDatosUsuario(){
    loginTemplate.getTNombreUsuario();
}
```
Una vez obtenido el objeto es posible obtener el valor de lo escrito o escogido por el usuario. Para el anterior objeto como es un JTextField la forma de obtener el texto es mediante el método:
* **getText:** que retorna el texto escrito por el usuario en forma de String.

```javascript
public void mostrarDatosUsuario(){
    loginTemplate.getTNombreUsuario().getText();
}
```

Sin embargo para obtenerlo se debe igualar a una variable por lo que creamos una variable llamada **nombreUsuario** de tipo String y la igualamos a la sentencia:
```javascript
public void mostrarDatosUsuario(){
    String nombreUsuario = loginTemplate.getTNombreUsuario().getText();
}
```
El anterior proceso se podría realizar de la misma manera con el JPasswordField:

```javascript
    String claveUsuario = loginTemplate.getTClaveUsuario().getText();
```

El anterior código funciona, sin embargo, es posible que el editor de texto lo marque como tachado o saque una advertencia, esto es debido a que en versiones posteriores de Java este código dejara de funcionar, entonces en el futuro cuando corras este código probablemente ya no funcione esta acción.

Una alternativa para los JPasswordField es la siguiente:
```javascript
    String claveUsuario = new String(loginTemplate.getTClaveUsuario().getPassword());
```

Se puede notar varias cosas:
* Se usa el método **getPassword** que devuelve un arreglo de tipo **char** es decir un arreglo donde cada posición es un carácter escrito.
* Como queremos manejar la clave en forma de String debemos manejar un String en forma de objeto (java permite esto) y es necesario ejemplificarlo haciendo **new String()** donde en su constructor obtendrá el arreglo de char y así lo convertirá en un String común.

Como por ahora funciona de ambas maneras se puede usar cualquiera de los dos métodos sin embargo por prevención es preferible usar el segundo enfoque.

**Nota:** El codigo anterior debe estar adentro del método **mostrarDatosUsuario**.

Para un **JComboBox** es necesario llamar al método:
* **getSelectedItem:** que retorna un objeto de la selección realizada por el usuario. 

Como nosotros queremos obtener el resultado en forma de String debemos realizar un **Cast** de datos a String y de igual modo se iguala con una variable String creada, de la siguiente manera:
```javascript
    String tipoUsuario = ((String) loginTemplate.getCbTipoUsuario().getSelectedItem());
```
**Nota:** El codigo anterior debe estar adentro del método **mostrarDatosUsuario**.

Para saber cual de las dos opciones de los JCheckBox fue escogida primero crearemos una variable String que estará vacía:
```javascript
String check= "";
```
Seguido de eso se va a preguntar si alguno de los dos botones fue seleccionado mediante el método:
* **isSelected:** que retorna un booleano que estará en true si se seleccionó o false en caso de que no.
En caso de ser seleccionado se dará un nuevo valor a la variable **Check** ("Si" en caso de seleccionar el **checkSi** o "No" en caso de seleccionar el **checkNo**).

```javascript
if(loginTemplate.getCheckSi().isSelected())
    check="si";
if(loginTemplate.getCheckNo().isSelected())
    check="no";
```

**Nota:** El codigo anterior debe estar adentro del método **mostrarDatosUsuario**.

Ahora mostraremos el mensaje mediante un JOptionPane de la siguiente manera: 

```javascript
JOptionPane.showMessageDialog(
    null, "Nombre Usuario: "+nombreUsuario+"\n Clave Usuario: "+claveUsuario+ 
    "\nTipo Usuario: "+tipoUsuario+"\n¿Notificaciones?: "+check, "Advertencia", 1
);
```
Pueden notar que la forma de encadenar un texto es mediante el uso de **+** y también recalcar que con **\n** se realiza un salto de linea.

**Nota:** El codigo anterior debe estar adentro del método **mostrarDatosUsuario**.

Nuestro método esta listo para usarse y se ve asi:

<div align="center">
  <img  src="https://i.imgur.com/a1LL0Ki.png">
  <p>Método para obtener los datos del usuario</p>
</div>

Si abrimos nuestra aplicación y damos click en el botón entrar se podrá ver un resultado como el siguiente:

<div align="center">
  <img  src="https://i.imgur.com/PWw3wxt.png">
  <p>Ejemplo de obtención de datos a través de evento de botón</p>
</div>

De esta forma hemos comprobado que nuestros métodos para la obtención del valor de nuestros objetos gráficos funciona perfectamente.


### **Abrir Vista Principal**

Suponiendo que ya se ha creado previamente todo el componente de vista principal (Clase Template y Clase Component con su Inyección de dependencia)

<div align="center">
  <img  src="https://i.imgur.com/FJ702j6.png">
  <p>Creación previa del componente VistaPrincipal</p>
</div>

Ahora en nuestra clase **LoginComponent** vamos a declarar un objeto de tipo **VistaPrincipalComponent**

<div align="center">
  <img  src="https://i.imgur.com/UmrMwl3.png">
  <p>Declaración objeto de tipo VistaPrincipalComponent desde LoginComponent</p>
</div>

Aquí se evidencia que la comunicación entre componentes se realizará desde las clases **Component**.

Ahora nos ubicaremos en nuestro método **entrar** y escribimos:
```javascript
this.vistaPrincipal = new VistaPrincipalComponent();
loginTemplate.setVisible(false);
```
Suceden 2 cosas:
* Se ejemplifica el objeto de la clase **VistaPrincipalComponent**
* Se le dice al objeto de la clase **LoginTemplate** que ya no sea visible.

Ahora cuando oprimimos el botón entrar notamos que primero mostrara el mensaje con los datos proporcionados por el usuario y después abrirá la vista principal y cerrara el login.

# Explicación previa y preparación para construcción de una Single-Page App

Ya podemos abrir desde nuestra aplicación nuestra vista principal, en realidad esta será la ventana que tendrá la característica de ser una **Single-Page App**. 
Se podría haber creado una aplicación con una única ventana y dentro de esta contendría el componente de login, sin embargo, en la propuesta del curso se realizo varias ventanas para ver multiples formas de hacerlo. Otra posible ventana extra podría ser la del registro de un usuario, pero hasta ahi seria el numero de ventanas separadas.

Como nuestra ventana principal será la ventana que contendrá todos los componentes realizados posteriormente ahora los componentes que crearemos de ahora en adelante los vamos a realizar dentro de un paquete llamado **components**, esto para diferenciar los componentes principales (Las ventanas) de los componentes secundarios (los paneles). 

<div align="center">
  <img  src="https://i.imgur.com/e012veH.png">
  <p>Creación de paquete components que contendrá los componentes secundarios dentro de nuestra ventana Single-Page App</p>
</div>

Efectivamente como se acabo de explicar, los componentes que vamos a crear de ahora en adelante se caracterizan por que su clase **Template** ahora hereda de un **JPanel**.

## Antes de continuar

Vamos a crear un nuevo color en el servicio **RecursosService** este sera el colorMorado y se usara en varios componentes gráficos en el futuro.

**Declaración**
```javascript
private Color colorMorado;
```
**Ejemplificación**
```javascript
colorMorado = new Color(151, 0, 158);
```
**Método get**
```javascript
public Color getColorMorado(){
    return colorMorado;
}
```

## Preparación de nuestra Ventana Principal

Vamos a crear varios paneles dentro de nuestra Vista principal, estos paneles posteriormente van a ser reemplazados por componentes que crearemos en clases siguientes.

primero vamos a quitar el color de fondo de la ventana, vamos a quitar la barra por defecto de Java y vamos a ajustar su tamaño:
```javascript
setUndecorated(true);
setSize(1100, 650);
```

Ahora declaramos 3 paneles y los crearemos con ayuda del servicio **objGraficosService**:

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
pNavegacion = sObjGraficos.construirJPanel(0, 0, 250, 700,sRecursos.getColorMorado(), null);
this.add(pNavegacion);

pBarra = sObjGraficos.construirJPanel(250, 0, 850, 50,sRecursos.getColorAzul(), null);
this.add(pBarra);

pPrincipal = sObjGraficos.construirJPanel(250, 50, 850, 600, Color.WHITE, null);
this.add(pPrincipal);
```

**Nota:** El anterior codigo va dentro del método **crearJPanels**.

**Adición de método en el constructor**

```javascript
this.crearJPanels();
```

Por ultimo debemos agregar los métodos **get** correspondientes a nuestros 3 paneles ya que la clase **Component** de seguro los necesitará en el futuro.

```javascript
public JPanel getPNavegacion() {
    return this.pNavegacion;
}

public JPanel getPPrincipal() {
    return this.pPrincipal;
}

public JPanel getPBarra() {
    return this.pBarra;
}
```

Por ahora nuestra ventana principal se vera así:

<div align="center">
  <img  src="https://i.imgur.com/7vWBw88.png">
  <p>Clase Vista Principal</p>
</div>

Ahora nuestra vista Principal esta lista para empezar a ser construida a traves de componentes, los colores se utilizan por ahora para comprobar que los paneles se crearon correctamente.

# Resultados

Si llegaste hasta aquí **¡Felicitaciones!** hemos aprendido qué son los componentes gráficos viendo una introducción de su concepto y aprendiendo cada una de sus partes. Hemos aprendido a utilizar eventos de **ActionListener** y cómo realizar la discriminación de acciones por botones. Tenemos lista nuestra clase **VistaPrincipal** que será construida a través de **componentes Gráficos**.
Si miramos nuestra aplicación a traves de un diagrama de clases esta se verá asi:

<div align="center">
  <img  src="https://i.imgur.com/33uQ7af.png">
  <p>Diagrama de clases general de la aplicación</p>
</div>


En la siguiente clase vamos a ver cómo hacer esta construcción a través de componentes gráficos y cómo gestionar la vista de componentes a medida que el usuario haga las peticiones.

# Actividad

Desarrollar los componentes gráficos del login y la vista principal en sus proyectos y realizar la gestión de eventos con los botones para que cierre la aplicación y abra la vista principal.
