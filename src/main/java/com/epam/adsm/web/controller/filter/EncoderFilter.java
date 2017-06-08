package com.epam.adsm.web.controller.filter;

import javax.servlet.*;
import java.io.IOException;

/**
 * Created by akmatleu on 25.05.17.
 */

public class EncoderFilter implements Filter {

    private String code;

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
