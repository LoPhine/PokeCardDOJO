package com.manickchand.pokecards.ui.main.bylivedata

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import com.manickchand.pokecards.databinding.ActivityMainBinding
import com.manickchand.pokecards.model.PokemonModel
import com.manickchand.pokecards.ui.detail.DetailDialogFragment
import com.manickchand.pokecards.utils.showToast
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainLiveDataActivity : AppCompatActivity(), MainListener {

    private val viewModel: MainViewModel by viewModel()
    private val pokemonsList = ArrayList<PokemonModel>()

    private val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setupRefresh()
        setupRecycler()
        bindObservables()
        setupFilter()
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

    private fun setupRefresh() {
        binding.load.apply {
            isRefreshing = true
            setOnRefreshListener {
                viewModel.fetchPokemons(this@MainLiveDataActivity)
            }
        }
    }

    private fun setupRecycler() =
        with(binding.recycler) {
            setHasFixedSize(true)
            adapter = MainAdapter(pokemonsList, this@MainLiveDataActivity)
        }

    private fun showItems(pokemons: List<PokemonModel>) {
        pokemonsList.clear()
        pokemonsList.addAll(pokemons)
        binding.recycler.adapter?.notifyDataSetChanged()
    }

    override fun clickPokemon(pokemonModel: PokemonModel) {
        DetailDialogFragment.newInstance(pokemonModel, supportFragmentManager)
    }

    private fun setupFilter() {
        binding.llHeader.etFilter.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                viewModel.filterList(s.toString())
            }
        })
    }

}