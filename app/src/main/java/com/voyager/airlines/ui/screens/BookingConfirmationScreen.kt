package com.voyager.airlines.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.voyager.airlines.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookingConfirmationScreen(onNavigateHome: () -> Unit) {
    val scrollState = rememberScrollState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { 
                    Text("Voyager", fontWeight = FontWeight.Black, color = MaterialTheme.colorScheme.primary, fontStyle = FontStyle.Italic) 
                },
                actions = {
                    AsyncImage(
                        model = "https://lh3.googleusercontent.com/aida-public/AB6AXuCytDHs5eKmhk8vkhBRIMjT1I3dxLSJRb_XhTHtP6bvrRrPvf8jP6fl2-terHlBrlToGmQozsuw6N47JEpoTgBMgtB9zve8lX9NzgLGmX-9lQGvkK_8OxSJ37zzB5PSz7p5WWLtHC4xwH3e1Re8-pUQDs5rNrVe0kDaeKKIhxY4mO1476zXsdHp5DdMX32FNA0t3qsLYsEGt_6gqNFe1ph3PCQd5fWe-xwI6f73kzNOGNAraejJ5Sgv3nfQqMrcl-5SkRQLfLmU1Rki",
                        contentDescription = "User Profile",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .padding(end = 16.dp)
                            .size(32.dp)
                            .clip(CircleShape)
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent)
            )
        }
    ) { padding ->
        Box(modifier = Modifier.fillMaxSize()) {
            // Background Image
            AsyncImage(
                model = "https://lh3.googleusercontent.com/aida-public/AB6AXuAkNW14mdFDF4zU1DijnXAr8w7jLdIa4el0MxrP_5an7wy1j8fs3SO990bcxvbvGyQA3pLLZtRK3GwJAGZMhbMMp1ISAw79stu_4Tz0CKtbWeiKMNTVCxdHQ6-zyu9GcPs8_-_BlIqVKLsatEpEe5wN1vZNUK0GqI_MQnm4D9_i6OgpeC8P39_N1MgfYtojr0-WlR2zMX_RNehwFsngmDteETSFrLs6kVY2tf3QdF3tCITbF5gsLgdKHoAbj-CPbYutYlQ8op0atUW6",
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize(),
                alpha = 0.3f
            )
            Box(modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.surface.copy(alpha = 0.7f)))

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(scrollState)
                    .padding(padding)
                    .padding(horizontal = 24.dp)
                    .padding(bottom = 32.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(16.dp))

                // Success Hero
                Box(
                    modifier = Modifier
                        .size(96.dp)
                        .clip(CircleShape)
                        .background(tertiary_fixed),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(Icons.Filled.CheckCircle, contentDescription = null, tint = tertiary, modifier = Modifier.size(48.dp))
                }
                Spacer(modifier = Modifier.height(24.dp))
                Text(
                    "Booking Successful!",
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.ExtraBold,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    "Your journey to the skies begins here.",
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Medium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                Spacer(modifier = Modifier.height(32.dp))

                // Ticket Card
                Surface(
                    color = surface_container_lowest,
                    shape = RoundedCornerShape(32.dp),
                    shadowElevation = 8.dp,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column {
                        // Top Gradient Element
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(100.dp)
                                .background(Brush.linearGradient(listOf(MaterialTheme.colorScheme.primary, tertiary_container)), alpha = 0.1f)
                        )

                        Column(modifier = Modifier.padding(24.dp).offset(y = (-100).dp)) {
                            // Header
                            Row(modifier = Modifier.fillMaxWidth().padding(top = 24.dp), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.Top) {
                                Column {
                                    Text("CONFIRMATION CODE", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary.copy(alpha = 0.6f), letterSpacing = 1.sp)
                                    Text("VY-7742091", fontSize = 24.sp, fontWeight = FontWeight.Black, color = MaterialTheme.colorScheme.onSurface)
                                }
                                Column(horizontalAlignment = Alignment.End) {
                                    Text("STATUS", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary.copy(alpha = 0.6f), letterSpacing = 1.sp)
                                    Surface(color = tertiary_container, shape = RoundedCornerShape(8.dp), modifier = Modifier.padding(top = 4.dp)) {
                                        Text("CONFIRMED", color = on_tertiary_container, fontSize = 12.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp))
                                    }
                                }
                            }

                            Spacer(modifier = Modifier.height(32.dp))

                            // Flight Details
                            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                                Column(modifier = Modifier.weight(1f)) {
                                    Text("DEPARTURE", fontSize = 10.sp, fontWeight = FontWeight.SemiBold, color = MaterialTheme.colorScheme.outline, letterSpacing = 1.sp)
                                    Text("London (LHR)", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                                    Text("Terminal 5, Gate B32", fontSize = 14.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
                                }
                                Column(horizontalAlignment = Alignment.End, modifier = Modifier.weight(1f)) {
                                    Text("ARRIVAL", fontSize = 10.sp, fontWeight = FontWeight.SemiBold, color = MaterialTheme.colorScheme.outline, letterSpacing = 1.sp)
                                    Text("Singapore (SIN)", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                                    Text("Terminal 3", fontSize = 14.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
                                }
                            }

                            Spacer(modifier = Modifier.height(16.dp))

                            Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                                Box(modifier = Modifier.weight(1f).height(1.dp).background(surface_container_high))
                                Icon(Icons.Filled.FlightTakeoff, contentDescription = null, tint = primary_fixed_dim, modifier = Modifier.padding(horizontal = 16.dp))
                                Box(modifier = Modifier.weight(1f).height(1.dp).background(surface_container_high))
                            }

                            Spacer(modifier = Modifier.height(16.dp))

                            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                                Column {
                                    Text("DATE & TIME", fontSize = 10.sp, fontWeight = FontWeight.SemiBold, color = MaterialTheme.colorScheme.outline, letterSpacing = 1.sp)
                                    Text("Oct 24, 2024", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                                    Text("10:45 AM (Local)", fontSize = 14.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
                                }
                                Column(horizontalAlignment = Alignment.End) {
                                    Text("FLIGHT ID", fontSize = 10.sp, fontWeight = FontWeight.SemiBold, color = MaterialTheme.colorScheme.outline, letterSpacing = 1.sp)
                                    Text("VOY-402", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                                    Text("Boeing 787-10", fontSize = 14.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
                                }
                            }

                            Spacer(modifier = Modifier.height(32.dp))

                            // Seat Info
                            Surface(color = surface_container_low, shape = RoundedCornerShape(16.dp)) {
                                Row(modifier = Modifier.fillMaxWidth().padding(24.dp), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                                    Row(verticalAlignment = Alignment.CenterVertically) {
                                        Surface(color = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.2f), shape = RoundedCornerShape(12.dp), modifier = Modifier.size(48.dp)) {
                                            Icon(Icons.Filled.EventSeat, contentDescription = null, tint = MaterialTheme.colorScheme.primaryContainer, modifier = Modifier.padding(12.dp))
                                        }
                                        Spacer(modifier = Modifier.width(16.dp))
                                        Column {
                                            Text("SEAT NUMBER", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.outline)
                                            Text("14A", fontSize = 24.sp, fontWeight = FontWeight.Bold)
                                        }
                                    }
                                    Column(horizontalAlignment = Alignment.End) {
                                        Text("CLASS", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.outline)
                                        Text("Premium Economy", fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary)
                                    }
                                }
                            }
                        }

                        // Dashed Line
                        Row(modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp).offset(y = (-100).dp), verticalAlignment = Alignment.CenterVertically) {
                            Box(modifier = Modifier.size(width = 16.dp, height = 32.dp).offset(x = (-16).dp).clip(RoundedCornerShape(topEnd = 16.dp, bottomEnd = 16.dp)).background(MaterialTheme.colorScheme.surface))
                            Box(modifier = Modifier.weight(1f).height(2.dp).background(Brush.horizontalGradient(listOf(surface_container_high, surface_container_high)))) // Mock dashed line
                            Box(modifier = Modifier.size(width = 16.dp, height = 32.dp).offset(x = 16.dp).clip(RoundedCornerShape(topStart = 16.dp, bottomStart = 16.dp)).background(MaterialTheme.colorScheme.surface))
                        }

                        // Barcode
                        Column(
                            modifier = Modifier.fillMaxWidth().offset(y = (-100).dp).padding(top = 24.dp, bottom = 24.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Surface(color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.05f), shape = RoundedCornerShape(4.dp), modifier = Modifier.width(200.dp).height(80.dp)) {
                                Row(modifier = Modifier.fillMaxSize().padding(16.dp), horizontalArrangement = Arrangement.Center) {
                                    // Simulated barcode bars
                                    listOf(4.dp, 8.dp, 4.dp, 12.dp, 4.dp, 4.dp, 16.dp, 4.dp, 8.dp, 4.dp, 12.dp, 4.dp).forEach { width ->
                                        Box(modifier = Modifier.width(width).fillMaxHeight().background(MaterialTheme.colorScheme.onSurface).padding(horizontal = 2.dp))
                                        Spacer(modifier = Modifier.width(4.dp))
                                    }
                                }
                            }
                            Spacer(modifier = Modifier.height(12.dp))
                            Text("SCAN AT CHECK-IN", fontSize = 10.sp, fontWeight = FontWeight.Medium, color = MaterialTheme.colorScheme.outline, letterSpacing = 2.sp)
                        }
                    }
                }

                Spacer(modifier = Modifier.height((-68).dp)) // Adjust for the offset content above

                // Actions
                Button(
                    onClick = { },
                    modifier = Modifier.fillMaxWidth().height(56.dp),
                    shape = RoundedCornerShape(16.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
                ) {
                    Icon(Icons.Filled.Download, contentDescription = null, modifier = Modifier.padding(end = 8.dp))
                    Text("Download Ticket", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                }
                Spacer(modifier = Modifier.height(16.dp))
                TextButton(
                    onClick = onNavigateHome,
                    modifier = Modifier.fillMaxWidth().height(56.dp)
                ) {
                    Icon(Icons.Filled.Home, contentDescription = null, modifier = Modifier.padding(end = 8.dp))
                    Text("Return to Home", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                }

                Spacer(modifier = Modifier.height(32.dp))

                // Secondary Info
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                    Surface(color = surface_container_low, shape = RoundedCornerShape(16.dp), modifier = Modifier.weight(1f)) {
                        Column(modifier = Modifier.padding(24.dp)) {
                            Icon(Icons.Filled.CalendarMonth, contentDescription = null, tint = MaterialTheme.colorScheme.secondary, modifier = Modifier.padding(bottom = 12.dp))
                            Text("Add to Calendar", fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onSurface)
                            Spacer(modifier = Modifier.height(4.dp))
                            Text("Get reminders for your check-in and boarding times.", fontSize = 12.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
                        }
                    }
                    Surface(color = surface_container_low, shape = RoundedCornerShape(16.dp), modifier = Modifier.weight(1f)) {
                        Column(modifier = Modifier.padding(24.dp)) {
                            Icon(Icons.Filled.Mail, contentDescription = null, tint = MaterialTheme.colorScheme.primary, modifier = Modifier.padding(bottom = 12.dp))
                            Text("Email Sent", fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onSurface)
                            Spacer(modifier = Modifier.height(4.dp))
                            Text("A copy of your itinerary has been sent to your email.", fontSize = 12.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
                        }
                    }
                }
            }
        }
    }
}
