package lesson6.task1

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Test

class Tests {
    @Test
    @Tag("Example")
    fun timeStrToSeconds() {
        assertEquals(36000, timeStrToSeconds("10:00:00"))
        assertEquals(41685, timeStrToSeconds("11:34:45"))
        assertEquals(86399, timeStrToSeconds("23:59:59"))
    }

    @Test
    @Tag("Example")
    fun twoDigitStr() {
        assertEquals("00", twoDigitStr(0))
        assertEquals("09", twoDigitStr(9))
        assertEquals("10", twoDigitStr(10))
        assertEquals("99", twoDigitStr(99))
    }

    @Test
    @Tag("Example")
    fun timeSecondsToStr() {
        assertEquals("10:00:00", timeSecondsToStr(36000))
        assertEquals("11:34:45", timeSecondsToStr(41685))
        assertEquals("23:59:59", timeSecondsToStr(86399))
    }

    @Test
    @Tag("4")
    fun dateStrToDigit() {
        assertEquals("15.07.2016", dateStrToDigit("15 июля 2016"))
        assertEquals("", dateStrToDigit("3 мартобря 1918"))
        assertEquals("18.11.2018", dateStrToDigit("18 ноября 2018"))
        assertEquals("", dateStrToDigit("23"))
        assertEquals("03.04.2011", dateStrToDigit("3 апреля 2011"))
        assertEquals("", dateStrToDigit("32 сентября 2011"))
        assertEquals("", dateStrToDigit("29 февраля 1993"))
    }

    @Test
    @Tag("4")
    fun dateDigitToStr() {
        assertEquals("15 июля 2016", dateDigitToStr("15.07.2016"))
        assertEquals("", dateDigitToStr("01.02.20.19"))
        assertEquals("", dateDigitToStr("28.00.2000"))
        assertEquals("3 апреля 2011", dateDigitToStr("03.04.2011"))
        assertEquals("", dateDigitToStr("ab.cd.ef"))
        assertEquals("", dateDigitToStr("32.09.2011"))
        assertEquals("", dateDigitToStr("29.02.1993"))
    }

    @Test
    @Tag("4")
    fun flattenPhoneNumber() {
        assertEquals("+79211234567", flattenPhoneNumber("+7 (921) 123-45-67"))
        assertEquals("123456798", flattenPhoneNumber("12 --  34- 5 -- 67 -98"))
        assertEquals("+12345", flattenPhoneNumber("+12 (3) 4-5"))
        assertEquals("", flattenPhoneNumber("+12 () 4-5"))
        assertEquals("+425667", flattenPhoneNumber("+42 56 -- 67"))
        assertEquals("+42566789", flattenPhoneNumber("+42(56 -- 67)89"))
        assertEquals("", flattenPhoneNumber("ab-123"))
        assertEquals("", flattenPhoneNumber("134_+874"))
    }

    @Test
    @Tag("5")
    fun bestLongJump() {
        assertEquals(717, bestLongJump("706 % - 717 - 703"))
        assertEquals(-1, bestLongJump("% - - % -"))
        assertEquals(754, bestLongJump("700 717 707 % 754"))
        assertEquals(-1, bestLongJump("700 + 700"))

    }

    @Test
    @Tag("6")
    fun bestHighJump() {
        assertEquals(226, bestHighJump("226 +"))
        assertEquals(-1, bestHighJump("???"))
        assertEquals(230, bestHighJump("220 + 224 %+ 228 %- 230 + 232 %%- 234 %"))
    }

    @Test
    @Tag("6")
    fun plusMinus() {
        assertEquals(0, plusMinus("0"))
        assertEquals(4, plusMinus("2 + 2"))
        assertEquals(6, plusMinus("2 + 31 - 40 + 13"))
        assertEquals(-1, plusMinus("0 - 1"))
        assertThrows(IllegalArgumentException::class.java) { plusMinus("+2") }
        assertThrows(IllegalArgumentException::class.java) { plusMinus("+ 4") }
        assertThrows(IllegalArgumentException::class.java) { plusMinus("4 - -2") }
        assertThrows(IllegalArgumentException::class.java) { plusMinus("44 - - 12") }
        assertThrows(IllegalArgumentException::class.java) { plusMinus("4 - + 12") }
    }

    @Test
    @Tag("6")
    fun firstDuplicateIndex() {
        assertEquals(-1, firstDuplicateIndex("Привет"))
        assertEquals(9, firstDuplicateIndex("Он пошёл в в школу"))
        assertEquals(40, firstDuplicateIndex("Яблоко упало на ветку с ветки оно упало на на землю"))
        assertEquals(9, firstDuplicateIndex("Мы пошли прямо Прямо располагался магазин"))
    }

    @Test
    @Tag("6")
    fun mostExpensive() {
        assertEquals("", mostExpensive(""))
        assertEquals("Курица", mostExpensive("Хлеб 39.9; Молоко 62.5; Курица 184.0; Конфеты 89.9"))
        assertEquals("Вино", mostExpensive("Вино 255.0"))
    }

    @Test
    @Tag("6")
    fun fromRoman() {
        assertEquals(1, fromRoman("I"))
        assertEquals(3000, fromRoman("MMM"))
        assertEquals(1978, fromRoman("MCMLXXVIII"))
        assertEquals(694, fromRoman("DCXCIV"))
        assertEquals(49, fromRoman("XLIX"))
        assertEquals(-1, fromRoman("Z"))
    }

