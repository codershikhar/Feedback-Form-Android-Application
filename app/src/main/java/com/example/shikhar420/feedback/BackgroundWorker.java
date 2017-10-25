package com.example.shikhar420.feedback;

import android.app.AlertDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

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

/**
 * Created by shikhar420 on 10/17/2017.
 */

public class BackgroundWorker extends AsyncTask<String, Void, String>
{
    Context context;
    AlertDialog alertDialog;
    BackgroundWorker(Context ctx)
    {
        context = ctx;
    }

    @Override
    protected String doInBackground(String... params)
    {
        String type = params[0];
        String login_url = "http://10.0.2.2/register.php";
        Log.d("--------->check type ", type);
        if(type.equals("insert"))
        {
            Log.d("--------->check type ", "1");
            try {
                Log.d("--------->check type", "2");
                String studentrollno = params[1];
                String studentsubject = params[2];
                String studentteacher = params[3];
                String studentrating = params[4];
                URL url = new URL(login_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);

                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("roll_no", "UTF-8")+"="+URLEncoder.encode(studentrollno, "UTF-8")+"&"
                        + URLEncoder.encode("subject", "UTF-8")+"="+URLEncoder.encode(studentsubject, "UTF-8")+"&"
                        + URLEncoder.encode("teacher", "UTF-8")+"="+URLEncoder.encode(studentteacher, "UTF-8")+"&"
                        + URLEncoder.encode("avg_rating", "UTF-8")+"="+URLEncoder.encode(studentrating, "UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();

                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                String result="";
                String line="";
                while((line = bufferedReader.readLine())!=null)
                {
                    result += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();

                Log.d("--------->result : ", "i hope it worked");
                return result;
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
        alertDialog.setTitle("Feedback Status");
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