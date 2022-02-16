package com.example.peliculas.ui.coreM

import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**
 * se hara abstracto porque se podra usar donde quiera
 * y facilitara el uso de los adaptadores
 */

abstract class BaseViewHolder<T>(itemView: View): RecyclerView.ViewHolder(itemView) {
    abstract fun bind (item: T)
}