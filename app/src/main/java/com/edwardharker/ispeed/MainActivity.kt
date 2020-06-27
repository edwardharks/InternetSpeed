package com.edwardharker.ispeed

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.Composable
import androidx.lifecycle.LiveData
import androidx.ui.core.setContent
import androidx.ui.livedata.observeAsState
import androidx.ui.tooling.preview.Preview
import com.edwardharker.ispeed.SpeedViewState.Initial
import com.edwardharker.ispeed.ui.ISpeedTheme

class MainActivity : AppCompatActivity() {
    private val speedViewModel: SpeedViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ISpeedTheme {
                Speed(speedViewModel.viewState)
            }
        }
    }
}

@Composable
fun Speed(speedViewState: LiveData<SpeedViewState>) {
    val state = speedViewState.observeAsState(Initial)
    state.value.draw()
}

@Preview(showBackground = true)
@Composable
fun LightPreview() {
    ISpeedTheme {
        SpeedViewState.Complete(speed = "100", retry = {}).draw()
    }
}

@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun DarkPreview() {
    ISpeedTheme {
        SpeedViewState.Complete(speed = "100", retry = {}).draw()
    }
}