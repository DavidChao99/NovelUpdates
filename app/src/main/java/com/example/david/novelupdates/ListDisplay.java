package com.example.david.novelupdates;

        import android.os.Bundle;
        import android.app.Activity;
        import android.view.Menu;
        import android.widget.ArrayAdapter;
        import android.widget.ListView;

public class ListDisplay extends Activity {
    // Array of strings...
    String[] mobileArray = {""};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.activity_listview, mobileArray);

        ListView listView = (ListView) findViewById(R.id.mobile_list);
        listView.setAdapter(adapter);
    }
}
