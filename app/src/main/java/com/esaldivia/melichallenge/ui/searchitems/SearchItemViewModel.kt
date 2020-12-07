package com.esaldivia.melichallenge.ui.searchitems

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.esaldivia.melichallenge.model.Item
import com.esaldivia.melichallenge.network.SearchItem.SearchItemApi
import javax.inject.Inject

// todo aca tengo que hacer que pida al repository
class SearchItemViewModel @Inject constructor(val searchItemApi: SearchItemApi) : ViewModel() {
    val TAG = "SearchItemViewModel"

}
