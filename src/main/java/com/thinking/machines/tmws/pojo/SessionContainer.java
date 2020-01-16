package com.thinking.machines.tmws.pojo;
import javax.servlet.http.*;
import javax.servlet.*;
import java.util.*;
public class SessionContainer extends HttpServlet
{
enum SessionTime{HOURS,MINUTES,SECONDS}
private HttpSessionContext session;
private HttpSession httpSession;
SessionContainer(HttpSession session)
{
this.httpSession=session;
}
/*public static HttpSessionContext getSessionContext()
{
return this.session;
}
*/
public void endSession()
{
httpSession.invalidate();
}
public void setSessionTimeout()
{
}
public long getLastOnlineTime()
{
return httpSession.getLastAccessedTime();
}
public void setSessionTimeout(int time,SessionTime sessionTime)
{
if(sessionTime==sessionTime.SECONDS)
{
httpSession.setMaxInactiveInterval(time);
}
if(sessionTime==sessionTime.MINUTES)
{
httpSession.setMaxInactiveInterval(time*60);
}
if(sessionTime==sessionTime.HOURS)
{
httpSession.setMaxInactiveInterval(time*60*60);
}
}
public String getId()
{
return httpSession.getId();
}
public ServletContext getServletAttribute()
{
return httpSession.getServletContext();
}
public void setAttribute(String name,Object object)
{
httpSession.setAttribute(name,object);
}
public Object getAttribute(String g)
{
return httpSession.getAttribute(g);
}
public void removeAttribute(String attributeName)
{
httpSession.removeAttribute(attributeName);
}
public void removeAllAttributes()
{

Enumeration<String> attributes=httpSession.getAttributeNames();
while(attributes.hasMoreElements())
{
httpSession.removeAttribute(attributes.nextElement());
}
}
}

