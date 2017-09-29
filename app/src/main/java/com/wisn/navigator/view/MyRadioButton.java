package com.wisn.navigator.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.RadioButton;

import com.wisn.navigator.R;

/**
 * Created by wisn on 2017/9/12.
 */

public class MyRadioButton extends RadioButton {
    private int mDrawableSize;
    private int tipTextSize;
    private int tipRedius;
    private int tipCircleRedius;
    private int tipRediusMarginTop;
    private int tipRediusMarginRight;
    private Paint mPaint;
    private Rect mRect;
    private String textMsg=null;
    private boolean isTip=false;
    public MyRadioButton(Context context) {
        super(context);
    }

    public MyRadioButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public MyRadioButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    public void init(Context context, AttributeSet attrs) {
        Log.e("MyRadioButton", "init");
        mPaint = new Paint();
        mRect = new Rect();
        Drawable drawableLeft = null, drawableTop = null, drawableRight = null, drawableBottom = null;
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.ButtonView);
        mDrawableSize = a.getDimensionPixelSize(R.styleable.ButtonView_drawableSize, 50);
        tipCircleRedius = a.getDimensionPixelSize(R.styleable.ButtonView_tipCircleRedius, 10);
        tipTextSize = a.getDimensionPixelSize(R.styleable.ButtonView_tipTextSize, 10);
        tipRedius = a.getDimensionPixelSize(R.styleable.ButtonView_tipRedius, 10);
        tipRediusMarginTop = a.getDimensionPixelSize(R.styleable.ButtonView_tipRediusMarginTop, 10);
        tipRediusMarginRight = a.getDimensionPixelSize(R.styleable.ButtonView_tipRediusMarginRight, 10);
        textMsg = a.getString(R.styleable.ButtonView_tipText);
        drawableTop= a.getDrawable(R.styleable.ButtonView_drawableTop);
        drawableBottom= a.getDrawable(R.styleable.ButtonView_drawableBottom);
        drawableRight= a.getDrawable(R.styleable.ButtonView_drawableRight);
        drawableLeft= a.getDrawable(R.styleable.ButtonView_drawableLeft);
        a.recycle();
        setCompoundDrawablesWithIntrinsicBounds(drawableLeft, drawableTop, drawableRight, drawableBottom);
    }

    /**
     * RadioButton上、下、左、右设置图标
     */
    public void setCompoundDrawablesWithIntrinsicBounds(Drawable left,
                                                        Drawable top,
                                                        Drawable right,
                                                        Drawable bottom) {
        if (left != null)left.setBounds(0, 0, mDrawableSize, mDrawableSize);
        if (right != null) right.setBounds(0, 0, mDrawableSize, mDrawableSize);
        if (top != null) top.setBounds(0, 0, mDrawableSize, mDrawableSize);
        if (bottom != null)bottom.setBounds(0, 0, mDrawableSize, mDrawableSize);
        setCompoundDrawables(left, top, right, bottom);
        Log.e("MyRadioButton", "setCompoundDrawablesWithIntrinsicBounds");
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width = getMeasuredWidth();
       /* Log.e("MyRadioButton", "mDrawableSize:" +
                               mDrawableSize +
                               " tipCircleRedius:"
                               +
                               tipCircleRedius +
                               " tipTextSize:"
                               +
                               tipTextSize +
                               " getMeasuredWidth():" +
                               getMeasuredWidth()
                               +
                               "  getMeasuredHeight:" +
                               getMeasuredHeight() +
                               "  getWidth:" +
                               getWidth() +
                               "  getHeight:" +
                               getHeight());*/


        /*if(textMsg!=null){
            mPaint.setColor(Color.RED);
            canvas.drawCircle((width + mDrawableSize) / 2, height / 5, tipCircleRedius, mPaint);
            mPaint.setColor(Color.YELLOW);
            mPaint.getTextBounds(textMsg, 0, textMsg.length(), mRect);
            mPaint.setTextSize(tipTextSize);
            canvas.drawText(textMsg,
                            (width + mDrawableSize - mRect.width()) / 2,
                            height / 5 + mRect.height() / 2,
                            mPaint);
        }else if(isTip){
            mPaint.setColor(Color.RED);
            canvas.drawCircle((width + mDrawableSize) / 2, height / 5, tipRedius, mPaint);
        }*/
        if(textMsg!=null){
            mPaint.setColor(Color.RED);
            canvas.drawCircle(width - tipCircleRedius*2-tipRediusMarginRight , tipRediusMarginTop+tipCircleRedius, tipCircleRedius, mPaint);
            mPaint.setColor(Color.YELLOW);
            mPaint.getTextBounds(textMsg, 0, textMsg.length(), mRect);
            mPaint.setTextSize(tipTextSize);
            canvas.drawText(textMsg,
                            width - tipCircleRedius*2-tipRediusMarginRight-mRect.width()/2,
                            tipRediusMarginTop+tipCircleRedius+ mRect.height() / 2,
                            mPaint);
        }else if(isTip){
            mPaint.setColor(Color.RED);
            canvas.drawCircle(width - tipCircleRedius*2-tipRediusMarginRight, tipRediusMarginTop+tipCircleRedius, tipRedius, mPaint);
        }

    }

    public void setTipText(String text){
        this.textMsg=text;
        isTip=false;
        invalidate();
    }
    public void clearTip(){
        this.textMsg=null;
        isTip=false;
        invalidate();
    }
    public void setTip(){
        isTip=true;
        this.textMsg=null;
        invalidate();
    }

}
