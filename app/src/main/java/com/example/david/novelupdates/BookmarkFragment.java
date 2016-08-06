package com.example.david.novelupdates;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by David on 8/6/2016.
 */
public class BookmarkFragment extends Fragment implements View.OnClickListener{

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.bookmark_fragment,container,false);

        Button b = (Button) v.findViewById(R.id.Inbox);
        b.setOnClickListener(this);

        return v;
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.Inbox:
                Toast.makeText(getActivity(),"Send Selected", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
