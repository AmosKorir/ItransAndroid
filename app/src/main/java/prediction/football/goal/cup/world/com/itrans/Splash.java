package prediction.football.goal.cup.world.com.itrans;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        Runnable runnable=new Runnable() {
            @Override
            public void run() {
                try {
                    SharedPreferences sharedPreferences = getSharedPreferences("login", MODE_PRIVATE);
                    String userdata = sharedPreferences.getString("user", null);
                    UserHelper helper = new UserHelper();
                    if(userdata.isEmpty()){
                        startActivity(new Intent(Splash.this,Login.class));
                        finish();

                    }else{
                        //
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
}
