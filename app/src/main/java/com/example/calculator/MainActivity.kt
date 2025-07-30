package com.example.calculator

import android.os.Bundle
import android.view.HapticFeedbackConstants
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.calculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var hasDotInTextView = false
    private var operatorClicked: String? = null
    private var isLastInputNumber = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
    private fun vibrateButton(view: View) {
        view.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY)
    }

    fun onDigitclick(view: View) {
        vibrateButton(view)
        try {
            val clickedBtn = view as Button
            binding.textView.append(clickedBtn.text)
            isLastInputNumber = true
        } catch (e: Exception) {
            showError("Digit input error")
        }
    }

    fun onDecimalpoint(view: View) {
        vibrateButton(view)
        if (isLastInputNumber && !hasDotInTextView) {
            binding.textView.append(".")
            hasDotInTextView = true
            isLastInputNumber = false
        }
    }

    fun onopratorClicked(view: View) {
        vibrateButton(view)
        try {
            if (isLastInputNumber) {
                val clickedBtn = view as Button
                val currentText = binding.textView.text.toString()

                // Replace the last operator if exists
                if (operatorClicked != null && !isLastInputNumber) {
                    binding.textView.text = currentText.dropLast(1) + clickedBtn.text
                } else {
                    binding.textView.append(clickedBtn.text)
                }

                operatorClicked = clickedBtn.text.toString()
                hasDotInTextView = false
                isLastInputNumber = false
            }
        } catch (e: Exception) {
            showError("Operator error")
        }
    }

    fun clearBtn(view: View) {
        vibrateButton(view)
        binding.textView.text = ""
        operatorClicked = null
        hasDotInTextView = false
        isLastInputNumber = false
    }

    fun equleisclicked(view: View) {
        vibrateButton(view)
        try {
            val expression = binding.textView.text.toString()
            if (expression.isEmpty() || operatorClicked == null) {
                showError("Invalid expression")
                return
            }

            val parts = expression.split(operatorClicked!!)
            if (parts.size != 2) {
                showError("Invalid expression format")
                return
            }

            val num1 = parts[0].toDoubleOrNull()
            val num2 = parts[1].toDoubleOrNull()

            if (num1 == null || num2 == null) {
                showError("Invalid numbers")
                return
            }

            val result = when (operatorClicked) {
                "+" -> num1 + num2
                "-" -> num1 - num2
                "*" -> num1 * num2
                "/" -> if (num2 != 0.0) num1 / num2 else Double.NaN
                else -> Double.NaN
            }

            if (result.isNaN()) {
                binding.textView.text = "Error"
            } else {
                // Remove .0 if the result is integer
                binding.textView.text = if (result % 1 == 0.0) {
                    result.toInt().toString()
                } else {
                    result.toString()
                }
            }

            hasDotInTextView = binding.textView.text.contains(".")
            isLastInputNumber = true
            operatorClicked = null

        } catch (e: Exception) {
            showError("Calculation error")
            binding.textView.text = "Error"
        }
    }

    private fun showError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}