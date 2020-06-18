package com.example.billapp;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ItemsAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private ArrayList<Items> itemList;

    public ItemsAdapter(Context context, int layout, ArrayList<Items> itemList) {
        this.context = context;
        this.layout = layout;
        this.itemList = itemList;
    }

    @Override
    public int getCount() {
        return itemList.size();
    }

    @Override
    public Object getItem(int position) {
        return itemList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private  class ViewHolder{
        ImageView imageView;
        TextView txtName,txtPrice,txtCategory;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row=convertView;
        ViewHolder holder= new ViewHolder();
        if(row==null){
            LayoutInflater inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row=inflater.inflate(layout,null);
            holder.txtName=(TextView) row.findViewById(R.id.textView);
            holder.txtPrice=(TextView) row.findViewById(R.id.textView3);
            holder.txtCategory=(TextView) row.findViewById(R.id.textView4);
            holder.imageView=(ImageView) row.findViewById(R.id.imageView2);
            row.setTag(holder);

        }
        else{holder=(ViewHolder) row.getTag();}
            Items items=itemList.get(position);

            holder.txtName.setText(items.getName());
            holder.txtPrice.setText(items.getPrice());
            holder.txtCategory.setText(items.getCategory());

            byte[] billImage=items.getImage();
            Bitmap bitmap = BitmapFactory.decodeByteArray(billImage,0,billImage.length);
            holder.imageView.setImageBitmap(bitmap);

        return row;
    }
}
