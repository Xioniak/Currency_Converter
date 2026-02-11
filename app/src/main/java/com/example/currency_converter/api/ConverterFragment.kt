package com.example.currency_converter.api

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.currency_converter.R
import kotlinx.coroutines.launch

class ConverterFragment : Fragment(R.layout.fragment_converter) {

    private var ratesList: List<Rate> = emptyList()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val etAmount = view.findViewById<EditText>(R.id.etAmount)
        val spinnerFrom = view.findViewById<Spinner>(R.id.spinnerFrom)
        val spinnerTo = view.findViewById<Spinner>(R.id.spinnerTo)
        val btnConvert = view.findViewById<Button>(R.id.btnConvert)
        val tvResult = view.findViewById<TextView>(R.id.tvResult)

        // 1. Pobierz dane z NBP
        lifecycleScope.launch {
            try {
                val response = RetrofitInstance.api.getExchangeRates()
                val allRates = mutableListOf(Rate("polski złoty", "PLN", 1.0))
                allRates.addAll(response[0].rates)
                ratesList = allRates

                val adapter = ArrayAdapter(requireContext(),
                    android.R.layout.simple_spinner_item,
                    ratesList.map { it.code })
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

                spinnerFrom.adapter = adapter
                spinnerTo.adapter = adapter
            } catch (e: Exception) {
                Toast.makeText(context, "Błąd pobierania danych", Toast.LENGTH_SHORT).show()
            }
        }

        // 2. Logika przycisku
        btnConvert.setOnClickListener {
            val amountStr = etAmount.text.toString()
            if (amountStr.isEmpty()) return@setOnClickListener

            val amount = amountStr.toDouble()
            val fromRate = ratesList[spinnerFrom.selectedItemPosition].mid
            val toRate = ratesList[spinnerTo.selectedItemPosition].mid

            val result = (amount * fromRate) / toRate
            tvResult.text = String.format("Wynik: %.2f %s", result, ratesList[spinnerTo.selectedItemPosition].code)
        }
    }
}