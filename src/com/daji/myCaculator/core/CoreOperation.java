package com.daji.myCaculator.core;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Stack;

public class CoreOperation {

    //放操作符号的堆栈
    private static Stack<Character> operateStack = new Stack<Character>();
    //放后缀表达式的队列
    // 如果元素是 数值 则为 double  如果是 操作符  则为 字符
    private static Queue<Object> initQueue = new ArrayDeque<Object>();
    //放结果的堆栈
    private static Stack<Object> resultStack = new Stack<Object>();

    private String tmp = "";

    public CoreOperation(String str){
        //初始化 放后缀表达式的队列
        initInitQueue(str);
    }

    public static void main(String args[]) {
		CoreOperation m = new CoreOperation("12+(2*2+3)-1");
        m.initInitQueue("12+(2*2+3)-1");
		//1 22 * 2 1 - + 3*
		//1 22 2 1 - + * 3 *
		//（ * +
//		while(initStack.size()>0){
//			System.out.println(initStack.poll());
//		}

        System.out.println(m.doCalculation());
    }

    //1 2 3 + 4 × + 5 -
    //1 2 3 1
    //+ * ( + )
    //1 2 3 1 + * +
    private void initInitQueue(String str) {
        //中缀表达式 转 后缀表达式
        //实现 多位数 的计算 而不是 一位数
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (isNumber(c) || c == '.') {
                //遇到数字 或者 . 先tmp追加
                tmp += c;
            } else if (c == '(') {
                //遇到 （ 放操作符号的堆栈
                operateStack.push(c);
            } else {
                //遇到 （ 或者 操作符
                //如果tmp不为空 则 转为double 放入  放后缀表达式的队列
                if (tmp != "") {
                    initQueue.add(Double.valueOf(tmp));
                }
                //重置tmp
                tmp = "";
                //如果 放操作符号的堆栈 为空  则 放入
                if ((operateStack.empty())) {
                    operateStack.push(c);
                } else if (c == ')') {
                    // 如果是 ） 则从 放操作符号的堆栈 pop 操作符 直到 （
                    while (operateStack.peek() != '(') {
                        initQueue.add(operateStack.pop());
                    }
                    //再把 （  从放操作符号的堆栈pop出去
                    operateStack.pop();
                    continue;
                    //如果是操作符  则比较操作符的优先级 进行 队列和栈的 操作
                } else if (judgePriority(c) <= judgePriority(operateStack.peek())) {
                    initQueue.add(operateStack.pop());
                    operateStack.push(c);
                    continue;
                } else if (judgePriority(c) != judgePriority(operateStack.peek())) {
                    operateStack.push(c);
                }
            }
        }
        //最后判断tmp是否为空  将表达式最后一个数值 入队
        if (tmp != "") {
            initQueue.add(Double.valueOf(tmp));
        }
        //最后 将操作符 依次pop 入队
        while (!operateStack.empty()) {
            initQueue.add(operateStack.pop());
        }

    }
    //比较操作符优先级
    private int judgePriority(char c) {
        int priority = 0;
        switch (c) {
            case '+':
                priority = 1;
                break;
            case '-':
                priority = 1;
                break;
            case '*':
                priority = 2;
                break;
            case '/':
                priority = 2;
                break;
            case '(':
                break;
            case ')':
                break;
        }
        return priority;

    }
    //进行计算
    public double doCalculation() {
        while (initQueue.size()>0) {
            Object peek = initQueue.poll();
            //判断 出队元素是否为字符 即操作符
            //如果不是 入 结果栈
            if(!isChar(peek)){
                resultStack.push(peek);
            }else{
                //如果是  则 结果栈 要 pop两个元素进行响应计算操作
                double pop1 = (Double)resultStack.pop();
                double pop2 = (Double)resultStack.pop();
                Character c = Character.valueOf((Character) peek);
                //将结果入 结果栈
                resultStack.push(doOperate(c,pop2,pop1));
            }
        }
        //最后 结果栈会只剩下一个元素 即 最终结果
        return (Double)resultStack.pop();
    }

    //响应计算操作
    private double doOperate(char c,double d1 ,double d2){
        double result = 0;
        switch (c) {
            case '+':
                result = d1+d2;
                break;
            case '-':
                result = d1-d2;
                break;
            case '*':
                result = d1*d2;
                break;
            case '/':
                result = d1/d2;
                break;
        }
        return result;
    }

    //判断是否为字符
    private boolean isChar(Object o) {
        if (o instanceof Character) {
            return true;
        }
        return false;
    }

    //判断是否为数字
    private boolean isNumber(char c) {
        if (Character.isDigit(c))
            return true;
        else return false;
    }

}
