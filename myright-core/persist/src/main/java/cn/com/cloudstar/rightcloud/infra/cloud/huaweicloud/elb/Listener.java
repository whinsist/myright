package cn.com.cloudstar.rightcloud.infra.cloud.huaweicloud.elb;

import cn.com.cloudstar.rightcloud.infra.cloud.huaweicloud.HuaweiUtil;
import com.clouds.util.JsonUtil;
import com.huawei.openstack4j.api.OSClient.OSClientV3;
import com.huawei.openstack4j.openstack.OSFactory;
import com.huawei.openstack4j.model.common.Identifier;

import com.huawei.openstack4j.model.network.ext.ListenerProtocol;
import com.huawei.openstack4j.model.network.ext.ListenerV2;
import com.huawei.openstack4j.model.network.ext.ListenerV2Update;
import com.huawei.openstack4j.openstack.networking.domain.ext.NeutronListenerV2Update;
import com.huawei.openstack4j.openstack.networking.domain.ext.NeutronListenerV2;

import java.util.List;

public class Listener {

	public static void main(String[] args) {



			OSClientV3 osclient = HuaweiUtil.getClient();
			

//Create a listener for Enhanced Loadbalancer
//			String lb_id = "165b6a38-5278-4569-b747-b2ee65ea84a4";
//			ListenerV2 listener = NeutronListenerV2.builder().protocolPort(80).protocol(ListenerProtocol.HTTP).loadBalancerId(lb_id).build();
//			osclient.networking().lbaasV2().listener().create(listener);
			
//List all listeners
		List<? extends ListenerV2> list = osclient.networking().lbaasV2().listener().list();
		System.out.println(JsonUtil.toJson(list));


//Query info about a listener
			String lsnr_id = "7568db0d-87dd-4252-a840-8a6e367bf9b9";
			osclient.networking().lbaasV2().listener().get(lsnr_id);
//
//Update a listener
			ListenerV2Update lsnr_mod = NeutronListenerV2Update.builder()
					.name("listener-http44")
					.defaultPoolId("08331547-9550-4362-b171-50a8cc0d3216")
					.build();
		ListenerV2 updateRes = osclient.networking().lbaasV2().listener().update(lsnr_id, lsnr_mod);
		System.out.println(updateRes);




//Delete a listener
//			osclient.networking().lbaasV2().listener().delete(lsnr_id);
			}

}