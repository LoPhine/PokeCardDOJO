package com.manickchand.pokecards.ui.main.bygenericviewstate

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.manickchand.pokecards.model.PokemonModel
import com.manickchand.pokecards.repository.PokeCardsRepositoryImpl
import com.manickchand.pokecards.ui.common.HomeBaseViewModel
import kotlinx.coroutines.launch

class HomeByGenericViewStateViewModel(private val pokeCardsRepositoryImpl: PokeCardsRepositoryImpl) :
    HomeBaseViewModel() {

    private val pokemonLiveData = MutableLiveData<ViewState<List<PokemonModel>>>()

    fun getPokemonLiveData() = pokemonLiveData as LiveData<ViewState<List<PokemonModel>>>

    override fun fetchPokemons(context: Context) {
        viewModelScope.launch {
            try {
                pokemonLiveData.value = ViewState.Loading
                var response = pokeCardsRepositoryImpl.getPokemons()
                if (!response.isNullOrEmpty()) {
                    setAllPokemonsList(context, response)
                    pokemonLiveData.value = ViewState.Success(response)
                } else {
                    pokemonLiveData.value = ViewState.Success(emptyList())
                }

            } catch (e: Exception) {
                pokemonLiveData.value = ViewState.Failed(e)
            }
        }
    }

    override fun filterList(filterStr: String?) {

        val list = getListFiltered(filterStr)

        pokemonLiveData.value = ViewState.Success(list)
    }
}