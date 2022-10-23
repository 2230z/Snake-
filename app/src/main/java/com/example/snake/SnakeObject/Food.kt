package com.example.snake.SnakeObject

import android.graphics.Canvas
import android.graphics.Paint
import android.util.Log
import android.util.SparseBooleanArray
import android.util.SparseIntArray


class Food : GameModel() {

    var x:Float = -1F
    var y: Float = -1F
    private val PositionsNumbers: Int = SnakeConfig.GAME_COLUMN_COUNT * SnakeConfig.GAME_ROW_COUNT - 1  //方格总数

    var position: Int = -1//球所在的index

    //当碰到球之后 重置坐标
    @Synchronized
    fun onEatFood() {
        x = -1F
        y = -1F
    }


    //生成一个新的球
    @Synchronized
    fun randomFood(canvas: Canvas, paint: Paint, snakeList: List<Food>) {

        if (x >= 0 && y >= 0) {
            //没吃到之前 则还是保持原来的位置
            draw(canvas, x, y, paint)
            return
        }

        //最大的位置数
        //存放随机生成的位置数
        var newPosition: Int = -1

        val sparryList: SparseBooleanArray = createHash(snakeList)

        //如果不在蛇坐标集合中,或值为-1 ,则重新生成
        while (newPosition == -1 || sparryList.get(newPosition)) {
            //随机生成一个位置数
            newPosition = (Math.random() * PositionsNumbers).toInt()
        }

        position = newPosition

        x = SnakeConfig.getSingleRowX(newPosition % SnakeConfig.GAME_COLUMN_COUNT)//计算x的位置
        y = SnakeConfig.getSingleColumnY(newPosition / SnakeConfig.GAME_COLUMN_COUNT)//计算y的位置

        draw(canvas, x, y, paint)

    }

    // 构建蛇身的hash表,检查新生的食物是否和食物重合
    // tips: 一次遍历，构建数组，节省每次的坐标比较
    private fun createHash(List: List<Food>):  SparseBooleanArray{
        //使用SparseIntArray存放蛇身体的index 便于判断
        var sparryList = SparseBooleanArray()

        for (item in List) {
            sparryList.put(item.position, true)//随便放个值
        }
        return sparryList
    }

    private fun drawPos(pos: Float, operation: Int): Float {
        val s: Float = 0F
        if(operation === 1) return (pos+s).toFloat()
        return (pos-s).toFloat()

    }

    //画食物
    override fun draw(canvas: Canvas, x: Float, y: Float, paint: Paint) {
        val width = SnakeConfig.GRID_WIDTH
        val height = SnakeConfig.GRID_HEIGHT
        canvas.drawRect(drawPos(x,1),drawPos(y,1),drawPos(x+width,2),drawPos(y+height,2),paint)
    }

    fun setFoodInformation(x: Float, y: Float, pos: Int) {
        this.x = x
        this.y = y
        this.position = pos
    }

}