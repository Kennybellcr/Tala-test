package com.example.talatest.ui.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.talatest.R
import com.example.talatest.data.model.LoanModel
import com.example.talatest.databinding.AdapterLoansBinding
import com.example.talatest.ui.view.UtilsFragment
import com.example.talatest.ui.view.fragment.DashboardFragmentDirections

class LoansAdapter(private val loanList: List<LoanModel>): RecyclerView.Adapter<LoansAdapter.ViewHolder>(){



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(layoutInflater.inflate(R.layout.adapter_loans,parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item: LoanModel = loanList[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return loanList.size
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = AdapterLoansBinding.bind(view)
        fun bind(loan: LoanModel){
            binding.tvLoanTitle.text = "Loan " + loan.loan?.status
            binding.tvLoanInfo.text = setLoanInfo(loan)
            binding.imMedal.setImageResource(setImageMedal(loan.loan?.level))
            binding.cardLoanItem.setOnClickListener{
                val action = DashboardFragmentDirections.actionDashboardFragmentToDescriptionLoanFragment(loan)
                it.findNavController().navigate(action)
            }
        }

        private fun setLoanInfo(loan: LoanModel): String {
            return when (loan.loan?.status?.lowercase()){
                UtilsFragment.LoanStatus.PAID.value-> UtilsFragment.timeStampToDate(loan.timestamp.toString())
                UtilsFragment.LoanStatus.DUE.value-> UtilsFragment.formatAmount(loan.loan?.due)
                else-> UtilsFragment.formatAmount(loan.loan?.approved)
            }
        }

        private fun setImageMedal(level: String?): Int{
            return when (level?.uppercase()){
                UtilsFragment.LoanLevel.GOLD.value-> R.drawable.img_gold_badge_large
                UtilsFragment.LoanLevel.SILVER.value-> R.drawable.img_silver_badge_large
                else-> R.drawable.img_bronze_badge_large
            }
        }
    }


}