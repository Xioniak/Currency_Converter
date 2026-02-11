package com.example.currency_converter.api

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.currency_converter.R

class RatesAdapter(private val rates: List<Rate>) : RecyclerView.Adapter<RatesAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvCode: TextView = view.findViewById(R.id.tvCurrencyCode)
        val tvName: TextView = view.findViewById(R.id.tvCurrencyName)
        val tvValue: TextView = view.findViewById(R.id.tvRateValue)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_rate, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val rate = rates[position]
        holder.tvCode.text = rate.code
        holder.tvName.text = rate.currency
        holder.tvValue.text = String.format("%.4f PLN", rate.mid)
    }

    override fun getItemCount() = rates.size
}