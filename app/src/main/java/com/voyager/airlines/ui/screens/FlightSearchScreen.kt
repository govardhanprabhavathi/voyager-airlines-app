package com.voyager.airlines.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.FlightLand
import androidx.compose.material.icons.filled.FlightTakeoff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.voyager.airlines.viewmodel.VoyagerViewModel
import com.voyager.airlines.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FlightSearchScreen(
    viewModel: VoyagerViewModel,
    onBack: () -> Unit,
    onSearchComplete: () -> Unit
) {
    var source by remember { mutableStateOf("") }
    var destination by remember { mutableStateOf("") }
    
    val passengerCount by viewModel.passengerCount.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Search Flights") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ) {
            Card(
                colors = CardDefaults.cardColors(containerColor = surface_container_low),
                shape = RoundedCornerShape(24.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(24.dp)) {
                    OutlinedTextField(
                        value = source,
                        onValueChange = { source = it },
                        label = { Text("From") },
                        leadingIcon = { Icon(Icons.Filled.FlightTakeoff, null) },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(16.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            unfocusedContainerColor = surface_container_lowest,
                            focusedContainerColor = surface_container_lowest,
                            unfocusedBorderColor = Color.Transparent,
                            focusedBorderColor = MaterialTheme.colorScheme.primary
                        )
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    OutlinedTextField(
                        value = destination,
                        onValueChange = { destination = it },
                        label = { Text("To") },
                        leadingIcon = { Icon(Icons.Filled.FlightLand, null) },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(16.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            unfocusedContainerColor = surface_container_lowest,
                            focusedContainerColor = surface_container_lowest,
                            unfocusedBorderColor = Color.Transparent,
                            focusedBorderColor = MaterialTheme.colorScheme.primary
                        )
                    )
                    
                    Spacer(modifier = Modifier.height(24.dp))
                    Text("Passengers", style = MaterialTheme.typography.labelSmall)
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
                    ) {
                        Text("$passengerCount Adult", style = MaterialTheme.typography.bodyLarge)
                        Row {
                            Button(onClick = { if (passengerCount > 1) viewModel.passengerCount.value -= 1 }) { Text("-") }
                            Spacer(modifier = Modifier.width(8.dp))
                            Button(onClick = { if (passengerCount < 9) viewModel.passengerCount.value += 1 }) { Text("+") }
                        }
                    }

                    Spacer(modifier = Modifier.height(32.dp))
                    Button(
                        onClick = {
                            viewModel.searchFlights(source, destination)
                            onSearchComplete()
                        },
                        modifier = Modifier.fillMaxWidth().height(56.dp),
                        shape = RoundedCornerShape(16.dp)
                    ) {
                        Text("Search Flights")
                    }
                }
            }
        }
    }
}
