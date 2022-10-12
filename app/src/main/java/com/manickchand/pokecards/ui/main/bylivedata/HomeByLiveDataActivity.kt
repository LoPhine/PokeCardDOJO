package com.manickchand.pokecards.ui.main.bylivedata

import android.os.Bundle
import android.util.Log
import com.manickchand.pokecards.ui.common.HomeBaseActivity
import com.manickchand.pokecards.ui.common.TAG_LOG
import com.manickchand.pokecards.utils.showToast
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeByLiveDataActivity : HomeBaseActivity() {

    private val viewModel: HomeByLiveDataViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.i(TAG_LOG, "INICIANDO DUMB VIEW POR LIVE DATA")
        baseViewModel = viewModel

        bindObservables()
        viewModel.fetchPokemons(this)
    }

    private fun bindObservables() {
        viewModel.getPokemonLiveData().observe(this, { pokemons ->
            binding.load.isRefreshing = false
            showItems(pokemons)
        })

        viewModel.getErrorLiveData().observe(this, { isError ->
            binding.load.isRefreshing = false
            if (isError) showToast("Erro ao carregar pokemons")
        })
    }

}