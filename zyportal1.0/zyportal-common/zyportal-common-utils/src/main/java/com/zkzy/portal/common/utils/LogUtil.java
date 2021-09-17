
package com.zkzy.portal.common.utils;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>日志辅助工具类，封装了slf4j的接口，可以直接调用相关的静态方法</p>
 * @author majianbo 2015-6-4 上午11:08:37
 * @version V1.0
 */
public class LogUtil {
	
	// logMap 存放key为class value为Log的缓存
	private static final Map<Class<?>, Logger>	logMap	= new HashMap<Class<?>, Logger>();
	
	/**
	 * 获得Logger对象
	 * @author majianbo 2015-6-4 上午11:11:03
	 * @param obj
	 * @return
	 */
	private static Logger getLog(Object obj) {
		Class<?> clazz = null;
		if(obj == null){
			throw new IllegalArgumentException("记录日志对象不能为空！");
		}
		if (obj instanceof Class<?>) {
			clazz = (Class<?>)obj;
		} else {
			clazz = obj.getClass();
		}
		Logger logger = logMap.get(clazz);
		if(logger == null){
			logger = LoggerFactory.getLogger(clazz);
			logMap.put(clazz,logger );
		}
		return logger;
	}
	
	/**
	 * info日志,传递一个日志参数
	 * @author majianbo 2015-6-4 上午11:11:42
	 * @param obj
	 * @param message
	 */
	public static void logInfo(Object obj, Object message) {
		Logger logger = getLog(obj);
		if(logger.isInfoEnabled()){
			logger.info(bufferToString(message));
		}
	}
	
	/**
	 * info日志 传递两个日志记录参数
	 * @author majianbo 2015-6-4 下午1:50:15
	 * @param obj
	 * @param message1
	 * @param message2
	 */
	public static void logInfo(Object obj, Object message1, Object message2) {
		Logger logger = getLog(obj);
		if(logger.isInfoEnabled()){
			logger.info(bufferToString(message1, message2));
		}
	}
	
	/**
	 * info日志 传递多个日志记录参数
	 * @author majianbo 2015-6-4 下午1:52:25
	 * @param obj
	 * @param message
	 */
	public static void logInfo(Object obj, Object... message) {
		Logger logger = getLog(obj);
		if(logger.isInfoEnabled()){
			logger.info(bufferToString(message));
		}
	}
	
	/**
	 * error日志 记录一个参数
	 * @author majianbo 2015-6-4 下午1:52:56
	 * @param obj
	 * @param message
	 */
	public static void logError(Object obj, Object message) {
		Logger logger = getLog(obj);
		if(logger.isErrorEnabled()){
			if (message instanceof Throwable) {
				logger.error("", (Throwable)message);
			} else {
				logger.error(bufferToString(message));
			}
		}
	}
	
	/**
	 * error日志 记录两个参数
	 * @author majianbo 2015-6-4 下午2:09:47
	 * @param obj
	 * @param message1
	 * @param message2
	 */
	public static void logError(Object obj, Object message1, Object message2) {
		Logger logger = getLog(obj);
		if(logger.isErrorEnabled()){
			if(message2 instanceof Throwable){
				logger.error(bufferToString(message1),(Throwable)message2);
			}else{
				logger.error(bufferToString(message1, message2));
			}
		}
	}
	
	/**
	 * error日志 记录多个参数
	 * @author majianbo 2015-6-4 下午2:10:19
	 * @param obj
	 * @param message
	 */
	public static void logError(Object obj, Object... message) {
		Logger logger = getLog(obj);
		if(logger.isErrorEnabled()){
			logger.error(bufferToString(message));
		}
	}
	
	/**
	 * debug日志 一个记录参数
	 * @author majianbo 2015-6-4 下午2:10:47
	 * @param obj
	 * @param message
	 */
	public static void logDebug(Object obj, Object message) {
		Logger logger = getLog(obj);
		if(logger.isDebugEnabled()){
			logger.debug(bufferToString(message));
		}
	}
	
	/**
	 * debug日志 记录两个参数
	 * @author majianbo 2015-6-4 下午2:11:06
	 * @param obj
	 * @param message1
	 * @param message2
	 */
	public static void logDebug(Object obj, Object message1, Object message2) {
		Logger logger = getLog(obj);
		if(logger.isDebugEnabled()){
			logger.debug(bufferToString(message1, message2));
		}
	}
	
