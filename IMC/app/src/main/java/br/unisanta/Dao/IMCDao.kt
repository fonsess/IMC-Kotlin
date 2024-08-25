package br.unisanta.Dao

import br.unisanta.Models.IMC

interface IMCDao {
    fun salvarImc(imc: IMC)
    fun obterCalculo():String
}