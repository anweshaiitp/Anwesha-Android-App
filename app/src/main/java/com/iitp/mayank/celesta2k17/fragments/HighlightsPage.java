package com.iitp.mayank.celesta2k17.fragments;

import android.content.Context;
import android.content.ContextWrapper;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.iitp.mayank.celesta2k17.R;
import com.iitp.mayank.celesta2k17.adapters.HighlightsRecylerViewAdapter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * Created by mayank on 26/5/17.
 */

public class HighlightsPage extends android.support.v4.app.Fragment {

    RecyclerView highlightsRecylerView ;
    HighlightsRecylerViewAdapter highlightsRecylerViewAdapter;
    ArrayList<String> header = new ArrayList<>();
    ArrayList<String> details = new ArrayList<>();
    private String LOG_TAG = getClass().toString();
    File directory;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_highlights, container, false);
        directory = new ContextWrapper(getContext()).getDir("highlights", Context.MODE_PRIVATE);

        setData();

        highlightsRecylerView=(RecyclerView)rootView.findViewById(R.id.highlightsRecylerView) ;

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        highlightsRecylerView.setLayoutManager(linearLayoutManager);

        highlightsRecylerViewAdapter = new HighlightsRecylerViewAdapter(getContext() , header , details);
        highlightsRecylerView.setAdapter(highlightsRecylerViewAdapter);

        return rootView;
    }

    private void setData() {
        File file = new File(directory , "highlight.txt");
        try {
            FileReader fileReader = new FileReader(file);
            Scanner readScanner = new Scanner(fileReader);
            int i = 0;
            while(readScanner.hasNext())
            {
                StringTokenizer stringTokenizer = new StringTokenizer(readScanner.nextLine() , "::");
                header.add(stringTokenizer.nextToken());
                details.add(stringTokenizer.nextToken());
            }
            readScanner.close();
            fileReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
