package com.polich.repository.ui.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import com.polich.repository.R
import com.polich.repository.Resource
import com.polich.repository.Result
import com.polich.repository.responce
import org.w3c.dom.Text
import kotlin.concurrent.fixedRateTimer

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
        val listView = view.findViewById<ListView>(R.id.message)
        viewModel.character.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Success -> {
                    listView.adapter =
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

        val but_tk : Button = view.findViewById(R.id.but_tk)
        val but_ok : Button = view.findViewById(R.id.but_ok)
        val block_info : LinearLayout = view.findViewById(R.id.info_linear)
        val textView : TextView = view.findViewById(R.id.textViewShowItem)
        val animHide = AnimationUtils.loadAnimation(requireContext(), R.anim.anim_hide)
        animHide.setAnimationListener(object : Animation.AnimationListener{
            override fun onAnimationStart(animation: Animation?) {}
            override fun onAnimationEnd(animation: Animation?) {
                listView.visibility = View.GONE
            }
            override fun onAnimationRepeat(animation: Animation?) {}
        })
        val animShow = AnimationUtils.loadAnimation(requireContext(), R.anim.test_view)
        animShow.setAnimationListener(object : Animation.AnimationListener{
            override fun onAnimationStart(animation: Animation?) {
                textView.visibility = View.VISIBLE
            }
            override fun onAnimationEnd(animation: Animation?) {
                listView.visibility = View.VISIBLE
            }
            override fun onAnimationRepeat(animation: Animation?) {}
        })


        val animShow2 = AnimationUtils.loadAnimation(requireContext(), R.anim.test_view)
        animShow2.setAnimationListener(object : Animation.AnimationListener{
            override fun onAnimationStart(animation: Animation?) {
                block_info.visibility = View.VISIBLE
                but_tk.visibility = View.GONE
            }
            override fun onAnimationEnd(animation: Animation?) {
                listView.visibility = View.VISIBLE
            }
            override fun onAnimationRepeat(animation: Animation?) {}
        })

        textView.setOnClickListener {
            it.visibility = View.GONE
            listView.visibility = View.VISIBLE

            but_tk.isClickable = true
            but_tk.isActivated = true
            but_tk.alpha = 1f
        }
        listView.setOnItemClickListener { parent, view1, position, id ->
            view1.startAnimation(animHide)
            textView.text = listView.adapter.getItem(position).toString()
            textView.startAnimation(animShow)
            listView.startAnimation(animHide)

            but_tk.isClickable = false
            but_tk.isActivated = false
            but_tk.alpha = 0.5f
            textView.visibility = View.GONE
        }

        but_tk.setOnClickListener {
            block_info.startAnimation(animShow2)
            listView.startAnimation(animHide)
            but_tk.startAnimation(animHide)
            textView.visibility = View.GONE
        }

        but_ok.setOnClickListener {
            //block_info.startAnimation(animHide)
            listView.startAnimation(animShow)
            block_info.visibility = View.GONE
            //listView.visibility = View.VISIBLE
            but_tk.visibility = View.VISIBLE
            textView.visibility = View.GONE
        }
        return view
    }
}