package com.edwardharker.ispeed

import androidx.compose.Composable
import androidx.ui.core.Alignment
import androidx.ui.core.Alignment.Companion.Bottom
import androidx.ui.core.Alignment.Companion.Center
import androidx.ui.core.Alignment.Companion.CenterHorizontally
import androidx.ui.core.Modifier
import androidx.ui.foundation.Box
import androidx.ui.foundation.Text
import androidx.ui.graphics.Color
import androidx.ui.graphics.Color.Companion.Transparent
import androidx.ui.layout.*
import androidx.ui.material.Button
import androidx.ui.material.CircularProgressIndicator
import androidx.ui.material.OutlinedButton
import androidx.ui.material.TextButton
import androidx.ui.text.FirstBaseline
import androidx.ui.tooling.preview.Preview
import androidx.ui.unit.dp
import com.edwardharker.ispeed.ui.typography

sealed class SpeedViewState {
    @Composable
    abstract fun draw()

    object Initial : SpeedViewState() {
        @Composable
        override fun draw() {
        }
    }

    class Complete(
        private val speed: String,
        private val retry: () -> Unit
    ) : SpeedViewState() {
        @Composable
        override fun draw() {
            Box(modifier = Modifier.fillMaxSize().wrapContentSize(Center)) {
                Column(horizontalGravity = CenterHorizontally) {
                    SpeedView(speed)
                    BottomContent {
                        OutlinedButton(
                            onClick = retry
                        ) {
                            Text(
                                text = "Retry"
                            )
                        }
                    }
                }
            }
        }
    }

    class Progress(
        private val speed: String
    ) : SpeedViewState() {
        @Composable
        override fun draw() {
            Box(modifier = Modifier.fillMaxSize().wrapContentSize(Center)) {
                Column(horizontalGravity = CenterHorizontally) {
                    SpeedView(speed)
                    BottomContent {
                        CircularProgressIndicator()
                    }
                }
            }
        }
    }

    class Error(
        private val message: String,
        private val retry: () -> Unit
    ) : SpeedViewState() {
        @Composable
        override fun draw() {
            Box(modifier = Modifier.fillMaxSize().wrapContentSize(Center)) {
                Column(horizontalGravity = CenterHorizontally) {
                    Text(text = message)
                    Button(
                        onClick = retry
                    ) {
                        Text("Error")
                    }
                }
            }
        }
    }
}

@Composable
private fun SpeedView(speed: String) {
    Row(verticalGravity = Bottom) {
        Text(
            style = typography.h1,
            modifier = Modifier.alignWithSiblings(FirstBaseline),
            text = speed
        )
        Text(
            style = typography.h4,
            modifier = Modifier.alignWithSiblings(FirstBaseline),
            text = "Mbps"
        )
    }
}

@Composable
fun BottomContent(content: @Composable() () -> Unit) {
    Box(
        modifier = Modifier.height(96.dp),
        gravity = Center
    ) {
        content()
    }
}

@Preview
@Composable
fun Initial() {
    SpeedViewState.Initial.draw()
}

@Preview
@Composable
fun Complete() {
    SpeedViewState.Complete(speed = "100", retry = {}).draw()
}

@Preview
@Composable
fun Progress() {
    SpeedViewState.Progress(speed = "100").draw()
}


@Preview
@Composable
fun Error() {
    SpeedViewState.Error(message = "There was an error", retry = {}).draw()
}