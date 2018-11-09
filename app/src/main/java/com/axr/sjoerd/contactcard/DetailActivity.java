package com.axr.sjoerd.contactcard;

import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.axr.sjoerd.contactcard.DomainLayer.Person;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;

import java.io.InputStream;
import java.net.URL;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        //We know the object type.
        final Person person = (Person) getIntent().getSerializableExtra("Person");


        TextView name = (TextView)findViewById(R.id.nameTextView);
        TextView location = (TextView)findViewById(R.id.locationTextView);
        TextView email = (TextView)findViewById(R.id.emailTextView);


        final ImageView image = (ImageView)findViewById(R.id.imageView);

        final URL[] myUrl = {null};
        try {
            Thread thread = new Thread(new Runnable(){
                @Override
                public void run() {
                    try {
                        myUrl[0] = new URL(person.getProfileThumbnail());
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

        name.setText(person.getFirstName() + " " + person.getLastName());
        location.setText(person.getCity());
        email.setText(person.getEmail());
    }


}
