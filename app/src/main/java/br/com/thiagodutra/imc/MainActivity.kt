package br.com.thiagodutra.imc

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.util.regex.Matcher
import java.util.regex.Pattern
import kotlin.math.pow

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fun validateInput(altura:String, peso:String): Boolean {
            val lAltura = altura.split(".",",")
            val lPeso = peso.split(".",",")

            if(lAltura.size <= 1 || lPeso.size <=1) {
                return false
            }
            return true
        }

        btnMainCalcular.setOnClickListener{
            val altura = edtMainAltura.text.toString().trim()
            val peso = edtMainPeso.text.toString().trim()

            if (altura.isEmpty() || peso.isEmpty()) {
                if (altura.isEmpty()) {
                    edtMainAltura.error = ("Campo obrigatório")
                    edtMainAltura.requestFocus()
                }
                if (peso.isEmpty()) {
                    edtMainPeso.error = "Campo obrigatório"
                    edtMainPeso.requestFocus()
                }
            }
            else if (!validateInput(altura, peso)){
                Toast.makeText(this, "Valores de altura ou peso inválidos", Toast.LENGTH_SHORT).show()
            } else {
                val imc = peso.toDouble() / (altura.toDouble().pow(2.0))
                val mIntent = Intent(this, ResultActivity::class.java)
                mIntent.putExtra("IMC", imc)
                startActivity(mIntent)
                finish()
            }
        }
    }
}
