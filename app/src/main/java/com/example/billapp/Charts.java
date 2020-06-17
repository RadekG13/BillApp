package com.example.billapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_charts2);

    barChart= findViewById(R.id.barChart);


 // barEntryArrayList.clear();
 // labelsName.clear();
  barEntryArrayList = new ArrayList<>();
  labelsName=new ArrayList<>();
    fillCategorysum();
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
    private void fillCategorysum(){
        categorySumArrayList.clear();
        categorySumArrayList.add(new CategorySum("Jedzenie",45));
        categorySumArrayList.add(new CategorySum("Zdrowie",78));
        categorySumArrayList.add(new CategorySum("Elektronika",22));
        categorySumArrayList.add(new CategorySum("Moda",85));
        categorySumArrayList.add(new CategorySum("Sport",65));
        categorySumArrayList.add(new CategorySum("Kultura",33));
    }



}
