@file:Suppress("UNUSED_PARAMETER", "ConvertCallChainIntoSequence")

package lesson6.task1

import lesson2.task2.daysInMonth

// Урок 6: разбор строк, исключения
// Максимальное количество баллов = 13
// Рекомендуемое количество баллов = 11
// Вместе с предыдущими уроками (пять лучших, 2-6) = 40/54

/**
 * Пример
 *
 * Время представлено строкой вида "11:34:45", содержащей часы, минуты и секунды, разделённые двоеточием.
 * Разобрать эту строку и рассчитать количество секунд, прошедшее с начала дня.
 */
fun timeStrToSeconds(str: String): Int {
    val parts = str.split(":")
    var result = 0
    for (part in parts) {
        val number = part.toInt()
        result = result * 60 + number
    }
    return result
}

/**
 * Пример
 *
 * Дано число n от 0 до 99.
 * Вернуть его же в виде двухсимвольной строки, от "00" до "99"
 */
fun twoDigitStr(n: Int) = if (n in 0..9) "0$n" else "$n"

/**
 * Пример
 *
 * Дано seconds -- время в секундах, прошедшее с начала дня.
 * Вернуть текущее время в виде строки в формате "ЧЧ:ММ:СС".
 */
fun timeSecondsToStr(seconds: Int): String {
    val hour = seconds / 3600
    val minute = (seconds % 3600) / 60
    val second = seconds % 60
    return String.format("%02d:%02d:%02d", hour, minute, second)
}

/**
 * Пример: консольный ввод
 */
fun main() {
    println("Введите время в формате ЧЧ:ММ:СС")
    val line = readLine()
    if (line != null) {
        val seconds = timeStrToSeconds(line)
        if (seconds == -1) {
            println("Введённая строка $line не соответствует формату ЧЧ:ММ:СС")
        } else {
            println("Прошло секунд с начала суток: $seconds")
        }
    } else {
        println("Достигнут <конец файла> в процессе чтения строки. Программа прервана")
    }
}


/**
 * Средняя (4 балла)
 *
 * Дата представлена строкой вида "15 июля 2016".
 * Перевести её в цифровой формат "15.07.2016".
 * День и месяц всегда представлять двумя цифрами, например: 03.04.2011.
 * При неверном формате входной строки вернуть пустую строку.
 *
 * Обратите внимание: некорректная с точки зрения календаря дата (например, 30.02.2009) считается неверными
 * входными данными.
 */
fun dateStrToDigit(str: String): String {
    val parts = str.split(" ")
    if (parts.size < 3) return ""
    if (parts[0].toIntOrNull() == null) return ""
    if (parts[2].toIntOrNull() == null) return ""
    val months = mapOf<String, String>(
        "января" to "01", "февраля" to "02",
        "марта" to "03", "апреля" to "04", "мая" to "05", "июня" to "06", "июля" to "07",
        "августа" to "08", "сентября" to "09", "октября" to "10", "ноября" to "11",
        "декабря" to "12"
    )
    val day = when {
        parts[0].toInt() in 1..9 && "0" !in parts[0] -> "0" + parts[0] + "."
        parts[0].toInt() in 1..months[parts[1]]?.let {
            daysInMonth(
                it.toInt(),
                parts[2].toInt()
            )
        }!! -> parts[0] + "."

        else -> return ""
    }

    val month = if (parts[1] in months) {
        months[parts[1]] + "."
    } else return ""

    val year = parts[2]
    return day + month + year
}

/**
 * Средняя (4 балла)
 *
 * Дата представлена строкой вида "15.07.2016".
 * Перевести её в строковый формат вида "15 июля 2016".
 * При неверном формате входной строки вернуть пустую строку
 *
 * Обратите внимание: некорректная с точки зрения календаря дата (например, 30 февраля 2009) считается неверными
 * входными данными.
 */
