package prediction.football.goal.cup.world.com.itrans;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.inputmethod.ExtractedTextRequest;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import static prediction.football.goal.cup.world.com.itrans.Constanst.CHECKCUSTOMERDETAILS;
import static prediction.football.goal.cup.world.com.itrans.Constanst.CUSTOMERDETAILS;
import static prediction.football.goal.cup.world.com.itrans.Splash.getUserId;

public class Account extends AppCompatActivity {
    TextView phonetxt,emailtxt,namxt,citytxt;
    String userid;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        phonetxt=(TextView)findViewById(R.id.phone);
        emailtxt=(TextView)findViewById(R.id.email);
        namxt=(TextView)findViewById(R.id.name);
        citytxt=(TextView)findViewById(R.id.city);

        context=this;

        userid=getUserId(context);


        try{
            fetchData(userid);
        } catch (Exception e){
            Toast.makeText(this, "Error occured ", Toast.LENGTH_SHORT).show();
        }
    }

    private void fetchData(String userid) {
        Connection connection=new Connection();
        try {

            String data = connection.execute(CHECKCUSTOMERDETAILS + "/" + userid).get();
            decodeJson(data);
        }catch (Exception e){
            Toast.makeText(this, "Error occured ", Toast.LENGTH_SHORT).show();
        }
    }

    //decode the json

    public void decodeJson(String jsostring){
        try {
            JSONArray jsonArray = new JSONArray(jsostring);

            for(int i=0;i<jsonArray.length();i++){
                JSONObject cobject = jsonArray.getJSONObject(i);
                namxt.setText(cobject.getString("firstname")+" "+cobject.getString("secondname"));
                emailtxt.setText(cobject.getString("email"));
                phonetxt.setText(cobject.getString("phone"));
                citytxt.setText(cobject.getString("city"));

            }


        }catch (Exception e){
            Toast.makeText(context, "Sorry an error ocurred!", Toast.LENGTH_SHORT).show();
        }
    }
}
