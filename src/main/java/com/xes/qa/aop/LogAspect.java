package com.xes.qa.aop;


import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;

public class LogAspect {  
	//下面这一行也是可以的
//    Logger logger =Logger.getLogger(LogAspect.class);
	Logger logger =Logger.getLogger("file");
	
	// 任何通知方法都可以将第一个参数定义为 org.aspectj.lang.JoinPoint类型  
    public void before(JoinPoint call) {  
        String clazz = call.getTarget().getClass().getName();  
        // 获取目标对象上正在执行的方法名  
        String methodName = call.getSignature().getName();  
        logger.info("###Begin : [" + clazz + ".class], method: [" + methodName + "]");  
    }  
  
    public void afterReturn(JoinPoint call) {  
    	String clazz = call.getTarget().getClass().getName();  
        // 获取目标对象上正在执行的方法名  
        String methodName = call.getSignature().getName();
    	logger.info("###End : [" + clazz + ".class], method: [" + methodName + "]");  
    }  
  
    public void after(JoinPoint call) {  
    	String clazz = call.getTarget().getClass().getName();  
    	logger.info("###exit :"+clazz);  
    	logger.info("###Exit : [" + clazz + ".class]");  
    }  
  
    public void afterThrowing(Throwable e) {  
    	logger.error("###there is something wrong###", e); 
    	doSendMail( e);
    }  
  
    // 用来做环绕通知的方法可以第一个参数定义为org.aspectj.lang.ProceedingJoinPoint类型  
    public Object doAround(ProceedingJoinPoint call) throws Throwable {  
        Object result = null;  
        this.before(call);// 相当于前置通知  
        try {  
            result = call.proceed();  
            this.afterReturn(call); // 相当于后置通知  
        } catch (Throwable e) {  
            this.afterThrowing(e); // 相当于异常抛出后通知  
            throw e;  
        } finally {  
            this.after(call); // 相当于最终通知  
        }  
        return result;  
    } 
    private void doSendMail(Throwable e){
    	System.out.println("可以在这里做邮件发送");
    }
}
