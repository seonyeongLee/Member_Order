package com.api.sy.common.utility;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.api.sy.common.utility.ObjectUtility.isEmpty;

@Slf4j
public class MapperUtility {

    static private ModelMapper getModelMapper() {
        ModelMapper mapper = SingleModelMapper.getInstance();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        return mapper;
    }

    static public <S, D> D convertData(ModelMapper mapper, S sourceData, Class<D> destinationClass) {
        return mapper.map(sourceData, destinationClass);
    }

    static public <S, D> D convertData(S sourceData, Class<D> destinationClass) {
        return convertData(getModelMapper(), sourceData, destinationClass);
    }

    static public <S, D> List<D> convertList(ModelMapper mapper, List<S> sourceDataList, Class<D> destinationClass) {
        if (isEmpty(sourceDataList)) {
            return new ArrayList<>();
        }

        return sourceDataList.stream()
                .map(sourceData -> mapper.map(sourceData, destinationClass))
                .collect(Collectors.toList());
    }

    static public <S, D> List<D> convertList(List<S> sourceDataList, Class<D> destinationClass) {
        return convertList(getModelMapper(), sourceDataList, destinationClass);
    }

    static public <K, S, D> Map<K, D> convertListToMap(Function<? super S, K> key, List<S> sourceList, Class<D> destinationClass) {
        ModelMapper mapper = getModelMapper();

        return sourceList.stream().collect(Collectors.toMap(key, sourceVo -> mapper.map(sourceVo, destinationClass)));
    }

    static private ObjectMapper getObjectMapper() {
        return new ObjectMapper();
    }

    static public <S> String convertDataToJson(S sourceData) {
        try {
            return getObjectMapper().writeValueAsString(sourceData);
        } catch (JsonProcessingException e) {
            log.error("===== >> Json Processing Exception : {}", e.getMessage(), e);
        }

        return "";
    }

    static public <D> D convertJsonToData(String data, Class<D> destinationClass) throws IOException {
        return getObjectMapper().readValue(data, destinationClass);
    }

    static public Map<String, Object> convertJsonToMap(String data) throws IOException {
        return getObjectMapper().readValue(data, new TypeReference<Map<String, Object>>() {
        });
    }

    static public <S> S convertDataDeepCopy(S sourceData, Class<S> sourceClass) throws IOException {
        ObjectMapper om = getObjectMapper();

        return om.readValue(om.writeValueAsString(sourceData), sourceClass);
    }
}
