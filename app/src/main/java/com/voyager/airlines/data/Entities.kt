package com.voyager.airlines.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(tableName = "airlines")
data class Airline(
    @PrimaryKey val id: String = UUID.randomUUID().toString(),
    val name: String,
    val logoUrl: String,
    val isLowCost: Boolean = false
)

@Entity(tableName = "cities")
data class City(
    @PrimaryKey val id: String = UUID.randomUUID().toString(),
    val name: String,
    val country: String,
    val airportCode: String,
    val imageUrl: String
)

@Entity(tableName = "flights")
data class Flight(
    @PrimaryKey val id: String = UUID.randomUUID().toString(),
    val airlineId: String,
    val sourceCityId: String,
    val destinationCityId: String,
    val departureTime: Long,
    val arrivalTime: Long,
    val basePrice: Double,
    val isConnecting: Boolean = false,
    val stopoverCityId: String? = null,
    val availableSeats: Int = 120
)

@Entity(tableName = "bookings")
data class Booking(
    @PrimaryKey val id: String = UUID.randomUUID().toString(),
    val flightId: String,
    val userId: String,
    val passengerCount: Int,
    val totalFare: Double,
    val bookingTime: Long = System.currentTimeMillis(),
    val seatNumbers: String // Comma separated string for simplicity
)
