package com.freedomedition.zet.ui.donations;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.firebase.ui.database.FirebaseListAdapter;
import com.firebase.ui.database.FirebaseListOptions;
import com.freedomedition.zet.UserHelperClass;
import com.freedomedition.zet.pushDonation;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.freedomedition.zet.R;

public class DonationsFragment extends Fragment {

    private DonationsViewModel donationsViewModel;

    TextView labelDonation;
    ListView listView;


    //For Firebase usage...
    FirebaseListAdapter adapter;
    DatabaseReference reff;

    SharedPreferences shared_str_imageurl;
    SharedPreferences shared_str_title;
    SharedPreferences shared_str_maxprogress;
    SharedPreferences shared_str_donated;
    SharedPreferences shared_str_more;
    SharedPreferences shared_str_video_link;
    SharedPreferences shared_str_locationX;
    SharedPreferences shared_str_locationY;
    SharedPreferences shared_str_number;
    SharedPreferences shared_str_image_vector;
    SharedPreferences shared_str_personName;
    SharedPreferences shared_str_descriptiontxt;
    SharedPreferences shared_str_endingtxt;
    SharedPreferences shared_str_category;


    String str_imageurl;
    String str_title;
    String str_maxprogress;
    String str_donated;
    String str_more;
    String str_video_link;
    String str_locationX;
    String  str_locationY;
    String  str_number;
    String  str_image_vector;
    String str_personName;
    String str_descriptiontxt;
    String str_endingtxt;
    String str_categorytxt;

     ImageView imageView;
     TextView title;
     ProgressBar progressBar;
     TextView amountDonatedtxt;
     TextView amountToGotxt;
     TextView personName;
     TextView descriptiontxt;
     TextView endingtxt;
     TextView categorytxt;

     Object model2;



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        donationsViewModel =
                ViewModelProviders.of(this).get(DonationsViewModel.class);


        View root = inflater.inflate(R.layout.fragment_donations, container, false);
        listView = root.findViewById(R.id.listView);


        Query query = FirebaseDatabase.getInstance().getReference().child("FEED");
        FirebaseListOptions<UserHelperClass> options = new FirebaseListOptions.Builder<UserHelperClass>()
                .setLayout(R.layout.message_layout)
                .setLifecycleOwner(getViewLifecycleOwner())
                .setQuery(query, UserHelperClass.class)
                .build();

