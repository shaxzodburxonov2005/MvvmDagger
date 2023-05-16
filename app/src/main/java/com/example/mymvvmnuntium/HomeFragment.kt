package com.example.mymvvmnuntium

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.mymvvmnuntium.adapter.PostAdapter
import com.example.mymvvmnuntium.databinding.FragmentHomeBinding
import com.example.mymvvmnuntium.utils.Resource
import com.example.mymvvmnuntium.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlin.math.log

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private val viewModel:MainViewModel by viewModels()
    lateinit var binding:FragmentHomeBinding
    private lateinit var adapter:PostAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_home, container, false)
        binding= FragmentHomeBinding.bind(view)

        adapter=PostAdapter{post, positsion ->

        }
        val itemDecoration=DividerItemDecoration(requireContext(),DividerItemDecoration.VERTICAL)
        binding.rvPost.addItemDecoration(itemDecoration)
        binding.rvPost.adapter=adapter
        viewLifecycleOwner.lifecycleScope.launch{
            repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.userFlow.collect{
                    when(it){
                        is Resource.Success-> {
                            adapter.submitList(it.list)
                            Log.d("BBB", "onCreate: ${it.list} ")
                        }
                        is Resource.Loading-> Log.d("AAA", "loading: ")
                        is Resource.Error -> Log.d("AAA", "onCreateView:${it.message} ")
                    }
                }
            }
        }

        return view
    }

}