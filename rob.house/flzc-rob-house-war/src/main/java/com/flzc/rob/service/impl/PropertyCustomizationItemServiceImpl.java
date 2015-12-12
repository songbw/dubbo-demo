package com.flzc.rob.service.impl;

import org.springframework.stereotype.Service;

import com.flzc.base.service.impl.BaseServiceImpl;
import com.flzc.rob.api.entity.PropertyCustomizationItem;
import com.flzc.rob.api.service.PropertyCustomizationItemService;

@Service("propertyCustomizationItemService")
public class PropertyCustomizationItemServiceImpl extends BaseServiceImpl implements PropertyCustomizationItemService{

	@Override
	public PropertyCustomizationItem queryPropertyCustomizationItemById(Integer id) {

		return this.findById(PropertyCustomizationItem.class, id);
	}

}