	/**
	 * debug日志 记录多个参数
	 * @author majianbo 2015-6-4 下午2:11:25
	 * @param obj
	 * @param message
	 */
	public static void logDebug(Object obj, Object... message) {
		Logger logger = getLog(obj);
		if(logger.isDebugEnabled()){
			logger.debug(bufferToString(message));
		}
	}
	
	/**
	 * warn日志 记录一个参数
	 * @author majianbo 2015-6-4 下午2:11:59
	 * @param obj
	 * @param message
	 */
	public static void logWarn(Object obj, Object message) {
		Logger logger = getLog(obj);
		if(logger.isWarnEnabled()){
			logger.warn(bufferToString(message));
		}
	}
	
	/**
	 * warn日志 记录两个参数
	 * @author majianbo 2015-6-4 下午2:12:18
	 * @param obj
	 * @param message1
	 * @param message2
	 */
	public static void logWarn(Object obj, Object message1, Object message2) {
		Logger logger = getLog(obj);
		if(logger.isWarnEnabled()){
			logger.warn(bufferToString(message1, message2));
		}
	}
	
	/**
	 * warn日志 记录多个参数
	 * @author majianbo 2015-6-4 下午2:12:42
	 * @param obj
	 * @param message
	 */
	public static void logWarn(Object obj, Object... message) {
		Logger logger = getLog(obj);
		if(logger.isWarnEnabled()){
			logger.warn(bufferToString(message));
		}
	}
	
	/**
	 * info日志带exception
	 * @author majianbo 2015-6-4 下午2:13:00
	 * @param obj
	 * @param e
	 * @param message
	 */
	public static void logInfo(Object obj, Throwable e, Object message) {
		Logger logger = getLog(obj);
		if(logger.isInfoEnabled()){
			logger.info(bufferToString(message), e);
		}
	}
	
	/**
	 * info日志带exception 记录两个参数
	 * @author majianbo 2015-6-4 下午2:13:20
	 * @param obj
	 * @param e
	 * @param message1
	 * @param message2
	 */
	public static void logInfo(Object obj, Throwable e, Object message1, Object message2) {
		Logger logger = getLog(obj);
		if(logger.isInfoEnabled()){
			logger.info(bufferToString(message1, message2), e);
		}
	}
	
	/**
	 * info日志带exception 多个参数
	 * @author majianbo 2015-6-4 下午2:13:38
	 * @param obj
	 * @param e
	 * @param message
	 */
	public static void logInfo(Object obj, Throwable e, Object... message) {
		Logger logger = getLog(obj);
		if(logger.isInfoEnabled()){
			logger.info(bufferToString(message), e);
		}
	}
	
	/**
	 *  error日志带exception 
	 * @author majianbo 2015-6-4 下午2:13:56
	 * @param obj
	 * @param e
	 * @param message
	 */
	public static void logError(Object obj, Throwable e, Object message) {
		Logger logger = getLog(obj);
		if(logger.isErrorEnabled()){
			logger.error(bufferToString(message), e);
		}
	}
	
	/**
	 * error日志带exception 两个参数
	 * @author majianbo 2015-6-4 下午2:14:11
	 * @param obj
	 * @param e
	 * @param message1
	 * @param message2
	 */
	public static void logError(Object obj, Throwable e, Object message1, Object message2) {
		Logger logger = getLog(obj);
		if(logger.isErrorEnabled()){
			logger.error(bufferToString(message1, message2), e);
		}
	}
	
	/**
	 * error日志带exception 多个参数
	 * @author majianbo 2015-6-4 下午2:14:28
	 * @param obj
	 * @param e
	 * @param message
	 */
	public static void logError(Object obj, Throwable e, Object... message) {
		Logger logger = getLog(obj);
		if(logger.isErrorEnabled()){
			logger.error(bufferToString(message), e);
		}
	}
	
	/**
	 * debug日志带exception
	 * @author majianbo 2015-6-4 下午2:14:48
	 * @param obj
	 * @param e
	 * @param message
	 */
	public static void logDebug(Object obj, Throwable e, Object message) {
		Logger logger = getLog(obj);
		if(logger.isDebugEnabled()){
			logger.debug(bufferToString(message), e);
		}
	}
	
