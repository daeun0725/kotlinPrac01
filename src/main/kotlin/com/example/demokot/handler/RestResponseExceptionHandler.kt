package com.example.demokot.handler
import com.example.demokot.domain.dto.request.ErrorDto
import com.example.demokot.exception.DuplicateMemberException
import com.example.demokot.exception.NotFoundMemberException
import org.springframework.security.access.AccessDeniedException
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

@ControllerAdvice
class RestResponseExceptionHandler : ResponseEntityExceptionHandler() {
    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(value = [DuplicateMemberException::class])
    @ResponseBody
    protected fun badRequest(ex: RuntimeException, request: WebRequest?): ErrorDto {
        return ErrorDto(HttpStatus.CONFLICT.value(), ex.message ?: "")
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(value = [NotFoundMemberException::class, AccessDeniedException::class])
    @ResponseBody
    protected fun forbidden(ex: RuntimeException, request: WebRequest?): ErrorDto {
        return ErrorDto(HttpStatus.FORBIDDEN.value(), ex.message ?: "")
    }
}