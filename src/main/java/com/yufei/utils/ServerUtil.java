package com.yufei.utils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;

public abstract class ServerUtil {

	private static final Log log = LogFactory.getLog(ServerUtil.class);
    
	/**
	 * 获取Client对象
	 * 
	 * @param wsdlUrlKey
	 * @return
	 */
	public static Client getClient(String wsdlUrlKey) {
		
		try {
            String wsdlUrl = PropertiesUtil.getValue(DataTypeUtils.FILENAME_CONFIG_PROPERTIES, wsdlUrlKey);
            JaxWsDynamicClientFactory factory = JaxWsDynamicClientFactory.newInstance();
            return factory.createClient(wsdlUrl);
        } catch (Exception e) {
            log.error("获取Client异常", e);
        }
		return null;
	}
}
