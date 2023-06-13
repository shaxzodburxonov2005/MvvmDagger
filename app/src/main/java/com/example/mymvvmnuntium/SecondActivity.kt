package com.example.mymvvmnuntium

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.mymvvmnuntium.adapter.AdapterNews
import com.example.mymvvmnuntium.databinding.ActivitySecondBinding
import com.example.mymvvmnuntium.utils.Resource
import com.example.mymvvmnuntium.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


class SecondActivity : AppCompatActivity() {
    lateinit var binding:ActivitySecondBinding

    private val viewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)




    }
}