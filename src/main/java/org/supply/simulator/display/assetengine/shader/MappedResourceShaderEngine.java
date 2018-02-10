package org.supply.simulator.display.assetengine.shader;

import java.util.Map;

import static java.util.Objects.nonNull;

/**
 * Created by Brandon on 2/9/2018.
 */
public class MappedResourceShaderEngine extends AbstractShaderEngine<String> {

    private Map<String, String> keyFragmentShaderMap;
    private Map<String, String> keyVertexShaderMap;

    @Override
    public boolean isValid(String key) {
        return nonNull(getFragmentResourceName(key)) && nonNull(getVertexResourceName(key));
    }

    @Override
    public String getFragmentResourceName(String key) {
        return keyFragmentShaderMap.get(key);
    }

    @Override
    public String getVertexResourceName(String key) {
        return keyVertexShaderMap.get(key);
    }

    public void setKeyFragmentShaderMap(Map<String, String> keyFragmentShaderMap) {
        this.keyFragmentShaderMap = keyFragmentShaderMap;
    }

    public void setKeyVertexShaderMap(Map<String, String> keyVertexShaderMap) {
        this.keyVertexShaderMap = keyVertexShaderMap;
    }
}
