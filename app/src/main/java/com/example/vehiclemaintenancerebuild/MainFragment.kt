package com.example.vehiclemaintenancerebuild

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.vehiclemaintenancerebuild.databinding.FragmentMainBinding
import com.example.vehiclemaintenancerebuild.tabadapter.HomeScreenTabAdapter
import com.example.vehiclemaintenancerebuild.tabadapter.HomeScreens
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MainFragment : Fragment(){

    private var _binding: FragmentMainBinding? = null
    private val binding get() = requireNotNull(_binding) { "Main Fragment UI called after onDestroyView" }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.homeScreenTabAdapter.adapter = HomeScreenTabAdapter(childFragmentManager, lifecycle)

        TabLayoutMediator(binding.homeScreenTabLayout, binding.homeScreenTabAdapter) { tab, index ->
            when (index) {
                HomeScreens.Home.ordinal ->
                    tab.setTab(HomeScreens.Home.stringResID, HomeScreens.Home.icon)
                HomeScreens.MyVehicles.ordinal ->
                    tab.setTab(HomeScreens.MyVehicles.stringResID, HomeScreens.MyVehicles.icon)
                HomeScreens.Maintenance.ordinal ->
                    tab.setTab(HomeScreens.Maintenance.stringResID, HomeScreens.Maintenance.icon)
            }
        }.attach()
    }

    private fun TabLayout.Tab.setTab(@StringRes tabTitle: Int, @DrawableRes tabIcon: Int){
        text = getString(tabTitle)
        icon = ContextCompat.getDrawable(requireContext(), tabIcon)
    }
}