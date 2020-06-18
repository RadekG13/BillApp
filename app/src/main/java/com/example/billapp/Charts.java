package com.example.billapp;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class Charts extends AppCompatActivity {

    BarChart barChart;
    ArrayList<BarEntry> barEntryArrayList;
    ArrayList<String> labelsName;
    ArrayList<CategorySum> categorySumArrayList= new ArrayList<>();
    public static SQLiteHelper sqLiteHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_charts2);

    barChart= findViewById(R.id.barChart);
    int SumJedzenie=0;
        int SumZdrowie=0;
        int SumElektronika=0;
        int SumModa=0;
        int SumSport=0;
        int SumKultura=0;

        sqLiteHelper=new SQLiteHelper(this,"BillDB.sqlite",null,1);
        Cursor cursor=sqLiteHelper.getData("SELECT * FROM BILLS");
        cursor.moveToFirst();
        do{
            String category = cursor.getString(3);
            String price = cursor.getString(2);
            int value=Integer.parseInt(price);
            switch(category){
                case "Jedzenie":
                    SumJedzenie=SumJedzenie+value;
                    break;
                case "Zdrowie":
                    SumZdrowie=SumZdrowie+value;
                    break;
                case "Elektronika":
                    SumElektronika=SumElektronika+value;
                    break;
                case "Moda":
                    SumModa=SumModa+value;
                    break;
                case "Sport":
                    SumSport=SumSport+value;
                    break;
                case "Kultura":
                    SumKultura=SumKultura+value;
                    break;
                default:
                    Toast.makeText(Charts.this, "OJOJ "+category,Toast.LENGTH_LONG).show();
            }





        }while(cursor.moveToNext());

 // barEntryArrayList.clear();
 // labelsName.clear();
  barEntryArrayList = new ArrayList<>();
  labelsName=new ArrayList<>();
    fillCategorysum(SumJedzenie, SumZdrowie,SumElektronika,SumModa,SumSport,SumKultura);
    for(int i=0;i<categorySumArrayList.size();i++){
        String category=categorySumArrayList.get(i).getCategory();
        int sum=categorySumArrayList.get(i).getPrice();
        barEntryArrayList.add(new BarEntry(i,sum));
        labelsName.add(category);
    }

    BarDataSet barDataSet=new BarDataSet(barEntryArrayList, "Category sum");
    barDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
    Description description=new Description();
    description.setText("Categories");
    barChart.setDescription(description);
    BarData barData=new BarData(barDataSet);
    barChart.setData(barData);
        XAxis xAxis = barChart.getXAxis();
        xAxis.setValueFormatter(new IndexAxisValueFormatter(labelsName));
        xAxis.setPosition(XAxis.XAxisPosition.TOP);
        xAxis.setDrawGridLines(false);
        xAxis.setDrawAxisLine(false);
       // xAxis.setGranularity(1f);
        xAxis.setLabelCount(labelsName.size());
        xAxis.setLabelRotationAngle(270);
        barChart.animateY(5000);
        barChart.invalidate();
   /*  entries.add(new BarEntry(35f,0));
        entries.add(new BarEntry(72f,1));
        entries.add(new BarEntry(40f,2));
        entries.add(new BarEntry(50f,3));
        entries.add(new BarEntry(62f,4));
    BarDataSet bardataset = new BarDataSet(entries, "Koszty");

    /*labels=new ArrayList();
    labels.add("Jedzenie");
    labels.add("Zdrowie");
    labels.add("Elektronika");
    labels.add("Sport");
    labels.add("Kultura");
        barChart.animateY(5000);
        BarData data = new BarData(labels,bardataset);
        bardataset.setColors(ColorTemplate.COLORFUL_COLORS);
        barChart.setData(data);
    */}
    private void fillCategorysum(int jedzenie, int zdrowie, int elektornika, int moda, int sport, int kultura){
        categorySumArrayList.clear();
        categorySumArrayList.add(new CategorySum("Jedzenie",jedzenie));
        categorySumArrayList.add(new CategorySum("Zdrowie",zdrowie));
        categorySumArrayList.add(new CategorySum("Elektronika",elektornika));
        categorySumArrayList.add(new CategorySum("Moda",moda));
        categorySumArrayList.add(new CategorySum("Sport",sport));
        categorySumArrayList.add(new CategorySum("Kultura",kultura));
    }



}
