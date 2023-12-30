package com.example.calculator.domain

import com.example.calculator.domain.api.ICalculatorHistory
import com.example.calculator.domain.api.IResultRepository
import com.example.calculator.infrastructure.data.SharedHistory

class CalculatorHistory : ICalculatorHistory{

    private val repository: IResultRepository = SharedHistory
    override fun getHistory(): List<Result> {
        return repository.getAll()
    }

}