package com.example.calculator.domain.api

import com.example.calculator.domain.Result

interface IResultRepository {
    fun add(result: Result)
    fun getAll(): List<Result>
}