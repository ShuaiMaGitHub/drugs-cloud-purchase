package com.ms.common.exception;

import com.ms.common.enums.ExceptionEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author Shuai.Ma
 * @date 2022/3/20 15:26
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class DrugsExecption extends RuntimeException{
        private ExceptionEnum exceptionEnum;

}
