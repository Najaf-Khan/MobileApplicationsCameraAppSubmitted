package com.example.hp1.recyclerviewexample;

/**
 * Created by hp1 on 11/2/2017.
 */

public class ItemData {

    private static ItemData instance;
    private static int index = 0;
    private ItemData(){}
    private int[] images = {R.drawable.pajarito1,
            R.drawable.pajarito2, R.drawable.pajarito3,
            R.drawable.pajarito4, R.drawable.pajarito5,
            R.drawable.pajarito6, R.drawable.pajarito7
    };

    public class Item {
        private String itemText;
        private int imageId;
        public Item(int id, String text){
            itemText = text;
            imageId = id;
        }
        public String getItemText(){
            return itemText;
        }
        public int getImageId(){
            return imageId;
        }
    }

    public static ItemData getInstance(){
        if(instance==null)
            instance = new ItemData();
        return instance;
    }

    public Item next(){
        String text = "Bird "+(index);
        int id = images[index%7];
        index++;
        return new Item(id, text);
    }


    public Item getNewItem(int image_id, String bird_name){
        return new Item(image_id, bird_name);
    }
}
