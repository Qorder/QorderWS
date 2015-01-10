package com.qorder.qorderws.provider;

import com.qorder.qorderws.model.EEntity;

import javax.validation.constraints.NotNull;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public interface IFileProvider {
	
	File getFile(@NotNull String path) throws FileNotFoundException;
	
	byte[] getResourceByteArray(@NotNull String path) throws IOException;
	
	byte[] getResourceByteArrayFor(@NotNull EEntity entity, long id);

}
