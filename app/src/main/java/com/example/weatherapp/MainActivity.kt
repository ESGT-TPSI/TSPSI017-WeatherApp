package com.example.weatherapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import com.example.weatherapp.ui.theme.Bluish
import com.example.weatherapp.ui.theme.WeatherAppTheme
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WeatherAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppCard()
                }
            }
        }
    }
}



@Preview(showBackground = true, widthDp = 390, heightDp = 800 )
@Composable
fun AppCard() {
    WeatherAppTheme() {
        Box(modifier = Modifier.fillMaxSize()){
                Image(painter = painterResource(id = R.drawable._86449326_37c2256216_b),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxSize()
                        .alpha(0.2f),
                    contentScale = ContentScale.Crop)
            }
            Box(modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colorStops = arrayOf(
                            Pair(0.4f, Color.Transparent),
                            Pair(1f, MaterialTheme.colorScheme.primary)
                        )
                    )
                ))
            Column(modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 5.dp)) {
                HeaderPreview()
                HelloUser()
                CentralImage()
                NextDays()
            }}
}

data class TemperatureDay(val Temperatura:Int, val Estado:String, val Resource:Int)
val listaDeTemperaturas = listOf(
    TemperatureDay(25, "Limpo", R.drawable.sun),
    TemperatureDay(22, "Limpo", R.drawable.sun),
    TemperatureDay(15, "Chuva", R.drawable.rainy),
    TemperatureDay(18, "Tempestade", R.drawable.storm),
    TemperatureDay(17, "Chuva", R.drawable.rainy),
    TemperatureDay(12, "Chuva", R.drawable.rainy),
    TemperatureDay(5, "Neve", R.drawable.snowy)
)
@Composable
fun FutureDayItem(day: TemperatureDay)
{
    Box(
        modifier = Modifier
            .padding(5.dp)
            .size(100.dp, 200.dp),
        contentAlignment = Alignment.Center
    ) {
        Row(modifier =
        Modifier
            .fillMaxSize()
            .background(color = Color.Transparent),
            Arrangement.Center) {
            Column(
                modifier = Modifier
                    .padding(5.dp)
                    .shadow(10.dp, CircleShape, spotColor = MaterialTheme.colorScheme.tertiaryContainer)
                    .clip(CircleShape)
                    .background(color = MaterialTheme.colorScheme.tertiaryContainer),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                Box(contentAlignment = Alignment.TopCenter, modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 15.dp)){
                    Box( modifier = Modifier
                        .height(60.dp)
                        .padding(top = 15.dp)){
                        Image(
                            painter = painterResource(id = day.Resource),
                            contentDescription = "ehll"
                        )
                    }
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(top = 0.dp)){
                        Text("${day.Temperatura}"+"º",
                            modifier = Modifier.padding(0.dp,0.dp,0.dp, 0.dp),
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary,
                            fontSize= TextUnit(25f, TextUnitType.Sp))
                    }
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(top = 50.dp)){
                        Text("${day.Estado}",
                            fontWeight = FontWeight.ExtraLight,color= MaterialTheme.colorScheme.primary,
                            fontSize= TextUnit(13f, TextUnitType.Sp),
                            modifier = Modifier.padding(0.dp,0.dp,0.dp, 0.dp))
                    }
                }
            }
        }

    }
}
@Preview(showBackground = true, widthDp = 390)
@Composable
fun NextDays()
{
    Column(modifier = Modifier.height(210.dp)) {
        Row(modifier = Modifier.padding(horizontal = 16.dp)) {
            Text("Próximos 7 dias",
                fontSize= TextUnit(25f, TextUnitType.Sp ),
                color= MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.Bold )
        }

        LazyRow(
            modifier = Modifier.fillMaxSize(),
        ) {
            items(listaDeTemperaturas) { item ->
                // Conteúdo a ser exibido para cada item da lista
                FutureDayItem(item)
            }
        }
    }
}

