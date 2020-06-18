package com.example.billapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class Database_1 extends AppCompatActivity {
    private ImageView Photo;
    public String category2;
    public static SQLiteHelper sqLiteHelper;
    final int REQUEST_CODE_GALLERY=999;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database_1);

        Button addPhoto=(Button) findViewById(R.id.button3);
        Button addDB=(Button) findViewById(R.id.button);
        Button ListDB=(Button)findViewById(R.id.button2);
        Photo=(ImageView)findViewById(R.id.imageView);
        final EditText name=(EditText)findViewById(R.id.editText);
        final EditText price=(EditText)findViewById(R.id.editText2);
        Spinner spinner=findViewById(R.id.spinner);

         textView=findViewById(R.id.textView2);

       List<String> list = new ArrayList<>();
       list.add("Jedzenie");
        list.add("Zdrowie");
        list.add("Elektronika");
        list.add("Moda");
        list.add("Sport");
        list.add("Kultura");
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String categoryItem=parent.getItemAtPosition(position).toString();
                category2=categoryItem;
                //Toast.makeText(Database_1.this, "Selected "+categoryItem,Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
      // final Context appcontext=this;
        addPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                selectImage(Database_1.this);


            }
        });




        sqLiteHelper=new SQLiteHelper(this,"BillDB.sqlite",null,1);

    sqLiteHelper.queryData("CREATE TABLE IF NOT EXISTS BILLS (Id INTEGER PRIMARY KEY AUTOINCREMENT, NAME VARCHAR, PRICE VARCHAR, CATEGORY VARCHAR, IMAGE  BLOB)");
        final String test="jedzenie";
        addDB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    sqLiteHelper.insertData(
                            name.getText().toString().trim(),
                            price.getText().toString().trim(),
                            category2.trim(),
                            ImageViewToByte(Photo)

                    );
                    Toast.makeText(getApplicationContext(),"wprowadzono!", Toast.LENGTH_SHORT).show();
                    name.setText("");
                    price.setText("");
                    Photo.setImageResource(R.drawable.ic_add_bill);
                }
                catch (Exception e){
                    e.printStackTrace();
                }

            }
        });

      ListDB.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              Cursor cursor=sqLiteHelper.getData("SELECT * FROM BILLS");
/*
              if(cursor.moveToFirst())
              { cursor.moveToNext();
                  String[] columnNames=cursor.getColumnNames();
                  int[] yData=new int[columnNames.length];
                  String test=cursor.getString(cursor.getColumnIndex(columnNames[3]));
                  int prosze=cursor.getCount();
                  String co ="cooo"+prosze;
                  textView.setText(test);

                  byte[] imag=cursor.getBlob(cursor.getColumnIndex(columnNames[4]));
                  Bitmap bt=BitmapFactory.decodeByteArray(imag,0,imag.length);
                  Photo.setImageBitmap(bt);
                  textView.setText(test);
              }*/
                Intent intent=new Intent(Database_1.this, ItemList.class);
                startActivity(intent);



              //String mozeteraz=cursor.getColumnName(3);
              //String[] testowe=cursor.getColumnNames();
             // textView.setText(testowe[0]);
          }
      });

    }

    private byte[] ImageViewToByte(ImageView image) {
        Bitmap bitmap=((BitmapDrawable) image.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100,stream);
        byte[] byteArray=stream.toByteArray();
        return  byteArray;
    }

  /*  @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
       if(requestCode==REQUEST_CODE_GALLERY){
           if(grantResults.length>0 && grantResults[0]== PackageManager.PERMISSION_GRANTED){
               Intent intent = new Intent(Intent.ACTION_PICK);
               intent.setType("image/*");
               startActivityForResult(intent, REQUEST_CODE_GALLERY);
           }
           else {
               Toast.makeText(getApplicationContext(),"permision fail", Toast.LENGTH_SHORT).show();
           }
           return;
       }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
*/






    private void selectImage(Context context) {
        final CharSequence[] options = { "Zrob zdjecie", "Wybierz z galerii","Anuluj" };

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Choose your profile picture");

        builder.setItems(options, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int item) {

                if (options[item].equals("Zrob zdjecie")) {
                    Intent takePicture = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(takePicture, 0);

                } else if (options[item].equals("Wybierz z galerii")) {
                    Intent pickPhoto = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(pickPhoto , 1);

                } else if (options[item].equals("Anuluj")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_CANCELED) {
            switch (requestCode) {
                case 0:
                    if (resultCode == RESULT_OK && data != null) {
                        Bitmap selectedImage = (Bitmap) data.getExtras().get("data");
                        Photo.setImageBitmap(selectedImage);
                    }

                    break;
                case 1:
                    if (resultCode == RESULT_OK && data != null) {
                        Uri selectedImage = data.getData();

                        try {
                            InputStream inputStream = getContentResolver().openInputStream(selectedImage);
                            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                            Photo.setImageBitmap(bitmap);

                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }/*
                        String[] filePathColumn = {MediaStore.Images.Media.DATA};
                        if (selectedImage != null) {
                            Cursor cursor = getContentResolver().query(selectedImage,
                                    filePathColumn, null, null, null);
                            if (cursor != null) {
                                cursor.moveToFirst();

                                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                                String picturePath = cursor.getString(columnIndex);
                                Photo.setImageBitmap(BitmapFactory.decodeFile(picturePath));
                                cursor.close();
                            }
                        }*/

                    }
                    break;
            }
        }
    }



}
