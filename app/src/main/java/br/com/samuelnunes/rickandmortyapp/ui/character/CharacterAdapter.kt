package br.com.samuelnunes.rickandmortyapp.ui.character

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import br.com.samuelnunes.rickandmortyapp.data.entities.Character
import br.com.samuelnunes.rickandmortyapp.databinding.ItemCharacterBinding
import br.com.samuelnunes.rickandmortyapp.extensions.url

/**
 * @author Samuel da Costa Araujo Nunes
 * Created 03/08/2021 at 15:50
 */
class CharacterAdapter(private val listener: CharacterClickListener) : PagingDataAdapter<Character, CharacterAdapter.CharacterViewHolder>(CharacterAdapter) {

    private companion object : DiffUtil.ItemCallback<Character>() {
        override fun areItemsTheSame(oldItem: Character, newItem: Character): Boolean = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: Character, newItem: Character): Boolean = oldItem == newItem
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CharacterViewHolder {
        val binding = CharacterViewHolder.create(parent)
        return CharacterViewHolder(binding, listener)
    }

    override fun onBindViewHolder(
        holder: CharacterViewHolder,
        position: Int
    ) {
        val item = getItem(position)
        if (item != null) {
            holder.bind(item)
        }
    }

    interface CharacterClickListener {
        fun onClickedCharacter(character: Character)
    }

    class CharacterViewHolder(
        private val binding: ItemCharacterBinding,
        private val listener: CharacterClickListener) :
        RecyclerView.ViewHolder(binding.root) {

        private lateinit var character: Character
        private val onClick = View.OnClickListener { listener.onClickedCharacter(character)}

        init {
            binding.root.setOnClickListener(onClick)
        }

        companion object {
            fun create(parent: ViewGroup): ItemCharacterBinding =
                ItemCharacterBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        }

        fun bind(item: Character) {
            character = item
            binding.character = item
        }
    }

}