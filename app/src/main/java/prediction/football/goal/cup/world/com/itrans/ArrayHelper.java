package prediction.football.goal.cup.world.com.itrans;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ArrayHelper  extends AsyncTask<String, Void, ArrayList> {
    ArrayList <String>arrayList=new ArrayList<String>();
    @Override
    protected ArrayList doInBackground(String... strings) {
        String jsonstring=strings[0];
        String ntype=strings[1];

        try {
            JSONArray jsonArray =new JSONArray(jsonstring);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject cobject = jsonArray.getJSONObject(i);
                if(ntype.equals("B")){
                    arrayList.add(cobject.getString("StationB"));
                }else if(ntype.equals("T")) {
                    arrayList.add(cobject.getString("startA"));
                }else{
                    arrayList.add(cobject.getString("stationA"));
                }

            }}
            catch (JSONException e) {
                e.printStackTrace();
            }
        return arrayList;
    }
}