	/**
	 * debug日志带exception 带两个参数
	 * @author majianbo 2015-6-4 下午2:15:03
	 * @param obj
	 * @param e
	 * @param message1
	 * @param message2
	 */
	public static void logDebug(Object obj, Throwable e, Object message1, Object message2) {
		Logger logger = getLog(obj);
		if(logger.isDebugEnabled()){
			logger.debug(bufferToString(message1, message2), e);
		}
	}
	
	/**
	 * debug日志带exception 多个参数
	 * @author majianbo 2015-6-4 下午2:15:23
	 * @param obj
	 * @param e
	 * @param message
	 */
	public static void logDebug(Object obj, Throwable e, Object... message) {
		Logger logger = getLog(obj);
		if(logger.isDebugEnabled()){
			logger.debug(bufferToString(message), e);
		}
	}
	
	/**
	 * warn日志带exception
	 * @author majianbo 2015-6-4 下午2:15:39
	 * @param obj
	 * @param e
	 * @param message
	 */
	public static void logWarn(Object obj, Throwable e, Object message) {
		Logger logger = getLog(obj);
		if(logger.isWarnEnabled()){
			logger.warn(bufferToString(message), e);
		}
	}
	
	/**
	 * warn日志带exception 带两个参数
	 * @author majianbo 2015-6-4 下午2:15:47
	 * @param obj
	 * @param e
	 * @param message1
	 * @param message2
	 */
	public static void logWarn(Object obj, Throwable e, Object message1, Object message2) {
		Logger logger = getLog(obj);
		if(logger.isWarnEnabled()){
			logger.warn(bufferToString(message1, message2), e);
		}
	}
	
	/**
	 * warn日志带exception 带多个参数
	 * @author majianbo 2015-6-4 下午2:16:10
	 * @param obj
	 * @param e
	 * @param message
	 */
	public static void logWarn(Object obj, Throwable e, Object... message) {
		Logger logger = getLog(obj);
		if(logger.isWarnEnabled()){
			logger.warn(bufferToString(message), e);
		}
	}
	
	/**
	 * info日志
	 * @author majianbo 2015-6-4 下午2:16:40
	 * @param clazz
	 * @param message
	 */
	public static void logInfo(Class<?> clazz, Object message) {
		Logger logger = getLog(clazz);
		if(logger.isInfoEnabled()){
			logger.info(bufferToString(message));
		}
	}
	
	/**
	 * info日志 带两个参数
	 * @author majianbo 2015-6-4 下午2:16:51
	 * @param clazz
	 * @param message1
	 * @param message2
	 */
	public static void logInfo(Class<?> clazz, Object message1, Object message2) {
		Logger logger = getLog(clazz);
		if(logger.isInfoEnabled()){
			logger.info(bufferToString(message1, message2));
		}
	}
	
	/**
	 * info日志 带多个参数
	 * @author majianbo 2015-6-4 下午2:17:09
	 * @param clazz
	 * @param message
	 */
	public static void logInfo(Class<?> clazz, Object... message) {
		Logger logger = getLog(clazz);
		if(logger.isInfoEnabled()){
			logger.info(bufferToString(message));
		}
	}
	
	/**
	 * error日志 
	 * @author majianbo 2015-6-4 下午2:17:25
	 * @param clazz
	 * @param message
	 */
	public static void logError(Class<?> clazz, Object message) {
		Logger logger = getLog(clazz);
		if(logger.isErrorEnabled()){
			logger.error(bufferToString(message));
		}
	}
	
	/**
	 * error日志 带两个参数
	 * @author majianbo 2015-6-4 下午2:17:42
	 * @param clazz
	 * @param message1
	 * @param message2
	 */
	public static void logError(Class<?> clazz, Object message1, Object message2) {
		Logger logger = getLog(clazz);
		if(logger.isErrorEnabled()){
			logger.error(bufferToString(message1, message2));
		}
	}
	
	/**
	 * error日志 带多个参数
	 * @author majianbo 2015-6-4 下午2:18:01
	 * @param clazz
	 * @param message
	 */
	public static void logError(Class<?> clazz, Object... message) {
		Logger logger = getLog(clazz);
		if(logger.isErrorEnabled()){
			logger.error(bufferToString(message));
		}
	}
	
