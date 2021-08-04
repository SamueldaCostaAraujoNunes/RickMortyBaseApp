package br.com.samuelnunes.rickandmortyapp.data.entities

/**
 * @author Samuel da Costa Araujo Nunes
 * Created 03/08/2021 at 15:39
 */
data class Info(
    val count: Int,
    val pages: Int,
    val next: String?,
    val prev: String?
)
