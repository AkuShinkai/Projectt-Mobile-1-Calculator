package com.example.calculator

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.calculator.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationView
import net.objecthunter.exp4j.ExpressionBuilder

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private lateinit var binding: ActivityMainBinding
    private lateinit var calculatorViewModel: CalculatorViewModel
    private lateinit var historyViewModel: HistoryViewModel
    private val historyList = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        // Inisialisasi calculatorViewModel dan historyViewModel
        calculatorViewModel = ViewModelProvider(this).get(CalculatorViewModel::class.java)
        historyViewModel = ViewModelProvider(this).get(HistoryViewModel::class.java)

        // Mengamati perubahan pada hasil perhitungan
        calculatorViewModel.result.observe(this, Observer { result ->
            binding.txtExpression.text = result
        })

        // Pemrosesan tombol operasi matematika
        binding.btnPlus.setOnClickListener { performOperation("+") }
        binding.btnMinus.setOnClickListener { performOperation("-") }
        binding.btnMultiply.setOnClickListener { performOperation("*") }
        binding.btnDivide.setOnClickListener { performOperation("/") }
        binding.btnPercentage.setOnClickListener { performOperation("%") }
        binding.btnPower.setOnClickListener { performOperation("^") }

        // Pemrosesan tombol angka dan tanda desimal
        binding.btnNumber0.setOnClickListener { appendToExpression("0") }
        binding.btnNumber1.setOnClickListener { appendToExpression("1") }
        binding.btnNumber2.setOnClickListener { appendToExpression("2") }
        binding.btnNumber3.setOnClickListener { appendToExpression("3") }
        binding.btnNumber4.setOnClickListener { appendToExpression("4") }
        binding.btnNumber5.setOnClickListener { appendToExpression("5") }
        binding.btnNumber6.setOnClickListener { appendToExpression("6") }
        binding.btnNumber7.setOnClickListener { appendToExpression("7") }
        binding.btnNumber8.setOnClickListener { appendToExpression("8") }
        binding.btnNumber9.setOnClickListener { appendToExpression("9") }
        binding.btnPoint.setOnClickListener { appendToExpression(".") }

        // Pemrosesan tombol hapus dan bersihkan
        binding.btnBackspace.setOnClickListener { deleteLastCharacter() }
        binding.btnClear.setOnClickListener { clearExpression() }
        binding.btnEquals.setOnClickListener { evaluateExpression() }

        // Pemrosesan tombol history dan berpindah ke activity2
        binding.btnHistory.setOnClickListener { openHistoryActivity() }
        binding.btnConverter.setOnClickListener { openMainActivity2() }

        // Inisialisasi ActionBarDrawerToggle
        val toggle = ActionBarDrawerToggle(
            this,
            binding.drawerLayout,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        binding.drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        // Set NavigationView listener
        val navigationView: NavigationView = findViewById(R.id.navigationView)
        navigationView.setNavigationItemSelectedListener(this)

        // Menambahkan klik listener untuk tombol ivDrawerIcon
        val ivDrawerIcon = findViewById<Button>(R.id.ivDrawerIcon)
        ivDrawerIcon.setOnClickListener {
            if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
                binding.drawerLayout.closeDrawer(GravityCompat.START)
            } else {
                binding.drawerLayout.openDrawer(GravityCompat.START)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.drawer_menu, menu)
        return true
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_decimal_to_binary -> {
            }
        }

        binding.drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onBackPressed() {
        if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            binding.drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    private fun performOperation(operator: String) {
        val expression = binding.txtExpression.text.toString()
        if (expression.isNotEmpty()) {
            val newExpression = "$expression$operator"
            binding.txtExpression.text = newExpression
        }
    }

    private fun appendToExpression(value: String) {
        val expression = binding.txtExpression.text.toString()
        val newExpression = "$expression$value"
        binding.txtExpression.text = newExpression
    }

    private fun deleteLastCharacter() {
        val expression = binding.txtExpression.text.toString()
        if (expression.isNotEmpty()) {
            val newExpression = expression.substring(0, expression.length - 1)
            binding.txtExpression.text = newExpression
        }
    }

    private fun clearExpression() {
        binding.txtExpression.text = ""
    }

    private fun evaluateExpression() {
        val expression = binding.txtExpression.text.toString()
        if (expression.isNotEmpty()) {
            try {
                val result = ExpressionBuilder(expression).build().evaluate()
                val newExpression = result.toString()
                binding.txtExpression.text = newExpression

                // Menyimpan hasil perhitungan ke dalam historyList
                val historyEntry = "$expression = $newExpression"
                historyList.add(historyEntry)
            } catch (e: Exception) {
                Log.d("Calculator", "Error evaluating expression: ${e.message}")
            }
        }
    }

    private fun openHistoryActivity() {
        // Mengirim historyList ke HistoryActivity menggunakan intent
        val intent = Intent(this, HistoryActivity::class.java)
        intent.putStringArrayListExtra("historyList", ArrayList(historyList))
        startActivity(intent)
    }

    private fun openMainActivity2(){
        val intent = Intent(this, MainActivity2::class.java)
        startActivity(intent)
    }

}
