package com.qorder.qorderws.fao;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import com.qorder.qorderws.model.EEntity;

public interface IFileAccessObject {
	
	File getFile(String path) throws FileNotFoundException;
	
	byte[] getResourceByteArray(String path) throws IOException;
	
	byte[] getResourceByteArrayFor(EEntity entity, long id) throws IOException;

}
