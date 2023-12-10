package com.example.austin_androidadvance

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.austin_androidadvance.data.RetrofitClient
import com.example.austin_androidadvance.data.response.FilmResponse
import com.example.austin_androidadvance.data.response.ResultsItem
import com.example.austin_androidadvance.databinding.ActivityMainBinding
import com.example.austin_androidadvance.views.home.adapter.HomeAdapter
import com.example.austin_androidadvance.views.home.adapter.SectionsEventPagerAdapter
import com.google.android.material.tabs.TabLayoutMediator
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var patcherAdapter: SectionsEventPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        patcherAdapter = SectionsEventPagerAdapter(this@MainActivity)
        binding.apply {
            viewPager.adapter = patcherAdapter
            TabLayoutMediator(tabLayout, viewPager) { tab, position ->
                when(position) {
                    0 -> tab.text = "Now Playing"
                    1 -> tab.text = "Popular"
                }
            }.attach()
        }
    }
}