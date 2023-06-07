# PizzaBuy
<img src="https://github.com/hall9zeha/PizzaBuy/blob/main/app/src/debug/ic_launcher-playstore.png" align="left"
width="20%" hspace="10" vspace="10">
Aplicación para simular pedidos de pizzas, escrita en Kotlin utilizando el patrón de arquitectura MVVM.

</br>
</br>
</br>
</br>
</br>
</br>
</br>

# Objetivo
Aprender el manejo de [Room database](https://developer.android.com/jetpack/androidx/releases/room?gclid=EAIaIQobChMIh-Hoi7C_-gIVRxXUAR2kZAAsEAAYASAAEgJnivD_BwE&gclsrc=aw.ds) con relaciones  de tablas más complejas (en este caso una estructura de arbol, Maestro-Detalle-SubDetalle).

# Se utilizó :gear:

* [Room database](https://developer.android.com/jetpack/androidx/releases/room?gclid=EAIaIQobChMIh-Hoi7C_-gIVRxXUAR2kZAAsEAAYASAAEgJnivD_BwE&gclsrc=aw.ds)
* [Lyfecycle view model](https://developer.android.com/jetpack/androidx/releases/lifecycle)
* [Glide](https://developer.android.com/training/dependency-injection/hilt-android) 
* [Material components and Material Design](https://material.io/components)
* [Dagger Hilt](https://developer.android.com/training/dependency-injection/hilt-android)
* [Navigation component](https://developer.android.com/guide/navigation/navigation-getting-started)

# Demo :file_folder:
* Descarga la apk [aquí](https://github.com/hall9zeha/PizzaBuy/raw/main/demo/app-debug.apk)

## A nivel general :memo:

* Al abrir la aplicación se nos presentará el listado de pizzas del restaurante y el usuario podrá escoger una para iniciar el pedido.
* El usuario podrá añadir tantas pizzas como quiera al carrito
* A través del listado de pizzas, el usuario verá una página detalle por pizza para ver sus principales detalles, imagen y descripción.
* Una pizza puede estar compuesta por hasta 4 tipos de ingredientes.


## Pantalla listado de pizzas:

* Muestra todas las pizzas disponibles, con su nombre, imagen y precio
* Los usuarios puedes añadir cualquiera de ellas al carrito
* Los usuarios pueden acceder a la página detalle haciendo click en cada item.
* Los usuarios puede visualizar su carrito para comprobar qué pizzas han añadido al pedido.

### Screenshots :framed_picture:
<img src="https://github.com/hall9zeha/PizzaBuy/blob/main/screenshots/Screenshot_1.jpg" alt="drawing" width="35%" height="35%"/> <img src="https://github.com/hall9zeha/PizzaBuy/blob/main/screenshots/Screenshot_2.jpg" alt="drawing" width="35%" height="35%"/>

## Pantalla de detalle:

* La app mostrará la imagen, el nombre, el precio e ingredientes + descripción de la pizza.
* El usuario podrá añadir la pizza al carrito
* Desde esta pantalla, el usuario podrá eliminar hasta 2 toppings/ingredientes
* El usuario puede crear una pizza de cuatro trozos. Por lo que la app mostrará el proceso de selección 4 veces, permitiendo eliminar dos toppings/ingredientes por cada cuarto de pizza. 

### Screenshots :framed_picture:
<img src="https://github.com/hall9zeha/PizzaBuy/blob/main/screenshots/Screenshot_3.jpg" alt="drawing" width="35%" height="35%"/> <img src="https://github.com/hall9zeha/PizzaBuy/blob/main/screenshots/Screenshot_4.jpg" alt="drawing" width="35%" height="35%"/>
<img src="https://github.com/hall9zeha/PizzaBuy/blob/main/screenshots/Screenshot_5.jpg" alt="drawing" width="35%" height="35%"/>

## Pantalla carrito:

* Muestra las pizzas seleccionadas.
* Si el usuario ha escogido una pizza por mitades, debe especificar qué contiene cada mitad.
* Si el usuario ha eliminado algunos ingredientes de la pizza, debe mostrarse.
* El usuario tiene algunos créditos cargados en la app, por lo que se mostrarán aquí. Si el usuario no tiene suficientes créditos, no podrá acabar el proceso de compra.
* En el caso de que tenga suficientes créditos, la app permite al usuario realizar el pedido!

<img src="https://github.com/hall9zeha/PizzaBuy/blob/main/screenshots/Screenshot_6.jpg" alt="drawing" width="35%" height="35%"/> <img src="https://github.com/hall9zeha/PizzaBuy/blob/main/screenshots/Screenshot_7.jpg" alt="drawing" width="35%" height="35%"/>

Pero... ¿qué ocurre si no tienes suficientes créditos en la app?

* Por cada 5 clicks seguidos que el usuario realiza sobre el importe total del carrito, el sistema añadirá $10 a sus créditos.

## Pantalla buscar
<img src="https://github.com/hall9zeha/PizzaBuy/blob/main/screenshots/Screenshot_8.jpg" alt="drawing" width="35%" height="35%"/>

