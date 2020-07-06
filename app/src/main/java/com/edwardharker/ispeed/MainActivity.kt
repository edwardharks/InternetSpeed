package com.edwardharker.ispeed

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.Composable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.ui.core.setContent
import androidx.ui.livedata.observeAsState
import androidx.ui.tooling.preview.Preview
import com.edwardharker.ispeed.SpeedViewState.Complete
import com.edwardharker.ispeed.SpeedViewState.Initial
import com.edwardharker.ispeed.ui.ISpeedTheme

class MainActivity : AppCompatActivity() {
    private val speedViewModel: SpeedViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainActivityContent(speedViewModel.viewState)
        }
    }
}

@Composable
private fun MainActivityContent(viewState: LiveData<SpeedViewState>) {
    ISpeedTheme {
        SpeedViewState(viewState)
    }
}

@Composable
private fun SpeedViewState(speedViewState: LiveData<SpeedViewState>) {
    val state = speedViewState.observeAsState(Initial)
    state.value.draw()
}

@Preview(showBackground = true)
@Composable
private fun LightPreview() {
    MainActivityContent(
        viewState = MutableLiveData(
            Complete(
                speed = "100",
                retry = {}
            )
        )
    )
}

@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
private fun DarkPreview() {
    MainActivityContent(
        viewState = MutableLiveData(
            Complete(
                speed = "100",
                retry = {}
            )
        )
    )
}