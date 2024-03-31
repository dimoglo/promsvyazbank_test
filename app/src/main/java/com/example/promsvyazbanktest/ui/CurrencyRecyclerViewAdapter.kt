package com.example.promsvyazbanktest.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.promsvyazbanktest.databinding.CurrencyItemBinding
import com.example.promsvyazbanktest.domain.CurrencyModel

class CurrencyRecyclerViewAdapter: RecyclerView.Adapter<CurrencyRecyclerViewAdapter.CurrencyViewHolder>() {

    private var currencyList: List<CurrencyModel> = listOf()

    fun setCurrencyList(newCurrencyList: List<CurrencyModel>){
        currencyList = newCurrencyList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyViewHolder {
        val binding = CurrencyItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CurrencyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CurrencyViewHolder, position: Int) {
        val currency = currencyList[position]
        holder.bind(currency)
    }

    override fun getItemCount() = currencyList.size

    class CurrencyViewHolder(
        private val binding: CurrencyItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(currency: CurrencyModel) {

            binding.currencyCharCode.text = currency.charCode
            binding.currencyName.text = currency.name
            binding.currencyValue.text = String.format("%.2f", currency.value)
        }
    }
}