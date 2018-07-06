package prediction.football.goal.cup.world.com.itrans;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ModelHelper extends AsyncTask<String, Void, ArrayList> {
    @Override
    protected ArrayList doInBackground(String... strings) {
        ArrayList<Model> myarraylist=new ArrayList<Model>();
        String jsonstring=strings[0];

        try {
            JSONArray jsonArray =new JSONArray(jsonstring);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject cobject = jsonArray.getJSONObject(i);
                Model model=new Model();
                model.setPlateno(cobject.getString("plateno"));
                model.setStartionA(cobject.getString("stationA"));
                model.setStartionB(cobject.getString("StationB"));
                model.setAllocationid(cobject.getString("id"));
                model.setFare(cobject.getInt("fare"));
                model.setTime(cobject.getString("startA"));
                myarraylist.add(model);
          }



    } catch (JSONException e) {
            e.printStackTrace();
        }
        return myarraylist;
    }}
