package com.qorder.qorderws.service;

import com.qorder.qorderws.model.EEntity;

import javax.validation.constraints.NotNull;

public interface IImageService {
	
	byte[] getImageFor(@NotNull EEntity entity, long entityID);

}
