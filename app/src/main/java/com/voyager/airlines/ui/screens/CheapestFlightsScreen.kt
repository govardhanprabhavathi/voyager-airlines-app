package com.voyager.airlines.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
fun CheapestFlightsScreen(onNavigateHome: () -> Unit, onNavigateSearch: () -> Unit) {
    val scrollState = rememberScrollState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { 
                    Text("Offers", fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary) 
                },
                navigationIcon = {
                    IconButton(onClick = onNavigateHome) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Back", tint = MaterialTheme.colorScheme.primary)
                    }
                },
                actions = {
                    AsyncImage(
                        model = "https://lh3.googleusercontent.com/aida-public/AB6AXuDMO-VkFsepfW-eiNPNcNQW3fqqJnJm2L9yDup-aZfTa4itntr1-e8TYbWIQd5xLnluBI9UidnkSaO8A-5KZ5KE9-zc7GIDqyNTvP6A82KoNEgaKPdvus4ZBB9TC59KR-bmeZ7Po19E87stlrCH8aiRzcle5vIrST06l6-MBH80fH2s9lyyq1u4xI5c1tvO3G1n5E-85fy3xK3lVWFxTVYnWQoh7xjcR2uinRqIJeEhCIF9xuytjq5PTtqKhIugLgqR25hoMUCD76rf",
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
                    NavBarItem("Home", Icons.Filled.Home, false) { onNavigateHome() }
                    NavBarItem("Search", Icons.Filled.Search, false) { onNavigateSearch() }
                    NavBarItem("History", Icons.Filled.History, false) {}
                    NavBarItem("Offers", Icons.Filled.LocalOffer, true) {}
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

            // Hero Section
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
                    .clip(RoundedCornerShape(32.dp))
                    .background(MaterialTheme.colorScheme.primary)
            ) {
                AsyncImage(
                    model = "https://lh3.googleusercontent.com/aida-public/AB6AXuBJaZ0wheHlrlN_HJm8MxmEqtTcEvI2jG0Sh4n3OWTSWnhOCPKYgi1vyygKDrCit4sGax-gt7Ts2lDwZkzBXCHHCtB9pxmU1ApDxGn1OFxF1dtMnmozUk1H6SXrAiZmSWBAIuBswGZfndpPHFHwxOGqxPStYcOnFlmxef0LY1yxOQ1_Ls1z3JLT0M8BtWb1vameAofU9_VOcdcMlJCxXRGnv-SijpdvjBnphre6uzp4vAzxxSO1VpwgLKtiXcDys1KhVxt895r63WNo",
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize(),
                    alpha = 0.4f
                )
                Box(modifier = Modifier.fillMaxSize().background(Brush.verticalGradient(listOf(Color.Transparent, MaterialTheme.colorScheme.primary.copy(alpha = 0.8f)))))
                
                Column(
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(32.dp)
                ) {
                    Surface(color = tertiary_fixed, shape = RoundedCornerShape(8.dp)) {
                        Text(
                            "OFF-PEAK SPECIALS",
                            color = on_tertiary_fixed,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
                        )
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        "Travel more,\nspend less.",
                        color = Color.White,
                        fontSize = 40.sp,
                        fontWeight = FontWeight.ExtraBold,
                        lineHeight = 44.sp
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        "Unlock exclusive low-demand pricing and seasonal deals tailored for the modern voyager.",
                        color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.9f),
                        fontSize = 14.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Price Trends Widget
            Surface(
                color = surface_container_low,
                shape = RoundedCornerShape(24.dp)
            ) {
                Column(modifier = Modifier.padding(24.dp)) {
                    Text("Price Trends", style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.height(16.dp))
                    
                    // Simple Bar Chart
                    Row(
                        modifier = Modifier.fillMaxWidth().height(120.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalAlignment = Alignment.Bottom
                    ) {
                        ChartBar(0.6f, false, Modifier.weight(1f))
                        ChartBar(0.85f, false, Modifier.weight(1f))
                        ChartBar(0.3f, true, Modifier.weight(1f)) // Lowest
                        ChartBar(0.7f, false, Modifier.weight(1f))
                        ChartBar(0.95f, false, Modifier.weight(1f))
                        ChartBar(0.5f, false, Modifier.weight(1f))
                    }
                    
                    Row(
                        modifier = Modifier.fillMaxWidth().padding(top = 8.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        listOf("Sep", "Oct", "Nov", "Dec", "Jan", "Feb").forEachIndexed { index, month ->
                            Text(
                                text = month,
                                fontSize = 10.sp,
                                fontWeight = if (index == 2) FontWeight.Bold else FontWeight.Normal,
                                color = if (index == 2) tertiary else outline
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "Prices are currently 32% lower than the seasonal average due to low demand in November.",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(
                        onClick = { },
                        modifier = Modifier.fillMaxWidth().height(56.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = surface_container_lowest, contentColor = MaterialTheme.colorScheme.primary),
                        shape = RoundedCornerShape(16.dp)
                    ) {
                        Icon(Icons.Filled.Notifications, contentDescription = null, modifier = Modifier.padding(end = 8.dp))
                        Text("Track Prices", fontWeight = FontWeight.Bold)
                    }
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Flight Deals List
            DealCard(
                "LHR", "London, 08:45", "MLE", "Malé, 22:05",
                "11h 20m", "Direct", "SAVE 45%", "$420",
                "https://lh3.googleusercontent.com/aida-public/AB6AXuAslCP6B753H6ITFYrhgYeSxnLwoT_b70DvKyU_2qOKbSuqnDMHy6b3ngb65UMVsStPZOt7rfPIyrUvru8drSEel_GB3xB-Lh4izYcKsZA4351iaS-HFHyhx0_ESsOzp5z5bz2xpWh3X0-efmnEZLPtstoDfW4v0_GuQO9T2GNi-gGQNliti8ocwCTNCtdgKMqzGxxA9DRh83oNu2MKq-aJoAD3ku5OI0mNF1WCFVgXcLoLP9mAphTSIzZ2nxmrDtcmryRYlpiht1D8"
            )
            Spacer(modifier = Modifier.height(16.dp))
            DealCard(
                "JFK", "New York, 14:10", "NRT", "Tokyo, 18:55",
                "14h 45m", "1 Stop (HND)", "SAVE 30%", "$680",
                "https://lh3.googleusercontent.com/aida-public/AB6AXuAKnTSNXXqYTEXfc7qU6dR8218NfJ3zo7N30OI9_K0fecI01hx5Ek9d4EkoI1o0MyhAlr_vkARBvZ3tw4SblxBlV1-4wnsSe770EYoj8V8D4x0PjTEDRpxflyVkLCcZK1t4dX4STyduYAdYOVTXtvCMERz-9SLi9fTZZlGP6yOHQ80Fyrm6gQiX7ZHFy42Qg3HF1t89NAc_b7ZqJDMpvF6NnYtOv6NsJqgCX1R_LlxBwRkj0xNw0HX6BoZZeo1YxCXpBgN_qaTV07CY"
            )
            Spacer(modifier = Modifier.height(16.dp))
            DealCard(
                "CDG", "Paris, 10:20", "BCN", "Barcelona, 12:35",
                "2h 15m", "Direct", "SAVE 55%", "$45",
                "https://lh3.googleusercontent.com/aida-public/AB6AXuCq7Uz-z4XFiDHxJdJsJxNsbM0ZLCPTFAe-b5T7pzh50nfbnx88HHqV4Y_a7J-AucrpLSeGn_dts7YnqMF-kk2UlZeu5FDelGrAqY000a7EE_K9NL59Pd4lU3RGVMQisvDAGmZLX85pjvTG5F6iu_qbi5D238Pp2BK7OKtuIESoa57lJCcqVnV6q61tC6z41Oq2uwzbbJtKVLgjatU-sZa8Gl4WFaymssmk48gnmVbJg1ViBVg_LSi-dZeTFw9H1Fg4qT29AD8Q_lp1"
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Flash Sale
            Surface(
                color = Color(0xFFfe6f42),
                shape = RoundedCornerShape(32.dp)
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth().padding(32.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(Icons.Filled.LocalFireDepartment, contentDescription = null, modifier = Modifier.size(64.dp), tint = Color(0xFF631800))
                    Spacer(modifier = Modifier.height(16.dp))
                    Text("Flash Sale", color = Color(0xFF631800), fontSize = 24.sp, fontWeight = FontWeight.ExtraBold)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        "Last minute seats to Madrid starting at \$19. Ends in 04:22:15.",
                        color = Color(0xFF631800).copy(alpha = 0.9f),
                        fontSize = 14.sp,
                        textAlign = androidx.compose.ui.text.style.TextAlign.Center
                    )
                    Spacer(modifier = Modifier.height(24.dp))
                    Button(
                        onClick = {},
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF631800), contentColor = Color(0xFFfe6f42))
                    ) {
                        Text("View Deal", fontWeight = FontWeight.Bold)
                    }
                }
            }
        }
    }
}

@Composable
fun ChartBar(fillRatio: Float, isLowest: Boolean, modifier: Modifier) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight(fillRatio)
            .clip(RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp))
            .background(if (isLowest) tertiary_fixed else MaterialTheme.colorScheme.primary.copy(alpha = 0.1f))
    )
}

@Composable
fun DealCard(
    depCode: String, depCity: String, arrCode: String, arrCity: String,
    duration: String, stops: String, saveText: String, price: String, imageUrl: String
) {
    Surface(
        color = surface_container_lowest,
        shape = RoundedCornerShape(24.dp),
        shadowElevation = 4.dp
    ) {
        Column(modifier = Modifier.padding(24.dp)) {
            Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                AsyncImage(
                    model = imageUrl,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.size(80.dp).clip(RoundedCornerShape(16.dp))
                )
                Spacer(modifier = Modifier.width(16.dp))
                
                Row(modifier = Modifier.weight(1f), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                    Column {
                        Text("DEPARTURE", fontSize = 10.sp, color = outline, fontWeight = FontWeight.Bold)
                        Text(depCode, fontSize = 24.sp, fontWeight = FontWeight.ExtraBold)
                        Text(depCity, fontSize = 12.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
                    }
                    
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(duration, fontSize = 10.sp, color = tertiary, fontWeight = FontWeight.Bold)
                        Icon(Icons.Filled.FlightTakeoff, contentDescription = null, tint = MaterialTheme.colorScheme.primary)
                        Text(stops, fontSize = 10.sp, color = outline)
                    }
                    
                    Column(horizontalAlignment = Alignment.End) {
                        Text("ARRIVAL", fontSize = 10.sp, color = outline, fontWeight = FontWeight.Bold)
                        Text(arrCode, fontSize = 24.sp, fontWeight = FontWeight.ExtraBold)
                        Text(arrCity, fontSize = 12.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
                    }
                }
            }
            
            Spacer(modifier = Modifier.height(24.dp))
            
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                Surface(color = tertiary_container.copy(alpha = 0.1f), shape = RoundedCornerShape(8.dp)) {
                    Text(saveText, color = tertiary, fontSize = 10.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp))
                }
                
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(price, fontSize = 32.sp, fontWeight = FontWeight.Black, color = tertiary)
                    Spacer(modifier = Modifier.width(16.dp))
                    Button(
                        onClick = {},
                        shape = RoundedCornerShape(12.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
                    ) {
                        Text("Book Now", fontWeight = FontWeight.Bold)
                    }
                }
            }
        }
    }
}
