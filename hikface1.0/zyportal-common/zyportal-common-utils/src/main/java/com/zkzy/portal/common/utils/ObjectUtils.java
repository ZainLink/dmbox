/**
 * @Copyright: 2010 杭州海康威视系统技术有限公司
 * @address: http://www.hikvision.com
 * @ProjectName: CMS基线平台
 * @Description: 公司内部的基础平台
 */
package com.zkzy.portal.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;


/**
 * <p>
 * 对象相关的工具类
 * </p>
 */
@SuppressWarnings("unchecked")
public class ObjectUtils {

	private static final Logger LOGGER = LoggerFactory.getLogger(ObjectUtils.class);
	
	/**
	 * 对象是否为null
	 * @author majianbo Mar 11, 2011 12:24:19 PM
	 * @param object
	 * @return true 空 false 不空
	 */
	public static boolean isObjectNull(Object object) {
		return object == null?true:false;
	}
	
	/**
	 * 对象是否不为null
	 * @author majianbo Jun 27, 2012 2:48:36 PM
	 * @param object
	 * @return
	 */
	public static boolean isObjectNotNull(Object object) {
		return object == null?false:true;
	}
	
	/**
	 * 字符串是否为空
	 * @author majianbo Mar 11, 2011 12:28:03 PM
	 * @param string
	 * @return true 空 false 不空
	 */
	public static boolean isStringEmpty(String string) {
		return ((string == null) || string.isEmpty())?true:false;
	}
	
	/**
	 * 字符串是否不为空
	 * @author majianbo Sep 15, 2011 9:41:01 AM
	 * @param string
	 * @return
	 */
	public static boolean isStringNotEmpty(String string) {
		return !isStringEmpty(string);
	}
	
