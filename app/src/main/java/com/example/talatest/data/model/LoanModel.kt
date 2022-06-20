package com.example.talatest.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class LoanModel (
    @SerializedName("locale"    ) var locale    : String? = null,
    @SerializedName("loan"      ) var loan      : LoanDescriptionModel?   = LoanDescriptionModel(),
    @SerializedName("timestamp" ) var timestamp : String?    = null,
    @SerializedName("username"  ) var username  : String? = null
): Parcelable