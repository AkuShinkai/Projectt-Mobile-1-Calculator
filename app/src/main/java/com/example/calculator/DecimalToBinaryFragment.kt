package com.example.calculator

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.calculator.databinding.FragmentDecimalToBinaryBinding

class DecimalToBinaryFragment : Fragment() {
    private lateinit var binding: FragmentDecimalToBinaryBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDecimalToBinaryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnConvert.setOnClickListener {
            val decimalValue = binding.tvDecimal.text.toString()
            if (decimalValue.isNotEmpty()) {
                val decimal = decimalValue.toInt()
                val binary = decimalToBinary(decimal)
                binding.tvBinary.text = binary
            }
        }

        binding.btnClear.setOnClickListener {
            binding.tvDecimal.text = ""
            binding.tvBinary.text = ""
        }

        binding.btnNumber0.setOnClickListener { appendNumber("0") }
        binding.btnNumber1.setOnClickListener { appendNumber("1") }
        binding.btnNumber2.setOnClickListener { appendNumber("2") }
        binding.btnNumber3.setOnClickListener { appendNumber("3") }
        binding.btnNumber4.setOnClickListener { appendNumber("4") }
        binding.btnNumber5.setOnClickListener { appendNumber("5") }
        binding.btnNumber6.setOnClickListener { appendNumber("6") }
        binding.btnNumber7.setOnClickListener { appendNumber("7") }
        binding.btnNumber8.setOnClickListener { appendNumber("8") }
        binding.btnNumber9.setOnClickListener { appendNumber("9") }
    }

    private fun appendNumber(number: String) {
        val currentDecimal = binding.tvDecimal.text.toString()
        val newDecimal = currentDecimal + number
        binding.tvDecimal.setText(newDecimal)
    }

    private fun decimalToBinary(decimal: Int): String {
        var num = decimal
        var binary = ""
        while (num > 0) {
            val rem = num % 2
            binary = rem.toString() + binary
            num /= 2
        }
        return binary
    }
}

