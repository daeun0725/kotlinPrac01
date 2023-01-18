package com.example.demokot.domain.dto.response

import org.springframework.http.ResponseEntity

class MyResponse<T>(
    val response: T
) {
    companion object{
        fun <T> getResponse(data: T): ResponseEntity<MyResponse<T>> {
            return ResponseEntity.ok(MyResponse(data))
        }
    }
    
}