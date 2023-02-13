package com.example.foodplanner.Login;

import com.example.foodplanner.DBConnection.reomtlydatabase.DatabaseConnection;

public class PresenterLogin implements NetworkDelegate {
    private DatabaseConnection databaseConnection;
    private Communication communication;

    public PresenterLogin(DatabaseConnection databaseConnection, Communication communication) {
        this.databaseConnection = databaseConnection;
        this.communication = communication;
    }

    public void setDatabaseConnection(DatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection;
    }

    public void login(String email, String password) {
        databaseConnection.login(email, password, this);
    }


    public void signup(String email, String password) {
        databaseConnection.signup(email, password, this);
    }

    @Override
    public void onSuccessful() {
        communication.login();

    }

    @Override
    public void onFailure(String msg) {
        communication.onFailure(msg);

    }


}
