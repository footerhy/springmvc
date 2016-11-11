package com.tools.jdbc.util;

import java.util.List;
import java.util.Map;

import com.etw.ecp.paas.core.das.dbas.DbAccessHelper;
import com.etw.ecp.paas.core.das.dbas.DbAccessService;
import com.etw.ecp.paas.impl.ServiceFactory;
import com.tools.consts.utils.Consts;

public class BaseDao {
	private static DbAccessHelper dbAccessHelper = ServiceFactory.getServiceFactory(Consts.RESOURCE_ID_PLATFORM).getDbAccessHelper();
    private static DbAccessService dbAccessService = ServiceFactory.getServiceFactory(Consts.RESOURCE_ID_PLATFORM).getDbAccessService();
    
    public void test(){
    	String sql = "select * from cuser";
    	List<Map<String, ?>> list = dbAccessHelper.queryForList(sql);
    	for(int x=0;x<list.size();x++){
    		Map<String, ?> map = list.get(x);
    		System.out.println(map.get("uname"));
    	}
    }
    
}
