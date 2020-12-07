package com.esaldivia.melichallenge.ui.searchitems

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
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
        handleIntent(intent)

        setupRecyclerView()
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        intent?.let { handleIntent(it) }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_search, menu)

        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView = menu?.findItem(R.id.search)?.actionView as SearchView
        searchView.setIconifiedByDefault(false)
        searchView.apply {
            setSearchableInfo(searchManager.getSearchableInfo(componentName))
        }

        return true
    }

    private fun setupRecyclerView() {
        item_list_recycler_view.apply {
            layoutManager = LinearLayoutManager(baseContext)

            adapter = ItemListAdapter(viewModel.getItems())
        }
    }

    private fun handleIntent(intent: Intent) {

        when (intent.action) {
            Intent.ACTION_SEARCH -> intent.getStringExtra(SearchManager.QUERY)?.let { search(it) }
        }
    }

    fun search(query: String) {
        viewModel.getItems()
    }
}