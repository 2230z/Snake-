package com.example.snake

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.widget.Toast
import com.example.snake.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var quitNum = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        startButton.setOnClickListener {
            // 显示跳转
            val intent = Intent(this,SnakeActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onKeyUp(keyCode: Int, event: KeyEvent?): Boolean {
        // 按下返回键
        if(keyCode== KeyEvent.KEYCODE_BACK && quitNum === 1)
            return super.onKeyUp(keyCode, event)
        else {
            quitNum += 1
            Toast.makeText(this,"再点击一次退出", Toast.LENGTH_SHORT).show()
            return true
        }
    }


}
