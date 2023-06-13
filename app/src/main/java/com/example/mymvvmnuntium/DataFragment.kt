package com.example.mymvvmnuntium

import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.example.mymvvmnuntium.databinding.FragmentDataBinding
import com.example.mymvvmnuntium.databse.ArticleDB
import com.example.mymvvmnuntium.modul.newss.Article
import com.example.mymvvmnuntium.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint


@Suppress("DEPRECATION")
@AndroidEntryPoint
class DataFragment : Fragment() {
   lateinit var binding:FragmentDataBinding
    private val viewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_data, container, false)
        binding=FragmentDataBinding.bind(view)

        val a=arguments?.getSerializable("next") as Article

        Glide.with(requireContext()).load(a.urlToImage).into(binding.img)
        binding.text.text=a.description
        binding.collapsingToolbar.title=a.title



        return view
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).setSupportActionBar(binding.toolbar)
    }


    @Deprecated("Deprecated in Java", ReplaceWith("inflater.inflate(R.menu.toolbar_menu, menu)"))
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.toolbar_menu,menu)

    }


    @Deprecated("Deprecated in Java")
    override fun onOptionsItemSelected(item: MenuItem): Boolean {

         when(item.itemId){
            R.id.save_toolbar -> {

            }
             R.id.share-> Toast.makeText(requireContext(), "shared", Toast.LENGTH_SHORT).show()
        }
        return true
    }

}