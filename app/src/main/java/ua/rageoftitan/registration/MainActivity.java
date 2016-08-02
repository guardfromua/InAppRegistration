package ua.rageoftitan.registration;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button bLogout;
    EditText etName, etAge, etUsername;
    UserLocalStore userlocalstore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = (EditText) findViewById(R.id.etName);
        etAge = (EditText) findViewById(R.id.etAge);
        etUsername = (EditText) findViewById(R.id.etUsername);

        bLogout = (Button) findViewById(R.id.bLogout);


        bLogout.setOnClickListener(this);

        userlocalstore =  new UserLocalStore(this);
    }
//если Юзер залогинелся - выведет детали
    @Override
    protected void onStart(){
        super.onStart();

        if(authenticate()==true){
            displayUserDetail();
        }
    }
    //will return true if the user logged in
    private boolean authenticate(){
        return userlocalstore.getUserLoggedIn();
    }
    //информация, если Юхзер зашел
    private void displayUserDetail(){

        User user = userlocalstore.getLoggedInUser();


        etUsername.setText(user.username);
        etName.setText(user.name);
        etAge.setText(user.age+"");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bLogout:
            userlocalstore.clearUserData();
                userlocalstore.setUserLoggedIn(false);



                startActivity(new Intent(this, Login.class));
            break;
        }

    }
}