fun dateDigitToStr(digital: String): String {
    val parts = digital.split(".")
    if (parts.size != 3) return ""
    if (parts[0].toIntOrNull() == null) return ""
    if (parts[2].toIntOrNull() == null) return ""
    val months = mapOf(
        "01" to "января ", "02" to "февраля ",
        "03" to "марта ", "04" to "апреля ", "05" to "мая ", "06" to "июня ", "07" to "июля ",
        "08" to "августа ", "09" to "сентября ", "10" to "октября ", "11" to "ноября ",
        "12" to "декабря "
    )
    val day = when {
        parts[0].toInt() in 1..daysInMonth(
            parts[1].toInt(), parts[2].toInt()
        ) -> parts[0].toInt().toString() + " "

        else -> return ""
    }

    val month = if (parts[1] in months) {
        months[parts[1]]
    } else return ""


    val year = parts[2]
    return day + month + year
}
/**
 * Средняя (4 балла)
 *
 * Номер телефона задан строкой вида "+7 (921) 123-45-67".
 * Префикс (+7) может отсутствовать, код города (в скобках) также может отсутствовать.
 * Может присутствовать неограниченное количество пробелов и чёрточек,
 * например, номер 12 --  34- 5 -- 67 -89 тоже следует считать легальным.
 * Перевести номер в формат без скобок, пробелов и чёрточек (но с +), например,
 * "+79211234567" или "123456789" для приведённых примеров.
 * Все символы в номере, кроме цифр, пробелов и +-(), считать недопустимыми.
 * При неверном формате вернуть пустую строку.
 *
 * PS: Дополнительные примеры работы функции можно посмотреть в соответствующих тестах.
 */
fun flattenPhoneNumber(phone: String): String = TODO()

/**
 * Средняя (5 баллов)
 *
 * Результаты спортсмена на соревнованиях в прыжках в длину представлены строкой вида
 * "706 - % 717 % 703".
 * В строке могут присутствовать числа, черточки - и знаки процента %, разделённые пробелами;
 * число соответствует удачному прыжку, - пропущенной попытке, % заступу.
 * Прочитать строку и вернуть максимальное присутствующее в ней число (717 в примере).
 * При нарушении формата входной строки или при отсутствии в ней чисел, вернуть -1.
 */
fun bestLongJump(jumps: String): Int = TODO()

/**
 * Сложная (6 баллов)
 *
 * Результаты спортсмена на соревнованиях в прыжках в высоту представлены строкой вида
 * "220 + 224 %+ 228 %- 230 + 232 %%- 234 %".
 * Здесь + соответствует удачной попытке, % неудачной, - пропущенной.
 * Высота и соответствующие ей попытки разделяются пробелом.
 * Прочитать строку и вернуть максимальную взятую высоту (230 в примере).
 * При нарушении формата входной строки, а также в случае отсутствия удачных попыток,
 * вернуть -1.
 */
fun bestHighJump(jumps: String): Int {
    if (!jumps.matches(Regex("""\d+ [-+%]+( \d+ [-+%]+)*"""))) return -1
    val res = jumps.replace(Regex("""[%-]"""), "").split(" ")
    return res[res.lastIndexOf("+") - 1].toInt()
}

/**
 * Сложная (6 баллов)
 *
 * В строке представлено выражение вида "2 + 31 - 40 + 13",
 * использующее целые положительные числа, плюсы и минусы, разделённые пробелами.
 * Наличие двух знаков подряд "13 + + 10" или двух чисел подряд "1 2" не допускается.
 * Вернуть значение выражения (6 для примера).
 * Про нарушении формата входной строки бросить исключение IllegalArgumentException
 */
fun plusMinus(expression: String): Int = TODO()

/**
 * Сложная (6 баллов)
 *
 * Строка состоит из набора слов, отделённых друг от друга одним пробелом.
 * Определить, имеются ли в строке повторяющиеся слова, идущие друг за другом.
 * Слова, отличающиеся только регистром, считать совпадающими.
 * Вернуть индекс начала первого повторяющегося слова, или -1, если повторов нет.
 * Пример: "Он пошёл в в школу" => результат 9 (индекс первого 'в')
 */
fun firstDuplicateIndex(str: String): Int {
    val strng = str.lowercase().split(" ")
    var counter = 0
    if (strng.size == 1) return -1
    for (i in 1 until strng.size) {
        if (strng[i - 1] == strng[i])
            return counter
        counter += strng[i - 1].length + 1
    }
    return -1
}

