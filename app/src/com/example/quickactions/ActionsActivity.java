
package com.example.quickactions;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.AbsoluteLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

public class ActionsActivity extends Activity implements View.OnClickListener {
    private static final String EXTRA_WIDTH = "width";
    private static final String EXTRA_HEIGHT = "height";
    private static final String EXTRA_TOP = "top";
    private static final String EXTRA_LEFT = "left";

    @SuppressWarnings("deprecation")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.actions);

        // Set up handlers for all the buttons
        findViewById(R.id.one).setOnClickListener(this);
        findViewById(R.id.two).setOnClickListener(this);
        findViewById(R.id.three).setOnClickListener(this);
        findViewById(R.id.four).setOnClickListener(this);

        // Make sure that when the background is clicked, the activity goes away
        final AbsoluteLayout root = (AbsoluteLayout) findViewById(R.id.root);
        root.setOnClickListener(this);
        root.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                finish();
                return false;
            }
        });

        final LinearLayout buttons = (LinearLayout) findViewById(R.id.buttons);

        // Once layout is complete, adjust the positions of the buttons
        // accordingly
        root.post(new Runnable() {
            @Override
            public void run() {
                // Figure out the window offset
                Rect rect = new Rect();
                Window window = getWindow();
                window.getDecorView().getWindowVisibleDisplayFrame(rect);
                int statusBarHeight = rect.top;

                // Determine how tall the buttons are
                int buttonsHeight = buttons.getHeight();
                int buttonsWidth = buttons.getWidth();

                // Find the anchor position
                int left = getIntent().getIntExtra(EXTRA_LEFT, 0);
                int top = getIntent().getIntExtra(EXTRA_TOP, 0);
                int height = getIntent().getIntExtra(EXTRA_HEIGHT, 0);

                // Account for window placement
                top -= statusBarHeight;

                // Figure out the placement
                int newTop = top - buttonsHeight;
                if (newTop < 0) {
                    // Damn, off the top of the screen. Gotta go below...
                    newTop = top + height;
                }

                // Check left bounds
                int right = left + buttonsWidth;
                if (right > root.getWidth()) {
                    // Gotta scoot it to the right
                    left = root.getWidth() - buttonsWidth;
                }

                root.removeView(buttons);

                root.addView(buttons, new AbsoluteLayout.LayoutParams(
                        AbsoluteLayout.LayoutParams.WRAP_CONTENT,
                        AbsoluteLayout.LayoutParams.WRAP_CONTENT, left, newTop));
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.one:
                Toast.makeText(this, "One clicked", Toast.LENGTH_SHORT).show();
                break;

            case R.id.two:
                Toast.makeText(this, "Two clicked", Toast.LENGTH_SHORT).show();
                break;

            case R.id.three:
                Toast.makeText(this, "Three clicked", Toast.LENGTH_SHORT).show();
                break;

            case R.id.four:
                Toast.makeText(this, "Four clicked", Toast.LENGTH_SHORT).show();
                break;
        }

        // In this case, always finish whenever a button is pressed
        // Note: this also gets called the background is pressed
        finish();
    }

    /**
     * Show the buttons with respect to the passed-in anchor.
     * 
     * @param anchor the View to use to anchor the controls
     */
    public static void show(View anchor) {
        Context context = anchor.getContext();

        Intent intent = new Intent(context, ActionsActivity.class);

        int[] pos = new int[2];
        anchor.getLocationOnScreen(pos);

        // Pass along details about the anchor
        intent.putExtra(EXTRA_WIDTH, anchor.getWidth());
        intent.putExtra(EXTRA_HEIGHT, anchor.getHeight());
        intent.putExtra(EXTRA_TOP, pos[1]);
        intent.putExtra(EXTRA_LEFT, pos[0]);

        // Disable the animation so that it shows up immediately
        // Note: if you need this on API <= 4, implement this some other way.
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);

        context.startActivity(intent);
    }
}
