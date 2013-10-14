package com.iti.cftc.cot.aspect;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class LoggingAspect {

	private static final Logger LOGGER = Logger.getLogger(LoggingAspect.class.getName()); // LoggingAspect.class.getName()
	DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss:SS");

	@Around("execution(* com.iti.cftc.cot.dao.NonFinancialDAO.getAllInstrumentData(..))")
	public Object logAroundGetAllInstrumentData(ProceedingJoinPoint pjp) throws Throwable {

		LOGGER.info("***************************************** Start NonFinancialDAO.getAllInstrumentData() **************************************");
		long start = System.currentTimeMillis();
		
		Object clazz = pjp.getTarget().getClass().getName();
		String methodName = pjp.getSignature().getName();

		try {
			LOGGER.info("Entering to Class " + clazz + " With Method Name " + methodName + " at " + dateFormat.format(Calendar.getInstance().getTime()));
			LOGGER.info("Method Excecution Completed " + methodName + " in Calss " + clazz + " at " + dateFormat.format(Calendar.getInstance().getTime()));
			long elapsedTime = System.currentTimeMillis() - start;
			LOGGER.info("Method execution time for method " + methodName + " in Calss " + clazz + ":" + elapsedTime + " milliseconds.");
			LOGGER.info("***************************************** End NonFinancialDAO.getAllInstrumentData() **************************************");

		} catch (Throwable e) {
			System.out.println("This is called after the method throws exception");
		}
		return pjp.proceed();
	}

	@Around("execution(* com.iti.cftc.cot.service.RunScheduler.run(..))")
	public void logAroundRunScheduler(ProceedingJoinPoint pjp) throws Throwable{
		
		LOGGER.info("***************************************** Start RunScheduler.run() **************************************");
		long start = System.currentTimeMillis();
		
		Object clazz = pjp.getTarget().getClass().getName();
		String methodName = pjp.getSignature().getName();

		try {
		    pjp.proceed();
			LOGGER.info("Entering to Class " + clazz + " With Method Name " + methodName + " at " + dateFormat.format(Calendar.getInstance().getTime()));
			LOGGER.info("Method Excecution Completed " + methodName + " in Calss " + clazz + " at " + dateFormat.format(Calendar.getInstance().getTime()));
			long elapsedTime = System.currentTimeMillis() - start;
			LOGGER.info("Method execution time for method " + methodName + " in Calss " + clazz + ":" + elapsedTime + " milliseconds.");
			LOGGER.info("***************************************** End RunScheduler.run() **************************************");

		} catch (Throwable e) {
			System.out.println("This is called after the method throws exception");
		}
	}

}