/**
 * Сложная (6 баллов)
 *
 * Строка содержит названия товаров и цены на них в формате вида
 * "Хлеб 39.9; Молоко 62; Курица 184.0; Конфеты 89.9".
 * То есть, название товара отделено от цены пробелом,
 * а цена отделена от названия следующего товара точкой с запятой и пробелом.
 * Вернуть название самого дорогого товара в списке (в примере это Курица),
 * или пустую строку при нарушении формата строки.
 * Все цены должны быть больше нуля либо равны нулю.
 */
fun mostExpensive(description: String): String = TODO()

/**
 * Сложная (6 баллов)
 *
 * Перевести число roman, заданное в римской системе счисления,
 * в десятичную систему и вернуть как результат.
 * Римские цифры: 1 = I, 4 = IV, 5 = V, 9 = IX, 10 = X, 40 = XL, 50 = L,
 * 90 = XC, 100 = C, 400 = CD, 500 = D, 900 = CM, 1000 = M.
 * Например: XXIII = 23, XLIV = 44, C = 100
 *
 * Вернуть -1, если roman не является корректным римским числом
 */
fun fromRoman(roman: String): Int = TODO()

/**
 * Очень сложная (7 баллов)
 *
 * Имеется специальное устройство, представляющее собой
 * конвейер из cells ячеек (нумеруются от 0 до cells - 1 слева направо) и датчик, двигающийся над этим конвейером.
 * Строка commands содержит последовательность команд, выполняемых данным устройством, например +>+>+>+>+
 * Каждая команда кодируется одним специальным символом:
 *	> - сдвиг датчика вправо на 1 ячейку;
 *  < - сдвиг датчика влево на 1 ячейку;
 *	+ - увеличение значения в ячейке под датчиком на 1 ед.;
 *	- - уменьшение значения в ячейке под датчиком на 1 ед.;
 *	[ - если значение под датчиком равно 0, в качестве следующей команды следует воспринимать
 *  	не следующую по порядку, а идущую за соответствующей следующей командой ']' (с учётом вложенности);
 *	] - если значение под датчиком не равно 0, в качестве следующей команды следует воспринимать
 *  	не следующую по порядку, а идущую за соответствующей предыдущей командой '[' (с учётом вложенности);
 *      (комбинация [] имитирует цикл)
 *  пробел - пустая команда
 *
 * Изначально все ячейки заполнены значением 0 и датчик стоит на ячейке с номером N/2 (округлять вниз)
 *
 * После выполнения limit команд или всех команд из commands следует прекратить выполнение последовательности команд.
 * Учитываются все команды, в том числе несостоявшиеся переходы ("[" при значении под датчиком не равном 0 и "]" при
 * значении под датчиком равном 0) и пробелы.
 *
 * Вернуть список размера cells, содержащий элементы ячеек устройства после завершения выполнения последовательности.
 * Например, для 10 ячеек и командной строки +>+>+>+>+ результат должен быть 0,0,0,0,0,1,1,1,1,1
 *
 * Все прочие символы следует считать ошибочными и формировать исключение IllegalArgumentException.
 * То же исключение формируется, если у символов [ ] не оказывается пары.
 * Выход за границу конвейера также следует считать ошибкой и формировать исключение IllegalStateException.
 * Считать, что ошибочные символы и непарные скобки являются более приоритетной ошибкой чем выход за границу ленты,
 * то есть если в программе присутствует некорректный символ или непарная скобка, то должно быть выброшено
 * IllegalArgumentException.
 * IllegalArgumentException должен бросаться даже если ошибочная команда не была достигнута в ходе выполнения.
 *
 */
fun computeDeviceCells(cells: Int, commands: String, limit: Int): List<Int> = TODO()


/**
 *Короче задается таблица с процентами на налог:
 * map = [Банковское дело = 4,Горнодобывающая промышленность = 15,Образование = 10]
 * также задается строка с предприятиями в формате
 * Название - отрасль - прибыль
 *
 * Поликек - Образование - 10000
 * ООО Горняк - Горнодобывающая промышленность - 1000000000
 * Бебра - Торговля - 5000
 *
 * Для неверного формата вывести ошибку NumberFormatException()
 *
 * Для каждого предприятия найти налог, если он не задан в таблице - берем стандартный 13%
 * Вывести список предприятий отсортированный по налогу...
 * название функции и тесты придумать самостоятельно
 * Collection<Any> поменять на тот тип который удобнее или че то такое
 *
 * fun myFun(table: Map<<String>,<Int>>, text: String): Collection<Any> =
 */

