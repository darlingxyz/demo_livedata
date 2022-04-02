package com.example.demo_livedata

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.MutableLiveData
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {
    // 此处声明LiveData数据类变量——信鸽
    private val textLiveData = MutableLiveData(String())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 分别绑定对应布局控件
        val show = findViewById<TextView>(R.id.show)
        val buttonToWorld = findViewById<Button>(R.id.update_to_world)
        val buttonToTomorrow = findViewById<Button>(R.id.update_to_tomorrow)

        // 设置“明天按钮”的监听
        buttonToTomorrow.setOnClickListener {
            // 启动一个子线程
            thread {
                // 传递字符串给该LiveData
                textLiveData.postValue("你好，明天")
            }
        }

        // 设置“世界按钮”的监听
        buttonToWorld.setOnClickListener {
            // 启动一个子线程
            thread {
                // 传递字符串给该LiveData
                textLiveData.postValue("你好，世界")
            }
        }

        // 启动该LiveData的观察者——等鸽人
        textLiveData.observe(this,  { it ->
            it?.let {
               // 将新的LiveData数据设置为show文本控件的文字，显示出来
               show.text = it
            }
        })
    }
}
