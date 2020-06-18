package com.example.billapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;

import com.hitomi.cmlibrary.CircleMenu;
import com.hitomi.cmlibrary.OnMenuSelectedListener;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        CircleMenu circleMenu=(CircleMenu)findViewById(R.id.circlemenu);
        circleMenu.setMainMenu(Color.parseColor("#CDCDCD"),R.drawable.ic_add,R.drawable.ic_cancel)
                .addSubMenu(Color.parseColor("#258CFF"),R.drawable.ic_chart)
                .addSubMenu(Color.parseColor("#A2F53A"),R.drawable.ic_view_list_black_24dp)
                .addSubMenu(Color.parseColor("#F58D3A"),R.drawable.ic_sql)
                .addSubMenu(Color.parseColor("#ff0000"),R.drawable.ic_exit)
                .setOnMenuSelectedListener(new OnMenuSelectedListener() {
                    @Override
                    public void onMenuSelected(int index) {

                        if (index == 0) {
                            Handler h = new Handler();
                            h.postDelayed(new Runnable() {
                                              public void run() {
                                                  Intent intent = new Intent(MainActivity.this, Charts.class);
                                                  startActivityForResult(intent, 1);
                                              }
                                          }
                                    , 1000);
                        }

                        if(index==1) {
                            Handler h = new Handler();
                            h.postDelayed(new Runnable() {
                                              public void run() {
                                                  Intent intent=new Intent(MainActivity.this, ItemList.class);
                                                  startActivity(intent);
                                              }
                                          }
                                    , 1000);
                        }
                        if(index==2) {
                            Handler h = new Handler();
                            h.postDelayed(new Runnable() {
                                              public void run() {
                                                  Intent intent = new Intent(MainActivity.this, Database_1.class);
                                                  startActivityForResult(intent, 1);
                                              }
                                          }
                                    , 1000);
                        }


                        if(index==3) {

                            Handler h = new Handler();
                            h.postDelayed(new Runnable() {
                                              @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                                              public void run() {
                                                  finishAffinity();
                                                  System.exit(0);
                                              }
                                          }
                                    , 1000);
                        }





                    }
                });
    }
}
