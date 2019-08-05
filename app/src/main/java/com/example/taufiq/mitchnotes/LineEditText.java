package com.example.taufiq.mitchnotes;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.AppCompatEditText;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;

/**
 * Created By Taufiq on 8/2/2019.
 */
public class LineEditText extends EditText {

    private Rect mrect;
    private Paint paint;


    // we need this constructor for layout inflater
    public LineEditText(Context context, AttributeSet attrs) {
        super(context, attrs);

        mrect = new Rect();
        paint = new Paint();

        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(2);
        paint.setColor(0xFF00574B); // COLOR OF THE LINES
    }


    @Override
    protected void onDraw(Canvas canvas) {

        // Get the height of the View
        int height = ((View) this.getParent()).getHeight();

        int lineHeght = getLineHeight();
        int numberOfLines = height / lineHeght;


        Rect r = mrect;
        Paint mpaint = paint;

        int baseline = getLineBounds(0, r);

        for (int i = 0; i < numberOfLines; i++) {
            canvas.drawLine(r.left, baseline + 1, r.right, baseline + 1, mpaint);

            baseline+= lineHeght;
        }

        super.onDraw(canvas);

    }
}
