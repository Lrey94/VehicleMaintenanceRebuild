package com.example.vehiclemaintenancerebuild.myvehicles

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.vehiclemaintenancerebuild.databinding.FragmentAddNewVehicleBinding

class AddNewVehicleFragment : Fragment() {

    private var _binding: FragmentAddNewVehicleBinding? = null
    private val binding get() = requireNotNull(_binding){
        "XML Input Fragment binding called when view not available"
    }
    private val vehicleChoices = arrayOf("Car", "Motorcycle")
    private var selectedVehicle = ""
    private var imageStateUri: Uri? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}