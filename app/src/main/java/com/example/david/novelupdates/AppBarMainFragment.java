package com.example.david.novelupdates;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Created by David on 8/6/2016.
 */
public class AppBarMainFragment extends Fragment implements View.OnClickListener{

    View v;
    ListView listView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.app_bar_main,container,false);

        FloatingActionButton fab = (FloatingActionButton) v.findViewById(R.id.fab);
        fab.setOnClickListener(this);

        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(getActivity());
        String url ="http://www.novelupdates.com";

    // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Document doc = Jsoup.parse(response);
                        final Elements listOfUpdates =  doc.select("table td a");

                        String s[] = new String[listOfUpdates.size()-1];
                        for(int i = 0; i < listOfUpdates.size()-1; i++) {
                            s[i] = "";
                        }
                        int j = 0;
                        int numCounted = 0;
                        final int JtoIArr[] = new int[listOfUpdates.size()-1];
                        for(int i = 3; i < listOfUpdates.size()-3; i ++)  {
                            if(listOfUpdates.get(i).text() == "" && i < listOfUpdates.size()-1 ) {
                                i+=1;
                            }

                            //s[j] = listOfUpdates.get(i).text() + "      " + listOfUpdates.get(i+1).text() + "      " + listOfUpdates.get(i+2).text();
                            if(!(numCounted%3 ==0)) {
                                s[j]+="          ";
                                if(numCounted%3 == 1) {
                                    JtoIArr[j] = i;
                                }
                            }
                            s[j] += listOfUpdates.get(i).text();
                            numCounted++;
                            if(numCounted%3 == 0) {
                                j++;
                            }
                        }

                        ArrayAdapter adapter = new ArrayAdapter<String>(getActivity(), R.layout.activity_listview, s);

                        listView = (ListView) v.findViewById(R.id.mobile_list);
                        listView.setAdapter(adapter);

                        listView.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                String item = (String) listView.getItemAtPosition(position);
                                Toast.makeText(getActivity(),"You selected : " + item +"at position: "+ position,Toast.LENGTH_SHORT).show();
                                String url = listOfUpdates.get(JtoIArr[position]).attr("href");
                                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                                startActivity(browserIntent);

                            }
                        });



                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
    // Add the request to the RequestQueue.
        queue.add(stringRequest);



        return v;
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.fab:
                Toast.makeText(getActivity(),"Send Selected", Toast.LENGTH_SHORT).show();
                break;
        }
    }


}
