package com.manickchand.pokecards.ui.main.bylivedata

import android.os.Bundle
import com.manickchand.pokecards.model.PokemonModel
import com.manickchand.pokecards.ui.common.HomeAdapter
import com.manickchand.pokecards.ui.common.HomeBaseActivity
import com.manickchand.pokecards.utils.showToast
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeByLiveDataActivity : HomeBaseActivity() {

    private val viewModel: HomeByLiveDataViewModel by viewModel()
    private val pokemonsList = ArrayList<PokemonModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        baseViewModel = viewModel

        setupRecycler()
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

    private fun setupRecycler() =
        with(binding.recycler) {
            setHasFixedSize(true)
            adapter = HomeAdapter(pokemonsList, this@HomeByLiveDataActivity)
        }

    private fun showItems(pokemons: List<PokemonModel>) {
        pokemonsList.clear()
        pokemonsList.addAll(pokemons)
        binding.recycler.adapter?.notifyDataSetChanged()
    }

}