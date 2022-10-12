package com.manickchand.pokecards.ui.main.bylivedata

import com.manickchand.pokecards.model.PokemonModel

interface MainListener {
    fun clickPokemon(pokemonModel: PokemonModel)
}