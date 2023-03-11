package com.example.vehiclemaintenancerebuild

import android.content.Context
import android.widget.ArrayAdapter

class DropDownAdapter<T>(
    context: Context,
    objects: List<T>
) : ArrayAdapter<T>(context, R.layout.menu_list_item, objects)
