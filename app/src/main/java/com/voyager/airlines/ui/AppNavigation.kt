package com.voyager.airlines.ui

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.voyager.airlines.ui.screens.*
import com.voyager.airlines.viewmodel.VoyagerViewModel

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val viewModel: VoyagerViewModel = viewModel()

    NavHost(navController = navController, startDestination = "login") {
        composable("login") {
            LoginScreen(
                viewModel = viewModel,
                onLoginSuccess = { navController.navigate("home") { popUpTo("login") { inclusive = true } } }
            )
        }
        composable("home") {
            HomeDashboardScreen(
                viewModel = viewModel,
                onNavigateToSearch = { navController.navigate("search") },
                onNavigateToHistory = { navController.navigate("history") },
                onNavigateToOffers = { navController.navigate("offers") },
                onNavigateToResults = { navController.navigate("results") }
            )
        }
        composable("offers") {
            CheapestFlightsScreen(
                onNavigateHome = { navController.navigate("home") { popUpTo("home") { inclusive = true } } },
                onNavigateSearch = { navController.navigate("search") }
            )
        }
        composable("search") {
            FlightSearchScreen(
                viewModel = viewModel,
                onBack = { navController.popBackStack() },
                onSearchComplete = { navController.navigate("results") }
            )
        }
        composable("results") {
            FlightResultsScreen(
                onNavigateBack = { navController.popBackStack() },
                onNavigateHome = { navController.navigate("home") { popUpTo("home") { inclusive = true } } },
                onNavigateSearch = { navController.navigate("search") { popUpTo("search") { inclusive = true } } },
                onNavigateOffers = { navController.navigate("offers") },
                onBookFlight = { navController.navigate("seat_selection") }
            )
        }
        composable("seat_selection") {
            SeatSelectionScreen(
                viewModel = viewModel,
                onBack = { navController.popBackStack() },
                onContinue = { navController.navigate("confirmation") }
            )
        }
        composable("confirmation") {
            BookingConfirmationScreen(
                onNavigateHome = { navController.navigate("home") { popUpTo(0) } }
            )
        }
        composable("history") {
            BookingHistoryScreen(
                onNavigateHome = { navController.navigate("home") { popUpTo("home") { inclusive = true } } },
                onNavigateSearch = { navController.navigate("search") },
                onNavigateOffers = { navController.navigate("offers") }
            )
        }
    }
}
