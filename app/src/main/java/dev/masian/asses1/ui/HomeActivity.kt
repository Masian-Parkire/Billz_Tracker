package dev.masian.asses1.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import dev.masian.asses1.R
import dev.masian.asses1.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
    lateinit var binding: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onResume() {
        super.onResume()
        setupBottomNav()

    }

    fun setupBottomNav(){
        binding.bnvHome.setOnItemSelectedListener { menuItem->
            when(menuItem.itemId){
                R.id.summary->{
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.fcvHome, SummaryFragment()).commit()
                    true
                }
                R.id.upcoming->{
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.fcvHome, UpcomingBillsFragment()).commit()
                    true


                }
                R.id.paid->{
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.fcvHome, PaidBillsFragment()).commit()
                    true
                }
                R.id.settings->{
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.fcvHome, SettingsFragment()).commit()
                    true
                }
                else->false
            }
        }
    }
}
