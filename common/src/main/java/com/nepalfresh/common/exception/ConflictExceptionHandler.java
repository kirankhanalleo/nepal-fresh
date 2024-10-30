//package com.nepalfresh.common.exception;
//
//import com.nepalfresh.common.constant.ServerResponseCodeConstant;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//
//@ControllerAdvice
//public class ConflictExceptionHandler {
//    @ExceptionHandler(value = {ConflictRequestException.class})
//    public ResponseEntity<Object> handleConflictException(ConflictRequestException e){
//        HttpStatus httpStatus = HttpStatus.CONFLICT;
//        String code = ServerResponseCodeConstant.VALIDATION_EXCEPTION;
//        ConflictException conflictException = new ConflictException(
//                e.getMessage(),
//                httpStatus,
//                code
//        );
//        return new ResponseEntity<>(conflictException, httpStatus);
//    }
//}
