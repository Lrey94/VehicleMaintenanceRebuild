package com.example.vehiclemaintenancerebuild.myvehicles

import android.app.Dialog
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.vehiclemaintenance.database.Vehicle
import com.example.vehiclemaintenance.database.VehicleDatabase
import com.example.vehiclemaintenancerebuild.DropDownAdapter
import com.example.vehiclemaintenancerebuild.R
import com.example.vehiclemaintenancerebuild.databinding.AddNewVehicleDialogFullScreenBinding
import com.example.vehiclemaintenancerebuild.dataclass.VehicleManufacturerListConstants
import com.example.vehiclemaintenancerebuild.viewmodels.MyVehiclesViewModel
import com.example.vehiclemaintenancerebuild.viewmodels.VehicleViewModelFactory
import com.google.android.material.snackbar.Snackbar

class AddNewVehicleDialogFragment : DialogFragment() {

    private var _binding: AddNewVehicleDialogFullScreenBinding? = null
    private val binding get() = requireNotNull(_binding){
        "XML Input Fragment binding called when view not available"
    }
    private val vehicleChoices = listOf("Car", "Motorcycle")
    private val vehicleMakeChoices = VehicleManufacturerListConstants.ManufacturerList.manufacturerListObject

    private var selectedVehicle = ""
    private var selectedVehicleMake = ""
    private var imageStateUri: Uri? = null
    private lateinit var vehicleViewModel: MyVehiclesViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = AddNewVehicleDialogFullScreenBinding.inflate(inflater, container, false)

        val application = requireNotNull(this.activity).application
        val dataSource = VehicleDatabase.getInstance(application).vehicleDatabaseDao
        val viewModelFactory = VehicleViewModelFactory(dataSource, application)
        vehicleViewModel = ViewModelProvider(this, viewModelFactory)[MyVehiclesViewModel::class.java]
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setClickListeners()

        val textInputAdapterVehicleType = DropDownAdapter(requireContext(), vehicleChoices)
        binding.inputExposedDropdownMenuTextInputVehicleType.setAdapter(textInputAdapterVehicleType)
        binding.inputExposedDropdownMenuTextInputVehicleType.onItemClickListener =
            AdapterView.OnItemClickListener {_, _, position, _ ->
                selectedVehicle = vehicleChoices[position]
            }

        val textInputAdapterVehicleMake = DropDownAdapter(requireContext(), vehicleMakeChoices)
        binding.inputExposedDropdownMenuTextInputVehicleMake.setAdapter(textInputAdapterVehicleMake)
        binding.inputExposedDropdownMenuTextInputVehicleMake.onItemClickListener =
            AdapterView.OnItemClickListener {_, _, position, _ ->
                selectedVehicleMake = vehicleMakeChoices[position]
            }
    }

    private fun saveVehicleToDatabase(vehicle: Vehicle) {
        if (validateAllFields()){
            vehicleViewModel.addVehicleToDatabase(vehicle)
        }
    }

    private fun setClickListeners(){
        binding.addVehicleFragmentCloseButton.setOnClickListener{
            findNavController().navigate(R.id.action_addNewVehicleFragment_to_mainFragment)
        }

        binding.addVehicleFragmentUploadImageButton.setOnClickListener {
            selectImageLauncher.launch("image/*")
        }

        binding.addVehicleFragmentSaveVehicleButton.setOnClickListener {
            saveVehicleToDatabase(createVehicleForDatabase())
            Thread.sleep(200)
            if (validateAllFields()) {
                view?.findNavController()?.navigate(R.id.action_addNewVehicleFragment_to_mainFragment)
            }
        }
    }

    private fun validateAllFields(): Boolean{
        return if (binding.inputEditTextVehicleName.text.isNullOrEmpty()
            || binding.inputExposedDropdownMenuTextInputVehicleType.text.isNullOrEmpty()
            || binding.inputExposedDropdownMenuTextInputVehicleMake.text.isNullOrEmpty()
            || binding.inputEditTextVehicleModel.text.isNullOrEmpty()
            || binding.inputEditTextVehicleMileage.text.isNullOrEmpty()
        ){
            binding.inputEditTextVehicleName.setBackgroundResource(R.drawable.error_background)
            binding.inputExposedDropdownMenuTextInputVehicleType.setBackgroundResource(R.drawable.error_background)
            binding.inputExposedDropdownMenuTextInputVehicleMake.setBackgroundResource(R.drawable.error_background)
            binding.inputEditTextVehicleModel.setBackgroundResource(R.drawable.error_background)
            binding.inputEditTextVehicleMileage.setBackgroundResource(R.drawable.error_background)
            Snackbar.make(
                requireActivity().findViewById(android.R.id.content),
                getString(R.string.not_all_details_entered),
                Snackbar.LENGTH_SHORT).show()
            false
        } else {
            true
        }
    }

    private fun createVehicleForDatabase(): Vehicle {
        // service and insurance date and picture set to null as they're required by the Vehicle
        // Entity however they are not officially added here but rather are added in the
        // maintenance fragment.
        val serviceDate: String = ""
        val insuranceDate: String = ""
        val vehicleNameString = binding.inputEditTextVehicleName.text.toString()
        val vehicleMakeString = selectedVehicleMake
        val vehicleModelString = binding.inputEditTextVehicleModel.text.toString()
        val vehicleType = selectedVehicle
        val vehicleMileageString = binding.inputEditTextVehicleMileage.text.toString()
        var vehicleImage: String = ""
        vehicleImage = if (imageStateUri != null){
            imageStateUri.toString()
        } else {
            ""
        }


        return Vehicle(0, vehicleNameString, vehicleMakeString, vehicleModelString, vehicleType,
            vehicleMileageString, vehicleImage, serviceDate, insuranceDate)
    }

    private val selectImageLauncher =
        registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
            imageStateUri = uri
            setImageOntoImageView()
        }

    private fun setImageOntoImageView() {
        if (imageStateUri != null) {
            binding.addVehicleFragmentUploadImageButton.visibility = View.GONE
            binding.addVehicleTopImageView.visibility = View.VISIBLE
            Glide.with(requireContext())
                .load(imageStateUri!!)
                .fallback(R.drawable.notavailableimage)
                .error(R.drawable.notavailableimage)
                .dontTransform()
                .into(binding.addVehicleTopImageView)
        }
    }

}