public class Main {
    public static void main(String[] args) {
        System.out.println("Задание 1");
        System.out.println(bell(1));
        System.out.println(bell(2));
        System.out.println(bell(3));

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