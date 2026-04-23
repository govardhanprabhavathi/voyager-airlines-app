package com.voyager.airlines.data

import java.util.UUID

object MockData {
    val sfo = City(UUID.randomUUID().toString(), "San Francisco", "USA", "SFO", "https://images.unsplash.com/photo-1501594907352-04cda38ebc29?q=80&w=2000&auto=format&fit=crop")
    val jfk = City(UUID.randomUUID().toString(), "New York", "USA", "JFK", "https://images.unsplash.com/photo-1496442226666-8d4d0e62e6e9?q=80&w=2000&auto=format&fit=crop")
    val lhr = City(UUID.randomUUID().toString(), "London", "UK", "LHR", "https://images.unsplash.com/photo-1513635269975-59663e0ac1ad?q=80&w=2000&auto=format&fit=crop")
    val syd = City(UUID.randomUUID().toString(), "Sydney", "Australia", "SYD", "https://images.unsplash.com/photo-1506973035872-a4ec16b8e8d9?q=80&w=2000&auto=format&fit=crop")
    val kix = City(UUID.randomUUID().toString(), "Kyoto", "Japan", "KIX", "https://images.unsplash.com/photo-1493976040374-85c8e12f0c0e?q=80&w=2000&auto=format&fit=crop")
    val cdg = City(UUID.randomUUID().toString(), "Paris", "France", "CDG", "https://images.unsplash.com/photo-1499856871958-5b9627545d1a?q=80&w=2000&auto=format&fit=crop")
    val dfw = City(UUID.randomUUID().toString(), "Dallas", "USA", "DFW", "https://images.unsplash.com/photo-1531219572328-a0171b4448a3?q=80&w=2000&auto=format&fit=crop")

    val cities = listOf(sfo, jfk, lhr, syd, kix, cdg, dfw)

    val delta = Airline(UUID.randomUUID().toString(), "Delta Airlines", "https://logo.clearbit.com/delta.com")
    val united = Airline(UUID.randomUUID().toString(), "United Airlines", "https://logo.clearbit.com/united.com")
    val emirates = Airline(UUID.randomUUID().toString(), "Emirates", "https://logo.clearbit.com/emirates.com")
    val ryanair = Airline(UUID.randomUUID().toString(), "Ryanair", "https://logo.clearbit.com/ryanair.com", isLowCost = true)

    val airlines = listOf(delta, united, emirates, ryanair)

    val flights = generateMockFlights()

    private fun generateMockFlights(): List<Flight> {
        val list = mutableListOf<Flight>()
        val currentTime = System.currentTimeMillis()
        val oneDay = 86400000L

        // Generate some specific flights
        list.add(Flight(UUID.randomUUID().toString(), delta.id, jfk.id, lhr.id, currentTime + oneDay, currentTime + oneDay + 28800000L, 450.0))
        list.add(Flight(UUID.randomUUID().toString(), united.id, jfk.id, lhr.id, currentTime + (oneDay*2), currentTime + (oneDay*2) + 28800000L, 380.0))
        list.add(Flight(UUID.randomUUID().toString(), emirates.id, jfk.id, syd.id, currentTime + (oneDay*3), currentTime + (oneDay*4), 890.0, isConnecting = true, stopoverCityId = dfw.id))
        list.add(Flight(UUID.randomUUID().toString(), ryanair.id, lhr.id, cdg.id, currentTime + (oneDay*5), currentTime + (oneDay*5) + 3600000L, 45.0))
        list.add(Flight(UUID.randomUUID().toString(), delta.id, sfo.id, kix.id, currentTime + (oneDay*6), currentTime + (oneDay*6) + 39600000L, 650.0))

        return list
    }
}
