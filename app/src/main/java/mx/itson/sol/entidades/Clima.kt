package mx.itson.sol.entidades

import com.google.gson.annotations.SerializedName

/**
 * Clase de entidad de tipo clima
 */
class Clima {


//@serializedName sirve para cambiar el nombre de lo que manda en JSON por si no le quieres cambiar el nombre identico
    @SerializedName("temperature")
    var temperatura: Float?  = null
    @SerializedName("windspeed")
    var velViento  : Float?  = null

    @SerializedName("winddirection")
    var dicViento : Float?  = null

    @SerializedName("weathercode")
    var codClima : Int? = null













}



