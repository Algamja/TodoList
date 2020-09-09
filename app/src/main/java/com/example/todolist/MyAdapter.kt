package com.example.todolist

import android.content.Context
import android.graphics.Color
import android.graphics.Paint
import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_list.view.*

class MyAdapter(
    val context: Context,
    val listTodo: List<ListEntity>,
    val listener: Listener
) :
    RecyclerView.Adapter<MyAdapter.MyViewHolder>() {
    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val todo: TextView = itemView.todo_list
        val root: ConstraintLayout = itemView.root
        val check:ImageView = itemView.img_check
        val delete:ImageView = itemView.img_close
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_list, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listTodo.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.todo.text = listTodo[position].item
        if(listTodo[position].finish){
            holder.check.setImageResource(R.drawable.ic_check_black_24dp)
            holder.todo.setTextColor(Color.GRAY)
            holder.todo.setPaintFlags(holder.todo.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG)
        }else{
            holder.check.setImageResource(R.drawable.ic_crop_square_black_24dp)
            holder.todo.setTextColor(Color.BLACK)
            holder.todo.setPaintFlags(holder.todo.paintFlags or 0)
        }

        holder.root.setOnLongClickListener {
            listener.onFinishListener(listTodo[position])
            true
        }

        holder.delete.setOnClickListener {
            listener.onDeleteListener(listTodo[position])
        }
    }
}