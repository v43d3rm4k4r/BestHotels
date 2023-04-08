package com.github.v43d3rm4k4r.besthotels

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

/**
 * TODO: Test Repository
 */
@RunWith(AndroidJUnit4::class)
class RepositoryTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.github.v43d3rm4k4r.besthotels", appContext.packageName)
    }
}