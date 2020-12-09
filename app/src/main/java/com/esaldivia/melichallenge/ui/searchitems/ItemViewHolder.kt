package com.esaldivia.melichallenge.ui.searchitems

import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.esaldivia.melichallenge.R
import org.w3c.dom.Text

class ItemViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.list_item, parent, false)) {

    var rootView: View = itemView.findViewById(R.id.list_item)

    var thumbnailImageView: ImageView = itemView.findViewById(R.id.item_thumbnail)

    var nameTextView: TextView = itemView.findViewById(R.id.item_name)

    var priveTextView: TextView = itemView.findViewById(R.id.item_price)

}