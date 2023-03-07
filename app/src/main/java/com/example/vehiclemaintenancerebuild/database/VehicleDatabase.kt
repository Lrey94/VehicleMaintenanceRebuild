package com.example.vehiclemaintenance.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.vehiclemaintenancerebuild.dataclass.VehicleListTypeConverter
import com.example.vehiclemaintenancerebuild.database.VehicleDatabaseDao

@TypeConverters(VehicleListTypeConverter::class)
@Database(entities = [Vehicle::class], version = 1, exportSchema = false)
abstract class VehicleDatabase: RoomDatabase(){

    abstract val vehicleDatabaseDao: VehicleDatabaseDao

    companion object{
        @Volatile
        private var INSTANCE: VehicleDatabase? = null

        fun getInstance(context: Context): VehicleDatabase{
            synchronized(this){
                var instance = INSTANCE

                if (instance == null){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        VehicleDatabase::class.java,
                        "vehicle_database"
                    ).fallbackToDestructiveMigration().build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}