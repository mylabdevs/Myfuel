package com.mylabs.myfuel.cors;

import com.mylabs.myfuel.config.property.MyLabsProperty;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//@Component
//@Order(Ordered.HIGHEST_PRECEDENCE)
public class CorsFilter { //implements Filter {

//    @Autowired
    private MyLabsProperty myLabsProperty; //Configurar para diferentes ambientes

//    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        response.setHeader("Access-Control-Allow-Origin", myLabsProperty.getOriginPermitida());
        response.setHeader("Access-Control-Allow-Credentials", "true");

        if ("OPTIONS".equals(request.getMethod()) && myLabsProperty.getOriginPermitida().equals(request.getHeader("Origin"))) {
            response.setHeader("Access-Control-Allow-Methods", "POST,PATH, GET, DELETE, PUT, OPTIONS");
            response.setHeader("Access-Control-Allow-headers", "Authorization, Content-Type, Accept");
            response.setHeader("Access-Control-Max-Age", "3600");

            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            chain.doFilter(req, resp);
        }
    }
}
