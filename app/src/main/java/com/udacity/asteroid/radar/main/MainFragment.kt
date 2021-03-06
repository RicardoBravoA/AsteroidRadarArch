package com.udacity.asteroid.radar.main

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.udacity.asteroid.domain.model.AsteroidModel
import com.udacity.asteroid.radar.R
import com.udacity.asteroid.radar.databinding.FragmentMainBinding

class MainFragment : Fragment() {

    private val mainViewModel: MainViewModel by lazy {
        ViewModelProvider(this, MainViewModelFactory(requireActivity().application)).get(
            MainViewModel::class.java
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentMainBinding.inflate(inflater)
        binding.lifecycleOwner = this

        binding.viewModel = mainViewModel

        setHasOptionsMenu(true)

        val mainAdapter = MainAdapter(::asteroidClick)

        binding.asteroidRecyclerView.adapter = mainAdapter

        mainViewModel.asteroidList.observe(viewLifecycleOwner, {
            it?.let {
                mainAdapter.submitList(it)
            }
        })

        return binding.root
    }

    private fun asteroidClick(asteroidModel: AsteroidModel) {
        findNavController().navigate(MainFragmentDirections.actionShowDetail(asteroidModel))
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_main, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.show_week_asteroids -> {
                mainViewModel.weekData()
                return true
            }
            R.id.show_today_asteroids -> {
                mainViewModel.today()
                return true
            }
            R.id.show_saved_asteroids -> {
                mainViewModel.saved()
                return true
            }
        }
        return true
    }
}