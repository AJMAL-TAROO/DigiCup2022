package com.freedomedition.zet.ui.StoreFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;


import com.freedomedition.zet.R;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

public class StoreFragment extends Fragment {

    private StoreViewModel storeViewModel;
    WebView webViewStore;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        storeViewModel =
                ViewModelProviders.of(this).get(StoreViewModel.class);

        View root = inflater.inflate(R.layout.fragment_store, container, false);


        webViewStore = root.findViewById(R.id.webViewStore);
        webViewStore.loadUrl("https://zenessexprimetoi.github.io/zetapp.store/");
        webViewStore.setWebViewClient(new WebViewClient());
        WebSettings webSettings = webViewStore.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webViewStore.getSettings().setDomStorageEnabled(true);
        webViewStore.getSettings().setAppCacheEnabled(true);
        webViewStore.getSettings().setLoadsImagesAutomatically(true);
        webViewStore.getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);


        return root;
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
