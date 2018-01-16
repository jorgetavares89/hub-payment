package com.payment.jorge.paymentbr.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ServiceAspectLogging {

	private static final Logger LOG = LoggerFactory.getLogger(ServiceAspectLogging.class);

	@Before("execution(* com.payment.jorge.paymentbr.service.*.*(..))")
	public void beforeMethodExecution(JoinPoint joinPoint) {
		String reference = getReference(joinPoint);
		LOG.info("["+reference+"] will be executed");
	}

	@After("execution(* com.payment.jorge.paymentbr.service.*.*(..))")
	public void afterMethodExecution(JoinPoint joinPoint) {
		String reference = getReference(joinPoint);
		LOG.info("["+reference+"] executed");
	}

	@AfterReturning(pointcut = "execution(* com.payment.jorge.paymentbr.service.*.*(..))",
			returning = "result")
	public void afterReturningMethodExecution(JoinPoint joinPoint, Object result) {
		String reference = getReference(joinPoint);
		if (result != null) {
			LOG.info("["+reference+"] result: "+result.toString());
		}
	}

	private String getReference(JoinPoint joinPoint) {
		String className  = joinPoint.getTarget().getClass().getSimpleName();
		String methodName = joinPoint.getSignature().getName();
		return className +
				":" +
				methodName;
	}
}
