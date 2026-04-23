package com.voyager.airlines.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.EventSeat
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.voyager.airlines.ui.theme.*
import com.voyager.airlines.viewmodel.VoyagerViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SeatSelectionScreen(
    viewModel: VoyagerViewModel,
    onBack: () -> Unit,
    onContinue: () -> Unit
) {
    val selectedFlight by viewModel.selectedFlight.collectAsState()
    val selectedSeats by viewModel.selectedSeats.collectAsState()
    
    val baseFare = selectedFlight?.flight?.basePrice ?: 150.0
    val totalFare = selectedSeats.size * baseFare
    val scrollState = rememberScrollState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { },
                navigationIcon = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        IconButton(onClick = onBack) {
                            Icon(Icons.Filled.ArrowBack, contentDescription = "Back", tint = MaterialTheme.colorScheme.primary)
                        }
                        Text("Voyager", fontWeight = FontWeight.Black, color = MaterialTheme.colorScheme.primary, fontStyle = FontStyle.Italic, fontSize = 18.sp)
                    }
                },
                actions = {
                    Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(end = 16.dp)) {
                        Text("Seat Selection", fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary, fontSize = 18.sp, modifier = Modifier.padding(end = 12.dp))
                        AsyncImage(
                            model = "https://lh3.googleusercontent.com/aida-public/AB6AXuA1eBY01PxA5vBjotg1vpQ_xfVuE7A2iMJZjHdZPTeuNN89CKSM75rZU1Xafz2_jdXaWcXvq_TSyk724y1nYF4-4_07T_uDSp8BLD_8tFw_HF2IdsbDWH3JuM-PFxhyB9Xd8VGlXFvo8NT8KUmEsI81k-dz1EAxOdJ_zVKNJlhkvNSpEToJ_RwCPeouopUu-Wn0URvil0d3B5ERlqu1OlrDGqHE7oGU8q3TZTVfn5W-UUYX3mHUFugcNRT_CuZ9ZPzFX4Pz4DIT36dG",
                            contentDescription = "User Profile",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .size(32.dp)
                                .clip(CircleShape)
                                .border(1.dp, MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.2f), CircleShape)
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.surface.copy(alpha = 0.9f))
            )
        },
        bottomBar = {
            // Floating Summary Bar
            Box(modifier = Modifier.fillMaxWidth().padding(16.dp).navigationBarsPadding(), contentAlignment = Alignment.Center) {
                Surface(
                    color = MaterialTheme.colorScheme.surface.copy(alpha = 0.95f),
                    shape = RoundedCornerShape(40.dp),
                    shadowElevation = 16.dp,
                    border = BorderStroke(1.dp, MaterialTheme.colorScheme.surfaceVariant),
                    modifier = Modifier.fillMaxWidth().height(88.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxSize().padding(horizontal = 20.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Box(
                                modifier = Modifier.size(48.dp).clip(CircleShape).background(MaterialTheme.colorScheme.primaryContainer),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(Icons.Filled.EventSeat, contentDescription = null, tint = MaterialTheme.colorScheme.primary)
                            }
                            Spacer(modifier = Modifier.width(16.dp))
                            Column {
                                Text("SELECTED SEATS", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onSurfaceVariant, letterSpacing = 1.sp)
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Text(if (selectedSeats.isEmpty()) "None" else selectedSeats.joinToString(", "), fontSize = 24.sp, fontWeight = FontWeight.Black, color = MaterialTheme.colorScheme.primary)
                                    if (selectedSeats.isNotEmpty()) {
                                        Text(" • ", color = MaterialTheme.colorScheme.outlineVariant, fontSize = 20.sp, modifier = Modifier.padding(horizontal = 8.dp))
                                        Text("$${totalFare.toInt()}", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.secondary)
                                    }
                                }
                            }
                        }
                        Button(
                            onClick = { 
                                viewModel.confirmBooking { onContinue() } 
                            },
                            enabled = selectedSeats.isNotEmpty(),
                            modifier = Modifier.width(140.dp).height(56.dp).padding(start = 16.dp),
                            shape = RoundedCornerShape(28.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
                        ) {
                            Text("Confirm", fontWeight = FontWeight.ExtraBold, fontSize = 16.sp, modifier = Modifier.padding(horizontal = 16.dp))
                        }
                    }
                }
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .verticalScroll(scrollState)
                .padding(padding)
                .padding(horizontal = 16.dp)
                .padding(bottom = 120.dp) // Space for bottom bar
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            // Flight Info Header
            Surface(
                color = surface_container_low,
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(modifier = Modifier.fillMaxWidth().padding(24.dp), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                    Column {
                        Text("FLIGHT", fontSize = 10.sp, fontWeight = FontWeight.SemiBold, color = MaterialTheme.colorScheme.onSurfaceVariant, letterSpacing = 1.sp)
                        Text("VY-204", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary)
                    }
                    Column(horizontalAlignment = Alignment.End) {
                        Text("ROUTE", fontSize = 10.sp, fontWeight = FontWeight.SemiBold, color = MaterialTheme.colorScheme.onSurfaceVariant, letterSpacing = 1.sp)
                        Text("${selectedFlight?.source?.airportCode ?: "SFO"} → ${selectedFlight?.destination?.airportCode ?: "LHR"}", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary)
                    }
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Seat Legend
            Surface(
                color = surface_container_lowest,
                shape = RoundedCornerShape(16.dp),
                border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.1f)),
                shadowElevation = 2.dp,
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(modifier = Modifier.fillMaxWidth().padding(16.dp), horizontalArrangement = Arrangement.SpaceAround) {
                    LegendItem("Available", surface_container_highest, MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.3f))
                    LegendItem("Selected", MaterialTheme.colorScheme.primary, MaterialTheme.colorScheme.primary)
                    LegendItem("Occupied", surface_container_highest, Color.Transparent, isOccupied = true)
                    LegendItem("Extra Legroom", tertiary_fixed, tertiary_fixed)
                }
            }

            Spacer(modifier = Modifier.height(40.dp))

            // Aircraft Cabin Visualizer
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(topStart = 120.dp, topEnd = 120.dp, bottomStart = 120.dp, bottomEnd = 120.dp))
                    .background(surface_container_low)
                    .border(1.dp, MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.1f), RoundedCornerShape(topStart = 120.dp, topEnd = 120.dp, bottomStart = 120.dp, bottomEnd = 120.dp))
                    .padding(horizontal = 24.dp, vertical = 80.dp)
            ) {
                // Cockpit Area Graphic
                Box(
                    modifier = Modifier
                        .align(Alignment.TopCenter)
                        .offset(y = (-80).dp)
                        .width(128.dp)
                        .height(56.dp)
                        .clip(RoundedCornerShape(bottomStart = 100.dp, bottomEnd = 100.dp))
                        .background(surface_container_highest)
                )

                Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                    
                    // Business Class (Rows 1-5, 4 seats per row: A, B, C, D)
                    CabinSectionLabel("BUSINESS CLASS")
                    for (r in 1..5) {
                        Row(modifier = Modifier.fillMaxWidth().padding(vertical = 6.dp), horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically) {
                            SeatBlock(r, listOf("A", "B"), selectedSeats) { viewModel.toggleSeatSelection(it) }
                            Spacer(modifier = Modifier.width(32.dp))
                            SeatBlock(r, listOf("C", "D"), selectedSeats) { viewModel.toggleSeatSelection(it) }
                        }
                    }

                    Spacer(modifier = Modifier.height(48.dp))

                    // Premium Economy (Rows 6-12, 6 seats per row: A, B, C | D, E, F)
                    CabinSectionLabel("PREMIUM ECONOMY")
                    for (r in 6..12) {
                        Row(modifier = Modifier.fillMaxWidth().padding(vertical = 6.dp), horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically) {
                            SeatBlock(r, listOf("A", "B", "C"), selectedSeats, isPremium = true) { viewModel.toggleSeatSelection(it) }
                            Box(modifier = Modifier.width(32.dp), contentAlignment = Alignment.Center) {
                                Text(r.toString(), fontSize = 10.sp, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.outlineVariant)
                            }
                            SeatBlock(r, listOf("D", "E", "F"), selectedSeats, isPremium = true) { viewModel.toggleSeatSelection(it) }
                        }
                    }

                    Spacer(modifier = Modifier.height(48.dp))

                    // Economy Class (Rows 14-35, 6 seats per row: A, B, C | D, E, F)
                    CabinSectionLabel("ECONOMY CLASS")
                    for (r in 14..35) {
                        Row(modifier = Modifier.fillMaxWidth().padding(vertical = 6.dp), horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically) {
                            SeatBlock(r, listOf("A", "B", "C"), selectedSeats) { viewModel.toggleSeatSelection(it) }
                            Box(modifier = Modifier.width(32.dp), contentAlignment = Alignment.Center) {
                                Text(r.toString(), fontSize = 10.sp, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.outlineVariant)
                            }
                            SeatBlock(r, listOf("D", "E", "F"), selectedSeats) { viewModel.toggleSeatSelection(it) }
                        }
                    }
                }

                // Tail Graphic
                Box(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .offset(y = 80.dp)
                        .width(192.dp)
                        .height(80.dp)
                        .clip(RoundedCornerShape(topStart = 100.dp, topEnd = 100.dp))
                        .background(surface_container_highest)
                )
            }
        }
    }
}

