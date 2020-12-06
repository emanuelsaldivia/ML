package com.esaldivia.melichallenge.ui.searchitems

import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProviders
import com.esaldivia.melichallenge.R
import com.esaldivia.melichallenge.viewmodels.ViewModelProviderFactory
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class SearchItemsActivity : DaggerAppCompatActivity() {
    val TAG: String = "SearchItemsActivity"

    @Inject
    lateinit var providerFactory: ViewModelProviderFactory

    val viewModel by viewModels<SearchItemViewModel> { providerFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_items)

    }
}