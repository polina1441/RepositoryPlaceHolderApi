package com.polich.repository.ui.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.polich.repository.ArchApplication
import com.polich.repository.Repository
import com.polich.repository.Resource
import com.polich.repository.Todos
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val repository :Repository =(application as ArchApplication).repository
    init {
        loadTodos()
    }
    private val _todos : MutableLiveData<Resource<Todos>> = MutableLiveData()

    val todos : LiveData<Resource<Todos>> = _todos

    private fun loadTodos(){
        viewModelScope.launch(Dispatchers.IO) {
            _todos.postValue(Resource.Loading<Todos>())
            _todos.postValue(Resource.Success<Todos>(repository.getTodos() ?: Todos()))
        }
    }
}