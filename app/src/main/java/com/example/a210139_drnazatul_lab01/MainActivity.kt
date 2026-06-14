package com.example.a210139_drnazatul_lab01

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.* // basic layout tools (calls all)
import androidx.compose.foundation.layout.* // sizing and spacing tools (dp, fillMaxSize)
import androidx.compose.foundation.lazy.LazyRow // horizontal scrollable lists
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.* // 3 design elements (cards, navigation bars, texts)
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.a210139_drnazatul_lab01.ui.theme.A210139_DrNazatul_Lab01Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            A210139_DrNazatul_Lab01Theme {
                val mainBackground = Brush.verticalGradient(colors = listOf(Color(0xFFFFE0E9), Color(0xFFE0F7FA)))
                Scaffold( // for header and bottom bar
                    bottomBar = { ResQBiteBottomNavigation() }
                ) { innerPadding ->
                    Box(modifier = Modifier
                        .fillMaxSize()
                        .background(mainBackground)
                        .padding(innerPadding)
                    ) {
                        ResQBiteHomeScreen()
                    }
                }
            }
        }
    }
}

// layout section
@Composable
fun ResQBiteHomeScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll( state = rememberScrollState()) // make it scrollable up and down
    ) {
        // 1. header section
        Surface(
            color = Color(0xFFFFC1D1).copy(alpha = 0.5f),
            modifier = Modifier.fillMaxWidth()
        ) {
            Row( // horizontal (side by side)
                modifier = Modifier
                    .padding(16.dp) // make the content has space between the edges
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Image( // ResQBite logo
                            painter = painterResource(id = R.drawable.resqlogo),
                            contentDescription = "logo",
                            modifier = Modifier.size(50.dp)
                        )

                        Text(text = "ResQBite", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color(0xFF4A148C))
                    }

                    Spacer(modifier = Modifier.width(5.dp))

                    Column {
                        Text(text = "Listing within 25km", fontSize = 14.sp, color = Color.Gray)
                        Text(text = "📍 Bangi ⌄", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                    }
                }

                Row {
                    Icon(Icons.Default.Notifications, contentDescription = "Notifications")
                    Spacer(modifier = Modifier.width(16.dp))
                    Text("☰", fontSize = 24.sp)
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp)) // spaces between the header and the next section

        Column(modifier = Modifier.padding(16.dp)) {
            // 2. top filter section
            val categories = listOf("All", "Free Food", "Discounted Food", "Convenience Stores", "Students", "Events")

            LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                items(categories) { name ->
                    CategoryChip(name, isSelected = (name == "All"))
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // 3. 'Take or Give' section
            Text(text = "Take or Give", fontSize = 20.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(12.dp))
            LazyRow(horizontalArrangement = Arrangement.spacedBy(16.dp)) { // horizontal scrollable with the space of 16 pixels between each box
                item {
                    TakeGiveCard(
                        text = "Expiring soon, grab now!!",
                        emoji = "⏰🔥",
                        bgColor = Color(0xFFFFF9C4) // light yellow
                    )
                }
                item {
                    TakeGiveCard(
                        text = "Wait... I can give that away?",
                        emoji = "👀👀",
                        bgColor = Color(0xFFFFF3E0) // light orange
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // 4. 'Suggested Food For You' section
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween, // the space between the section title and 'All>'
                verticalAlignment = Alignment.Bottom
            ) {
                Text(text = "Suggested Food For You", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                Text(text = "All >", color = Color.Gray)
            }

            Spacer(modifier = Modifier.height(12.dp))

            LazyRow(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                item {
                    FoodCard(
                        title = "Extra Croissants",
                        distance = "1.2km",
                        user = "NearBakery",
                        imageRes = R.drawable.croissant
                    )
                }
                item {
                    FoodCard(
                        title = "Tin tuna",
                        distance = "0.8km",
                        user = "Mira",
                        imageRes = R.drawable.canned_tuna
                    )
                }
                item {
                    FoodCard(
                        title = "Roti Gardenia Pandan Gula Melaka",
                        distance = "1.0km",
                        user = "Amar",
                        imageRes = R.drawable.roti
                    )
                }
                item {
                    FoodCard(
                        title = "Nasi Ayam",
                        distance = "0.3km",
                        user = "Qila",
                        imageRes = R.drawable.nasiayam
                    )
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            // 5. 'Events Rescue Pile' section
            Text(text = "Events Rescue Pile", fontSize = 20.sp, fontWeight = FontWeight.Bold)

            Spacer(modifier = Modifier.height(12.dp))

            EventRescueCard(
                eventName = "EduTalk",
                location = "DECTAR",
                foodItem = "Packed Nasi Ayam Merah",
                quantity = "17 packages remaining"
            )

            Spacer(modifier = Modifier.height(16.dp))

            EventRescueCard(
                eventName = "Charity Run 2026",
                location = "Panggung Seni",
                foodItem = "Banana & Isotonic Drinks",
                quantity = "30 items available"
            )
        }
    }
}

// design section
@Composable
fun EventRescueCard(eventName: String, location: String, foodItem: String, quantity: String) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF1F8E9)), // light green
        border = BorderStroke(2.dp, Color(0xFFC5E1A5)) // outline border
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = eventName, fontSize = 22.sp, fontWeight = FontWeight.ExtraBold, color = Color(0xFF2E7D32)) // title of the box (dark green)
            Text(text = "📍 $location", fontSize = 16.sp, fontWeight = FontWeight.Medium)
            HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp), color = Color.LightGray) // line that separates title and description
            Text(text = "Food: $foodItem", fontSize = 16.sp)
            Text(text = "Quantity: $quantity", fontSize = 14.sp, fontWeight = FontWeight.Bold, color = Color.Red)
        }
    }
}

