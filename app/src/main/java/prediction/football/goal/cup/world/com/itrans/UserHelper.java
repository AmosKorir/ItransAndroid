package prediction.football.goal.cup.world.com.itrans;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class UserHelper extends AsyncTask<String ,Void,User> {
    User me;
    @Override
    protected User doInBackground(String... strings) {
        String jsonstring=strings[0];


        try {
            JSONArray jsonArray =new JSONArray(jsonstring);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject cobject = jsonArray.getJSONObject(i);
                User user=new User();
                user.setAmount(cobject.getInt("amount"));
                user.setId(cobject.getString(""));
                user.setName(cobject.getString(""));

                me=user;
                }

            }catch (Exception e){

        }

        return me;
    }
}
