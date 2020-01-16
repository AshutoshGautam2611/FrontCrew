package com.thinking.machines.tmws.pojo;
import javax.servlet.*;
import javax.servlet.http.*;
public class CookieManager implements java.io.Serializable
{
private HttpServletRequest request;
CookieManager(HttpServletRequest request)
{
this.request=request;
}
}
