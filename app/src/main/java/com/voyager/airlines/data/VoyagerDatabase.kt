package com.voyager.airlines.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Flight::class, Airline::class, City::class, Booking::class], version = 1, exportSchema = false)
abstract class VoyagerDatabase : RoomDatabase() {
    abstract fun flightDao(): FlightDao
    abstract fun bookingDao(): BookingDao

    companion object {
        @Volatile
        private var INSTANCE: VoyagerDatabase? = null

        fun getDatabase(context: Context): VoyagerDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    VoyagerDatabase::class.java,
                    "voyager_database"
                )
                .fallbackToDestructiveMigration()
                .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
