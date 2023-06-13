package com.example.mymvvmnuntium.access1

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController

import com.example.mymvvmnuntium.R
import com.example.mymvvmnuntium.adapter.SelectedAdapter
import com.example.mymvvmnuntium.databinding.FragmentThreeBinding
import com.example.mymvvmnuntium.modul.SelectedItem
import com.example.mymvvmnuntium.sharedPr.MySharedPreference
import com.example.mymvvmnuntium.sharedPr.MySharedPreference.editSplash
import com.example.mymvvmnuntium.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ThreeFragment : Fragment() {
    private val viewModel: MainViewModel by viewModels()
    lateinit var binding:FragmentThreeBinding
    lateinit var list:ArrayList<SelectedItem>
    lateinit var adapter: SelectedAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val view= inflater.inflate(R.layout.fragment_three, container, false)
        binding= FragmentThreeBinding.bind(view)
        MySharedPreference.init(requireContext())
        listAdd()


        adapter=SelectedAdapter(requireActivity(),list){post,positsion->
            val count=viewModel.findBySelectedId(post.nameSd!!)
            Log.d("SSS", "onCreateView: $count")
            if (count==0){
                viewModel.insertSD(post.getSelected())
            }else{
                viewModel.deletedSelected(post.nameSd)
            }
        }
        adapter.submitList(list)


        binding.rv.adapter=adapter

        binding.nextBtn.setOnClickListener {
            editSplash = true
            if (viewModel.getSelectedDB().isEmpty()) {
                Toast.makeText(requireContext(), "bitta tanlang", Toast.LENGTH_SHORT).show()
            } else {
                findNavController().navigate(R.id.homeFragment)
            }
        }
        return view
    }

    private fun listAdd() {
        list= ArrayList()
        list.add(SelectedItem("Sport",R.drawable.american_f))
        list.add(SelectedItem("Politics", R.drawable.balancedavid))
        list.add(SelectedItem("Life", R.drawable.sun_svgrepo_com))
        list.add(SelectedItem("Gaming", R.drawable.plsgame))
        list.add(SelectedItem("Animals", R.drawable.bearface))
        list.add(SelectedItem("Nature", R.drawable.emojipalma))
        list.add(SelectedItem("Food", R.drawable.twemoji))
        list.add(SelectedItem("Art", R.drawable.paint_palette_and_brush_svgrepo_com))
        list.add(SelectedItem("History", R.drawable.history))
        list.add(SelectedItem("Fashion", R.drawable.fashionsvg))
    }


}