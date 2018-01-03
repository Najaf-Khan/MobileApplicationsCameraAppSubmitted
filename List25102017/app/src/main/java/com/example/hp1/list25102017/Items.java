package com.example.hp1.list25102017;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by hp1 on 10/25/2017.
 */

public class Items {
//    private int ImageArray[];
//    private String textArray[];

    ArrayList<Integer> imageArray = new ArrayList<Integer>();
    ArrayList<String> listArray = new ArrayList<String>();


    public ArrayList lists() {
        listArray.add("Badoo");
        listArray.add("");


        return listArray;
    }



    public ArrayList Images() {

        imageArray.add(R.drawable.badoo);
        imageArray.add(R.drawable.bebo);
        imageArray.add(R.drawable.behance);
        imageArray.add(R.drawable.blogger);
        imageArray.add(R.drawable.disqus);
        imageArray.add(R.drawable.deviantart);
        imageArray.add(R.drawable.digg);
        imageArray.add(R.drawable.facebook);
        imageArray.add(R.drawable.flickr);
        imageArray.add(R.drawable.youtube);
        imageArray.add(R.drawable.whatsapp);
        imageArray.add(R.drawable.viber);
        imageArray.add(R.drawable.vimeo);
        imageArray.add(R.drawable.twitter);
        imageArray.add(R.drawable.tumblr);
        imageArray.add(R.drawable.skype);
        imageArray.add(R.drawable.myspace);
        imageArray.add(R.drawable.telegram);
        return imageArray;

    }



}
