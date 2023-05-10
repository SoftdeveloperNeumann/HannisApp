package de.softdeveloper.hannisapp

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import de.softdeveloper.hannisapp.Values.text
import de.softdeveloper.hannisapp.ui.theme.HannisAppTheme
import de.softdeveloper.hannisapp.ui.theme.Purple40
import de.softdeveloper.hannisapp.ui.theme.Purple80


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var app = (application as MyApplication)
        app.counter = 10
        setContent {
            HannisAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    FullScreen()
                }
            }
        }
    }
}

object Values : ViewModel() {
    var text by mutableStateOf("")
}

@Composable
fun NumBlock() {
    var count = 0
    Column(
        modifier = Modifier
            .wrapContentSize()
            .border(1.dp, Purple80, RoundedCornerShape(8.dp)),
    ) {
        repeat(2) {
            Row(
                modifier = Modifier
                    .wrapContentSize()
                    .padding(horizontal = 4.dp),
            ) {
                repeat(5) {
                    LetterButton(value = count.toString())
                    count++
                }
            }
        }
    }
}

@Composable
fun TextBlock() {
    val alphabet = ('a'..'z').toList()
    Column(
        Modifier
            .wrapContentSize()
            .border(1.dp, Purple80,RoundedCornerShape(8.dp)),
    ) {
        for (i in 0 until alphabet.size step 5) {
            Row(
                Modifier.padding(horizontal = 4.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                for (j in i until i + 5) {
                    if (j < alphabet.size) {
                        LetterButton(value = alphabet[j].toString())
                    }
                }
            }
        }
    }
}

@Composable
fun LetterButton(value: String) {
    val t by remember {
        mutableStateOf(value)
    }
    Button(
        onClick = {
            text = text.plus(t)
            Log.d("TAG", "LetterButton: $text")
        },
        colors = ButtonDefaults.buttonColors(containerColor = Purple80),
        modifier = Modifier
            .width(70.dp)
            .padding(all = 4.dp)
            .background(Purple80, RoundedCornerShape(32.dp))
    ) {
        Text(
            text = t,
            fontFamily = FontFamily(Font(R.font.handsign)),
            fontSize = 48.sp,
            fontWeight = FontWeight.ExtraBold,
            color = Color.Black,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun ImageView() {
    // Beschaffung des Context, je nach Anforderung
    val application = (LocalContext.current as MainActivity).application as MyApplication
    LazyColumn(
        Modifier
            .padding(start = 4.dp, top = 4.dp)
            .fillMaxWidth(1f)
    ) {
        items(application.array.size) { col ->
            LazyRow(Modifier.padding(end = 4.dp, bottom = 4.dp)) {
                items(application.array[col].size) { row ->

                    Image(
                        painter = painterResource(
                            id = application.array[col][row] ?: R.drawable.katze2
                        ),
                        contentDescription = "",
                        Modifier
                            .width(120.dp)
                            .clickable {
                                Toast
                                    .makeText(application, "Hallo Welt!", Toast.LENGTH_SHORT)
                                    .show()
                                Log.d("TAG", "MainView: Hallo Welt!")
                            })
                    Spacer(modifier = Modifier.width(4.dp))
                }


            }
        }

    }

}

@Composable
fun TextOut() {
    OutlinedTextField(
        value = text,
        onValueChange = { text = it },
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .padding(horizontal = 8.dp)
            .fillMaxWidth(),
        textStyle = TextStyle.Default.copy(
            fontFamily = FontFamily(Font(R.font.handsign)),
            fontSize = 36.sp,
            fontWeight = FontWeight.ExtraBold
        ),
        label = { Text(text = "Was kann ich für dich tun?") }
    )
}

@Composable
fun MainView() {
    Column(
        Modifier
            .fillMaxSize()
            .padding(top = 64.dp, bottom = 4.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        NumBlock()
        Spacer(modifier = Modifier.height(8.dp))
        TextBlock()
        Spacer(modifier = Modifier.height(8.dp))
        TextOut()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FullScreen(){
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Hannis App",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        color = Color.White
                    )
                },
                colors = topAppBarColors(
        containerColor = Purple40,
        ),
                navigationIcon = {
                    IconButton(onClick = { /* doSomething() */ }) {
                        Icon(
                            imageVector = Icons.Filled.Menu,
                            contentDescription = "Localized description",
                            tint = Color.White
                        )
                    }
                },
                actions = {
                    IconButton(onClick = {

                    }) {
                        Icon(
                            imageVector = Icons.Filled.Delete,
                            contentDescription = "Localized description",
                            tint = Color.White
                        )
                    }
                }
            )
        },
        ){
        it.calculateBottomPadding()
        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(all = 8.dp)
        ) {
            MainView()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    HannisAppTheme {
       FullScreen()
    }
}