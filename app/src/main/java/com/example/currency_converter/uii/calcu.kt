package com.example.currency_converter.uii

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.example.currency_converter.R
import kotlinx.coroutines.*
import your.package.name.api.RetrofitInstance

class Calcu : Fragment() {

    private val scope = CoroutineScope(Dispatchers.Main)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.Calculator, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val amountInput = view.findViewById<EditText>(R.id.amountInput)
        val fromInput = view.findViewById<EditText>(R.id.fromInput)
        val toInput = view.findViewById<EditText>(R.id.toInput)
        val resultText = view.findViewById<TextView>(R.id.resultText)
        val button = view.findViewById<Button>(R.id.convertBtn)

        button.setOnClickListener {
            val amount = amountInput.text.toString().toDoubleOrNull() ?: return@setOnClickListener
            val from = fromInput.text.toString().uppercase()
            val to = toInput.text.toString().uppercase()

            scope.launch {
                try {
                    val data = RetrofitInstance.api.getRates(from)
                    val rate = data.rates[to]
                    if (rate != null) {
                        resultText.text = "${amount * rate}"
                    } else {
                        resultText.text = "Nie znaleziono waluty."
                    }
                } catch (e: Exception) {
                    resultText.text = "Błąd połączenia."
                }
            }
        }
    }
}
