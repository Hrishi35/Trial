package com.example.owner.trial;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.textclassifier.TextLinks;
import android.widget.Button;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    ListView listView;
    List<String> list;
    List<Pojo> testlist;
    ListAdapter listAdapter;
    RecyclerAdapter recyclerAdapter;
    Button b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_json);
        recyclerView = (RecyclerView) findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        listView = (ListView) findViewById(R.id.list);
        b=(Button)findViewById(R.id.b);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setupRecycler();
            }
        });
        list = new ArrayList<>();
        list.add("question1");
        list.add("question2");
        list.add("ques3");
        list.add("q4");
        listAdapter = new ListAdapter(getApplicationContext(), list);
        listView.setAdapter(listAdapter);
        getData();
    }

    private void getData() {
        final ProgressDialog dialog = new ProgressDialog(this);
        dialog.setTitle("Loading");
        dialog.setMessage("Please Wait ...");
        dialog.show();
        String url = "https://qmine.000webhostapp.com/image/retrieveMaths.php";
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject object = new JSONObject(response);
                    Log.d("response","rr"+response);
                    testlist= new ArrayList<>();
                    JSONArray array = object.getJSONArray("images");
                    for (int i=0;i<array.length();i++){
                        JSONObject ob =array.getJSONObject(i);
                        Pojo pojo = new Pojo();
                        pojo.setImageurl(ob.getString("url"));
                        pojo.setQuestion(ob.getString("question"));
                        pojo.setMarks(ob.getString("marks"));
                        testlist.add(pojo);
                    }
                    recyclerAdapter=new RecyclerAdapter(getApplicationContext(),testlist);
                    recyclerView.setAdapter(recyclerAdapter);

                }catch (Exception e){
                    e.printStackTrace();
                }

            }}, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                    dialog.dismiss();
                }
            });
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        queue.add(request);
        dialog.dismiss();
    }

    private void setupRecycler() {
        List<Pojo> pojoList=((RecyclerAdapter)recyclerAdapter).getPojoList();
        List<String> iList = new ArrayList<>();
        for (int i =0; i<pojoList.size();i++){
            Pojo pojo=pojoList.get(i);
            if (pojo.isSelected()==true){
                String myimage = pojo.getImageurl();
                iList.add(myimage);
                Log.d("Check","aa"+iList);
            }
        }

    }
}
//    StringRequest request = new StringRequest(Request.Method.POST, MainUrl, new Response.Listener<String>() {
//        @Override
//        public void onResponse(String response) {
//            Toast.makeText(getApplicationContext(), "Successfully Inserted", Toast.LENGTH_LONG).show();
//        }
//    }, new Response.ErrorListener() {
//        @Override
//        public void onErrorResponse(VolleyError error) {
//            Toast.makeText(getApplicationContext(), "ERROR", Toast.LENGTH_SHORT).show();
//        }
//    }) {
//        @Override
//        protected Map<String, String> getParams() throws AuthFailureError {
//
//            Map<String, String> Params = new HashMap<String, String>();
//            Params.put("question", question);
//            Params.put("marks", marks);
//            Params.put("difficulty", difficulty);
//            Params.put("image", baseurl);
//            Params.put("subject", sub);
//            return Params;
//
//        }
//
//    };
//
//        queue.add(request);
