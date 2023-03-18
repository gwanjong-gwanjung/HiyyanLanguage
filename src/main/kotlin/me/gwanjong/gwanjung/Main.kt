package me.gwanjong.gwanjung

import java.io.File
import java.io.IOException
import java.util.*
import kotlin.system.exitProcess

fun main(args: Array<String>) {

    print("히바얀보 위치를 알려주세요\n")
    val sc = Scanner(System.`in`)
    val Name = sc.nextLine()
    print("위치를 지정했습니다 : $Name\n")
    print("===============출력=================\n")
    val Filename = Name

    if(Filename.isEmpty()){
        throw IOException("파일 이름이 없음")
    }
    else if(!Filename.endsWith(".hiyyanbabo")){
        throw IOException("${args[0]} 작동")
    }



    val f = File(Filename)
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
    if(code.startsWith("히바얀보")) {
        print("히바얀보")
    }

    if(code.endsWith("바보?")){
        if(code.replace("바보?", "") == ""){
            throw java.lang.RuntimeException("문법 오류: 대입할 변수 미지정 (${pc + 1}번 줄)")
        }
        else {
            val sc = Scanner(System.`in`)
            memory.setSafe(code.replace("바보?", "").toUmmInt(), sc.nextInt())
        }
    }
    else if(code.startsWith("바보")){
        if(code.endsWith("!")){
            val q = code.replace("바보", "").replace("!", "")
            print("${q.toUmmInt()}")
        }
        else if(code.endsWith("히바얀보")){
            val q = code.replace("바보", "").replace("히바얀보", "")
            if(q == ""){
                println()
            }
            else {
                val b = q.toUmmInt()

                print(convertUnicodeToString("\\u${b.toString(16).padStart(4 - b.toString(16).length, '0')}"))
            }
        }
    }
    else if(code.contains("얀")){
        val c = code.split("얀")

        memory.setSafe(c[0].countChar("히"), c[1].toUmmInt())
    }

    else {
        return
    }
    return
}


