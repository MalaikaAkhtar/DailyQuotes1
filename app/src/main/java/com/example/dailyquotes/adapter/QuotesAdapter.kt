package com.example.dailyquotes.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.dailyquotes.R
import com.example.dailyquotes.databinding.QuotesLayoutBinding
import com.example.dailyquotes.dataclass.Quote

class QuotesAdapter(private val quotes: List<Quote>,
                    private val selectedTheme: Int,
                    private var copyToClipboard:(Quote) -> Unit,
                    private var onShare: (String) -> Unit,
                    private var onFavQuotes: (Quote, Boolean) -> Unit) : RecyclerView.Adapter<QuotesAdapter.QuoteViewHolder>() {

    inner class QuoteViewHolder(private val binding: QuotesLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        val quotes_copy: ImageView = binding.quotesCopy
        val quotes_fav: ImageView = binding.quotesFav
        val quotes_share: ImageView = binding.quotesShare
        private val layout: ConstraintLayout = itemView.findViewById(R.id.quotesFragmentLayout)


        fun bind(quote: Quote, theme: Int) {
            binding.quotesTV.text = quote.q
            binding.authorTV.text = quote.a
            layout.setBackgroundResource(theme)

            quotes_fav.setOnClickListener {
                val isFavorite = !quote.isFavorite
                quote.isFavorite = isFavorite
                binding.quotesFav.setImageResource(
                    if (isFavorite) R.drawable.ic_favorite else R.drawable.ic_favorite_border
                )
                onFavQuotes(quote, isFavorite)
            }

            quotes_copy.setOnClickListener {
                copyToClipboard.invoke(quote)
            }
            quotes_share.setOnClickListener {
                onShare.invoke(quote.q)
            //    onShare(quote.q)
            }


        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuoteViewHolder {
        val binding = QuotesLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return QuoteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: QuoteViewHolder, position: Int) {
        holder.bind(quotes[position], selectedTheme)

    }

    override fun getItemCount(): Int {
        return quotes.size
    }

}


