package com.presentation.CJPack;


import javax.jws.WebService;

/**
 * Created by cj on 2016-11-02.
 */

@WebService
public class MyWebService {
    public void constructor(){

    }

    public String hello(String data){
        return "Hello " + data + ".";
    }
}
