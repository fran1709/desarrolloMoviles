package com.example.tarea2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        chargeListView()
    }

    fun chargeListView(){
        val elementos = getResources().getStringArray(R.array.paises);

        val listView: ListView = findViewById(R.id.listVIew)

        // Crea un ArrayAdapter para mostrar los nombres en el ListView
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, elementos)

        // Asocia el ArrayAdapter con el ListView
        listView.adapter = adapter

        // Configura un escuchador para el clic en los elementos del ListView
        listView.onItemClickListener = object : AdapterView.OnItemClickListener {
            override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val nombreSeleccionado = elementos[position]
                // TODO: Se llama al activity de un pais seleccionado
                callActivity2(findViewById(R.id.listVIew), nombreSeleccionado)
                // Muestra un Toast con el nombre seleccionado
                Toast.makeText(this@MainActivity, "País seleccionado: $nombreSeleccionado", Toast.LENGTH_SHORT).show()
            }
        }
    }
    fun callActivity2(view: View, pais: String){
        // Crear un Intent para iniciar la Activity2
        val intent = Intent(this, PaisSeleccionado::class.java)

        // TODO: Enviando el pais seleccionado (string) por un Intent.
        intent.putExtra("pais", pais)

        // Iniciar la Activity2 utilizando el Intent
        startActivity(intent)
    }
}