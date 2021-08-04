package br.com.samuelnunes.rickandmortyapp.data.entities

/**
 * @author Samuel da Costa Araujo Nunes
 * Created 03/08/2021 at 15:40
 */

data class Character(
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val type: String,
    val gender: String,
    val image: String
)