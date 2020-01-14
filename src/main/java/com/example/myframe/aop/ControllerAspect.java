package com.example.myframe.aop;
import com.example.myframe.common.consts.ResultEnum;
import com.example.myframe.common.redis.RedisUtils;
import com.example.myframe.common.response.RestResponse;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseBody;
import java.lang.reflect.*;
import javax.security.auth.login.LoginException;
import javax.servlet.http.HttpServletRequest;

/**
 * 实现切面控制
 */
@Aspect
@Component
public class ControllerAspect {


    @Autowired
    private UserService userService;

    @Autowired
    private RedisUtils redisUtils;
    @Autowired
    private HttpServletRequest request;
    /**
     * 定义切点
     */
    @Pointcut("execution(public * com.example.myframe.controller.DemoController.*(..))")
    public void privilege() {

    }

    /**
     * 权限环绕通知
     *
     * @param joinPoint
     * @throws Throwable
     */
    @ResponseBody
    @Around("privilege()")
    public Object isAccessMethod(ProceedingJoinPoint joinPoint) throws Throwable {
        //获取访问目标方法
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method targetMethod = methodSignature.getMethod();
        //得到方法的访问权限
        //final String methodAccess = AnnotationParse.privilegeParse(targetMethod);

        //如果该方法上没有权限注解，直接调用目标方法
        if (!targetMethod.isAnnotationPresent(Permission.class)) {
            return joinPoint.proceed();
        } else {
            //获取当前用户
            Object[] args = joinPoint.getArgs();
            if (args == null) {
                throw new LoginException("参数错误");
            }
            String name=(String) request.getSession().getAttribute("name");
            boolean flag=redisUtils.hasKey(name);
            if (flag == false) {
                return error();

                //throw new LoginException("您不是管理员");
            }
            if (!userService.isAdmin(name)) {
                return error1();
            } else {
                //logger.info("您是管理员");
                //是管理员时，才返回所需要的信息
                return joinPoint.proceed();
            }

        }
    }

    /**
     * 返回没有登录的信息
     * @return
     */
    @ResponseBody
    public static RestResponse error() {
        return new RestResponse(ResultEnum.INVALID_TOKEN,"缓存时间过期或者没有登录");
    }

    /**
     * 返回没有权限的信息
     * @return
     */
    @ResponseBody
    public static RestResponse error1() {
        return new RestResponse(ResultEnum.UNAUTHORIZED,"无权限");
    }
}