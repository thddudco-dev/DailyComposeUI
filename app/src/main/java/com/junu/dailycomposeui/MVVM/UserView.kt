package com.junu.dailycomposeui.MVVM

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.junu.dailycomposeui.ui.theme.DailyComposeUITheme
import java.util.*

class UserView : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val vm = UserViewModel()

        setContent {
            DailyComposeUITheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    UserView(vm = vm)
                }
            }
        }
    }
}

@Composable
fun UserView(vm: UserViewModel) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("User") },
                actions = {
                    IconButton(onClick = { vm.addUser(User(UUID.randomUUID(), "User")) }) {
                        Icon(Icons.Filled.Add, null)
                    }
                }
            )
        }
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            LazyColumn(modifier = Modifier.fillMaxHeight()) {
                items(vm.users) {
                    Column {
                        Text(it.name)
                        Text("${it.id}")
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview2() {
    DailyComposeUITheme {
        UserView(UserViewModel())
    }
}