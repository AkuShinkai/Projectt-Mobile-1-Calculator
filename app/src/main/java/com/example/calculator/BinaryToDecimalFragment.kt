package com.example.calculator

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.calculator.databinding.FragmentBinaryToDecimalBinding

class BinaryToDecimalFragment : Fragment() {
    private lateinit var binding: FragmentBinaryToDecimalBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBinaryToDecimalBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnConvert.setOnClickListener {
            val binaryValue = binding.tvBinary.text.toString()
            if (binaryValue.isNotEmpty()) {
                val decimal = binaryToDecimal(binaryValue)
                binding.tvDecimal.text = decimal.toString()
            }
        }

        binding.btnClear.setOnClickListener {
            binding.tvBinary.text = ""
            binding.tvDecimal.text = ""
        }

        binding.btnNumber0.setOnClickListener { appendNumber("0") }
        binding.btnNumber1.setOnClickListener { appendNumber("1") }
    }

    private fun appendNumber(number: String) {
        val currentBinary = binding.tvBinary.text.toString()
        val newBinary = currentBinary + number
        binding.tvBinary.text = newBinary
    }

    private fun binaryToDecimal(binary: String): Int {
        var num = binary
        var decimal = 0
        var base = 1
        var len = num.length
        while (len > 0) {
            val lastDigit = num[len - 1]
            if (lastDigit == '1') {
                decimal += base
            }
            base *= 2
            len--
            num = num.substring(0, len)
        }
        return decimal
    }
}
