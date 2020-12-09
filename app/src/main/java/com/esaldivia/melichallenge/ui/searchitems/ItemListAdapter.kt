package com.esaldivia.melichallenge.ui.searchitems

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.esaldivia.melichallenge.model.Item
import com.esaldivia.melichallenge.ui.itemdisplay.ItemDisplayActivity
import com.esaldivia.melichallenge.utils.Constants

class ItemListAdapter(val itemList: ArrayList<Item>) : RecyclerView.Adapter<ItemViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ItemViewHolder(layoutInflater, parent)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = itemList[position]

        holder.nameTextView.text = item.name

        holder.rootView.setOnClickListener { v ->
            val context = v.context

            val intent = Intent(context, ItemDisplayActivity::class.java)
            intent.putExtra(Constants.ITEM_URL_KEY, item.url)

            context.startActivity(intent)
        }
    }

    fun clearItems() {
        itemList.clear()
        notifyDataSetChanged()
    }

}