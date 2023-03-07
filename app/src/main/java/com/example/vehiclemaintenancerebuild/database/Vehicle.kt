package com.example.vehiclemaintenance.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Vehicle_Table")
data class Vehicle(
    @PrimaryKey(autoGenerate = true)
    var uid: Int,

    @ColumnInfo(name = "vehicleName")
    var vehicleName: String = "",

    @ColumnInfo(name = "vehicleMake")
    var vehicleMake: String = "",

    @ColumnInfo(name = "vehicleModel")
    var vehicleModel: String = "",

    @ColumnInfo(name = "vehicleType")
    var vehicleType: String = "",

    @ColumnInfo(name = "vehicleMileage")
    var vehicleMileage: String = "",

    @ColumnInfo(name = "vehicleImageURI")
    var vehicleImageURI: String = "",

    @ColumnInfo(name = "serviceDate")
    var serviceDate: String = "",

    @ColumnInfo(name = "insuranceDate")
    var insuranceDate: String = "")