package com.example.talatest.ui.view

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.animation.Animation
import android.view.animation.LinearInterpolator
import android.view.animation.RotateAnimation
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.example.talatest.R
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.*


class UtilsFragment{

    enum class LoanStatus(val value: String) {
        PAID("paid"),
        DUE("due"),
        APPROVED("approved")
    }

    enum class LoanLevel(val value: String) {
        SILVER("SILVER"),
        GOLD("GOLD"),
        BRONZE("BRONZE")
    }

    companion object{
        fun rotateAnimation(): Animation {
            val rotate = RotateAnimation(0f, 360f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f)
            rotate.duration = 2000
            rotate.interpolator = LinearInterpolator()
            rotate.repeatCount = 1000
            return rotate
        }

        fun showDialog(title: String,message: String,context: Context?,layoutInflater: LayoutInflater){
            val alert = AlertDialog.Builder(context, R.style.CustomAlertDialog).create()
            val alertCustom = layoutInflater.inflate(R.layout.custom_dialog,null)
            val btnCancel = alertCustom.findViewById<ImageView>(R.id.btn_cancel)
            val tvTitle = alertCustom.findViewById<TextView>(R.id.tv_title)
            val tvMessage = alertCustom.findViewById<TextView>(R.id.tv_message)
            val btnOk = alertCustom.findViewById<Button>(R.id.btn_ok)
            alert.setView(alertCustom)
            btnCancel.setOnClickListener { alert.dismiss() }
            btnOk.setOnClickListener { alert.dismiss() }
            tvTitle.text = title
            tvMessage.text = message
            alert.setCanceledOnTouchOutside(false)
            alert.show()
        }

        fun formatAmount(amount: Int?): String {
            val dec = DecimalFormat("###,###.00")
            return dec.format(amount)
        }

        fun timeStampToDate(timeStamp: String): String{
            val timeD = Date(timeStamp.toLong())
            val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
            return sdf.format(timeD)
        }
    }
}