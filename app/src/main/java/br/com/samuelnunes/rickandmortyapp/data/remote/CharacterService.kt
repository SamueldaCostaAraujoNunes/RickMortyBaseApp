package br.com.samuelnunes.rickandmortyapp.data.remote

import br.com.samuelnunes.rickandmortyapp.data.entities.CharacterList
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * @author Samuel da Costa Araujo Nunes
 * Created 03/08/2021 at 16:26
 */
interface CharacterService {

    @GET("character")
    suspend fun getAllCharacters(
        @Query("page") page: Int? =  null,
        @Query("name") name: String? =  null
    ): CharacterList

}