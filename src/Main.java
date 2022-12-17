import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        System.out.println("Задание 1");
        System.out.println(bell(1));
        System.out.println(bell(2));
        System.out.println(bell(3));

        System.out.println("Задание 6");
        System.out.println(ulam(4));
        System.out.println(ulam(9));
        System.out.println(ulam(206));

        System.out.println("Задание 7");
        System.out.println(longestNonrepeatingSubstring("abcabcbb"));
        System.out.println(longestNonrepeatingSubstring("aaaaaa"));
        System.out.println(longestNonrepeatingSubstring("abcde"));
        System.out.println(longestNonrepeatingSubstring("abcda"));

        System.out.println("Задание 8");
        System.out.println(convertToRoman(2));
        System.out.println(convertToRoman(12));
        System.out.println(convertToRoman(16));

        System.out.println("Задание 9");
        System.out.println(formula("6 * 4 = 24"));
        System.out.println(formula("18 / 17 = 2"));
        System.out.println(formula("16 * 10 = 160 = 14 + 120"));

        System.out.println("Задание 10");
        System.out.println(palindromedescendant(11211230));
        System.out.println(palindromedescendant(13001120));
        System.out.println(palindromedescendant(23336014));
        System.out.println(palindromedescendant(11));
        System.out.println(palindromedescendant(121214));
    }

    // Число Белла - кол-во способов, которыми массив из n элементов может быть разбит на непустые подмножества.
    // функция принимает число n и возвращает соответствующее число Белла.
    // вычисляем с помощью треугольника Пирса
    public static int bell(int num) {
        // начинаем с единицы
        int result = 1;
        // создаём двумерный массив
        int[][] triangle = new int[num][num];
        // первый элемент массива - единичка
        triangle[0][0] = 1;
        // создаём переменную для номера последнего элемента в строке треугольника
        int last = 0;
        // проходим по строкам
        for (int i = 1; i < num; i++) {
            // первый элемент новой строки - последний элемент предыдущей
            triangle[i][0] = triangle[i - 1][last];
            // проходим по элементам в строке
            for (int j = 1; j <= i; j++) {
                triangle[i][j] = triangle[i - 1][j - 1] + triangle[i][j - 1];
                // если дошли до последнего элемента
                if (j == i) {
                    result = triangle[i][j];
                    last = j;
                }
            }
        }
        return result;
    }

    // принимаем число n и возвращаем n-е число в последовательности Улама
    /*
    Последовательность Улама начинается с 1,2
    Следующее число в последовательности - это наименьшее положительное число, равное
    сумме двух разных чисел (которые уже есть в последовательности) ровно одним
    способом.
     */
    public static int ulam(int num) {
        int count;
        int sum = 2;
        ArrayList<Integer> ulam = new ArrayList<>();
        ulam.add(1);
        ulam.add(2);
        // пока мы не добавили заданное количество жлементов в последовательность
        while (ulam.size() < num) {
            count = 0;
            sum++; //для каждой итерации увеличиваем сумму на 1 (и проверяем, подходит ли наша сумма под условия Улама)
            // перебираем все элементы
            for (int i = 0; i < ulam.size(); i++) {
                for (int j = i + 1; j < ulam.size(); j++) {
                    if (ulam.get(i) + ulam.get(j) == sum) {
                        count++; // увеличиваем количество способов получения числа
                    }
                    // если способов получения больше одного, эти значения не берём
                    if (count > 1) {
                        break;
                    }
                }
                // если к первому числу пары мы не смогли подобрать второе,
                // чтобы пар удовлетворяла условиям, берём следующее
                if (count > 1) {
                    break;
                }
            }
            // если после всего перебора мы нашли подходящуу пару, добавляем сумму в список с числами Улама
            if (count == 1) {
                ulam.add(sum);
            }
        }
        // возвращаем последний элемент последовательности
        return ulam.get(ulam.size()-1);
    }


    // возвращаем самую длинную неповторяющуюся подстроку для строкового ввода
    public static String longestNonrepeatingSubstring(String str) {
        int maxLength = 0;
        StringBuilder result = new StringBuilder();
//        StringBuilder temp = new StringBuilder();
        ArrayList<String> strings = new ArrayList<>();
        String[] strArr = str.split("");
        for (String i : strArr) {
            // если символа в строке нет
            if (result.indexOf(i) == -1) {
                result.append(i);
            } else {
                strings.add(result.toString());
                // обрезаем строчку по индексу символа, который посторился (первый из пары)
                result = new StringBuilder(result.substring(result.indexOf(i) + 1));
                result.append(i);
            }
        }
        // добавляем последнюю строчку
        strings.add(result.toString());
        String answer = "";
        // перебираем все элементы массива из строк с неповторяющимися символами и находим строку максимальной длины
        for (String i : strings) {
            if (i.length() > answer.length()) {
                answer = i;
            }
        }
        return answer;
    }

    // принимаем арабское число и преобразуем его в римское число
    public static String convertToRoman(int num) {
        StringBuilder result = new StringBuilder();
        while (num > 0) {
            if (num >= 1000) {
                num -= 1000;
                result.append("M");
            } else if (num >= 900) {
                num -= 900;
                result.append("CM");
            } else if (num >= 500) {
                num -= 500;
                result.append("D");
            } else if (num >= 400) {
                num -= 400;
                result.append("CD");
            } else if (num >= 100) {
                num -= 100;
                result.append("C");
            } else if (num >= 90) {
                num -= 90;
                result.append("XC");
            } else if (num >= 50) {
                num -= 50;
                result.append("L");
            } else if (num >= 40) {
                num -= 40;
                result.append("XL");
            } else if (num >= 10) {
                num -= 10;
                result.append("X");
            } else if (num >= 9) {
                num -= 9;
                result.append("IX");
            } else if (num >= 5) {
                num -= 5;
                result.append("V");
            } else if (num == 4) {
                num -= 4;
                result.append("IV");
            } else {
                num -= 1;
                result.append("I");
            }
        }
        return result.toString();
    }

    // принимаем строку и возвращаем true или false в зависимости от того, является ли формула правильной или нет
    public static boolean formula(String str) {
        // убираем из строки все пробелы
        str = str.replace(" ", "");
        // делим строку на части по пробелам
        String[] parts = str.split("=");
        // запоминаем первое значение (с ним будем сравнивать всё остальное)
        float firstPart = partResult(parts[0]);
        // перебираем все части
        for (String part : parts) {
            // если хоть одна часть не равна первой - выражение неверно, возвращаем false
            if (firstPart != partResult(part)) {
                return false;
            }
        }
        return true;
    }

    // вспомогательная функция, которая ищет результат вычислений в каждой части
    public static float partResult(String s) {
        if (s.contains("+")) {
            String[] numbers = s.split("\\+"); // экранируем плюсик
            return Float.parseFloat(numbers[0]) + Float.parseFloat(numbers[1]);
        } else if (s.contains("-")) {
            String[] numbers = s.split("-");
            return Float.parseFloat(numbers[0]) - Float.parseFloat(numbers[1]);
        } else if (s.contains("*")) {
            String[] numbers = s.split("\\*");
            return Float.parseFloat(numbers[0]) * Float.parseFloat(numbers[1]);
        } else if (s.contains("/")) {
            String[] numbers = s.split("/");
            return Float.parseFloat(numbers[0]) / Float.parseFloat(numbers[1]);
        }
        return Float.parseFloat(s);
    }


    // Прямой потомок числа = сумма каждой пары соседних цифр (они являются цифрами следующего числа)
    // функция возвращает true, если число является палиндромом или любой из его потомков вплоть до 2 цифр
    // (однозначное число - тривиально палиндром)
    public static boolean palindromedescendant(int num) {
        int result = 0;
        // делаем из числа строку
        StringBuilder numStr = new StringBuilder(Integer.toString(num));

        while (true) {
            // если количество цифр нечётное
            if (numStr.length() % 2 != 0) {
                // проверяем, является ли число палиндромом
                return String.valueOf(numStr).equals(numStr.reverse().toString());
            }
            if (String.valueOf(numStr).equals(numStr.reverse().toString())) {
                return true;
            } else {
                StringBuilder res = new StringBuilder("");
                // перебираем все пары (составляем новое число)
                for (int i = 0; i < numStr.length(); i += 2) {
                    // -48 т.к. считается в ascii
                    res.append(numStr.charAt(i) - 48 + numStr.charAt(i + 1) - 48);
                }
                numStr = res;
            }
        }
    }
}