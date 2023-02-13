package com.example.foodplanner.serach.presenter;

import java.util.List;

public interface CommunicationSearch {
    void setList(List<String> names);

    void setError(String msg);

    void setListSearch(String[] names);
}
