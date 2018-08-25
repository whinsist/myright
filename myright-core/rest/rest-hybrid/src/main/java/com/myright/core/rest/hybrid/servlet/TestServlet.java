package com.myright.core.rest.hybrid.servlet;

import com.myright.infra.cloud.CloudUtil;
import com.myright.infra.common.DateUtil;
import com.myright.infra.common.JsonUtil;
import com.myright.infra.mq.MQUtil;
import com.myright.infra.security.SecurityUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class TestServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, Object> map = new HashMap<String,Object>();
        map.put("name", "你好");

        request.setAttribute("json", JsonUtil.toJson(map));
        request.setAttribute("date", DateUtil.formatDate());
        request.setAttribute("mqInfo", MQUtil.info());
        request.setAttribute("securityInfo", SecurityUtil.info());
        request.setAttribute("cloudInfo", CloudUtil.info());

        request.getRequestDispatcher("/index2.jsp").forward(request, response);
    }





}
