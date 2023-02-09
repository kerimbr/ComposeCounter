@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.material3compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*

import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.material3compose.ui.theme.Material3ComposeTheme
import compose.icons.FeatherIcons
import compose.icons.feathericons.Minus
import compose.icons.feathericons.Plus


class MainActivity : ComponentActivity() {
    @ExperimentalMaterial3Api
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Material3ComposeTheme {
                CounterView()
            }
        }
    }
}

@ExperimentalMaterial3Api
@Composable
fun CounterView() {
    val counter = remember { mutableStateOf(0) }
    Scaffold(
        topBar = {
            AppBar(counter)
        },
        content = { innerPadding ->
            Surface(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize()
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {

                    CounterText(counter)

                    Spacer(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(1.dp)
                            .background(MaterialTheme.colorScheme.onSurface.copy(alpha = 0.12f))
                    )

                    ResetCounterButton(counter)

                    BoostButtonsRow(counter)

                    IncDecButtonsRow(counter)

                }
            }
        }
    )

}

@Composable
private fun AppBar(counter: MutableState<Int>) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                "Counter App",
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        },
        navigationIcon = {
            IconButton(onClick = {
                counter.value--
            }) {
                Icon(
                    imageVector = FeatherIcons.Minus,
                    contentDescription = "Decrement Counter"
                )
            }
        },
        actions = {
            IconButton(onClick = {
                counter.value++
            }) {
                Icon(
                    imageVector = FeatherIcons.Plus,
                    contentDescription = "Increment Counter"
                )
            }
        }
    )
}

@Composable
private fun CounterText(counter: MutableState<Int>) {
    val animatedProgress by animateFloatAsState(
        targetValue = counter.value.toFloat() / 100,
        animationSpec = ProgressIndicatorDefaults.ProgressAnimationSpec
    )
    Box(
        contentAlignment = Alignment.Center,
    ) {
        CircularProgressIndicator(
            progress = animatedProgress,
            modifier = Modifier
                .size(200.dp)
                .padding(16.dp),
            strokeWidth = 16.dp,
            color = setCircularProgressIndicatorColor(counter.value),
        )
        Text(
            text = "${counter.value}",
            modifier = Modifier.padding(16.dp),
            style = TextStyle(
                fontSize = 50.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = MaterialTheme.typography.bodyLarge.fontFamily,
                letterSpacing = MaterialTheme.typography.bodyLarge.letterSpacing,
                lineHeight = MaterialTheme.typography.bodyLarge.lineHeight,
                color = MaterialTheme.colorScheme.primary
            )
        )
    }
}

@Composable
fun setCircularProgressIndicatorColor(value: Int): Color {
    return when {
        value < 50 -> MaterialTheme.colorScheme.primary
        value < 75 -> MaterialTheme.colorScheme.secondary
        else -> MaterialTheme.colorScheme.tertiary
    }
}

@Composable
private fun ResetCounterButton(counter: MutableState<Int>) {
    TextButton(
        onClick = {
            counter.value = 0
        },
        modifier = Modifier
            .padding(8.dp),
        shape = MaterialTheme.shapes.medium,
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
    ) {
        Text("Reset")
    }
}

@Composable
private fun BoostButtonsRow(counter: MutableState<Int>) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {

        OutlinedButton(
            onClick = {
                counter.value -= 10
                if (counter.value < 0)
                    counter.value = 0
            },
            modifier = Modifier
                .padding(16.dp)
                .weight(1f),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 4.dp),
        ) {
            Icon(
                imageVector = FeatherIcons.Minus,
                contentDescription = "Dec by 10"
            )
            Text(
                modifier = Modifier.padding(start = 8.dp),
                text = "10",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        }

        OutlinedButton(
            onClick = {
                counter.value += 10
                if (counter.value > 100)
                    counter.value = 100
            },
            modifier = Modifier
                .padding(16.dp)
                .weight(1f),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 4.dp),
        ) {
            Icon(
                imageVector = FeatherIcons.Plus,
                contentDescription = "Inc by 10"
            )
            Text(
                modifier = Modifier.padding(start = 8.dp),
                text = "10",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
private fun IncDecButtonsRow(counter: MutableState<Int>) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {

        Button(
            onClick = {
                counter.value--
                if (counter.value < 0)
                    counter.value = 0
            },
            modifier = Modifier
                .padding(16.dp)
                .weight(1f),
            shape = MaterialTheme.shapes.medium,
            contentPadding = PaddingValues(16.dp),
        ) {
            Icon(
                imageVector = FeatherIcons.Minus,
                contentDescription = "Decrement Counter"
            )
        }

        Button(
            onClick = {
                counter.value++
                if (counter.value > 100)
                    counter.value = 100
            },
            modifier = Modifier
                .padding(16.dp)
                .weight(1f),
            shape = MaterialTheme.shapes.medium,
            contentPadding = PaddingValues(16.dp),
        ) {
            Icon(
                imageVector = FeatherIcons.Plus,
                contentDescription = "Increment Counter"
            )
        }
    }
}







@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Material3ComposeTheme {
        CounterView()
    }
}