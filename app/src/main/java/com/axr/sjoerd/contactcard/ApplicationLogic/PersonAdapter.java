package com.axr.sjoerd.contactcard.ApplicationLogic;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.axr.sjoerd.contactcard.DomainLayer.Person;
import com.axr.sjoerd.contactcard.R;

import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class PersonAdapter extends ArrayAdapter<Person> {
    private Context mContext;
    private List<Person> personList = new ArrayList<>();

    public PersonAdapter(@NonNull Context context, @SuppressLint("SupportAnnotationUsage") ArrayList<Person> list) {
        super(context, 0 , list);
        mContext = context;
        personList = list;
    }

    @Override
    public int getCount() {
        Log.i("Counted objects ", String.valueOf(personList.size()));
        return personList.size();
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        View listItem = convertView;

        if(listItem == null)
            listItem = LayoutInflater.from(mContext).inflate(R.layout.list_item,parent,false);

        final Person currentPerson = personList.get(position);
        //Movie currentMovie = personList.get(position);

        final ImageView image = (ImageView)listItem.findViewById(R.id.imageView_poster);

        final URL[] myUrl = {null};
        try {
            Thread thread = new Thread(new Runnable(){
                @Override
                public void run() {
                    try {
                        myUrl[0] = new URL(currentPerson.getProfileThumbnail());
                        InputStream inputStream = (InputStream) myUrl[0].getContent();
                        Drawable drawable = Drawable.createFromStream(inputStream, null);
                        image.setImageDrawable(drawable);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
            thread.start();
            thread.join();


        } catch (Exception e) {
            e.printStackTrace();
        }



        TextView name = (TextView) listItem.findViewById(R.id.textView_name);
        name.setText(currentPerson.getFirstName() + " " + currentPerson.getLastName());

        TextView release = (TextView) listItem.findViewById(R.id.textView_release);
        release.setText("From: "+ currentPerson.getCity());

        return listItem;
    }
}
