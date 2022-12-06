package com.barryzea.pizzabuy.data.dataSource

import com.barryzea.pizzabuy.data.model.*

/****
 * Project PizzaBuy
 * Created by Barry Zea H. on 22/11/2022.
 * Copyright (c)  All rights reserved.
 ***/

class PizzaDatasource {
    companion object{
        fun getPizzas():List<PizzaAndTopics>{
            return arrayListOf(
                PizzaAndTopics(
                Pizza(
                    pizzaId = 0,
                    name="Peperoni",
                    description = "La pizza pepperoni es un clásico italiano difícil de resistir, especialmente si eres amante de los sabores picantes y la intensidad. ",
                    urlImage = "https://upload.wikimedia.org/wikipedia/commons/d/d1/Pepperoni_pizza.jpg",
                    quantity = 1,
                    price=12.2),
                    arrayListOf(DetailAndTopicsRef(
                        PizzaDetail(),
                    arrayListOf(Topic(id=1,nameOfTopic ="Mozzarella")
                    ,Topic(id=2,nameOfTopic ="Peperoni"),
                    Topic(id=3,nameOfTopic ="Cebolla"),
                    Topic(id=4,nameOfTopic ="Pimientos"))

                    ))),
                PizzaAndTopics(
                Pizza(
                    pizzaId = 0,
                    name="Margherita",
                    description = "La Margherita recuerda la bandera italiana: verde por las hojas de albahaca, blanco por el queso mozzarella y rojo por los tomates.",
                    urlImage = "https://upload.wikimedia.org/wikipedia/commons/4/41/Margherita_-_Five50_Aria.jpg",
                    quantity = 1,
                    price=14.5),
                    arrayListOf(DetailAndTopicsRef(
                        PizzaDetail(),
                    arrayListOf(
                        Topic(id=5,nameOfTopic="tomate"),
                        Topic(id=6,nameOfTopic="mozzarella"),
                        Topic(id=7,nameOfTopic="albahaca"),
                        Topic(id=8,nameOfTopic="aceite de oliva")
                    )))),
                PizzaAndTopics(
                Pizza(
                    pizzaId = 0,
                    name="Fugazza",
                    description = "Nacida de la pizza italiana, en Argentina, se inventó otro tipo de pizza: la Fugazza. Cabe aclarar que aquí también la masa es diferente de la napolitana ya que en Argentina se coloca más levadura, teniendo que esperar solamente de 3 a 4 horas a temperatura ambiente para que leve, y convirtiéndola en más esponjosa.",
                    urlImage = "https://upload.wikimedia.org/wikipedia/commons/9/96/Guerrin_pizza_fugazza_queso.jpg",
                    quantity = 1,
                    price = 13.0),
                    arrayListOf(DetailAndTopicsRef(
                        PizzaDetail(),
                    arrayListOf(
                        Topic(id=9, nameOfTopic="Mozzarella"),
                        Topic(id=10,nameOfTopic="cebolla"),
                        Topic(id=11,nameOfTopic="aceituna"),
                        Topic(id=12,nameOfTopic="tomate")
                    ) ))),
                PizzaAndTopics(
                Pizza(
                    pizzaId = 0,
                    name = "Hawaiana",
                    description = "Fue inventada en Canadá en la década de los sesenta por el griego Sam Panopoulos. Se compone por la tradicional salsa de tomate, queso mozzarella y jamón cocido. Puede llevar pimiento, bacon e, incluso, jalapeños. Si sustituimos el jamón cocido por mejillones, gambas, maíz y piña, se le suele llamar 4 estaciones.",
                    urlImage = "https://upload.wikimedia.org/wikipedia/commons/e/ea/Pizza_with_pineapple.jpg",
                    quantity = 1,
                    price = 20.0),
                    arrayListOf(DetailAndTopicsRef(
                        PizzaDetail(),
                    arrayListOf(
                        Topic(id=13,nameOfTopic="Piña"),
                        Topic(id=14,nameOfTopic="jamón"),
                        Topic(id=15,nameOfTopic="champinón"),
                        Topic(id=16,nameOfTopic="pimientos")
                    ) ))),
                PizzaAndTopics(
                Pizza(
                    pizzaId = 0,
                    name="Marinara",
                    description = "Nacida de la pizza italiana, en Argentina, se inventó otro tipo de pizza: la Fugazza. Cabe aclarar que aquí también la masa es diferente de la napolitana ya que en Argentina se coloca más levadura, teniendo que esperar solamente de 3 a 4 horas a temperatura ambiente para que leve, y convirtiéndola en más esponjosa.",
                    urlImage = "https://upload.wikimedia.org/wikipedia/commons/a/aa/Pizza_marinara_%28Napoli%29.jpg",
                    quantity = 1,
                    price = 17.0 ),
                    arrayListOf(DetailAndTopicsRef(
                        PizzaDetail(),
                    arrayListOf(
                        Topic(id=17, nameOfTopic="ajo"),
                        Topic(id=18,nameOfTopic="prosciutto"),
                        Topic(id=19,nameOfTopic="aceitunas"),
                        Topic(id=20,nameOfTopic="champiñones")
                    )))),
                PizzaAndTopics(
                Pizza(
                    pizzaId = 0,
                    name="Seafood",
                    description = "Nacida de la pizza italiana, en Argentina, se inventó otro tipo de pizza: la Fugazza. Cabe aclarar que aquí también la masa es diferente de la napolitana ya que en Argentina se coloca más levadura, teniendo que esperar solamente de 3 a 4 horas a temperatura ambiente para que leve, y convirtiéndola en más esponjosa.",
                    urlImage = "https://images.eatsmarter.com/sites/default/files/styles/1600x1200/public/seafood-pizza-with-garlic-and-tomatoes-551418.jpg",
                    quantity = 1,
                    price = 24.0 ),
                    arrayListOf(DetailAndTopicsRef(
                        PizzaDetail(),
                    arrayListOf(
                        Topic(id=21,nameOfTopic="cebolla"),
                        Topic(id=22,nameOfTopic="mozarrella"),
                        Topic(id=23,nameOfTopic="mejillones"),
                        Topic(id=24,nameOfTopic="pimientos verdes")
                    )))),
                PizzaAndTopics(
                Pizza(
                    pizzaId = 0,
                    name = "Funghi",
                    description = "La receta original puede utilizar una mezcla de setas, pero no lleva queso ni tomate. Aunque en versiones más contemporáneas se agregan quesos y salsa, incluso puede darse en un orden invertido (pan, setas, queso y salsa o pan, setas salsa y queso).",
                    urlImage = "https://ohmydish.com/wp-content/uploads/2017/07/Pizza-funghi.jpg",
                    quantity = 1,
                    price = 17.0),
                    arrayListOf(DetailAndTopicsRef(
                        PizzaDetail(),
                    arrayListOf(
                        Topic(id=25,nameOfTopic="champiñones"),
                        Topic(id=26,nameOfTopic="aceituna"),
                        Topic(id=27,nameOfTopic="pimientos verdes"),
                        Topic(id=28,nameOfTopic="cebolla")
                    ) ))),
                PizzaAndTopics(
                Pizza(
                    pizzaId = 0,
                    name = "Napolitana",
                    description = "Tiene una masa suave, esponjosa y ligeramente más gruesa que la tradicional en otras partes de Italia. Sus ingredientes: tomate, mozzarella, albahaca y pueden añadirse aceite de oliva o anchoas.",
                    urlImage = "https://en.wikipedia.org/wiki/Pizza#/media/File:Eq_it-na_pizza-margherita_sep2005_sml.jpg",
                    quantity = 1,
                    price = 18.0 ),
                    arrayListOf(DetailAndTopicsRef(
                        PizzaDetail(),
                    arrayListOf(
                        Topic(id=29,nameOfTopic="aceitunas"),
                        Topic(id=30,nameOfTopic="champiñones"),
                        Topic(id=31,nameOfTopic="alcachofa"),
                        Topic(id=32,nameOfTopic="jamón")
                    )))),
                PizzaAndTopics(
                Pizza(
                    pizzaId = 0,
                    name = "Cuatro quesos",
                    description = "Para los verdaderos amantes del queso. Mozzarella, fontina, gorgonzola y provolone o parmesano son los quesos más utilizados en Italia. Aunque podemos encontrarla con diferentes combinaciones dependiendo de dónde nos encontremos",
                    urlImage = "https://www.bofrost.es/writable/products/images-v2/low-res_10099_01774.jpg",
                    quantity = 1,
                    price = 25.0 ),
                    arrayListOf(DetailAndTopicsRef(
                        PizzaDetail(),
                    arrayListOf(
                        Topic(id=33,nameOfTopic="mozzarella"),
                        Topic(id=34,nameOfTopic="gorgonzola"),
                        Topic(id=35,nameOfTopic="fontina"),
                        Topic(id=36,nameOfTopic="parmesano")
                    )))),
                PizzaAndTopics(
                Pizza(
                    pizzaId = 0,
                    name = "Prosciutto",
                    description = "Esta pizza lleva jamón de cerdo curado de origen italiano, se cura por un máximo de dos años y tiene una mayor humedad que los jamones españoles. Estas pizzas suelen acompañarse con hojas verdes y aceite de oliva.",
                    urlImage = "https://upload.wikimedia.org/wikipedia/commons/6/61/Pizza_Prosciutto.jpg",
                    quantity = 1,
                    price = 19.0 ),
                    arrayListOf(DetailAndTopicsRef(
                        PizzaDetail(),
                    arrayListOf(
                        Topic(id=37,nameOfTopic="prosciutto"),
                        Topic(id=38,nameOfTopic="aceituna"),
                        Topic(id=39,nameOfTopic="champiñones"),
                        Topic(id=40,nameOfTopic="albahaca")
                    )))),
                PizzaAndTopics(
                Pizza(
                    pizzaId = 0,
                    name="Carbonara",
                    description = "Es una pizza blanca, es decir, que no lleva tomate en su base. Como su nombre indica, se elabora con panceta o bacon, huevo, queso o nata y pimienta negra molida. En definitiva, los mismos ingredientes con los que preparamos la pasta a la carbonara.",
                    urlImage = "https://images.hola.com/imagenes/cocina/recetas/20200120158507/pizza-carbonara-con-huevo/0-770-845/pizza-carbonara-con-huevo-t.jpg",
                    quantity = 1,
                    price = 18.0),
                    arrayListOf(DetailAndTopicsRef(
                        PizzaDetail(),
                    arrayListOf(
                        Topic(id=41,nameOfTopic="cebolla"),
                        Topic(id=42,nameOfTopic="champiñones"),
                        Topic(id=43,nameOfTopic="albahaca"),
                        Topic(id=44,nameOfTopic="mozzarella")
                    ) )))
            )
        }
    }
}