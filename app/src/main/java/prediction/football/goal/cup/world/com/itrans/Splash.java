package prediction.football.goal.cup.world.com.itrans;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        Runnable runnable=new Runnable() {
            @Override
            public void run() {
                try {
                    Context context=Splash.this;
                    String  userdata=getUserId(context);

                    try{
                        if(userdata.equals("")){
                            startActivity(new Intent(Splash.this,Login.class));
                            finish();

                        }else{
                            //
                            startActivity(new Intent(Splash.this,Main.class));
                            finish();
                        }


                    }catch (Exception e){

                         startActivity(new Intent(Splash.this,Login.class));
                         finish();
                    }


                }catch (Exception e){

                }

            }
        };

        Handler handler=new Handler();

        handler.postDelayed(runnable,1000);
    }

    //static method to get the user id from the shared preference

    public static String getUserId(Context ctx){
        SharedPreferences sharedPreferences = ctx. getSharedPreferences("login", MODE_PRIVATE);
        String userdata = sharedPreferences.getString("user", null);
        return  userdata;
    }
}
