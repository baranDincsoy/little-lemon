package com.example.metacapstone

import androidx.compose.runtime.Composable

interface Destinations{
    val route: String
    val title: String

    object Home: Destinations{
        override val route = "home"
        override val title = "Home"
    }

    object Profile: Destinations{
        override val route = "profile"
        override val title = "Profile"
    }

    object Onboarding: Destinations {
        override val route = "onboarding"
        override val title = "Onboarding"
    }
}