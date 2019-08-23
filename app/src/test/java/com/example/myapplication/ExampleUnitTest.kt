package com.example.myapplication

import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import com.example.myapplication.MainActivity

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    private var mainActivity: MainActivity? = null

    @Before
    fun init() {
        mainActivity = MainActivity()
        
    }
    
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun myTest() {
        assertEquals(2L, mainActivity?.sum())
    }
}
