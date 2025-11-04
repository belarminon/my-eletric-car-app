package br.com.bnms.electriccarapp.presentation

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.bnms.electriccarapp.R
import br.com.bnms.electriccarapp.presentation.adapter.CarAdapter

class MainActivity : AppCompatActivity() {

    lateinit var btnCalcularAutonomia: Button
    lateinit var listaCarros: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setupView()
        setupListeners()
        setUpLista()
    }

    fun setupView() {
        btnCalcularAutonomia = findViewById<Button>(R.id.btn_calcular_autonomia)
        listaCarros = findViewById(R.id.rv_lista_carros)
    }
    fun setupListeners() {

        btnCalcularAutonomia.setOnClickListener {
            startActivity(Intent(this,CalcularAutonomiaActivity::class.java))
        }
    }

    fun setUpLista() {

        val dados = arrayOf(
            "Android 1.0 - Alpha",
            "Android 1.1 - Beta",
            "Android 1.5 - Cupcake",
            "Android 1.6 - Donut",
            "Android 2.0 - Eclair",
            "Android 2.2 - Froyo",
            "Android 2.3 - Gingerbread",
            "Android 3.0 - Honeycomb",
            "Android 4.0 - Ice Cream Sandwich",
            "Android 4.1 - Jelly Bean",
            "Android 4.4 - KitKat",
            "Android 5.0 - Lollipop",
            "Android 6.0 - Marshmallow",
            "Android 7.0 - Nougat",
            "Android 8.0 - Oreo",
            "Android 9.0 - Pie",
            "Android 10",
            "Android 11",
            "Android 12",
            "Android 13",
            "Android 14",
            "Android 15"
        )

        val adapter = CarAdapter(dados)
        //listaCarros.LayoutManager = LinearLayoutManager(this)
        listaCarros.adapter = adapter
    }

}