	/**
	 * debug日志
	 * @author majianbo 2015-6-4 下午2:18:16
	 * @param clazz
	 * @param message
	 */
	public static void logDebug(Class<?> clazz, Object message) {
		Logger logger = getLog(clazz);
		if(logger.isDebugEnabled()){
			logger.debug(bufferToString(message));
		}
	}
	
	/**
	 *  debug日志 带两个参数
	 * @author majianbo 2015-6-4 下午2:18:28
	 * @param clazz
	 * @param message1
	 * @param message2
	 */
	public static void logDebug(Class<?> clazz, Object message1, Object message2) {
		Logger logger = getLog(clazz);
		if(logger.isDebugEnabled()){
			logger.debug(bufferToString(message1, message2));
		}
	}
	
	/**
	 * debug日志 带多个参数
	 * @author majianbo 2015-6-4 下午2:18:44
	 * @param clazz
	 * @param message
	 */
	public static void logDebug(Class<?> clazz, Object... message) {
		Logger logger = getLog(clazz);
		if(logger.isDebugEnabled()){
			logger.debug(bufferToString(message));
		}
	}
	
	/**
	 * warn日志 
	 * @author majianbo 2015-6-4 下午2:18:59
	 * @param clazz
	 * @param message
	 */
	public static void logWarn(Class<?> clazz, Object message) {
		Logger logger = getLog(clazz);
		if(logger.isWarnEnabled()){
			logger.warn(bufferToString(message));
		}
	}
	
	/**
	 * warn日志 带两个参数
	 * @author majianbo 2015-6-4 下午2:19:09
	 * @param clazz
	 * @param message1
	 * @param message2
	 */
	public static void logWarn(Class<?> clazz, Object message1, Object message2) {
		Logger logger = getLog(clazz);
		if(logger.isWarnEnabled()){
			logger.warn(bufferToString(message1, message2));
		}
	}
	
	/**
	 * warn日志 带多个参数
	 * @author majianbo 2015-6-4 下午2:19:30
	 * @param clazz
	 * @param message
	 */
	public static void logWarn(Class<?> clazz, Object... message) {
		Logger logger = getLog(clazz);
		if(logger.isWarnEnabled()){
			logger.warn(bufferToString(message));
		}
	}
	
	/**
	 * info日志带exception 
	 * @author majianbo 2015-6-4 下午2:19:46
	 * @param clazz
	 * @param e
	 * @param message
	 */
	public static void logInfo(Class<?> clazz, Throwable e, Object message) {
		Logger logger = getLog(clazz);
		if(logger.isInfoEnabled()){
			logger.info(bufferToString(message), e);
		}
	}
	
	/**
	 * info日志带exception 带两个参数
	 * @author majianbo 2015-6-4 下午2:20:05
	 * @param clazz
	 * @param e
	 * @param message1
	 * @param message2
	 */
	public static void logInfo(Class<?> clazz, Throwable e, Object message1, Object message2) {
		Logger logger = getLog(clazz);
		if(logger.isInfoEnabled()){
			logger.info(bufferToString(message1, message2), e);
		}
	}
	
	/**
	 *  info日志带exception 带多个参数
	 * @author majianbo 2015-6-4 下午2:20:25
	 * @param clazz
	 * @param e
	 * @param message
	 */
	public static void logInfo(Class<?> clazz, Throwable e, Object... message) {
		Logger logger = getLog(clazz);
		if(logger.isInfoEnabled()){
			logger.info(bufferToString(message), e);
		}
	}
	
	/**
	 * error日志带exception 
	 * @author majianbo 2015-6-4 下午2:20:48
	 * @param clazz
	 * @param e
	 * @param message
	 */
	public static void logError(Class<?> clazz, Throwable e, Object message) {
		Logger logger = getLog(clazz);
		if(logger.isErrorEnabled()){
			logger.error(bufferToString(message), e);
		}
	}
	
	/**
	 * error日志带exception 带两个参数
	 * @author majianbo 2015-6-4 下午2:21:23
	 * @param clazz
	 * @param e
	 * @param message1
	 * @param message2
	 */
	public static void logError(Class<?> clazz, Throwable e, Object message1, Object message2) {
		Logger logger = getLog(clazz);
		if(logger.isErrorEnabled()){
			logger.error(bufferToString(message1, message2), e);
		}
	}
	
