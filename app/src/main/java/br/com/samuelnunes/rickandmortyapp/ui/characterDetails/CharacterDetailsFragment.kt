package br.com.samuelnunes.rickandmortyapp.ui.characterDetails

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import br.com.samuelnunes.rickandmortyapp.R
import br.com.samuelnunes.rickandmortyapp.databinding.CharacterDetailsFragmentBinding
import br.com.samuelnunes.rickandmortyapp.ui.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CharacterDetailsFragment : Fragment() {

    private val arguments by navArgs<CharacterDetailsFragmentArgs>()
    private lateinit var binding: CharacterDetailsFragmentBinding
    private val viewModel: CharacterDetailsViewModel by viewModels()
    private val mainViewModel: MainViewModel by activityViewModels()

    override fun onResume() {
        super.onResume()
        mainViewModel.hideSearchView()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return CharacterDetailsFragmentBinding.inflate(inflater, container, false).also{
            binding = it
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launch {
            binding.character = viewModel.getCharacter(arguments.idCharacter)
        }
    }
}