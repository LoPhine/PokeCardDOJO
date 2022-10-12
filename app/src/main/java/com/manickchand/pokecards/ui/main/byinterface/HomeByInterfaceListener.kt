package com.manickchand.pokecards.ui.main.byinterface

import com.manickchand.pokecards.model.PokemonModel

interface HomeByInterfaceListener {
    fun setPokemons(pokemons:  List<PokemonModel>)
    fun startLoad()
    fun error()
}