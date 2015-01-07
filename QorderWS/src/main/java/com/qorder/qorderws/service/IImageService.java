package com.qorder.qorderws.service;

import com.qorder.qorderws.model.EEntity;

import java.io.IOException;

public interface IImageService {
	
	byte[] getImageFor(EEntity entity, long entityID) throws IOException;	

}
