package prediction.football.goal.cup.world.com.itrans;


import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class BookingHelper extends AsyncTask<String, Void, ArrayList> {
    @Override
    protected ArrayList doInBackground(String... strings) {
        ArrayList<Ticket> myarraylist=new ArrayList<Ticket>();
        String jsonstring=strings[0];

        try {
            JSONArray jsonArray =new JSONArray(jsonstring);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject cobject = jsonArray.getJSONObject(i);
                Ticket model=new Ticket();
                model.setTicketcode(cobject.getString("ticketcode"));
                model.setSeats(cobject.getString("seats"));
                model.setStationA(cobject.getString("stationA"));
                model.setStartionB(cobject.getString("StationB"));
                model.setAllocationid(cobject.getString("allocationid"));
                model.setAmount(cobject.getString("amount"));
                model.setTime(cobject.getString("startA"));
                model.setDate(cobject.getString("date"));
                myarraylist.add(model);
            }



        } catch (JSONException e) {
            e.printStackTrace();
        }
        return myarraylist;
    }}
