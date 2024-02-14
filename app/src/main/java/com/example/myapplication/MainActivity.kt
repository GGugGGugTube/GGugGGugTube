package com.example.myapplication

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.home.HomeFragment
import com.example.myapplication.myvideo.MyVideoFragment
import com.example.myapplication.search.SearchFragment

class MainActivity : AppCompatActivity() {
    private var mBinding: ActivityMainBinding? = null
    private val binding get() = mBinding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)



        binding.navMain.setOnItemSelectedListener { item->
            when(item.itemId) {
                R.id.homeFragment -> {
                    setFrag(0)
                }
                R.id.searchFragment -> {
                    setFrag(1)
                }
                R.id.myvideoFragment -> {
                    setFrag(2)
                } else ->{
                    false
                }
            }
        }
        binding.navMain.selectedItemId = R.id.homeFragment
    }
    private fun setFrag(fragNum : Int) : Boolean {
        val ft = supportFragmentManager.beginTransaction()

        when(fragNum) {
            0 ->{
                ft.replace(R.id.main_frame, HomeFragment()).commit()
            }
            1 -> {
                ft.replace(R.id.main_frame, SearchFragment()).commit()
            }
            2 -> {
                ft.replace(R.id.main_frame, MyVideoFragment()).commit()
            }

        }
        return true
    }

    fun hideBottomNavigation (state:Boolean) {
        if(state) binding.navMain.visibility = View.GONE
        else binding.navMain.visibility= View.VISIBLE
    }
}
