package com.example.calculator.infrastructure.data

import android.content.Context
import android.content.SharedPreferences
import com.example.calculator.domain.api.IResultRepository
import com.example.calculator.domain.Result
import org.json.JSONArray
import org.json.JSONObject

object SharedHistory : IResultRepository {

    private lateinit var sharedPreferences: SharedPreferences

    fun init(context: Context){
        sharedPreferences = context.getSharedPreferences("calc", Context.MODE_PRIVATE)
    }


    override fun add(result: Result) {
        val history = sharedPreferences.getString("history", "[]")
        val jsonArray = JSONArray(history)
        jsonArray.put(toJson(result))
        val editor = sharedPreferences.edit()
        editor.putString("history", jsonArray.toString())
        editor.apply()
    }

    override fun getAll(): List<Result> {
        val json = sharedPreferences.getString("history", "[]")
        val resultList = mutableListOf<Result>()

        if (json != null) {
            val jsonArray = JSONArray(json)
            for (i in 0 until jsonArray.length()) {
                val result = fromJson(jsonArray.getString(i))
                resultList.add(result)
            }
        }
        return resultList
    }

    private fun toJson(result: Result): String {
        val json = JSONObject()
        json.put("input", result.input)
        json.put("output", result.output)
        return json.toString()
    }

    private fun fromJson(json: String): Result {
        val obj = JSONObject(json)
        val input = obj.getString("input")
        val output = obj.getString("output")
        return Result(input, output)
    }
}