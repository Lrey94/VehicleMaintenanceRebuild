package com.example.vehiclemaintenancerebuild.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.vehiclemaintenance.database.Vehicle
import com.example.vehiclemaintenancerebuild.database.VehicleDatabaseDao
import kotlinx.coroutines.launch

class MyVehiclesViewModel(private val database: VehicleDatabaseDao, application: Application) : AndroidViewModel(application) {

    var vehiclesFromDatabase: LiveData<List<Vehicle>> = database.getAllVehicles()

    // for testing purposes only - won't be in final code.
    init {
        vehiclesFromDatabase.observeForever{
            Log.i("Database Contents:", vehiclesFromDatabase.value.toString())
        }
    }

    fun addVehicleToDatabase(vehicle: Vehicle){
        viewModelScope.launch{
            insertVehicleIntoDB(vehicle)
        }
    }

    fun removeVehicleFromDatabase(vehicleID: Int){
        viewModelScope.launch{
            removeVehicleFromDB(vehicleID)
        }
    }

    fun updateVehicleInVehicleDatabase(vehicle: Vehicle, vehicleID: Int){
        viewModelScope.launch {
            val vehicleToUpdate = database.getVehicleFromDatabase(vehicleID)
            updateVehicleInDatabase(vehicle)
        }
    }

    fun updateVehicleServiceDateInDB(serviceDate: String, vehicleID: Int){
        viewModelScope.launch {
            updateVehicleServiceDateInDatabase(serviceDate, vehicleID)
        }
    }

    fun updateVehicleInsuranceDateInDB(insuranceDate: String, vehicleID: Int){
        viewModelScope.launch{
            updateVehicleInsuranceDateInDatabase(insuranceDate, vehicleID)
        }
    }

    fun getVehicleFromDatabase(vehicleID: Int): Vehicle?{
        var vehicleFromDatabase: Vehicle? = null
        viewModelScope.launch {
            vehicleFromDatabase = retrieveVehicleFromDatabase(vehicleID)
        }
        return vehicleFromDatabase
    }

    private suspend fun insertVehicleIntoDB(vehicle: Vehicle){
        database.insertVehicleToDatabase(vehicle)
    }

    private suspend fun removeVehicleFromDB(vehicleID: Int){
        database.removeVehicleFromDatabase(vehicleID)
    }

    private suspend fun updateVehicleInDatabase(vehicle: Vehicle){
        database.updateVehicleInDatabase(vehicle)
    }

    private suspend fun retrieveVehicleFromDatabase(vehicleID: Int): Vehicle? {
        return database.getVehicleFromDatabase(vehicleID)
    }

    private suspend fun updateVehicleServiceDateInDatabase(serviceDate: String, vehicleID: Int){
        database.updateVehicleServiceDate(serviceDate, vehicleID)
    }

    private suspend fun updateVehicleInsuranceDateInDatabase(insuranceDate: String, vehicleID: Int){
        database.updateVehicleInsuranceDate(insuranceDate, vehicleID)
    }

    private suspend fun clear() {
        database.clear()
    }

    fun onClear(){
        viewModelScope.launch {
            clear()
        }
    }
}