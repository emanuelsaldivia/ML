package com.esaldivia.melichallenge.ui.searchitems

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.esaldivia.melichallenge.model.Item
import com.esaldivia.melichallenge.network.SearchItem.SearchItemApi
import com.esaldivia.melichallenge.repository.Repository
import javax.inject.Inject

// todo deberia mandar una interfaz?
class SearchItemViewModel @Inject constructor(val repository: Repository) : ViewModel() {
    val TAG = "SearchItemViewModel"

    fun getItems(): List<Item> {
        return repository.getItems()
    }
}
