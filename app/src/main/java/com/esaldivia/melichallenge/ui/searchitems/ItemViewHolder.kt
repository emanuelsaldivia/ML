package com.esaldivia.melichallenge.ui.searchitems

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.esaldivia.melichallenge.R

class ItemViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.list_item, parent, false)) {

    var mNameView: TextView = itemView.findViewById(R.id.item_name)

}