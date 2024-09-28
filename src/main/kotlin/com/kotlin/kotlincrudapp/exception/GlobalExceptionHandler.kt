package com.kotlin.kotlincrudapp.exception

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(value = [ItemNotFoundException::class])
    fun handleNotFoundException(ex: ItemNotFoundException): ResponseEntity<String> {
        return ResponseEntity.notFound().build()
    }

    @ExceptionHandler(value = [Exception::class])
    fun handleGenericException(ex: Exception): ResponseEntity<String> {
        return ResponseEntity.internalServerError().body("An unexpected error occurred.")
    }
}