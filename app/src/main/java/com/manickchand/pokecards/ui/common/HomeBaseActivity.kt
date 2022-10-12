package com.manickchand.pokecards.ui.common

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import com.manickchand.pokecards.databinding.ActivityMainBinding
import com.manickchand.pokecards.model.PokemonModel
import com.manickchand.pokecards.ui.detail.DetailDialogFragment

open class HomeBaseActivity : AppCompatActivity(), HomeListener {

    val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    var baseViewModel: HomeBaseViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupRefresh()
        setupFilter()
    }

    private fun setupRefresh() {
        binding.load.apply {
            isRefreshing = true
            setOnRefreshListener {
                baseViewModel?.fetchPokemons(this@HomeBaseActivity)
            }
        }
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