package com.zybooks.threadingdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import com.zybooks.threadingdemo.databinding.ActivityMainBinding
import java.text.NumberFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding;
    private val flowCounter: FlowCounterSource = FlowCounterSource()
    private val counterText: LiveData<String> = flowCounter.latestData.asLiveData()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        binding.findFibonacci.setOnClickListener { fibonacciClick(it) }
        counterText.observe(this){
            binding.counter.text = it
        }
        setContentView(binding.root)

    }

    //From Textbook
    private fun fibonacciClick(view: View) {
        // Display progress circle
        binding.progressBar.visibility = View.VISIBLE

        // Find the nth Fibonacci number using the given number
        val num = binding.numberEdittext.text.toString().toLong()
        binding.resultTextView.text = ""

            //Calculate a the Fibonacci sequence - this may take a LONG time
            val fibNumber = fibonacci(num)
            //Display the result on the UI
            binding.resultTextView.text = "Result:" +
                        NumberFormat.getNumberInstance(Locale.US).format(fibNumber)

            // Hide progress circle
            binding.progressBar.visibility = View.INVISIBLE

    }

    private fun fibonacci(n: Long): Long {
        return if (n <= 1) n else fibonacci(n - 1) + fibonacci(n - 2)
    }



}