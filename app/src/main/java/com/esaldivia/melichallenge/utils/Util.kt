package com.esaldivia.melichallenge.utils

import java.text.NumberFormat
import java.util.*

object Util{
    fun getCurrencyFormat(currencyID: String = "ARS"): NumberFormat {
        val currencyFormat = NumberFormat.getCurrencyInstance()
        currencyFormat.currency = Currency.getInstance(currencyID)
        currencyFormat.maximumFractionDigits = 0

        return currencyFormat
    }
}