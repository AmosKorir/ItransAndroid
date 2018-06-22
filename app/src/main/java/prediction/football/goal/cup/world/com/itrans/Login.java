package prediction.football.goal.cup.world.com.itrans;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.concurrent.ExecutionException;

import static prediction.football.goal.cup.world.com.itrans.Constanst.LOGINURL;
import static prediction.football.goal.cup.world.com.itrans.Constanst.REGISTERURL;

public class Login extends AppCompatActivity {
    Button login,register;
    EditText phone,password;

    String passwordstr,phonestr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //resource init

        login=(Button)findViewById(R.id.login);
        register=(Button)findViewById(R.id.register);
        password=(EditText)findViewById(R.id.password);
        phone=(EditText)findViewById(R.id.phone);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getInput();
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login.this,Register.class));
            }
        });



    }

    //method to get the input

    public void getInput(){
        passwordstr=password.getText().toString().trim();
        phonestr=phone.getText().toString().trim();



        if(!passwordstr.isEmpty() && !phonestr.isEmpty()){




                Runnable runnable=new Runnable() {
                    @Override
                    public void run() {
                        Connection direconection=new Connection();
                        String output= null;

                        try {
                            output = direconection.execute(LOGINURL+"/"+phonestr +"/"+passwordstr).get();

                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        } catch (ExecutionException e) {
                            e.printStackTrace();
                        }

                        if(output.equals("no")){
                            //Toast.makeText(this, "Please Check your credentials", Toast.LENGTH_SHORT).show();
                        }else{
                           // Toast.makeText(this, output, Toast.LENGTH_SHORT).show();
//
                    SharedPreferences preferences=getSharedPreferences("login",MODE_PRIVATE);
                    SharedPreferences.Editor editor=preferences.edit();
                    editor.putString("user",output);
                            Toast.makeText(Login.this, output, Toast.LENGTH_SHORT).show();
                    editor.commit();
                    startActivity(new Intent(Login.this,Main.class));

                        }


                    }
                };
                runnable.run();








        }else {
            Toast.makeText(this, "Please Enter both fields", Toast.LENGTH_SHORT).show();
        }
    }
}
