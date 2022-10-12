package com.manickchand.pokecards.ui.common

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import com.manickchand.pokecards.databinding.ActivityMainBinding
import com.manickchand.pokecards.model.PokemonModel
import com.manickchand.pokecards.ui.detail.DetailDialogFragment

const val TAG_LOG = "DUMBVIEWSTARTED"

open class HomeBaseActivity : AppCompatActivity(), HomeListener {

    val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    var baseViewModel: HomeBaseViewModel? = null
    val pokemonsList = ArrayList<PokemonModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupRefresh()
        setupFilter()
        setupRecycler()
    }

    private fun setupRecycler() =
        with(binding.recycler) {
            setHasFixedSize(true)
            adapter = HomeAdapter(pokemonsList, this@HomeBaseActivity)
        }

    private fun setupRefresh() {
        binding.load.apply {
            isRefreshing = true
            setOnRefreshListener {
                baseViewModel?.fetchPokemons(this@HomeBaseActivity)
            }
        }
    }

    fun showItems(pokemons: List<PokemonModel>) {
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
                baseViewModel?.filterList(s.toString())
            }
        })
    }
}