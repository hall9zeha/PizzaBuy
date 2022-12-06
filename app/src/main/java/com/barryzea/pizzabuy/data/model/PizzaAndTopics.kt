package com.barryzea.pizzabuy.data.model

import android.os.Parcelable
import androidx.room.*
import com.barryzea.pizzabuy.common.Constant
import kotlinx.parcelize.Parcelize

/****
 * Project PizzaBuy
 * Created by Barry Zea H. on 22/11/2022.
 * Copyright (c)  All rights reserved.
 ***/
@Parcelize
@Entity
data class Pizza(
    @PrimaryKey(autoGenerate = true) var pizzaId:Long=0,
    var name:String="",
    var description:String="",
    var urlImage:String="",
    var quantity:Int=1,
    var price:Double=0.0,
    var totalPrice:Double=0.0

    ):Parcelable /*{
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Pizza

        if (pizzaId != other.pizzaId) return false

        return true
    }

    override fun hashCode(): Int {
        return pizzaId.hashCode()
    }
}*/
@Entity(
    foreignKeys = [
        ForeignKey(
            entity=Pizza::class,
            parentColumns = arrayOf(Constant.PIZZA_ENTITY_PARENT_KEY),
            childColumns = arrayOf(Constant.DETAIL_ENTITY_CHILD_KEY),
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE
        )
    ]
)
@Parcelize
data class PizzaDetail(
    @PrimaryKey(autoGenerate = true)var idPizzaDetail:Long=0,
    @ColumnInfo(index=true)
    var pizzaId:Long=0,
    var namePizza:String=""
    //@Ignore val topics: ArrayList<Topic> = arrayListOf()
):Parcelable

@Parcelize
@Entity(foreignKeys = [
    ForeignKey(
        entity = PizzaDetail::class,
        parentColumns = arrayOf(Constant.PIZZA_DETAIL_ENTITY_PARENT_KEY),
        childColumns = arrayOf(Constant.TOPIC_ENTITY_CHILD_KEY),
        onDelete = ForeignKey.CASCADE,
        onUpdate = ForeignKey.CASCADE
    )
])
data class Topic(
    @PrimaryKey(autoGenerate = true)var id: Long=0,
    @ColumnInfo(index=true)
    var topicPizzaId:Long=0,
    var nameOfPizza:String="",
    var nameOfTopic:String="",
    var state:Boolean=true,
):Parcelable {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Topic

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }
}

//data class para relacionar una pizza con varios ingredientes y cargarlos en una consulta
@Parcelize
data class PizzaAndTopics(
    @Embedded var pizza:Pizza= Pizza(),
    @Relation(
        parentColumn = Constant.PIZZA_ENTITY_PARENT_KEY,//columna de la llave principal que está en dataclass o entidad Pizza
        entityColumn = Constant.DETAIL_ENTITY_CHILD_KEY,//columna de la llave foranea en  dataclass o entidad  Topic
        entity = PizzaDetail::class
    )

    //var topics: List<Topic> = arrayListOf()
    var detail: List<DetailAndTopicsRef> = arrayListOf()
):Parcelable


@Parcelize
data class DetailAndTopicsRef(
    @Embedded var detail:PizzaDetail=PizzaDetail(),
    @Relation(
        parentColumn = Constant.PIZZA_DETAIL_ENTITY_PARENT_KEY,
        entityColumn = Constant.TOPIC_ENTITY_CHILD_KEY,

        )
    var topics: List<Topic> = arrayListOf()
):Parcelable
