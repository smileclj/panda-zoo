package com.panda.zoo.servlet.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.StringWriter;

/**
 * Created by huixiangdou on 2017/4/28.
 */
public class ZooServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println(1 / 0);
        OutputStreamWriter osw = new OutputStreamWriter(resp.getOutputStream());
        osw.write("123");
        osw.flush();
    }
}
