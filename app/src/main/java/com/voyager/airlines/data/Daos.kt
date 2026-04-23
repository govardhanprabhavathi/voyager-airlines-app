package com.voyager.airlines.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

data class FlightWithDetails(
    val flight: Flight,
    val airline: Airline,
    val source: City,
    val destination: City,
    val stopover: City?
)

@Dao
interface FlightDao {
    @Query("SELECT * FROM flights")
    fun getAllFlights(): Flow<List<Flight>>

    @Insert
    suspend fun insertFlights(flights: List<Flight>)

    @Insert
    suspend fun insertAirlines(airlines: List<Airline>)

    @Insert
    suspend fun insertCities(cities: List<City>)
    
    @Query("SELECT COUNT(*) FROM flights")
    suspend fun getFlightCount(): Int

    @Query("""
        SELECT * FROM flights 
        WHERE sourceCityId = :sourceId AND destinationCityId = :destId
        ORDER BY basePrice ASC
    """)
    fun searchFlights(sourceId: String, destId: String): Flow<List<Flight>>

    @Query("""
        SELECT * FROM flights 
        ORDER BY basePrice ASC LIMIT 10
    """)
    fun getCheapestFlights(): Flow<List<Flight>>
    
    @Query("SELECT * FROM airlines WHERE id = :id")
    suspend fun getAirline(id: String): Airline
    
    @Query("SELECT * FROM cities WHERE id = :id")
    suspend fun getCity(id: String): City
    
    @Query("SELECT * FROM cities")
    suspend fun getAllCitiesSync(): List<City>
}

@Dao
interface BookingDao {
    @Insert
    suspend fun insertBooking(booking: Booking)

    @Query("SELECT * FROM bookings ORDER BY bookingTime DESC")
    fun getBookings(): Flow<List<Booking>>
}
