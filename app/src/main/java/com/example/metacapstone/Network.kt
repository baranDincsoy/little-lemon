package com.example.metacapstone

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
//@SerialName("MenuItem")
data class MenuItemNetwork(
    val id: Int,
    val title: String,
    val description: String,
    val price: String,
    val image: String,
    val category: String
)

@Serializable
data class MenuNetwork(
    val menu: List<MenuItemNetwork>
)

