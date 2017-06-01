package com.tims.pretims;

/**
 * Created by mayocartad on 24.05.2017.
 */

public class User {

    /*---VARIABLES---*/

    private int idUser;
    private String useLogin;
    private String usePassword;
    private String useToken;

    /*---PROPRIETIES---*/
    public int getId()
    {
        return idUser;
    }

    public String getLogin()
    {
        return useLogin;
    }

    public String getPassword()
    {
        return usePassword;
    }

    public String getToken()
    {
        return useToken;
    }

    public void setId(int id)
    {
        idUser = id;
    }

    public void setLogin(String login)
    {
        useLogin = login;
    }

    public void setPassword(String password)
    {
        usePassword = password;
    }

    public void setToken(String token)
    {
        useToken = token;
    }
}
