package com.esaldivia.melichallenge.ui.searchitems

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.*
import com.esaldivia.melichallenge.R
import com.esaldivia.melichallenge.model.Item
import com.esaldivia.melichallenge.network.SearchItem.SearchItemApi
import com.esaldivia.melichallenge.repository.Repository
import com.esaldivia.melichallenge.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject
import kotlin.coroutines.coroutineContext

class SearchItemViewModel @Inject constructor(val repository: Repository, application: Application) : AndroidViewModel(application) {
    val TAG = "SearchItemViewModel"

    private val _items = MutableLiveData<List<Item>>().apply { value = emptyList() }
    val items: LiveData<List<Item>> = _items

    fun searchItemByName(query: String) {
        viewModelScope.launch {
            val response = repository.searchItemByName(name = query)
            _items.value = response
        }

    }

    fun searchItem(name: String) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.succces(data = repository.search(name = name)))
        } catch (exception: Exception) {
            val context = getApplication<Application>().applicationContext
            emit(Resource.error(null, exception.message ?: context.getString(R.string.unexpected_error)))
        }
    }
}
