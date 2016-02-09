package auto_BSA;

import java.awt.Graphics2D;
import java.util.HashMap;

public class MathExpressionDrawer
{
    private static HashMap<String, Double> variables;
    public static Graphics2D g2d;

    static {
        variables = new HashMap<String, Double>();
    }

    public static void main(String[] args) {
		try {
			//System.out.println(Parse("-7+5-7+9*(8+9+9)%(abs(8+9)+sin(10))", null));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
    
    public static void setVariable(String variableName, Double variableValue)
    {
        variables.put(variableName, variableValue);
    }

    public static Double getVariable(String variableName)
    {
        if (!variables.containsKey(variableName)) {
            System.err.println( "Error: Try get unexists variable '"+variableName+"'" );
            return 0.0;
        }
        return variables.get(variableName);
    }

    public static MathExpression Parse(String s, Graphics2D g2d) throws Exception
    {
    	MathExpressionDrawer.g2d = g2d;
        MathExpression MathExpression = PlusMinus(s);
        if (!MathExpression.rest.isEmpty()) {
            System.err.println("Error: can't full parse");
            System.err.println("rest: " + MathExpression.rest);
        }
        return MathExpression;
    }

    private static MathExpression PlusMinus(String s) throws Exception
    {
        MathExpression current = MulDiv(s);

        while (current.rest.length() > 0) {
            if (!(current.rest.charAt(0) == '+' || current.rest.charAt(0) == '-')) break;

            char sign = current.rest.charAt(0);
            String next = current.rest.substring(1);

            MathExpression current2 = MulDiv(next);
            if (sign == '+') {
                current = new Operator("+", current, current2, current2.rest);
            } else {
            	current = new Operator("-", current, current2, current2.rest);
            }
        }
        return current;
    }

    private static MathExpression Bracket(String s) throws Exception
    {
        char zeroChar = s.charAt(0);
        if (zeroChar == '(') {
            MathExpression r = PlusMinus(s.substring(1));
            if (!r.rest.isEmpty() && r.rest.charAt(0) == ')') {
                r.rest = r.rest.substring(1);
            } else {
                System.err.println("Error: not close bracket");
            }
            return new Parentheses(r, r.rest);
        }
        return FunctionVariable(s);
    }

    private static MathExpression FunctionVariable(String s) throws Exception
    {
        String f = "";
        int i = 0;
        // ищем название функции или переменной
        // имя обязательно должна начинаться с буквы
        while (i < s.length() && (Character.isLetter(s.charAt(i)) || ( Character.isDigit(s.charAt(i)) && i > 0 ) )) {
            f += s.charAt(i);
            i++;
        }
        if (!f.isEmpty()) { // если что-нибудь нашли
            if ( s.length() > i && s.charAt( i ) == '(') { // и следующий символ скобка значит - это функция
            	MathExpression r;
            	if (f.equals("pow")) {
                	r = readArgument(s.substring(f.length()));
                	MathExpression e = readArgument(r.rest);
                	return processFunction(f, r, e);
                } else {
                	r = Bracket(s.substring(f.length()));
                	return processFunction(f, r);
                }
            } else { // иначе - это переменная
                return new Number(f, s.substring(f.length()));
            }
        }
        return Num(s);
    }

    private static MathExpression readArgument(String s) throws Exception {
    	char zeroChar = s.charAt(0);
        if (zeroChar == '(') {
            MathExpression r = PlusMinus(s.substring(1));
            if (!r.rest.isEmpty() && r.rest.charAt(0) == ',') {
                //r.rest = r.rest.substring(1);
            } else {
                System.err.println("Error: not close bracket");
            }
            return new Parentheses(r, r.rest);
        }
        if (zeroChar == ',') {
            MathExpression r = PlusMinus(s.substring(1));
            if (!r.rest.isEmpty() && r.rest.charAt(0) == ')') {
                r.rest = r.rest.substring(1);
            } else {
                System.err.println("Error: not close bracket");
            }
            return new Parentheses(r, r.rest);
        }
        return FunctionVariable(s);
	}

	private static MathExpression MulDiv(String s) throws Exception
    {
        MathExpression current = Bracket(s);

        while (true) {
            if (current.rest.length() == 0) {
                return current;
            }
            char sign = current.rest.charAt(0);
            if ((sign != '*' && sign != '/' && sign != '%')) return current;

            String next = current.rest.substring(1);
            MathExpression right = Bracket(next);

            if (sign == '*') {
                current = new Operator("*", current, right, right.rest);
            } else if (sign == '/') {
            	current = new Operator("/", current, right, right.rest);
            } else {
            	current = new Operator("%", current, right, right.rest);
            }

            //current = new Number(current, right.rest);
        }
    }

   private static MathExpression Num(String s) throws Exception
   {
        int i = 0;
        int dot_cnt = 0;
        boolean negative = false;
        // число также может начинаться с минуса
        if( s.charAt(0) == '-' ){
            negative = true;
            s = s.substring( 1 );
        }
        // разрешаем только цифры и точку
        while (i < s.length() && (Character.isDigit(s.charAt(i)) || s.charAt(i) == '.')) {
            // но также проверям, что в числе может быть только одна точка!
            if (s.charAt(i) == '.' && ++dot_cnt > 1) {
                throw new Exception("not valid number '" + s.substring(0, i + 1) + "'");
            }
            i++;
        }
        if( i == 0 ){ // что-либо похожее на число мы не нашли
            throw new Exception( "can't get valid number in '" + s + "'" );
        }

        String dPart = s.substring(0, i);
        if (negative) {
        	dPart = "-" + dPart;
        }
        String restPart = s.substring(i);

        return new Number(dPart, restPart);
    }

    // Тут определяем все нашие функции, которыми мы можем пользоватся в формулах
    private static MathExpression processFunction(String func, MathExpression r)
    {
        /*if (func.equals("sin")) {
            return new MathFunction("sin", Math.sin(Math.toRadians(r.acc)), r.rest);
        } else if (func.equals("cos")) {
            return new MathExpression(Math.cos(Math.toRadians(r.acc)), r.rest);
        } else if (func.equals("tan")) {
            return new MathExpression(Math.tan(Math.toRadians(r.acc)), r.rest);
        } else {
            System.err.println("function '" + func + "' is not defined");
        }
        return r;*/
    	// remove 2 Parentheses
    	if (r instanceof Parentheses) {
    		r = ((Parentheses) r).getArg();
    	}
    	return new MathFunction(func, r, r.rest);
    }
    
    private static MathExpression processFunction(String func, MathExpression r, MathExpression e) {
    	if (r instanceof Parentheses) {
    		r = ((Parentheses) r).getArg();
    	}
    	if (e instanceof Parentheses) {
    		e = ((Parentheses) e).getArg();
    	}
    	return new MathFunction(func, r, e, e.rest);
    }
} 