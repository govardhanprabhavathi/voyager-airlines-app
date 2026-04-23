package com.voyager.airlines.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.voyager.airlines.data.Booking
import com.voyager.airlines.data.City
import com.voyager.airlines.data.Flight
import com.voyager.airlines.data.FlightWithDetails
import com.voyager.airlines.data.MockData
import com.voyager.airlines.data.VoyagerDatabase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class VoyagerViewModel(application: Application) : AndroidViewModel(application) {
    private val db = VoyagerDatabase.getDatabase(application)
    private val flightDao = db.flightDao()
    private val bookingDao = db.bookingDao()

    private val _cities = MutableStateFlow<List<City>>(emptyList())
    val cities: StateFlow<List<City>> = _cities.asStateFlow()

    private val _searchResults = MutableStateFlow<List<FlightWithDetails>>(emptyList())
    val searchResults: StateFlow<List<FlightWithDetails>> = _searchResults.asStateFlow()

    private val _selectedFlight = MutableStateFlow<FlightWithDetails?>(null)
    val selectedFlight: StateFlow<FlightWithDetails?> = _selectedFlight.asStateFlow()

    var passengerCount = MutableStateFlow(1)
    var selectedSeats = MutableStateFlow<List<String>>(emptyList())

    init {
        viewModelScope.launch {
            if (flightDao.getFlightCount() == 0) {
                flightDao.insertAirlines(MockData.airlines)
                flightDao.insertCities(MockData.cities)
                flightDao.insertFlights(MockData.flights)
            }
            _cities.value = flightDao.getAllCitiesSync()
        }
    }

    fun searchFlights(sourceCode: String, destCode: String) {
        viewModelScope.launch {
            val allCities = flightDao.getAllCitiesSync()
            val sourceCity = allCities.find { it.airportCode.equals(sourceCode, ignoreCase = true) || it.name.contains(sourceCode, ignoreCase = true) }
            val destCity = allCities.find { it.airportCode.equals(destCode, ignoreCase = true) || it.name.contains(destCode, ignoreCase = true) }

            if (sourceCity != null && destCity != null) {
                flightDao.searchFlights(sourceCity.id, destCity.id).collect { flights ->
                    val detailedFlights = flights.map { flight ->
                        FlightWithDetails(
                            flight = flight,
                            airline = flightDao.getAirline(flight.airlineId),
                            source = sourceCity,
                            destination = destCity,
                            stopover = flight.stopoverCityId?.let { flightDao.getCity(it) }
                        )
                    }
                    _searchResults.value = detailedFlights
                }
            } else {
                _searchResults.value = emptyList() // Not found
            }
        }
    }

    fun selectFlight(flight: FlightWithDetails) {
        _selectedFlight.value = flight
        selectedSeats.value = emptyList()
    }

    fun toggleSeatSelection(seat: String) {
        val current = selectedSeats.value.toMutableList()
        if (current.contains(seat)) {
            current.remove(seat)
        } else {
            if (current.size < 4) {
                current.add(seat)
            }
        }
        selectedSeats.value = current
        passengerCount.value = current.size.coerceAtLeast(1)
    }

    fun confirmBooking(onSuccess: () -> Unit) {
        viewModelScope.launch {
            val flight = _selectedFlight.value 
            if (flight == null) {
                onSuccess()
                return@launch
            }
            val seats = selectedSeats.value
            if (seats.size != passengerCount.value) return@launch // Must select all seats

            val baseFare = flight.flight.basePrice
            val totalFare = baseFare * passengerCount.value

            val booking = Booking(
                flightId = flight.flight.id,
                userId = "user_1", // Mock user
                passengerCount = passengerCount.value,
                totalFare = totalFare,
                seatNumbers = seats.joinToString(",")
            )

            bookingDao.insertBooking(booking)
            onSuccess()
        }
    }

    fun login(email: String, pass: String): Boolean {
        // A simple dummy login that succeeds if email is not blank and password is at least 4 characters
        return email.isNotBlank() && pass.length >= 4
    }
}
