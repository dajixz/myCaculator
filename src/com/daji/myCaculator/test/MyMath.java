package com.daji.myCaculator.test;
import java.math.BigDecimal;
public class MyMath {
	
	private static BigDecimal getBigDecimal(double number){
		return new BigDecimal(number);
	}
	
	public static double add(double n1, double n2) {
		BigDecimal first = getBigDecimal(n1);
		BigDecimal second = getBigDecimal(n2);
		return first.add(second).doubleValue();
	}
	
	public static double subtract(double n1, double n2) {
		BigDecimal first = getBigDecimal(n1);
		BigDecimal second = getBigDecimal(n2);
		return first.subtract(second).doubleValue();
	}
	
	public static double multiply(double n1, double n2) {
		BigDecimal first = getBigDecimal(n1);
		BigDecimal second = getBigDecimal(n2);
		return first.multiply(second).doubleValue();
	}
	
	public static double divide(double n1, double n2) {
		BigDecimal first = getBigDecimal(n1);
		BigDecimal second = getBigDecimal(n2);
		return first.divide(second).doubleValue();
	}
	
}