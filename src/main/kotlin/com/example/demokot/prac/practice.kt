package com.example.demokot.prac

import io.swagger.v3.oas.models.info.Contact

const val num = 20 // topLevel 인데 자바에서는 final
private val Order.commaDelimitedItemNames: String
    get() = item.map {it.name}.joinToString()

fun main0(){
    val order = Order
}


fun main1(){
    var i     : Int    =  10
    var name  : String = "sophie"
    var point : Double =  3.3

    var p = 10
    var o = 20L

    o = p.toLong()

}

//자바에서는 모든 타입이 nullable이지만
//코틀린에서는 nullable(null값을 허용할 수 있다), non-nullable(null값을 할당할 수 없다.) 타입으로 나뉜다.
fun main2(){

    var myLover: String? = null

    if (myLover != null){
        println("I love $myLover")
    }else{
     println("I love None")
    }
}



fun main3(){
    val testList = listOf("Jeff", "Burger", "Sophie")
    val newList1 = testList.map{
        "$it"
    }
    println(newList1) // [Jeff! Burger! Sophie!]

    val newList2 = testList.flatMap {
        "$it".toList()
    }
    println(newList2) // [Jeff,!, Burger,!, Sophie,!]
}

fun main4(){
    val contact = Contact( 1, "euncd61@naver.com")
    _secondFun(contact)
    println(contact.email)
}

fun _secondFun(contact: Contact){
    contact.email = "blac2013@gmaill.com"
}
class Contact(val id:Int, val email: String){}




fun main5(){
    val phone = Phone(2, "010-7742-6564")
    extensionMain(phone)
    println(phone.number)
}
fun extensionMain(phone: Phone){
    phone.number = "010-1212-3434"
}
class Phone(val id: Int, var number: String){}



fun main6(){
    val personInfo = PersonInfo(1, "sophie", 20000725 )
    extensionMain1(personInfo)
    println(personInfo.name)
    println(personInfo.birth)
}

fun extensionMain1(personInfo: PersonInfo) {
    personInfo.name = "burger"
    personInfo.birth = 19950207

}

class PersonInfo(val id: Int, var name: String, var birth: Int){}

//제네릭
fun main7(){
    println(_isSum(1,2))
    println(_fSum(1.0f, 2.0f))
    println(_gSum<Int>(1,2))
    println(_gSum(1.0f, 2.0f))
    println(_gSum(1.0, 2.0))
}

fun<T: Number> _gSum(x: T, y: T): T{
    return (x.toDouble() + y.toDouble()) as T
}

fun _fSum(x: Float, y: Float): Float {
    return (x+y)
}

fun _isSum(x: Int, y: Int): Int{
    return (x+y)
}



class practice(

) {
}