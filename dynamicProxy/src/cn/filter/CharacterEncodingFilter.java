package spring.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * CharacterEncodingFilter class
 *
 * @author Administrator
 * @date
 */
public class CharacterEncodingFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        servletRequest.setCharacterEncoding("UTF-8");
        servletResponse.setCharacterEncoding("UTF-8");
        servletResponse.setContentType("text/html;charset=UTF-8");

        //添加对get方法的过滤
        ServletRequest requestProxy = getHttpServletRequestProxy((HttpServletRequest) servletRequest);

        filterChain.doFilter(requestProxy, servletResponse);
    }


    private ServletRequest getHttpServletRequestProxy(final HttpServletRequest request) {
        ServletRequest proxy = (ServletRequest) Proxy.newProxyInstance(
                CharacterEncodingFilter.class.getClassLoader(),
                request.getClass().getInterfaces(),
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        if (request.getMethod().equalsIgnoreCase("get") && method.getName().equalsIgnoreCase("getparameter")) {
                            //调用getParameter方法获取参数的值
                            String value = (String) method.invoke(request, args);
                            if (value == null) {
                                return null;
                            }
                            return new String(value.getBytes("iso8859-1"), "UTF-8");
                        } else {
                            return method.invoke(request, args);
                        }
                    }
                }
        );
        return proxy;
    }


}
