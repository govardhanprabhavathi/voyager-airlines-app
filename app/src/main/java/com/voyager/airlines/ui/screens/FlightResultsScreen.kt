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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.voyager.airlines.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FlightResultsScreen(
    onNavigateBack: () -> Unit,
    onNavigateHome: () -> Unit,
    onNavigateSearch: () -> Unit,
    onNavigateOffers: () -> Unit,
    onBookFlight: () -> Unit
) {
    val scrollState = rememberScrollState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { 
                    Text("Voyager", fontWeight = FontWeight.Black, color = MaterialTheme.colorScheme.primary, fontStyle = FontStyle.Italic) 
                },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Back", tint = MaterialTheme.colorScheme.primary)
                    }
                },
                actions = {
                    // Navigation links for large screens, hidden on mobile in HTML but we can omit or just put avatar
                    AsyncImage(
                        model = "https://lh3.googleusercontent.com/aida-public/AB6AXuCL0q00vLoF2YK1Zhft4cr9_BVMtiCrmsmKOoJOgG73ndT473z6ZrgXw55jaWGmlrLOKoPNy80JOb9AtDAA7rzza7Yb25BaPzjGYj7_5IsSNem9I-61OplwJbvwlyXVoSOSvcS_IQ-vwdh6KhY42MvNm1Nj8hVUPaP26HGtVcRvHWiKGsKQPykMu6c6kwUeU9IpbRxXQ1vOjy-NbFGx0o-TgWZuSKkRCykvaBInLkA6JcbFW3thQxgeu00rsuy7PqbphOdUTGQTbJh6",
                        contentDescription = "User Profile",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .padding(end = 16.dp)
                            .size(32.dp)
                            .clip(CircleShape)
                            .border(2.dp, MaterialTheme.colorScheme.primary.copy(alpha = 0.2f), CircleShape)
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.surface.copy(alpha = 0.9f))
            )
        },
        bottomBar = {
            // Custom Rounded Bottom Nav (Search Active)
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
                    NavBarItem("Search", Icons.Filled.Search, true) { onNavigateSearch() }
                    NavBarItem("History", Icons.Filled.History, false) {}
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
                .padding(horizontal = 16.dp)
                .padding(bottom = 32.dp)
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            // Search Header Info
            Column(modifier = Modifier.padding(horizontal = 8.dp)) {
                Text(
                    "SELECT DEPARTURE",
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 1.sp
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    "London to New York",
                    style = MaterialTheme.typography.headlineLarge,
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 32.sp
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    "Oct 24 • 1 Adult • Economy",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    fontWeight = FontWeight.Medium
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Editorial Filter Tabs
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(rememberScrollState())
                    .padding(horizontal = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                FilterTab("Cheapest", "$432", true)
                FilterTab("Fastest", "7h 20m", false)
                FilterTab("Best", "Recommended", false)
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Flight Results List
            Column(verticalArrangement = Arrangement.spacedBy(24.dp)) {
                // Featured/Cheapest Flight
                FlightCard(
                    airlineName = "SkyLink Air",
                    flightNum = "SL-402 • Boeing 787",
                    depTime = "08:45",
                    arrTime = "11:40",
                    duration = "7h 55m • Non-stop",
                    depCode = "LHR",
                    arrCode = "JFK",
                    originalPrice = "$510",
                    currentPrice = "$432",
                    isFeatured = true,
                    airlineLogo = "https://lh3.googleusercontent.com/aida-public/AB6AXuDnX1tfjRIqWjLJSgJB4ztsjZI0TqoreHfeEJ3PqT0BuzwxQDt2zO8hTS2iUXOzRMCiawgnMcrAVi7mxh5yH0JPeHrWbqAdLy4wY93bD4AAcg11a7G7efTKb9ZNuScEyYmcJZ3fwqbYbjcd1nhHIwn8YXoeLkmqZ4teQvbAnWvpyFcFPH9vQqDbxMnTWEoHxOm6uYt7Hr69JyeUGUfqjqZDVv46DXBpy6UG_aLIGIicj0Nu2vq4ldZyR6smCRXMdotp0zVf2plUXUbs",
                    icon = Icons.Filled.FlightTakeoff,
                    onBookFlight = onBookFlight
                )

                // Flight Card 2
                FlightCard(
                    airlineName = "Global Reach",
                    flightNum = "GR-118 • Airbus A350",
                    depTime = "13:20",
                    arrTime = "18:35",
                    duration = "10h 15m • 1 Stop",
                    layover = "Layover in DUB",
                    depCode = "LHR",
                    arrCode = "JFK",
                    originalPrice = null,
                    currentPrice = "$485",
                    isFeatured = false,
                    airlineLogo = "https://lh3.googleusercontent.com/aida-public/AB6AXuDSLZx1CSyG59SyibEV0Mjquac2jdPijgyAv8ArMiw7HK07q1-zM5XURpDdhsEjBZlOSJ0wLTdaHtgSVrWhmMpTZK1Jl1Jzv2AAhpGp1XKW7V9iVz9qv6CdIEn1kEufayXNUkEcgsi-pgUeNEOyp0BtOcO2npnTCU7_3LMKoL9qp8z1L6023uTOfl7-QigWP5XuyoWR9adCpdCmKBiRhSeaHPepzOjw_kxyAUlFASVe72tfdz6FM0y01jE89pOhoChD-Xomjka07vot",
                    icon = Icons.Filled.Flight,
                    onBookFlight = onBookFlight
                )

                // Flight Card 3
                FlightCard(
                    airlineName = "Vanguard Jet",
                    flightNum = "VJ-99 • Boeing 777X",
                    depTime = "20:00",
                    arrTime = "22:20",
                    duration = "7h 20m • Non-stop",
                    depCode = "LHR",
                    arrCode = "JFK",
                    originalPrice = null,
                    currentPrice = "$620",
                    isFeatured = false,
                    airlineLogo = "https://lh3.googleusercontent.com/aida-public/AB6AXuDBuY48f3eDKImoCCyepC9ifNaKfIGAd_41a6tMxyrg0lLZoVjgxhUSZ1gQ8GvBxPDyJNNHCi3CxjSeIIlBAwGK4OuwWbUGp1YfAQshYxdOw1uV6iFIy-3dyuUE5wbHam0CH-Qtz01CaMxQbU71_DQ-JkAOi1MDBCc2dEQkdORPyFaS_6uG8uwi9r3PO2NWWyJCdZMdS5avUXSzPvvsLvG4dje8owzFgklWvXmAYqoxLnGAeBTUcpKo3cxkq5Ug8MouTUMZ1Luj5NBT",
                    icon = Icons.Filled.FlightTakeoff,
                    onBookFlight = onBookFlight
                )
            }
        }
    }
}

@Composable
fun FilterTab(label: String, value: String, isSelected: Boolean) {
    Surface(
        color = if (isSelected) MaterialTheme.colorScheme.primary else surface_container_high,
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier.width(120.dp).clickable { }
    ) {
        Column(modifier = Modifier.padding(horizontal = 24.dp, vertical = 12.dp)) {
            Text(
                label,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = if (isSelected) MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.8f) else MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.7f),
                letterSpacing = 0.5.sp
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                value,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = if (isSelected) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
fun FlightCard(
    airlineName: String,
    flightNum: String,
    depTime: String,
    arrTime: String,
    duration: String,
    layover: String? = null,
    depCode: String,
    arrCode: String,
    originalPrice: String?,
    currentPrice: String,
    isFeatured: Boolean,
    airlineLogo: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    onBookFlight: () -> Unit
) {
    Surface(
        color = surface_container_lowest,
        shape = RoundedCornerShape(24.dp),
        shadowElevation = if (isFeatured) 8.dp else 2.dp,
        modifier = Modifier.fillMaxWidth().clickable { onBookFlight() }
    ) {
        Box {
            if (isFeatured) {
                Surface(
                    color = tertiary_fixed,
                    shape = RoundedCornerShape(bottomStart = 16.dp),
                    modifier = Modifier.align(Alignment.TopEnd)
                ) {
                    Text(
                        "BEST VALUE",
                        color = on_tertiary_fixed,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 6.dp)
                    )
                }
            }

            Column(modifier = Modifier.padding(24.dp)) {
                // Airline Info
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Surface(color = surface_container_low, shape = RoundedCornerShape(12.dp), modifier = Modifier.size(48.dp)) {
                        AsyncImage(model = airlineLogo, contentDescription = airlineName, modifier = Modifier.padding(8.dp))
                    }
                    Spacer(modifier = Modifier.width(16.dp))
                    Column {
                        Text(airlineName, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onSurface)
                        Text(flightNum, fontSize = 12.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                // Journey Info
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(depTime, fontSize = 24.sp, fontWeight = FontWeight.Black, color = if (isFeatured) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant)
                        Text(depCode, fontSize = 14.sp, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onSurfaceVariant)
                    }

                    Column(modifier = Modifier.weight(1f).padding(horizontal = 16.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(text = duration, fontSize = 10.sp, fontWeight = FontWeight.Bold, color = if (layover != null) MaterialTheme.colorScheme.secondary else MaterialTheme.colorScheme.onSurfaceVariant)
                        Box(modifier = Modifier.fillMaxWidth().height(2.dp).background(MaterialTheme.colorScheme.outlineVariant).padding(vertical = 8.dp)) {
                            Icon(icon, contentDescription = null, tint = if (isFeatured) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.outline, modifier = Modifier.align(Alignment.Center).background(surface_container_lowest))
                        }
                        if (layover != null) {
                            Text(text = layover!!, fontSize = 10.sp, fontWeight = FontWeight.Medium, color = MaterialTheme.colorScheme.onSurfaceVariant)
                        }
                    }

                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(arrTime, fontSize = 24.sp, fontWeight = FontWeight.Black, color = if (isFeatured) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant)
                        Text(arrCode, fontSize = 14.sp, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onSurfaceVariant)
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                // Price and Action
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Bottom
                ) {
                    Column {
                        if (originalPrice != null) {
                            Text(text = originalPrice!!, fontSize = 14.sp, color = MaterialTheme.colorScheme.onSurfaceVariant, textDecoration = TextDecoration.LineThrough)
                        }
                        Text(currentPrice, fontSize = 32.sp, fontWeight = FontWeight.Black, color = MaterialTheme.colorScheme.onSurface)
                    }

                    Button(
                        onClick = onBookFlight,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (isFeatured) MaterialTheme.colorScheme.primary else surface_container_high,
                            contentColor = if (isFeatured) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.primary
                        ),
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier.height(48.dp)
                    ) {
                        Text("Book", fontWeight = FontWeight.Bold, modifier = Modifier.padding(horizontal = 16.dp))
                    }
                }
            }
        }
    }
}
