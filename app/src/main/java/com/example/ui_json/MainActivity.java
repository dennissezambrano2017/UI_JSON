package com.example.ui_json;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private TextView textView;
    private RequestQueue requeque;
    String url="https://gorest.co.in/public/v1/users";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.text_view_result);
        requeque= Volley.newRequestQueue(this);

        GetApiData();

    }
    private void GetApiData(){
        ArrayList<String> lstDatos = new ArrayList<String>();
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("data");

                            for (int i=0; i <jsonArray.length();i++){
                                JSONObject data = jsonArray.getJSONObject(i);
                                lstDatos.add(data.getString("name").toString()+" , "+
                                        data.getString("email")+" , "+
                                        data.getString("gender")+" , "+
                                        data.getString("status")+"\n\n");
                            }
                            textView.setKeyListener(null);
                            textView.setText(lstDatos.toString());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                textView.append(String.valueOf("ERROR"));
                error.printStackTrace();
            }
        });
        requeque.add(request);
    }
}