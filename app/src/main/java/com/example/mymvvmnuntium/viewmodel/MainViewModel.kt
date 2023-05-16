package com.example.mymvvmnuntium.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mymvvmnuntium.databasemodul.Post
import com.example.mymvvmnuntium.repository.MainRepository
import com.example.mymvvmnuntium.utils.NetworkHelper
import com.example.mymvvmnuntium.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val mainRepository: MainRepository,
    private val networkHelper:NetworkHelper
):ViewModel() {
    private val _userFlow= MutableStateFlow<Resource>(Resource.Loading)
    val userFlow=_userFlow.asStateFlow()

    init {
        fetchUsers()
    }
    fun insertPost(post: Post) {
        viewModelScope.launch {
            mainRepository.insertPost(post)
        }
    }
    fun getPostFromDB()=mainRepository.getAllPost()



    private fun fetchUsers() {
        if (networkHelper.isNetworkConnected()){
            viewModelScope.launch {
                _userFlow.value=Resource.Loading
                mainRepository.getPost()
                    .flowOn(Dispatchers.IO)
                    .catch {
                        _userFlow.value=Resource.Error(it.message!!)
                    }
                    .collect{
                        if (it.isSuccessful){
                            _userFlow.value=Resource.Success(it.body()!!)
                        }else{
                            _userFlow.value=Resource.Error(it.message())
                        }
                    }
            }
        }else{
            _userFlow.value=Resource.Error("Internet")
        }
    }


}