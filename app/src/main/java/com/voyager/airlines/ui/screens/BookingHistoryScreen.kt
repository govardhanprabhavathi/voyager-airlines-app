package com.voyager.airlines.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.voyager.airlines.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookingHistoryScreen(
    onNavigateHome: () -> Unit,
    onNavigateSearch: () -> Unit,
    onNavigateOffers: () -> Unit
) {
    val scrollState = rememberScrollState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { 
                    Text("Voyager", fontWeight = FontWeight.Black, color = MaterialTheme.colorScheme.primary, fontStyle = FontStyle.Italic) 
                },
                navigationIcon = {
                    IconButton(onClick = onNavigateHome) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Back", tint = MaterialTheme.colorScheme.primary)
                    }
                },
                actions = {
                    AsyncImage(
                        model = "https://lh3.googleusercontent.com/aida-public/AB6AXuBmANYKuhZ6A86kBamk-UGhWjqHO3ERC4r5ZQ0oNj2H_4HA2q67QrtZaSlo7uHu5GWvf_HZnN2d-p_i2H7xRtBiDaQXzCWDpxE6lRu_4l3zGL8kHq-0OluHKkcx49jUgqVs8O1ghjhVIIWe_Z0MH0RtqkoXaVC0nlAzE3XJKXKsTFWfsZl1s_EWs4Y5mDLS67JKiLv5MyPViRwfezmD4Y8ofFtdHaK9O1VERFL3hXsTBibnGUpPtJ95x1zwWi24_2hrwdU1EEWoAZpf",
                        contentDescription = "User Profile",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .padding(end = 16.dp)
                            .size(32.dp)
                            .clip(CircleShape)
                            .border(1.dp, MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.2f), CircleShape)
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.surface.copy(alpha = 0.9f))
            )
        },
        bottomBar = {
            // Custom Rounded Bottom Nav (History Active)
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 0.dp),
                color = MaterialTheme.colorScheme.surface.copy(alpha = 0.95f),
                tonalElevation = 8.dp,
                shadowElevation = 16.dp,
                shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 12.dp)
                        .navigationBarsPadding(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    NavBarItem("Home", Icons.Filled.Home, false) { onNavigateHome() }
                    NavBarItem("Search", Icons.Filled.Search, false) { onNavigateSearch() }
                    NavBarItem("History", Icons.Filled.History, true) {}
                    NavBarItem("Offers", Icons.Filled.LocalOffer, false) { onNavigateOffers() }
                }
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.surface)
                .verticalScroll(scrollState)
                .padding(padding)
                .padding(horizontal = 24.dp)
                .padding(bottom = 32.dp)
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            // Header Section
            Text(
                "My Bookings",
                style = MaterialTheme.typography.headlineLarge,
                color = MaterialTheme.colorScheme.onSurface,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 32.sp
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                "Manage your upcoming journeys and review past travels.",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Filter Chips
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(rememberScrollState()),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Chip("All Bookings", true)
                Chip("Upcoming", false)
                Chip("Completed", false)
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Booking List
            Column(verticalArrangement = Arrangement.spacedBy(24.dp)) {
                // Booking Card 1: Upcoming
                UpcomingBookingCard()

                // Booking Card 2: Completed
                CompletedBookingCard(
                    ref = "REF: VY-771204",
                    price = "$890.00",
                    depCity = "New York", depCode = "JFK", depTime = "Sep 12, 02:15 PM",
                    arrCity = "Paris", arrCode = "CDG", arrTime = "Sep 13, 06:20 AM",
                    statusText = "Flight landed on time",
                    actionText = "Download Invoice"
                )

                // Booking Card 3: Completed
                CompletedBookingCard(
                    ref = "REF: VY-650991",
                    price = "$450.00",
                    depCity = "Berlin", depCode = "BER", depTime = "Aug 05, 09:00 AM",
                    arrCity = "Rome", arrCode = "FCO", arrTime = "Aug 05, 11:15 AM",
                    statusText = "Trip Rating: 5/5",
                    actionText = "Repeat Trip"
                )
            }

            Spacer(modifier = Modifier.height(48.dp))

            // Featured Destination Promo
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(256.dp)
                    .clip(RoundedCornerShape(40.dp))
            ) {
                AsyncImage(
                    model = "https://lh3.googleusercontent.com/aida-public/AB6AXuDStz9C54vQEtgt-YK2f5ELW19LT0RphlPNHzEB-Lge43C2TT1kMimx074wv7_GcTx9Yf6ePEOgPL8Mo8UuK3X3Jiq-99Spdl8YRRBGqYlWLavXphGPQZVY8JgFVvkjkNO4lhhAb2GjBJvaJedL-H9jXivvtyfXVFeE5q0VPQd1-DDkx3N7w_SMu9fKl672BJq15n9R9bzFNacilFYcP1jDd7DGFcQWdPw53EZtQs6IakPewdBOWPob6FcV2fbT_JJoKx7I2MrAmHUn",
                    contentDescription = "Maldives",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
                Box(modifier = Modifier.fillMaxSize().background(Brush.verticalGradient(listOf(Color.Transparent, MaterialTheme.colorScheme.primary.copy(alpha = 0.9f)))))
                
                Column(modifier = Modifier.align(Alignment.BottomStart).padding(32.dp)) {
                    Text("MEMBER SPECIAL", color = MaterialTheme.colorScheme.onPrimaryContainer, fontSize = 10.sp, fontWeight = FontWeight.Bold, letterSpacing = 1.sp)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text("Ready for your next\nadventure, Alex?", color = Color.White, fontSize = 24.sp, fontWeight = FontWeight.Black, lineHeight = 28.sp)
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(
                        onClick = onNavigateOffers,
                        colors = ButtonDefaults.buttonColors(containerColor = Color.White, contentColor = MaterialTheme.colorScheme.primary),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Text("Explore Maldives", fontWeight = FontWeight.Bold)
                    }
                }
            }
        }
    }
}

