package com.example.todolist

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity(), Listener {

    lateinit var db: ListDatabase
    var todoList: List<ListEntity> = mutableListOf<ListEntity>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        db = ListDatabase.getInstance(this)!!

        button_add_memo.setOnClickListener {
            val todoList: ListEntity = ListEntity(null, et_edit_memo.text.toString(), false)
            insertTodo(todoList)
        }
        getAll()
    }

    private fun insertTodo(listEntity: ListEntity) {
        GlobalScope.launch {
            val insert = withContext(Dispatchers.Default) {
                db.listDao().addItem(listEntity)
            }
            getAll()
        }
        et_edit_memo.setText("")
    }

    private fun getAll() {
        var list: List<ListEntity>? = null
        GlobalScope.launch(Dispatchers.Main) {
            val get = async(Dispatchers.Default) {
                list = db.listDao().getAllItem()
            }.await()
            setRecyclerView(list!!)
        }
    }

    private fun updateTodo(listEntity: ListEntity) {
        GlobalScope.launch {
            val update = withContext(Dispatchers.Default) {
                db.listDao().updateItem(listEntity)
            }
            getAll()
        }
    }

    private fun deleteTodo(listEntity: ListEntity) {
        GlobalScope.launch {
            val update = withContext(Dispatchers.Default) {
                db.listDao().deleteItem(listEntity)
            }
            getAll()
        }
    }

    override fun onFinishListener(listEntity: ListEntity) {
        listEntity.finish = !listEntity.finish
        updateTodo(listEntity)
    }

    override fun onDeleteListener(listEntity: ListEntity) {
        deleteTodo(listEntity)
        getAll()
    }

    private fun setRecyclerView(todoList:List<ListEntity>) {
        recycler_view.adapter = MyAdapter(this, todoList, this)
        recycler_view.layoutManager = LinearLayoutManager(this)
    }
}
