package cn.com.cloudstar.rightcloud.infra.cloud.huaweicloud.elb;

import cn.com.cloudstar.rightcloud.infra.cloud.huaweicloud.HuaweiUtil;
import com.alibaba.fastjson.JSON;
import com.huawei.openstack4j.api.OSClient.OSClientV3;
import com.huawei.openstack4j.model.common.Identifier;
import com.huawei.openstack4j.openstack.OSFactory;
import com.huawei.openstack4j.openstack.networking.domain.ext.NeutronWhitelist;

//import com.huawei.openstack4j.openstack.networking.domain.ext.NeutronWhitelist;
//import com.huawei.openstack4j.openstack.networking.domain.ext.NeutronWhitelistUpdate;

public class Whitelist {

	public static void main(String[] args) {
        
		String user = "******";
		String password = "******";
		String projectId = "******";
		String userDomainId = "******";
		String authUrl = "******";

		OSClientV3 osclient = HuaweiUtil.getClient();
		NeutronWhitelist.NeutronWhitelists list = osclient.networking().lbaasV2().lbWhitelist().list();
		System.out.println(JSON.toJSONString(list));

		String lsnr_id = "******";
//Create a Whitelist
//		NeutronWhitelist whitelist_test = NeutronWhitelist.builder().listenerId(lsnr_id).enableWhitelist(true).whitelist("192.168.0.0/24,192.168.1.0/24").build();
//		NeutronWhitelist neutronWhitelist = osclient.networking().lbaasV2().lbWhitelist().create(whitelist_test);
//		System.out.println(neutronWhitelist);


//List all Whitelists
//

//Query a Whitelist
		String wl_id = "******";
//		osclient.networking().lbaasV2().lbWhitelist().get(wl_id);
		
//Update a Whitelist
//		NeutronWhitelistUpdate whitelist_update = NeutronWhitelistUpdate.builder().whitelist("192.168.0.0/24，192.168.1.0、25").build();
//		osclient.networking().lbaasV2().lbWhitelist().update(whitelist_update, wl_id);
		
//Delete a Whitelist
//		osclient.networking().lbaasV2().lbWhitelist().delete(wl_id);
	}

}