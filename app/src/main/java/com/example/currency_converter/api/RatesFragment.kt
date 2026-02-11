package com.example.currency_converter.api

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.currency_converter.R
import kotlinx.coroutines.launch

class RatesFragment : Fragment(R.layout.fragment_rates) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView = view.findViewById<RecyclerView>(R.id.rvRates)
        val progressBar = view.findViewById<ProgressBar>(R.id.progressBar)

        recyclerView.layoutManager = LinearLayoutManager(context)

        // Pobieranie danych
        lifecycleScope.launch {
            progressBar.visibility = View.VISIBLE
            try {
                val response = RetrofitInstance.api.getExchangeRates()
                val rates = response[0].rates

                // Ustawienie adaptera z danymi
                recyclerView.adapter = RatesAdapter(rates)
            } catch (e: Exception) {
                Toast.makeText(context, "Błąd połączenia: ${e.message}", Toast.LENGTH_LONG).show()
            } finally {
                progressBar.visibility = View.GONE
            }
        }
    }
}