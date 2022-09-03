package com.app.appbelajarkomik;

import android.os.AsyncTask;
import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class ParsePageTask extends AsyncTask<String, Void, String> {

    private Callback mCallback;

    public interface Callback{
        void onChange(String response);
    }

    public void setCallback(Callback callback){
        mCallback = callback;
    }


    @Override
    protected String doInBackground(String... urls) {
        try{
            Document document = Jsoup.connect(urls[0]).get();
            Elements element = document.getElementsByTag("body");
            return element.toString();
        }catch (Exception ignored) {
            Log.i( "doInBackground: ", ignored.getMessage());
        }
        return null;
    }


    @Override
    protected void onPostExecute(String s) {
        if (mCallback!=null){
            mCallback.onChange(s);
        }
        Log.i( "doInBackground: ", s);

    }
}
