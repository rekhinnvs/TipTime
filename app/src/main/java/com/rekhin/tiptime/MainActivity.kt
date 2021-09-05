package com.rekhin.tiptime

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.rekhin.tiptime.databinding.ActivityMainBinding
import java.text.NumberFormat
import kotlin.math.ceil

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.calculateButton.setOnClickListener { calculateTip() }
    }

    private fun calculateTip() {

        /*
        * Get the  cost of service as a string
        * */
        val stringInTextField = binding.costOfServiceEditText.text.toString()
        //Check if the user entered an empty string
        val cost = stringInTextField.toDoubleOrNull()
        if(cost == null) {
            binding.tipResult.text = ""
            return
        }

        //Get the selected tip options id

        //Get the tip value from the selected tip percentage
        val tipPercentage = when(binding.tipOptions.checkedRadioButtonId) {
            R.id.option_twenty_percent -> 0.20
            R.id.option_eighteen_percent -> 0.18
            else -> 0.15
        }

        var tip = tipPercentage * cost

        //val roundUp = binding.roundUpSwitch.isChecked

        if(binding.roundUpSwitch.isChecked) {
            tip = ceil(tip)
        }

        val formattedTip = NumberFormat.getCurrencyInstance().format(tip)

        binding.tipResult.text = getString(R.string.tip_amount,formattedTip)

    }


}