package com.example.talatest.ui.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.talatest.R
import com.example.talatest.TalaApp
import com.example.talatest.databinding.FragmentLoginBinding
import com.example.talatest.ui.view.UtilsFragment
import com.example.talatest.ui.viewmodel.LoginViewModel

class LoginFragment : Fragment() {
    // View Binding
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    // ViewModel
    private val loginViewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        (activity?.application as TalaApp).appComponent.inject(this)

        loginViewModel.isValidUser.observe(this) {
            if (it) {
                val bundle = bundleOf("username" to binding.etUser.text.toString())
                findNavController().navigate(R.id.action_loginFragment_to_dashboardFragment,bundle)
            } else {
                UtilsFragment.showDialog("Error!", "Usuario incorrecto.",context, layoutInflater)
            }
        }

        loginViewModel.isLoading.observe(this) {
            binding.contProgress.isVisible = it
            binding.cardLogin.isVisible = !it
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentLoginBinding.inflate(inflater,container,false)
        binding.progressTala.startAnimation(UtilsFragment.rotateAnimation())
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnLogin.setOnClickListener { loginViewModel.validUser(context,binding.etUser.text.toString()) }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}