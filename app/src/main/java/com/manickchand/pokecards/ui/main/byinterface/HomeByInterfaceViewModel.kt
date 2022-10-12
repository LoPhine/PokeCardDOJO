package com.manickchand.pokecards.ui.main.byinterface

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.manickchand.pokecards.model.PokemonModel
import com.manickchand.pokecards.repository.PokeCardsRepositoryImpl
import com.manickchand.pokecards.ui.common.HomeBaseViewModel
import kotlinx.coroutines.launch

class HomeByInterfaceViewModel(private val pokeCardsRepositoryImpl: PokeCardsRepositoryImpl) :
    HomeBaseViewModel() {

    fun fetchPokemons(context: Context, resultListener: HomeByInterfaceListener) {
        viewModelScope.launch {
            try {
                var response = pokeCardsRepositoryImpl.getPokemons()
                if (!response.isNullOrEmpty()) {
                    setAllPokemonsList(context, response)
                    resultListener.setPokemons(response)
                } else {
                    resultListener.setPokemons(emptyList())
                }

            } catch (e: Exception) {
                resultListener.error()
            }
        }
    }

    fun filterList(filterStr: String?, resultListener: HomeByInterfaceListener) {
        val list = filterStr?.run {
            allPokemonsList.filter { pokemon ->
                pokemon.name.toLowerCase().contains(filterStr.toLowerCase())
            }
        } ?: allPokemonsList

        resultListener.setPokemons(list)
    }
}