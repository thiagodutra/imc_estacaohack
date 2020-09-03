package br.com.thiagodutra.imc

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
import androidx.core.util.toHalf
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_result.*

class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val MUITO_ABAIXO_DO_PESO = 17
        val ABAIXO_DO_PESO = 18.49
        val PESO_NORMAL = 25.0
        val ACIMA_DO_PESO= 30.0
        val OBESIDADE_1 = 35.0
        val OBESIDADE_2 = 40.0

        fun Double.format(digits: Int) = "%.${digits}f".format(this)

        val imc = intent.getDoubleExtra("IMC", 0.0)
        val imgViewer = findViewById<ImageView>(R.id.imvResultImgInfo)
        val defaultText = "Seu IMC é: ${imc.format(2)}"

        if (imc == 0.0) {
            txvResultMessage.text = "Erro inesperado, volte a tela de cálculo"
            AlertDialog.Builder(this)
                .setTitle("Atenção!")
                .setMessage("Erro inesperado, volte a tela de cálculo")
                .setPositiveButton("Voltar"){_,_ ->
                    val mIntent = Intent(this, MainActivity::class.java)
                    startActivity(mIntent)
                    finish()
                }
                .setNegativeButton("Sair") { _, _ ->
                    finishAffinity()
                }
                .setCancelable(false)
                .create()
                .show()
        } else {
            txvResultMessage.text = defaultText
        }
        when  {
            imc < MUITO_ABAIXO_DO_PESO -> {
                imgViewer.setImageResource(R.drawable.imc_under_17)
            }
            imc < ABAIXO_DO_PESO -> {
                imgViewer.setImageResource(R.drawable.imc_between_17_1849)
            }
            imc < PESO_NORMAL -> {
                imgViewer.setImageResource(R.drawable.imc_between_18_5_25)
            }
            imc < ACIMA_DO_PESO -> {
                imgViewer.setImageResource(R.drawable.imc_between_25_30)
            }
            imc < OBESIDADE_1 -> {
                imgViewer.setImageResource(R.drawable.imc_between_30_34)
            }
            imc < OBESIDADE_2 -> {
                imgViewer.setImageResource(R.drawable.imc_between_35_40)
            }
            else -> {
                imgViewer.setImageResource(R.drawable.imc_above_40)
            }
        }

        btnResultRecalcular.setOnClickListener{
            val mIntent = Intent(this, MainActivity::class.java)
            startActivity(mIntent)
            finish()
        }

    }
}