@Composable
fun Chip(label: String, isSelected: Boolean) {
    Surface(
        color = if (isSelected) MaterialTheme.colorScheme.primary else surface_container_high,
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier.clickable { }
    ) {
        Text(
            label,
            color = if (isSelected) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurfaceVariant,
            fontWeight = FontWeight.SemiBold,
            fontSize = 14.sp,
            modifier = Modifier.padding(horizontal = 24.dp, vertical = 10.dp)
        )
    }
}

@Composable
fun UpcomingBookingCard() {
    Surface(
        color = surface_container_lowest,
        shape = RoundedCornerShape(32.dp),
        shadowElevation = 4.dp,
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(24.dp)) {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.Top) {
                Column {
                    Surface(color = secondary_fixed, shape = RoundedCornerShape(16.dp)) {
                        Text("UPCOMING", color = on_secondary_fixed_variant, fontSize = 10.sp, fontWeight = FontWeight.Bold, letterSpacing = 1.sp, modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp))
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Text("REF: VY-882910", fontSize = 11.sp, color = MaterialTheme.colorScheme.outline, fontWeight = FontWeight.Medium)
                }
                Text("$1,240.00", fontSize = 24.sp, fontWeight = FontWeight.Black, color = MaterialTheme.colorScheme.primary)
            }

            Spacer(modifier = Modifier.height(32.dp))

            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                Column(modifier = Modifier.weight(1f)) {
                    Text("LONDON", fontSize = 12.sp, fontWeight = FontWeight.SemiBold, color = MaterialTheme.colorScheme.onSurfaceVariant, letterSpacing = 1.sp)
                    Text("LHR", fontSize = 24.sp, fontWeight = FontWeight.Bold)
                    Text("Oct 24, 10:30 AM", fontSize = 14.sp, color = MaterialTheme.colorScheme.outline)
                }

                Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.padding(horizontal = 16.dp)) {
                    Icon(Icons.Filled.FlightTakeoff, contentDescription = null, tint = primary_fixed_dim, modifier = Modifier.size(32.dp))
                    Box(modifier = Modifier.width(64.dp).height(2.dp).background(surface_container_high).padding(top = 8.dp)) {
                        Box(modifier = Modifier.fillMaxWidth(0.5f).fillMaxHeight().background(MaterialTheme.colorScheme.primaryContainer))
                    }
                }

                Column(modifier = Modifier.weight(1f), horizontalAlignment = Alignment.End) {
                    Text("TOKYO", fontSize = 12.sp, fontWeight = FontWeight.SemiBold, color = MaterialTheme.colorScheme.onSurfaceVariant, letterSpacing = 1.sp)
                    Text("HND", fontSize = 24.sp, fontWeight = FontWeight.Bold)
                    Text("Oct 25, 08:45 AM", fontSize = 14.sp, color = MaterialTheme.colorScheme.outline)
                }
            }

            Spacer(modifier = Modifier.height(24.dp))
            Box(modifier = Modifier.fillMaxWidth().height(1.dp).background(MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.3f)))
            Spacer(modifier = Modifier.height(24.dp))

            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    AsyncImage(
                        model = "https://lh3.googleusercontent.com/aida-public/AB6AXuBzGpWfkHyq4RibCNxAQm1nIjuQgVlyzHGsJzAerwSEim4fNARvkzLc-TWai2tG29eT44gCOtlvOO1oI9enSm_XczejqMS-sBp2JfxPO7lXrRSgzQzpZUNVwPOwkKRbqkpUh1nLpor6VMXui4MXqtUikQaq7ghEdgizbCTpXvG55NZmOIIUdaQg6NKAEtJum6xDaykMvXFY4tatAIpyDJhxEYDpF-1uw4HWNJB2iip5MqGw7DJ8Lc3_7KurwtwDEM9nw9nG_9X8v-qD",
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.size(32.dp).clip(CircleShape).border(2.dp, surface_container_lowest, CircleShape)
                    )
                    Box(
                        modifier = Modifier
                            .offset(x = (-8).dp)
                            .size(32.dp)
                            .clip(CircleShape)
                            .background(MaterialTheme.colorScheme.primaryContainer)
                            .border(2.dp, surface_container_lowest, CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Text("+2", color = Color.White, fontSize = 10.sp, fontWeight = FontWeight.Bold)
                    }
                }

                Button(
                    onClick = { },
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
                ) {
                    Text("View Ticket", fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}

@Composable
fun CompletedBookingCard(ref: String, price: String, depCity: String, depCode: String, depTime: String, arrCity: String, arrCode: String, arrTime: String, statusText: String, actionText: String) {
    Surface(
        color = surface_container_low,
        shape = RoundedCornerShape(32.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(24.dp)) {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.Top) {
                Column {
                    Surface(color = tertiary_fixed, shape = RoundedCornerShape(16.dp)) {
                        Text("COMPLETED", color = on_tertiary_fixed_variant, fontSize = 10.sp, fontWeight = FontWeight.Bold, letterSpacing = 1.sp, modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp))
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(ref, fontSize = 11.sp, color = MaterialTheme.colorScheme.outline, fontWeight = FontWeight.Medium)
                }
                Text(price, fontSize = 24.sp, fontWeight = FontWeight.Black, color = MaterialTheme.colorScheme.onSurfaceVariant)
            }

            Spacer(modifier = Modifier.height(32.dp))

            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(depCity.uppercase(), fontSize = 12.sp, fontWeight = FontWeight.SemiBold, color = MaterialTheme.colorScheme.onSurfaceVariant, letterSpacing = 1.sp)
                    Text(depCode, fontSize = 24.sp, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onSurfaceVariant)
                    Text(depTime, fontSize = 14.sp, color = MaterialTheme.colorScheme.outline)
                }

                Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.padding(horizontal = 16.dp)) {
                    Icon(Icons.Filled.CheckCircle, contentDescription = null, tint = MaterialTheme.colorScheme.outline, modifier = Modifier.size(32.dp))
                }

                Column(modifier = Modifier.weight(1f), horizontalAlignment = Alignment.End) {
                    Text(arrCity.uppercase(), fontSize = 12.sp, fontWeight = FontWeight.SemiBold, color = MaterialTheme.colorScheme.onSurfaceVariant, letterSpacing = 1.sp)
                    Text(arrCode, fontSize = 24.sp, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onSurfaceVariant)
                    Text(arrTime, fontSize = 14.sp, color = MaterialTheme.colorScheme.outline)
                }
            }

            Spacer(modifier = Modifier.height(24.dp))
            Box(modifier = Modifier.fillMaxWidth().height(1.dp).background(MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.3f)))
            Spacer(modifier = Modifier.height(24.dp))

            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                Text(statusText, fontSize = 14.sp, color = MaterialTheme.colorScheme.outline, fontWeight = FontWeight.Medium)
                Text(actionText, color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Bold, fontSize = 14.sp)
            }
        }
    }
}
