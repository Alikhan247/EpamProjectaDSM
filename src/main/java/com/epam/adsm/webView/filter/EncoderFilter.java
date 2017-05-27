package com.epam.adsm.webView.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import java.io.IOException;

/**
 * Created by akmatleu on 25.05.17.
 */
@WebFilter(filterName = "EncoderFilter", urlPatterns = "/do/*", initParams = {@WebInitParam(name = "encode", value = "UTF-8", description = "Encoding param")})

public class EncoderFilter implements Filter{
    String code;


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
       code = filterConfig.getInitParameter("encode");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        String codeReq = servletRequest.getCharacterEncoding();
        if (code != null && !code.equalsIgnoreCase(codeReq)) {
            servletRequest.setCharacterEncoding(code);
            servletResponse.setCharacterEncoding(code);
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
