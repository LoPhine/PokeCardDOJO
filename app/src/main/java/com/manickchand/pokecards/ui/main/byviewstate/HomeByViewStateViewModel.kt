package com.manickchand.pokecards.ui.main.byviewstate

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.manickchand.pokecards.model.PokemonModel
import com.manickchand.pokecards.repository.PokeCardsRepositoryImpl
import com.manickchand.pokecards.ui.common.HomeBaseViewModel
import kotlinx.coroutines.launch

class HomeByViewStateViewModel(private val pokeCardsRepositoryImpl: PokeCardsRepositoryImpl) :
    HomeBaseViewModel() {

    private val pokemonLiveData = MutableLiveData<List<PokemonModel>>()

    fun getPokemonLiveData() = pokemonLiveData as LiveData<List<PokemonModel>>

    override fun fetchPokemons(context: Context) {
        viewModelScope.launch {
            try {
                var response = pokeCardsRepositoryImpl.getPokemons()
                if (!response.isNullOrEmpty()) {
                    setAllPokemonsList(context, response)
                    //TODO
                } else {
                    //TODO
                }

            } catch (e: Exception) {

            }
        }
    }

    override fun filterList(filterStr: String?) {
        val list = getListFiltered(filterStr)
        //TODO
    }

    sealed class HomeViewState() {
        class Success(Data: List<PokemonModel>) : HomeViewState()
        object PokemonsListEmpty : HomeViewState()
        object Error : HomeViewState()
        object Loading : HomeViewState()
    }
}