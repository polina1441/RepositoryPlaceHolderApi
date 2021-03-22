package com.polich.repository.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.ProgressBar
import androidx.lifecycle.observe
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.polich.repository.R
import com.polich.repository.Resource
import com.polich.repository.Todos
import com.polich.repository.TodosItem

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
        viewModel.todos.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Success -> {
                    view.findViewById<ListView>(R.id.message).adapter =
                    ArrayAdapter<TodosItem>(
                        requireContext(),
                        android.R.layout.simple_list_item_1,
                        it.data ?: Todos()

                    )
                    view.findViewById<ProgressBar>(R.id.progressBar).visibility = View.GONE
                }
                is Resource.Loading -> {
                    view.findViewById<ProgressBar>(R.id.progressBar).visibility = View.VISIBLE
                }
            }
        }
        /* viewModel.user.observe(viewLifecycleOwner) {
             when (it) {
                 is Resource.Loading -> {
                     view.findViewById<ProgressBar>(R.id.progressBar).visibility = View.VISIBLE
                 }
                 is Resource.Success -> {
                     view.findViewById<ProgressBar>(R.id.progressBar).visibility = View.GONE
                 }
             }
         }*/
        return view
    }
}