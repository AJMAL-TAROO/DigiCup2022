package com.freedomedition.zet.ui.home;

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


public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    WebView webViewStore;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);

        View root = inflater.inflate(R.layout.fragment_home, container, false);


        webViewStore = root.findViewById(R.id.webViewStore);
        webViewStore.loadUrl("https://zenessexprimetoi.github.io/zetapp.home/");
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
