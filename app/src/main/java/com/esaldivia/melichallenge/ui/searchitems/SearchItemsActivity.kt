package com.esaldivia.melichallenge.ui.searchitems

import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.esaldivia.melichallenge.R
import com.esaldivia.melichallenge.viewmodels.ViewModelProviderFactory
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_search_items.*
import javax.inject.Inject

class SearchItemsActivity : DaggerAppCompatActivity() {
    val TAG: String = "SearchItemsActivity"

    @Inject
    lateinit var providerFactory: ViewModelProviderFactory

    val viewModel by viewModels<SearchItemViewModel> { providerFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_items)

        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        item_list_recycler_view.apply {
            layoutManager = LinearLayoutManager(baseContext)

            adapter = ItemListAdapter(viewModel.getItems())
        }
    }
}