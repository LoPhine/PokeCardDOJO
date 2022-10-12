package com.manickchand.pokecards.ui.main.bygenericviewstate

import android.os.Bundle
import android.util.Log
import com.manickchand.pokecards.ui.common.HomeBaseActivity
import com.manickchand.pokecards.ui.common.TAG_LOG
import com.manickchand.pokecards.utils.showToast
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeByGenericViewStateActivity : HomeBaseActivity() {

    private val viewModel: HomeByGenericViewStateViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i(TAG_LOG, "INICIANDO DUMB VIEW POR VIEW STATE GENERICO")

        baseViewModel = viewModel

        bindObservables()
        viewModel.fetchPokemons(this)
    }

    private fun bindObservables() {
        viewModel.getPokemonLiveData().observe(this, { state ->

            when(state){
                is ViewState.Loading ->{
                    binding.load.isRefreshing = true
                }
                is ViewState.Success ->{
                    binding.load.isRefreshing = false
                    showItems(state.data)
                }
                is ViewState.Failed ->{
                    binding.load.isRefreshing = false
                    showToast("Erro ao carregar pokemons")
                }
            }
        })
    }

}