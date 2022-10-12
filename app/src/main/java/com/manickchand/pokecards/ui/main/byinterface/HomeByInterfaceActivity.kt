package com.manickchand.pokecards.ui.main.byinterface

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import com.manickchand.pokecards.model.PokemonModel
import com.manickchand.pokecards.ui.common.HomeBaseActivity
import com.manickchand.pokecards.ui.common.TAG_LOG
import com.manickchand.pokecards.utils.showToast
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeByInterfaceActivity : HomeBaseActivity(), HomeByInterfaceListener {

    private val viewModel: HomeByInterfaceViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.i(TAG_LOG, "INICIANDO DUMB VIEW POR INTERFACE")
        baseViewModel = viewModel
        setupFilter()
        setupRefresh()

        startLoad()
        viewModel.fetchPokemons(this, this)
    }

    override fun setPokemons(pokemons: List<PokemonModel>) {
        binding.load.isRefreshing = false
        showItems(pokemons)
    }

    override fun startLoad() {
        binding.load.isRefreshing = true
    }

    override fun error() {
        binding.load.isRefreshing = false
        showToast("Erro ao carregar pokemons")
    }

    private fun setupRefresh() {
        binding.load.apply {
            setOnRefreshListener {
                viewModel?.fetchPokemons(this@HomeByInterfaceActivity, this@HomeByInterfaceActivity)
            }
        }
    }

    private fun setupFilter() {
        binding.llHeader.etFilter.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                viewModel?.filterList(s.toString(), this@HomeByInterfaceActivity)
            }
        })
    }

}