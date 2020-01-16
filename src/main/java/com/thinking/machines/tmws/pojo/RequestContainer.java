package com.thinking.machines.tmws.pojo;
import javax.servlet.http.*;
import javax.servlet.*;
import java.util.*;
public class RequestContainer
{
private HttpServletRequest request;
RequestContainer(HttpServletRequest request)
{
this.request=request;
}
}

