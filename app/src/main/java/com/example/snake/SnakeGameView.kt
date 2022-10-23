package com.example.snake

import android.app.Activity
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.os.Handler
import android.support.v4.content.ContextCompat.startActivity
import android.support.v7.app.AlertDialog
import android.util.AttributeSet
import android.util.Log
import android.util.TypedValue
import android.view.View
import com.example.snake.SnakeObject.EnumMoveType
import com.example.snake.SnakeObject.Food
import com.example.snake.SnakeObject.Obj_Snake
import com.example.snake.SnakeObject.SnakeConfig
import com.taobao.tlog.adapter.AdapterForTLog.logw
import kotlinx.android.synthetic.main.activity_snake.view.*
import java.util.*
import kotlin.collections.ArrayList


class SnakeGameView : View {

    var scores: Int = 0

    //定义食物对象
    var mFood: Food? = null

    //定义蛇对象
    var mSnake: Obj_Snake? = null

    var moveTo: Int = 4

    private var gameOver: Boolean = false

    var foodPaint: Paint = Paint().apply {
        style = Paint.Style.FILL
        color = Color.parseColor("#585053")
    }

    var paint: Paint = Paint().apply {
        style = Paint.Style.FILL
        color = Color.parseColor("#e8257d")
    }



    private var mTime = 200//多久移动一次

    private var mRun = Handler()
    private var mRunnable: Runnable? = null



    constructor(context: Context) : this(context, null) {
    }


    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {

    }


    override fun onDraw(canvas: Canvas) {

        mSnake?.drawSnake(canvas, paint)

        mFood?.randomFood(canvas, foodPaint, mSnake!!.getPositionList())


    }




    /**
     * 手动初始化蛇和球
     */
    fun initGameObj() {

        if (mFood != null && mSnake != null) return

        mFood = Food()
        mSnake = Obj_Snake()

        initCycle()


    }


    private fun randomPosition(): Int{
        return (1..10086).random()
    }

    //主要方法 设置溜蛇的方向
    @Synchronized
    fun StartMove(direc: Int) {
        if(mSnake!!.isCrashWall() || mSnake!!.checkEatMySelf()){
            gameOver = true
            return
        }
        if(mSnake!!.eatFood(mFood!!)) {
            mSnake!!.getPositionList().add(0,mFood!!)
            mFood = Food()
            scores++
        }
        var body: ArrayList<Food> = mSnake!!.getPositionList()
        if(body.size === 0) return
        val head: Food = Food()
        body.removeAt(body.size-1)
        val first: Food = body[0]
        when(direc){
            /**
             * 移动方向
             *  1  向上
             *  2  向左
             *  3  向下
             *  4  向右
             */
            1 -> head.setFoodInformation(first.x,first.y-SnakeConfig.GRID_WIDTH,randomPosition())

            2 -> head.setFoodInformation(first.x-SnakeConfig.GRID_HEIGHT,first.y,randomPosition())

            3 -> head.setFoodInformation(first.x,first.y+SnakeConfig.GRID_HEIGHT,randomPosition())

            4 -> head.setFoodInformation(first.x+SnakeConfig.GRID_HEIGHT,first.y,randomPosition())
        }
        body.add(0,head)


        invalidate() // View重新绘制

    }


    //初始化轮训
    private fun initCycle() {

        mRunnable = Runnable {
            if (gameOver) {
                AlertDialog.Builder(context).apply {
                    setTitle("贪吃蛇大作战")
                    setMessage("你已死亡")
                    setCancelable(false)
                    setPositiveButton("确定") {
                        dialog,which -> {
                            val intent= Intent("com.example.snake.GameOver")
                            (context as Activity).startActivity(intent)
                            (context as Activity).finish()
                        }
                    }
                    show()
                }
                return@Runnable
            }
            mRun.postDelayed(mRunnable, mTime.toLong())
            StartMove(moveTo)
        }
        //开始循环
        mRun.post(mRunnable)


    }



}
