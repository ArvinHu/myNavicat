package com.milla.navicat.exception;

import com.alibaba.fastjson.JSONException;
import com.fasterxml.jackson.core.JsonParseException;
import com.milla.navicat.comm.PropertiesReaderUtil;
import com.milla.navicat.comm.ResponseData;
import com.milla.navicat.constant.Constant;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.binding.BindingException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;

/**
 * @Package: com.milla.navicat.exception
 * @Description: <异常拦截类>
 * @Author: MILLA
 * @CreateDate: 2019/8/15 18:39
 * @UpdateUser: MILLA
 * @UpdateDate: 2019/8/15 18:39
 * @UpdateRemark: <>
 * @Version: 1.0
 */
@Slf4j
@RestControllerAdvice
public class RestfulExceptionHandler {

    private ResponseData responseData(String code, Exception e) {
        log.error("异常代码:{},异常描述:{},异常堆栈:", code, PropertiesReaderUtil.getProperty(code), e);
        return ResponseData.error(code);
    }

    private ResponseData<String> responseData(String code, String message, Exception e) {
        log.error("异常代码:{},异常描述:{},异常堆栈:", code, message, e);
        return ResponseData.error(code, e.getCause().getMessage());
    }

    //运行时异常
    @ExceptionHandler(Exception.class)
    public ResponseData runtimeExceptionHandler(Exception e) {
        return responseData(Constant.EX_RUN_TIME_EXCEPTION, e);
    }

    //处理SQLSyntaxErrorException
    @ExceptionHandler(SQLException.class)
    public ResponseData<String> sqlException(SQLException e) {
        return responseData(Constant.EX_RUN_TIME_EXCEPTION, e.getMessage(), e);
    }

    //处理BadSqlGrammarException
    @ExceptionHandler(BadSqlGrammarException.class)
    public ResponseData<String> badSqlGrammarException(BadSqlGrammarException e) {
        return responseData(Constant.EX_RUN_TIME_EXCEPTION, e.getMessage(), e);
    }

    //处理CustomerMessageException
    @ExceptionHandler(CustomMessageException.class)
    public ResponseData<String> customerMessageException(CustomMessageException e) {
        return responseData(Constant.EX_RUN_TIME_EXCEPTION, e.getMessage(), e);
    }

    //处理BindingException
    @ExceptionHandler(BindingException.class)
    public ResponseData<String> bindingException(BindingException e) {
        return responseData(Constant.EX_RUN_TIME_EXCEPTION, "数据源未绑定", e);
    }

    //处理AccountException
    @ExceptionHandler(AccountException.class)
    public ResponseData<String> accountException(AccountException e) {
        return responseData(e.getMessage(), e);
    }

    //DataSourceException
    @ExceptionHandler(DataSourceException.class)
    public ResponseData<String> dataSourceException(DataSourceException e) {
        return responseData(Constant.EX_RUN_TIME_EXCEPTION, e.getMessage(), e);
    }

    //---------------------------------------jdk/spring自带的异常----------------------------------
    //处理IllegalArgumentException
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseData<String> illegalArgumentException(IllegalArgumentException e) {
        return responseData(Constant.EX_RUN_TIME_EXCEPTION, e.getMessage(), e);
    }

    //空指针异常
    @ResponseStatus
    @ExceptionHandler(NullPointerException.class)
    public ResponseData nullPointerExceptionHandler(NullPointerException e) {
        return responseData(Constant.EX_NULL_POINTER_EXCEPTION, e);
    }

    //类型转换异常
    @ExceptionHandler(ClassCastException.class)
    public ResponseData classCastExceptionHandler(ClassCastException e) {
        return responseData(Constant.EX_CLASS_CAST_EXCEPTION, e);
    }

    //IO异常
    @ExceptionHandler(IOException.class)
    public ResponseData iOExceptionHandler(IOException e) {
        return responseData(Constant.EX_IO_EXCEPTION, e);
    }

    //未知方法异常
    @ExceptionHandler(NoSuchMethodException.class)
    public ResponseData noSuchMethodExceptionHandler(NoSuchMethodException e) {
        return responseData(Constant.EX_NO_SUCH_METHOD_EXCEPTION, e);
    }

    //数组越界异常
    @ExceptionHandler(IndexOutOfBoundsException.class)
    public ResponseData indexOutOfBoundsExceptionHandler(IndexOutOfBoundsException e) {
        return responseData(Constant.EX_INDEX_OUT_OF_BOUNDS_EXCEPTION, e);
    }

    //请求body缺失异常
    @ExceptionHandler({HttpMessageNotReadableException.class})
    public ResponseData requestNotReadable(HttpMessageNotReadableException e) {
        return responseData(Constant.EX_HTTP_MESSAGE_NOT_READABLE_EXCEPTION, e);
    }

    //类型匹配异常
    @ExceptionHandler({TypeMismatchException.class})
    public ResponseData requestTypeMismatch(TypeMismatchException e) {
        return responseData(Constant.EX_HTTP_MESSAGE_NOT_READABLE_EXCEPTION, e);
    }

    //方法不支持异常
    @ExceptionHandler({HttpRequestMethodNotSupportedException.class})
    public ResponseData methodNotSupported(HttpRequestMethodNotSupportedException e) {
        return responseData(Constant.EX_HTTP_REQUEST_METHOD_NOT_SUPPORTED_EXCEPTION, e);
    }


    //请求头不支持异常
    @ExceptionHandler({HttpMediaTypeNotSupportedException.class})
    public ResponseData mediaTypeNotAcceptable(HttpMediaTypeNotSupportedException e) {
        return responseData(Constant.EX_HTTP_MEDIA_TYPE_NOT_ACCEPTABLE_EXCEPTION, e);
    }

    //参数解析异常
    @ExceptionHandler(JSONException.class)
    public ResponseData runtimeExceptionHandler(JSONException e) {
        return responseData(Constant.PARAMS_PARSE_EXCEPTION, e);
    }

    //参数解析异常
    @ExceptionHandler(JsonParseException.class)
    public ResponseData runtimeExceptionHandler(JsonParseException e) {
        return responseData(Constant.PARAMS_PARSE_EXCEPTION, e);
    }

    //请求参数缺失异常
    @ExceptionHandler({MissingServletRequestParameterException.class})
    public ResponseData requestMissingServletRequest(MissingServletRequestParameterException e) {
        return responseData(Constant.EX_MISSING_SERVLET_REQUEST_PARAMETER_EXCEPTION, e);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseData exceptionHandler(MethodArgumentNotValidException e) {
        return responseData(Constant.PARAMS_IS_NULL, e);
    }
}
