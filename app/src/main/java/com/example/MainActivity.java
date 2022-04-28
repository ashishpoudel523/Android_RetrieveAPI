package com.example.apidemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.text1);
    }
        public void sendData(){
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            String url = "http://192.168.1.115/index.php";
            StringRequest stringRequest = new StringRequest(
                    Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Log.d("success", "sent successfully");
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            }){@Override
                public HashMap<String, String> getParams(){
                            HashMap<String, String> data = new HashMap<String, String>();
                            data.put("name", "abc");
                            data.put("address", "ktm");
                            data.put("phone", "213456");
                            return data;
                        }
            };
            requestQueue.add(stringRequest);
        }


        public void readData(){
        RequestQueue rQueue = Volley.newRequestQueue(this);
        String url = "http://192.168.1.115/mobileapi/index.php";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
            textView.setText(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show();
            }
    });
rQueue.add(stringRequest);
    }
}