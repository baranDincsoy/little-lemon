package com.example.metacapstone

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.metacapstone.ui.theme.MetaCapstoneTheme

@Composable
fun Onboarding ( navController: NavHostController){
    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }

    val context = LocalContext.current

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally){
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color(0xFFD5B50A))
                .padding(vertical = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(R.drawable.logo),
                contentDescription ="logo",
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .fillMaxSize(0.07f)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Lets get to know you",
                color = Color(0xFF495E57),
                fontSize = 20.sp,
                fontWeight = androidx.compose.ui.text.font.FontWeight.Bold
            )
        }
        OutlinedTextField( value = firstName, onValueChange = { firstName = it },  label = { Text(text = "Name")  })
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField( value = lastName, onValueChange = { lastName = it }, label = { Text(text = "Surname") })
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField( value = email, onValueChange = { email = it }, label = { Text(text = "Email") })
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = { if (firstName.isNotBlank() && lastName.isNotBlank() && email.isNotBlank()){
                context.getSharedPreferences("user_data", Context.MODE_PRIVATE).edit().apply {
                    putString("firstName", firstName)
                    putString("lastName", lastName)
                    putString("email", email)
                    apply()
                }
                Toast.makeText(context, "Registration Successful", Toast.LENGTH_SHORT).show()

                navController.navigate(Destinations.Home.route)
            }else{
                Toast.makeText(context, "Please fill all fields", Toast.LENGTH_SHORT).show()
            } },
            modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFF4CE14))
        ) {
            Text(text = "Register", color = Color.Black)
        }    }

    
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun OnboardingPreview(){
    MetaCapstoneTheme {
        Onboarding(navController = rememberNavController())
    }
}


