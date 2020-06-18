package com.example.billapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;

public class ItemList extends AppCompatActivity {

        GridView gridView;
        ArrayList<Items> list;
        ItemsAdapter adapter=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_list);
        gridView = (GridView) findViewById(R.id.gridView);
        list=new ArrayList<>();
        adapter=new ItemsAdapter(this,R.layout.items,list);
        gridView.setAdapter(adapter);
        SQLiteHelper sqLiteHelper=new SQLiteHelper(this,"BillDB.sqlite",null,1);
        Cursor cursor=sqLiteHelper.getData("SELECT * FROM BILLS");
        list.clear();
        while(cursor.moveToNext()){
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String price = cursor.getString(2);
            String category = cursor.getString(3);
            byte[] image=cursor.getBlob(4);
            //Toast.makeText(ItemList.this, "Selected "+name,Toast.LENGTH_LONG).show();
            list.add(new Items(id,name,price,category,image));

        }
        adapter.notifyDataSetChanged();

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String pozycja=Integer.toString(position);
                String  price2="nic";
                Cursor cursor=Database_1.sqLiteHelper.getData("SELECT * FROM BILLS");
                cursor.moveToFirst();
                do{
                    int id2 = cursor.getInt(0);
                    Log.i("sprawdz",Integer.toString(id2));
                    if(id2==position+1){
                        price2=cursor.getString(2);
                    }
                }while(cursor.moveToNext());
                Toast.makeText(getApplicationContext(),"tutaj"+price2, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ItemList.this,CashSplit.class);
                        intent.putExtra("price",price2);
                startActivity(intent);

            }
        });
    }
}
