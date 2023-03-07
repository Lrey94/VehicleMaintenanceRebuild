package com.example.vehiclemaintenancerebuild.viewmodels

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.vehiclemaintenancerebuild.database.VehicleDatabaseDao

class VehicleViewModelFactory(
    private val dataSource: VehicleDatabaseDao,
    private val application: Application) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MyVehiclesViewModel::class.java)) {
            return MyVehiclesViewModel(dataSource, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}