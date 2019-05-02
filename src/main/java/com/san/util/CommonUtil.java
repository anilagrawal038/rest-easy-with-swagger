package com.san.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class CommonUtil {

	public static void mapProperties(Object sourceObject, Object destObject) {
		Class<?> sourceClass = sourceObject.getClass();
		Class<?> destClass = destObject.getClass();
		Method[] methods = sourceClass.getMethods();
		for (Method sourceMethod : methods) {
			String sourceMethodName = sourceMethod.getName();
			String destMethodName = "set" + sourceMethodName.substring(3);
			if (sourceMethodName.startsWith("get")) {
				try {
					Method destMethod = destClass.getMethod(destMethodName, sourceMethod.getReturnType());
					destMethod.invoke(destObject, sourceMethod.invoke(sourceObject));
				} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				}
			} else if (sourceMethodName.startsWith("is")) {
				destMethodName = "set" + sourceMethodName.substring(2);
				try {
					Method destMethod = destClass.getMethod(destMethodName, sourceMethod.getReturnType());
					destMethod.invoke(destObject, sourceMethod.invoke(sourceObject));
				} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				}
			}
		}
	}

}
