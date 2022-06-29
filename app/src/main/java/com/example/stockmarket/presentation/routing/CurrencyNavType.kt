package com.example.stockmarket.presentation.routing

import android.os.Bundle
import androidx.navigation.NavType
import com.example.stockmarket.domain.model.Currency
import com.google.gson.Gson

object CurrencyNavType : NavType<Currency>(false) {
    override fun get(bundle: Bundle, key: String): Currency? {
        return bundle.getParcelable(key)
    }

    override fun parseValue(value: String): Currency {
        return Gson().fromJson(value, Currency::class.java)
    }

    override fun put(bundle: Bundle, key: String, value: Currency) {
        bundle.putParcelable(key, value)
    }
}