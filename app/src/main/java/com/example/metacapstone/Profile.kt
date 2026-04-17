import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.metacapstone.Destinations
import com.example.metacapstone.R

@Composable
fun Profile(navController: NavHostController) {

    val context = LocalContext.current
    val sharedPreferences = context.getSharedPreferences("user_data", Context.MODE_PRIVATE)

    val firstName = sharedPreferences.getString("firstName", "") ?: ""
    val lastName = sharedPreferences.getString("lastName", "") ?: ""
    val email = sharedPreferences.getString("email", "") ?: ""

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(R.drawable.logo),
            contentDescription ="logo",
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .fillMaxSize(0.07f)
        )
        Spacer(modifier = Modifier.height(150.dp))
        Text(
            text = "Profile information:",
            color = Color(0xFF495E57),
            fontSize = 20.sp,
            fontWeight = androidx.compose.ui.text.font.FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "First name")
        OutlinedTextField(value = firstName, onValueChange = {}, readOnly = true)
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Lastname: ")
        OutlinedTextField(value = lastName, onValueChange = {}, readOnly = true)
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Email:")
        OutlinedTextField(value = email, onValueChange = {}, readOnly = true)
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {navController.navigate(Destinations.Onboarding.route)} ) {
            Text(text = "Logout")
        }


    }
}