	/**
	 * 所有的字符串是不是都不为空
	 * @author majianbo Mar 20, 2012 9:32:21 AM
	 * @param strings
	 * @return
	 */
	public static boolean isAllStringNotEmpty(String... strings) {
		if (strings == null) {
			return false;
		}
		for (String string : strings) {
			if (isStringEmpty(string)) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * 是否所有的字符串都为空
	 * @author majianbo Mar 20, 2012 9:35:12 AM
	 * @param strings
	 * @return
	 */
	public static boolean isAllStringEmpty(String... strings) {
		if (strings == null) {
			return true;
		}
		for (String string : strings) {
			if (isStringNotEmpty(string)) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * 至少有一个字符串不为空
	 * @author majianbo Mar 20, 2012 9:38:37 AM
	 * @param strings
	 * @return
	 */
	public static boolean isStringLeastOneNotEmpty(String... strings) {
		if (strings == null) {
			return false;
		}
		for (String string : strings) {
			if (isStringNotEmpty(string)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 数组是否为空
	 * @author majianbo Mar 11, 2011 10:37:16 AM
	 * @param array
	 * @return true 空 false 不空
	 */
	public static boolean isArrayEmpty(Object[] array) {
		return (array == null || array.length == 0)?true:false;
	}
	
	/**
	 * 数组是否不为空
	 * @author majianbo Sep 15, 2011 9:37:05 AM
	 * @param array
	 * @return
	 */
	public static boolean isArrayNotEmpty(Object[] array) {
		return !isArrayEmpty(array);
	}
	
	/**
	 * 判断数组的长度是否为指定长度
	 * @author majianbo Jun 5, 2013 10:56:32 AM
	 * @param array
	 * @param length
	 * @return
	 */
	public static boolean isArrayLengthEqual(Object[] array, int length) {
		if (isArrayEmpty(array)) {
			return false;
		}
		return array.length == length?true:false;
	}
	
	/**
	 * Map是否为空
	 * @author majianbo Mar 11, 2011 10:37:20 AM
	 * @param map
	 * @return true 空 false 不空
	 */
	public static boolean isMapEmpty(Map<?, ?> map) {
		return (map == null || map.isEmpty())?true:false;
	}
	
	/**
	 * Map是否不为空
	 * @author majianbo Sep 15, 2011 9:37:59 AM
	 * @param map
	 * @return
	 */
	public static boolean isMapNotEmpty(Map<?, ?> map) {
		return !isMapEmpty(map);
	}
	
	/**
	 * List是否为空
	 * @author majianbo Mar 12, 2011 12:27:21 PM
	 * @param list
	 * @return true 空 false 不空
	 */
	public static boolean isListEmpty(List<?> list) {
		return (list == null || list.isEmpty())?true:false;
	}
	
	/**
	 * List是否不为空
	 * @author majianbo Sep 15, 2011 9:39:05 AM
	 * @param list
	 * @return
	 */
	public static boolean isListNotEmpty(List<?> list) {
		return !isListEmpty(list);
	}
	
	/**
	 * 集合框架是否为空
	 * @author majianbo Apr 28, 2011 4:43:17 PM
	 * @param collection
	 * @return
	 */
	public static boolean isCollectionEmpty(Collection<?> collection) {
		return (collection == null || collection.isEmpty())?true:false;
	}
	
	/**
	 * 集合框架是否不为空
	 * @author majianbo Sep 15, 2011 9:40:04 AM
	 * @param collection
	 * @return
	 */
	public static boolean isCollectionNotEmpty(Collection<?> collection) {
		return !isCollectionEmpty(collection);
	}
	
	/**
	 * 两个数组是否有相同的元素个数
	 * @author majianbo Mar 11, 2011 12:06:19 PM
	 * @param array1
	 * @param array2
	 * @return true 相同 false 不相同
	 */
	public static boolean isSameLength(Object[] array1, Object[] array2) {
		return (array1 == null || array2 == null || array1.length != array2.length)?false:true;
	}
	
	/**
	 * 数组中是否包含某个对象
	 * @author majianbo Mar 11, 2011 11:03:06 AM
	 * @param object
	 * @param objectArray
	 * @return
	 */
	public static boolean containObject(Object object, Object[] objectArray) {
		if (object == null || objectArray == null || objectArray.length == 0) {
			return false;
		} else {
			for (Object obj : objectArray) {
				if (obj == object)
					return true;
			}
			return false;
		}
	}
	
	/**
	 * 所有的对象是否都不等于null
	 * @author majianbo Jun 28, 2011 9:54:36 AM
	 * @param objects
	 * @return
	 */
	public static boolean isAllObjectsNotNull(Object... objects) {
		boolean result = true;
		if (!isArrayEmpty(objects)) {
			for (Object object : objects) {
				result = isObjectNull(object);
				if (result) {
					break;
				}
			}
		}
		return !result;
	}
	
	/**
	 * 集合中是否包含某个元素
	 * @author majianbo Jun 28, 2011 11:07:26 AM
	 * @param object
	 * @param collection
	 * @return
	 */
	public static boolean containObject(Object object, Collection<?> collection) {
		if (isObjectNull(object) || isCollectionEmpty(collection)) {
			return false;
		} else {
			return collection.contains(object);
		}
	}
	
	/**
	 * 集合框架中是否包含多个对象
	 * @author majianbo Jun 28, 2011 11:09:41 AM
	 * @param collection
	 * @param isAllContain
	 * @param objects
	 * @return
	 */
	public static boolean containObject(Collection<?> collection, boolean isAllContain, Object... objects) {
		if (isCollectionEmpty(collection) || isArrayEmpty(objects)) {
			return false;
		} else {
			boolean result = false;
			for (Object object : objects) {
				result = containObject(object, collection);
				if (isAllContain) {// 需要全部包含
					if (!result) {
						break;
					}
				} else {// 只需要包含一个
					if (result) {
						break;
					}
				}
			}
			return result;
		}
	}
	
	/**
	 * 在一组object对象数组中，是否至少有一个对象为Null
	 * @author majianbo Sep 29, 2011 3:04:01 PM
	 * @param objects
	 * @return true 至少一个对象为Null
	 */
	public static boolean isObjectsLeastOneNull(Object... objects) {
		if (objects == null) {
			return true;
		}
		for (Object object : objects) {
			if (isObjectNull(object)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 将对象转换为字符串
	 * @author majianbo Jun 27, 2012 2:50:38 PM
	 * @param obj
	 * @return
	 */
	public static String toString(Object obj) {
		List<Object> visited = new ArrayList<Object>();
		return toString(obj, visited);
	}
	
	private static String toString(Object obj, List<Object> visited) {
		if (obj == null) {
			return "null";
		}
		if (visited.contains(obj)) {
			return "...";
		}
		visited.add(obj);
		Class clazz = obj.getClass();
		if (clazz == String.class) {
			return obj.toString();
		}
		if (clazz.isArray()) {
			Class componentType = clazz.getComponentType();
			int length = Array.getLength(obj);
			StringBuffer result = new StringBuffer(componentType.getName()).append("[").append(length).append("]{");
			for (int i = 0; i < length; i++) {
				Object value = Array.get(obj, i);
				if (i > 0) {
					result.append(",");
				}
				if (componentType.isPrimitive()) {
					result.append(value);
				} else {
					result.append(toString(value, visited));
				}
			}
			result.append("}");
			return result.toString();
		}
		StringBuffer result = new StringBuffer("[");
		do {
			Field[] fields = clazz.getDeclaredFields();
			AccessibleObject.setAccessible(fields, true);
			for (Field field : fields) {
				if (!Modifier.isStatic(field.getModifiers())) {
					try {
						Object value = field.get(obj);
						if (!result.toString().endsWith("[")) {
							result.append(",");
						}
						result.append(field.getName()).append("=");
						if (field.getType().isPrimitive()) {
							result.append(value);
						} else {
							result.append(toString(value, visited));
						}
					} catch (IllegalArgumentException e) {
						LogUtil.logError(ObjectUtils.class, e.getMessage());
					} catch (IllegalAccessException e) {
						LogUtil.logError(ObjectUtils.class, e.getMessage());
					}
				}
			}
			clazz = clazz.getSuperclass();
		} while (clazz != Object.class);
		result.append("]");
		return result.toString();
	}
	
	/**
	 * 把第一个字符变成大写
	 * @param str 字符串
	 * @return 转化后的字符串
	 */
	public static String firstToUpper(String str) {
		if (isStringEmpty(str)) {
			return "";
		}
		return str.substring(0, 1).toUpperCase() + str.substring(1, str.length());
	}
	
	/**
	 * 把第一个字符变成小写
	 * @param str 字符串
	 * @return 转化后的字符串
	 */
	public static String firstToLower(String str) {
		if (isStringEmpty(str)) {
			return "";
		}
		return str.substring(0, 1).toLowerCase() + str.substring(1, str.length());
	}
	
	/**
	 * 创建类的实例，并将bean的属性值拷贝到这个类的对象中
	 * @param bean 保存属性值的bean
	 * @param clazz 想要实例化的类
	 * @return 拥有bean属性值的类的实例
	 * @throws Exception
	 */
	public static <T> T newInstanceFromBean(Object bean, Class<T> clazz) throws Exception {
		T obj = null;
		
		/* 参数为空直接返回null */
		if (bean == null || clazz == null) {
			LogUtil.logDebug(ObjectUtils.class, ObjectUtils.class, "传入参数为空");
			return obj;
		}
		
		// 创建对象
		obj = (T)clazz.newInstance();
		LogUtil.logDebug(ObjectUtils.class, ObjectUtils.class, "创建实体对象" + obj);
		
		megreToBean(bean, obj);
		
		// 返回对象
		return obj;
	}
	
	/**
	 * 合并bean的属性值
	 * @param orin 属性值来源对象
	 * @param dest 属性值合并到的目标对象
	 * @throws Exception
	 */
	public static void megreToBean(Object orin, Object dest) {
		/* 参数为空直接返回null */
		if (orin == null || dest == null) {
			LogUtil.logDebug(ObjectUtils.class, "传入参数为空");
			return;
		}
		
		// 获得dest类
		Class destClass = dest.getClass();
		
		// 获得dest所有属性名称
		Field[] fields = destClass.getDeclaredFields();
		
		/* 遍历dest所有属性 */
		for (Field field : fields) {
			String fieldName = field.getName();
			LogUtil.logDebug(ObjectUtils.class, "处理属性名称：" + fieldName);
			
			/* 构造get方法 */
			String getterName = "get" + firstToUpper(fieldName);
			
			/* 构造set方法 */
			String setterName = "set" + firstToUpper(fieldName);
			
			/* 获得get、set方法 */
			Method orinGetter = null;
			Method destSetter = null;
			try {
				orinGetter = orin.getClass().getDeclaredMethod(getterName);
				destSetter = destClass.getDeclaredMethod(setterName, field.getType());
			} catch (NoSuchMethodException nsme) {
				// 如果找不到方法直接忽略，处理下一个属性
				LogUtil.logError(ObjectUtils.class, "找不到dest中的get或orin对象中的set方法，忽略该属性" + fieldName);
				continue;
			}
			
			/* 从实体对象中取值 */
			if (orinGetter == null) {
				LogUtil.logDebug(ObjectUtils.class, "实体对象中的属性" + fieldName + "无get方法");
				continue;
			}
			Object fieldValue = null;
			try {
				fieldValue = orinGetter.invoke(orin, (Object[])null);
			} catch (Exception e) {
				// 方法调用失败后，为了不影响下一属性赋值，打印异常后继续
				e.printStackTrace();
				continue;
			}
			
			/* 给dest对象赋值 */
			if (destSetter == null) {
				LogUtil.logDebug(ObjectUtils.class, "dest对象中的属性" + fieldName + "无set方法");
				continue;
			}
			
			/* 返回类型不相同 */
			if (!orinGetter.getReturnType().equals(destSetter.getParameterTypes()[0])) {
				LogUtil.logDebug(ObjectUtils.class, "返回类型不相同");
				continue;
			}
			
			// 调用set方法
			try {
				destSetter.invoke(dest, fieldValue);
			} catch (Exception ignore) {
				// 方法调用失败后，为了不影响下一属性赋值，打印异常后继续
				ignore.printStackTrace();
				continue;
			}
			LogUtil.logInfo(ObjectUtils.class, "调用dest对象" + dest + "的set方法" + setterName + "，赋值" + fieldValue);
		}
	}
}
