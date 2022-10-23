package com.example.snake

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.KeyEvent
import android.widget.Toast
import com.example.snake.R
import com.example.snake.SnakeObject.EnumMoveType
import com.example.snake.SnakeObject.SnakeConfig
import kotlinx.android.synthetic.main.activity_snake.*

//贪吃蛇游戏页面
class SnakeActivity : AppCompatActivity() {

    private var quitNum = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_snake)

        snake_view.initGameObj()

        initClick()

    }


    //点击事件
    private fun initClick() {
        //左
        toLeft.setOnClickListener { start(2) }
        //右
        toRight.setOnClickListener { start(4) }
        //上
        toTop.setOnClickListener { start(1) }
        //下
        toBottom.setOnClickListener { start(3) }

    }

    fun start(dir: Int) {
        if(Math.abs(dir - snake_view.moveTo) !== 2) snake_view.moveTo = dir
    }

    override fun onKeyUp(keyCode: Int, event: KeyEvent?): Boolean {
        // 按下返回键
        if(keyCode== KeyEvent.KEYCODE_BACK && quitNum === 1)    return super.onKeyUp(keyCode, event)
        else {
            quitNum += 1
            Toast.makeText(this,"再点击一次退出", Toast.LENGTH_SHORT).show()
            return true
        }
    }

}
