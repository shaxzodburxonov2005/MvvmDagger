package com.example.mymvvmnuntium

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.mymvvmnuntium.adapter.PostAdapter
import com.example.mymvvmnuntium.adapter.SearchAdapter
import com.example.mymvvmnuntium.databinding.FragmentSearchBinding
import com.example.mymvvmnuntium.modul.newss.Article
import com.example.mymvvmnuntium.utils.Resource
import com.example.mymvvmnuntium.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchFragment : Fragment() {
    private val viewModel: MainViewModel by viewModels()
    lateinit var binding: FragmentSearchBinding
    lateinit var postAdapter: PostAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_search, container, false)

        binding = FragmentSearchBinding.bind(view)

        showSoftKeyboard(requireContext(), binding.etSearch)

        postAdapter= PostAdapter(requireContext(),ArrayList(),object :PostAdapter.itemClick{
            override fun rootI(article: Article, position: Int) {
                findNavController().navigate(R.id.dataFragment)
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

        binding.etSearch.addTextChangedListener {
          if (it?.length==0){
              Toast.makeText(requireContext(), "Enter information to search ", Toast.LENGTH_SHORT).show()
          }else{
              viewModel.fetchUsers(it.toString())
          }

        }
        binding.rvSearch.adapter=postAdapter
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {

                viewModel.userFlow.collect {
                    when (it) {
                        is Resource.Success -> {
                            postAdapter.submitList(it.list)
                        }
                        is Resource.Loading -> Log.d("AAA", "loading: ")
                        is Resource.Error -> Log.d("AAA", "onCreateView:${it.message} ")
                    }
                }
            }
        }



//        binding.etSearch.addTextChangedListener(object : TextWatcher {
//            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
//
//            }
//
//            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
//
//            }
//
//            override fun afterTextChanged(s: Editable?) {
//
//            }
//        })

        return view
    }

    private fun searchFun(string: String) {

    }

    private fun showSoftKeyboard(context: Context, editText: EditText) {
        try {
            editText.requestFocus()
            editText.postDelayed(
                {
                    val keyboard =
                        context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    keyboard.showSoftInput(editText, 0)
                }, 200
            )
        } catch (npe: NullPointerException) {
            npe.printStackTrace()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }


}