@Preview(showBackground = true, widthDp = 390)
@Composable
fun HelloUser(username: String= "Pedro", )
{
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 16.dp)) {

    Row(modifier = Modifier.fillMaxWidth())  {
        Text("Olá",
            fontSize= TextUnit(25f, TextUnitType.Sp ),
            color= MaterialTheme.colorScheme.primary,
            fontWeight = FontWeight.Light )
        Text(" $username",
            fontWeight = FontWeight.Bold,
            color= MaterialTheme.colorScheme.primary,
            fontSize= TextUnit(25f, TextUnitType.Sp))
    }
    Row(modifier = Modifier.fillMaxWidth())  {
        Text(formatData(Calendar.getInstance().time),
            fontSize= TextUnit(18f, TextUnitType.Sp ),
            fontWeight = FontWeight.Light,
            color = MaterialTheme.colorScheme.secondary)
        }
    Row(modifier = Modifier.fillMaxWidth())  {
        Text("Benavente",
            fontSize= TextUnit(18f, TextUnitType.Sp ),
            fontWeight = FontWeight.Light,
            color = MaterialTheme.colorScheme.secondary)
    }
    }
}

@Preview(showBackground = true, widthDp = 390)
@Composable
fun HeaderPreview(modifier: Modifier = Modifier) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 15.dp, horizontal = 16.dp)
    ) {
        CircularButton(iconResource= R.drawable.arrowback, modifier= Modifier)
        CircularButton(iconResource= R.drawable.foto, color = Color.Transparent)
    }
}

@Composable
fun CircularButton(
    modifier: Modifier = Modifier,
    @DrawableRes iconResource: Int,
    color: Color = MaterialTheme.colorScheme.background,
    onClick: () -> Unit = {}
){
    Button(
        onClick = onClick,
        contentPadding = PaddingValues(),
        colors = ButtonDefaults.buttonColors(containerColor = color, contentColor = color),
        modifier = modifier
            .size(38.dp)
            .clip(CircleShape)
    )
    {
        Image(painter = painterResource(id = iconResource), contentDescription = null)
    }
}

@Composable
fun CentralImage(temperatura: Int = 23, estadoTempo: String = "Limpo", @DrawableRes iconResource: Int= R.drawable.sun)
{
    Row(modifier =
    Modifier
        .padding(vertical = 25.dp)
        .height(300.dp)
        .fillMaxWidth()
        .background(color = Color.Transparent),
        Arrangement.Center) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .size(250.dp)
                .shadow(80.dp, CircleShape, spotColor = Color.Transparent)
                .clip(CircleShape)
                .background(
                    Brush.verticalGradient(
                        colorStops = arrayOf(
                            Pair(0.2f, MaterialTheme.colorScheme.secondaryContainer),
                            Pair(1f, MaterialTheme.colorScheme.tertiaryContainer)
                        )
                    )
                ),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Box(contentAlignment = Alignment.TopCenter, modifier = Modifier
                .fillMaxSize()
                .padding(top = 15.dp)){
                Box( modifier = Modifier
                    .height(140.dp)
                    .padding(top = 0.dp)){
                        Image(
                                painter = painterResource(id = iconResource),
                                contentDescription = "ehll"
                            )
                    }
                Box(
                    contentAlignment = Alignment.CenterEnd,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 90.dp)){
                    Text("$temperatura"+"º",
                            modifier = Modifier.padding(0.dp,0.dp,100.dp, 0.dp),
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary,
                            fontSize= TextUnit(55f, TextUnitType.Sp))
                }
                Box(
                    contentAlignment = Alignment.CenterStart,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 90.dp)){
                    Text("$estadoTempo",
                        fontWeight = FontWeight.ExtraLight,color= MaterialTheme.colorScheme.primary,
                        fontSize= TextUnit(15f, TextUnitType.Sp),
                        modifier = Modifier.padding(145.dp,23.dp,0.dp, 0.dp))
                }
            }
        }
    }
}

@Preview(showBackground = true, widthDp = 390)
@Composable
fun CentralImage_Clean()
{
    CentralImage(17, "Limpo",R.drawable.sun)
}

@Preview(showBackground = true, widthDp = 390)
@Composable
fun CentralImage_Rainy()
{
    CentralImage(7, "Chuva",R.drawable.rainy)
}

@Preview(showBackground = true, widthDp = 390)
@Composable
fun CentralImage_Snowy()
{
    CentralImage(-1, "Neve",R.drawable.snowy)
}

@Preview(showBackground = true, widthDp = 390)
@Composable
fun CentralImage_Storm()
{
    CentralImage(12, "Trovoada",R.drawable.storm)
}

// Helpers
fun formatData(data: Date): String {
    val format = SimpleDateFormat("dd MMMM, EEEE yyyy", Locale("pt", "PT"))
    return format.format(data)
}