package mx.itson.sol.interfaces

import mx.itson.sol.entidades.Ubicacion
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface OpenMeteo {
@GET("forecast")
//se le puso Double para no andar haciendo convversioners inneccesarias
fun getClima(@Query("latitude")lat:Double,
             @Query("longitude")lon: Double,
             @Query("current_weather")curret:Boolean): Call<Ubicacion>


}