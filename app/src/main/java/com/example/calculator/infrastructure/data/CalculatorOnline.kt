package com.example.calculator.infrastructure.data

import android.util.Log
import com.example.calculator.domain.Result
import com.example.calculator.domain.api.ICalculatorService
import org.json.JSONObject
import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.net.HttpURLConnection
import java.net.URL

class CalculatorOnline : ICalculatorService {

    private val history = SharedHistory

    override suspend fun calculate(expression: String): Double? {
        val url = URL("http://192.168.0.129:8080/api/calculations")
        val openedConnection = url.openConnection() as HttpURLConnection
        openedConnection.requestMethod = "POST"
        openedConnection.setRequestProperty(
            "Content-Type",
            "application/json"
        )
        openedConnection.setRequestProperty("Accept", "application/json")
        openedConnection.doInput = true
        openedConnection.doOutput = true
        try {
            openedConnection.setChunkedStreamingMode(0);
            val out = BufferedWriter(OutputStreamWriter(openedConnection.outputStream));
            val json = JSONObject()
            json.put("expression", expression)
            out.write(json.toString())
            out.flush()
            val responseCode = openedConnection.responseCode
            val reader = BufferedReader(InputStreamReader(openedConnection.inputStream))
            val response = reader.readText()
            Log.d("Success", response)
            reader.close()
            history.add(Result(expression, response.toDouble().toString()))
            return response.toDouble()
        } catch (e: Exception) {
            e.printStackTrace()
            Log.d("Error", e.message.toString())
            return null
        }
    }
}