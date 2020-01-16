package com.thinking.machine.tmws.annotation;
import java.lang.annotation.*;
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE,ElementType.METHOD})
public @interface OnException
{
public String handler();
public boolean isPublic() default true;
}
