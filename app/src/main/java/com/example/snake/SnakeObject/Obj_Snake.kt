package com.example.snake.SnakeObject

import android.content.Intent
import android.graphics.Canvas
import android.graphics.Paint
import android.support.v4.content.ContextCompat.startActivity
import com.example.snake.OverActivity
import java.util.*
import kotlin.collections.ArrayList


/**
 * 因为蛇的组成是块状的，此对象就是组成蛇的块集合
 * */
class Obj_Snake : GameModel {

    var FirstLength = 5//初始的长度

    val maxX: Float = 54F * SnakeConfig.GAME_ROW_COUNT
    val maxY: Float = 54F * SnakeConfig.GAME_COLUMN_COUNT
    /**
     * 移动方向
     *  1  向上
     *  2  向左
     *  3  向下
     *  4  向右
     */
    var direction: Int = 2;

    //蛇身体的位置集合
    private var snakeBodys: ArrayList<Food> = ArrayList<Food>(0)

    private var scores: Int = 0 // 分数


    constructor() {
        // 蛇的初始化，初始长度为 5
//        if (snakeBodys == null) snakeBodys = ArrayList()

        var pos: Int = -1

        //todo 第一步 构建一条皮皮蛇
        for (i in FirstLength downTo 1) {
            val food: Food = Food()
            if(pos < 0) {
                pos = (0..10086).random()   // 行数随机
            }else pos++
            food.setFoodInformation(i*54.0F,0F,pos)
            snakeBodys.add(food)
        }

    }

    //获取蛇的肉体
    fun getPositionList(): ArrayList<Food> {
        if (snakeBodys== null)
            snakeBodys = ArrayList()
        return snakeBodys
    }


    //画蛇
    fun drawSnake(canvas: Canvas, paint: Paint) {
        val s: Float = 0F
        snakeBodys.forEach {
//            it.draw(canvas,it.x,it.y,paint)
            canvas.drawRect(it.x+s,it.y+s,it.x-s+SnakeConfig.GRID_WIDTH
                ,it.y+s+SnakeConfig.GRID_HEIGHT,paint)
        }
    }



    /**
     * 检查是否撞墙
     */
    fun isCrashWall(): Boolean {

        val first: Food = snakeBodys.get(0)
        if(first.x < 0 || first.x > maxX || first.y < 0 || first.y > maxY ) return true
        return false

    }

    /**
     * 检查是否碰到了自己
     * 只要碰到除了第二个位置以外的身体就算吃到自己(第三个位置也碰不到 不过无所谓)
     */
    fun checkEatMySelf(): Boolean {

        val first: Food = snakeBodys.get(0)
        val len: Int = snakeBodys.size
        for(i in 1 until len) {
            if(first.x === snakeBodys.get(i).x && first.y === snakeBodys.get(i).y)  return true
        }
        return false
    }

    fun eatFood(food: Food): Boolean {
        val first: Food = snakeBodys[0]

        if(food.x === first.x && food.y === first.y)  return true
        return false
    }


}