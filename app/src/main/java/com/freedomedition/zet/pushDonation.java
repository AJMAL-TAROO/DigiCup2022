package com.freedomedition.zet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.billingclient.api.BillingClient;
import com.android.billingclient.api.BillingClientStateListener;
import com.android.billingclient.api.BillingFlowParams;
import com.android.billingclient.api.BillingResult;
import com.android.billingclient.api.ConsumeParams;
import com.android.billingclient.api.ConsumeResponseListener;
import com.android.billingclient.api.Purchase;
import com.android.billingclient.api.PurchasesUpdatedListener;
import com.android.billingclient.api.SkuDetails;
import com.android.billingclient.api.SkuDetailsParams;
import com.android.billingclient.api.SkuDetailsResponseListener;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.github.chrisbanes.photoview.PhotoView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class pushDonation extends AppCompatActivity {



    String[] items = {"Rs 25", "Rs 50", "Rs 100", "Rs 200", "Rs 500", "Rs 1000"};
    int value;
    int amount;

    //For Firebase usage...
    DatabaseReference reff;
    FirebaseDatabase rootNode;
    DatabaseReference reference;

    //For payment usage...
    private static final String TAG = "InAppBilling";
    static final String id_25rupees= "id_25rupees";
    static final String id_50rupees= "id_50rupees";
    static final String id_100rupees= "id_100rupees";
    static final String id_200rupees= "id_200rupees";
    static final String id_500rupees= "id_500rupees";
    static final String id_1000rupees= "id_1000rupees";
    BillingClient billingClient;

    //All widgets...
    ImageView imageView;
    TextView title;
    ProgressBar progressBar;
    TextView amountDonatedtxt;
    TextView amountToGotxt;
    ImageView imgMore;
    ImageView imgVideo;
    ImageView imgLocation;
    Button donateBtn;
    TextView labelDonation;
    ListView listView;
    TextView personName;
    TextView descriptiontxt;
    TextView endingtxt;
    TextView categorytxt;
    TextView watchVideotxt;
    TextView seeLocationtxt;

    SharedPreferences shared_amount;

    int int_temp;
    String str_temp;

    WebView webViewLocation;

    //All sharedpreferences...
    /*SharedPreferences shared_str_imageurl;
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
    SharedPreferences shared_str_category;*/

    //All strings...
    /*String str_imageurl;
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
    String str_categorytxt;*/



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_push_donation);

        imageView = findViewById(R.id.imageView);
        title = findViewById(R.id.title);
        progressBar = findViewById(R.id.progressBar);
        amountDonatedtxt = findViewById(R.id.amountDonatedtxt);
        amountToGotxt = findViewById(R.id.amountToGotxt);
        imgVideo = findViewById(R.id.imgVideo);
        imgLocation = findViewById(R.id.imgLocation);
        donateBtn = findViewById(R.id.donateBtn);
        personName = findViewById(R.id.personName);
        descriptiontxt = findViewById(R.id.descriptiontxt);
        endingtxt = findViewById(R.id.endingtxt);
        categorytxt = findViewById(R.id.categorytxt);
        watchVideotxt = findViewById(R.id.watchVideotxt);
        seeLocationtxt = findViewById(R.id.seeLocationtxt);
        webViewLocation = findViewById(R.id.webViewLocation);


        SharedPreferences shared_str_imageurl = getSharedPreferences("shared_str_imageurl", Context.MODE_PRIVATE);
        SharedPreferences shared_str_title = getSharedPreferences("shared_str_title", Context.MODE_PRIVATE);
        SharedPreferences shared_str_maxprogress = getSharedPreferences("shared_str_maxprogress", Context.MODE_PRIVATE);
        SharedPreferences shared_str_donated = getSharedPreferences("shared_str_donated", Context.MODE_PRIVATE);
        SharedPreferences shared_str_more = getSharedPreferences("shared_str_more", Context.MODE_PRIVATE);
        SharedPreferences shared_str_video_link = getSharedPreferences("shared_str_video_link", Context.MODE_PRIVATE);
        SharedPreferences shared_str_locationX = getSharedPreferences("shared_str_locationX", Context.MODE_PRIVATE);
        SharedPreferences shared_str_locationY = getSharedPreferences("shared_str_locationY", Context.MODE_PRIVATE);
        SharedPreferences shared_str_number = getSharedPreferences("shared_str_number", Context.MODE_PRIVATE);
        SharedPreferences shared_str_image_vector = getSharedPreferences("shared_str_image_vector", Context.MODE_PRIVATE);
        SharedPreferences shared_str_personName = getSharedPreferences("shared_str_personName", Context.MODE_PRIVATE);
        SharedPreferences shared_str_descriptiontxt = getSharedPreferences("shared_str_descriptiontxt", Context.MODE_PRIVATE);
        SharedPreferences shared_str_endingtxt = getSharedPreferences("shared_str_endingtxt", Context.MODE_PRIVATE);
        SharedPreferences shared_str_category = getSharedPreferences("shared_str_category", Context.MODE_PRIVATE);


        final String str_imageurl = shared_str_imageurl.getString("key1", "default");
        final String str_title = shared_str_title.getString("key2", "default");
        final String str_maxprogress = shared_str_maxprogress.getString("key3", "default");
        final String str_donated = shared_str_donated.getString("key4", "default");
        final String str_more = shared_str_more.getString("key5", "default");
        final String str_video_link = shared_str_video_link.getString("key6", "default");
        final String str_locationX = shared_str_locationX.getString("key7", "default");
        final String str_locationY = shared_str_locationY.getString("key8", "default");
        final String str_number = shared_str_number.getString("key9", "default");
        final String str_image_vector = shared_str_image_vector.getString("key10", "default");
        final String str_personName = shared_str_personName.getString("key11", "default");
        final String str_descriptiontxt = shared_str_descriptiontxt.getString("key12", "default");
        final String str_endingtxt = shared_str_endingtxt.getString("key13", "default");
        final String str_categorytxt = shared_str_category.getString("key14", "default");


        billingClient = BillingClient.newBuilder(pushDonation.this)
                .setListener(purchasesUpdatedListener)
                .enablePendingPurchases()
                .build();

        billingClient.startConnection(new BillingClientStateListener() {
            @Override
            public void onBillingSetupFinished(BillingResult billingResult) {
                if(billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK){

                }
            }
            @Override
            public void onBillingServiceDisconnected() {

            }
        });


        RequestOptions options = new RequestOptions();
        Glide.with(getApplicationContext())
                .load(str_imageurl)
                .apply(options.circleCropTransform())
                .placeholder(new ColorDrawable(Color.LTGRAY))
                .into(imageView);


        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomDialog dialog = new CustomDialog(pushDonation.this);
                View view = getLayoutInflater().inflate(R.layout.activity_pop_image_only, null);
                dialog.setView(view);
                dialog.setCancelable(true);

                PhotoView photoView = view.findViewById(R.id.photoView);

                Glide.with(pushDonation.this)
                        .load(str_imageurl)
                        .centerCrop()
                        .placeholder(R.drawable.ic_launcher_foreground)
                        .into(photoView);
                dialog.show();
            }
        });


        title.setText(str_title);

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


        imgVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent appIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:" + str_video_link));
                Intent webIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://youtu.be/" + str_video_link));
                try{
                    startActivity(appIntent);
                } catch (Exception e) {
                    startActivity(webIntent);
                }

            }
        });

        watchVideotxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent appIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:" + str_video_link));
                Intent webIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://youtu.be/" + str_video_link));
                try{
                    startActivity(appIntent);
                } catch (Exception e) {
                    startActivity(webIntent);
                }
            }
        });

        imgLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uri = "http://maps.google.com/maps?q=loc:" + Float.parseFloat(str_locationX) + "," + Float.parseFloat(str_locationY) + " (" + "Location" + ")";
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                intent.setPackage("com.google.android.apps.maps");
                startActivity(intent);
            }
        });

        seeLocationtxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uri = "http://maps.google.com/maps?q=loc:" + Float.parseFloat(str_locationX) + "," + Float.parseFloat(str_locationY) + "(" + "Location" + ")";
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                intent.setPackage("com.google.android.apps.maps");
                startActivity(intent);
            }
        });


        donateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                        final CustomDialog dialog = new CustomDialog(pushDonation.this);
                        View view = getLayoutInflater().inflate(R.layout.popup_donation, null);
                        dialog.setView(view);
                        dialog.setCancelable(true);

                        listView = view.findViewById(R.id.listView);
                        labelDonation = view.findViewById(R.id.labelDonation);

                        labelDonation.setText(str_title);


                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(),
                                android.R.layout.simple_list_item_1, android.R.id.text1, items);

                        listView.setAdapter(adapter);

                        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                                List<String> skuList = new ArrayList<>();

                        switch (position){
                            case 0:

                                amount = 25;
                                shared_amount = getSharedPreferences("shared_amount", Context.MODE_PRIVATE);
                                SharedPreferences.Editor editor1 = shared_amount.edit();
                                editor1.putInt("keyAmount" , amount);
                                editor1.commit();

                                skuList.add(id_25rupees);
                                call_billing(skuList);
                                dialog.dismiss();
                                break;
                            case 1:

                                amount = 50;
                                shared_amount = getSharedPreferences("shared_amount", Context.MODE_PRIVATE);
                                SharedPreferences.Editor editor2 = shared_amount.edit();
                                editor2.putInt("keyAmount" , amount);
                                editor2.commit();

                                skuList.add(id_50rupees);
                                call_billing(skuList);
                                dialog.dismiss();
                                break;
                            case 2:

                                amount = 100;
                                shared_amount = getSharedPreferences("shared_amount", Context.MODE_PRIVATE);
                                SharedPreferences.Editor editor3 = shared_amount.edit();
                                editor3.putInt("keyAmount" , amount);
                                editor3.commit();

                                skuList.add(id_100rupees);
                                call_billing(skuList);
                                dialog.dismiss();
                                break;
                            case 3:

                                amount = 200;
                                shared_amount = getSharedPreferences("shared_amount", Context.MODE_PRIVATE);
                                SharedPreferences.Editor editor4 = shared_amount.edit();
                                editor4.putInt("keyAmount" , amount);
                                editor4.commit();

                                skuList.add(id_200rupees);
                                call_billing(skuList);
                                dialog.dismiss();
                                break;
                            case 4:

                                amount = 500;
                                shared_amount = getSharedPreferences("shared_amount", Context.MODE_PRIVATE);
                                SharedPreferences.Editor editor5 = shared_amount.edit();
                                editor5.putInt("keyAmount" , amount);
                                editor5.commit();

                                skuList.add(id_500rupees);
                                call_billing(skuList);
                                dialog.dismiss();
                                break;
                            case 5:

                                amount = 5000;
                                shared_amount = getSharedPreferences("shared_amount", Context.MODE_PRIVATE);
                                SharedPreferences.Editor editor6 = shared_amount.edit();
                                editor6.putInt("keyAmount" , amount);
                                editor6.commit();

                                skuList.add(id_1000rupees);
                                call_billing(skuList);
                                dialog.dismiss();
                                break;
                        }
                        dialog.dismiss();
                    }
                });

                dialog.show();

            }
        });


        String uri = "http://maps.google.com/maps?q=loc:" + Float.parseFloat(str_locationX) + "," + Float.parseFloat(str_locationY) + " (" + "Location" + ")";
        webViewLocation.loadUrl(uri);
        webViewLocation.setWebViewClient(new WebViewClient());
        WebSettings webSettings = webViewLocation.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webViewLocation.getSettings().setDomStorageEnabled(true);
        webViewLocation.getSettings().setAppCacheEnabled(true);
        webViewLocation.getSettings().setLoadsImagesAutomatically(true);
        webViewLocation.getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        webViewLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uri = "http://maps.google.com/maps?q=loc:" + Float.parseFloat(str_locationX) + "," + Float.parseFloat(str_locationY) + " (" + "Location" + ")";
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                intent.setPackage("com.google.android.apps.maps");
                startActivity(intent);
            }
        });

    }

    private PurchasesUpdatedListener purchasesUpdatedListener = new PurchasesUpdatedListener() {
        @Override
        public void onPurchasesUpdated(BillingResult billingResult, List<Purchase> purchases) {
            if(billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK && purchases !=null){
                for (Purchase purchase : purchases){
                    handlePurchase(purchase);
                }
            } else if(billingResult.getResponseCode() == BillingClient.BillingResponseCode.USER_CANCELED){
                // Handle an error caused by a user cancelling the purchase flow.
                Log.d(TAG, "User Canceled");
            }else{
                // Handle any other error codes.
                Log.d(TAG, "Other code");
                Toast.makeText(pushDonation.this,"An error occurred", Toast.LENGTH_LONG).show();
            }
        }
    };



    public void handlePurchase(Purchase purchase){
        // Purchase retrieved from BillingClient#queryPurchases or your PurchasesUpdatedListener.

        // Purchase purchase = ...;

        if(purchase.getSku().equals(id_25rupees) || purchase.getSku().equals(id_50rupees) || purchase.getSku().equals(id_100rupees)
                || purchase.getSku().equals(id_200rupees) || purchase.getSku().equals(id_500rupees) || purchase.getSku().equals(id_1000rupees)){
            ConsumeParams consumeParams = ConsumeParams.newBuilder()
                    .setPurchaseToken(purchase.getPurchaseToken())
                    .build();
            ConsumeResponseListener listener = new ConsumeResponseListener() {
                @Override
                public void onConsumeResponse(BillingResult billingResult, String purchaseToken) {
                    if(billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK){
                        //** Handle the success of the consume operation**//


                        SharedPreferences shared_str_imageurl = getSharedPreferences("shared_str_imageurl", Context.MODE_PRIVATE);
                        SharedPreferences shared_str_title = getSharedPreferences("shared_str_title", Context.MODE_PRIVATE);
                        SharedPreferences shared_str_maxprogress = getSharedPreferences("shared_str_maxprogress", Context.MODE_PRIVATE);
                        SharedPreferences shared_str_donated = getSharedPreferences("shared_str_donated", Context.MODE_PRIVATE);
                        SharedPreferences shared_str_more = getSharedPreferences("shared_str_more", Context.MODE_PRIVATE);
                        SharedPreferences shared_str_video_link = getSharedPreferences("shared_str_video_link", Context.MODE_PRIVATE);
                        SharedPreferences shared_str_locationX = getSharedPreferences("shared_str_locationX", Context.MODE_PRIVATE);
                        SharedPreferences shared_str_locationY = getSharedPreferences("shared_str_locationY", Context.MODE_PRIVATE);
                        SharedPreferences shared_str_number = getSharedPreferences("shared_str_number", Context.MODE_PRIVATE);
                        SharedPreferences shared_str_image_vector = getSharedPreferences("shared_str_image_vector", Context.MODE_PRIVATE);
                        SharedPreferences shared_str_personName = getSharedPreferences("shared_str_personName", Context.MODE_PRIVATE);
                        SharedPreferences shared_str_descriptiontxt = getSharedPreferences("shared_str_descriptiontxt", Context.MODE_PRIVATE);
                        SharedPreferences shared_str_endingtxt = getSharedPreferences("shared_str_endingtxt", Context.MODE_PRIVATE);
                        SharedPreferences shared_str_category = getSharedPreferences("shared_str_category", Context.MODE_PRIVATE);


                        final String str_imageurl = shared_str_imageurl.getString("key1", "default");
                        final String str_title = shared_str_title.getString("key2", "default");
                        final String str_maxprogress = shared_str_maxprogress.getString("key3", "default");
                        final String str_donated = shared_str_donated.getString("key4", "default");
                        final String str_more = shared_str_more.getString("key5", "default");
                        final String str_video_link = shared_str_video_link.getString("key6", "default");
                        final String str_locationX = shared_str_locationX.getString("key7", "default");
                        final String str_locationY = shared_str_locationY.getString("key8", "default");
                        final String str_number = shared_str_number.getString("key9", "default");
                        final String str_image_vector = shared_str_image_vector.getString("key10", "default");
                        final String str_personName = shared_str_personName.getString("key11", "default");
                        final String str_descriptiontxt = shared_str_descriptiontxt.getString("key12", "default");
                        final String str_endingtxt = shared_str_endingtxt.getString("key13", "default");
                        final String str_categorytxt = shared_str_category.getString("key14", "default");

                        int_temp = shared_amount.getInt("keyAmount", 999);

                        value = Integer.valueOf(str_donated) + int_temp;

                        progressBar.setProgress(value);
                        rootNode = FirebaseDatabase.getInstance();
                        reference = rootNode.getReference("FEED");
                        UserHelperClass helperClass1 = new UserHelperClass(str_title, str_maxprogress, String.valueOf(value), str_descriptiontxt,  str_video_link,  str_locationX, str_locationY,  str_imageurl,  str_image_vector, str_number,
                                str_personName, str_endingtxt, str_categorytxt);
                        reference.child("DONATION"+str_number).setValue(helperClass1);
                        Toast.makeText(getApplicationContext(), "Donation successful!", Toast.LENGTH_LONG).show();

                        progressBar.setProgress(value);

                        amountDonatedtxt.setText("Rs " + String.valueOf(value) + " donated");

                        if(Integer.valueOf(str_donated) >= Integer.valueOf(str_maxprogress)){
                            amountToGotxt.setText("COMPLETED");
                        }
                        else{
                            amountToGotxt.setText("Rs " + (Integer.valueOf(str_maxprogress) - Integer.valueOf(str_donated)) + " to go");
                        }

                        shared_str_donated = getSharedPreferences("shared_str_donated", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = shared_str_donated.edit();
                        editor.putString("key4" , String.valueOf(value));
                        editor.commit();

                    }
                }
            };
            billingClient.consumeAsync(consumeParams, listener);

        }
    }



    public void call_billing(List<String> skuList){
        SkuDetailsParams skuDetailsParams = SkuDetailsParams.newBuilder()
                .setSkusList(skuList).setType(BillingClient.SkuType.INAPP).build();
        billingClient.querySkuDetailsAsync(skuDetailsParams,
                new SkuDetailsResponseListener() {
                    @Override
                    public void onSkuDetailsResponse(BillingResult billingResult, List<SkuDetails> list) {
                        BillingFlowParams billingFlowParams = BillingFlowParams.newBuilder()
                                .setSkuDetails(list.get(0))
                                .build();
                        int billingResponseCode = billingClient.launchBillingFlow(pushDonation.this, billingFlowParams).getResponseCode();
                    }
                });
    }

}
