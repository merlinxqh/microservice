package com.itmuch.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by leo on 2017/8/15.
 * zuul过滤器
 */
public class PreRequestLogFilter extends ZuulFilter {

    private static  final Logger logger= LoggerFactory.getLogger(PreRequestLogFilter.class);

    @Override
    public String filterType() {
        //pre route post error 四种过滤类型
        return "pre";
    }

    @Override
    public int filterOrder() {
        //指定过滤器执行顺序
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        //判断该过滤器是否要执行
        return true;
    }

    @Override
    public Object run() {
        //执行具体逻辑
        RequestContext ctx=RequestContext.getCurrentContext();
        HttpServletRequest request=ctx.getRequest();
        PreRequestLogFilter.logger.info(String.format("send %s request to %s",request.getMethod(),request.getRequestURL().toString()));
        return null;
    }
}
