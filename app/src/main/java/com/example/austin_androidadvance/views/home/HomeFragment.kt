package com.example.austin_androidadvance.views.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.austin_androidadvance.data.RetrofitClient
import com.example.austin_androidadvance.data.response.FilmResponse
import com.example.austin_androidadvance.data.response.ResultsItem
import com.example.austin_androidadvance.databinding.FragmentHomeBinding
import com.example.austin_androidadvance.model.Film
import com.example.austin_androidadvance.views.home.adapter.HomeAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding
    private lateinit var homeAdapter: HomeAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        RetrofitClient.instance.getFilms().enqueue(object : Callback<FilmResponse> {
            override fun onResponse(call: Call<FilmResponse>, response: Response<FilmResponse>) {
                val listFilm = response.body()
                if (listFilm != null) {
                    setupRecyclerFilm(listFilm.results)
                }
            }

            override fun onFailure(call: Call<FilmResponse>, t: Throwable) {
//                Log.e("HomeFragment", "onFailure: ${t.message}")
                Toast.makeText(requireActivity(), t.message, Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun setupRecyclerFilm(film: List<ResultsItem>) {
        homeAdapter = HomeAdapter()

        binding?.contentHome?.rvCardMovies?.apply {
            adapter = homeAdapter
            layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
        }

        homeAdapter.submitList(film)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}