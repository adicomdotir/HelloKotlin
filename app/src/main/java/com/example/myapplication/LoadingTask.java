package com.example.myapplication;

import android.os.AsyncTask;
import android.util.Log;

// <Param, Progress, Result>
public class LoadingTask extends AsyncTask<Void, Integer, String> {
    @Override
    protected String doInBackground(Void... voids) {
        long sum = 0;
        for (int i = 0; i < 100000000; i++) {
            sum += i;
        }
        return "Sum 1 to 100000000 = " + sum ;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        Log.e("TAG", s);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
    }
}