package edu.temple.webbrowser;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class MainActivity extends AppCompatActivity {
    FragmentManager fm;
    MyAdapter fragmentAdapter;
    int fragmentCount = 0;
    EditText editText;
    BrowserFragment currentFragment;
    ViewPager viewPager;
    String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);

        editText = (EditText) findViewById(R.id.editText);
        Button goButton = (Button) findViewById(R.id.goButton);
        viewPager = (ViewPager) findViewById(R.id.pager);

        fm = getSupportFragmentManager();
        fragmentAdapter = new MyAdapter(fm);
        viewPager.setAdapter(fragmentAdapter);
        viewPager.setOffscreenPageLimit(30);

        /*
        Uri uri = getIntent().getData();
        if (uri != null){
            url = uri.toString();
            System.out.println("Intent url: " + url);
            currentFragment = (BrowserFragment) fragmentAdapter.instantiateItem(viewPager, viewPager.getCurrentItem());
            System.out.println(viewPager.getCurrentItem());
            currentFragment.loadUrl(url);
        }
        */

        goButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentFragment = (BrowserFragment) fragmentAdapter.instantiateItem(viewPager, viewPager.getCurrentItem());
                currentFragment.loadUrl(editText.getText().toString());
            }
        });
    }

    @Override
    public void onNewIntent(Intent intent){
        Uri uri = intent.getData();
        if (uri != null){
            url = uri.toString();
            System.out.println("Intent url: " + url);
            viewPager.setCurrentItem(++fragmentCount);
            currentFragment = (BrowserFragment) fragmentAdapter.instantiateItem(viewPager, viewPager.getCurrentItem());
            System.out.println(viewPager.getCurrentItem());
            System.out.println("Fragment url: " + currentFragment.getUrl());
            currentFragment.loadUrl(url);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.action_bar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case R.id.action_newTab:
                viewPager.setCurrentItem(++fragmentCount);
                return true;

            case R.id.action_next:
                if(viewPager.getCurrentItem() < fragmentCount) {
                    viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
                    currentFragment = (BrowserFragment) fragmentAdapter.instantiateItem(viewPager, viewPager.getCurrentItem());
                    editText.setText(currentFragment.getUrl());
                }
                return true;

            case R.id.action_prev:
                viewPager.setCurrentItem(viewPager.getCurrentItem() - 1);
                currentFragment = (BrowserFragment) fragmentAdapter.instantiateItem(viewPager, viewPager.getCurrentItem());
                editText.setText(currentFragment.getUrl());
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
