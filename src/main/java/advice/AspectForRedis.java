package advice;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;


@Aspect 
public class AspectForRedis {

	@Pointcut("execution(public * redis*(..))")
	public void targetMethod(){
		//dummy
	}
	
	@Around("targetMethod()")
	public Object aroundTargetMethod(ProceedingJoinPoint thisJoinPoint) throws Throwable{
		 System.out.println("AspectUsingAnnotation.aroundTargetMethod start." + thisJoinPoint +"," + thisJoinPoint.getSourceLocation() );
        long time1 = System.currentTimeMillis();
        
        //run targetMethod
        Object retVal = thisJoinPoint.proceed();
 
        //System.out.println("ProceedingJoinPoint executed. return value is [" + retVal + "]");
        
        long time2 = System.currentTimeMillis();
        System.out.println("AspectUsingAnnotation.aroundTargetMethod end. Time(" + (time2 - time1) + ")");
        return retVal;
	}
	
}
