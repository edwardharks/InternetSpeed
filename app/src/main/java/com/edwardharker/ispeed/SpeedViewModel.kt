package com.edwardharker.ispeed

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fr.bmartel.speedtest.SpeedTestReport
import fr.bmartel.speedtest.SpeedTestSocket
import fr.bmartel.speedtest.inter.ISpeedTestListener
import fr.bmartel.speedtest.model.SpeedTestError
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SpeedViewModel : ViewModel() {
    private val _viewState = MutableLiveData<SpeedViewState>()
    val viewState: LiveData<SpeedViewState> = _viewState

    private val speedTestSocket = SpeedTestSocket()

    init {
        speedTestSocket.addSpeedTestListener(ResultListener())
        start()
    }

    private fun start() {
        viewModelScope.launch {
            withContext(IO) {
                speedTestSocket.startFixedDownload(
                    "http://ipv4.ikoula.testdebit.info/10M.iso",
                    5_000
                )
            }
        }
    }

    private fun stop() {
        speedTestSocket.forceStopTask()
    }

    private fun getFormattedSpeed(report: SpeedTestReport): String {
        return report.transferRateMegaBit.toInt().toString()
    }

    private inner class ResultListener : ISpeedTestListener {
        override fun onCompletion(report: SpeedTestReport) {
            _viewState.postValue(
                SpeedViewState.Complete(
                    speed = getFormattedSpeed(report),
                    retry = ::start
                )
            )
        }

        override fun onProgress(percent: Float, report: SpeedTestReport) {
            _viewState.postValue(
                SpeedViewState.Progress(
                    speed = getFormattedSpeed(report)
                )
            )
        }

        override fun onError(
            speedTestError: SpeedTestError,
            errorMessage: String
        ) {
            _viewState.postValue(SpeedViewState.Error(message = errorMessage, retry = ::start))
        }
    }
}