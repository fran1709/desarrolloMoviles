package com.example.view2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog

class MainActivity : AppCompatActivity() {
    private lateinit var seleccionado:String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        spinnerExample()
    }

    fun suma(view: View){
        val et1: EditText = findViewById(R.id.et)
        val et2:EditText = findViewById(R.id.et2)
        val tvResult: TextView = findViewById(R.id.tvResultado)

        var valor1 = et1.text.toString().toInt()
        var valor2 = et2.text.toString().toInt()

        // comparando variable global para verificar si es suma
        val resultado = when (seleccionado){
            "Suma" -> valor1 + valor2
            "Resta" ->  valor1 - valor2
            "Multiplicación" -> valor1 * valor2
            "División" -> valor1 / valor2
            else -> {0}
        }

        tvResult.text = resultado.toString()
    }

    private fun spinnerExample(){
        //val elementos = listOf("Elemento 1", "Elemento 2", "Elemento 3")
        val elementos = getResources().getStringArray(R.array.elementos);

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
                Toast.makeText(this@MainActivity, "Seleccionaste: $itemSeleccionado", Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Se llama cuando no se ha seleccionado nada en el Spinner (opcional)
                Toast.makeText(this@MainActivity, "Nada", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun callActivity2(view: View){
        // crearun intent para iniciar la Activity2
        val intent = Intent(this, MainActivity2::class.java)

        // modo de mandar parametros
        //intent.putExtra()

        // iniciar la Activity2 utilizando el intent
        startActivity(intent)
    }

    fun mostrarDialog(view: View) {
        val dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_layout, null)

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