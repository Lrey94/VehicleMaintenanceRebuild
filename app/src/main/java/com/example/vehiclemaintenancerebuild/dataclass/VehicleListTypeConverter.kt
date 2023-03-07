package com.example.vehiclemaintenancerebuild.dataclass

import androidx.room.TypeConverter
import com.example.vehiclemaintenance.database.Vehicle
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class VehicleListTypeConverter {

    @OptIn(ExperimentalSerializationApi::class)
    @TypeConverter
    fun fromList (value: ArrayList<Vehicle>) = Json.encodeToString(value)

    @OptIn(ExperimentalSerializationApi::class)
    @TypeConverter
    fun toList(value: String) = Json.decodeFromString<ArrayList<Vehicle>>(value)
}