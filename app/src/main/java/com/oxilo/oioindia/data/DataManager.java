package com.oxilo.oioindia.data;

import com.oxilo.oioindia.modal.DirectoryData;

/**
 * Created by ManuelVivo on 03/10/15.
 */
public class DataManager {

    private static DataManager instance;
    private DirectoryData userData;

    private DataManager() {

        userData = new DirectoryData();
    }

    public static DataManager getInstance() {

        synchronized (DataManager.class) {
            if (instance == null) {
                instance = new DataManager();
            }

            return instance;
        }
    }

    public DirectoryData getDirectoryData() {

        return userData;
    }
}
