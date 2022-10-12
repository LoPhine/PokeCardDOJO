package com.manickchand.pokecards.ui.common

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.manickchand.pokecards.databinding.ItemPokemonBinding
import com.manickchand.pokecards.model.PokemonModel
import com.manickchand.pokecards.utils.loadGlideImage

class HomeAdapter(private val items: List<PokemonModel>, private val listener: HomeListener) :
    RecyclerView.Adapter<HomeAdapter.ViewHolder?>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val itemBinding = ItemPokemonBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return ViewHolder(itemBinding)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) = holder.bind(items[position])

    inner class ViewHolder(private val binding: ItemPokemonBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(pokemon: PokemonModel) {

            pokemon.run {

                binding.ivPokemon.apply {
                    loadGlideImage(context, imageurl)
                    setBackgroundColor(color)
                }
                binding.tvPokemon.text = name

                binding.tvHp.apply {
                    text = hp.toString()
                    setTextColor(color)
                }

                binding.cardPokemon.setOnClickListener { listener.clickPokemon(this) }
            }
        }

    }
}