	/**
	 * error日志带exception 带多个参数
	 * @author majianbo 2015-6-4 下午2:21:39
	 * @param clazz
	 * @param e
	 * @param message
	 */
	public static void logError(Class<?> clazz, Throwable e, Object... message) {
		Logger logger = getLog(clazz);
		if(logger.isErrorEnabled()){
			logger.error(bufferToString(message), e);
		}
	}
	
	/**
	 * debug日志带exception 
	 * @author majianbo 2015-6-4 下午2:21:54
	 * @param clazz
	 * @param e
	 * @param message
	 */
	public static void logDebug(Class<?> clazz, Throwable e, Object message) {
		Logger logger = getLog(clazz);
		if(logger.isDebugEnabled()){
			logger.debug(bufferToString(message), e);
		}
	}
	
	/**
	 * debug日志带exception 带两个参数
	 * @author majianbo 2015-6-4 下午2:22:05
	 * @param clazz
	 * @param e
	 * @param message1
	 * @param message2
	 */
	public static void logDebug(Class<?> clazz, Throwable e, Object message1, Object message2) {
		Logger logger = getLog(clazz);
		if(logger.isDebugEnabled()){
			logger.debug(bufferToString(message1, message2), e);
		}
	}
	
	/**
	 * debug日志带exception 带多个参数
	 * @author majianbo 2015-6-4 下午2:22:18
	 * @param clazz
	 * @param e
	 * @param message
	 */
	public static void logDebug(Class<?> clazz, Throwable e, Object... message) {
		Logger logger = getLog(clazz);
		if(logger.isDebugEnabled()){
			logger.debug(bufferToString(message), e);
		}
	}
	
	/**
	 * warn日志带exception
	 * @author majianbo 2015-6-4 下午2:22:31
	 * @param clazz
	 * @param e
	 * @param message
	 */
	public static void logWarn(Class<?> clazz, Throwable e, Object message) {
		Logger logger = getLog(clazz);
		if(logger.isWarnEnabled()){
			logger.warn(bufferToString(message), e);
		}
	}
	
	/**
	 * warn日志带exception 带两个参数
	 * @author majianbo 2015-6-4 下午2:22:40
	 * @param clazz
	 * @param e
	 * @param message1
	 * @param message2
	 */
	public static void logWarn(Class<?> clazz, Throwable e, Object message1, Object message2) {
		Logger logger = getLog(clazz);
		if(logger.isWarnEnabled()){
			logger.warn(bufferToString(message1, message2), e);
		}
	}
	
	/**
	 * warn日志带exception 带多个参数
	 * @author majianbo 2015-6-4 下午2:22:54
	 * @param clazz
	 * @param e
	 * @param message
	 */
	public static void logWarn(Class<?> clazz, Throwable e, Object... message) {
		Logger logger = getLog(clazz);
		if(logger.isWarnEnabled()){
			logger.warn(bufferToString(message), e);
		}
	}
	
	/**
	 * 将对象转换成字符串
	 * @author majianbo 2015-6-4 下午2:23:08
	 * @param object
	 * @return
	 */
	public static String bufferToString(Object object) {
		if (object instanceof Throwable) {
			StringBuilder sb = new StringBuilder();
			Throwable throwable = ((Throwable)object);
			sb.append("异常描述：").append(throwable.getMessage()).append("\n堆栈信息：");
			for (StackTraceElement stackTraceElement : throwable.getStackTrace()) {
				sb.append(stackTraceElement).append("\n");
			}
			return sb.toString();
		} else {
			return object == null?"NULL":object.toString();
		}
	}
	
	/**
	 * 将对象转换成字符串
	 * @author majianbo 2015-6-4 下午2:23:23
	 * @param object1
	 * @param object2
	 * @return
	 */
	private static String bufferToString(Object object1, Object object2) {
		return bufferToString(object1) + bufferToString(object2);
	}
	
	/**
	 * 将字符串数组转换成字符串
	 * @author majianbo 2015-6-4 下午2:23:43
	 * @param objects
	 * @return
	 */
	private static String bufferToString(Object... objects) {
		StringBuilder sb = new StringBuilder();
		if (objects != null) {
			for (Object object : objects) {
				sb.append(bufferToString(object));
			}
		} else {
			sb.append("NULL");
		}
		return sb.toString();
	}
}
