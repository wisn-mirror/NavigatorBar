package com.wisn.navigator.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.util.Log;
import android.view.ViewTreeObserver;
import android.widget.RadioButton;

import com.wisn.navigator.R;

/**
 * Created by wisn on 2017/9/12.
 */
public class MyRadioButton extends RadioButton {
    private int mDrawableSize;
    private int tipTextSize;
    private int tipRedius;
    private int tipTextRedius;
    private int tipRediusMarginTop;
    private int tipRediusMarginRight;
    private int tipTextColor;
    private int tipBackground;
    private Paint mPaint;
    private Rect mRect;
    private String textMsg=null;
    private boolean isTip=false;
    private Drawable mDrawableLeft;
    private Drawable mDrawableTop;
    private Drawable mDrawableRight;
    private Drawable mDrawableBottom;
    private boolean isDraw=false;

    public MyRadioButton(Context context) {
        super(context);
    }

    public MyRadioButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        Log.e("MyRadioButton", "init222222");
        init(context, attrs);
    }

    public MyRadioButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        Log.e("MyRadioButton", "init33333");
        init(context, attrs);

    }


    public void init(Context context, AttributeSet attrs) {
        Log.e("MyRadioButton", "init");
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.ButtonView);
        mDrawableSize = a.getDimensionPixelSize(R.styleable.ButtonView_drawableSize, 50);
        tipTextRedius = a.getDimensionPixelSize(R.styleable.ButtonView_tipTextRedius, 10);
        tipTextSize = a.getDimensionPixelSize(R.styleable.ButtonView_tipTextSize, 10);
        tipRedius = a.getDimensionPixelSize(R.styleable.ButtonView_tipRedius, 10);
        tipRediusMarginTop = a.getDimensionPixelSize(R.styleable.ButtonView_tipRediusMarginTop, 10);
        tipRediusMarginRight = a.getDimensionPixelSize(R.styleable.ButtonView_tipRediusMarginRight, 10);
        tipTextColor = a.getColor(R.styleable.ButtonView_tipTextColor,Color.WHITE);
        tipBackground = a.getColor(R.styleable.ButtonView_tipBackground, Color.RED);
        textMsg = a.getString(R.styleable.ButtonView_tipText);
        mDrawableTop = a.getDrawable(R.styleable.ButtonView_drawableTop);
        mDrawableBottom = a.getDrawable(R.styleable.ButtonView_drawableBottom);
        mDrawableRight = a.getDrawable(R.styleable.ButtonView_drawableRight);
        mDrawableLeft = a.getDrawable(R.styleable.ButtonView_drawableLeft);
        mPaint = new Paint();
        mRect = new Rect();
        a.recycle();
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
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        Log.e("MyRadioButton-->onLayout","changed:"+changed+" left:"+left+ " top:"+top+" right:"+right+" bottom:"+bottom);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        Log.e("MyRadioButton-->onMeasure","widthMeasureSpec:"+widthMeasureSpec+" heightMeasureSpec:"+heightMeasureSpec);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(!isDraw){
            setCompoundDrawablesWithIntrinsicBounds(mDrawableLeft, mDrawableTop, mDrawableRight, mDrawableBottom);
            isDraw=true;
        }
        if(mPaint==null)return ;
        int width = getMeasuredWidth();
        Log.e("MyRadioButton-->onDraw", "mDrawableSize:" +
                               mDrawableSize +
                               " tipTextRedius:"
                               +
                               tipTextRedius +
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
                               getHeight());
        if(textMsg!=null){
            mPaint.setColor(tipBackground);
            canvas.drawCircle(width - tipTextRedius*2-tipRediusMarginRight , tipRediusMarginTop+tipTextRedius, tipTextRedius, mPaint);
            mPaint.setColor(tipTextColor);
            mPaint.getTextBounds(textMsg, 0, textMsg.length(), mRect);
            mPaint.setTextSize(tipTextSize);
            canvas.drawText(textMsg,
                            width - tipTextRedius*2-tipRediusMarginRight-mRect.width()/2,
                            tipRediusMarginTop+tipTextRedius+ mRect.height() / 2,
                            mPaint);
        }else if(isTip){
            mPaint.setColor(tipBackground);
            canvas.drawCircle(width - tipTextRedius*2-tipRediusMarginRight, tipRediusMarginTop+tipTextRedius, tipRedius, mPaint);
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
