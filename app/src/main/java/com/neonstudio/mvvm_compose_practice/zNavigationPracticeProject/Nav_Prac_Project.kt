package com.neonstudio.mvvm_compose_practice.zNavigationPracticeProject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.neonstudio.mvvm_compose_practice.ComposeScreenDesigns.DetailScreen
import com.neonstudio.mvvm_compose_practice.ui.theme.MVVM_Compose_PracticeTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class Nav_Prac_Project : ComponentActivity() {

//    @Inject
//    lateinit var tweetsyAPI: TweetsyAPI

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        GlobalScope.launch {
//            var response = tweetsyAPI.getCategories()
//            Log.d("CHEEXYCODE",response.body()!! .distinct().toString())
////            var result = response.body()
////            result.distinct()
//        }


        enableEdgeToEdge()
        setContent {
            MVVM_Compose_PracticeTheme {
//                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

                App()


            }
        }
    }
}


@Composable
fun App() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "registration") {
        composable(route = "registration") {
            RegistrationScreen {
                navController.navigate(
                    "main/${it}"
                )
            }
        }
        composable(route = "login") {
            LoginScreen()
        }
        composable(
            route = "main/{email}", arguments = listOf(
                navArgument("email") {
                    type = NavType.StringType
                }
            )) {
            val email = it.arguments!!.getString("email")
            MainScreen(email!!)
        }
    }
}

@Composable
fun RegistrationScreen(onClick: (email: String) -> Unit) {
    Text(text = "Registration",
        style = MaterialTheme.typography.bodyLarge.copy(fontSize = 70.sp),
        modifier = Modifier.clickable {
            onClick(
                "cheezycake@gmail.com"
            )
        }
    )
}


@Composable
fun LoginScreen() {
    Text(text = "Login", style = MaterialTheme.typography.bodyLarge.copy(fontSize = 70.sp))
}

@Composable
fun MainScreen(email: String) {
    Text(text = "Main Screen - $email", style = MaterialTheme.typography.bodyLarge.copy(fontSize = 70.sp)

    )
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MVVM_Compose_PracticeTheme {
        App()
    }
}