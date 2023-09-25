package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.fragment.app.Fragment
import com.example.calculator.databinding.ActivityMain2Binding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity2 : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {

    private lateinit var binding: ActivityMain2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bottomNavigationView.setOnNavigationItemSelectedListener(this)

        // Set default fragment
        val defaultFragment = DecimalToBinaryFragment()
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container, defaultFragment).commit()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_decimal_to_binary -> {
                val decimalToBinaryFragment = DecimalToBinaryFragment()
                supportFragmentManager.beginTransaction().replace(R.id.fragment_container, decimalToBinaryFragment).commit()
                return true
            }
            R.id.menu_binary_to_decimal -> {
                val binaryToDecimalFragment = BinaryToDecimalFragment()
                supportFragmentManager.beginTransaction().replace(R.id.fragment_container, binaryToDecimalFragment).commit()
                return true
            }
        }
        return false
    }
}
