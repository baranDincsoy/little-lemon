package com.example.metacapstone

import android.R.attr.button
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Home(navController: NavHostController, db: AppDatabase) {

    var selectedCategory by remember { mutableStateOf("All") }

    var searchPhrase by remember { mutableStateOf("") }


    val databaseMenuItems by db.menuItemDao().getAll().observeAsState(emptyList())


    val filteredItems = if (searchPhrase.isEmpty() && selectedCategory == "All") {
        databaseMenuItems
    } else {
        databaseMenuItems.filter { it.title.contains(searchPhrase, ignoreCase = true) && (selectedCategory == "All" || it.category == selectedCategory) }
    }


    Column(modifier = Modifier.fillMaxSize()) {

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp, vertical = 20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(R.drawable.logo),
                contentDescription = "Logo",
                modifier = Modifier.width(180.dp).height(40.dp)
            )
            Image(
                painter = painterResource(R.drawable.profile),
                contentDescription = "Profile",
                modifier = Modifier
                    .size(50.dp)
                    .clip(RoundedCornerShape(50))
                    .clickable { navController.navigate(Destinations.Profile.route) }
            )
        }


        Column(
            modifier = Modifier
                .background(Color(0xFF495E57))
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = "Little Lemon",
                fontSize = 40.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFFF4CE14)
            )
            Text(
                text = "Chicago",
                fontSize = 24.sp,
                color = Color.White
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 18.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "We are a family-owned Mediterranean restaurant, focused on traditional recipes served with a modern twist.",
                    color = Color.White,
                    fontSize = 16.sp,
                    modifier = Modifier.weight(0.6f).padding(end = 8.dp)
                )
                Image(
                    painter = painterResource(R.drawable.heroimage),
                    contentDescription = "Hero",
                    modifier = Modifier.size(120.dp).clip(RoundedCornerShape(16.dp)),
                    contentScale = ContentScale.Crop
                )
            }


            TextField(
                value = searchPhrase,
                onValueChange = { searchPhrase = it },
                placeholder = { Text("Enter search phrase") },
                leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
                    .clip(RoundedCornerShape(8.dp)),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color(0xFFEDEFEE),
                    unfocusedContainerColor = Color(0xFFEDEFEE),
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                )
            )
        }


        Text(
            text = "ORDER FOR DELIVERY!",
            modifier = Modifier.padding(start = 16.dp, top = 20.dp, bottom = 10.dp),
            fontWeight = FontWeight.ExtraBold,
            fontSize = 18.sp
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Button(
                onClick = {

                    selectedCategory = if (selectedCategory == "starters") "All" else "starters"
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (selectedCategory == "starters") Color(0xFF495E57) else Color(0xFFEDEFEE),
                    contentColor = if (selectedCategory == "starters") Color.White else Color(0xFF495E57)
                ),
                modifier = Modifier.height(40.dp)
            ) {
                Text(text = "Starters", fontWeight = FontWeight.Bold)
            }


            Button(
                onClick = {
                    selectedCategory = if (selectedCategory == "mains") "All" else "mains"
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (selectedCategory == "mains") Color(0xFF495E57) else Color(0xFFEDEFEE),
                    contentColor = if (selectedCategory == "mains") Color.White else Color(0xFF495E57)
                ),
                modifier = Modifier.height(40.dp)
            ) {
                Text(text = "Mains", fontWeight = FontWeight.Bold)
            }


            Button(
                onClick = {
                    selectedCategory = if (selectedCategory == "desserts") "All" else "desserts"
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (selectedCategory == "desserts") Color(0xFF495E57) else Color(0xFFEDEFEE),
                    contentColor = if (selectedCategory == "desserts") Color.White else Color(0xFF495E57)
                ),
                modifier = Modifier.height(40.dp)
            ) {
                Text(text = "Desserts", fontWeight = FontWeight.Bold)
            }


            Button(
                onClick = {
                    selectedCategory = if (selectedCategory == "drinks") "All" else "drinks"
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (selectedCategory == "drinks") Color(0xFF495E57) else Color(0xFFEDEFEE),
                    contentColor = if (selectedCategory == "drinks") Color.White else Color(0xFF495E57)
                ),
                modifier = Modifier.height(40.dp)
            ) {
                Text(text = "Drinks", fontWeight = FontWeight.Bold)
            }
        }

        LazyColumn(modifier = Modifier.fillMaxWidth()) {
            items(filteredItems) { menuItem ->
                MenuItem(
                    title = menuItem.title,
                    description = menuItem.description,
                    price = menuItem.price,
                    image = menuItem.image
                )
            }
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun MenuItem(title: String, description: String, price: String, image: String) {
    Column(modifier = Modifier.padding(horizontal = 16.dp)) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(text = title, fontWeight = FontWeight.Bold, style = MaterialTheme.typography.titleMedium)
                Text(
                    text = description,
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Gray,
                    modifier = Modifier.padding(vertical = 5.dp),
                    maxLines = 2
                )
                Text(text = "$$price", fontWeight = FontWeight.SemiBold)
            }


            Box(modifier = Modifier.padding(start = 8.dp).size(90.dp)) {
                GlideImage(
                    model = image,
                    contentDescription = title,
                    modifier = Modifier.matchParentSize().clip(RoundedCornerShape(8.dp)),
                    contentScale = ContentScale.Crop
                )
            }
        }
        Divider(thickness = 0.5.dp, color = Color.LightGray)
    }
}