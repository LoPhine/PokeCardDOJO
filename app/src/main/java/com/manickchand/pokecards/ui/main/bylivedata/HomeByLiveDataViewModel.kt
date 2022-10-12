package com.manickchand.pokecards.ui.main.bylivedata

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.manickchand.pokecards.model.PokemonModel
import com.manickchand.pokecards.repository.PokeCardsRepositoryImpl
import com.manickchand.pokecards.ui.common.HomeBaseViewModel
import kotlinx.coroutines.launch

class HomeByLiveDataViewModel(private val pokeCardsRepositoryImpl: PokeCardsRepositoryImpl) :
    HomeBaseViewModel() {

    private val pokemonLiveData = MutableLiveData<List<PokemonModel>>()
    private val errorLiveData = MutableLiveData<Boolean>()

    fun getPokemonLiveData() = pokemonLiveData as LiveData<List<PokemonModel>>
    fun getErrorLiveData() = errorLiveData as LiveData<Boolean>

    override fun fetchPokemons(context: Context) {
        viewModelScope.launch {
            try {
                var response = pokeCardsRepositoryImpl.getPokemons()
                if (!response.isNullOrEmpty()) {
                    setAllPokemonsList(context, response)
                    pokemonLiveData.value = allPokemonsList
                    errorLiveData.value = false
                } else {
                    pokemonLiveData.value = emptyList()
                }

            } catch (e: Exception) {
                errorLiveData.value = true
            }
        }
    }

    private fun setAllPokemonsList(context: Context, pokemons: List<PokemonModel>) {
        allPokemonsList.clear()
        allPokemonsList.addAll(pokemons)
        setColorPokemons(context)
    }

    override fun filterList(filterStr: String?) {
        pokemonLiveData.value = filterStr?.run {
            allPokemonsList.filter { pokemon ->
                pokemon.name.toLowerCase().contains(filterStr.toLowerCase())
            }
        } ?: allPokemonsList
    }
}