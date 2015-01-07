package com.qorder.qorderws.provider;

import com.qorder.qorderws.model.EEntity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public interface IFileProvider {
	
	File getFile(String path) throws FileNotFoundException;
	
	byte[] getResourceByteArray(String path) throws IOException;
	
	byte[] getResourceByteArrayFor(EEntity entity, long id) throws IOException;

}
