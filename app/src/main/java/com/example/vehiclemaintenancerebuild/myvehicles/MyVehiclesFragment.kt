package com.example.vehiclemaintenancerebuild.myvehicles

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.vehiclemaintenance.database.VehicleDatabase
import com.example.vehiclemaintenancerebuild.databinding.FragmentMyvehiclesBinding
import com.example.vehiclemaintenancerebuild.recyclerview.ItemAdapter
import com.example.vehiclemaintenancerebuild.recyclerview.SwipeToDeleteCallback
import com.example.vehiclemaintenancerebuild.viewmodels.MyVehiclesViewModel
import com.example.vehiclemaintenancerebuild.viewmodels.VehicleViewModelFactory

class MyVehiclesFragment : Fragment() {

    private var _binding: FragmentMyvehiclesBinding? = null
    private val binding get() = requireNotNull(_binding){
        "XML Input Fragment binding called when view not available"
    }
    private lateinit var vehicleViewModel: MyVehiclesViewModel
    private val adapter = ItemAdapter()

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val application = requireNotNull(this.activity).application
        val dataSource = VehicleDatabase.getInstance(application).vehicleDatabaseDao
        val viewModelFactory = VehicleViewModelFactory(dataSource, application)
        vehicleViewModel = ViewModelProvider(this, viewModelFactory)[MyVehiclesViewModel::class.java]

        val recyclerView = binding.myVehiclesRecyclerView
        recyclerView.adapter = adapter
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(context)

        observeVehiclesFromDatabase()

    }


    private fun setClickListeners() {

    }

    private fun observeVehiclesFromDatabase(){
        vehicleViewModel.vehiclesFromDatabase.observe(viewLifecycleOwner){ list ->
            list?.let {
                if (list.isNotEmpty()){
                    adapter.submitList(list)
                    binding.textViewNoItemsToDisplay.visibility = View.GONE
                    binding.myVehiclesRecyclerView.visibility = View.VISIBLE
                } else {
                    binding.textViewNoItemsToDisplay.visibility = View.VISIBLE
                    binding.myVehiclesRecyclerView.visibility = View.GONE
                }
                }
            }
        }

    private fun setUpSwipeToDelete(){
        val swipeToDeleteCallback = object: SwipeToDeleteCallback(){
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val builder = AlertDialog.Builder(context)
                builder.setTitle("Confirm Delete")
                builder.setMessage("Are you sure you want to delete this vehicle?")

                builder.setPositiveButton("Yes") { _: DialogInterface?, _: Int ->
                    val position = viewHolder.adapterPosition
                    val vehicleID = vehicleViewModel.vehiclesFromDatabase.value?.get(position)?.uid
                    if (vehicleID != null) {
                        vehicleViewModel.removeVehicleFromDatabase(vehicleID)
                    }
                    adapter.notifyItemRemoved(position)

                } .setNegativeButton("No") { _: DialogInterface?, _: Int ->
                    binding.myVehiclesRecyclerView.adapter?.notifyItemChanged(viewHolder.adapterPosition)
                }.create().show()
            }
        }
        val itemTouchHelper = ItemTouchHelper(swipeToDeleteCallback)
        itemTouchHelper.attachToRecyclerView(binding.myVehiclesRecyclerView)
    }
}