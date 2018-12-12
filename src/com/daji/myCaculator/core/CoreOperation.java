package com.daji.myCaculator.core;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Stack;

public class CoreOperation {

    //�Ų������ŵĶ�ջ
    private static Stack<Character> operateStack = new Stack<Character>();
    //�ź�׺���ʽ�Ķ���
    // ���Ԫ���� ��ֵ ��Ϊ double  ����� ������  ��Ϊ �ַ�
    private static Queue<Object> initQueue = new ArrayDeque<Object>();
    //�Ž���Ķ�ջ
    private static Stack<Object> resultStack = new Stack<Object>();

    private String tmp = "";

    public CoreOperation(String str){
        //��ʼ�� �ź�׺���ʽ�Ķ���
        initInitQueue(str);
    }

    public static void main(String args[]) {
		CoreOperation m = new CoreOperation("12+(2*2+3)-1");
        m.initInitQueue("12+(2*2+3)-1");
		//1 22 * 2 1 - + 3*
		//1 22 2 1 - + * 3 *
		//�� * +
//		while(initStack.size()>0){
//			System.out.println(initStack.poll());
//		}

        System.out.println(m.doCalculation());
    }

    //1 2 3 + 4 �� + 5 -
    //1 2 3 1
    //+ * ( + )
    //1 2 3 1 + * +
    private void initInitQueue(String str) {
        //��׺���ʽ ת ��׺���ʽ
        //ʵ�� ��λ�� �ļ��� ������ һλ��
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (isNumber(c) || c == '.') {
                //�������� ���� . ��tmp׷��
                tmp += c;
            } else if (c == '(') {
                //���� �� �Ų������ŵĶ�ջ
                operateStack.push(c);
            } else {
                //���� �� ���� ������
                //���tmp��Ϊ�� �� תΪdouble ����  �ź�׺���ʽ�Ķ���
                if (tmp != "") {
                    initQueue.add(Double.valueOf(tmp));
                }
                //����tmp
                tmp = "";
                //��� �Ų������ŵĶ�ջ Ϊ��  �� ����
                if ((operateStack.empty())) {
                    operateStack.push(c);
                } else if (c == ')') {
                    // ����� �� ��� �Ų������ŵĶ�ջ pop ������ ֱ�� ��
                    while (operateStack.peek() != '(') {
                        initQueue.add(operateStack.pop());
                    }
                    //�ٰ� ��  �ӷŲ������ŵĶ�ջpop��ȥ
                    operateStack.pop();
                    continue;
                    //����ǲ�����  ��Ƚϲ����������ȼ� ���� ���к�ջ�� ����
                } else if (judgePriority(c) <= judgePriority(operateStack.peek())) {
                    initQueue.add(operateStack.pop());
                    operateStack.push(c);
                    continue;
                } else if (judgePriority(c) != judgePriority(operateStack.peek())) {
                    operateStack.push(c);
                }
            }
        }
        //����ж�tmp�Ƿ�Ϊ��  �����ʽ���һ����ֵ ���
        if (tmp != "") {
            initQueue.add(Double.valueOf(tmp));
        }
        //��� �������� ����pop ���
        while (!operateStack.empty()) {
            initQueue.add(operateStack.pop());
        }

    }
    //�Ƚϲ��������ȼ�
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
    //���м���
    public double doCalculation() {
        while (initQueue.size()>0) {
            Object peek = initQueue.poll();
            //�ж� ����Ԫ���Ƿ�Ϊ�ַ� ��������
            //������� �� ���ջ
            if(!isChar(peek)){
                resultStack.push(peek);
            }else{
                //�����  �� ���ջ Ҫ pop����Ԫ�ؽ�����Ӧ�������
                double pop1 = (Double)resultStack.pop();
                double pop2 = (Double)resultStack.pop();
                Character c = Character.valueOf((Character) peek);
                //������� ���ջ
                resultStack.push(doOperate(c,pop2,pop1));
            }
        }
        //��� ���ջ��ֻʣ��һ��Ԫ�� �� ���ս��
        return (Double)resultStack.pop();
    }

    //��Ӧ�������
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

    //�ж��Ƿ�Ϊ�ַ�
    private boolean isChar(Object o) {
        if (o instanceof Character) {
            return true;
        }
        return false;
    }

    //�ж��Ƿ�Ϊ����
    private boolean isNumber(char c) {
        if (Character.isDigit(c))
            return true;
        else return false;
    }

}
