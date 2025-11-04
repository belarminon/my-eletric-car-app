package br.com.bnms.electriccarapp.presentation

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import br.com.bnms.electriccarapp.R

class MainActivity : AppCompatActivity() {

    lateinit var btnCalcularAutonomia: Button

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
    }

    fun setupView() {
        btnCalcularAutonomia = findViewById<Button>(R.id.btn_calcular_autonomia)
    }
    fun setupListeners() {

        btnCalcularAutonomia.setOnClickListener {
            startActivity(Intent(this,CalcularAutonomiaActivity::class.java))
        }
    }
}