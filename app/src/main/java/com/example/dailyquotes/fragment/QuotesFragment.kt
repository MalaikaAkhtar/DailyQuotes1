package com.example.dailyquotes.fragment

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.example.dailyquotes.R
import com.example.dailyquotes.dataclass.Quote
import com.example.dailyquotes.adapter.QuotesAdapter
import com.example.dailyquotes.databinding.FragmentQuotesBinding
import com.example.dailyquotes.viewmodel.QuotesViewModel


class QuotesFragment : Fragment() {
    private lateinit var binding: FragmentQuotesBinding
    private lateinit var viewModel: QuotesViewModel
    private var selectedTheme: Int = R.drawable.quotes
    private lateinit var viewPager: ViewPager2
    private val activityContext by lazy { requireActivity() }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentQuotesBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(requireActivity())[QuotesViewModel::class.java]

        observeViewModel()
        selectedTheme = applySelectedTheme()
        return (binding.root)
    }

    private fun observeViewModel() {
        viewModel.quotes.observe(viewLifecycleOwner) { quotes ->
            setUI(quotes)
        }

        viewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            setInProgress(isLoading)
        }
    }

    private fun setUI(quotes: List<Quote>) {
        val adapter = QuotesAdapter(quotes, selectedTheme,copyToClipboard = { quote ->
            val clipboard = context?.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val clip = ClipData.newPlainText("Copied Quote", quote.q)
            clipboard.setPrimaryClip(clip)
            Toast.makeText(context, "Quote copied to clipboard", Toast.LENGTH_SHORT).show()
        }, onFavQuotes = { quote: Quote, isChecked: Boolean ->
            quote.isFavorite = isChecked
            if (isChecked) {
                viewModel.addFavorite(quote)
            } else {
                viewModel.removeFavorite(quote)
            }

        }, onShare = { text ->
            shareQuote(text) })

        viewPager = binding.viewPager
        viewPager.orientation = ViewPager2.ORIENTATION_VERTICAL
        viewPager.adapter = adapter
    }

    private fun shareQuote(text: String) {
        val intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, text)
            type = "text/plain"
        }
        startActivity(Intent.createChooser(intent, "Share Quote via"))
    }

    private fun setInProgress(inProgress: Boolean) {
        if (inProgress){
            binding.progressBar.visibility = View.VISIBLE
        }else{
            binding.progressBar.visibility = View.GONE
        }

    }

    private fun applySelectedTheme(): Int {
        val sharedPreferences = activityContext.getSharedPreferences("ThemePrefs", Context.MODE_PRIVATE)
        return when (sharedPreferences.getString("selected_theme", "default_theme")) {
            "pink_theme" -> R.drawable.pink_theme
            "blue_theme" -> R.drawable.blue_theme
            "green_theme" -> R.drawable.green_theme
            "black_theme" -> R.drawable.blackleaves
            "yellow_theme" -> R.drawable.yellow_theme
            "purple_theme" -> R.drawable.purple_theme
            else -> R.drawable.blackleaves
        }
    }
}





