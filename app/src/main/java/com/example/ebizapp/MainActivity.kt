package com.example.ebizapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.compose.material3.CardDefaults
import androidx.activity.compose.setContent
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.staggeredgrid.rememberLazyStaggeredGridState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardElevation
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.lightColorScheme
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ebizapp.ui.theme.EbizAppTheme
import kotlinx.coroutines.flow.callbackFlow


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EbizAppTheme {
                // A surface container using the 'background' color from the theme
                CreateBizCard()
            }
        }
    }
}

@Composable
fun CreateBizCard(){
    val buttonClickedState = remember {
        mutableStateOf(false)
    }
    Surface( modifier= Modifier
        .fillMaxHeight()
        .fillMaxWidth()) {

        Card( modifier = Modifier
            .width(200.dp)
            .height(390.dp)
            .padding(6.dp)
            .animateContentSize(),
            shape = RoundedCornerShape(corner = CornerSize(20.dp)),
            elevation = CardDefaults.cardElevation(8.dp)) {

            Column(modifier = Modifier,
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally) {

                CreateImageProfile()

                Divider(
                    modifier = Modifier.shadow(2.dp),
                    thickness = 1.dp,
                    color = Color.LightGray,)

                CreateInfo()

                Button(onClick = {
                    buttonClickedState.value =! buttonClickedState.value }) {

                    Text(text = "Portfolio",
                        style = MaterialTheme.typography.labelMedium)

                }

                if (buttonClickedState.value) {
                    Content()
                }
            }
        }
    }
}

@Preview
@Composable
fun Content() {

    Box(modifier = Modifier
        .fillMaxSize()) {

        Surface(modifier = Modifier
            .padding(10.dp).fillMaxSize(),
            shape = RoundedCornerShape(corner = CornerSize(10.dp)),
            border = BorderStroke(4.dp, Color.White)) {

            Portfolio(data = listOf(
                "Project 1",
                "Project 2",
                "Project 3",
                "Project 4",
                "Project 5",
                "Project 6",
                "Project 7",
                "Project 8",))

        }
    }
}

@Composable
fun Portfolio(data: List<String>) {
    LazyColumn{
        items(data) { item ->
            Card(modifier = Modifier
                    .padding(6.dp)
                    .fillMaxWidth(),
                shape = RectangleShape,
                elevation = CardDefaults.cardElevation(6.dp)) {

                Row(modifier = Modifier
                    .background(MaterialTheme.colorScheme.surface)
                    .padding(15.dp)) {

                    //Image
                    CreateImageProfile(modifier = Modifier.size(90.dp))

                    Column(modifier = Modifier
                        .padding(10.dp)
                        .align(Alignment.CenterVertically)) {

                        Text(text = item,
                            fontWeight = FontWeight.Bold,
                            style = MaterialTheme.typography.bodyLarge)
                        Text(text = "A great Project",
                            style = MaterialTheme.typography.bodyMedium)
                    }
                }
            }
        }
    }
}

@Composable
private fun CreateInfo() {
    Column(modifier = Modifier.padding(5.dp)) {
        Text(
            text = "Yash Sharma",
            style = MaterialTheme.typography.headlineLarge,
            color = Color.Magenta
        )

        Text(
            text = "Android Compose Developer",
            modifier = Modifier.padding(3.dp),
            style = MaterialTheme.typography.bodyLarge)

        Text(
            text = "@_Yash_ss",
            modifier = Modifier.padding(3.dp),
            style = MaterialTheme.typography.titleMedium)

    }
}

@Composable
private fun CreateImageProfile(modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier
            .size(170.dp)
            .padding(5.dp),
        shape = CircleShape,
        border = BorderStroke(3.dp, Color.LightGray),
        shadowElevation = 2.dp,
        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)) {

        Image(
            painter = painterResource(id = R.drawable.profile_image),
            contentDescription = "profile Image",
            modifier = modifier.size(135.dp),
            contentScale = ContentScale.Crop
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    EbizAppTheme {
        CreateBizCard()
    }
}
