package prediction.football.goal.cup.world.com.itrans;

import android.os.AsyncTask;


import android.content.Context;
import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by ADMIN on 4/14/2018.
 */

public class Connection extends AsyncTask<String ,Void ,String> {

//    public Connection (Context ctx){
//
//    }

    HttpURLConnection connect;
    String text_url = null;
    URL url;

    @Override
    protected String doInBackground(String... strings) {
        text_url = strings[0];
        String feed = getContent(text_url);
        return feed;
    }

    //method to read

    private String getContent(String string){
        String result_type = null;
        try {
            url=new URL(text_url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        try {
            connect=(HttpURLConnection)url.openConnection();
            connect.setRequestMethod("POST");
            connect.setDoOutput(true);
            connect.setDoInput(true);




            //reading the response
            InputStream inputStream=connect.getInputStream();
            BufferedReader reader=new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
            String  result="",line;

            while((line=reader.readLine())!=null){
                result+=line;
            }
            result_type=result;
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        connect.disconnect();
        return result_type;
    }

}





