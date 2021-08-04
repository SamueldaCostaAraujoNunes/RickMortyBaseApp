package br.com.samuelnunes.rickandmortyapp.ui.character

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.paging.ExperimentalPagingApi
import br.com.samuelnunes.rickandmortyapp.data.entities.Character
import br.com.samuelnunes.rickandmortyapp.databinding.CharacterFragmentBinding
import br.com.samuelnunes.rickandmortyapp.ui.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharacterFragment : Fragment(), CharacterAdapter.CharacterClickListener {

    private lateinit var binding: CharacterFragmentBinding
    private val viewModel: CharactersViewModel by viewModels()
    val adapter: CharacterAdapter = CharacterAdapter(this)
    private val mainViewModel: MainViewModel by activityViewModels()

    override fun onResume() {
        super.onResume()
        mainViewModel.showSearchView()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return CharacterFragmentBinding.inflate(inflater, container, false).also {
            binding = it
        }.root
    }

    @ExperimentalPagingApi
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
    }

    @ExperimentalPagingApi
    private fun setupRecyclerView() {
        binding.charactersRv.adapter = adapter
        updateData()
        mainViewModel.query.observe(viewLifecycleOwner, {
            updateData(it)
        })
    }

    @ExperimentalPagingApi
    private fun updateData(query: String = "") {
        viewModel.characters(query).observe(viewLifecycleOwner) {
            adapter.submitData(lifecycle, it)
        }
    }

    override fun onClickedCharacter(character: Character) {
        val direction =
            CharacterFragmentDirections
                .actionCharacterFragmentToCharacterDetailsFragment(character.id)
        findNavController().navigate(direction)
    }

}