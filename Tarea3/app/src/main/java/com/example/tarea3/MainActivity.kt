package com.example.tarea3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.Toolbar

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Tarea 3 Móviles"

        chargeListView()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menucito, menu)
        return true
    }

    // boton de + en el toolbar
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_search -> {
                // Acción para el elemento de búsqueda
                Toast.makeText(this, "Elemento de búsqueda seleccionado", Toast.LENGTH_SHORT).show()
                mostrarDialog()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    // cargado de list view
    fun chargeListView(){
        val elementos = getResources().getStringArray(R.array.paises);

        val listView: ListView = findViewById(R.id.listView)

        // Crea un ArrayAdapter para mostrar los nombres en el ListView
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, elementos)

        //refresca el listview
        adapter.setNotifyOnChange(true);

        // Asocia el ArrayAdapter con el ListView
        listView.adapter = adapter

        // Configura un escuchador para el clic en los elementos del ListView
        listView.onItemClickListener = object : AdapterView.OnItemClickListener {
            override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val nombreSeleccionado = elementos[position]
                // TODO: Se llama al activity de un pais seleccionado
                callActivity2(findViewById(R.id.listView), nombreSeleccionado)
                // Muestra un Toast con el nombre seleccionado
                Toast.makeText(this@MainActivity, "País seleccionado: $nombreSeleccionado", Toast.LENGTH_SHORT).show()
            }
        }
    }
    fun callActivity2(view: View, pais: String){
        // Crear un Intent para iniciar la Activity2
        val intent = Intent(this, MainActivity2::class.java)

        // Iniciar la Activity2 utilizando el Intent
        startActivity(intent)
    }

    // suceso al presionar el boton +
    fun mostrarDialog() {
        val dialogView = LayoutInflater.from(this).inflate(R.layout.add_layout, null)

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