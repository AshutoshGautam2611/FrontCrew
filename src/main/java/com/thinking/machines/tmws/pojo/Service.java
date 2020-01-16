package com.thinking.machines.tmws.pojo;
import java.lang.reflect.*;
import java.util.*;
import java.io.*;
import com.thinking.machines.tmws.annotations.*;
import com.thinking.machines.tmws.pool.*;
import com.thinking.machines.tmws.pojo.*;
import com.thinking.machines.tmws.interfaces.*;
import com.thinking.machines.tmws.pool.*;

public class Service
{
private String path;//
private Method method;//
private boolean injectRequestContainer;
private boolean injectSessionContainer;
private boolean injectApplicationContainer;
private boolean injectContextDirectory;
private boolean injectCookieeManager;
private boolean isSecured;
private boolean allowGet;
private boolean allowPost;
private Guard guard;
private Class returnType;
private ResponseType responseType;
private String forwardTo;//
private boolean isForwarding;

private Class [] parameters;//
private int[] autoWiredParameter;
private int[] autoWiredParameterIndexes;
private int[] sessionParameterIndexes;
private int[] applicationParameterIndexes;
private int[] contextDirectoryParameterIndexes;
private int[] cookieManagerParameterIndexes;
private Property propertyParameters;
private int[] propertyParameterIndexes	;
public Service()
{
}


public void setPath(String path)
{
this.path=path;
}

public String getPath()
{
return this.path;
}

public void setMethod(Method method)
{
this.method=method;
}

public Method getMethod()
{
return this.method;
}

public void setInjectRequestContainer(boolean injectRequestContainer)
{
this.injectRequestContainer=injectRequestContainer;
}

public boolean getInjectRequestContainer()
{
return this.injectRequestContainer;
}

public void setInjectSessionContainer(boolean injectSessionContainer)
{
this.injectSessionContainer=injectSessionContainer;
}

public boolean getInjectSessionContainer()
{
return this.injectSessionContainer;
}

public void setInjectApplicationContainer(boolean injectApplicationContainer)
{
this.injectApplicationContainer=injectApplicationContainer;
}

public boolean getInjectApplicationContainer()
{
return this.injectApplicationContainer;
}

public void setInjectContextDirectory(boolean injectContextDirectory)
{
this.injectContextDirectory=injectContextDirectory;
}

public boolean getInjectContextDirectory()
{
return this.injectContextDirectory;
}

public void setInjectCookieeManager(boolean injectCookieeManager)
{
this.injectCookieeManager=injectCookieeManager;
}

public boolean getInjectCookieeManager()
{
return this.injectCookieeManager;
}

public void setIsSecured(boolean isSecured)
{
this.isSecured=isSecured;
}

public boolean getIsSecured()
{
return this.isSecured;
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

public void setGuard(Guard guard)
{
this.guard=guard;
}

public Guard getGuard()
{
return this.guard;
}

public void setReturnType(Class returnType)
{
this.returnType=returnType;
}

public Class getReturnType()
{
return this.returnType;
}

public void setResponseType(ResponseType responseType)
{
this.responseType=responseType;
}

public ResponseType getResponseType()
{
return this.responseType;
}

public void setForwardTo(String forwardTo)
{
this.forwardTo=forwardTo;
}

public String getForwardTo()
{
return this.forwardTo;
}

public void setIsForwarding(boolean isForwarding)
{
this.isForwarding=isForwarding;
}

public boolean getIsForwarding()
{
return this.isForwarding;
}

public void setParameters(Class[] parameters)
{
this.parameters=parameters;
}

public Class[] getParameters()
{
return this.parameters;
}

public void setAutoWiredParameter(int[] autoWiredParameter)
{
this.autoWiredParameter=autoWiredParameter;
}

public int[] getAutoWiredParameter()
{
return this.autoWiredParameter;
}

public void setAutoWiredParameterIndexes(int[] autoWiredParameterIndexes)
{
this.autoWiredParameterIndexes=autoWiredParameterIndexes;
}

public int[] getAutoWiredParameterIndexes()
{
return this.autoWiredParameterIndexes;
}

public void setSessionParameterIndexes(int[] sessionParameterIndexes)
{
this.sessionParameterIndexes=sessionParameterIndexes;
}

public int[] getSessionParameterIndexes()
{
return this.sessionParameterIndexes;
}

public void setApplicationParameterIndexes(int[] applicationParameterIndexes)
{
this.applicationParameterIndexes=applicationParameterIndexes;
}

public int[] getApplicationParameterIndexes()
{
return this.applicationParameterIndexes;
}

public void setContextDirectoryParameterIndexes(int[] contextDirectoryParameterIndexes)
{
this.contextDirectoryParameterIndexes=contextDirectoryParameterIndexes;
}

public int[] getContextDirectoryParameterIndexes()
{
return this.contextDirectoryParameterIndexes;
}

public void setCookieManagerParameterIndexes(int[] cookieManagerParameterIndexes)
{
this.cookieManagerParameterIndexes=cookieManagerParameterIndexes;
}

public int[] getCookieManagerParameterIndexes()
{
return this.cookieManagerParameterIndexes;
}

public void setPropertyParameters(Property propertyParameters)
{
this.propertyParameters=propertyParameters;
}

public Property getPropertyParameters()
{
return this.propertyParameters;
}

public void setPropertyParameterIndexes(int[] propertyParameterIndexes)
{
this.propertyParameterIndexes=propertyParameterIndexes;
}

public int[] getPropertyParameterIndexes()
{
return this.propertyParameterIndexes;
}
/*public void  setMethod(Method method)
{

this.method=method;
this.setReturnType(method.getReturnType());
this.setParameters(method.getParameterTypes());
List<int> autoWiredParameterIndexes= new LinkedList<>();
List<int> sessionParameterIndexes=new LinkedList<>();
List<int> applicationParameterIndexes=new LinkedList<>();
List<int> contextDirectoryParameterIndexes=new LinkedList<>();
List<int> cookieManagerParameterIndexes=new LinkedList<>();
Annotations [][] annotations= method.getParameterAnnotations();
int i=0;
int j=0;
for(Class parameter: parameters)
{
if(parameter.getName().equals("com.thinking.machines.tmws.ApplicationContainer"))
{
applicationParameterIndex.add(i);
i++;
continue;
}
if(parameter.getName().equals("com.thinking.machines.tmws.SessionContainer"))
{
sessionParameterIndex.add(i);
i++;
continue;
}
if(parameter.getName().equals("com.thinking.machines.tmws.pojo.contextDirectory"))
{
contextDirectoryParameterIndex.add(i);
i++;
continue;
}
if(parameter.getName().equals("com.thinking.machines.tmws.pojo.cookieManager"))
{
cookieManagerParameterIndex.add(i);
i++;
continue;
}

}	
}*/
}
