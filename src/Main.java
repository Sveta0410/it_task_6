public class Main {
    public static void main(String[] args) {
        System.out.println("Задание 1");
        System.out.println(bell(1));
        System.out.println(bell(2));
        System.out.println(bell(3));
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
}