package com.example.thithu2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.thithu2.ui.theme.Thithu2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
          MeoScreen()
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}
@Composable
fun MeoScreen() {
    val showDialog = remember { mutableStateOf(false) }
    val viewModel: MeoViewModel = viewModel()
    val selectedMeo by viewModel.selectedMeo.observeAsState()
    val meoList by viewModel.meo.observeAsState(emptyList())
    Column {
        meoList.forEach { meo ->
            ItemMeo(meo = meo) {
                viewModel.getMeoById(meo.id)
                showDialog.value = true

            }
        }
        DialogMeo(show = showDialog.value, onDismiss = { showDialog.value = false } , meo = selectedMeo )
    }


}

@Composable
fun ItemMeo(meo: Meo, onClick: () -> Unit) {
    Card(modifier = Modifier.fillMaxWidth().padding(10.dp).clickable { onClick() }) {
        Row  () {
            AsyncImage(model = meo.hinhanh, contentDescription = null, modifier = Modifier.size(100.dp))
            Column() {
                Text(text = meo.ten)
                Text(text = meo.giong)
                Text(text = meo.tuoi.toString())
                Text(text = meo.moTa)
            }
        }
    }
}
@Composable
fun DialogMeo(show: Boolean, onDismiss: () -> Unit, meo: Meo?) {
    if (show) {
        Dialog(onDismissRequest = onDismiss) {
            Card {
                Column {
                    AsyncImage(model = meo?.hinhanh, contentDescription = null, modifier = Modifier.size(100.dp))
                    Text("Tên: ${meo?.ten}")
                    Text("Giong: ${meo?.giong}")
                    Text("Tuoi: ${meo?.tuoi}")
                    Text("MoTa: ${meo?.moTa}")

                    Button(onClick = onDismiss) {
                        Text("Đóng")
                    }

                }
            }
        }
    }
}
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Thithu2Theme {
        Greeting("Android")
    }
}