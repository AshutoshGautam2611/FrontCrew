package com.thinking.machines.tmws.pojo;
import com.thinking.machines.tmws.annotations.*;
import com.thinking.machines.tmws.pool.*;
import com.thinking.machines.tmws.interfaces.*;
import com.thinking.machines.tmws.model.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.lang.reflect.*;
import java.util.*;
public class ServiceModule implements java.io.Serializable
{
private String path;
private Class serviceClass;
private Method applicationInjectorMethod;
private Method sessionInjectorMethod;
private Method requestInjectorMethod;
private Method cookieManagerInjectorMethod;
private Method contextDirectoryInjectorMethod;
private Field applicationContainerField;
private Field sessionContainerField;
private Field requestContainerField;
private Field cookieManagerField;
private Field contextDirectoryField;
private boolean allowGet;
private boolean allowPost;
private boolean isSecured;
private Guard guard;
private Map<String,Service> services;
private List<AutoWiredField> autoWiredFields;


//Setter Getter
public void setPath(String path)
{
this.path=path;
}
public String getPath()
{
return this.path;
}
public void setServiceClass(Class serviceClass)
{
this.serviceClass=serviceClass;
}
public Class getServiceClass()
{
return this.serviceClass;
}
public void setApplicationInjectorMethod(Method applicationInjectorMethod)
{
this.applicationInjectorMethod=applicationInjectorMethod;
}
public Method getApplicationInjectorMethod()
{
return this.applicationInjectorMethod;
}
public void setSessionInjectorMethod(Method sessionInjectorMethod)
{
this.sessionInjectorMethod=sessionInjectorMethod;
}
public Method getSessionInjectorMethod()
{
return this.sessionInjectorMethod;
}
public void setRequestInjectorMethod(Method requestInjectorMethod)
{
this.requestInjectorMethod=requestInjectorMethod;
}
public Method getRequestInjectorMethod()
{
return this.requestInjectorMethod;
}

public void setCookieManagerctorMethod(Method cookieManagerInjectorMethod)
{
this.cookieManagerInjectorMethod=cookieManagerInjectorMethod;
}
public Method getCookieManagerInjectorMethod()
{
return this.cookieManagerInjectorMethod;
}
public void setContextDirectoryInjectorMethod(Method contextDirectoryInjectorMethod)
{
this.contextDirectoryInjectorMethod=contextDirectoryInjectorMethod;
}
public Method getContextDirectoryInjectorMethod()
{
return this.contextDirectoryInjectorMethod;
}
public void setAllowGet(boolean allowGet)
{
this.allowGet=allowGet;
}
public boolean getAllowGet()
{
return this.allowGet;
}
public void setAllowPost(boolean allowPost)
{
this.allowPost=allowPost;
}
public boolean getAllowPost()
{
return this.allowPost;
}
public void setIsSecured(boolean isSecured)
{
this.isSecured=isSecured;
}
public boolean getIsSecured()
{
return this.isSecured;
}
public void setGuard(Guard guard)
{
this.guard=guard;
}
public Guard getGuard()
{
return this.guard;
}
public void setServices(Map<String,Service> services)
{
this.services=services;
}
public Map<String,Service> getServices()
{
return this.services;
}
public void setAutoWiredField(List<AutoWiredField> autoWiredFields)
{
this.autoWiredFields =autoWiredFields;
}
public List<AutoWiredField> getAutoWiredField()
{
return this.autoWiredFields;
}
public Object getServiceObject(ServletContext servletContext,HttpServletRequest request,Service service)throws Throwable
{
Model model=(Model)servletContext.getAttribute(Model.id);
ServiceController serviceObject;
serviceObject=ServiceObjectPool.get(this.serviceClass);
if(applicationInjectorMethod!=null)applicationInjectorMethod.invoke(serviceObject,new ApplicationContainer(servletContext));
else if(applicationContainerField!=null)applicationContainerField.set(serviceObject,new ApplicationContainer(servletContext));
if(sessionInjectorMethod!=null)sessionInjectorMethod.invoke(serviceObject,new SessionContainer(request.getSession()));
else if(sessionContainerField!=null) sessionContainerField.set(serviceObject,new SessionContainer(request.getSession()));
if(requestInjectorMethod!=null) requestInjectorMethod.invoke(serviceObject,new RequestContainer(request));
else if(requestContainerField!=null) requestContainerField.set(serviceObject,new RequestContainer(request));
if(cookieManagerInjectorMethod!=null) cookieManagerInjectorMethod.invoke(serviceObject, new CookieManager(request));
else if(cookieManagerField!=null) cookieManagerField.set(serviceObject, new CookieManager(request));
if(contextDirectoryInjectorMethod!=null) contextDirectoryInjectorMethod.invoke(serviceObject, model.getContextDirectory());
else if(contextDirectoryField!=null) contextDirectoryField.set(serviceObject,model.getContextDirectory());
for(AutoWiredField awf:autoWiredFields)
{
String name= awf.getName();
Field field= awf.getField();
Object object= null;
if(name.length()>0)
{
String paramValues[]=request.getParameterValues(name);
if(paramValues!=null)parseParamValues(paramValues,field.getType());
if(object==null)object=request.getAttribute(name);
if(object==null)object=request.getSession().getAttribute(name);
if(object==null)object=servletContext.getAttribute(name);
}
else
{
Enumeration en;
if(object==null)
{
en= request.getAttributeNames();
while(en.hasMoreElements())
{
Object value= request.getAttribute((String)en.nextElement());
if(field.getType().isInstance(value))
{
object=value;
break;
}
}
}//
if(object==null)
{
en= request.getSession().getAttributeNames();
while(en.hasMoreElements())
{
Object value= request.getSession().getAttribute((String) en.nextElement());
if(field.getType().isInstance(value))
{
object=value;
break;
}
} 
}//
if(object==null)
{
en= servletContext.getAttributeNames();
while(en.hasMoreElements())
{
Object value= request.getSession().getAttribute((String) en.nextElement());
if(field.getType().isInstance(value))
{
object=value;
break;
}
} 
}//
}//else
field.set(serviceObject,object);
}//loop
return serviceObject;
}
public Object parseParamValues(String values[],Class type)
{
try
{
if(values==null || values.length==0)return null;

if(type.isArray()==false)
{
String data=values[0];
return parseParamValue(data,type);
}


if(type.getComponentType().isArray())return null;
//parsing
if(type.getComponentType().equals(String.class))
{
return values;
}//


if(type.getComponentType().equals(Integer.class))
{
Integer val[]=new Integer[values.length];
int i=0;
int count=0;
for(String value:values)
{
try
{
val[i]=Integer.parseInt(value);
count++;
i++;
}catch(NumberFormatException nfe){}
}
Integer vall[]= new Integer[count];
for(i=0;i<count;i++)vall[i]=val[i];
return vall;
}


if(type.getComponentType().equals(int.class))
{
int val[]=new int[values.length];
int i=0;
int count=0;
for(String value:values)
{
try
{
val[i]=Integer.parseInt(value);
count++;
i++;
}catch(NumberFormatException nfe){}
}
int vall[]= new int[count];
for(i=0;i<count;i++)vall[i]=val[i];
return vall;
}


if(type.getComponentType().equals(Short.class))
{
Short val[]= new Short[values.length];
int i=0;
int count=0;
for(String value:values)
{
try
{
val[i]=Short.parseShort(value);
count++;
i++;
}catch(NumberFormatException nfe){}
}
Short vall[]= new Short[count];
for(i=0;i<count;i++)vall[i]=val[i];
return vall;
}


if(type.getComponentType().equals(short.class))
{
short val[]= new short[values.length];
int i=0;
int count=0;
for(String value:values)
{
try
{
val[i]=Short.parseShort(value);
count++;
i++;
}catch(NumberFormatException nfe){}
}
short vall[]= new short[count];
for(i=0;i<count;i++)vall[i]=val[i];
return vall;
}


if(type.getComponentType().equals(Long.class))
{
Long val[]= new Long[values.length];
int i=0;
int count=0;
for(String value:values)
{
try
{
val[i]=Long.parseLong(value);
count++;
i++;
}catch(NumberFormatException nfe){}
}
Long vall[]= new Long[count];
for(i=0;i<count;i++)vall[i]=val[i];
return vall;
}


if(type.getComponentType().equals(long.class))
{
long val[]= new long[values.length];
int i=0;
int count=0;
for(String value:values)
{
try
{
val[i]=Long.parseLong(value);
count++;
i++;
}catch(NumberFormatException nfe){}
}
long vall[]= new long[count];
for(i=0;i<count;i++)vall[i]=val[i];
return vall;
}


if(type.getComponentType().equals(Byte.class))
{
Byte val[]= new Byte[values.length];
int i=0;
int count=0;
for(String value:values)
{
try
{
val[i]=Byte.parseByte(value);
count++;
i++;
}catch(NumberFormatException nfe){}
}
Byte vall[]= new Byte[count];
for(i=0;i<count;i++)vall[i]=val[i];
return vall;
}
if(type.getComponentType().equals(byte.class))
{
byte val[]= new byte[values.length];
int i=0;
int count=0;
for(String value:values)
{
try
{
val[i]=Byte.parseByte(value);
count++;
i++;
}catch(NumberFormatException nfe){}
}
byte vall[]= new byte[count];
for(i=0;i<count;i++)vall[i]=val[i];
return vall;
}
if(type.getComponentType().equals(Double.class))
{
Double val[]= new Double[values.length];
int i=0;
int count=0;
for(String value:values)
{
try
{
val[i]=Double.parseDouble(value);
count++;
i++;
}catch(NumberFormatException nfe){}
}
Double vall[]= new Double[count];
for(i=0;i<count;i++)vall[i]=val[i];
return vall;
}

if(type.getComponentType().equals(double.class))
{
double val[]= new double[values.length];
int i=0;
int count=0;
for(String value:values)
{
try
{
val[i]=Double.parseDouble(value);
count++;
i++;
}catch(NumberFormatException nfe){}
}
double vall[]= new double[count];
for(i=0;i<count;i++)vall[i]=val[i];
return vall;
}
if(type.getComponentType().equals(Float.class))
{
Float val[]= new Float[values.length];
int i=0;
int count=0;
for(String value:values)
{
try
{
val[i]=Float.parseFloat(value);
count++;
i++;
}catch(NumberFormatException nfe){}
}
Float vall[]= new Float[count];
for(i=0;i<count;i++)vall[i]=val[i];
return vall;
}

if(type.getComponentType().equals(float.class))
{
float val[]= new float[values.length];
int i=0;
int count=0;
for(String value:values)
{
try
{
val[i]=Float.parseFloat(value);
count++;
i++;
}catch(NumberFormatException nfe){}
}
float vall[]= new float[count];
for(i=0;i<count;i++)vall[i]=val[i];
return vall;
}

if(type.getComponentType().equals(Character.class))
{
Character val[]= new Character[values.length];
int i=0;
int count=0;
for(String value:values)
{
try
{
val[i]=value.charAt(0);
count++;
i++;
}catch(NumberFormatException nfe){}
}
Character vall[]= new Character[count];
for(i=0;i<count;i++)vall[i]=val[i];
return vall;
}

if(type.getComponentType().equals(char.class))
{
char val[]= new char[values.length];
int i=0;
int count=0;
for(String value:values)
{
try
{
val[i]=value.charAt(0);
count++;
i++;
}catch(NumberFormatException nfe){}
}
char vall[]= new char[count];
for(i=0;i<count;i++)vall[i]=val[i];
return vall;
}

if(type.getComponentType().equals(Boolean.class))
{
Boolean val[]= new Boolean[values.length];
int i=0;
int count=0;
for(String value:values)
{
try
{
val[i]=Boolean.parseBoolean(value);
count++;
i++;
}catch(NumberFormatException nfe){}
}
Boolean vall[]= new Boolean[count];
for(i=0;i<count;i++)vall[i]=val[i];
return vall;
}

if(type.getComponentType().equals(boolean.class))
{
boolean val[]= new boolean[values.length];
int i=0;
int count=0;
for(String value:values)
{
try
{
val[i]=Boolean.parseBoolean(value);
count++;
i++;
}catch(NumberFormatException nfe){}
}
boolean vall[]= new boolean[count];
for(i=0;i<count;i++)vall[i]=val[i];
return vall;
}

//parsed
return null;	
}catch(Exception e)
{
return null;	
}
}
//
public Object parseParamValue(String data,Class type)
{
if(type.equals(String.class))return data;
if(type.equals(Object.class))return data;
try
{
if(type.equals(Integer.class))return Integer.parseInt(data);
if(type.equals(int.class))return Integer.parseInt(data);
if(type.equals(Short.class))return Short.parseShort(data);
if(type.equals(short.class))return Short.parseShort(data);
if(type.equals(Long.class))return Long.parseLong(data);
if(type.equals(long.class))return Long.parseLong(data);
if(type.equals(Byte.class))return Byte.parseByte(data);
if(type.equals(byte.class))return Byte.parseByte(data);
if(type.equals(Double.class))return Double.parseDouble(data);
if(type.equals(double.class))return Double.parseDouble(data);
if(type.equals(Float.class))return Float.parseFloat(data);
if(type.equals(float.class))return Float.parseFloat(data);
if(type.equals(Character.class))return data.charAt(0);
if(type.equals(char.class))return data.charAt(0);
if(type.equals(Boolean.class))return Boolean.parseBoolean(data);
if(type.equals(boolean.class))return Boolean.parseBoolean(data);
}catch(NumberFormatException nfe){}
return null;
}
}
