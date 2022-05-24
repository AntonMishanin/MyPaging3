package com.my.mypaging3.coroutines

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import com.my.mypaging3.R
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import java.lang.IllegalArgumentException
import kotlin.system.measureTimeMillis

class CoroutinesActivity : AppCompatActivity() {

    private val viewModel by viewModels<CoroutinesViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coroutines)

        val scope = CoroutineScope(Job() + Dispatchers.Main)
        val job: Job = lifecycleScope.launch(Job() + Dispatchers.Main) {

            val time1 = measureTimeMillis {
                // successively
                fetchContent1()
                fetchContent2()
            }

            println("time1 $time1")

            val time2 = measureTimeMillis {
                // parallel
                val result1 = async { fetchContent1() }
                val result2 = async { fetchContent2() }
                result1.await()
                result2.await()
            }

            println("time2 $time2")
        }

        val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
            println("throwable $throwable")
        }

        lifecycleScope.launch(exceptionHandler + Dispatchers.IO) {
            println(Thread.currentThread())
            throw IllegalArgumentException("sds")
        }


        val result: Deferred<Unit> = lifecycleScope.async {
            // val localResult = fetchContent()


        }

        viewModel.content.onEach {
            println("ACTIVITY $it")
        }
        //fetchContent()
    }
}

private suspend fun fetchContent1() {
    println("start-1")
    delay(1_000)
    println("finish-1")
}

private suspend fun fetchContent2() {
    println("start-2")
    delay(1_000)
    println("finish-2")
}

class CoroutinesViewModel(
    private val interactor: CoroutinesInteractor = CoroutinesInteractor()
) : ViewModel() {

  //  var content: StateFlow<String> = MutableStateFlow()//= fetchSomeContent()
  var content = MutableSharedFlow<String>()

    init {
      //  viewModelScope.launch {
      //  fetchSomeContent().onEach {
      //      println("VIEW MODEL $it")
      //  }.launchIn(viewModelScope)//.collect()
      //
      //  //content.value = interactor.fetchContent()
      //  }

      //  content.launchIn(viewModelScope).emit("floww()").launchI
    }

    private suspend fun fetchSomeContent(): StateFlow<String>  {
        val stateFlow = MutableStateFlow("init value")
        stateFlow.emit("first value")
 return stateFlow
    }

    private fun floww() = flow {
        emit("s")
    }
}

class CoroutinesInteractor(
    private val repository: CoroutinesRepository = CoroutinesRepository()
) {

    suspend fun fetchContent() = repository.fetchContent()
}

class CoroutinesRepository {

    private var sessionCache = ""

    suspend fun fetchContent(isNeedFresh: Boolean = true): String {
        return when (isNeedFresh || sessionCache.isEmpty()) {
            true -> requestFreshContent()
            false -> sessionCache
        }
    }

    private suspend fun requestFreshContent(): String = coroutineScope {
        val networkResult = doNetworkRequest()
        async { saveToDb(networkResult) }
        sessionCache = networkResult
        return@coroutineScope networkResult
    }

    private suspend fun doNetworkRequest(): String {
        delay(1_500)
        return "success"
    }

    private suspend fun saveToDb(value: String) {
        println()
        delay(2_000)
    }
}

class CoroutinesRepository2 {

    fun fetchContent(isNeedFresh: Boolean = true) {


    }
}