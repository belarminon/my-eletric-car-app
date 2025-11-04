package br.com.bnms.electriccarapp.ui

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import br.com.bnms.electriccarapp.R

class CalcularAutonomiaActivity : AppCompatActivity() {

    lateinit var btnClose: ImageView
    lateinit var preco: EditText
    lateinit var kmPercorrido: EditText
    lateinit var resultado: TextView

    lateinit var btnCalcular: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calcular_autonomia)

        setupView()
        setupListeners()
    }

    fun setupView() {
        btnClose = findViewById(R.id.iv_close)
        preco = findViewById(R.id.et_preco_kwh)
        kmPercorrido = findViewById(R.id.et_percorrido)
        resultado = findViewById(R.id.tv_resultado)
        btnCalcular = findViewById<Button>(R.id.btn_calcular)
    }

    fun setupListeners() {

        btnCalcular.setOnClickListener {

            val quantia = preco.text.toString().toFloat()
            val km = kmPercorrido.text.toString().toFloat()

            resultado.text = CalcularKM(quantia, km)
        }

        btnClose.setOnClickListener {
            finish()
        }
    }

    private fun CalcularKM(quantia: Float, km: Float): String {
        val result = quantia / km
        return "O valor da autonomia é de: ${result.toString()}"
    }
}