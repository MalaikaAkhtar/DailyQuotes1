package com.example.dailyquotes.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.PagerAdapter
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


//override fun instantiateItem(container: ViewGroup, position: Int): Any {
//        val inflater = LayoutInflater.from(container.context)
//        val binding = FavoriteItemBinding.inflate(inflater, container, false)
//
//        val quote = quotes[position]
//        binding.quotesTextView.text = quote.q
//        binding.authorTextView.text = quote.a
//
//        container.addView(binding.root)
//        return binding.root
//    }
//
//    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
//        container.removeView(`object` as View)
//    }
//
//    override fun getCount(): Int {
//        return quotes.size
//    }
//
//    override fun isViewFromObject(view: View, `object`: Any): Boolean {
//        return view == `object`
//    }
//    fun updateData(newData: List<Quote>) {
//        this.quotes = newData
//        notifyDataSetChanged()
//    }