        adapter = new FirebaseListAdapter(options) {
            @Override
            protected void populateView(View v, Object model, int position) {

                imageView = v.findViewById(R.id.imageView);
                title = v.findViewById(R.id.title);
                progressBar = v.findViewById(R.id.progressBar);
                amountDonatedtxt = v.findViewById(R.id.amountDonatedtxt);
                amountToGotxt = v.findViewById(R.id.amountToGotxt);
                personName = v.findViewById(R.id.personName);
                descriptiontxt = v.findViewById(R.id.descriptiontxt);
                endingtxt = v.findViewById(R.id.endingtxt);
                categorytxt = v.findViewById(R.id.categorytxt);

                final UserHelperClass helperClass = (UserHelperClass) model;


                passModel(model, helperClass);


                RequestOptions options = new RequestOptions();
                Glide.with(getContext())
                        .load(str_imageurl)
                        .apply(options.circleCropTransform())
                        .placeholder(new ColorDrawable(Color.LTGRAY))
                        .into(imageView);


                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        forceSetValues();
                        passModel(model2, helperClass);

                        Intent intent = new Intent(getContext(), pushDonation.class);
                        startActivity(intent);
                    }
                });


                title.setText(str_title);
                //title.setPaintFlags(title.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
                title.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        forceSetValues();
                        passModel(model2, helperClass);

                        Intent intent = new Intent(getContext(), pushDonation.class);
                        startActivity(intent);
                    }
                });

                personName.setText(str_personName);

                descriptiontxt.setText(str_descriptiontxt);

                endingtxt.setText(str_endingtxt);

                categorytxt.setText(str_categorytxt);

                progressBar.setMax(Integer.valueOf(str_maxprogress));
                progressBar.setProgress(Integer.valueOf(str_donated));

                amountDonatedtxt.setText("Rs " + str_donated + " donated");

                if(Integer.valueOf(str_donated) >= Integer.valueOf(str_maxprogress)){
                    amountToGotxt.setText("COMPLETED");
                }
                else{
                    amountToGotxt.setText("Rs " + (Integer.valueOf(str_maxprogress) - Integer.valueOf(str_donated)) + " to go");
                }

            }
        };
        listView.setAdapter(adapter);
        return root;
    }

    public void passModel(Object model, UserHelperClass helperClass){

        model2 = model;

        str_imageurl = helperClass.getImageUrl();
        str_title = helperClass.getStr_title();
        str_maxprogress = helperClass.getStr_maxprogress();
        str_donated = helperClass.getStr_currentprogress();
        str_more = helperClass.getStr_description();
        str_video_link = helperClass.getStr_videolink();
        str_locationX = helperClass.getStr_locationX();
        str_locationY = helperClass.getStr_locationY();
        str_number = helperClass.getStr_number();
        str_image_vector = helperClass.getStr_image_vector();
        str_personName = helperClass.getStr_personName();
        str_descriptiontxt = helperClass.getStr_description();
        str_endingtxt = helperClass.getStr_ending();
        str_categorytxt = helperClass.getStr_category();



        shared_str_imageurl = getContext().getSharedPreferences("shared_str_imageurl", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor1 = shared_str_imageurl.edit();
        editor1.putString("key1", str_imageurl);
        editor1.commit();

        shared_str_title = getContext().getSharedPreferences("shared_str_title", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor2 = shared_str_title.edit();
        editor2.putString("key2", str_title);
        editor2.commit();

        shared_str_maxprogress = getContext().getSharedPreferences("shared_str_maxprogress", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor3 = shared_str_maxprogress.edit();
        editor3.putString("key3" , str_maxprogress);
        editor3.commit();

        shared_str_donated = getContext().getSharedPreferences("shared_str_donated", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor4 = shared_str_donated.edit();
        editor4.putString("key4" , str_donated);
        editor4.commit();

        shared_str_more = getContext().getSharedPreferences("shared_str_more", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor5 = shared_str_more.edit();
        editor5.putString("key5" , str_more);
        editor5.commit();

        shared_str_video_link = getContext().getSharedPreferences("shared_str_video_link", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor6 = shared_str_video_link.edit();
        editor6.putString("key6" , str_video_link);
        editor6.commit();

        shared_str_locationX = getContext().getSharedPreferences("shared_str_locationX", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor7 = shared_str_locationX.edit();
        editor7.putString("key7" , str_locationX);
        editor7.commit();

        shared_str_locationY = getContext().getSharedPreferences("shared_str_locationY", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor8 = shared_str_locationY.edit();
        editor8.putString("key8" , str_locationY);
        editor8.commit();

        shared_str_number = getContext().getSharedPreferences("shared_str_number", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor9 = shared_str_number.edit();
        editor9.putString("key9" , str_number);
        editor9.commit();

        shared_str_image_vector = getContext().getSharedPreferences("shared_str_image_vector", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor10 = shared_str_image_vector.edit();
        editor10.putString("key10" , str_image_vector);
        editor10.commit();

        shared_str_personName = getContext().getSharedPreferences("shared_str_personName", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor11 = shared_str_personName.edit();
        editor11.putString("key11" , str_personName);
        editor11.commit();

        shared_str_descriptiontxt = getContext().getSharedPreferences("shared_str_descriptiontxt", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor12 = shared_str_descriptiontxt.edit();
        editor12.putString("key12" , str_descriptiontxt);
        editor12.commit();

        shared_str_endingtxt = getContext().getSharedPreferences("shared_str_endingtxt", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor13 = shared_str_endingtxt.edit();
        editor13.putString("key13" , str_endingtxt);
        editor13.commit();

        shared_str_category = getContext().getSharedPreferences("shared_str_category", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor14 = shared_str_category.edit();
        editor14.putString("key14" , str_categorytxt);
        editor14.commit();


    }

    public void forceSetValues(){
        reff = FirebaseDatabase.getInstance().getReference().child("FEED").child("DONATION"+ str_number);
        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                str_imageurl = snapshot.child("imageUrl").getValue().toString();
                str_title = snapshot.child("str_title").getValue().toString();
                str_maxprogress = snapshot.child("str_maxprogress").getValue().toString();
                str_donated = snapshot.child("str_currentprogress").getValue().toString();
                str_more = snapshot.child("str_description").getValue().toString();
                str_video_link = snapshot.child("str_videolink").getValue().toString();
                str_locationX = snapshot.child("str_locationX").getValue().toString();
                str_locationY = snapshot.child("str_locationY").getValue().toString();
                str_number = snapshot.child("str_number").getValue().toString();
                str_image_vector = snapshot.child("str_image_vector").getValue().toString();
                str_personName = snapshot.child("str_personName").getValue().toString();
                str_descriptiontxt = snapshot.child("str_description").getValue().toString();
                str_endingtxt = snapshot.child("str_ending").getValue().toString();
                str_categorytxt = snapshot.child("str_category").getValue().toString();
            }
            @Override
            public void onCancelled(DatabaseError error) {
                Toast.makeText(getContext(), "Database error", Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onResume(){
        super.onResume();
        ((AppCompatActivity)getActivity()).getSupportActionBar().hide();
    }

    @Override
    public void onStop(){
        super.onStop();
        ((AppCompatActivity)getActivity()).getSupportActionBar().show();
    }
}
