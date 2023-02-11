package com.example.foodplanner.APIconnection;

public interface RemoteDataSource {
    void enqueueCall(NetworkDelegate networkDelegate);
    void clearDisposable();
}
