package com.example.mymvvmnuntium

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.mymvvmnuntium.adapter.SelectedAdapter
import com.example.mymvvmnuntium.databinding.FragmentCategorysBinding
import com.example.mymvvmnuntium.modul.SelectedItem
import com.example.mymvvmnuntium.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CategorysFragment : Fragment() {

    lateinit var binding: FragmentCategorysBinding
    private val viewModel: MainViewModel by viewModels()
    lateinit var list:ArrayList<SelectedItem>
    lateinit var adapter: SelectedAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_categorys, container, false)
        binding = FragmentCategorysBinding.bind(view)
        listAdd()
        adapter= SelectedAdapter(requireActivity(),ArrayList()){ post, positsion->
            val count=viewModel.findBySelectedId(post.nameSd!!)
            if (count==0){
                viewModel.insertSD(post.getSelected())
            }else{
                viewModel.deletedSelected(post.nameSd)
            }

        }
        adapter.submitList(list)


        binding.rv.adapter=adapter




        return view
    }
    private fun listAdd() {
        list= ArrayList()
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