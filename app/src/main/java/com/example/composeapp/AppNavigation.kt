import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.composeapp.core.composables.LocalNavHostControllerProvider
import com.example.composeapp.features.home.presentation.pages.HomePage
import com.example.composeapp.features.home.presentation.viewmodel.HomeViewModel

@Composable
fun AppNavigation(
) {
    val navController = rememberNavController()
    LocalNavHostControllerProvider(
        navHostController = navController,
    ) {
        NavHost(navController = navController, startDestination = AppRoutes.home) {
            composable(AppRoutes.home) {
                HomePage(
                    viewModel = HomeViewModel()
                )
            }
        }
    }
}


object AppRoutes {
    const val home = "/home"
}