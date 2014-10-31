package com.qorder.qorderws.service;

import java.io.IOException;

import com.qorder.qorderws.model.EEntity;

public interface IImageService {
	
	byte[] getImageFor(EEntity entity, long entityID) throws IOException;	

}
