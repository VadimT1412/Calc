import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Внимание!!! введите выражение. (Числа должны быть арабские от 1 до 10 или римские от I до X, при этом пример должен содержать только один из операторов (+, -, /, *))");
        String input = scanner.nextLine();
        System.out.println(calc(input));
        scanner.close();
    }

    public static String calc(String input) throws Exception {
        int numberOne;
        int numberTwo;
        boolean isRoman;
        String[] stringSplit = input.split(" ");
        int len = stringSplit.length;
        if (len > 3)
            throw new Exception("Внимание!!! формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
        if (len < 3) throw new Exception("Внимание!!! строка не является математической операцией");
        String num1 = stringSplit[0];
        String action = stringSplit[1];
        String num2 = stringSplit[2];
        if ((action.equals("+")) || (action.equals("-")) || (action.equals("*")) || (action.equals("/"))) {
            if (Roman.isRoman(stringSplit[0]) && Roman.isRoman(stringSplit[2])) {
                numberOne = Roman.convertToArabian(stringSplit[0]);
                numberTwo = Roman.convertToArabian(stringSplit[2]);
                isRoman = true;
            } else if ((num1.equals("0")) || (num2.equals("0")))
                throw new Exception("Внимание!!! числа должны быть арабские от 1 до 10 или римские от I до X");
            else if (!Roman.isRoman(stringSplit[0]) && !Roman.isRoman(stringSplit[2])) {
                numberOne = Integer.parseInt(stringSplit[0]);
                numberTwo = Integer.parseInt(stringSplit[2]);
                isRoman = false;
            } else throw new Exception("Внимание!!! используются одновременно разные системы счисления");
            if (numberOne > 10 || numberTwo > 10 || numberOne < 1 || numberTwo < 1)
                throw new Exception("Внимание!!! числа должны быть арабские от 1 до 10 или римские от I до X");
            int variant = expression(numberOne, numberTwo, action);
            if (isRoman) {
                if (variant < 0) throw new Exception("Внимание!!! в римской системе нет отрицательных чисел");
                if ((num1.equals(0)) || (num2.equals(0)))
                    throw new Exception("Внимание!!! числа должны быть арабские от 1 до 10 или римские от I до X");
                input = Roman.convertToRoman(variant);
            } else
                input = String.valueOf(variant);
            return input;
        }
        throw new Exception("Внимание!!! формат математической операции не удовлетворяет заданию (+, -, /, *)");
    }

    static int expression(int a, int b, String primer) throws Exception {
        if (primer.equals("+")) return a + b;
           else if (primer.equals("-")) return a - b;
           else if (primer.equals("*")) return a * b;
           else if (primer.equals("/")) ;
        return a / b;
    }

    class Roman {
        static String[] roman = new String[]{"0", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "XI", "XII", "XIII", "XIV", "XV", "XVI", "XVII", "XVIII", "XIX", "XX",
                "XXI", "XXII", "XXIII", "XXIV", "XXV", "XXVI", "XXVII", "XXVIII", "XXIX", "XXX", "XXXI", "XXXII", "XXXIII", "XXXIV", "XXXV", "XXXVI", "XXXVII", "XXXVIII", "XXXIX", "XL",
                "XLI", "XLII", "XLIII", "XLIV", "XLV", "XLVI", "XLVII", "XLVIII", "XLIX", "L", "LI", "LII", "LIII", "LIV", "LV", "LVI", "LVII", "LVIII", "LIX", "LX",
                "LXI", "LXII", "LXIII", "LXIV", "LXV", "LXVI", "LXVII", "LXVIII", "LXIX", "LXX", "LXXI", "LXXII", "LXXIII", "LXXIV", "LXXV", "LXXVI", "LXXVII", "LXXVIII", "LXXIX", "LXXX",
                "LXXXI", "LXXXII", "LXXXIII", "LXXXIV", "LXXXV", "LXXXVI", "LXXXVII", "LXXXVIII", "LXXXIX", "XC", "XCI", "XCII", "XCIII", "XCIV", "XCV", "XCVI", "XCVII", "XCVIII", "XCIX", "C"};

        public static boolean isRoman(String val) {
            for (int i = 0; i < roman.length; i++) {
                if (val.equals(roman[i])) {
                    return true;
                }
            }
            return false;
        }

        public static int convertToArabian(String romanTo) {
            for (int i = 0; i < roman.length; i++) {
                if (romanTo.equals(roman[i])) {
                    return i;
                }
            }
            return -1;
        }

        public static String convertToRoman(int arabian) {
            return roman[arabian];
        }
    }
}
