package com.example.kotlinsampleapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinsampleapp.models.Card
import com.example.kotlinsampleapp.utils.toDrawable

class CardViewHolder(view: View): RecyclerView.ViewHolder(view) {
    var image: ImageView = view.findViewById(R.id.img_social_card)
    var name: TextView = view.findViewById(R.id.txt_name)
    var description: TextView = view.findViewById(R.id.txt_description)
}

class MainRecyclerViewAdapter(private var cards: List<Card>): RecyclerView.Adapter<CardViewHolder>() {

    fun loadData(newCards: List<Card>) {
        cards = newCards
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.social_card, parent, false)
        return CardViewHolder(view)
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        val card = cards[position]

        holder.image.setImageResource(card.icon.toDrawable(holder.image.context))
        holder.name.text = card.name
        holder.description.text = card.description
    }

    override fun getItemCount(): Int {
        return cards.size
    }
}