fun myFun(table: Map<String, Int>, text: String): List<String> {
    val map = mutableMapOf<String, Double>()
    val list = text.split("\n")
    for (i in list) {
        if (!i.matches(Regex("""[ёЁА-я ]+ - [ёЁА-я ]+ - \d+"""))) throw IllegalArgumentException()
        val (name, type, profit) = i.split(" - ")
        map[name] = table.getOrDefault(type, 13).toDouble() / 100.0 * profit.toDouble()
        //if (type in table.keys) map[name] = table[type]!!.toDouble() * profit.toDouble() / 100.0
        //else map[name] = 0.13 * profit.toDouble()
    }
    return map.keys.sortedBy { map[it] }
}


/**
 * На вход функции подается список строк в формате
 * “Иванов Петр: улица Ленина, 41, кв. 2”
 * Каждая строка начинается с фамилии и имени человека (разделенных
 * одним пробелом), далее через запятую пробел(ы) следует адрес:
 * название улицы (может состоять из нескольких слов через
 * один пробел),
 * номер дома (целое число) и номер квартиры
 * (с префиксом “кв.”; целое число).
 *
 * На вход также подается имя человека.
 *
 * Вернуть список людей, которые являются соседями
 * указанного человека
 * (соседями считаются люди, которые живут в одном доме).
 *
 * Имя функции и тип результата функции предложить самостоятельно;
 * в задании указан тип Collection<Any>,
 * то есть коллекция объектов произвольного типа,
 * можно (и нужно) изменить как вид коллекции,
 * так и тип её элементов.
 *
 * При нарушении формата входной строки,
 * бросить IllegalArgumentException
 *
 * Кроме функции, следует написать тесты,
 * подтверждающие её работоспособность.
 */
fun wagner(addresses: List<String>, person: String): List<String> {
    val answer = mutableMapOf<String, MutableList<String>>()
    var personAddress = ""
    for (i in addresses) {
        if (!i.matches(Regex("""[ёЁA-я\s]+: улица [ёЁA-я\s]+, \d+, кв\. \d+"""))) throw IllegalArgumentException()
        val name = i.split(":")[0]
        val address = i.split(":")[1].split(", кв.")[0]
        if (name == person) {
            personAddress = address
        } else {
            if (address in answer.keys) {
                answer[address]!!.add(name)
            } else {
                answer[address] = mutableListOf(name)
            }
        }
    }
    return answer[personAddress]!!
}


fun marry(marks: List<String>, cost: Int): Int {
    var answer = 2
    val count =
        listOf("ноль", "один", "два", "три", "четыре", "пять", "шесть", "семь", "восемь", "девять", "десять")
    for (i in marks) {
        if (!i.matches(Regex("""[ёА-я]+\+[ёА-я]+"""))) throw IllegalArgumentException()
        var counts = i.split("+")[1]
        if (!count.contains(counts)) throw IllegalArgumentException()
        answer += 1 + count.indexOf(counts)
    }
    return answer * cost
}


fun telephones(phones: List<String>, prefix: Int): List<String> {
    val answer = mutableListOf<String>()
    for (i in phones) {
        if (!i.matches(Regex("""\d+ [ёЁА-я]+"""))) throw java.lang.IllegalArgumentException()
        val (number, name) = i.split(" ")
        if (number.split(prefix.toString())[0] == "") answer += name
    }
    return answer
}

fun exams(examResults: List<String>, humSubjects: List<String>): List<String> {
    val answer = mutableListOf<String>()
    for (i in examResults) {
        var flag = false
        if (!i.matches(Regex("""[ёА-я]+ [ёА-я]+ - ([ёА-я]+ \d, )*[ёА-я]+ \d"""))) throw IllegalArgumentException()
        val (name, a) = i.split(" - ")
        val subjects = a.split(", ")
        var counter = 0
        for (j in subjects) {
            if (j.last() == '3') {
                if (j.split(" ")[0] in humSubjects) counter += 1
                else {
                    flag = true
                    break
                }
                if (counter > 1) {
                    flag = true
                    break
                }
            }
            if (j.last() == '2') {
                flag = true
                break
            }
        }
        if (!flag) {
            answer.add(name)
        }
    }
    return answer
}

