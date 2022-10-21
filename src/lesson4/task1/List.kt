@file:Suppress("UNUSED_PARAMETER", "ConvertCallChainIntoSequence")

package lesson4.task1

import lesson1.task1.discriminant
import lesson1.task1.sqr
import kotlin.math.sqrt
import kotlin.math.*

// Урок 4: списки
// Максимальное количество баллов = 12
// Рекомендуемое количество баллов = 8
// Вместе с предыдущими уроками = 24/33

/**
 * Пример
 *
 * Найти все корни уравнения x^2 = y
 */
fun sqRoots(y: Double) =
    when {
        y < 0 -> listOf()
        y == 0.0 -> listOf(0.0)
        else -> {
            val root = sqrt(y)
            // Результат!
            listOf(-root, root)
        }
    }

/**
 * Пример
 *
 * Найти все корни биквадратного уравнения ax^4 + bx^2 + c = 0.
 * Вернуть список корней (пустой, если корней нет)
 */
fun biRoots(a: Double, b: Double, c: Double): List<Double> {
    if (a == 0.0) {
        return if (b == 0.0) listOf()
        else sqRoots(-c / b)
    }
    val d = discriminant(a, b, c)
    if (d < 0.0) return listOf()
    if (d == 0.0) return sqRoots(-b / (2 * a))
    val y1 = (-b + sqrt(d)) / (2 * a)
    val y2 = (-b - sqrt(d)) / (2 * a)
    return sqRoots(y1) + sqRoots(y2)
}

/**
 * Пример
 *
 * Выделить в список отрицательные элементы из заданного списка
 */
fun negativeList(list: List<Int>): List<Int> {
    val result = mutableListOf<Int>()
    for (element in list) {
        if (element < 0) {
            result.add(element)
        }
    }
    return result
}

/**
 * Пример
 *
 * Изменить знак для всех положительных элементов списка
 */
fun invertPositives(list: MutableList<Int>) {
    for (i in 0 until list.size) {
        val element = list[i]
        if (element > 0) {
            list[i] = -element
        }
    }
}

/**
 * Пример
 *
 * Из имеющегося списка целых чисел, сформировать список их квадратов
 */
fun squares(list: List<Int>) = list.map { it * it }

/**
 * Пример
 *
 * Из имеющихся целых чисел, заданного через vararg-параметр, сформировать массив их квадратов
 */
fun squares(vararg array: Int) = squares(array.toList()).toTypedArray()

/**
 * Пример
 *
 * По заданной строке str определить, является ли она палиндромом.
 * В палиндроме первый символ должен быть равен последнему, второй предпоследнему и т.д.
 * Одни и те же буквы в разном регистре следует считать равными с точки зрения данной задачи.
 * Пробелы не следует принимать во внимание при сравнении символов, например, строка
 * "А роза упала на лапу Азора" является палиндромом.
 */
fun isPalindrome(str: String): Boolean {
    val lowerCase = str.lowercase().filter { it != ' ' }
    for (i in 0..lowerCase.length / 2) {
        if (lowerCase[i] != lowerCase[lowerCase.length - i - 1]) return false
    }
    return true
}

/**
 * Пример
 *
 * По имеющемуся списку целых чисел, например [3, 6, 5, 4, 9], построить строку с примером их суммирования:
 * 3 + 6 + 5 + 4 + 9 = 27 в данном случае.
 */
fun buildSumExample(list: List<Int>) = list.joinToString(separator = " + ", postfix = " = ${list.sum()}")

/**
 * Простая (2 балла)
 *
 * Найти модуль заданного вектора, представленного в виде списка v,
 * по формуле abs = sqrt(a1^2 + a2^2 + ... + aN^2).
 * Модуль пустого вектора считать равным 0.0.
 */
fun abs(v: List<Double>): Double {
    var abs = 0.0
    for (i in v.indices) {
        abs += sqr(v[i])
    }
    return sqrt(abs)
}

/**
 * Простая (2 балла)
 *
 * Рассчитать среднее арифметическое элементов списка list. Вернуть 0.0, если список пуст
 */
fun mean(list: List<Double>): Double =
    if (list.isNotEmpty()) list.sum() / list.size
    else 0.0


/**
 * Средняя (3 балла)
 *
 * Центрировать заданный список list, уменьшив каждый элемент на среднее арифметическое всех элементов.
 * Если список пуст, не делать ничего. Вернуть изменённый список.
 *
 * Обратите внимание, что данная функция должна изменять содержание списка list, а не его копии.
 */
fun center(list: MutableList<Double>): MutableList<Double> {
    val s = mean(list)
    for (i in list.indices) {
        list[i] -= s
    }
    return list
}

