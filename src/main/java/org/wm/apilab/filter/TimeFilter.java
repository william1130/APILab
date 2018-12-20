package org.wm.apilab.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import org.springframework.core.annotation.Order;

@Order(1)
@WebFilter(filterName = "timeFilter", urlPatterns = "/*")
public class TimeFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println(">>>>>>>Time Filter Init");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println(">>>>>>>Time Filter Start");
        long startTime = System.currentTimeMillis();

        filterChain.doFilter(servletRequest, servletResponse);

        long endTime = System.currentTimeMillis();
        System.out.println(">>>>>>>Time Filter Consume " + (endTime - startTime) + " ms");
        System.out.println(">>>>>>>Time Filter End");
    }

    @Override
    public void destroy() {
        System.out.println(">>>>>>>Time Filter Init");
    }
}
