package br.com.bnms.electriccarapp

import android.os.Bundle
import android.support.v4.os.IResultReceiver
import android.util.Log
import android.view.TextureView
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Switch
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    lateinit var preco: EditText
    lateinit var kmPercorrido: EditText
    lateinit var btnCalcular: Button
    lateinit var resultado: TextView

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
        preco = findViewById(R.id.et_preco_kwh)
        kmPercorrido = findViewById(R.id.et_percorrido)
        resultado = findViewById(R.id.tv_resultado)
        btnCalcular = findViewById<Button>(R.id.btn_calcular)
    }

    fun setupListeners() {

        btnCalcular.setOnClickListener {

            val preco = preco.text.toString().toFloat()
            val km = kmPercorrido.text.toString().toFloat()

            resultado.text = CalcularKM(preco, km)
        }
    }

    private fun CalcularKM(preco: Float, km: Float): String {
        val result = preco / km
        return "O valor da quilometragem é de: ${result.toString()}"
    }
}