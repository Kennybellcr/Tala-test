package com.example.talatest.ui.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.FragmentFactory
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.talatest.R
import com.example.talatest.TalaApp
import com.example.talatest.data.model.LoanModel
import com.example.talatest.databinding.FragmentDashboardBinding
import com.example.talatest.di.ViewModelFactory
import com.example.talatest.ui.view.UtilsFragment
import com.example.talatest.ui.view.adapter.LoansAdapter
import com.example.talatest.ui.viewmodel.LoanViewModel
import javax.inject.Inject

class DashboardFragment : Fragment() {
    // View Binding
    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!
    // View Factory
    @Inject lateinit var viewModelFactory: ViewModelFactory
    // ViewModel
    private lateinit var loanViewModel: LoanViewModel

    private var userName: String? = null
    private var chipSelected: String = UtilsFragment.LoanStatus.PAID.value

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity?.application as TalaApp).appComponent.inject(this)
        loanViewModel = ViewModelProvider(this,viewModelFactory)[LoanViewModel::class.java]
        userName = arguments?.getString("username")
        loanViewModel.isLoading.observe(this) {
            binding.contProgress.isVisible = it
        }
        loanViewModel.filterLoans(context,UtilsFragment.LoanStatus.PAID.value,userName)

        loanViewModel.loansList.observe(this) {
            setUpAdapter(it)
        }
    }

    override fun onResume() {
        super.onResume()
        loanViewModel.filterLoans(context,chipSelected,userName)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        _binding = FragmentDashboardBinding.inflate(inflater,container,false)

        binding.progressTala.startAnimation(UtilsFragment.rotateAnimation())
        binding.chipDue.setOnClickListener {
            loanViewModel.filterLoans(context, UtilsFragment.LoanStatus.DUE.value, userName)
            chipSelected = UtilsFragment.LoanStatus.DUE.value
        }
        binding.chipPaid.setOnClickListener {
            loanViewModel.filterLoans(context, UtilsFragment.LoanStatus.PAID.value, userName)
            chipSelected = UtilsFragment.LoanStatus.PAID.value
        }
        binding.chipAproved.setOnClickListener {
            loanViewModel.filterLoans(context, UtilsFragment.LoanStatus.APPROVED.value, userName)
            chipSelected = UtilsFragment.LoanStatus.APPROVED.value
        }
        binding.tvWelcome.text = getString(R.string.tv_welcome,userName)

        return binding.root
    }

    private fun setUpAdapter(loanList: List<LoanModel>){
        val recyclerview = binding.rcvLoans
        recyclerview.layoutManager = LinearLayoutManager(context)
        // This will pass the ArrayList to our Adapter
        val adapter = LoansAdapter(loanList)
        // Setting the Adapter with the recyclerview
        recyclerview.adapter = adapter

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}