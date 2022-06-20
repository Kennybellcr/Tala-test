package com.example.talatest.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class LoanDescriptionModel(
    @SerializedName("status"  ) var status  : String? = null,
    @SerializedName("level"   ) var level   : String? = null,
    @SerializedName("due"     ) var due     : Int?    = null,
    @SerializedName("approved") var approved: Int?    = null,
    @SerializedName("dueDate" ) var dueDate : String?    = null
) : Parcelable