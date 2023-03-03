package com.example.vehiclemaintenancerebuild.myvehicles

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.vehiclemaintenancerebuild.databinding.FragmentHomeBinding
import com.example.vehiclemaintenancerebuild.databinding.FragmentMyvehiclesBinding

class MyVehiclesFragment : Fragment() {

    private var _binding: FragmentMyvehiclesBinding? = null
    private val binding get() = requireNotNull(_binding){
        "XML Input Fragment binding called when view not available"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMyvehiclesBinding.inflate(inflater, container, false)
        return binding.root
    }
}