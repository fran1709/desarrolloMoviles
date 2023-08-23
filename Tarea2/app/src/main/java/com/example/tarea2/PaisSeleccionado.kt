package com.example.tarea2

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog

class PaisSeleccionado : AppCompatActivity() {
    private lateinit var seleccionado:String
    private lateinit var datos:String
    val paisesInformation = hashMapOf<String, Any>(
        "Alemania" to hashMapOf<String, Any>(
            "Continente" to "Europa",
            "Capital" to "Berlín"
        ),
        "Costa Rica" to hashMapOf<String, Any>(
            "Continente" to "América Central",
            "Capital" to "San José"
        ),
        "Estados Unidos" to hashMapOf<String, Any>(
            "Continente" to "América del Norte",
            "Capital" to "Washington, D.C."
        ),
        "Finlandia" to hashMapOf<String, Any>(
            "Continente" to "Europa",
            "Capital" to "Helsinki"
        ),
        "Grecia" to hashMapOf<String, Any>(
            "Continente" to "Europa",
            "Capital" to "Atenas"
        ),
        "Japón" to hashMapOf<String, Any>(
            "Continente" to "Asia",
            "Capital" to "Tokio"
        ),
        "Nueva Zelanda" to hashMapOf<String, Any>(
            "Continente" to "Oceanía",
            "Capital" to "Wellington"
        )
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pais_seleccionado)
        spinnerExample()
        val pais : TextView =findViewById(R.id.pais)
        pais.text = intent.getStringExtra("pais")
    }

    private fun spinnerExample(){
        //val elementos = listOf("Elemento 1", "Elemento 2", "Elemento 3")
        val elementos = getResources().getStringArray(R.array.datos);
        val spinner: Spinner = findViewById(R.id.spinner)

        // Crea un ArrayAdapter usando los elementos y el diseño predeterminado para el spinner
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, elementos)

        // Especifica el diseño que se usará cuando se desplieguen las opciones
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        // Une el ArrayAdapter al Spinner
        spinner.adapter = adapter

        // Opcionalmente, puedes configurar un escuchador para detectar la selección del usuario
        spinner.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val itemSeleccionado = elementos[position]
                seleccionado = itemSeleccionado;
                // Realiza alguna acción con el elemento seleccionado
                Toast.makeText(this@PaisSeleccionado, "Seleccionaste: $itemSeleccionado", Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Se llama cuando no se ha seleccionado nada en el Spinner (opcional)
                Toast.makeText(this@PaisSeleccionado, "Nada", Toast.LENGTH_SHORT).show()
            }
        }
    }
    fun llamarActivity(view: View){
        // TODO: Enviando datos seleccionados por un Intent.
        val intent = Intent()
        intent.putExtra("datos", datos)
        setResult(Activity.RESULT_OK, intent)
        // Finaliza la activity actual y regresa a la anterior
        finish()
    }

    fun mostrarInfo(view: View){
        // Acceder a valores dentro del diccionario interno
        val dato = (paisesInformation[intent.getStringExtra("pais")] as? Map<*, *>)?.get(seleccionado)
        if (::datos.isInitialized) {
            val nuevoDato = seleccionado
            datos = "$datos, $nuevoDato" // Concatenar el nuevo dato a la cadena existente
        } else {
            // Inicializar la variable si aún no lo esta
            datos = seleccionado
        }
        //val txt: TextView = findViewById(R.id.txtView)
        //txt.text = "dato.toString()"

        val dialogView = LayoutInflater.from(this).inflate(R.layout.information_pais, null)
        val txt: TextView = dialogView.findViewById(R.id.txtView)
        txt.text = dato.toString()

        //añadimos a activity la info
        //datos.plus(seleccionado)

        val builder = AlertDialog.Builder(this)
        builder.setView(dialogView)
        val dialog = builder.create()

        val btnCerrar: Button = dialogView.findViewById(R.id.btnCerrar)
        btnCerrar.setOnClickListener {
            dialog.dismiss() // Cierra el diálogo cuando se hace clic en el botón "Cerrar"
        }

        dialog.show()
    }
}