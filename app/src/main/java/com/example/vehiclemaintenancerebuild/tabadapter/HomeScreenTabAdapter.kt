package com.example.vehiclemaintenancerebuild.tabadapter

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ReportFragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.vehiclemaintenancerebuild.R
import com.example.vehiclemaintenancerebuild.fuelFragment.FuelFragment
import com.example.vehiclemaintenancerebuild.maintenance.MaintenanceFragment
import com.example.vehiclemaintenancerebuild.myvehicles.MyVehiclesFragment
import com.example.vehiclemaintenancerebuild.reports.ReportsFragment
import java.lang.IllegalArgumentException

class HomeScreenTabAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :

    FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount() = HomeScreens.values().size

    override fun createFragment(position: Int) = when (position){
        HomeScreens.MyVehicles.ordinal -> MyVehiclesFragment()
        HomeScreens.Fuel.ordinal -> FuelFragment()
        HomeScreens.Maintenance.ordinal -> MaintenanceFragment()
        HomeScreens.Reports.ordinal -> ReportsFragment()
        else -> throw IllegalArgumentException("Position Not Found")
    }
}

enum class HomeScreens(@StringRes val stringResID: Int, @DrawableRes val icon: Int) {
    MyVehicles(R.string.myVehiclesScreenName, R.drawable.baseline_garage_24),
    Fuel(R.string.fuelScreenName, R.drawable.baseline_local_gas_station_24),
    Maintenance(R.string.maintenanceScreenName, R.drawable.tool),
    Reports(R.string.reportsScreenName, R.drawable.baseline_computer_24)
}