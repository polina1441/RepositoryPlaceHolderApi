package com.polich.repository.ui.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.polich.repository.ArchApplication
import com.polich.repository.Repository
import com.polich.repository.Resource
import com.polich.repository.todos.Todos
import com.polich.repository.responce
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val repository :Repository =(application as ArchApplication).repository
    init {
        loadTodos()
    }
    private val _todos : MutableLiveData<Resource<Todos>> = MutableLiveData()
    private val _character : MutableLiveData<Resource<responce>> = MutableLiveData()

    val todos : LiveData<Resource<Todos>> = _todos
    val character : LiveData<Resource<responce>> = _character

    private fun loadTodos(){
        viewModelScope.launch(Dispatchers.IO) {
            //_todos.postValue(Resource.Loading<Todos>())
            //_todos.postValue(Resource.Success<Todos>(repository.getTodos() ?: Todos()))
            _character.postValue(Resource.Loading<responce>())
            _character.postValue(Resource.Success<responce>(repository.getCharacter() ?: responce()))
        }
    }
}