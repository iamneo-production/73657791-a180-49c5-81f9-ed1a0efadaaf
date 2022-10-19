package com.examly.springapp.security.service.store;

import com.examly.springapp.security.service.UserDetailsImpl;

public class MyCredentials {
    private static UserDetailsImpl userDetailsImpl;
    public static UserDetailsImpl getInstancDetailsImpl(){
        return userDetailsImpl;
    }
    public static void setInstanceDetailsImpl(UserDetailsImpl u){
        userDetailsImpl=u;
    }
}