    @Test
    @Tag("7")
    fun computeDeviceCells() {
        assertEquals(listOf(0, 0, 0, 0, 0, 1, 1, 1, 1, 1), computeDeviceCells(10, "+>+>+>+>+", 10000))
        assertEquals(listOf(-1, -1, -1, -1, -1, 0, 0, 0, 0, 0), computeDeviceCells(10, "<-<-<-<-<-", 10000))
        assertEquals(listOf(1, 1, 1, 1, 1, 0, 0, 0, 0, 0), computeDeviceCells(10, "- <<<<< +[>+]", 10000))
        assertEquals(
            listOf(0, 8, 7, 6, 5, 4, 3, 2, 1, 0, 0),
            computeDeviceCells(11, "<<<<< + >>>>>>>>>> --[<-] >+[>+] >++[--< <[<] >+[>+] >++]", 10000)
        )

        assertEquals(listOf(0, 0, 0, 0, 0, 1, 1, 0, 0, 0), computeDeviceCells(10, "+>+>+>+>+", 4))
        assertEquals(listOf(0, 0, -1, -1, -1, 0, 0, 0, 0, 0), computeDeviceCells(10, "<-<-<-<-<-", 6))
        assertEquals(listOf(1, 1, 1, 0, 0, -1, 0, 0, 0, 0), computeDeviceCells(10, "- <<<<< +[>+]", 17))
        assertEquals(
            listOf(0, 6, 5, 4, 3, 2, 1, 0, -1, -1, -2),
            computeDeviceCells(11, "<<<<< + >>>>>>>>>> --[<-] >+[>+] >++[--< <[<] >+[>+] >++]", 256)
        )
        assertThrows(IllegalArgumentException::class.java) { computeDeviceCells(10, "===", 3) }
        assertThrows(IllegalArgumentException::class.java) { computeDeviceCells(10, "+>+>[+>", 3) }
        assertThrows(IllegalStateException::class.java) { computeDeviceCells(20, ">>>>>>>>>>>>>", 12) }
    }

    @Test
    fun myFun() {
        assertEquals(
            listOf("Бебра", "Поликек", "ООО Горняк"), myFun(
                mapOf("Банковское дело" to 4, "Горнодобывающая промышленность" to 15, "Образование" to 10),
                """Поликек - Образование - 10000
                |ООО Горняк - Горнодобывающая промышленность - 1000000000
                |Бебра - Торговля - 5000""".trimMargin()
            )
        )
        assertThrows(IllegalArgumentException::class.java) { myFun(mapOf("fff" to 5), "GFFGFGFG") }
    }


    @Test
    fun wagner() {
        assertEquals(
            listOf("Вагнер Артём", "Писькострой Майнкрафтович"), wagner(
                listOf(
                    "Иванов Петр: улица Чехова, 41, кв. 22", "Иванов Пётр: улица Чехова, 42, кв. 22",
                    "Вагнер Артём: улица Чехова, 41, кв. 22", "Писькострой Майнкрафтович: улица Чехова, 41, кв. 32"
                ),
                "Иванов Петр"
            )
        )
        assertThrows(IllegalArgumentException::class.java) { wagner(listOf("fff", "341"), "GFFGFGFG") }
    }


    @Test
    fun marry() {
        assertEquals(4500, marry(listOf("Петр+два", "Паша+восемь", "Лох+ноль"), 300))
        assertThrows(java.lang.IllegalArgumentException::class.java) { marry(listOf("13r", "Аа+одиннадцать"), 1) }
    }

    @Test
    fun telephones() {
        assertEquals(
            listOf("Петров", "Непридумал", "Тоженепридумал"),
            telephones(
                listOf(
                    "460921 Петров",
                    "934104 Иванов",
                    "254245 Васильев",
                    "467832 Непридумал",
                    "467903184 Тоженепридумал"
                ), 46
            )
        )
        assertThrows(java.lang.IllegalArgumentException::class.java) {
            telephones(
                listOf(
                    "460921 Петров",
                    "934104 Иванов",
                    "254245 Васильев",
                    "467832 Непридумал",
                    "f4 Тоженепридумал"
                ), 46
            )
        }
    }

    @Test
    fun exams() {
        assertEquals(
            listOf("Иван Иванов", "Петр Петров", "Вася Васильев"),
            exams(
                listOf(
                    "Иван Иванов - Математика 4, Физика 5, Русский 3",
                    "Петр Петров - Математика 4, Физика 4, Русский 4, Общество 3",
                    "Вася Васильев - Математика 5, Физика 5, Русский 5",
                    "Ермолин Андрей - Математика 2, Физика 2, Русский 2"
                ), listOf("Русский", "Общество")
            )
        )
    }

    @Test
    fun telephone() {
        assertEquals(
            listOf("ABCDEF", "BCADFEG"),
            telephone(
                listOf("ABCDEF", "BCADFEG", "YUYUYU", "FFDFG", "fggf"),
                "2223"
            )
        )
    }

    @Test
    fun nalog() {
        assertEquals(
            1600,
            nalog(
                "2000 у.е. - 5%, 4000 у.е. - 10%, 6000 у.е. - 15%, else - 25%",
                10000
            )
        )
        assertEquals(
            13000,
            nalog(
                "20000 у.е. - 0%, 40000 у.е. - 5%, 60000 у.е. - 10%, else - 25%",
                100000
            )
        )
    }
}