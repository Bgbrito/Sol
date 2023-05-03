package mx.itson.sol.utilerias

import com.google.gson.GsonBuilder
import mx.itson.sol.interfaces.OpenMeteo
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitUtil {

    fun getApi(): OpenMeteo {
        val gson = GsonBuilder().create()
        val retrofit = Retrofit.Builder().baseUrl("https://api.open-meteo.com/v1/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

        return retrofit.create(OpenMeteo::class.java)
    }
}


