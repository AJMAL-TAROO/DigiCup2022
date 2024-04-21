package com.freedomedition.zet.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;


import com.freedomedition.zet.R;

public class DashboardFragment extends Fragment  {

    private DashboardViewModel dashboardViewModel;
    WebView webViewDashboard;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                ViewModelProviders.of(this).get(DashboardViewModel.class);

        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);

        //freedomedition.github.io/eventFragment/
        webViewDashboard = root.findViewById(R.id.webViewDashboard);
        webViewDashboard.loadUrl("https://zenessexprimetoi.github.io/zetapp.event/");
        webViewDashboard.setWebViewClient(new WebViewClient());
        WebSettings webSettings = webViewDashboard.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webViewDashboard.getSettings().setDomStorageEnabled(true);
        webViewDashboard.getSettings().setAppCacheEnabled(true);
        webViewDashboard.getSettings().setLoadsImagesAutomatically(true);
        webViewDashboard.getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);


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
