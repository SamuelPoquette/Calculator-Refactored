package simplejavacalculator;

import static java.lang.Math.log;
import static java.lang.Math.log10;
import static java.lang.Math.pow;

public class Calculator {
    public enum BiOperatorModes {
        normal, add, minus, multiply, divide , xpowerofy 
    }

    public static class MonoOperatorModes {
    	
        static Double square(Double num) {
            return num * num;
        }
        static Double squareRoot(Double num) {
            return Math.sqrt(num);
        }
        static Double oneDevidedBy(Double num) {
            return 1 / num;
        }
        static Double cos(Double num) {
            return Math.cos(num);
        }
        static Double sin(Double num) {
            return Math.sin(num);
        }
        static Double tan(Double num) {
            return Math.tan(num);
        }
        static Double log(Double num) {
            return log10(num);
        }
        static Double rate(Double num) {
            return num / 100;
        }
    };

	private Double num1, num2;
    private BiOperatorModes mode = BiOperatorModes.normal;

    private Double calculateBiImpl() {
        if (mode == BiOperatorModes.normal) {
            return num2;
        }
        if (mode == BiOperatorModes.add) {
            return num1 + num2;
        }
        if (mode == BiOperatorModes.minus) {
            return num1 - num2;
        }
        if (mode == BiOperatorModes.multiply) {
            return num1 * num2;
        }
        if (mode == BiOperatorModes.divide) {
            return num1 / num2;
        }
        if (mode == BiOperatorModes.xpowerofy) {
            return pow(num1,num2);
        }

        // never reach
        throw new Error();
    }

    public Double calculateBi(BiOperatorModes newMode, Double num) {
        if (mode == BiOperatorModes.normal) {
            num2 = 0.0;
            num1 = num;
            mode = newMode;
            return Double.NaN;
        } else {
            num2 = num;
            num1 = calculateBiImpl();
            mode = newMode;
            return num1;
        }
    }

    public Double calculateEqual(Double num) {
        return calculateBi(BiOperatorModes.normal, num);
    }

    public Double reset() {
        num2 = 0.0;
        num1 = 0.0;
        mode = BiOperatorModes.normal;

        return Double.NaN;
    }

//    public Double calculateMono(MonoOperatorModes newMode, Double num) {
//        if (newMode == MonoOperatorModes.square) {
//            return num * num;
//        }
//        if (newMode == MonoOperatorModes.squareRoot) {
//            return Math.sqrt(num);
//        }
//        if (newMode == MonoOperatorModes.oneDevidedBy) {
//            return 1 / num;
//        }
//        if (newMode == MonoOperatorModes.cos) {
//            return Math.cos(num);
//        }
//        if (newMode == MonoOperatorModes.sin) {
//            return Math.sin(num);
//        }
//        if (newMode == MonoOperatorModes.tan) {
//            return Math.tan(num);
//        }
//        if (newMode == MonoOperatorModes.log) {
//            return log10(num);
//        }
//        if (newMode == MonoOperatorModes.rate) {
//           return num / 100;
//        }
//        
//
//
//        // never reach
//        throw new Error();
//    }

}