@Composable
fun TakeGiveCard(text: String, emoji: String, bgColor: Color) {
    Card(
        modifier = Modifier.size(width = 150.dp, height = 150.dp), // size of the box
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = bgColor),
        border = BorderStroke(1.dp, Color.LightGray),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize().padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = emoji, fontSize = 30.sp)
            Spacer(modifier = Modifier.height(12.dp)) // space between emojis and texts
            Text(text = text, fontSize = 16.sp, fontWeight = FontWeight.Medium, color = Color.DarkGray)
        }
    }
}

@Composable
fun FoodCard(title: String, distance: String, user: String, imageRes: Int) {
    Card(
        modifier = Modifier.width(180.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White), // white background
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp) // shadow below the box
    ) {
        Column {
            Box {
                Image(
                    painter = painterResource(id = imageRes),
                    contentDescription = title,
                    modifier = Modifier.fillMaxWidth().height(120.dp),
                    contentScale = ContentScale.Crop
                )
                Surface( // little box for the distance text
                    color = Color.White.copy(alpha = 0.9f), // white with slight transparency
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier.align(Alignment.BottomEnd).padding(4.dp)
                ) {
                    Text(text = "📍 $distance", fontSize = 10.sp, modifier = Modifier.padding(4.dp))
                }
            }
            Column(modifier = Modifier.padding(8.dp)) { // texts section under the image
                Text(text = title, fontWeight = FontWeight.Medium)
                Spacer(modifier = Modifier.height(4.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(modifier = Modifier.size(18.dp).background(Color.LightGray, RoundedCornerShape(9.dp))) // user profile icon
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(text = user, fontSize = 12.sp, color = Color.Gray)
                }
            }
        }
    }
}

@Composable
fun CategoryChip(label: String, isSelected: Boolean) {
    Surface(
        shape = RoundedCornerShape(20.dp),
        border = BorderStroke(1.dp, if (isSelected) Color.Transparent else Color.LightGray),
        color = if (isSelected) Color(0xFF4A148C) else Color.White // dark purple for the selected button
    ) {
        Text(
            text = label,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
            color = if (isSelected) Color.White else Color.Black,
            fontSize = 14.sp
        )
    }
}

@Composable
fun ResQBiteBottomNavigation() {
    NavigationBar(containerColor = Color.White) {
        NavigationBarItem( // home button
            icon = { Icon(Icons.Default.Home, contentDescription = null) },
            label = { Text("Home") },
            selected = true,
            onClick = {}
        )
        NavigationBarItem( // search button
            icon = { Icon(Icons.Default.Search, contentDescription = null) },
            label = { Text("Search") },
            selected = false,
            onClick = {}
        )
        NavigationBarItem( // add button
            icon = {
                Surface(shape = RoundedCornerShape(20.dp), color = Color(0xFF4A148C), modifier = Modifier.size(40.dp)) { // round dark purple circle
                    Icon(Icons.Default.Add, contentDescription = null, tint = Color.White, modifier = Modifier.padding(8.dp))
                }
            },
            label = { Text("Add") },
            selected = false,
            onClick = {}
        )
        NavigationBarItem( // community button
            icon = { Icon(Icons.Default.Person, contentDescription = null) },
            label = { Text("Community") },
            selected = false,
            onClick = {}
        )
        NavigationBarItem( // messages button
            icon = { Icon(Icons.Default.Email, contentDescription = null) },
            label = { Text("Messages") },
            selected = false,
            onClick = {}
        )
    }
}
 user.email
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    A210139_DrNazatul_Lab01Theme {
        Scaffold(bottomBar = { ResQBiteBottomNavigation() }) { p ->
            Box(modifier = Modifier.padding(p)) { ResQBiteHomeScreen() }
        }
    }
}