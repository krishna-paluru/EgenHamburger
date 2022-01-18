package com.krishna.TexasHamburger.Interceptor;
import com.krishna.TexasHamburger.model.ExecutionTime;

import com.krishna.TexasHamburger.repository.ExecutionTimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class ExecutionTimeInterceptor implements HandlerInterceptor {
    @Autowired
    private  ExecutionTimeRepository executionTimeRepository;
    @Autowired
    private  ExecutionTime executionTime;

    public ExecutionTimeInterceptor(final ExecutionTimeRepository executionTimeRepository,
                                    final ExecutionTime executionTime)
    {
        this.executionTimeRepository = executionTimeRepository;
        this.executionTime = executionTime;
    }

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        request.setAttribute("startTime",System.currentTimeMillis());
        String URI = request.getRequestURI();
        System.out.println(URI);
        return true;
    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {

    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
        long time = (long)request.getAttribute("startTime");
        long total = System.currentTimeMillis()-time;
        System.out.print(request.getRequestURI());
        System.out.println("Time taken"+total);
        ExecutionTime executionTime = new ExecutionTime();
        executionTime.setTime(total);
        executionTime.setName(request.getRequestURI());
        executionTimeRepository.save(executionTime);
    }
}

