package com.oxilo.oioindia.modal;

import com.oxilo.oioindia.vo.Category;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ManuelVivo on 03/10/15.
 */
public class DirectoryData {

    private ArrayList<Category> allCategory;
    private ArrayList<Category> topCategory ;

    public DirectoryData() {
    }

    public ArrayList<Category> getAllCategory() {
        return allCategory;
    }

    public void setAllCategory(ArrayList<Category> category) {
        this.allCategory = category;
    }

    public ArrayList<Category> getTopCategory() {
        return topCategory;
    }

    public void setTopCategory(ArrayList<Category> topCategory) {
        this.topCategory = topCategory;
    }
}
