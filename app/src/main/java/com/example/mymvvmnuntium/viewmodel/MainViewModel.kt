package com.example.mymvvmnuntium.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mymvvmnuntium.databse.ArticleDB
import com.example.mymvvmnuntium.modul.ItemDB
import com.example.mymvvmnuntium.repository.MainRepository
import com.example.mymvvmnuntium.utils.NetworkHelper
import com.example.mymvvmnuntium.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val mainRepository: MainRepository,
    private val networkHelper: NetworkHelper,

    ) : ViewModel() {
    private val _userFlow = MutableStateFlow<Resource>(Resource.Loading)
    val userFlow = _userFlow.asStateFlow()

    fun insertPost(article: ArticleDB) {
        viewModelScope.launch {
            mainRepository.insertPost(article)
        }
    }

    fun findById(id: String): Int {
        return mainRepository.findById(id)
    }
    fun findBySelectedId(id: String):Int{
        return mainRepository.findBySelected(id)
    }


    fun getPostFromDB() = mainRepository.getAllPost()

    fun getSelectedFromDB()=mainRepository.getAllSD()

    fun getTitleFromDB() = mainRepository.getAllTitles()

    fun getSelectedDB()=mainRepository.getAllSelected()


    fun insertSD(itemDB: ItemDB) {
        viewModelScope.launch {
            mainRepository.insertSD(itemDB)
        }
    }

    fun fetchUsers(query: String) {
        if (networkHelper.isNetworkConnected()) {
            viewModelScope.launch {
                _userFlow.value = Resource.Loading
                mainRepository.getPost(query)
                    .flowOn(Dispatchers.IO)
                    .catch {
                        _userFlow.value = Resource.Error(it.message!!)
                    }
                    .collect {
                        _userFlow.value = Resource.Success(it!!)
                    }
            }
        } else {
            _userFlow.value = Resource.Error("Internet")
        }
    }

    fun deletePost(post: String) {
        viewModelScope.launch(Dispatchers.IO) {
            mainRepository.delete(post)
        }
    }
    fun deletedSelected(itemDB:String){
        viewModelScope.launch (Dispatchers.IO){
            mainRepository.deleteSelected(itemDB)
        }
    }
}