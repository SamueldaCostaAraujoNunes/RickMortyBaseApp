package br.com.samuelnunes.rickandmortyapp.ui.characterDetails

import androidx.lifecycle.ViewModel
import br.com.samuelnunes.rickandmortyapp.data.entities.Character
import br.com.samuelnunes.rickandmortyapp.data.repository.CharacterRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class CharacterDetailsViewModel @Inject constructor(
    private val characterRepository: CharacterRepository
): ViewModel() {
    suspend fun getCharacter(idCharacter: Int): Character? = characterRepository.getCharacter(idCharacter)
}