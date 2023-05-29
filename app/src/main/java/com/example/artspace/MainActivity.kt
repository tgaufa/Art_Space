package com.example.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Arrangement.SpaceBetween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.artspace.ui.theme.ArtSpaceTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtSpaceTheme {
                ArtSpaceLayout()
            }
        }
    }
}

@Composable
fun ArtSpaceLayout(modifierParam: Modifier = Modifier){
    val initialValue = 0

    var stepRemember by remember {
        mutableStateOf(value = initialValue)
    }

    val imgResource: List<Painter> =
        listOf(
        painterResource(id = R.drawable.image_1),
        painterResource(id = R.drawable.image_2),
        painterResource(id = R.drawable.image_3),
        painterResource(id = R.drawable.image_4),
        painterResource(id = R.drawable.image_5),
        painterResource(id = R.drawable.image_6),
        painterResource(id = R.drawable.image_7),
        )

    val titleResource: List<String> =
        listOf(
            stringResource(id = R.string.title_1),
            stringResource(id = R.string.title_2),
            stringResource(id = R.string.title_3),
            stringResource(id = R.string.title_4),
            stringResource(id = R.string.title_5),
            stringResource(id = R.string.title_6),
            stringResource(id = R.string.title_7),
        )

    val artistResource: List<String> =
        listOf(
            stringResource(id = R.string.artist_1),
            stringResource(id = R.string.artist_2),
            stringResource(id = R.string.artist_3),
            stringResource(id = R.string.artist_4),
            stringResource(id = R.string.artist_5),
            stringResource(id = R.string.artist_6),
            stringResource(id = R.string.artist_7),
        )

    val yearResource: List<String> =
        listOf(
            stringResource(id = R.string.year_1),
            stringResource(id = R.string.year_2),
            stringResource(id = R.string.year_3),
            stringResource(id = R.string.year_4),
            stringResource(id = R.string.year_5),
            stringResource(id = R.string.year_6),
            stringResource(id = R.string.year_7),
        )


    Surface(
        modifier = modifierParam.fillMaxSize(),

        color = MaterialTheme.colorScheme.background) {
        Column(
            modifier = modifierParam,
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier.weight(4f),
                contentAlignment = Alignment.BottomCenter) {
                ArtWorkWall(stepRemember = stepRemember, imgRes = imgResource)
            }
            Box(
                modifier = Modifier.weight(2f),
                contentAlignment = Alignment.TopCenter){
                ArtWorkDescriptor(
                    stepRemember = stepRemember,
                    titleRes = titleResource,
                    artistRes = artistResource,
                    yearRes = yearResource
                )
            }
            Box(modifier = Modifier.weight(1f)) {
                ButtonController(
                    onNext = { if (stepRemember < imgResource.size - 1) stepRemember++ else stepRemember = initialValue },
                    onPrev = { if (stepRemember > initialValue) stepRemember-- else stepRemember = imgResource.size - 1}
                )
            }

        }
    }

}



@Composable
fun ArtWorkWall(stepRemember: Int, imgRes: List<Painter>){

    Surface(
        modifier = Modifier.padding(30.dp),
        shadowElevation = 16.dp
    ) {
        Image(
            painter = imgRes[stepRemember],
            contentDescription = "",
            modifier = Modifier.padding(30.dp)
        )
    }
}

@Composable
fun ArtWorkDescriptor(
    stepRemember: Int,
    titleRes: List<String>,
    artistRes: List<String>,
    yearRes: List<String>,

    ){
    val lineHeight = with(LocalDensity.current) {
        MaterialTheme.typography.headlineMedium.fontSize.toDp()
    }
    val lines = 3

    Surface(modifier = Modifier
        .padding(top = 15.dp, bottom = 15.dp, start = 30.dp, end = 30.dp)
        .widthIn(min = 500.dp, max = 400.dp),
        color = MaterialTheme.colorScheme.inverseOnSurface,
        shadowElevation = 16.dp
    ) {
        Column(modifier = Modifier
            .padding(15.dp)
        ) {
            Text(
                text = titleRes[stepRemember],
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.height(lineHeight*lines)
            )
            Text(
                buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(fontWeight = FontWeight.Bold)
                    ) {
                        append(artistRes[stepRemember])
                    }
                    append(" ")
                    append("(${yearRes[stepRemember]})")
                }
            )
        }
    }
}

@Composable
fun ButtonController(onNext: () -> Unit, onPrev: () -> Unit){
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(top = 5.dp, start = 30.dp, end = 30.dp, bottom = 5.dp),
        horizontalArrangement = SpaceBetween
    ) {
        OutlinedButton(
            onClick = onPrev,
            modifier = Modifier.width(150.dp),
        ) {
            Text(text = "Previous", color = MaterialTheme.colorScheme.inverseSurface)
        }
        Spacer(modifier = Modifier.weight(1f))
        OutlinedButton(
            onClick = onNext,
            modifier = Modifier.width(150.dp),
            ) {
            Text(text = "Next", color = MaterialTheme.colorScheme.inverseSurface)
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ArtSpaceTheme {
        ArtSpaceLayout()
    }
}