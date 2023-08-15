package com.example.assessment3.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.assessment3.R
import com.example.assessment3.databinding.ActivityHomeBinding

class Home : AppCompatActivity() {
    lateinit var binding=ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }


    override fun onResume() {
        super.onResume()
        setUpBottomNav()
    }
    fun setUpBottomNav(){
        binding.bnvHome.setOnItemSelectedlistener{menuItem}
        when (menuItem.itemId){
            R.id.summary->{
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.fcvHome,SummaryFragment())
                        .commit()
                true : setOnItemSelectedlistener
            }
            R.id.upcoming->{
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.fcvHome,SummaryFragment())
                    .commit()
                true : setOnItemSelectedlistener
            }
            R.id.paid->{

            }
            R.id.settings->{

            }
                else ->{
                    false
                }
        }
    }
}