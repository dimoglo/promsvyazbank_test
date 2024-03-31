package com.example.promsvyazbanktest.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.promsvyazbanktest.R
import com.example.promsvyazbanktest.databinding.FragmentMainBinding
import com.example.promsvyazbanktest.utils.ScreenState
import com.example.promsvyazbanktest.utils.formatDate
import com.example.promsvyazbanktest.utils.getDateString
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainFragment: Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MainViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val currencyAdapter = CurrencyRecyclerViewAdapter()
        binding.recyclerView.apply {
            adapter = currencyAdapter
            layoutManager = LinearLayoutManager(context)
        }

        lifecycleScope.launch {
            viewModel.state.collect{
                when(it){
                    is ScreenState.Loading -> {
                        binding.loading.visibility = View.VISIBLE
                    }
                    is ScreenState.Success -> {
                        binding.loading.visibility = View.GONE
                        binding.date.text =
                            String.format(
                                resources.getString(R.string.data_timestamp_format),
                                formatDate(it.data!!.date!!)
                            )
                        binding.updateTime.text =
                            String.format(
                                resources.getString(R.string.update_at_format),
                                getDateString(it.data.updateAt!!)
                            )
                        currencyAdapter.setCurrencyList(it.data.valutes)
                    }
                    is ScreenState.Error -> {
                        Toast.makeText(requireContext(), it.errorMessage, Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.startPeriodicUpdate()
    }

    override fun onPause() {
        super.onPause()
        viewModel.stopPeriodicUpdate()
    }
}