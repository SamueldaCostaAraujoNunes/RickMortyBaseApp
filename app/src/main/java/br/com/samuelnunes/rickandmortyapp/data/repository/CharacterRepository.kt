package br.com.samuelnunes.rickandmortyapp.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import javax.inject.Inject
import br.com.samuelnunes.rickandmortyapp.data.entities.Character
import br.com.samuelnunes.rickandmortyapp.data.remote.CharacterService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import timber.log.Timber

/**
 * @author Samuel da Costa Araujo Nunes
 * Created 30/07/2021 at 12:57
 */
class CharacterRepository @Inject constructor(
    private val service: CharacterService
) {

    fun getAllCharacters(): LiveData<List<Character>> {
        return liveData { emit(service.getAllCharacters().results) }
    }
}