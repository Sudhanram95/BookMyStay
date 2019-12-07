package com.example.bookmystay.presenter

import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import com.example.bookmystay.R
import com.example.bookmystay.presenter.comment.CommentFragment
import com.example.bookmystay.presenter.hoteldetail.HotelDetailFragment
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : DaggerAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initializeView()
    }

    private fun initializeView() {
        setUpViewPager(viewPager)
        tabLayout.setupWithViewPager(viewPager)
    }

    private fun setUpViewPager(viewPager: ViewPager) {
        val viewPagerAdapter = ViewPagerAdapter(supportFragmentManager, 1)
        viewPagerAdapter.addFragment(CommentFragment(), "Comment")
        viewPagerAdapter.addFragment(HotelDetailFragment(), "HotelDetail")
        viewPager.adapter = viewPagerAdapter
    }
}
