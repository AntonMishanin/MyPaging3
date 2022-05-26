package com.my.mypaging3.coroutines

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import com.my.mypaging3.R
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import java.lang.IllegalArgumentException
import kotlin.random.Random
import kotlin.system.measureTimeMillis

class CoroutinesActivity : AppCompatActivity() {

    private val viewModel by viewModels<CoroutinesViewModel>()

    private val scope = CoroutineScope(Dispatchers.Main)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coroutines)

        viewModel.onTextChanged("dsds")

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

        CoroutineScope(Dispatchers.IO)
        MainScope()

        lifecycleScope.launch(exceptionHandler + Dispatchers.IO) {
            println(Thread.currentThread())
            throw IllegalArgumentException("sds")
        }


        val result: Deferred<Unit> = lifecycleScope.async {
            // val localResult = fetchContent()


        }

        viewModel.observeState().onEach {
            println("ACTIVITY $it")
        }.launchIn(lifecycleScope)
        //fetchContent()

        viewModel.channelEvents.onEach {
            println("CHANNEL $it")
        }.launchIn(lifecycleScope)

        viewModel.flowEvent.onEach {
            println("SHARED FLOW $it")
        }.launchIn(lifecycleScope)
    }

    override fun onDestroy() {
        super.onDestroy()
        scope.cancel()
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

    private val _state = MutableStateFlow("init")

    val flowEvent = MutableSharedFlow<String>()

    private val _channelEvents = Channel<String>(Channel.BUFFERED)
    val channelEvents = _channelEvents.receiveAsFlow()

    init {
        viewModelScope.launch {

            flowEvent.emit("FLOW SHARED")
            _channelEvents.send("CHANNEL VALUE")

            println(interactor.fetch())
        }


        //  viewModelScope.launch {
        //  fetchSomeContent().onEach {
        //      println("VIEW MODEL $it")
        //  }.launchIn(viewModelScope)//.collect()
        //
        //  //content.value = interactor.fetchContent()
        //  }

        //  content.launchIn(viewModelScope).emit("floww()").launchI
        //content.tryEmit()
        //synchronized()


        interactor.flowContent().onEach {
            _state.value = it
        }.launchIn(scope = viewModelScope + Dispatchers.Main)

        println("VIEW MODEL INITED")
    }

    fun observeState(): StateFlow<String> = _state

    private val emitter = MutableSharedFlow<String>()

    fun onTextChanged(value: String) {
        viewModelScope.launch {
            emitter.emit(value)

            emitter
                //.debounce(500)
                //.distinctUntilChanged()
                //.mapLatest { interactor.doSearchRequest(it) }
                .onStart {
                    println("onStart2 +${Thread.currentThread()}")
                    emit("222")
                }
                .onEach {
                    println("onEach222 $it" + Thread.currentThread())
                }
                .flowOn(Dispatchers.IO)
                .onStart {
                    println("onStart1 +${Thread.currentThread()}")
                    emit("111")
                }
                .flowOn(Dispatchers.Default)
                .onEach {
                    println("onEach111 $it" + Thread.currentThread())
                }
                .collect()
        }
    }

    private suspend fun fetchSomeContent(): StateFlow<String> {
        val stateFlow = MutableStateFlow("init value")
        stateFlow.emit("first value")
        return stateFlow
    }

    private fun floww() = flow {
        emit("s")
    }
}

class CoroutinesInteractor(
    private val repository: CoroutinesRepository = CoroutinesRepository(),
    private val flowRepository: CoroutinesRepository2 = CoroutinesRepository2()
) {

    suspend fun fetchContent(): String {
        return withContext(Dispatchers.IO) {
            repository.fetchContent()
        }
    }

    fun flowContent() = flowRepository.fetchContent()
        .map {
            // handle result and do some logic
            it
        }.flowOn(Dispatchers.IO)

    suspend fun fetch(): List<Int> {
        val result: List<Deferred<Int>> = listOfIds().map { id ->
            coroutineScope {
                async { getById(id) }
            }
        }

        return result.awaitAll()
    }

    private suspend fun listOfIds(): List<Int> {
        delay(400)
        return listOf(1, 2, 3, 4, 5, 6, 7, 8, 9)
    }

    private suspend fun getById(id: Int): Int {
        delay(Random.nextLong(0, 200))
        return id
    }

    suspend fun doSearchRequest(value: String): String {
        delay(500)
        return "result"
    }
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

    fun fetchContent(isNeedFresh: Boolean = true): Flow<String> {
        return when (isNeedFresh) {
            true -> networkRequest()
            false -> storageFetch()
        }
    }

    private fun networkRequest() = flow {

        val value = networkSuspend()
        emit(value)
        coroutineScope {
            async {
                saveToStorageSuspend(value)
            }
        }

    }

    private fun storageFetch() = flow {
        val value = fetchFromSuspend()
        emit(value)
    }

    private suspend fun networkSuspend(): String {
        delay(1_500)
        return "network request"
    }

    private suspend fun saveToStorageSuspend(value: String) {
        delay(1_000)
    }

    private suspend fun fetchFromSuspend(): String {
        delay(1_000)
        return "storage fetch"
    }
}