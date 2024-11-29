package com.neonstudio.mvvm_compose_practice

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ListItemDefaults.contentColor
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.neonstudio.mvvm_compose_practice.ComposeScreenDesigns.CategoriesScreen
import com.neonstudio.mvvm_compose_practice.ComposeScreenDesigns.DetailScreen
import com.neonstudio.mvvm_compose_practice.ui.theme.MVVM_Compose_PracticeTheme
import com.neonstudio.mvvm_compose_practice.zNavigationPracticeProject.LoginScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

//    @Inject
//    lateinit var tweetsyAPI: TweetsyAPI

    @OptIn(ExperimentalMaterial3Api::class)
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
                Scaffold(
                    topBar = {
                        TopAppBar(

                            title = { Text(text = "Tweetsy") },
                            colors = TopAppBarDefaults.topAppBarColors(
                                containerColor = Color.Blue,  // Background color
                                titleContentColor = Color.White // Title text color
                            )
                        )
                    }
                ) {
                    Box(modifier = Modifier.padding(it)) {
                        App()
                    }
                }
            }
        }
    }
}

@Composable
fun App(modifier: Modifier = Modifier) {

    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "category") {
        composable(route = "category") {
            CategoriesScreen {
                navController.navigate("detail/$it")
            }
        }

        composable(route = "detail/{category}",
            arguments = listOf(
                navArgument("category") {
                    type = NavType.StringType
                }
            )
        ) {
            DetailScreen()
        }
    }

}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MVVM_Compose_PracticeTheme {

        App()
    }
}