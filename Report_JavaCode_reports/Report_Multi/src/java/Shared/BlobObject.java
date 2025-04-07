/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Shared;

import java.sql.Blob;

/**
 *
 * @author ernesto
 */
public class BlobObject {
    String fileName;
    String mimeType;
    Blob objectData;
    
    public BlobObject(String name, String mime, Blob data){
        this.fileName=name;
        this.mimeType=mime;
        this.objectData=data;    
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    public Blob getObjectData() {
        return objectData;
    }

    public void setObjectData(Blob objectData) {
        this.objectData = objectData;
    }
    
    
         
    
}
