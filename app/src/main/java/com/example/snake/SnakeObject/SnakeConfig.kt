package com.example.snake.SnakeObject

object SnakeConfig {
    var GRID_WIDTH: Float = 0F//每格的宽度 根据分辨率动态设置
    var GRID_HEIGHT: Float = 0F //每格的高度 根据分辨率动态设置

    val GAME_COLUMN_COUNT: Int = 20 //布局的列数
    val GAME_ROW_COUNT: Int = 20  //布局的行数

    var arrayRowX: FloatArray? = FloatArray(GAME_COLUMN_COUNT)//存放横向格子的X坐标的数组
    var arrayColumnY: FloatArray? = FloatArray(GAME_ROW_COUNT)//存放纵向格子Y坐标的数组


    /**
     * 设置横向位置 index 和x坐标
     */
    fun setSingleRowX(index: Int, x: Float? = 0f) {
        if (x == null || index == GAME_COLUMN_COUNT) return
        arrayRowX?.set(index, x)

    }

    /**
     * 设置纵向位置 index 和x坐标
     */
    fun setSingleColumnY(index: Int, y: Float? = 0f) {
        if (y == null || index == GAME_ROW_COUNT) return
        arrayColumnY?.set(index, y)
    }


    /**
     * 取横向位置坐标
     */
    fun getSingleRowX(index: Int): Float {
        return arrayRowX?.get(if (index < GAME_ROW_COUNT) index else GAME_ROW_COUNT - 1) ?: -1f
    }


    /**
     * 取纵向位置坐标
     */
    fun getSingleColumnY(index: Int): Float {
        return arrayColumnY?.get(if (index < GAME_COLUMN_COUNT) index else GAME_COLUMN_COUNT - 1)
                ?: -1f
    }


}
