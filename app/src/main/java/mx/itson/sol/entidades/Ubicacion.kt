package mx.itson.sol.entidades

import com.google.gson.annotations.SerializedName

/**
 * Clase de entidad de tipo ubicacion
 */
class Ubicacion {

    var latitude : Float? = null
    var longitude: Float? = null
    var timezone : String? = null
    var elevation: Float? = null

    @SerializedName("current_weather")
    var clima: Clima?  = null



}