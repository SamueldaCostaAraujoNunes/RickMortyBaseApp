package br.com.samuelnunes.rickandmortyapp.data.local

import androidx.lifecycle.LiveData
import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import br.com.samuelnunes.rickandmortyapp.data.entities.Character

@Dao
interface CharacterDao {

    @Query("SELECT * FROM Character WHERE name LIKE :query")
    fun getPagingDataSource(query: String): PagingSource<Int,Character>

    @Query("SELECT * FROM Character WHERE id = :idCharacter")
    suspend fun getCharacter(idCharacter: Int): Character

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(characters: List<Character>)
}
