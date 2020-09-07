package com.example.calculadoramilionaria

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun calcular(componente:View) {
        val proporcao = 0.55

        var nome:String = et_texto_nome.text.toString()
        var salario:Double = et_texto_valor_mes.text.toString().toDouble()
        var gastoReal:Double = et_valor_essencial_mes.text.toString().toDouble()

        var gastoIdeal = salario * proporcao
        var valorAcima = gastoReal - gastoIdeal

        if (nome.length < 3 || salario <= 0 || gastoReal <= 0) {
            Toast.makeText(this, "Opa! Algo deu errado.", Toast.LENGTH_SHORT).show()

            tv_resultado_texto.visibility = View.INVISIBLE
            tv_resultado_texto_grande.visibility = View.INVISIBLE
            tv_descricao_resultado.visibility = View.INVISIBLE

            if (nome.length < 3) {
                tv_red_nome.visibility = View.VISIBLE
            } else {
                tv_red_nome.visibility = View.INVISIBLE
            }
            if (salario <= 0) {
                tv_red_salario.visibility = View.VISIBLE
            } else {
                tv_red_salario.visibility = View.INVISIBLE
            }
            if (gastoReal <= 0) {
                tv_red_gastos.visibility = View.VISIBLE
            } else {
                tv_red_gastos.visibility = View.INVISIBLE
            }
        } else {
            tv_red_nome.visibility = View.INVISIBLE
            tv_red_salario.visibility = View.INVISIBLE
            tv_red_gastos.visibility = View.INVISIBLE

            tv_resultado_texto.visibility = View.VISIBLE
            tv_resultado_texto_grande.visibility = View.VISIBLE
            tv_descricao_resultado.visibility = View.VISIBLE

            if (gastoReal > gastoIdeal) {
                tv_resultado_texto_grande.text = "PERIGO!!"
                tv_resultado_texto_grande.setTextColor(Color.RED)
                tv_descricao_resultado.text = "$nome, seus gastos estão R$${"%.2f".format(valorAcima)} acima do ideal!! Que tal Netflix e uma comida caseira para este fim de semana?"
            } else if (gastoReal == gastoIdeal){
                tv_resultado_texto_grande.text = "CUIDADO..."
                tv_resultado_texto_grande.setTextColor(Color.parseColor("#ff8c00"))
                tv_descricao_resultado.text = "$nome, seus gastos estão EXATAMENTE no valor ideal, parabéns pela disciplina! Mas tenha cuidado: sair do controle pode ser mais fácil do que você imagina..."
            } else {
                tv_resultado_texto_grande.text = "SEGURA :)"
                tv_resultado_texto_grande.setTextColor(Color.GREEN)
                tv_descricao_resultado.text = "$nome, seus gastos estão dentro do ideal, parabéns! Você deve ser o exemplo da família, continue assim!"
            }
        }

    }

}