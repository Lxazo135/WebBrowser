package edu.temple.webbrowser;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

import org.w3c.dom.Text;



public class BrowserFragment extends Fragment {
    private WebView webView;
    private String url;
    int page;


    public BrowserFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static BrowserFragment newInstance(int page) {
        BrowserFragment fragment = new BrowserFragment();
        Bundle args = new Bundle();
        args.putInt("page", page);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        page = getArguments() != null ? getArguments().getInt("page") : 1;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_browser, container, false);
        webView = (WebView) v.findViewById(R.id.webView);
        return v;
    }

    public void loadUrl(String url){
        webView.loadUrl(url);
        this.url = url;
    }

    public String getUrl(){
        return url;
    }

    @Override
    public String toString(){

        return "BrowserFragment: " + String.valueOf(page);
    }

}
