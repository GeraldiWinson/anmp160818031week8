package com.multimedia2018.ubaya.todoapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.room.Room
import com.multimedia2018.ubaya.todoapp.model.Todo
import com.multimedia2018.ubaya.todoapp.model.TodoDatabase
import com.multimedia2018.ubaya.todoapp.util.buildDB
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class ListTodoViewModel(application: Application):AndroidViewModel(application), CoroutineScope {
    val todoLD = MutableLiveData<List<Todo>>()
    //val todoLoadErrorLD = MutableLiveData<Boolean>()
    //val loadingLD = MutableLiveData<Boolean>()

    fun refresh() {
        //loadingLD.value = true
        //todoLoadErrorLD.value = false
        launch {
            val db = Room.databaseBuilder(getApplication(),
                TodoDatabase::class.java, "tododb").build()
            todoLD.value = db.todoDao().selectAllTodo()
        }
    }

//Clear Task Week 8
//    fun clearTask(todo: Todo) {
//        launch {
//            val db = buildDB(getApplication())
//            db.todoDao().deleteTodo(todo)
//            todoLD.value = db.todoDao().selectAllTodo()
//        }
//    }

    //Clear Task Week 9, edited
    fun clearTask(uuid: Int) {
        launch {
            val db = buildDB(getApplication())
            db.todoDao().updateIsDone(uuid)
            todoLD.value = db.todoDao().selectAllTodo()
        }
    }

    private var job = Job()
    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main
}