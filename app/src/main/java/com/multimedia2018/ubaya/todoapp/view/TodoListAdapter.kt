package com.multimedia2018.ubaya.todoapp.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.multimedia2018.ubaya.todoapp.R
import com.multimedia2018.ubaya.todoapp.databinding.TodoItemLayoutBinding
import com.multimedia2018.ubaya.todoapp.model.Todo
import kotlinx.android.synthetic.main.todo_item_layout.view.*
import java.util.zip.Inflater

class TodoListAdapter(val todoList:ArrayList<Todo>,
                      val adapterOnClick: (Any) -> Unit) : RecyclerView.Adapter<TodoListAdapter.TodoListViewHolder>(),
                        TodoCheckedChangedListener, TodoEditClickListener{
    class TodoListViewHolder(var view: TodoItemLayoutBinding):RecyclerView.ViewHolder(view.root)

    fun updateTodoList(newTodoList:List<Todo>) {
        todoList.clear()
        todoList.addAll(newTodoList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoListViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        //val view = inflater.inflate(R.layout.todo_item_layout, parent, false)
        val view = DataBindingUtil.inflate<TodoItemLayoutBinding>(inflater, R.layout.todo_item_layout, parent, false)

        return TodoListViewHolder(view)
    }

    override fun onBindViewHolder(holder: TodoListViewHolder, position: Int) {
        //Jangan lupa untuk selalu meng-instantiate di sini dahulu!
        holder.view.todo = todoList[position]
        holder.view.listener = this
        holder.view.editlistener = this

        /*
        //Penulisan lama week 8 - 9, okay to delete, kept for reasons
        holder.view.checkTask.text = todoList[position].title + " [Priority: " + todoList[position].priority + "]"

        holder.view.imgEdit.setOnClickListener {
            val action = TodoListFragmentDirections.actionEditTodoFragment(todoList[position].uuid)
            Navigation.findNavController(it).navigate(action)
        }

        holder.view.checkTask.setOnCheckedChangeListener { compoundButton, isChecked ->
            if (isChecked)
            {
                //adapterOnClick(todoList[position]) //<== Ini week 8 - 9
                adapterOnClick(todoList[position].uuid) // Ini Post-Homework Week 9
            }
        }
        */
    }

    override fun getItemCount(): Int {
        return todoList.size
    }

    //Week 10
    override fun onTodoCheckedChanged(cb: CompoundButton, isChecked: Boolean, obj: Todo) {
        if (isChecked)
        {
            adapterOnClick(obj)
        }
    }

    override fun onTodoEditClick(v: View) {
        val action = TodoListFragmentDirections.actionEditTodoFragment(v.tag.toString().toInt())
        Navigation.findNavController(v).navigate(action)
    }
}