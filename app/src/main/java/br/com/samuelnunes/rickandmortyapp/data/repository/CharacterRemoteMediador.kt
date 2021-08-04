package br.com.samuelnunes.rickandmortyapp.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import br.com.samuelnunes.rickandmortyapp.data.local.CharacterDao
import br.com.samuelnunes.rickandmortyapp.data.entities.Character
import br.com.samuelnunes.rickandmortyapp.data.remote.CharacterService
import retrofit2.HttpException
import timber.log.Timber
import java.io.IOException

@ExperimentalPagingApi
class CharacterRemoteMediador (
    private val query: String,
    private val dao: CharacterDao,
    private val service: CharacterService
) : RemoteMediator<Int, Character>() {

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, Character>
    ): MediatorResult {
        val pageSize = state.config.pageSize
        val page = when (loadType) {
            // Passe null para carregar a primeira pagina
            LoadType.REFRESH -> null
            // Já que REFRESH sempre carregará a primeira página da lista. Retorne imediatamente, informando o fim da paginação.
            LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
            //Devemos verificar explicitamente se o último item é `null` ao anexar,
            // uma vez que passar null para networkService é válido apenas para o
            // carregamento inicial. Se lastItem for `null`, significa que nenhum item
            // foi carregado após o REFRESH inicial e que não há mais itens para carregar.
            LoadType.APPEND -> {
                val lastItem = state.lastItemOrNull()
                    ?: return MediatorResult.Success(
                        endOfPaginationReached = true
                    )
                (lastItem.id / pageSize) + 1
            }
        }

        return try {
            val response = service.getAllCharacters(
                page = page,
                name = query
            )
            dao.insertAll(response.results)
            MediatorResult.Success(
                endOfPaginationReached = response.info.next == null
            )
        } catch (e: HttpException) {
            Timber.d(e, "Error during fetch")
            MediatorResult.Error(e)
        } catch (e: IOException) {
            Timber.d(e, "Error during fetch")
            MediatorResult.Error(e)
        }
    }

}
