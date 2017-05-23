package com.example.poul.bloodykeras;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.poul.bloodykeras.Model.Bloodbag;
import com.example.poul.bloodykeras.Service.APIService;
import com.example.poul.bloodykeras.Service.APIServiceAdapter;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;


import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;

public class PieChartScreen extends AppCompatActivity {
   //private int Apos, Bpos, Opos, Aneg, Bneg, Oneg ,ABneg,ABpos =0;
   // Apos = Bpos = Opos = Aneg = Bneg = Oneg =ABneg=ABpos= 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pie_chart_screen);
        getBags();




    }

            public void getBags(){

                APIServiceAdapter.getInstance().getAllBloodbags().subscribe(new Subscriber<List<Bloodbag>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(List<Bloodbag> bloodbags) {
                        int Apos, Bpos, Opos, Aneg, Bneg, Oneg, ABneg, ABpos = 0;
                        Apos = Bpos = Opos = Aneg = Bneg = Oneg = ABneg = ABpos = 0;



                        //Toast.makeText(PieChartScreen.this, bloodbags.size() ,Toast.LENGTH_LONG).show();

                        // Toast.makeText(PieChartScreen.this,bloodbags.size() + "twra omws?",Toast.LENGTH_LONG).show();

                        int l;
                        for (l = 0; l <= (bloodbags.size() - 1); l++) {

                            if (bloodbags.get(l).getBloodtype().equals("A") && bloodbags.get(l).getRh().equals("positive")) {
                                Apos = Apos + 1;
                            } else if (bloodbags.get(l).getBloodtype().equals("B") && bloodbags.get(l).getRh().equals("positive")) {
                                Bpos = Bpos + 1;
                            } else if (bloodbags.get(l).getBloodtype().equals("O") && bloodbags.get(l).getRh().equals("positive")) {
                                Opos = Opos + 1;
                            } else if (bloodbags.get(l).getBloodtype().equals("A") && bloodbags.get(l).getRh().equals("negative")) {
                                Aneg = Aneg + 1;
                            } else if (bloodbags.get(l).getBloodtype().equals("B") && bloodbags.get(l).getRh().equals("negative")) {
                                Bneg = Bneg + 1;
                            } else if (bloodbags.get(l).getBloodtype().equals("O") && bloodbags.get(l).getRh().equals("negative")) {
                                Oneg = Oneg + 1;
                            } else if (bloodbags.get(l).getBloodtype().equals("AB") && bloodbags.get(l).getRh().equals("negative")) {
                                ABneg = ABneg + 1;
                            } else if (bloodbags.get(l).getBloodtype().equals("AB") && bloodbags.get(l).getRh().equals("positive")) {
                                ABpos = ABpos + 1;
                            }


                        }


                        PieChart pieChart = (PieChart) findViewById(R.id.piechart);
                       // pieChart.setUsePercentValues(true);
                        pieChart.setCenterText("Bag units");
                        pieChart.setEntryLabelTextSize(12);
                        pieChart.valuesToHighlight();

                        List<PieEntry> entries = new ArrayList<>();

                        entries.add(new PieEntry(Apos, "A+"));
                        entries.add(new PieEntry(Bpos, "B+"));
                        entries.add(new PieEntry(Opos, "O+"));
                        entries.add(new PieEntry(ABpos, "AB+"));
                        entries.add(new PieEntry(Aneg, "A-"));
                        entries.add(new PieEntry(Bneg, "B-"));
                        entries.add(new PieEntry(Oneg, "O-"));
                        entries.add(new PieEntry(ABneg, "AB-"));




                        // create pie data set
                        PieDataSet set = new PieDataSet(entries, "Bloodtypes");
                        //Colors of the set
                        ArrayList<Integer> colors = new ArrayList<>();
                        colors.add( Color.rgb(220,20,60));
                        colors.add( Color.rgb(255,140,0));
                        colors.add(Color.rgb(255,215,0));
                        colors.add(Color.rgb(107,142,35));
                        colors.add(Color.rgb(32,178,170));
                        colors.add(Color.rgb(70,130,180));
                        colors.add(Color.rgb(186,85,211));
                        colors.add(Color.rgb(188,143,143));

                        set.setColors(colors);
                        set.setSliceSpace(3f);
                            //ypomnhma
                        Legend legend = pieChart.getLegend();
                        legend.setForm(Legend.LegendForm.CIRCLE);
                        legend.setPosition(Legend.LegendPosition.LEFT_OF_CHART);
                        legend.setTextSize(11);


                        //create pie data object
                        PieData data = new PieData(set);
                        data.setValueTextSize(12);

                        pieChart.setData(data);
                        pieChart.invalidate(); // refresh


                    }
                });
            }


}
