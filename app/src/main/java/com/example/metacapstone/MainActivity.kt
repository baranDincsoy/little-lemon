package com.example.metacapstone

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.autofill.ContentType
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import com.example.metacapstone.ui.theme.MetaCapstoneTheme
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.text.category

class MainActivity : ComponentActivity() {
    private val httpClient = HttpClient(Android) {
        install(ContentNegotiation) {

            json(contentType = io.ktor.http.ContentType("text", "plain"))
        }
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val database = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "database"
        ).build()

        // 2. Arka plan işlemleri (Coroutine) başlasın!
        lifecycleScope.launch(Dispatchers.IO) {
            // A. Veriyi internetten çek:
            val networkMenu = fetchMenu()

            val menuItemsRoom = networkMenu.menu.map { networkItem ->
                MenuItemRoom(
                    id = networkItem.id,
                    title = networkItem.title,
                    description = networkItem.description,
                    price = networkItem.price,
                    image = networkItem.image,
                    category = networkItem.category
                )
            }

            database.menuItemDao().insertAll(*menuItemsRoom.toTypedArray())
        }

        setContent {
            Navigation( navController = rememberNavController(),
                database = database
            )
        }

    }

    private suspend fun fetchMenu(): MenuNetwork {
        return httpClient.get("https://raw.githubusercontent.com/Meta-Mobile-Developer-PC/Working-With-Data-API/main/menu.json").body<MenuNetwork>()
    }


}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MetaCapstoneTheme {
        Greeting("Android")
    }
}