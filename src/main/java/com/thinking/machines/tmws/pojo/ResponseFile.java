package com.thinking.machines.tmws.pojo;
import java.io.*;
public class ResponseFile
{
private File file;
private String mimeType;
private String fileName;
private boolean shouldDownload;
public ResponseFile()
{
this.file=null;
this.mimeType="";
this.shouldDownload=false;
this.fileName="";
}
public void setFile(File file)
{
this.file=file;
}
public File getFile()
{
return file;
}
public void setMimeType(String mimeType)
{
this.mimeType=mimeType;
}
public String getMimeType()
{
return mimeType;
}
public void setFileName(String fileName)
{
this.fileName=fileName;
}
public String getFileName()
{
return fileName;
}
public void setShouldDownload(boolean shouldDownload)
{
this.shouldDownload=shouldDownload;
}
public boolean getShouldDownload()
{
return shouldDownload;
}

}
