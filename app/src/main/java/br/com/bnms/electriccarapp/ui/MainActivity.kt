package br.com.bnms.electriccarapp.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.RecyclerView
import br.com.bnms.electriccarapp.R
import br.com.bnms.electriccarapp.data.CarFactory
import br.com.bnms.electriccarapp.ui.adapter.CarAdapter

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

        val adapter = CarAdapter(CarFactory.list)
        listaCarros.adapter = adapter
    }

}