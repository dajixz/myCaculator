package com.daji.myCaculator.test;

public class CalService {
	
	private boolean isSecondNum = false;
	private String lastOp;
	private String firstNum = "0";
	private String secondNum = "null";
    private String num = "0123456789.";
	private String op = "+-*/";
	
	public String catNum(String cmd, String text) {
		String result = cmd;
        if (!"0".equals(text)) {
        	if (isSecondNum) {
        		isSecondNum = false;
			} 
        	else {
				result = text + cmd;
			}
		}
		if (result.indexOf(".") == 0) {
			result = "0" + result;
		}
		
		return result;
	}
	
	public String setOp(String cmd, String text) {
		this.lastOp = cmd;
		this.firstNum = text;
		this.secondNum = null;
        this.isSecondNum = true;
		return null;
	}
	
	public String cal(String text) {
		double secondResult = secondNum == null ? Double.valueOf(text).doubleValue() : Double.valueOf(secondNum).doubleValue();
		if(secondResult == 0 && this.lastOp.equals("/")){
			return "0";
		}
		if(this.lastOp.equals("+")){
			firstNum = String.valueOf(MyMath.add(Double.valueOf(firstNum),secondResult));
		}else if (this.lastOp.equals("-")) {
			firstNum = String.valueOf(MyMath.subtract(Double.valueOf(firstNum),secondResult));
		}else if (this.lastOp.equals("*")) {
			firstNum = String.valueOf(MyMath.multiply(Double.valueOf(firstNum),secondResult));
		}else if (this.lastOp.equals("/")) {
			firstNum = String.valueOf(MyMath.divide(Double.valueOf(firstNum),secondResult));
		}
	    secondNum = secondNum == null ? text :secondNum;
		this.isSecondNum = true;
		return firstNum;
	}
	
	public String Backspace(String text){
		return text.equals("0") || text.equals("") ? "0" :text.substring(0,text.length()-1);
	}
	
	public String clearAll(){
		this.firstNum = "0";
		this.secondNum = null;
		return this.firstNum;
	}
	
	public String clear(String text){
		return "0";
	}
	
	public String callMethod(String cmd, String text){
		if(cmd.equals("C")){
			return clearAll();
		}else if (cmd.equals("Backspace")) {
			return Backspace(text);
		}else if (num.indexOf(cmd) != -1) {
			return catNum(cmd, text);
		}else if (op.indexOf(cmd) != -1) {
			return setOp(cmd, text);
		}else if(cmd.equals("=")){
			return cal(text);
		}else
		return "";
	}
}
