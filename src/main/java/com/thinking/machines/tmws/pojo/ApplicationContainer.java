package com.thinking.machines.tmws.pojo;
import javax.servlet.*;
import javax.servlet.http.*;
public class ApplicationContainer
{
private ServletContext servletContext;
ApplicationContainer(ServletContext servletContext)
{
this.servletContext=servletContext;
}	
}
