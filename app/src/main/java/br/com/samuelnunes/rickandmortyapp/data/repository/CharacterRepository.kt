package br.com.samuelnunes.rickandmortyapp.data.repository

import androidx.lifecycle.LiveData
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import javax.inject.Inject
import br.com.samuelnunes.rickandmortyapp.data.entities.Character
import br.com.samuelnunes.rickandmortyapp.data.local.AppDatabase
import br.com.samuelnunes.rickandmortyapp.data.local.CharacterDao
import br.com.samuelnunes.rickandmortyapp.data.remote.CharacterService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import timber.log.Timber

/**
 * @author Samuel da Costa Araujo Nunes
 * Created 30/07/2021 at 12:57
 */
class CharacterRepository @Inject constructor(
    private val service: CharacterService,
    private val database: AppDatabase
) {
    val dao: CharacterDao = database.characterDao()

    @ExperimentalPagingApi
    fun getCharacters(query: String): Flow<PagingData<Character>> = Pager(
        config = PagingConfig(
            pageSize = 20,
            enablePlaceholders = false
        ),
        pagingSourceFactory = { dao.getPagingDataSource("%$query%") },
        remoteMediator = CharacterRemoteMediador(query, dao, service)
    ).flow

    suspend fun getCharacter(idCharacter: Int): Character? = dao.getCharacter(idCharacter)

}