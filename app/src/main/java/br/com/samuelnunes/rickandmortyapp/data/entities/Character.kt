package br.com.samuelnunes.rickandmortyapp.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * @author Samuel da Costa Araujo Nunes
 * Created 03/08/2021 at 15:40
 */

@Entity
data class Character(
    @PrimaryKey
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val type: String,
    val gender: String,
    val image: String
)