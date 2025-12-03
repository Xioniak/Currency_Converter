package com.example.currency_converter.uii

import android.os.Bundle
import android.view.*
import android.widget.*
import androidx.fragment.app.Fragment
import com.example.currency_converter.R
import com.example.currency_converter.api.Retrofitinstance
import kotlinx.coroutines.*


class RatesFragment : Fragment() {

    private val scope = CoroutineScope(Dispatchers.Main)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.rates, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val list = view.findViewById<ListView>(R.id.ratesList)

        scope.launch {
            try {
                val data = RetrofitInstancenstance.api.getRates("EUR")
                val entries = data.rates.entries
                    .take(10)
                    .map { "${it.key}: ${it.value}" }

                list.adapter = ArrayAdapter(
                    requireContext(),
                    android.R.layout.simple_list_item_1,
                    entries
                )

            } catch (e: Exception) {
                Toast.makeText(requireContext(), "Błąd pobierania danych", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