fun telephone(names: List<String>, home: String): List<String> {
    val map = mapOf(
        '2' to "abc",
        '3' to "def",
        '4' to "ghi",
        '5' to "jkl",
        '6' to "mno",
        '7' to "pqrs",
        '8' to "tuv",
        '9' to "wxyz",
    )
    var names = names.sortedBy { it.length == home.length }
    var m = String()
    var answer = mutableListOf<String>()
    for (i in home.indices) {
        m = map[home[i]]!!
        println(m)
        for (j in names) {
            if (j[i].lowercase() in m) {
                answer.add(j)
            }
        }
        if (answer.isEmpty()) {
            break
        }
        names = answer.toMutableList()
        answer = mutableListOf()
    }
    return names
}

fun nalog(taxes: String, money: Int): Int {
    val list = taxes.split(", ")
    val listSum = mutableListOf(0)
    val listPercent = mutableListOf<Int>()
    var money = money
    var answer = 0
    for (i in list.indices) {
        if (!list[i].matches(Regex("""(\d+ у.е.)*(else)* - \d+%"""))) throw IllegalArgumentException()
        if (list[i].split(" ")[0] == "else") {
            listPercent.add(list[i].split(" - ")[1].split("%")[0].toInt())
            listSum.add(money)
        } else {
            listSum.add(list[i].split(" ")[0].toInt())
            listPercent.add(list[i].split(" - ")[1].split("%")[0].toInt())
        }
    }
    for (i in listSum.indices - (list.size)) {
        if (money > i) {
            answer += (listSum[i + 1] - listSum[i]) * listPercent[i] / 100
            money -= (listSum[i + 1] - listSum[i])
        }
    }
    return answer
}

/**
 * Во входном списке `movers` перечислены компании, которые занимаются
 * перевозкой домашних животных. Каждая компания описана в следующем
 * формате:
 *
 * имя компании: вид животного - стоимость,
 *
 * Пример входных данных:
 * SuperCats: кот - 100000,
 * FastAndCheap: кот - 25000, собака - 30000, шиншилла - 5000,
 * Lux: кот - 1000000, собака - 1000000, крыса - 1000000,
 * корова - 1000000, бегемот - 1000000,
 *
 * Также на вход вам подается еще два параметра: список домашних
 * животных, которых необходимо перевезти, `pets` и максимальная
 * сумма, которую хозяева готовы потратить на перевозку, `limit`.
 *
 * Вам необходимо имена всех компаний, которые могут перевезти
 * указанных животных в пределах заданной суммы.
 *
 * Например, для указанного списка компаний и параметров
 * - `pets=["кот", "собака"] money = 20000000` вернуть ["Lux", "FastAndCheap"]
 * - `pet=["кот"] money = 25000` вернуть ["FastAndCheap"]
 * - `pet=["бегемот"] money = 500000` вернуть []
 *
 * При нарушении формата входных данных следует выбросить
 * IllegalArgumentException.
 *
 * Имя функции и тип результата функции предложить самостоятельно;
 * в задании указан тип Collection<Any>, то есть коллекция объектов
 * произвольного типа, можно (и нужно) изменить как вид коллекции,
 * так и тип её элементов.
 *
 * Кроме функции, следует написать тесты,
 * подтверждающие её работоспособность.
 */

fun transportingAnimals(movers: List<String>, pets: List<String>, limit: Int): Collection<Any> {
    val result = mutableListOf<String>()
    for (mover in movers) {
        if (!Regex("""[а-яА-ЯA-Za-zЁ]+:\s([а-яА-ЯA-Za-zЁ]+\s-\s\d+,*\s*)+""").matches(mover)) throw IllegalArgumentException("Ошибка ввода")
        var remainMoney = limit
        val petMap = mutableMapOf<String, Int>()
        val str = mover.split(": ")
        val companyName = str[0]
        val petCells = str[1].split(",")
        petCells.forEach { elem ->
            val petInfo = elem.split(" - ")
            petMap[petInfo[0].trim()] = petInfo[1].toInt()
        }
        if (pets.any { it !in petMap}) continue
        pets.forEach { remainMoney -= petMap[it]!! }
        if (remainMoney < 0) continue
        result.add(companyName)
    }
    return result.toList()
}

