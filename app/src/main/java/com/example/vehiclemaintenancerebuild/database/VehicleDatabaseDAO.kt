package com.example.vehiclemaintenancerebuild.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.vehiclemaintenance.database.Vehicle

@Dao
interface VehicleDatabaseDao {
    @Insert
    suspend fun insertVehicleToDatabase(vehicle: Vehicle)

    @Update
    suspend fun updateVehicleInDatabase(vehicle: Vehicle)

    @Query("UPDATE Vehicle_Table SET serviceDate = :serviceDate WHERE uid = :vehicleID")
    suspend fun updateVehicleServiceDate(serviceDate: String, vehicleID: Int)

    @Query("UPDATE Vehicle_Table SET insuranceDate = :insuranceDate WHERE uid = :vehicleID")
    suspend fun updateVehicleInsuranceDate(insuranceDate: String, vehicleID: Int)

    @Query("DELETE FROM Vehicle_Table WHERE uid = :vehicleID")
    suspend fun removeVehicleFromDatabase(vehicleID: Int)

    @Query("SELECT * from Vehicle_Table WHERE vehicleName = :key")
    suspend fun getVehicleFromDatabase(key: Int): Vehicle?

    @Query("SELECT * FROM Vehicle_Table")
    fun getAllVehicles(): LiveData<List<Vehicle>>

    @Query("DELETE FROM Vehicle_Table")
    suspend fun clear()
}