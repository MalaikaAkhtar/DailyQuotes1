package com.example.dailyquotes.fragment


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.example.dailyquotes.adapter.FavoriteAdapter
import com.example.dailyquotes.databinding.FragmentFavoriteBinding
import com.example.dailyquotes.viewModel.QuotesViewModel

class FavoriteFragment : Fragment() {

    private lateinit var binding: FragmentFavoriteBinding
    private lateinit var viewModel: QuotesViewModel
    private lateinit var favoriteAdapter: FavoriteAdapter
    private lateinit var favViewPager : ViewPager2

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFavoriteBinding.inflate(inflater,container,false)
        viewModel = ViewModelProvider(requireActivity()).get(QuotesViewModel::class.java)
        return (binding.root)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        favoriteAdapter = FavoriteAdapter(emptyList())

        viewModel.favoriteQuotes.observe(viewLifecycleOwner, { quotes ->
            binding.favViewPager.adapter = favoriteAdapter
            favoriteAdapter.updateData(quotes)
        })

        favViewPager = binding.favViewPager
        //favViewPager.orientation = ViewPager2.ORIENTATION_VERTICAL
        favViewPager.adapter = favoriteAdapter

    }
}

