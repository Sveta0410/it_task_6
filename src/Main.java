import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        System.out.println("Задание 1");
        System.out.println(bell(1));
        System.out.println(bell(2));
        System.out.println(bell(3));

        System.out.println("Задание 2");
        System.out.println(translateWord("flag"));
        System.out.println(translateWord("Apple"));
        System.out.println(translateWord("button"));
        System.out.println(translateWord(""));
        System.out.println(translateSentence("I like to eat honey waffles."));
        System.out.println(translateSentence("Do you think it is going to rain today?"));

        System.out.println("Задание 3");
        System.out.println(validColor("rgb(0,0,0)"));
        System.out.println(validColor("rgb(0,,0)"));
        System.out.println(validColor("rgb(255,256,255)"));
        System.out.println(validColor("rgba(0,0,0,0.123456789)"));

        System.out.println("Задание 4");
        String[] array1 = new String[]{"b"};
        System.out.println(stripUrlParams("https://edabit.com?a=1&b=2&a=2"));
        System.out.println(stripUrlParams("https://edabit.com?a=1&b=2&a=2", array1));
        System.out.println(stripUrlParams("https://edabit.com", array1));

        System.out.println("Задание 5");
        System.out.println(Arrays.toString(getHashTags("How the Avocado Became the Fruit of the Global Trade")));
        System.out.println(Arrays.toString(getHashTags(
                "Why You Will Probably Pay More for Your Christmas Tree This Year")));
        System.out.println(Arrays.toString(getHashTags("Hey Parents, Surprise, Fruit Juice Is Not Fruit")));
        System.out.println(Arrays.toString(getHashTags("Visualizing Science")));

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

    // переводчик с английского на свинский латинский
    public static String translateWord(String str) {
        if ("".equals(str)) {
            return "";
        }
        StringBuilder result = new StringBuilder();
        String firstLetter = String.valueOf(str.charAt(0));
        String lastLetter = String.valueOf(str.charAt(str.length() - 1));
        String vowels = "aeiouyAEIOUY";
        // если первая буква - глассная
        if (vowels.contains(firstLetter)) {
            // если после слова стоит знак препинания
            if ("!.,?".contains(lastLetter)) {
                result.append(str, 0, str.length() - 1).append("yay").append(lastLetter);
            } else {
                result.append(str).append("yay");
            }
        } else {
            // делим слово на массив из букв
            String[] strArr = str.split("");
            // согласные, найденные до гласной
            StringBuilder consonant = new StringBuilder();
            // перебираем буквы в слове
            for (String i : strArr) {
                // пока не нашли первую гласную
                if (!vowels.contains(i)) {
                    consonant.append(i);
                } else {
                    // убираем согласные из начала слова
                    str = str.substring(consonant.length());
                    if ("!.,?".contains(lastLetter)) {
                        result.append(str, 0, str.length() - 1)
                                .append(consonant).append("ay").append(lastLetter);
                    } else {
                        result.append(str).append(consonant).append("ay");
                    }
                    break;
                }
            }
        }
        return result.toString();
    }


    public static String translateSentence(String str) {
        String[] words = str.split(" ");
        StringBuilder result = new StringBuilder();
        StringBuilder word;
        String firstLetter = String.valueOf(str.charAt(0)).toUpperCase();
        // перебираем слова и переводим из, использую ранее написанную функцию
        for (int i = 0; i < words.length; i++) {
            word = new StringBuilder(translateWord(words[i]));
            // если это первое слово в предложении
            if (i == 0) {
                word = new StringBuilder(firstLetter + word.toString().toLowerCase().substring(1));
            }
            result.append(word).append(" ");
        }
        // убираем последний пробел
        result.setLength(result.length() - 1);
        return result.toString();
    }

    // принимаем строку (например, " rgb(0, 0, 0)") и возвращаем true, если ее формат правильный,
    // в противном случае возвращаем false
    public static boolean validColor(String str) {
        // d+ - одна цифра и более
        Pattern pattern1 = Pattern.compile("rgb\\(\\d+,\\d+,\\d+\\)");
        Matcher matcher1 = pattern1.matcher(str);
        // если строка не соответствует условиям rgb( , , , )
        if (!matcher1.matches()) {
            // 1|(0(\.\d+)?) = 1 или 0 с числами после точки
            // (\.\d+)? - чисел после точки может не быть
            Pattern pattern2 = Pattern.compile("rgba\\(\\d+,\\d+,\\d+,(1|(0(\\.\\d+)?))\\)");
            Matcher matcher2 = pattern2.matcher(str);
            if (!matcher2.matches()) {
                return false;
            } else {
                // оставляем в строке только цифры
                str = str.substring("rgba(".length(), str.length() - 1);
                String[] numbers = str.split(",");
                // если первые два числа НЕ больше нуля и НЕ меньше 255
                if (!(Integer.parseInt(numbers[0]) <= 255 && Integer.parseInt(numbers[0]) >= 0)) {
                    return false;
                } else return Integer.parseInt(numbers[1]) <= 255 && Integer.parseInt(numbers[1]) >= 0;
            }
        } else {
            // оставляем в строке только цифры
            str = str.substring("rgb(".length(), str.length() - 1);
            String[] numbers = str.split(",");
            for (String i : numbers) {
                if (!(Integer.parseInt(i) <= 255 && Integer.parseInt(i) >= 0)) {
                    return false;
                }
            }
            return true;
        }
    }

    // принимаем URL (строку), удаляем дублирующиеся параметры запроса и параметры, указанные во втором аргументе
    // (который является необязательным массивом)
    public static String stripUrlParams(String url) {
        return stripUrlParams(url, new String[]{""});
    }

    public static String stripUrlParams(String url, String[] toStrip) {
        // если в url параметров нет, сразу возвращаем просто url
        if (!url.contains("?")) {
            return url;
        }
        // hashmap для хранения пар запроса = буква + число
        HashMap<String, String> pairs = new HashMap<>();
        // делим url на части = адрес + параметры запроса
        String[] urlArr = url.split("\\?");
        String[] params = urlArr[1].split("&");
        // перебираем все пары параметров и добавляем в hashmap (повторяющихся элементов не будет)
        for (String i : params) {
            String[] temp = i.split("=");
            pairs.put(temp[0], temp[1]);
        }
        StringBuilder result = new StringBuilder(urlArr[0] + "?");
        List<String> toStripList = Arrays.asList(toStrip);
        for (String key : pairs.keySet()) {
            if (!toStripList.contains(key)) {
                result.append(key).append("=").append(pairs.get(key)).append("&");
            }
        }
        // убираем последний символ в ответе ("&")
        result.setLength(result.length() - 1);
        return result.toString();

    }

    // извлекаем три самых длинных слова из заголовка газеты и преобразовываем их в хэштеги
    /*
    если несколько слов одинаковой длины, находим слово, которое встречается первым
    если заголовок содержит менее 3 слов, распологаем слова в заголовке по длине в порядке убывания
    */
    public static String[] getHashTags(String str) {
        // убираем все знаки препинания
        str = str.replaceAll("\\p{Punct}", "");
        String[] words = str.split(" ");
        ArrayList<String> result = new ArrayList<>();

        // чтобы не было ошибок, добавляем пустые строки в ArrayList
        result.add("");
        result.add("");
        result.add("");

        // перебираем все слова
        for (String word : words) {
            // сравниваем с самым коротким словом из списка длинных слов с текущим словом
            // +1 т.к. учитываем #
            if (word.length() + 1 > result.get(2).length()) {
                // сравнимаем со вторым словом
                if (word.length() + 1 > result.get(1).length()) {
                    // сравниваем с первым
                    if (word.length() + 1 > result.get(0).length()) {
                        // текущее слово ставим на первое место
                        result.set(2, result.get(1));
                        result.set(1, result.get(0));
                        result.set(0, "#" + word.toLowerCase());
                    } else {
                        // текущее слово ставим на второе место
                        result.set(2, result.get(1));
                        result.set(1, "#" + word.toLowerCase());
                    }
                } else {
                    // текущее слово ставим на третье место
                    result.set(2, "#" + word.toLowerCase());
                }
            }
        }
        // результат - массив из строк
        // определяем размеры массива
        int size = result.size();
        // если последний элемент списка пустой - делаем массив на единицу меньше
        if ("".equals(result.get(2))) {
            size--;
        } else if ("".equals(result.get(1))) {
            size -= 2;
        }
        String[] answer = new String[size];
        for (int i = 0; i < result.size(); i++) {
            if (!"".equals(result.get(i))) {
                answer[i] = result.get(i);
            }
        }
        return answer;
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
        return ulam.get(ulam.size() - 1);
    }


    // возвращаем самую длинную неповторяющуюся подстроку для строкового ввода
    public static String longestNonrepeatingSubstring(String str) {
        StringBuilder result = new StringBuilder();
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
            } else if (num == 9) {
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
                StringBuilder res = new StringBuilder();
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