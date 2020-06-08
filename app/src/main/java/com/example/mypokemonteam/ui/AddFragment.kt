package com.example.mypokemonteam.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider

import com.example.mypokemonteam.R
import com.example.mypokemonteam.model.DataPokemon
import kotlinx.android.synthetic.main.fragment_add.*

/**
 * A simple [Fragment] subclass.
 */
class AddFragment : Fragment() {

    private lateinit var viewModel: PokemonViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        initViewModel()
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViewModel(){
        viewModel = ViewModelProvider(activity as AppCompatActivity).get(PokemonViewModel::class.java)
    }

    private fun initViews(){
        btnAdd.setOnClickListener {
            if (viewModel.isPokemonValid(etPokemon.text.toString(), etNickname.text.toString())) {
                if (!viewModel.isPartyFull()) {
                    val dataPokemon = DataPokemon(
                        etPokemon.text.toString().toLowerCase(),
                        etNickname.text.toString()
                    )
                    viewModel.insertPokemon(dataPokemon)
                }
                else
                    Toast.makeText(requireContext(), getString(R.string.full_error) , Toast.LENGTH_LONG).show()
            }
            else
                Toast.makeText(requireContext(), getString(R.string.blank_error), Toast.LENGTH_SHORT).show()
        }
    }

}
