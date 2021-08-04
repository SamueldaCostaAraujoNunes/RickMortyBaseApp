package br.com.samuelnunes.rickandmortyapp.ui.character

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import br.com.samuelnunes.rickandmortyapp.R
import br.com.samuelnunes.rickandmortyapp.databinding.CharacterFragmentBinding
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class CharacterFragment : Fragment() {

    private lateinit var binding: CharacterFragmentBinding
    private val viewModel: CharactersViewModel by viewModels()
    val adapter: CharacterAdapter = CharacterAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return CharacterFragmentBinding.inflate(inflater, container, false).also{
            binding = it
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.charactersRv.adapter = adapter
        viewModel.characters.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
    }

}