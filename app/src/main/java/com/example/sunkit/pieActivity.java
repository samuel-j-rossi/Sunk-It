package com.example.sunkit;

import android.os.Bundle;

//import androidx.appcompat.app.AppCompatActivity;
import android.support.v7.app.AppCompatActivity;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.AxisBase;

import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.components.XAxis;
//import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.os.AsyncTask;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.URL;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import java.util.ArrayList;

import android.content.Intent;

import android.view.View;
import android.widget.Button;

public class pieActivity extends AppCompatActivity {

    private static final String URL = "http://192.168.1.29:80/SinkCharts.php";
    int testWins;
    //int wins = 15;
    //RecyclerView recyclerView;
    String[] lastnameArray = new String[100];
    int[] sinkArray = new int[100];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getJSON(URL);
        setContentView(R.layout.activity_pie_chart);

        // setting up Home Button
        Button homeButton = findViewById(R.id.homeButton);
        // adding clicklistener
        homeButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v)
            {
                Intent intent = new Intent(pieActivity.this, MainActivity.class);
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

            sinkArray[i] = obj.getInt("sinks");


        }

        int length = jsonArray.length();

        PieChart pieChart = findViewById(R.id.piechart);

        ArrayList NoOfEmp = new ArrayList();

        for (int i = 0; i < jsonArray.length(); i++) {
            NoOfEmp.add(new Entry(sinkArray[i], i));
        }



/*
        NoOfEmp.add(new Entry(945f, 0));
        NoOfEmp.add(new Entry(1040f, 1));
        NoOfEmp.add(new Entry(1133f, 2));
        NoOfEmp.add(new Entry(1240f, 3));
        NoOfEmp.add(new Entry(1369f, 4));
        NoOfEmp.add(new Entry(1487f, 5));
        NoOfEmp.add(new Entry(1501f, 6));
        NoOfEmp.add(new Entry(1645f, 7));
        NoOfEmp.add(new Entry(1578f, 8));
        NoOfEmp.add(new Entry(length, 9));
*/
        PieDataSet dataSet = new PieDataSet(NoOfEmp, "Number Of Sinks");

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

        PieData data = new PieData(year, dataSet);
        pieChart.setData(data);
        dataSet.setSliceSpace(10);
        pieChart.setUsePercentValues(true);
        dataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        pieChart.setCenterText("Percent Share of sinks");
        //pieChart.setDrawSliceText(true);
        dataSet.setValueTextSize(13);
        pieChart.animateXY(5000, 5000);
        Legend legend = pieChart.getLegend();
        legend.setForm(Legend.LegendForm.CIRCLE);
        legend.setTextSize(12);
        legend.setFormSize(20);
        legend.setFormToTextSpace(2);
        pieChart.setDescription("");
        pieChart.getLegend().setEnabled(false);
        pieChart.setHoleColor(16777215);
        //data.setDrawValues(false);
        //pieChart.setDrawSliceText(false);

    }



}
