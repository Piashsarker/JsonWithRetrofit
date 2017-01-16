package com.ptlearnpoint.www.jsonwithretrofit;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    Button btnMakeObjectRequest , btnMakeArrayRequest ;
    TextView txtResponse;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnMakeObjectRequest = (Button) findViewById(R.id.btnObjRequest);
        btnMakeArrayRequest = (Button) findViewById(R.id.btnArrayRequest);
        txtResponse = (TextView) findViewById(R.id.txtResponse);

        btnMakeArrayRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeArrayRequest();
            }
        });

        btnMakeObjectRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeObjectRequest();
            }
        });
    }

    private void makeArrayRequest() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Wait");
        progressDialog.setMessage("Loading");
        progressDialog.show();

        ApiService apiService = RetroClient.getApiService() ;

        Call<ArrayList<Person>> call = apiService.getPersonList();

        call.enqueue(new Callback<ArrayList<Person>>() {
            @Override
            public void onResponse(Call<ArrayList<Person>> call, Response<ArrayList<Person>> response) {
                progressDialog.dismiss();

                ArrayList<Person> personList = response.body();

                String jsonResponse = "";
                for(int i=0 ; i<personList.size(); i++){

                    jsonResponse += "Name: " + personList.get(i).getName() + "\n\n";
                    jsonResponse += "Email: " + personList.get(i).getEmail() + "\n\n";
                    jsonResponse += "Home: " + personList.get(i).getPhone().getHome() + "\n\n";
                    jsonResponse += "Mobile: " + personList.get(i).getPhone().getMobile() + "\n\n\n";
                }
                txtResponse.setText(jsonResponse.toString());
            }

            @Override
            public void onFailure(Call<ArrayList<Person>> call, Throwable t) {


            }
        });


    }

    private void makeObjectRequest() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Wait");
        progressDialog.setMessage("Loading");
        progressDialog.show();

        ApiService apiService = RetroClient.getApiService() ;

        Call<Person> call = apiService.getPerson();

        call.enqueue(new Callback<Person>() {
            @Override
            public void onResponse(Call<Person> call, Response<Person> response) {
                progressDialog.dismiss();
                 Person person = response.body();
                txtResponse.setText(person.getName() + " \n"+person.getEmail()+"\n"+person.getPhone());
            }

            @Override
            public void onFailure(Call<Person> call, Throwable t) {


            }
        });
    }


}
