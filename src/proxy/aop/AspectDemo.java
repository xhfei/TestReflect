package proxy.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component("aspectDemo")
@Aspect
public class AspectDemo {
	//定义切入点表达式
	private final static String EXP="execution (* *.m1(..)) || execution (* *.m2(..))";
	//前置方法通知
	//@before的value属性对哪些切入点织入通知
	//这个方法必须是返回值为void，无参或者只有一个连接点类型的参数，
	//从这个JoinPoint参数上可以获取到被代理的对象的相关信息
	@Before(EXP)
	public void before(JoinPoint point){
		System.out.println("Before "+point.getSignature().getName());
	}
	@After(EXP)
	public void after(JoinPoint point) {
		System.out.println("After "+point.getSignature().getName());
	}
	//后置方法返回值通知
	//@AfterReturning(EXP)
	//默认情况下，@AfterReturning与@After没什么不同，
	//但你可以使用下面的方式，定义argNames参数，表示被注解的方法
	//的一个参数，然后returning置顶返回值使用这个参数，实际上就是
	//spring会把方法的返回值传递给你指定的参数obj
	@AfterReturning(pointcut=EXP,returning="obj",argNames="obj")
	public void afterReturning(JoinPoint point,Object obj){
		System.out.println("After Returning "+obj);
	}
	
	//异常通知
	@AfterThrowing(pointcut=EXP,throwing="e",argNames="e")
	public void afterThrowing(JoinPoint point,Exception e){
		System.out.println("After Throwing "+e);
	}
	
	//环绕方法通知，环绕方法通知要注意必须给出调用之后的返回值，
	//否则被代理的方法会停止调用并返回null，除非你真的打算这么做
	//只有环绕通知猜可以使用JoinPoint的子类ProceedingJoinPoint，
	//这个连接点类型可以调用代理的方法，并获取、改变返回值
	@Around(EXP)
	public Object around(ProceedingJoinPoint point)throws Throwable{
		System.out.println("around Before "+point.getSignature().getName());
		Object o=point.proceed(point.getArgs());
		System.out.println("around After "+point.getSignature().getName());
		return o;
	}
}
