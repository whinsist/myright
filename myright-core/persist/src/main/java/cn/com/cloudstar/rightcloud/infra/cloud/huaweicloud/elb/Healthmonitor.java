package cn.com.cloudstar.rightcloud.infra.cloud.huaweicloud.elb;

import javax.ws.rs.HttpMethod;

import cn.com.cloudstar.rightcloud.infra.cloud.huaweicloud.HuaweiUtil;
import com.alibaba.fastjson.JSON;
import com.huawei.openstack4j.api.OSClient.OSClientV3;
import com.huawei.openstack4j.model.common.Identifier;
import com.huawei.openstack4j.openstack.OSFactory;

import com.huawei.openstack4j.model.network.ext.HealthMonitorType;
import com.huawei.openstack4j.model.network.ext.HealthMonitorV2;
import com.huawei.openstack4j.model.network.ext.HealthMonitorV2Update;
import com.huawei.openstack4j.openstack.networking.domain.ext.NeutronHealthMonitorV2;
import com.huawei.openstack4j.openstack.networking.domain.ext.NeutronHealthMonitorV2Update;

import java.util.List;

public class Healthmonitor {

	public static void main(String[] args) {
        
		String user = "******";
		String password = "******";
		String projectId = "******";
		String userDomainId = "******";
		String authUrl = "******";

	OSClientV3 osclient = HuaweiUtil.getClient();

		//List all health monitor
		List<? extends HealthMonitorV2> list = osclient.networking().lbaasV2().healthMonitor().list();
		System.out.println(JSON.toJSONString(list));


		String pool_id = "ad725ca6-75aa-4b31-95a8-90bbb4534f1f";
//Create a healthmonitor		
		HealthMonitorV2 hm = NeutronHealthMonitorV2.builder().adminStateUp(true).poolId(pool_id).delay(10).maxRetries(10).timeout(10).type(HealthMonitorType.HTTP).build();
		HealthMonitorV2 healthMonitorV2 = osclient.networking().lbaasV2().healthMonitor().create(hm);
		System.out.println(" healthMonitorV2 result:"+healthMonitorV2);


//Query info about a healthmonitor
//		String hm_id = "";
//		osclient.networking().lbaasV2().healthMonitor().get(hm_id);
		
//Update a healthmonitor
//		HealthMonitorV2Update hm_mod = NeutronHealthMonitorV2Update.builder().delay(3).maxRetries(5).httpMethod(HttpMethod.GET).expectedCodes("200").build();
//		osclient.networking().lbaasV2().healthMonitor().update(hm_id, hm_mod);
		
//Delete a healthmonitor
//		osclient.networking().lbaasV2().healthMonitor().delete(hm_id);
	}

}