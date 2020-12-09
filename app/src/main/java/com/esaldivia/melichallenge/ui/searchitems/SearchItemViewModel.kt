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

    fun searchItem(name: String) = repository.renombrar(name,
        getApplication<Application>().applicationContext)
}
