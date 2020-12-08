package com.esaldivia.melichallenge.ui.searchitems

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.esaldivia.melichallenge.R
import com.esaldivia.melichallenge.model.Item
import com.esaldivia.melichallenge.utils.Status
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

    private fun setupObservers(name: String) {
        viewModel.searchItem(name).observe(this, Observer {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        progressBar.visibility = View.GONE
                        resource.data?.let { response -> retrieveList(response.itemList) }
                    }
                    Status.ERROR -> {
                        progressBar.visibility = View.GONE
                        Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                    }
                    Status.LOADING -> {
                        progressBar.visibility = View.VISIBLE
                    }
                }
            }
        })
    }

    private fun retrieveList(items: ArrayList<Item>) {
        recyclerView.apply {
            adapter = ItemListAdapter(items)
            (adapter as ItemListAdapter).notifyDataSetChanged()
        }
    }

    private fun setupRecyclerView() {
        recyclerView.apply {
            layoutManager = LinearLayoutManager(baseContext)

            adapter = ItemListAdapter(arrayListOf())
        }
    }

    private fun handleIntent(intent: Intent) {

        when (intent.action) {
            Intent.ACTION_SEARCH -> intent.getStringExtra(SearchManager.QUERY)?.let { search(it) }
        }
    }

    fun search(query: String) {
        setupObservers(query)
        recyclerView.apply {
            val itemListAdapter = adapter as ItemListAdapter
            itemListAdapter.clearItems()
            itemListAdapter.notifyDataSetChanged()
        }
    }
}