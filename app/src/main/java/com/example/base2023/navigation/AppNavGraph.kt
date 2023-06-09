package com.example.base2023.navigation

import androidx.compose.material3.BottomSheetScaffoldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SheetState
import androidx.compose.material3.SheetValue
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.base2023.presentation.common.BottomSheetWrapper
import com.example.base2023.presentation.common.ErrorHandlerWrapper
import com.example.base2023.presentation.common.SnackbarWrapper
import com.example.base2023.presentation.home.HomeEvent
import com.example.base2023.presentation.home.HomeScreen
import com.example.base2023.presentation.home.HomeViewModel
import com.example.base2023.presentation.login.LoginScreen
import com.example.base2023.presentation.login.LoginViewModel
import com.example.base2023.presentation.profile.ProfileScreen
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppNavGraph(
    appViewModel: AppViewModel = hiltViewModel(),
    navController: NavHostController = rememberNavController(),
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    startDestination: String = AppDestinations.LOGIN_ROUTE,
    snackbarHostState: SnackbarHostState = remember { SnackbarHostState() },
    scaffoldState: BottomSheetScaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = SheetState(skipPartiallyExpanded = true,
            skipHiddenState = false,
            initialValue = SheetValue.Hidden,
            confirmValueChange = { it != SheetValue.PartiallyExpanded })
    ),
    navActions: AppNavigationActions = remember(navController) {
        AppNavigationActions(navController)
    },
    tabItems: List<BottomBarItem> = remember {
        listOf(BottomBarItem.Home(), BottomBarItem.Profile())
    }
) {

    val sheetContent: MutableState<@Composable () -> Unit> = remember {
        mutableStateOf(@Composable {})
    }

    val onBottomBarItemClick: (BottomBarItem) -> Unit = {
        when (it) {
            is BottomBarItem.Home -> navActions.navigateToHome()
            is BottomBarItem.Profile -> navActions.navigateToProfile()
        }
    }

    val expandBottomSheet: (@Composable () -> Unit) -> Unit = {
        sheetContent.value = it
        coroutineScope.launch { scaffoldState.bottomSheetState.expand() }
    }

    val hideBottomSheet: () -> Unit = {
        coroutineScope.launch { scaffoldState.bottomSheetState.hide() }
    }

    val showSnackbar: (msg: String) -> Unit = {
        coroutineScope.launch {
            snackbarHostState.showSnackbar(it)
        }
    }

    val logout: () -> Unit = {
        navActions.navigateToLogin()
    }

    LaunchedEffect(key1 = Unit) {
        appViewModel.uiEvent.collect() { event ->
            when (event) {
                is AppUiEvent.OnLogout -> {
                    navActions.navigateToLogin()
                }

                is AppUiEvent.OnLoginAgain -> {
                    navActions.navigateToLogin()
                }
            }
        }
    }

    SnackbarWrapper(snackbarHostState = snackbarHostState) {
        NavHost(
            navController = navController, startDestination = startDestination
        ) {
            composable(AppDestinations.LOGIN_ROUTE) {
                val viewModel = hiltViewModel<LoginViewModel>()
                // Toast
                LoginScreen(
                    onLoginSuccess = { navActions.navigateFromLoginToHome() }, viewModel = viewModel
                )
            }
            composable(AppDestinations.HOME_ROUTE) {
                val viewModel = hiltViewModel<HomeViewModel>()
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination
                val uiState by viewModel.uiState.collectAsStateWithLifecycle()
                BottomSheetWrapper(
                    scaffoldState = scaffoldState,
                    onDismissRequest = hideBottomSheet,
                    sheetContent = sheetContent.value
                ) {
                    ErrorHandlerWrapper(throwable = uiState.throwable,
                        onCloseErrorDialog = { viewModel.onEvent(HomeEvent.ClearError) },
                        onCloseErrorDialog401 = { appViewModel.onEvent(AppEvent.CloseDialog401) },
                        onCloseErrorDialog408 = { appViewModel.onEvent(AppEvent.CloseDialog408) }) {
                        HomeScreen(
                            viewModel = viewModel,
                            currentDestination = currentDestination,
                            tabItems = tabItems,
                            onBottomBarItemClick = onBottomBarItemClick,
                            onBottomSheetClick = expandBottomSheet,
                            onDismissBottomSheet = hideBottomSheet,
                            onSnackbarClick = showSnackbar,
                            onLogoutClick = logout
                        )
                    }
                }
            }
            composable(AppDestinations.PROFILE_ROUTE) {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination
                ProfileScreen(
                    currentDestination = currentDestination,
                    tabItems = tabItems,
                    onBottomBarItemClick = onBottomBarItemClick
                )
            }
        }
    }
}