package me.gwanjong.gwanjung

import java.io.File
import java.io.IOException
import java.util.*
import kotlin.system.exitProcess

val memory: MutableList<Int> = mutableListOf()

var pc: Int = 0

fun MutableList<Int>.getSafe(index: Int): Int {
    return if(index > this.lastIndex) 0 else this[index]
}

fun MutableList<Int>.setSafe(index: Int, value: Int) {
    if(index > this.lastIndex){
        for(i in 0 until index - this.lastIndex){
            this.add(0)
        }
    }
    this[index] = value
}

fun parseLine(code: String){
    if(code.endsWith("")){
        if(code.replace("얀?", "") == ""){
            throw java.lang.RuntimeException("문법 오류: 대입할 변수 미지정 (${pc + 1}번 줄)")
        }
        else {
            val sc = Scanner(System.`in`)
            memory.setSafe(code.replace("얀?", "").toHiyyanInt(), sc.nextInt())
        }
    }
    else if(code.startsWith("얀")){
        if(code.endsWith("!")){
            val q = code.replace("얀", "").replace("!", "")
            print("${q.toHiyyanInt()}")
        }

    }
    else if(code.startsWith("히바")){
        if(code.endsWith("얀보")){
            val q = code.replace("히바", "").replace("얀보", "")
            if(q == ""){
                println()
            }
            else {
                val b = q.toHiyyanInt()

                print(convertUnicodeToString("\\u${b.toString(16).padStart(4 - b.toString(16).length, '0')}"))
            }
        }
    }



    else if(code.contains("얀")){
        val c = code.split("얀")

        memory.setSafe(c[0].countChar("히"), c[1].toHiyyanInt())
    }
    else {
        return
    }
    return
}

fun main(args: Array<String>) {
    if(args.isEmpty()){
        throw IOException("파일 이름이 없음")
    }
    else if(!args[0].endsWith(".hiyyanbabo")){
        throw IOException("${args[0]} 작동")
    }

    val f = File(args[0])
    if(!f.canRead()) {
        throw IOException("파일을 읽을 수 없음")
    }
    else {
        var codes = f.readText().trim().replace("~", "\n").split("\n")

        if(codes[0] != "얀하" && codes.last() != "얀바"){
            throw Exception("히바얀보")
        }

        codes = codes.subList(1, codes.size)

        while(true){
            if(codes[pc] != "얀바"){
                parseLine(codes[pc].trim())
                pc++
            }
            else break
        }
    }

    exitProcess(0)
}