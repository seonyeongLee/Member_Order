package com.api.idus.common.utility;

import org.modelmapper.ModelMapper;

public class SingleModelMapper {
    private static ModelMapper modelMapper = new ModelMapper();
    public static ModelMapper getInstance() {
        return modelMapper;
    }
}
