package com.example.vehiclemaintenancerebuild.maintenance

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.vehiclemaintenancerebuild.databinding.FragmentHomeBinding
import com.example.vehiclemaintenancerebuild.databinding.FragmentMaintenanceBinding

class MaintenanceFragment : Fragment() {

    private var _binding: FragmentMaintenanceBinding? = null
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
        _binding = FragmentMaintenanceBinding.inflate(inflater, container, false)
        return binding.root
    }
}