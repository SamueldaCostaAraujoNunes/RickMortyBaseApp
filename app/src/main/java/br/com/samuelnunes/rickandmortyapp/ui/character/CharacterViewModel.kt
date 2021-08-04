package br.com.samuelnunes.rickandmortyapp.ui.character

import androidx.lifecycle.*
import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import br.com.samuelnunes.rickandmortyapp.data.entities.Character
import br.com.samuelnunes.rickandmortyapp.data.repository.CharacterRepository
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(
    private val characterRepository: CharacterRepository
) : ViewModel() {

    @ExperimentalPagingApi
    fun characters(query: String): LiveData<PagingData<Character>> =
        characterRepository.getCharacters(query).cachedIn(viewModelScope).asLiveData()
}