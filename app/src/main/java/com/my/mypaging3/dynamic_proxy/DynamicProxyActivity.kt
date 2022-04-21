package com.my.mypaging3.dynamic_proxy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.my.mypaging3.R
import java.lang.reflect.InvocationHandler
import java.lang.reflect.Method
import java.lang.reflect.Proxy

class DynamicProxyActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dynamic_proxy)

        val analytics = Analytics.Builder()
            .withDependencies("")
            .build()
        val analyticsSource = analytics.create(AnalyticsSource::class.java)

        analyticsSource.logEvent("on_settings_clicked")
    }
}

interface AnalyticsSource {

    @Event("some_event")
    fun logEvent(@Param("event") event: String)
}

class AnalyticsInvocationHandler : InvocationHandler {
    override fun invoke(proxy: Any?, method: Method?, args: Array<out Any>?): Any {
        val event: Event = method?.annotations?.firstNotNullOf { it as? Event } ?: return Unit
        Log.d("EE", "event = ${event.value}")

        method.parameterAnnotations.map {
            val param = it.firstNotNullOf { it as? Param }.value
            Log.d("EE", "param = $param")
        }

        args?.forEach { arg ->
            Log.d("EE", "arg = $arg")
        }

        return Unit
    }
}

class Analytics private constructor(private val dependencies: String) {

    fun <T : Any> create(clazz: Class<T>): T {
        return Proxy.newProxyInstance(
            clazz.classLoader,
            arrayOf(clazz),
            AnalyticsInvocationHandler()
        ) as T
    }

    class Builder {
        private var dependencies: String? = null

        fun withDependencies(dependencies: String) = apply {
            this.dependencies = dependencies
        }

        fun build(): Analytics {
            val dep = dependencies ?: throw NullPointerException("dependencies must be not null")
            return Analytics(dep)
        }
    }
}