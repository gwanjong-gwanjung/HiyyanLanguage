package me.gwanjong.gwanjung

import java.util.*

fun String.countChar(c: String): Int {
    var ret = 0
    this.toCharArray().forEach {
        if(it.toString() == c){
            ret++
        }
    }
    return ret
}

fun String.toHiyyanInt(): Int {
    return toHiyyanInt(this)
}

fun toHiyyanInt(a: String): Int {
    var n = 0
    if (a.contains(" ")) {
        val b = a.split(" ").map {
            toUmmmIntInternal(it)
        }
        n = b[0]
        b.subList(1, b.size).forEach {
            n *= it
        }
    }
    else {
        n = toUmmmIntInternal(a)
    }
    return n
}

fun toUmmmIntInternal(a: String): Int {
    var s = a
    var n = 0

    if(s.contains("얀?")) {
        val answer = Scanner(System.`in`).nextInt()
        s = s.replace("얀?", ".".repeat(answer))
    }
    if(s.contains("히")) n += memory.getSafe(s.countChar("히") - 1)
    if(s.contains(".")) n += s.countChar(".")
    if(s.contains(",")) n -= s.countChar(",")
    return n
}

fun String.toVarIndex(isLoad: Boolean = true): Int {
    if(this.split(" ").size > 1){
        val s = this.split(" ")

        val b = mutableListOf<Int>()
        s.forEach { b.add(it.countChar("히") - 1) }

        var r = 1

        b.forEach {
            r *= it
        }

        return r + if(isLoad) -1 else 0
    }
    else {
        return this.countChar("히") + if(isLoad) -1 else 0
    }
}

fun String.isVariable(): Boolean = this.startsWith("히") || this.endsWith("얀")

fun convertUnicodeToString(unicodeString: String): String {
    var str: String = unicodeString.split(" ")[0]
    str = str.replace("\\", "")
    val arr = str.split("u").toTypedArray()
    var text = ""
    for (i in 1 until arr.size) {
        val hexVal = arr[i].toInt(16)
        text += hexVal.toChar()
    }

    return text
}