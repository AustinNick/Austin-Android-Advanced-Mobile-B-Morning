package com.example.austin_androidadvance.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.example.austin_androidadvance.R
import com.example.austin_androidadvance.data.RetrofitClient
import com.example.austin_androidadvance.data.response.FilmResponse
import com.example.austin_androidadvance.data.response.ResultsItem
import com.example.austin_androidadvance.databinding.FragmentHomeBinding
import com.example.austin_androidadvance.views.home.adapter.HomeAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : Fragment() {
    private lateinit var homeAdapter: HomeAdapter
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        RetrofitClient.instance.getFilmsNowPlaying().enqueue(object : Callback<FilmResponse> {
            override fun onResponse(call: Call<FilmResponse>, response: Response<FilmResponse>) {
                val listFilm = response.body()
                if (listFilm != null) {
                    setupRecyclerFilm(listFilm.results)
//                    Log.d("Film", "onResponse: ${listFilm.results}")
                }
            }

            override fun onFailure(call: Call<FilmResponse>, t: Throwable) {
                Toast.makeText(requireActivity(), t.message, Toast.LENGTH_SHORT).show()
//                Log.d("ErrorFilm", "onResponse: ${t.message}")
            }
        })
    }

    private fun setupRecyclerFilm(film: List<ResultsItem>) {
        homeAdapter = HomeAdapter()

        binding?.rvCardMovies?.apply {
            adapter = homeAdapter
            layoutManager = GridLayoutManager(requireActivity(), 2)
        }

        homeAdapter.submitList(film)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}