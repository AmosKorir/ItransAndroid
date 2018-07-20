package prediction.football.goal.cup.world.com.itrans;

import android.app.ProgressDialog;
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static prediction.football.goal.cup.world.com.itrans.Constanst.LOGINURL;
import static prediction.football.goal.cup.world.com.itrans.Constanst.REGISTERURL;

public class Login extends AppCompatActivity {
    Button login,register;
    EditText phone,password;

    String passwordstr,phonestr;
    ProgressDialog progressDialog;

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
        progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("Please wait....");

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
        isPhoneValid(phonestr);




        if(!passwordstr.isEmpty() && !phonestr.isEmpty()){
            progressDialog=new ProgressDialog(this);
            progressDialog.setMessage("Please wait....");


            progressDialog.show();


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

                            outputMessage("Please check your details");
                        }else{
                           // Toast.makeText(this, output, Toast.LENGTH_SHORT).show();

                    SharedPreferences preferences=getSharedPreferences("login",MODE_PRIVATE);
                    SharedPreferences.Editor editor=preferences.edit();
                    editor.putString("user",output);
                    editor.commit();
                    startActivity(new Intent(Login.this,Main.class));
                    finish();

                        }


                    }
                };
                runnable.run();








        }else {
            Toast.makeText(this, "Please Enter both fields", Toast.LENGTH_SHORT).show();
        }
    }

    private void outputMessage(String output) {
        progressDialog.dismiss();
        Toast.makeText(this, output, Toast.LENGTH_SHORT).show();
    }

    public void   isPhoneValid(String phone){
        Pattern pattern = Pattern.compile("^(?:254|\\+254|0)?(7(?:(?:[129][0-9])|(?:0[0-8])|(4[0-1]))[0-9]{6})$");
        Matcher matcher = pattern.matcher(phone);
        if (matcher.matches()) {
            phone = "254" + matcher.group(1);

        }else {
            pattern = Pattern.compile("^(?:254|\\+254|0)?(7(?:(?:[3][0-9])|(?:5[0-6])|(8[5-9]))[0-9]{6})$");
            matcher = pattern.matcher(phone);
            if (matcher.matches()) {
                phone = "254" + matcher.group(1);

            }else {
                pattern = Pattern.compile("^(?:254|\\+254|0)?(77[0-6][0-9]{6})$");
                matcher = pattern.matcher(phone);
                matcher = pattern.matcher(phone);
                if (matcher.matches()) {
                    phone = "254" + matcher.group(1);

                }
            }
        }

        phonestr=phone;
    }
}
