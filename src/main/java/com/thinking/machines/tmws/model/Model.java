package com.thinking.machines.tmws.model;
import com.thinking.machines.tmws.pojo.*;
import java.util.*;
public class Model
{
public final static String id;
static
{
id=java.util.UUID.randomUUID().toString();
}
private Map<String,ServiceModule> serviceModules;
private Map<String,String> serviceModuleKeys;
private ContextDirectory contextDirectory;
public Model()
{
this.serviceModules= new HashMap<>();
this.serviceModules= new HashMap<>();
}
public void addServiceModule(ServiceModule serviceModule)
{
Map<String,Service> services=serviceModule.getServices();
String serviceModulePath=serviceModule.getPath();
String servicePath;
String serviceModuleKey=UUID.randomUUID().toString();
serviceModules.put(serviceModuleKey,serviceModule);
Set<String> serviceKeys=services.keySet();
for(String key:serviceKeys)
{
servicePath=serviceModulePath+key;
serviceModuleKeys.put(servicePath,serviceModuleKey);
}
}
public ServiceModule getServiceModule(String requestPath)
{
String serviceModuleKey=serviceModuleKeys.get(requestPath);
if(serviceModuleKey==null)return null;
return serviceModules.get(serviceModuleKey);
}
public void setContextDirectory(String folderName)
{
this.contextDirectory=new ContextDirectory(folderName);
}
public ContextDirectory getContextDirectory()
{
return this.contextDirectory;
}
} 







