package com.example.talatest.ui.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.talatest.data.model.LoanModel
import com.example.talatest.databinding.FragmentDescriptionLoanBinding
import com.example.talatest.ui.view.UtilsFragment

class DescriptionLoanFragment : Fragment() {

    // View Binding
    private var _binding: FragmentDescriptionLoanBinding? = null
    private val binding get() = _binding!!

    private var loanParcel: LoanModel? = null
    private val args: DescriptionLoanFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loanParcel = args.loanParcelable
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentDescriptionLoanBinding.inflate(inflater,container,false)
        binding.tvStatus.text = loanParcel?.loan?.status
        binding.tvLevel.text = loanParcel?.loan?.level
        binding.tvCreationDate.text = UtilsFragment.timeStampToDate(loanParcel?.timestamp.toString())
        binding.tvLocale.text = loanParcel?.locale

        when {
            loanParcel?.loan?.due != null -> {
                binding.tvAmount.text = UtilsFragment.formatAmount(loanParcel?.loan?.due)
            }
            loanParcel?.loan?.approved != null -> {
                binding.tvAmount.text = UtilsFragment.formatAmount(loanParcel?.loan?.approved)
            }
            else -> {
                binding.lyAmount.isVisible = false
            }
        }

        if (loanParcel?.loan?.dueDate != null){
            binding.tvDueDate.text = UtilsFragment.timeStampToDate(loanParcel?.loan?.dueDate.toString())
        }else{
            binding.lyDueDate.isVisible = false
        }
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}