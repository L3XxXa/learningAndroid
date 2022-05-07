package com.example.learningcaching

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import java.io.File
import java.nio.file.Files

class MainActivity : AppCompatActivity() {
    private val filename = "superfile"
    private lateinit var textToCache : EditText
    private lateinit var cachedText : TextView
    private lateinit var cacheButton : Button
    private lateinit var getCacheButton: Button
    private lateinit var clearCacheButton: Button
    private lateinit var getCacheSizeButton: Button
    private lateinit var cacheSizeTextView: TextView
    private lateinit var cacheFile : File
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        File.createTempFile(filename, null, applicationContext.cacheDir)
        cacheFile = File(applicationContext.cacheDir, filename)
        textToCache = findViewById(R.id.messageToCache)
        cachedText = findViewById(R.id.cachedText)
        cacheButton = findViewById(R.id.buttonToCache)
        cacheButton.setOnClickListener {
            cacheData()
        }
        getCacheButton = findViewById(R.id.getCache)
        getCacheButton.setOnClickListener {
            getCache()
        }
        clearCacheButton = findViewById(R.id.clearCacheButton)
        clearCacheButton.setOnClickListener {
            clearCache()
        }
        getCacheSizeButton = findViewById(R.id.getCacheSize)
        getCacheSizeButton.setOnClickListener {
            getCacheSize()
        }
        cacheSizeTextView = findViewById(R.id.cacheSize)
    }

    private fun getCacheSize() {
        val fileLength = cacheFile.length()
        cacheSizeTextView.text = "Cache size: $fileLength"
    }

    private fun clearCache() {
        cacheFile.delete()
    }

    private fun cacheData() {
        cacheFile.appendText(textToCache.text.toString() + "\n")
        Toast.makeText(applicationContext, "Successfully cached data", Toast.LENGTH_SHORT).show()
    }

    private fun getCache() {

        try {
        val textValue = java.lang.StringBuilder()
        cacheFile.forEachLine {
            textValue.append(it)
            textValue.append("\n")
        }
        cachedText.text = textValue.toString()
        } catch (e: Exception){
            Toast.makeText(this, "Cache is empty", Toast.LENGTH_SHORT).show()
            cacheFile = File(applicationContext.cacheDir, filename)

        }
    }


}