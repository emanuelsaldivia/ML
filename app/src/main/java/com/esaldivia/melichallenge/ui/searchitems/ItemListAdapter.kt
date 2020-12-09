package com.esaldivia.melichallenge.ui.searchitems

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.request.RequestOptions
import com.esaldivia.melichallenge.BaseApplication
import com.esaldivia.melichallenge.R
import com.esaldivia.melichallenge.di.AppComponent
import com.esaldivia.melichallenge.model.Item
import com.esaldivia.melichallenge.ui.itemdisplay.ItemDisplayActivity
import com.esaldivia.melichallenge.utils.Constants
import com.esaldivia.melichallenge.utils.Util
import dagger.android.support.DaggerApplication
import java.text.NumberFormat
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList

class ItemListAdapter(private val itemList: ArrayList<Item>) : RecyclerView.Adapter<ItemViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ItemViewHolder(layoutInflater, parent)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = itemList[position]
        setupViewHolder(item, holder)
    }

    private fun setupViewHolder(item: Item, holder: ItemViewHolder) {
        setupGlide(holder, item)

        holder.nameTextView.text = item.name

        val currencyFormat = Util.getCurrencyFormat(item.currency)
        holder.priveTextView.text = currencyFormat.format(item.price)

        holder.rootView.setOnClickListener { v ->
            startItemDisplayActivity(v, item)
        }
    }

    private fun startItemDisplayActivity(
            v: View,
            item: Item
    ) {
        val context = v.context

        val intent = Intent(context, ItemDisplayActivity::class.java)
        intent.putExtra(Constants.ITEM_URL_KEY, item.url)

        context.startActivity(intent)
    }

    private fun setupGlide(
            holder: ItemViewHolder,
            item: Item
    ) {
        val requestOptions: RequestOptions = RequestOptions.placeholderOf(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_baseline_broken_image)

        Glide.with(holder.rootView.context)
                .setDefaultRequestOptions(requestOptions)
                .load(item.thumbnail.toUri())
                .into(holder.thumbnailImageView)
    }

    fun clearItems() {
        itemList.clear()
        notifyDataSetChanged()
    }

}