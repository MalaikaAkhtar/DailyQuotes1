package com.example.dailyquotes.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dailyquotes.databinding.FavoriteItemBinding
import com.example.dailyquotes.dataclass.Quote

class FavoriteAdapter(private var quotes: List<Quote>):RecyclerView.Adapter<FavoriteAdapter.FavoriteViewHolder>() {


    inner class FavoriteViewHolder(val binding: FavoriteItemBinding) : RecyclerView.ViewHolder(binding.root) {

//        val quoteTextView: TextView = binding.quotesTextView
//        val authorTextView: TextView = binding.authorTextView

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        val binding = FavoriteItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FavoriteViewHolder(binding)
    }


    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        val quote = quotes[position]
        holder.binding.quotesTextView.text = quote.q
        holder.binding.authorTextView.text = quote.a

    }

    override fun getItemCount(): Int {
        return quotes.size
    }

    fun updateData(newData: List<Quote>) {
        this.quotes = newData
        notifyDataSetChanged()

    }
}


