package mx.itson.sol.utilerias

import com.google.gson.GsonBuilder
import mx.itson.sol.interfaces.OpenMeteo
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * esta clase es un objeto que establece la estructura del llamado a la api
 */
object RetrofitUtil {
//retro fit es una libreria que añade la compatibilidad con apis
    fun getApi(): OpenMeteo {
        val gson = GsonBuilder().create()
    // esta URL es la base de la api
        val retrofit = Retrofit.Builder().baseUrl("https://api.open-meteo.com/v1/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
//Gson es una libreria de google para agregar copatibilidad con archivo Json
        return retrofit.create(OpenMeteo::class.java)
    }
}

//https://api.open-meteo.com/v1/forecast?latitude=52&longitude=130&true esta cosa es la consulta completa

