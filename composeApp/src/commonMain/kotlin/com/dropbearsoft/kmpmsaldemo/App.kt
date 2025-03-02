package com.dropbearsoft.kmpmsaldemo

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import com.dropbearsoft.kmpmsaldemo.auth.MsalAuth
import com.dropbearsoft.kmpmsaldemo.student.StudentId
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.koinInject

@Composable
@Preview
fun App() {

    val insets = WindowInsets.systemBars // Access system bars insets
    val topPadding = with(LocalDensity.current) { insets.getTop(this).toDp() }
    val bottomPadding = with(LocalDensity.current) { insets.getBottom(this).toDp() }
    var claimsJson by remember { mutableStateOf("") }

    val msalAuth = koinInject<MsalAuth>()
    val studentIdService = koinInject<StudentId>()
    var token by remember { mutableStateOf("") }

    MaterialTheme {
        var showContent by remember { mutableStateOf(false) }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = topPadding, bottom = bottomPadding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(onClick = {
                CoroutineScope(Dispatchers.Main).launch {
                    try {
                        token = msalAuth.getToken()
                    } catch (e: Exception) {
                        println(e.message)
                    }

                    val studentId = studentIdService.getStudentId(token)

                    println("StudentID: $studentId")

                }
            }) {
                Text("Login")
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(claimsJson)
        }
    }
}