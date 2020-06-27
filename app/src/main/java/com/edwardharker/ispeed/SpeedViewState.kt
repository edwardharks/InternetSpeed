package com.edwardharker.ispeed

import androidx.compose.Composable
import androidx.ui.core.Alignment.Companion.Bottom
import androidx.ui.core.Alignment.Companion.Center
import androidx.ui.core.Alignment.Companion.CenterHorizontally
import androidx.ui.core.Modifier
import androidx.ui.core.drawOpacity
import androidx.ui.foundation.Box
import androidx.ui.foundation.Text
import androidx.ui.layout.*
import androidx.ui.material.Button
import androidx.ui.material.CircularProgressIndicator
import androidx.ui.material.MaterialTheme
import androidx.ui.material.OutlinedButton
import androidx.ui.text.FirstBaseline
import androidx.ui.tooling.preview.Preview
import androidx.ui.unit.dp

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
                    SpeedView(
                        speed = speed,
                        alpha = 0.7f
                    )
                    BottomContent {
                        CircularProgressIndicator()
                    }
                }
            }
        }
    }

    class Error(
        private val retry: () -> Unit
    ) : SpeedViewState() {
        @Composable
        override fun draw() {
            Box(modifier = Modifier.fillMaxSize().wrapContentSize(Center)) {
                Column(horizontalGravity = CenterHorizontally) {
                    Text(
                        style = MaterialTheme.typography.h4,
                        color = MaterialTheme.colors.onBackground,
                        text = "Error"
                    )
                    BottomContent {
                        Button(
                            onClick = retry
                        ) {
                            Text("Retry")
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun SpeedView(
    speed: String,
    alpha: Float = 1f
) {
    Row(
        verticalGravity = Bottom,
        modifier = Modifier.drawOpacity(alpha)
    ) {
        Text(
            style = MaterialTheme.typography.h1,
            modifier = Modifier.alignWithSiblings(FirstBaseline),
            color = MaterialTheme.colors.onBackground,
            text = speed
        )
        Text(
            style = MaterialTheme.typography.h4,
            modifier = Modifier.alignWithSiblings(FirstBaseline),
            color = MaterialTheme.colors.onBackground,
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
    SpeedViewState.Error(retry = {}).draw()
}