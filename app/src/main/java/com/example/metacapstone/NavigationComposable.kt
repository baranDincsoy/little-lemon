package com.example.metacapstone

import Profile
import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun Navigation(navController: NavHostController, database: AppDatabase) {
    val  context = LocalContext.current
    val sharedPreferences = context.getSharedPreferences("user_data", Context.MODE_PRIVATE)


    NavHost(navController = navController, startDestination = sharedPreferences.getString("email", null)?.let { Destinations.Home.route } ?: Destinations.Onboarding.route){
        composable(Destinations.Onboarding.route){
            Onboarding( navController = navController)
        }
        composable(Destinations.Home.route){
            Home( navController = navController, db = database)
        }
        composable(Destinations.Profile.route){
            Profile( navController = navController)
        }

    }
}