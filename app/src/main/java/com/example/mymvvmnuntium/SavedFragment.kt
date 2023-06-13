package com.example.mymvvmnuntium

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.mymvvmnuntium.adapter.AdapterNews
import com.example.mymvvmnuntium.databinding.FragmentSavedBinding
import com.example.mymvvmnuntium.databse.ArticleDB
import com.example.mymvvmnuntium.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SavedFragment : Fragment() {
    lateinit var binding: FragmentSavedBinding
    private val viewModel: MainViewModel by viewModels()
    lateinit var adapterNews: AdapterNews
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_saved, container, false)
        binding = FragmentSavedBinding.bind(view)

        adapterNews= AdapterNews(requireContext(),ArrayList(),object :AdapterNews.SavedData{
            override fun savedData(articleDB: ArticleDB, position: Int) {
                viewModel.deletePost(articleDB.title!!)
                if (articleDB.title.isNotEmpty()) {
                    binding.btnFloat.visibility = View.INVISIBLE
                    binding.textSave.visibility = View.INVISIBLE
                    binding.rvSaveBook.visibility = View.VISIBLE
                }
            }

            override fun nextData(article: ArticleDB, position: Int) {
                val bundle=Bundle()
                bundle.putSerializable("data",article)
                findNavController().navigate(R.id.savedDataFragment,bundle)
            }

        })



        binding.rvSaveBook.adapter = adapterNews

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.getPostFromDB().collect {
                    adapterNews.submitList(it)
                    Log.d("RRR", "onCreateView: $it")
                    if (it.isNotEmpty()) {
                        binding.btnFloat.visibility = View.INVISIBLE
                        binding.textSave.visibility = View.INVISIBLE
                        binding.rvSaveBook.visibility = View.VISIBLE
                    }


                }
            }

        }






        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().onBackPressedDispatcher.addCallback(this) {
            requireActivity().finish()
        }
    }


}