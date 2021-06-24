package com.example.sunkit;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.components.XAxis;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.os.AsyncTask;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.URL;

import java.util.ArrayList;
import android.content.Intent;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LossGraphActivity extends AppCompatActivity {
    private static final String URL = "http://192.168.1.29:80/LossCharts.php";
    String[] lastnameArray = new String[100];
    int[] lossArray = new int[100];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loss_graph);
        getJSON(URL);


        // setting up Home Button
        Button homeButton = findViewById(R.id.buttonHome);
        // adding clicklistener
        homeButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v)
            {
                Intent intent = new Intent(LossGraphActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }
    public void getJSON(final String urlWebService) {

        class GetJSON extends AsyncTask<Void, Void, String> {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();


            }

            @Override
            public void onPostExecute(String s) {
                super.onPostExecute(s);
                try {
                    loadIntoListView(s);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public String doInBackground(Void... voids) {


                try {
                    URL url = new URL(urlWebService);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    StringBuilder sb = new StringBuilder();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));
                    String json;
                    while ((json = bufferedReader.readLine()) != null) {
                        sb.append(json + "\n");
                    }

                    return sb.toString().trim();
                } catch (Exception e) {
                    return null;

                }

            }
        }

        GetJSON getJSON = new GetJSON();
        getJSON.execute();
    }

    public void loadIntoListView(String json) throws JSONException {
        JSONArray jsonArray = new JSONArray(json);

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject obj = jsonArray.getJSONObject(i);

            lastnameArray[i] = obj.getString("lastname");

            lossArray[i] = obj.getInt("losses");


        }

        int length = jsonArray.length();

        BarChart chart = findViewById(R.id.barchart);


        ArrayList NoOfEmp = new ArrayList();

        for (int i = 0; i < jsonArray.length(); i++) {
            NoOfEmp.add(new BarEntry(lossArray[i], i));
        }
/*
        NoOfEmp.add(new BarEntry(945f, 0));
        NoOfEmp.add(new BarEntry(1040f, 1));
        NoOfEmp.add(new BarEntry(1133f, 2));
        NoOfEmp.add(new BarEntry(1240f, 3));
        NoOfEmp.add(new BarEntry(1369f, 4));
        NoOfEmp.add(new BarEntry(1487f, 5));
        NoOfEmp.add(new BarEntry(1501f, 6));
        NoOfEmp.add(new BarEntry(1645f, 7));
        NoOfEmp.add(new BarEntry(1578f, 8));
        NoOfEmp.add(new BarEntry(1695f, 9));

*/

        BarDataSet bardataset = new BarDataSet(NoOfEmp, "Number of Losses");

        ArrayList year = new ArrayList();
/*
        year.add("2008");
        year.add("2009");
        year.add("2010");
        year.add("2011");
        year.add("2012");
        year.add("2013");
        year.add("2014");
        year.add("2015");
        year.add("2016");
        year.add("2017");
*/

        for (int i = 0; i < jsonArray.length(); i++) {
            year.add(lastnameArray[i]);

        }


        chart.animateY(5000);
        BarData data = new BarData(year, bardataset);
        bardataset.setColors(ColorTemplate.COLORFUL_COLORS);
        chart.setData(data);
        chart.setDrawValueAboveBar(true);
        bardataset.setBarSpacePercent(50);
        //xAxis.setCenterAxisLabels(false);
        Legend legend = chart.getLegend();
        legend.setEnabled(false);
        legend.setTextColor(Color.RED);
        legend.setForm(Legend.LegendForm.SQUARE);
        legend.setXEntrySpace(25f);
        legend.setYEntrySpace(6f);
        chart.setDescription("");
        chart.setDescriptionPosition(470, 1000);
        chart.setDescriptionTextSize(20);


        //legend.setCustom(ColorTemplate.COLORFUL_COLORS, new String[]{"Set1", "Set2", "Set3", "Set4", "Set5"});


    }

}
