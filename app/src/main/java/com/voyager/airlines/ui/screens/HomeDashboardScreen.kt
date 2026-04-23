package com.voyager.airlines.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
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
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeDashboardScreen(
    viewModel: com.voyager.airlines.viewmodel.VoyagerViewModel,
    onNavigateToSearch: () -> Unit, 
    onNavigateToHistory: () -> Unit, 
    onNavigateToOffers: () -> Unit,
    onNavigateToResults: () -> Unit
) {
    val scrollState = rememberScrollState()

    // State for Quick Search
    var selectedFrom by remember { mutableStateOf("San Francisco (SFO)") }
    var showFromDropdown by remember { mutableStateOf(false) }

    var selectedTo by remember { mutableStateOf("Destination City") }
    var showToDropdown by remember { mutableStateOf(false) }

    val cities = listOf("San Francisco (SFO)", "New York (JFK)", "London (LHR)", "Sydney (SYD)", "Kyoto (KIX)", "Paris (CDG)")

    var selectedDate by remember { mutableStateOf("12 Oct") }
    var showDatePicker by remember { mutableStateOf(false) }
    val datePickerState = rememberDatePickerState()

    var selectedPassengers by remember { mutableStateOf("1 Adult, Economy") }
    var showPassengerDropdown by remember { mutableStateOf(false) }
    val passengersOptions = listOf("1 Adult, Economy", "2 Adults, Economy", "1 Adult, Business", "2 Adults, Business")

    Scaffold(
        topBar = {
            TopAppBar(
                title = { 
                    Text("Voyager", fontWeight = FontWeight.Black, color = MaterialTheme.colorScheme.primary, fontStyle = FontStyle.Italic) 
                },
                navigationIcon = {
                    IconButton(onClick = { /* Back handled by system */ }) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Back", tint = MaterialTheme.colorScheme.primary)
                    }
                },
                actions = {
                    AsyncImage(
                        model = "https://lh3.googleusercontent.com/aida-public/AB6AXuAoARkab3pYoIeQ1yA733IWZ4O18KPYi4DI0Dc_W6EcnEhP01apbnojBKvViqbiohXahWl9jpC-6eJwfUhaPjT9zxNgT33xIVnBVMKNNQjCnJ12Lubxl7-5zhw1FZckIqxvL9gNrvsGYtLT8YoWl_z09JYMTt16Gm0EBcjSXcTanOkSmtPMrvQl4cRj-vscn6v3QTmQdQcDE2RiqXht42X_hLKJHvDvxywJsTejY_J0_2LYr9j4BPh4yhKyuy0BYtsYCfPk8exx_XMI",
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
            // Custom Rounded Bottom Nav
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
                    NavBarItem("Home", Icons.Filled.Home, true) {}
                    NavBarItem("Search", Icons.Filled.Search, false) { onNavigateToSearch() }
                    NavBarItem("History", Icons.Filled.History, false) { onNavigateToHistory() }
                    NavBarItem("Offers", Icons.Filled.LocalOffer, false) { onNavigateToOffers() }
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

            // Welcome Section
            Text(
                "WELCOME BACK, ALEX",
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.secondary,
                fontWeight = FontWeight.Bold,
                letterSpacing = 1.sp
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                "Where would you",
                style = MaterialTheme.typography.headlineLarge,
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 36.sp,
                lineHeight = 40.sp
            )
            Text(
                "like to go?",
                style = MaterialTheme.typography.headlineLarge.copy(fontStyle = FontStyle.Italic),
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                fontWeight = FontWeight.Bold,
                fontSize = 36.sp,
                lineHeight = 40.sp
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Quick Search Widget
            Surface(
                color = surface_container_low,
                shape = RoundedCornerShape(32.dp),
                border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f))
            ) {
                Column(modifier = Modifier.padding(24.dp)) {
                    Box(modifier = Modifier.fillMaxWidth()) {
                        SearchField("FROM", selectedFrom, Icons.Filled.FlightTakeoff, { showFromDropdown = true }, Modifier.fillMaxWidth())
                        DropdownMenu(expanded = showFromDropdown, onDismissRequest = { showFromDropdown = false }) {
                            cities.forEach { city ->
                                DropdownMenuItem(text = { Text(city) }, onClick = { selectedFrom = city; showFromDropdown = false })
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    Box(modifier = Modifier.fillMaxWidth()) {
                        SearchField("TO", selectedTo, Icons.Filled.FlightLand, { showToDropdown = true }, Modifier.fillMaxWidth())
                        DropdownMenu(expanded = showToDropdown, onDismissRequest = { showToDropdown = false }) {
                            cities.forEach { city ->
                                DropdownMenuItem(text = { Text(city) }, onClick = { selectedTo = city; showToDropdown = false })
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                        InfoField(selectedDate, Icons.Filled.CalendarToday, { showDatePicker = true }, Modifier.weight(1f))
                        
                        Box(modifier = Modifier.weight(1f)) {
                            InfoField(selectedPassengers, Icons.Filled.Person, { showPassengerDropdown = true }, Modifier.fillMaxWidth())
                            DropdownMenu(expanded = showPassengerDropdown, onDismissRequest = { showPassengerDropdown = false }) {
                                passengersOptions.forEach { opt ->
                                    DropdownMenuItem(text = { Text(opt) }, onClick = { selectedPassengers = opt; showPassengerDropdown = false })
                                }
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(24.dp))
                    Button(
                        onClick = { 
                            val sourceCode = selectedFrom.substringAfterLast("(").removeSuffix(")")
                            val destCode = selectedTo.substringAfterLast("(").removeSuffix(")")
                            viewModel.searchFlights(sourceCode, destCode)
                            onNavigateToResults()
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp),
                        shape = RoundedCornerShape(16.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
                    ) {
                        Icon(Icons.Filled.Search, contentDescription = null, modifier = Modifier.padding(end = 8.dp))
                        Text("Find Best Flights", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                    }
                }
            }

            if (showDatePicker) {
                DatePickerDialog(
                    onDismissRequest = { showDatePicker = false },
                    confirmButton = {
                        TextButton(onClick = {
                            val sdf = SimpleDateFormat("dd MMM", Locale.getDefault())
                            val date = datePickerState.selectedDateMillis?.let { sdf.format(Date(it)) } ?: "Select Date"
                            selectedDate = date
                            showDatePicker = false
                        }) {
                            Text("OK")
                        }
                    },
                    dismissButton = {
                        TextButton(onClick = { showDatePicker = false }) { Text("Cancel") }
                    }
                ) {
                    DatePicker(state = datePickerState)
                }
            }

            Spacer(modifier = Modifier.height(40.dp))

            // Exclusive Offers
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.Bottom) {
                Column {
                    Text("Exclusive Offers", style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold)
                    Text("Curated deals just for you", style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                }
                Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.clickable { onNavigateToOffers() }) {
                    Text("View All", style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Bold)
                    Icon(Icons.Filled.ChevronRight, contentDescription = null, tint = MaterialTheme.colorScheme.primary, modifier = Modifier.size(16.dp))
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            LazyRow(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                item {
                    OfferCard(
                        "Early Bird Special", "20% OFF", "On International Flights", "CODE: VOYAGER20", Icons.Filled.Stars, 
                        Color(0xFFfe6f42), Color(0xFF631800), onNavigateToOffers
                    )
                }
                item {
                    OfferCard(
                        "Cashback Reward", "\$50 BACK", "Instant wallet credit", "Min. Spend \$400", Icons.Filled.AccountBalanceWallet,
                        Color(0xFF006e2a), Color(0xFF54f67a), onNavigateToOffers
                    )
                }
                item {
                    OfferCard(
                        "Hotel Bundle", "SAVE \$120", "Flight + Stay packages", "Summer Deals", Icons.Filled.Hotel,
                        Color(0xFF0056d2), Color(0xFFccd8ff), onNavigateToOffers
                    )
                }
            }

            Spacer(modifier = Modifier.height(40.dp))

            // Trending Destinations
            Column {
                Text("Trending Destinations", style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold)
                Text("Recommended based on your history", style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Masonry Grid
            Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(360.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    // Sydney (Large)
                    Box(modifier = Modifier.weight(1f).fillMaxHeight().clip(RoundedCornerShape(32.dp)).clickable { 
                        viewModel.searchFlights("SFO", "SYD")
                        onNavigateToResults()
                    }) {
                        AsyncImage(
                            model = "https://lh3.googleusercontent.com/aida-public/AB6AXuBqtvRWshjTK8yLQUIhB_swKtNWYbogj3C_1MgtbOaW2qDAFnuyEFzSwokW9NiG4ckHD8vBXQ3HlF80xZvWQCc8BokEgrFC0GcWULwJCpXDkyw5x4Y8ENG_GKorOT-OEY24pDcLGi83BaEOClYWPZyq3wNbodik_i1txa0RBAec55o6dH_MjD8n62OeMQihw5H6nxThLfBITLNXsLj05alqm-pjXxM77ZEA_kwmls59O5lqof04XDWelqZBdOlDzKHNnP3dSVQoN7ou",
                            contentDescription = "Sydney", contentScale = ContentScale.Crop, modifier = Modifier.fillMaxSize()
                        )
                        Box(modifier = Modifier.fillMaxSize().background(Brush.verticalGradient(listOf(Color.Transparent, Color.Black.copy(alpha = 0.8f)))))
                        Column(modifier = Modifier.align(Alignment.BottomStart).padding(24.dp)) {
                            Surface(color = tertiary_fixed, shape = RoundedCornerShape(16.dp)) {
                                Text("TOP CHOICE", color = on_tertiary_fixed, fontSize = 10.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp))
                            }
                            Spacer(modifier = Modifier.height(8.dp))
                            Text("Sydney", color = Color.White, fontSize = 28.sp, fontWeight = FontWeight.Black)
                            Text("Australia • Starting \$890", color = Color.White.copy(alpha = 0.8f), fontSize = 12.sp)
                        }
                    }

                    // Kyoto & London
                    Column(modifier = Modifier.weight(1f).fillMaxHeight(), verticalArrangement = Arrangement.spacedBy(16.dp)) {
                        SmallDestCard(
                            "Kyoto", "$650", 
                            "https://lh3.googleusercontent.com/aida-public/AB6AXuBeXfcGg7rHPCN91TUgMLvjueN2fJayV1px2fKEexwbYlUHP3CtRCMsbZsAamLj1OsD6csKw0RcggLA5bX9cPRwXwT6V-3JzTI5S6DXdO2G1WuPTrgtMW3quYSnCAZoV99f-vlgryLnDGdHCY7m_wYhy3PQM2d42YApWzE7pkNgoOxtfg9FW0R1_pEc_ikQhJDUiq7x-FvK3a9j3nQyx4dyglPmXTQL9EPdSUc2591oGpaMo7AcEhtvlR6KV0ucgNjddr_rnkazRGzO",
                            Modifier.weight(1f)
                        ) {
                            viewModel.searchFlights("SFO", "KIX")
                            onNavigateToResults()
                        }
                        SmallDestCard(
                            "London", "$420", 
                            "https://lh3.googleusercontent.com/aida-public/AB6AXuAlSk8_hprtQx0s0W6wR3QOLlTqfAi4n-wiitddjatdPU8tGDcnh1OGbsl_-VKWDJlnWRkeNmRf-JifcAFXvwBQ_CWesebLqK_1lBbdS6wnzoc25JT7Vc0cNdw_Nb8ipCLuTnIzIm3WP1Gfb-FQRQlvVsyX3-ThfzubUCR1BdxT2ACyB0XVhvyt-sMi3dGi_YRgcREgfE4_xFQJj_QxsaabNCDwUuwojDAZQfHxmywEK5kwu0Tdaes8UEBF85CX9rRcw-YUgvETeSiU",
                            Modifier.weight(1f)
                        ) {
                            viewModel.searchFlights("SFO", "LHR")
                            onNavigateToResults()
                        }
                    }
                }

                // Paris (Wide)
                Box(modifier = Modifier.fillMaxWidth().height(172.dp).clip(RoundedCornerShape(32.dp)).clickable { 
                    viewModel.searchFlights("SFO", "CDG")
                    onNavigateToResults()
                }) {
                    AsyncImage(
                        model = "https://lh3.googleusercontent.com/aida-public/AB6AXuAtQoQZVdBr7Oyie7JJ4RvOQcoXtFdOsW9uzs7LO-c3LIwmE1_1xwk-8Jdv3gK00qD8ual4OUul0aM5ocS9VS7DuKWaUNMKEtMCNUP232oUzFDmbU-Eiyn8jsla77rMK1Xwwweo5P3yJWzb6sjpG68s2rRVWrFh9sWkhEfXdaCX_feVYDwQrh59q_nzJ6uUrTJl2mDqT7-QBnM8jaz87cpOnMVPKc09NrXQ5yuNnQAtA5aLMHgInYTJCXncdruAP9oHmdEmc8oA6LDT",
                        contentDescription = "Paris", contentScale = ContentScale.Crop, modifier = Modifier.fillMaxSize()
                    )
                    Box(modifier = Modifier.fillMaxSize().background(Brush.verticalGradient(listOf(Color.Transparent, Color.Black.copy(alpha = 0.6f)))))
                    Column(modifier = Modifier.align(Alignment.BottomStart).padding(24.dp)) {
                        Text("Paris", color = Color.White, fontSize = 20.sp, fontWeight = FontWeight.Bold)
                        Text("France • \$530", color = Color.White.copy(alpha = 0.7f), fontSize = 14.sp)
                    }
                }
            }

            Spacer(modifier = Modifier.height(40.dp))

            // Travel Banner
            Surface(
                color = MaterialTheme.colorScheme.primary.copy(alpha = 0.05f),
                shape = RoundedCornerShape(32.dp)
            ) {
                Row(modifier = Modifier.padding(24.dp), verticalAlignment = Alignment.CenterVertically) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text("Travel with Confidence", style = MaterialTheme.typography.headlineSmall, color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Bold)
                        Spacer(modifier = Modifier.height(8.dp))
                        Text("Our Digital Concierge services provide 24/7 support for Voyager Pro members.", style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
                        Spacer(modifier = Modifier.height(16.dp))
                        OutlinedButton(onClick = { }, border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary)) {
                            Text("Learn More", color = MaterialTheme.colorScheme.primary)
                        }
                    }
                    Box(modifier = Modifier.size(80.dp).clip(CircleShape).background(surface_container_highest), contentAlignment = Alignment.Center) {
                        Icon(Icons.Filled.SupportAgent, contentDescription = null, modifier = Modifier.size(40.dp), tint = MaterialTheme.colorScheme.primary.copy(alpha = 0.2f))
                    }
                }
            }
        }
    }
}

@Composable
fun SearchField(label: String, value: String, icon: androidx.compose.ui.graphics.vector.ImageVector, onClick: () -> Unit, modifier: Modifier) {
    Column(modifier = modifier) {
        Text(label, fontSize = 10.sp, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary.copy(alpha = 0.6f), modifier = Modifier.padding(start = 16.dp, bottom = 4.dp))
        Surface(color = surface_container_lowest, shape = RoundedCornerShape(16.dp), modifier = Modifier.height(64.dp).clickable { onClick() }) {
            Row(modifier = Modifier.fillMaxSize().padding(horizontal = 16.dp), verticalAlignment = Alignment.CenterVertically) {
                Icon(icon, contentDescription = null, tint = primary_fixed_dim, modifier = Modifier.size(24.dp))
                Spacer(modifier = Modifier.width(12.dp))
                Text(value, style = MaterialTheme.typography.bodyLarge, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onSurface)
            }
        }
    }
}

@Composable
fun InfoField(value: String, icon: androidx.compose.ui.graphics.vector.ImageVector, onClick: () -> Unit, modifier: Modifier) {
    Surface(color = surface_container_lowest, shape = RoundedCornerShape(16.dp), modifier = modifier.height(64.dp).clickable { onClick() }) {
        Row(modifier = Modifier.fillMaxSize().padding(horizontal = 16.dp), verticalAlignment = Alignment.CenterVertically) {
            Icon(icon, contentDescription = null, tint = primary_fixed_dim, modifier = Modifier.size(20.dp))
            Spacer(modifier = Modifier.width(12.dp))
            Text(value, style = MaterialTheme.typography.bodyMedium, fontWeight = FontWeight.SemiBold)
        }
    }
}

@Composable
fun OfferCard(label: String, title: String, subtitle: String, code: String, icon: androidx.compose.ui.graphics.vector.ImageVector, bg: Color, textOnBg: Color, onClick: () -> Unit) {
    Surface(
        color = bg,
        shape = RoundedCornerShape(32.dp),
        modifier = Modifier.size(280.dp, 176.dp).clickable { onClick() }
    ) {
        Column(modifier = Modifier.fillMaxSize().padding(24.dp), verticalArrangement = Arrangement.SpaceBetween) {
            Column {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(icon, contentDescription = null, tint = textOnBg, modifier = Modifier.size(16.dp))
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(label, color = textOnBg, fontSize = 10.sp, fontWeight = FontWeight.Bold)
                }
                Spacer(modifier = Modifier.height(8.dp))
                Text(title, color = textOnBg, fontSize = 24.sp, fontWeight = FontWeight.Black)
                Text(subtitle, color = textOnBg.copy(alpha = 0.9f), fontSize = 14.sp)
            }
            Surface(color = Color.White.copy(alpha = 0.2f), shape = RoundedCornerShape(16.dp)) {
                Text(code, color = textOnBg, fontSize = 10.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp))
            }
        }
    }
}

@Composable
fun SmallDestCard(title: String, price: String, url: String, modifier: Modifier, onClick: () -> Unit) {
    Box(modifier = modifier.fillMaxWidth().clip(RoundedCornerShape(32.dp)).clickable { onClick() }) {
        AsyncImage(model = url, contentDescription = title, contentScale = ContentScale.Crop, modifier = Modifier.fillMaxSize())
        Box(modifier = Modifier.fillMaxSize().background(Brush.verticalGradient(listOf(Color.Transparent, Color.Black.copy(alpha = 0.6f)))))
        Column(modifier = Modifier.align(Alignment.BottomStart).padding(20.dp)) {
            Text(title, color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.Bold)
            Text(price, color = Color.White.copy(alpha = 0.7f), fontSize = 12.sp)
        }
    }
}

@Composable
fun NavBarItem(label: String, icon: androidx.compose.ui.graphics.vector.ImageVector, selected: Boolean, onClick: () -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.clickable { onClick() }
    ) {
        if (selected) {
            Surface(color = MaterialTheme.colorScheme.primaryContainer, shape = RoundedCornerShape(16.dp)) {
                Icon(icon, contentDescription = label, tint = MaterialTheme.colorScheme.primary, modifier = Modifier.padding(horizontal = 20.dp, vertical = 8.dp))
            }
        } else {
            Icon(icon, contentDescription = label, tint = MaterialTheme.colorScheme.onSurfaceVariant, modifier = Modifier.padding(horizontal = 20.dp, vertical = 8.dp))
        }
        Spacer(modifier = Modifier.height(4.dp))
        Text(label, fontSize = 10.sp, fontWeight = FontWeight.SemiBold, color = if (selected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant)
    }
}
