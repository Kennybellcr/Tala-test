package com.example.talatest.domain

import android.content.Context
import com.example.talatest.data.model.LoanModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.IOException

class Utils {
    companion object{
        fun readJsonFile(context: Context?,fileName: String):List<LoanModel>{
            lateinit var jsonString: String
            try {
                jsonString = context?.assets?.open(fileName)
                    ?.bufferedReader().use {
                        it?.readText() ?: ""
                    }
            } catch (ioException: IOException) {
               return emptyList()
            }

            val listLoanType = object : TypeToken<List<LoanModel>>() {}.type
            return Gson().fromJson(jsonString, listLoanType)
        }
    }
}