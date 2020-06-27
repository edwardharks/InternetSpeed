package com.edwardharker.ispeed

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
fun DefaultPreview() {
    ISpeedTheme {
        SpeedViewState.Complete(speed = "100", retry = {}).draw()
    }
}