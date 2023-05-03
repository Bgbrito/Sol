package mx.itson.sol.interfaces

import mx.itson.sol.entidades.Ubicacion
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Es un interfaz que tiene una estructura de una consulta a la api de Openmeteo
 */
interface OpenMeteo {
    //se accede al metodo forecast
@GET("forecast")
//se le puso Double para no andar haciendo convversioners inneccesarias
fun getClima(@Query("latitude")lat:Double,
             @Query("longitude")lon: Double,
             @Query("current_weather")curret:Boolean): Call<Ubicacion>


}