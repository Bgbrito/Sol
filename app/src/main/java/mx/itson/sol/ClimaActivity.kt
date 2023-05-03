package mx.itson.sol

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class ClimaActivity : AppCompatActivity(), View.OnClickListener {
    var temp: Float = 0.0f
    var elevacion: Float = 0.0f
    var velocito: Float = 0.0f
    var direccionDeViento: Float = 0.0f
    var codigoClima: Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_clima)

        // se reciben los valores enviados de extra desde la MainActivity
        temp = intent.getFloatExtra("temperatura", 0.0f)
        elevacion = intent.getFloatExtra("eleve", 0.0f)
        velocito = intent.getFloatExtra("velocidadviento", 0.0f)
        direccionDeViento = intent.getFloatExtra("direccionV", 0.0f)
        codigoClima = intent.getIntExtra("codigoClma", 0)
//la funcion clima asigna los valores recividos de los extras
        clima(temp,  elevacion, velocito, direccionDeViento, codigoClima)
        findViewById<Button>(R.id.cerrar).setOnClickListener(this)

    }

    private fun clima(
        temp: Float,
        elevacion: Float,
        velocito: Float,
        direccionDeViento: Float,
        codigoClima: Int
    ) {
//localiza el componente en la vista, y se asigna una variable
        var textelevacion = findViewById<TextView>(R.id.elevacion)
        var textclima = findViewById<TextView>(R.id.clima)
        var texttemperaturea = findViewById<TextView>(R.id.temperatura)
        var textvelocidad = findViewById<TextView>(R.id.velocidad)
        var textdireccion = findViewById<TextView>(R.id.direccion)
//este sirve para determinar el codigo de clima y designar los valores correctos al tipo de clima
        //when es un switch para las diferentes tipos de condiciones
        when (codigoClima) {
            0 -> {
                findViewById<View>(R.id.papi).setBackgroundResource(R.drawable.maxresdefault)
                textclima.text = "soleado"

            }// codigos de nublado
            1, 2, 3 -> {
                findViewById<View>(R.id.papi).setBackgroundResource(R.drawable.images)
                textclima.text = "nublado"
            }
            //codigos de neblina
            45, 48, 49 -> {
                findViewById<View>(R.id.papi).setBackgroundResource(R.drawable.descarga12)
                textclima.text = "neblinaa"
            }// codigos de todos los tipos de lluvias que hay
            55, 53, 55, 61, 63, 65, 80, 81, 82, 95, 96, 99 -> {
                findViewById<View>(R.id.papi).setBackgroundResource(R.drawable.descarga)
                textclima.text = "lluvia"
            }//codigos de diferentes tipos de nieve
            56, 57, 66, 67, 71, 73, 75, 77, 85, 86 -> {
                findViewById<View>(R.id.papi).setBackgroundResource(R.drawable.descarga11)
                textclima.text = "Nevao"

            }
        }// con esto se le asignan los valores a los campos de texto con su respectiva monecclatura
        textelevacion.text = "$elevacion m s.n.m"
        textdireccion.text = "$direccionDeViento °"
        texttemperaturea.text = "$temp C°"
        textvelocidad.text = "$velocito Km/h "

    }
// para cerrar la aplicacion
    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.cerrar -> finish()
        }
    }
}