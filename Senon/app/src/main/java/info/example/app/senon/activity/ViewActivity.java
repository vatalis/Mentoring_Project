package info.example.app.senon.activity;
import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import info.example.app.senon.JsonModel;
import info.example.app.senon.R;
import info.example.app.senon.activity.LoginActivity;
import info.example.app.senon.activity.RegisterActivity;
import info.example.app.senon.adapter.JsonAdapter;
import info.example.app.senon.app.AppConfig;
import info.example.app.senon.helper.SQLiteHandler;
import info.example.app.senon.helper.SessionManager;


public class ViewActivity extends Activity {

    private String jsonResult;
    public String url = "http://192.168.216.1/android_api/getJson.php";
    ListView listView;
    List<Map<String, String>> android_api = new ArrayList<Map<String, String>>();
    ImageButton IB;
    JsonAdapter jsonAdapter;


    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_activity);
        listView = (ListView) findViewById(R.id.StaffList);
        json();
//        accessWebService();


        IB = (ImageButton) findViewById(R.id.imageButton);
        IB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(ViewActivity.this,
                        MainActivity.class);
                startActivity(i);
                finish();

            }
        });

    }

    public void json(){
        StringRequest stringRequest = new StringRequest(Request.Method.GET, AppConfig.URL_JSPN, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray array = jsonObject.getJSONArray("users");

                    ArrayList<JsonModel> arrayList = new ArrayList<>();
                    for(int i = 0; i < array.length(); i++) {
                        System.out.println("response lenght: " + response.length() + "current: " + i);
                        String ID = jsonObject.getJSONArray("users").getJSONObject(i).getString("id");
                        String Name = jsonObject.getJSONArray("users").getJSONObject(i).getString("name");
                        String Email = jsonObject.getJSONArray("users").getJSONObject(i).getString("email");
                        System.out.println(ID+ Name + Email);
                        JsonModel jsonModel = new JsonModel();
                        jsonModel.setId(ID);
                        jsonModel.setName(Name);
                        jsonModel.setEmail(Email);
                        arrayList.add(jsonModel);
                    }

                    jsonAdapter = new JsonAdapter(getApplicationContext(), arrayList);
                    listView.setAdapter(jsonAdapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        Volley.newRequestQueue(getApplicationContext()).add(stringRequest);
    }

}
