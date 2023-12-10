package com.example.austin_androidadvance.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.austin_androidadvance.R
import com.example.austin_androidadvance.data.RetrofitClient
import com.example.austin_androidadvance.data.response.FilmResponse
import com.example.austin_androidadvance.databinding.FragmentPopularBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PopularFragment : Fragment() {
    private var _binding: FragmentPopularBinding? = null
    private val binding get() = _binding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPopularBinding.inflate(layoutInflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        RetrofitClient.instance.getFilmsPopular().enqueue(object : Callback<FilmResponse> {
            override fun onResponse(call: Call<FilmResponse>, response: Response<FilmResponse>) {
                val listFilm = response.body()
                if (listFilm != null) {
                    setupRecyclerFilm(listFilm.results)
//                    Log.d("FilmPopular", "onResponse: ${listFilm.results}")
                }
            }

            override fun onFailure(call: Call<FilmResponse>, t: Throwable) {
                Toast.makeText(requireActivity(), t.message, Toast.LENGTH_SHORT).show()
//                Log.d("ErrorFilm", "onResponse: ${t.message}")
            }
        })
    }

    private fun setupRecyclerFilm(film: List<com.example.austin_androidadvance.data.response.ResultsItem>) {
        val homeAdapter = com.example.austin_androidadvance.views.home.adapter.HomeAdapter()

        binding?.rvCardMovies?.apply {
            adapter = homeAdapter
            layoutManager = androidx.recyclerview.widget.GridLayoutManager(requireActivity(), 2)
        }

        homeAdapter.submitList(film)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}