package com.wka.viewdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.wka.viewdemo.weight.BaseDiagramView;

public class MainActivity extends AppCompatActivity {
    private BaseDiagramView myDiagram;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        myDiagram = (BaseDiagramView) findViewById(R.id.my_diagram);
        myDiagram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("MainActivity","push----------");
                Log.e("MainActivity","push----trista------");

                startActivity(new Intent(MainActivity.this, AnimationActivity.class));
            }
        });


    }
}
