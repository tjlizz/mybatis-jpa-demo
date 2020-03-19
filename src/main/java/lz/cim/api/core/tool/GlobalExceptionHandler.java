package lz.cim.api.core.tool;

import jdk.internal.util.xml.PropertiesDefaultHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.logging.Logger;

import static org.springframework.http.HttpStatus.NOT_EXTENDED;

/**
 * @author ：lzz
 * @date ：Created in 2020/3/19 10:33
 * @description：全局异常处理
 * @modified By：
 * @version: 1$
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    //声明要捕获的异常
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseEntity<?> defultExcepitonHandler(HttpServletRequest request, Exception e) {

        log.error(ErrorTool.getErrerInfo(e));
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
    }
}

 