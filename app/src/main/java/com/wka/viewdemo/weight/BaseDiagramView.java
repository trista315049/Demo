package com.wka.viewdemo.weight;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;

import com.wka.viewdemo.R;

/**
 * Created by  trista on 2017/9/7.
 */

public  class BaseDiagramView extends View {
//    起始点
    private int oPointx =    100;
    private int oPointY = 800;
    //视图宽高
    private int width;
    private int hight;
    private Paint paint;

    private String titleName,xTextName,yTextName ;
    private int titleSize,xTextSize,yTextSize;
    private int titleColor,xTextColor,yTextColor;


    public BaseDiagramView(Context context) {
        this(context,null);
    }

    public BaseDiagramView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,-1);
    }

    public BaseDiagramView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        initPaint();
        getAttrs(context, attrs);
    }

    private void getAttrs(Context context, AttributeSet attrs) {
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.DiagramView);
        titleName = ta.getString(R.styleable.DiagramView_titleName);
        titleSize = ta.getInt(R.styleable.DiagramView_titleSize, 0);
        titleColor = ta.getInt(R.styleable.DiagramView_titleColor, 0);
        xTextName = ta.getString(R.styleable.DiagramView_xTextName);
        xTextSize = ta.getInt(R.styleable.DiagramView_xTextSize, 0);
        xTextColor = ta.getInt(R.styleable.DiagramView_xTextColor, 0);
        yTextName = ta.getString(R.styleable.DiagramView_yTextName);
        yTextSize = ta.getInt(R.styleable.DiagramView_yTextSize, 0);
        yTextColor = ta.getInt(R.styleable.DiagramView_yTextColor, 0);
        ta.recycle();

    }

    private void initPaint() {
        paint = new Paint();
        paint.setColor(getResources().getColor(R.color.text333));
        paint.setAntiAlias(true);
        paint.setDither(true);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        width = 800;
        hight = 800;

        drawX(paint,canvas);
        drawY(paint,canvas);
        drawTitle(paint,canvas);
        drawXKd(paint,canvas);
        drawYKd(paint,canvas);
        drawXjt(paint,canvas);
        drawYjt(paint,canvas);
        drawDiagram(paint,canvas);
        drawXvalue(paint,canvas);
        drawYvalue(paint,canvas);
    }
    /**
     * 画Y轴的文体
     * @param paint
     * @param canvas
     */
    private void drawYvalue(Paint paint, Canvas canvas) {
        paint.setTextSize(32);
        paint.setColor(Color.GRAY);
        paint.setFakeBoldText(true);
        float cellhight = hight/yTextSize;
        for (int i = 0; i < yTextSize-1; i++) {
            canvas.drawText((i+1)+"00",oPointx-60,oPointY-cellhight*(i+1)+10,paint);
        }
    }
    /**
     * 画X轴的文体
     * @param paint
     * @param canvas
     */
    private void drawXvalue(Paint paint, Canvas canvas) {
        paint.setTextSize(32);
        paint.setColor(Color.GRAY);
        paint.setFakeBoldText(true);
        float cellwidth = width/xTextSize;
        for (int i = 0; i < xTextSize-1; i++) {
            canvas.drawText((i+1)+"",cellwidth*(i+1)+oPointx-10,oPointY+60,paint);
        }
    }

    /**
     * 画图表
     * @param paint
     * @param canvas
     */
    protected  void drawDiagram(Paint paint, Canvas canvas){
//        1
        int colors[] = {Color.YELLOW,Color.GRAY,Color.RED,Color.LTGRAY,Color.DKGRAY,Color.YELLOW};
        int value[] = {200,350,500,150,400,240};
        paint.setAntiAlias(true);
        float cellwidth =  width/xTextSize;
        float cellhight = hight/yTextSize;
        for (int i = 0; i < xTextSize-1; i++) {
            paint.setColor( colors[i]);
            canvas.drawRect(oPointx+(cellwidth*(i+1)-cellwidth/2), oPointY-value[i], oPointx+(cellwidth*(i+1)+cellwidth/2), oPointY-2, paint);
        }
    }


    /**
     * 画y轴的箭头
     * @param paint
     * @param canvas
     */
    private void drawYjt(Paint paint, Canvas canvas) {
        paint.setStrokeWidth(5);
        Path path = new Path();
        path.moveTo(oPointx ,oPointY-hight-5);// 此点为多边形的起点
        path.lineTo(oPointx-10, oPointY-hight+20);
        path.lineTo(oPointx +10, oPointY-hight+20);

        path.close(); // 使这些点构成封闭的多边形
        canvas.drawPath(path, paint);
        paint.setStrokeWidth(20);
        canvas.drawText(yTextName,oPointx+50,oPointY-hight+50,paint);
    }

    /**
     * 画x轴的箭头
     * @param paint
     * @param canvas
     */
    private void drawXjt(Paint paint, Canvas canvas) {
        paint.setStrokeWidth(5);

        Path path = new Path();
        path.moveTo(oPointx+width,oPointY);// 此点为多边形的起点
        path.lineTo(oPointx+width-20, oPointY+10);
        path.lineTo(oPointx+width-20, oPointY-10);

        path.close(); // 使这些点构成封闭的多边形
        canvas.drawPath(path, paint);

        paint.setStrokeWidth(20);
        canvas.drawText(xTextName,oPointx+width+10,oPointY+50,paint);

    }


    /**
     * 画Y轴的刻度
     * @param paint
     * @param canvas
     */
    protected  void drawYKd(Paint paint, Canvas canvas){
        paint.setStrokeWidth(5);
        float cellhight = hight/yTextSize;
        for (int i = 0; i < yTextSize-1; i++) {
            canvas.drawLine(oPointx,oPointY-cellhight*(i+1),oPointx+10,oPointY-cellhight*(i+1),paint);
        }
    }

    /**
     * 画x轴的刻度
     * @param paint
     * @param canvas
     */
    protected  void drawXKd(Paint paint, Canvas canvas){
        paint.setStrokeWidth(5);
        float cellwidth = width/xTextSize;
        for (int i = 0; i < xTextSize-1; i++) {
            canvas.drawLine(cellwidth*(i+1)+oPointx,oPointY,cellwidth*(i+1)+oPointx,oPointY-10,paint);
        }
    }


    /**
     * 话标题
     * @param paint
     * @param canvas
     */
    protected  void drawTitle(Paint paint, Canvas canvas){
        if (!TextUtils.isEmpty(titleName)){
            paint.setStrokeWidth(60);
            paint.setTextSize(40);
            paint.setColor(titleColor);
            paint.setFakeBoldText(true);
            canvas.drawText(titleName,(width/2)-(paint.measureText(titleName)/2)+30,oPointY + 150,paint);
        }
    }


    /**
     * 画y轴
     * @param paint
     * @param canvas
     */
    private void drawY(Paint paint, Canvas canvas) {
        paint.setStrokeWidth(5);
        canvas.drawLine(oPointx,oPointY,oPointx,oPointx-hight,paint);

    }

    /**
     * 画x轴
     * @param paint
     * @param canvas
     */
    private void drawX(Paint paint, Canvas canvas) {
        paint.setStrokeWidth(5);
        canvas.drawLine(oPointx,oPointY,oPointx+width,oPointY,paint);
    }
}