/**
 * Средняя (3 балла)
 *
 * Найти скалярное произведение двух векторов равной размерности,
 * представленные в виде списков a и b. Скалярное произведение считать по формуле:
 * C = a1b1 + a2b2 + ... + aNbN. Произведение пустых векторов считать равным 0.
 */
fun times(a: List<Int>, b: List<Int>): Int {
    var c = 0
    for (i in a.indices) {
        c += a[i] * b[i]
    }
    return c
}

/**
 * Средняя (3 балла)
 *
 * Рассчитать значение многочлена при заданном x:
 * p(x) = p0 + p1*x + p2*x^2 + p3*x^3 + ... + pN*x^N.
 * Коэффициенты многочлена заданы списком p: (p0, p1, p2, p3, ..., pN).
 * Значение пустого многочлена равно 0 при любом x.
 */
fun polynom(p: List<Int>, x: Int): Int {
    var px = 0.0
    val s = x.toDouble()
    for ((counter, i) in p.indices.withIndex()) {
        px += p[i] * s.pow(counter)
    }
    return px.toInt()
}


/**
 * Средняя (3 балла)
 *
 * В заданном списке list каждый элемент, кроме первого, заменить
 * суммой данного элемента и всех предыдущих.
 * Например: 1, 2, 3, 4 -> 1, 3, 6, 10.
 * Пустой список не следует изменять. Вернуть изменённый список.
 *
 * Обратите внимание, что данная функция должна изменять содержание списка list, а не его копии.
 */
fun accumulate(list: MutableList<Int>): MutableList<Int> {
    for (i in 1 until list.size) {
        list[i] += list[i - 1]
    }
    return list
}

/**
 * Средняя (3 балла)
 *
 * Разложить заданное натуральное число n > 1 на простые множители.
 * Результат разложения вернуть в виде списка множителей, например 75 -> (3, 5, 5).
 * Множители в списке должны располагаться по возрастанию.
 */
fun factorize(n: Int): List<Int> {
    var num = n
    val list = mutableListOf<Int>()
    var counter = 2
    while (num > 1) {
        if (num % counter == 0) {
            list.add(counter)
            num /= counter
            counter = 2
        } else counter++
    }
    return list
}

/**
 * Сложная (4 балла)
 *
 * Разложить заданное натуральное число n > 1 на простые множители.
 * Результат разложения вернуть в виде строки, например 75 -> 3*5*5
 * Множители в результирующей строке должны располагаться по возрастанию.
 */
fun factorizeToString(n: Int): String = factorize(n).joinToString(separator = "*")

/**
 * Средняя (3 балла)
 *
 * Перевести заданное целое число n >= 0 в систему счисления с основанием base > 1.
 * Результат перевода вернуть в виде списка цифр в base-ичной системе от старшей к младшей,
 * например: n = 100, base = 4 -> (1, 2, 1, 0) или n = 250, base = 14 -> (1, 3, 12)
 */
fun convert(n: Int, base: Int): List<Int> {
    val s = mutableListOf<Int>()
    var num = n
    var mod: Int
    if (num == 0) {
        s.add(0)
        return s
    }
    while (num > 0) {
        mod = num % base
        num /= base
        s.add(mod)
    }
    return s.reversed()
}

/**
 * Сложная (4 балла)
 *
 * Перевести заданное целое число n >= 0 в систему счисления с основанием 1 < base < 37.
 * Результат перевода вернуть в виде строки, цифры более 9 представлять латинскими
 * строчными буквами: 10 -> a, 11 -> b, 12 -> c и так далее.
 * Например: n = 100, base = 4 -> 1210, n = 250, base = 14 -> 13c
 *
 * Использовать функции стандартной библиотеки, напрямую и полностью решающие данную задачу
 * (например, n.toString(base) и подобные), запрещается.
 */
fun convertToString(n: Int, base: Int): String {
    val s: List<Int> = convert(n, base)
    val res = mutableListOf<String>()
    for (i in s.indices) {
        if (s[i] > 9) {
            val k = (97 + (s[i] - 10))
            res.add(k.toChar().toString())
        } else
            res.add(s[i].toString())
    }
    return res.joinToString(separator = "")
}

/**
 * Средняя (3 балла)
 *
 * Перевести число, представленное списком цифр digits от старшей к младшей,
 * из системы счисления с основанием base в десятичную.
 * Например: digits = (1, 3, 12), base = 14 -> 250
 */
fun decimal(digits: List<Int>, base: Int): Int {
    val b = base.toDouble()
    var result = 0.0
    var counter = digits.size - 1
    for (i in digits.indices) {
        result += digits[i] * b.pow(counter)
        counter -= 1
    }
    return result.toInt()

}

