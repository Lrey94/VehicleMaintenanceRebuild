package com.example.vehiclemaintenancerebuild.tabadapter

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.vehiclemaintenancerebuild.R
import com.example.vehiclemaintenancerebuild.homescreen.HomeFragment
import com.example.vehiclemaintenancerebuild.maintenance.MaintenanceFragment
import com.example.vehiclemaintenancerebuild.myvehicles.MyVehiclesFragment
import java.lang.IllegalArgumentException

class HomeScreenTabAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :

    FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount() = HomeScreens.values().size

    override fun createFragment(position: Int) = when (position){
        HomeScreens.Home.ordinal -> HomeFragment()
        HomeScreens.MyVehicles.ordinal -> MyVehiclesFragment()
        HomeScreens.Maintenance.ordinal -> MaintenanceFragment()
        else -> throw IllegalArgumentException("Position Not Found")
    }
}

enum class HomeScreens(@StringRes val stringResID: Int, @DrawableRes val icon: Int) {
    Home(R.string.homeScreenName, R.drawable.ic_baseline_home_24),
    MyVehicles(R.string.myVehicleScreenName, R.drawable.garage),
    Maintenance(R.string.maintenanceScreenName, R.drawable.tool)
}