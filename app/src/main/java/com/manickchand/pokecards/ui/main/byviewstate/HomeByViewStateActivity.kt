package com.manickchand.pokecards.ui.main.byviewstate

import android.os.Bundle
import android.util.Log
import com.manickchand.pokecards.ui.common.HomeBaseActivity
import com.manickchand.pokecards.ui.common.TAG_LOG
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeByViewStateActivity : HomeBaseActivity() {

    private val viewModel: HomeByViewStateViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.i(TAG_LOG, "INICIANDO DUMB VIEW POR VIEWSTATE ESPECIFICOS")
        baseViewModel = viewModel

        bindObservables()
        viewModel.fetchPokemons(this)
    }

    private fun bindObservables() {
        viewModel.getPokemonLiveData().observe(this, { state ->
           //TODO
        })
    }

}