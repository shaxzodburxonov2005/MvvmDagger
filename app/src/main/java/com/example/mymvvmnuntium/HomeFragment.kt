package com.example.mymvvmnuntium

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.activity.addCallback
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.mymvvmnuntium.adapter.AdapterRvHome
import com.example.mymvvmnuntium.adapter.PostAdapter
import com.example.mymvvmnuntium.databinding.FragmentHomeBinding
import com.example.mymvvmnuntium.modul.SelectedItem
import com.example.mymvvmnuntium.utils.Resource
import com.example.mymvvmnuntium.viewmodel.MainViewModel
import com.example.mymvvmnuntium.viewpageradapter.AdapterTabHome
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.forEach
import kotlinx.coroutines.launch
import java.util.*
import kotlin.collections.ArrayList

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private val viewModel: MainViewModel by viewModels()
    lateinit var binding: FragmentHomeBinding

    lateinit var list: ArrayList<SelectedItem>
    lateinit var adapterTabHome: AdapterTabHome
    lateinit var adapterRvHome: AdapterRvHome

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        binding = FragmentHomeBinding.bind(view)

        adapterRvHome = AdapterRvHome(requireContext()) { post, positison ->

        }

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.getSelectedFromDB().collect {

                    adapterTabHome = AdapterTabHome(requireActivity(), it)
                    binding.view2.adapter = adapterTabHome
                    TabLayoutMediator(binding.tabAll, binding.view2) { tab, positsiom ->
                        tab.text = it[positsiom].name
                    }.attach()

                }
            }
        }
        binding.searchTv.setOnClickListener {
            findNavController().navigate(R.id.searchFragment)
        }


            val itemDecoration =
                DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL)
            // val name = argument?.get....

            //  viewModel.fetchUsers(name)


            return view
        }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().onBackPressedDispatcher.addCallback(this) {
            requireActivity().finish()
        }
    }
    }

