package com.polich.repository.ui.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import com.polich.repository.R
import com.polich.repository.Resource
import com.polich.repository.Result
import com.polich.repository.responce

class TrueFragment : Fragment() {
    companion object {
        fun newInstance() = TrueFragment()
    }

    private val viewModel: MainViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val view = inflater.inflate(R.layout.main_fragment, container, false)
        viewModel.character.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Success -> {
                    view.findViewById<ListView>(R.id.message).adapter =
                    ArrayAdapter<Result>(
                        requireContext(),
                        android.R.layout.simple_list_item_1,
                        it.data?.results ?: responce().results

                    )
                    Log.d("asd", it.data.toString())
                    view.findViewById<ProgressBar>(R.id.progressBar).visibility = View.GONE
                }
                is Resource.Loading -> {
                    view.findViewById<ProgressBar>(R.id.progressBar).visibility = View.VISIBLE
                }
            }
        }
        return view
    }
}