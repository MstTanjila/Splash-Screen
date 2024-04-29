package com.bogo.bmicalculator1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.bogo.bmicalculator1.databinding.ActivityBmiactivity2Binding

class BMIactivity2 : AppCompatActivity() {
    lateinit var binding:ActivityBmiactivity2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityBmiactivity2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.calBtn.setOnClickListener {
            binding.card3.setVisibility(View.VISIBLE)
            val weight =binding.weightEt.text.toString()
            val height =binding.heightEt.text.toString()
            if (validatInput(weight,height)){
                val bmi = weight.toDouble()/(height.toDouble()/100*(height.toDouble()/100))
                val bmiDigi = String.format("%,2f",bmi).toDouble()
                displayResult(bmiDigi)
            }

        }
    }
    private fun displayResult(bmiDigi:Double){
      var result=""
        var color=0
        var range=""

        when{
            bmiDigi <18.50->{
                result = "Underweight"
                color = R.color.underweight
                range ="(Underweight range is less then 18.50)"
            }
            bmiDigi in 18.50..24.99->{
                result = "Healthy"
                color = R.color.healthy
                range = "(Healthy range is 18.50 to 24.99)"

            }
            bmiDigi in 24.99..29.00->{
                result="Overweight"
                color = R.color.overweight
                range = "(Overweight range is  24.99 to 29.00)"
            }
            bmiDigi >29.00->{
                result ="Obese"
                color = R.color.obese
                range = "Obese range is grater than 29.00"
            }
        }
        binding.CountTxt.setTextColor(ContextCompat.getColor(this,color))
        binding.CountTxt.text = bmiDigi.toString()
        binding.result.setTextColor(ContextCompat.getColor(this,color))
        binding.result.text=result
        binding.range.setTextColor(ContextCompat.getColor(this,color))
        binding.range.text = range
    }
    private fun validatInput(weight:String,height:String): Boolean {
        return when{
           weight.isNullOrEmpty()->{
               Toast.makeText(this,"Weight is empty",Toast.LENGTH_SHORT).show()
               return false
           }
            height.isNullOrEmpty()->{
                Toast.makeText(this,"Height is empty",Toast.LENGTH_SHORT).show()
                return false
            }
            else->{
                return true
            }


        }
    }
}