package com.example.myapplication

import android.app.Application
import android.content.Context
import androidx.test.runner.AndroidJUnitRunner
import dagger.hilt.android.testing.HiltTestApplication

class CustomTestRunner : AndroidJUnitRunner() {

    override fun newApplication(
        cl: ClassLoader?, appName: String?, context: Context?
    ): Application? {
        println("POZVALO SE JEEEE")
        return super.newApplication(
            cl, HiltTestApplication::class.java.name, context
        )
    }
}