package com.manickchand.pokecards.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.manickchand.pokecards.databinding.FragmentDetailBinding
import com.manickchand.pokecards.model.PokemonModel
import com.manickchand.pokecards.utils.loadGlideImage

class DetailDialogFragment : DialogFragment() {

    private val binding: FragmentDetailBinding by lazy {
        FragmentDetailBinding.inflate(
            layoutInflater
        )
    }

    private val pokemonModel by lazy { requireArguments().getSerializable(EXTRA_POKEMON) as PokemonModel }

    companion object {
        private const val EXTRA_POKEMON = "pokemon"

        @JvmStatic
        fun newInstance(pokemonModel: PokemonModel, fragmentManager: FragmentManager) =
            DetailDialogFragment().apply {
                arguments = bundleOf(EXTRA_POKEMON to pokemonModel)
                show(fragmentManager, "Card Pokemon")
            }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = binding.root


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setViewData()
    }

    private fun setViewData() {

        pokemonModel.run {

            binding.tvName.text = name
            binding.ivPokemon.loadGlideImage(requireContext(), imageurl)
            binding.tvDescription.text = xdescription

            binding.tvType.apply {
                text = typeofpokemon.first()
                setTextColor(color)
            }

            binding.tvHp.apply {
                text = hp.toString()
                setTextColor(color)
            }

            binding.tvDf.apply {
                text = defense.toString()
                setTextColor(color)
            }

            binding.tvAt.apply {
                text = attack.toString()
                setTextColor(color)
            }

            binding.cardDetailPokemon.setCardBackgroundColor(color)
        }
    }

}