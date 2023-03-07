package com.example.vehiclemaintenancerebuild.recyclerview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.vehiclemaintenancerebuild.R
import com.example.vehiclemaintenancerebuild.database.Vehicle

class ItemAdapter : ListAdapter<Vehicle, ItemAdapter.ViewHolder>(VehicleDiffCallBack()) {

    private var listener: OnItemClickListener? = null

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    fun setClickListener(clickListener: OnItemClickListener) {
        listener = clickListener
    }

    class ViewHolder(private var context: Context, itemView: View, listener: OnItemClickListener?) :
        RecyclerView.ViewHolder(itemView) {
        private var mVehicleName: TextView = itemView.findViewById(R.id.recycler_view_card_layout_textViewName)
        private var mVehicleMake: TextView = itemView.findViewById(R.id.recycler_view_card_layout_textViewMake)
        private var mVehicleModel: TextView = itemView.findViewById(R.id.recycler_view_card_layout_textViewModel)
        private var mVehicleImage: ImageView = itemView.findViewById(R.id.recycler_view_card_layout_imageView)

        init {
            itemView.setOnClickListener {
                if (listener != null) {
                    val position = adapterPosition
                    if (position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(position)
                    }
                }
            }
        }

        fun bind(item: Vehicle) {
            mVehicleName.text = item.vehicleName
            mVehicleModel.text = item.vehicleModel

            Glide.with(this.context)
                .load(item.vehicleImageURI)
                .error(R.drawable.notavailableimage)
                .fallback(R.drawable.notavailableimage)
                .dontTransform()
                .into(mVehicleImage)
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.myvehicles_recycler_view_list_item, parent, false)
        return ViewHolder(parent.context, view, listener)
    }

    class VehicleDiffCallBack: DiffUtil.ItemCallback<Vehicle>(){
        override fun areItemsTheSame(oldItem: Vehicle, newItem: Vehicle): Boolean {
            return oldItem.uid == newItem.uid
        }

        override fun areContentsTheSame(oldItem: Vehicle, newItem: Vehicle): Boolean {
            return oldItem == newItem
        }

    }

}