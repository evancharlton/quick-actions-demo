
package com.example.quickactions;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

public class QuickActionsDemo extends Activity implements View.OnClickListener {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        findViewById(R.id.one).setOnClickListener(this);
        findViewById(R.id.two).setOnClickListener(this);
        findViewById(R.id.three).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        // Show the buttons with respect to whatever view was clicked
        ActionsActivity.show(v);
    }
}
