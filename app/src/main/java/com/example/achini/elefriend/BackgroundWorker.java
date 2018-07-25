package com.example.achini.elefriend;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class BackgroundWorker extends AsyncTask<String, Void, String> {

    Context context;
    AlertDialog alertDialog;

    //pass constructor
    BackgroundWorker (Context ctx){
        context = ctx;
    }

    @Override
    protected String doInBackground(String... params) {
        String type = params[0];
        String login_URL = "http://192.168.137.1/mainActivity.php";
        String report_URL = "http://192.168.137.1/reportActivity.php";
        String reportCurrent_URL = "http://192.168.137.1/reportCurrentActivity.php";

        if (type.equals("login")){
            try {
                String phone_number = params[1];

                URL url = new URL(login_URL);

                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));

                String post_Data = URLEncoder.encode("phone_number","UTF-8")+"="+URLEncoder.encode(phone_number,"UTF-8");
                bufferedWriter.write(post_Data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();

                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));

                String results = "";
                String line = "";

//                context.startActivity(new Intent(context, ReportPopupActivity.class));

                while ((line = bufferedReader.readLine()) != null){
                    results += line;
                }

                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return results;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (type.equals("report")){

            try {
                String reported_date = params[1];
                String reported_time = params[2];
                String area_name = params[3];
                String numberof_elephants = params[4];

                URL url = new URL(report_URL);

                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));

                String post_Data = URLEncoder.encode("reported_date","UTF-8")+"="+URLEncoder.encode(reported_date,"UTF-8")+"&"+URLEncoder.encode("reported_time","UTF-8")+"="+URLEncoder.encode(reported_time,"UTF-8")+"&"+URLEncoder.encode("area_name","UTF-8")+"="+URLEncoder.encode(area_name,"UTF-8")+"&"+URLEncoder.encode("numberof_elephants","UTF-8")+"="+URLEncoder.encode(numberof_elephants,"UTF-8");
                bufferedWriter.write(post_Data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();

                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));

                String results = "";
                String line = "";

                while ((line = bufferedReader.readLine()) != null){
                    results += line;
                }

                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return results;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        else if (type.equals("reportCurrent")){

            try {
                String reported_date = params[1];
                String reported_time = params[2];
                String latitude = params[3];
                String longitude = params[4];
                String numberof_elephants = params[5];

                URL url = new URL(reportCurrent_URL);

                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));

                String post_Data = URLEncoder.encode("reported_date","UTF-8")+"="+URLEncoder.encode(reported_date,"UTF-8")+"&"+URLEncoder.encode("reported_time","UTF-8")+"="+URLEncoder.encode(reported_time,"UTF-8")+"&"+URLEncoder.encode("latitude","UTF-8")+"="+URLEncoder.encode(latitude,"UTF-8")+"&"+URLEncoder.encode("longitude","UTF-8")+"="+URLEncoder.encode(longitude,"UTF-8")+"&"+URLEncoder.encode("numberof_elephants","UTF-8")+"="+URLEncoder.encode(numberof_elephants,"UTF-8");
                bufferedWriter.write(post_Data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();

                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));

                String results = "";
                String line = "";

                while ((line = bufferedReader.readLine()) != null){
                    results += line;
                }

                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return results;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return null;
    }

    @Override
    protected void onPreExecute() {
        alertDialog = new AlertDialog.Builder(context).create();
    }

    @Override
    protected void onPostExecute(String result) {
        alertDialog.setMessage(result);
        alertDialog.show();
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}
