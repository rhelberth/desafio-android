package com.picpay.desafio.android.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.picpay.desafio.android.R
import com.picpay.desafio.android.databinding.FragmentHomeBinding
import com.picpay.desafio.android.domain.model.User
import com.picpay.desafio.android.ui.ResultStatus
import com.picpay.desafio.android.ui.adapter.UserListAdapter
import com.picpay.desafio.android.ui.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val viewModel: HomeViewModel by viewModels()
    private lateinit var adapter: UserListAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initComponents()
        addObserver()

    }

    private fun initComponents() {
        adapter = UserListAdapter()
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())

    }

    private fun addObserver() {
        viewModel.resultStatus().observe(viewLifecycleOwner) { result ->
            if (result != null) {
                resultStatus(result)
            }
        }
    }

    private fun resultStatus(resultStatus: ResultStatus<List<User>>) {
        when (resultStatus) {
            is ResultStatus.Loading -> {
                binding.userListProgressBar.visibility = View.VISIBLE
                binding.recyclerView.visibility = View.GONE
            }
            is ResultStatus.Success -> {
                binding.userListProgressBar.visibility = View.GONE
                binding.recyclerView.visibility = View.VISIBLE
                adapter.users = resultStatus.data.toList()
            }
            is ResultStatus.Error -> {
                binding.userListProgressBar.visibility = View.GONE
                binding.recyclerView.visibility = View.GONE
                val message = getString(R.string.error)
                Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
            }
        }
    }
}
