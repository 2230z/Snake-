package com.example.snake

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import com.example.snake.SnakeObject.SnakeConfig

class GridView: View{

    constructor(context: Context) : this(context, null) {

    }


    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {

    }

    val paint: Paint = Paint().apply() {}

    override fun draw(canvas: Canvas?) {
        super.draw(canvas)
        countGrids(canvas)
    }

    // 计算相应的网格信息
    private fun countGrids(canvas: Canvas?) {
        var Width: Float = 0F
        var Height: Float = 0F

        Width = (canvas!!.width / SnakeConfig.GAME_COLUMN_COUNT).toFloat()
        Height = Width
        // 记录每一个方格的大小
        SnakeConfig.GRID_WIDTH = Width
        SnakeConfig.GRID_HEIGHT = Height

        //根据行数画对应数量的横线 底部需要画一条 所以加1
        for (i in 0 until SnakeConfig.GAME_COLUMN_COUNT) {
            //把一列中每一格的y坐标存起来
            SnakeConfig.setSingleColumnY(i, i * Height)
        }

        //根据列数画对应数量的竖线
        for (i in 0 until SnakeConfig.GAME_ROW_COUNT) {
            //把一行中每一格的x坐标存起来
            SnakeConfig.setSingleRowX(i, i * Width)
        }

    }

}