/**
 * Сложная (4 балла)
 *
 * Перевести число, представленное цифровой строкой str,
 * из системы счисления с основанием base в десятичную.
 * Цифры более 9 представляются латинскими строчными буквами:
 * 10 -> a, 11 -> b, 12 -> c и так далее.
 * Например: str = "13c", base = 14 -> 250
 *
 * Использовать функции стандартной библиотеки, напрямую и полностью решающие данную задачу
 * (например, str.toInt(base)), запрещается.
 */
fun decimalFromString(str: String, base: Int): Int {
    val s = mutableListOf<Char>()
    val res = mutableListOf<Int>()
    for (i in str.indices) {
        s.add(str[i])
    }
    for (i in s.indices) {
        when {
            s[i] == 'a' -> res.add(10)
            s[i] == 'b' -> res.add(11)
            s[i] == 'c' -> res.add(12)
            s[i] == 'd' -> res.add(13)
            s[i] == 'e' -> res.add(14)
            s[i] == 'f' -> res.add(15)
            s[i] == 'g' -> res.add(16)
            s[i] == 'h' -> res.add(17)
            s[i] == 'i' -> res.add(18)
            s[i] == 'j' -> res.add(19)
            s[i] == 'k' -> res.add(20)
            s[i] == 'l' -> res.add(21)
            s[i] == 'm' -> res.add(22)
            s[i] == 'n' -> res.add(23)
            s[i] == 'o' -> res.add(24)
            s[i] == 'p' -> res.add(25)
            s[i] == 'q' -> res.add(26)
            s[i] == 'r' -> res.add(27)
            s[i] == 's' -> res.add(28)
            s[i] == 't' -> res.add(29)
            s[i] == 'u' -> res.add(30)
            s[i] == 'v' -> res.add(31)
            s[i] == 'w' -> res.add(32)
            s[i] == 'x' -> res.add(33)
            s[i] == 'y' -> res.add(34)
            s[i] == 'z' -> res.add(35)
            else -> res.add(s[i].toInt() - 48)
        }
    }
    return decimal(res, base)
}

/**
 * Сложная (5 баллов)
 *
 * Перевести натуральное число n > 0 в римскую систему.
 * Римские цифры: 1 = I, 4 = IV, 5 = V, 9 = IX, 10 = X, 40 = XL, 50 = L,
 * 90 = XC, 100 = C, 400 = CD, 500 = D, 900 = CM, 1000 = M.
 * Например: 23 = XXIII, 44 = XLIV, 100 = C
 */
fun roman(n: Int): String {
    var res = ""
    var s = n
    var k: Int
    var counter = 0
    while (s > 0) {
        k = s % 10
        counter += 1
        if (counter == 1) {
            when {
                (k == 1) -> res = "I$res"
                (k == 2) -> res = "II$res"
                (k == 3) -> res = "III$res"
                (k == 5) -> res = "V$res"
                (k == 4) -> res = "IV$res"
                (k == 6) -> res = "VI$res"
                (k == 7) -> res = "VII$res"
                (k == 8) -> res = "VIII$res"
                (k == 9) -> res = "IX$res"
            }
        }
        if (counter == 2) {
            when {
                (k == 1) -> res = "X$res"
                (k == 2) -> res = "XX$res"
                (k == 3) -> res = "XXX$res"
                (k == 5) -> res = "L$res"
                (k == 4) -> res = "XL$res"
                (k == 6) -> res = "LX$res"
                (k == 7) -> res = "LXX$res"
                (k == 8) -> res = "LXXX$res"
                (k == 9) -> res = "XC$res"
            }
        }
        if (counter == 3) {
            when {
                (k == 1) -> res = "C$res"
                (k == 2) -> res = "CC$res"
                (k == 3) -> res = "CCC$res"
                (k == 5) -> res = "D$res"
                (k == 4) -> res = "CD$res"
                (k == 6) -> res = "DC$res"
                (k == 7) -> res = "DCC$res"
                (k == 8) -> res = "DCCC$res"
                (k == 9) -> res = "CM$res"
            }
        }
        if (counter == 4) {
            when {
                (k == 1) -> res = "M$res"
                (k == 2) -> res = "MM$res"
                (k == 3) -> res = "MMM$res"
                (k == 5) -> res = "MMMMM$res"
                (k == 4) -> res = "MMMM$res"
                (k == 6) -> res = "MMMMMM$res"
                (k == 7) -> res = "MMMMMMM$res"
                (k == 8) -> res = "MMMMMMMM$res"
                (k == 9) -> res = "MMMMMMMMM$res"
            }
        }
        s /= 10
    }
    return res
}

/**
 * Очень сложная (7 баллов)
 *
 * Записать заданное натуральное число 1..999999 прописью по-русски.
 * Например, 375 = "триста семьдесят пять",
 * 23964 = "двадцать три тысячи девятьсот шестьдесят четыре"
 */
fun russian(n: Int): String = TODO()