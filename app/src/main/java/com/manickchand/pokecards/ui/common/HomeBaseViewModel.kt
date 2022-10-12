package com.manickchand.pokecards.ui.common

import android.content.Context
import androidx.lifecycle.ViewModel
import com.manickchand.pokecards.model.PokemonModel
import com.manickchand.pokecards.utils.getPokemonColor

open class HomeBaseViewModel : ViewModel() {

    val allPokemonsList = ArrayList<PokemonModel>()

    open fun fetchPokemons(context: Context) {}
    open fun filterList(filterStr: String?) {}

    fun setAllPokemonsList(context: Context, pokemons: List<PokemonModel>) {
        allPokemonsList.clear()
        allPokemonsList.addAll(pokemons)
        setColorPokemons(context)
    }

    private fun setColorPokemons(context: Context) {
        allPokemonsList.forEach {
            it.color = getPokemonColor(context, it.typeofpokemon.firstOrNull() ?: "")
        }
    }
}