package mx.itson.sol

import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.GoogleMap.OnMarkerDragListener
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import mx.itson.sol.entidades.Clima
import mx.itson.sol.entidades.Ubicacion
import mx.itson.sol.utilerias.RetrofitUtil
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity(), OnMapReadyCallback, LocationListener {

    private var mapa: GoogleMap? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        val mapFragment = supportFragmentManager.findFragmentById(R.id.mapa) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        try {
            mapa = googleMap
            mapa!!.mapType = GoogleMap.MAP_TYPE_HYBRID

            val estaPermitido = ActivityCompat.checkSelfPermission(
                this,
                ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED

            if (estaPermitido) {
                mapa!!.isMyLocationEnabled = true
            } else {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(ACCESS_FINE_LOCATION),
                    200
                )
            }

            val locationManager = getSystemService(LOCATION_SERVICE) as LocationManager
            val location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)
            if (location != null) {
                onLocationChanged(location)
            }

        } catch (ex: Exception) {
            Log.e("error al cargar el mapa", ex.toString())
        }
    }

    private fun obtenerUbicacion(latitud : Double, longitud : Double ) {
        // ahora obtener ubicacion obtienen los parametros de lon y lat a tipo double
        val llamada: Call<Ubicacion> =

            RetrofitUtil.getApi().getClima(latitud, longitud, true)

        llamada.enqueue(object : Callback<Ubicacion> {
            //hace llamar la api de Openmeteo a la cual posteriormente le mandamos la lon y lat y en caso de respuesta se ejecuta lo siguiente
            override fun onResponse(call: Call<Ubicacion>, response: Response<Ubicacion>) {
                val ubicacion: Ubicacion? = response.body()
                // se hizo esto para obtener los valores de los objetos del Json
                val latit = ubicacion?.latitude
                val long = ubicacion?.longitude
                val eleve = ubicacion?.elevation
                val temperatura= ubicacion?.clima?.temperatura
                val velocidadviento= ubicacion?.clima?.velViento
                val direccionV= ubicacion?.clima?.dicViento
                val codigoClma= ubicacion?.clima?.codClima
                //se inician la activiti clima y se le pasan los valores de las variables
                val intent = Intent (this@MainActivity,ClimaActivity::class.java)
                intent.putExtra("latitud",latit)
                intent.putExtra("longitud",long)
                intent.putExtra("eleve",eleve)
                intent.putExtra("temperatura",temperatura)
                intent.putExtra("velocidadviento",velocidadviento)
                intent.putExtra("direccionV",direccionV)
                intent.putExtra("codigoClma",codigoClma)
                //ejecuta el codigo que lanza la actividad
                startActivity(intent)




            }

            override fun onFailure(call: Call<Ubicacion>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }

    override fun onLocationChanged(location: Location) {
        val latitud = location.latitude
        val longitud = location.longitude

        val latLng = LatLng(latitud, longitud)
        mapa?.clear()

        // Guardar referencia al marcador
        mapa?.addMarker(MarkerOptions().position(latLng).draggable(true).title("Mi ubicación"))

        mapa?.moveCamera(CameraUpdateFactory.newLatLng(latLng))
        mapa?.animateCamera(CameraUpdateFactory.zoomTo(15f))

        // Agregar listener al marcador
        mapa?.setOnMarkerDragListener(object : OnMarkerDragListener {
            override fun onMarkerDragStart(marker: Marker) {
                //se puso esto por la version de android no me deja tener metodos vacios
                var a : Int = 1
            }

            override fun onMarkerDrag(marker: Marker) {
                var a : Int = 1
                }

            override fun onMarkerDragEnd(marker: Marker) {
                //se obtienen el parametro de lat y long de poscion del marcador y se manda a llamar obtener ubicacion con esos parametros
                val lat = marker.position.latitude
                val lon = marker.position.longitude
                obtenerUbicacion(lat, lon)

                // Acción a realizar cuando se termina el arrastre
                Toast.makeText(
                    this@MainActivity,
                    "Latitud: ${marker.position.latitude} Longitud: ${marker.position.longitude}",
                    Toast.LENGTH_LONG
                ).show()
            }
        })
    }
}