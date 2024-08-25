package br.unisanta.Dao

import br.unisanta.Models.IMC

class IMCDaoImplement:IMCDao {
    companion object{
        private var imc:IMC?=null
    }
    override fun salvarImc(imc: IMC) {
        Companion.imc = imc
    }

    override fun obterCalculo(): String {
        val peso = Companion.imc?.peso ?: throw IllegalArgumentException("Peso não pode ser nulo")
        val altura = Companion.imc?.altura ?: throw IllegalArgumentException("Altura não pode ser nula")

        val imc = peso / (altura * altura)

        if(imc < 16.9){
            return "Muito abaixo do peso"
        }else if(imc < 18.4){
            return "Abaixo do peso"
        }else if(imc < 24.9){
            return "Peso ideal"
        }else if(imc < 29.9){
            return "Acima do peso"
        }else if(imc < 34.9){
            return "Obesidade grau I"
        }else if(imc < 40){
            return "Obesidade grau II"
        } else{
            return "Obesidade grau II"
        }
    }
}