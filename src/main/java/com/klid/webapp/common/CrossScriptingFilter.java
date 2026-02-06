package com.klid.webapp.common;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;

public class CrossScriptingFilter implements Filter {

 public FilterConfig filterConfig;
 
 public void init(FilterConfig filterConfig) throws ServletException { 
        this.filterConfig = filterConfig;    
 }    

 public void destroy() {
         this.filterConfig = null;     
 }    

 public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {        

  chain.doFilter(new RequestWrapper((HttpServletRequest) request), response);     
 } 
 
}

