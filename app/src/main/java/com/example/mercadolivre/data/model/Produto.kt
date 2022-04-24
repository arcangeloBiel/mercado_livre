package com.example.mercadolivre.data.model

import java.io.Serializable

data class Produto(
    val id: Int,
    val nome: String,
    val preco: String,
    val descricao: String,
    val imageUrl: String,
): Serializable
//{
//
//    /**
//     * Sobrescrevi os métodos equals() e hashCode() para melhorar o
//     * desempenho da DiffUtil
//     */
//    override fun equals(other: Any?): Boolean {
//        if (this === other) return true
//        if (javaClass != other?.javaClass) return false
//
//        other as Produto
//
//        if (id != other.id) return false
//        if (nome != other.nome) return false
//
//        return true
//    }
//
//    override fun hashCode(): Int {
//        var result = id
//        result = 31 * result + nome.hashCode()
//        return result
//    }

//}
