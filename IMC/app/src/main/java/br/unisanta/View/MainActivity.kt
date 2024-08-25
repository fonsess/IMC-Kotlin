package br.unisanta.View

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.unisanta.Dao.IMCDaoImplement
import br.unisanta.Models.IMC
import br.unisanta.R

class MainActivity : AppCompatActivity(R.layout.activity_main) {
    val imcDao = IMCDaoImplement()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val btnCalcularImc = findViewById<Button>(R.id.btnCalcular)
        val btnResetarCampos = findViewById<Button>(R.id.btnLimpar)
        val edtAlturaImc = findViewById<EditText>(R.id.txtAltura)
        val edtPesoImc = findViewById<EditText>(R.id.txtPeso)

        btnResetarCampos.setOnClickListener {
            edtPesoImc.setText("")
            edtAlturaImc.setText("")
        }

        edtAlturaImc.addTextChangedListener(object : TextWatcher {
            private var alterandoTexto: Boolean = false
            private val caracterePonto = '.'

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                if (alterandoTexto) return

                alterandoTexto = true
                var texto = s.toString()

                texto = texto.replace(caracterePonto.toString(), "")

                if (texto.length > 3) {
                    texto = texto.substring(0, 3)
                }

                if (texto.length >= 2) {
                    texto = texto.substring(0, 1) + caracterePonto + texto.substring(1)
                }

                edtAlturaImc.setText(texto)
                edtAlturaImc.setSelection(texto.length)

                alterandoTexto = false
            }
        })

        btnCalcularImc.setOnClickListener {
            val alturaUsuario = edtAlturaImc.text.toString().toDoubleOrNull()
            val pesoUsuario = edtPesoImc.text.toString().toDoubleOrNull()

            if (alturaUsuario != null && pesoUsuario != null) {
                val calculoImc = IMC(alturaUsuario, pesoUsuario)
                imcDao.salvarImc(calculoImc)

                val novaTela = Intent(this, MainActivity2::class.java)
                startActivity(novaTela)
            } else {
                Toast.makeText(this, "Por favor, insira valores válidos para altura e peso.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
