package com.example.mymvvmnuntium.viewpageradapter

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController

import com.example.mymvvmnuntium.R
import com.example.mymvvmnuntium.adapter.PostAdapter
import com.example.mymvvmnuntium.databinding.FragmentViewpagerHomeTabBinding

import com.example.mymvvmnuntium.modul.ItemDB
import com.example.mymvvmnuntium.modul.newss.Article

import com.example.mymvvmnuntium.utils.Resource
import com.example.mymvvmnuntium.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

import kotlinx.coroutines.launch

@Suppress("DEPRECATION")
@AndroidEntryPoint
class ViewpagerHomeTabFragment : Fragment() {
    private val viewModel: MainViewModel by viewModels()
    lateinit var binding: FragmentViewpagerHomeTabBinding
    lateinit var adapter: PostAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_viewpager_home_tab, container, false)
        binding = FragmentViewpagerHomeTabBinding.bind(view)
        val m = arguments?.getSerializable("viewTab") as ItemDB

        adapter= PostAdapter(requireContext(),ArrayList(),object :PostAdapter.itemClick{
            override fun rootI(article: Article, position: Int) {
                val bundle=Bundle()
                bundle.putSerializable("next",article)
                findNavController().navigate(R.id.dataFragment,bundle)
            }

            override fun save(article: Article, position: Int) {
                val count = viewModel.findById(article.title!!)
                Log.d("AAA", "onCreateView: $count")
                if (count == 0) {
                    viewModel.insertPost(article.getArticleDb())
                } else {
                    viewModel.deletePost(article.title!!)
                }
            }

        })



        viewModel.fetchUsers(m.name!!)
        binding.rvViewpager.adapter = adapter

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                //db
//                val newListForDb = ArrayList<ArticleDB>() // database ni classni berganmisz ?
//                viewModel.getPostFromDB().collect{
//                    newListForDb.addAll(it)
//                }
                // internet
                viewModel.userFlow.collect {
                    when (it) {
                        is Resource.Success -> {
                            adapter.submitList(it.list)
                        }
                        is Resource.Loading -> Log.d("AAA", "loading: ")
                        is Resource.Error -> Log.d("AAA", "onCreateView:${it.message} ")
                    }
                }
            }
        }
        return view
    }


}