package com.manickchand.pokecards.ui.common

import com.manickchand.pokecards.model.PokemonModel

interface HomeListener {
    fun clickPokemon(pokemonModel: PokemonModel)
}