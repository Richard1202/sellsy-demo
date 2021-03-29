package com.example.demo.ui.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import com.example.demo.R
import com.google.android.material.tabs.TabLayout


class MainActivity : AppCompatActivity() {
    companion object {
        val titles = arrayOf("DASHBOARD", "CLIENTS")
    }

    lateinit var context: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        context = this@MainActivity

        val viewPager = findViewById<View>(R.id.viewPager) as ViewPager
        viewPager.adapter = ViewPagerAdapter(supportFragmentManager)
        viewPager.addOnPageChangeListener(WizardPageChangeListener())
        viewPager.currentItem = 0

        val tabLayout = findViewById<View>(R.id.tab_layout) as TabLayout
        viewPager.adapter = ViewPagerAdapter(supportFragmentManager)
        viewPager.addOnPageChangeListener(WizardPageChangeListener())
        tabLayout.setupWithViewPager(viewPager)

        // Iterate over all tabs and set the custom view
        for (i in 0 until tabLayout.tabCount) {
            val tab = tabLayout.getTabAt(i)
            tab?.setCustomView(getTabView(i))
        }
    }

    fun setPageTitle(position: Int) {

    }

    fun getTabView(position: Int): View? {
        val v: View = LayoutInflater.from(context).inflate(R.layout.tab_item, null)
        val img: ImageView = v.findViewById<View>(R.id.imageView) as ImageView
        if (position == 0)
            img.setImageResource(R.drawable.ic_dashboard)
        if (position == 1)
            img.setImageResource(R.drawable.ic_group)
        return v
    }

    private inner class ViewPagerAdapter(fm: FragmentManager?) :
        FragmentPagerAdapter(fm!!) {
        private val WIZARD_PAGES_COUNT = 2
        override fun getItem(position: Int): Fragment {
            if (position == 0)
                return DashboardFragment()
            else
                return ClientListFragment.newInstance()
        }

        override fun getCount(): Int {
            return WIZARD_PAGES_COUNT
        }

        override fun getPageTitle(position: Int): CharSequence? {
            return titles[position]
        }
    }

    private inner class WizardPageChangeListener : OnPageChangeListener {
        override fun onPageScrollStateChanged(position: Int) {
            // TODO Auto-generated method stub
        }

        override fun onPageScrolled(
            position: Int, positionOffset: Float,
            positionOffsetPixels: Int
        ) {
            // TODO Auto-generated method stub
        }

        override fun onPageSelected(position: Int) {
            setPageTitle(position)
        }
    }
}