@Composable
fun LegendItem(label: String, color: Color, borderColor: Color, isOccupied: Boolean = false) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Box(
            modifier = Modifier
                .size(32.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(color)
                .border(1.dp, borderColor, RoundedCornerShape(8.dp)),
            contentAlignment = Alignment.Center
        ) {
            if (isOccupied) {
                Icon(Icons.Filled.Close, contentDescription = null, tint = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.4f), modifier = Modifier.size(16.dp))
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(label, fontSize = 10.sp, fontWeight = FontWeight.SemiBold, color = MaterialTheme.colorScheme.onSurfaceVariant)
    }
}

@Composable
fun CabinSectionLabel(label: String) {
    Surface(color = surface_container, shape = RoundedCornerShape(16.dp), modifier = Modifier.padding(bottom = 24.dp)) {
        Text(label, fontSize = 10.sp, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onSurfaceVariant, letterSpacing = 1.sp, modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp))
    }
}

@Composable
fun SeatBlock(row: Int, letters: List<String>, selectedSeats: List<String>, isPremium: Boolean = false, onSeatClick: (String) -> Unit) {
    Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
        letters.forEach { letter ->
            val seatId = "$row$letter"
            // Deterministic pseudo-random occupancy (about 40% occupied)
            val isOccupied = (seatId.hashCode() % 10) < 4
            val isSelected = selectedSeats.contains(seatId)

            val bgColor = when {
                isSelected -> MaterialTheme.colorScheme.primary
                isOccupied -> surface_container_highest
                isPremium -> tertiary_fixed
                else -> surface_container_lowest
            }
            val textColor = when {
                isSelected -> MaterialTheme.colorScheme.onPrimary
                isOccupied -> MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.3f)
                isPremium -> on_tertiary_fixed
                else -> MaterialTheme.colorScheme.primary
            }
            val borderColor = when {
                isSelected -> MaterialTheme.colorScheme.primary
                isOccupied -> Color.Transparent
                isPremium -> tertiary_fixed
                else -> MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.3f)
            }

            Box(
                modifier = Modifier
                    .size(36.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(bgColor)
                    .border(1.dp, borderColor, RoundedCornerShape(8.dp))
                    .clickable(enabled = !isOccupied) { onSeatClick(seatId) },
                contentAlignment = Alignment.Center
            ) {
                Text(seatId, fontSize = 10.sp, fontWeight = FontWeight.Bold, color = textColor)
            }
        }
    }
}
