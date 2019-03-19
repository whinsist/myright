package cn.com.cloudstar.rightcloud.infra.cloud.huaweicloud.elb;

import cn.com.cloudstar.rightcloud.infra.cloud.huaweicloud.HuaweiUtil;
import com.clouds.util.JsonUtil;
import com.huawei.openstack4j.api.OSClient.OSClientV3;
import com.huawei.openstack4j.model.common.Identifier;
import com.huawei.openstack4j.model.compute.FloatingIP;
import com.huawei.openstack4j.openstack.OSFactory;

import com.huawei.openstack4j.model.network.ext.LoadBalancerV2;
import com.huawei.openstack4j.model.network.ext.LoadBalancerV2Update;
import com.huawei.openstack4j.openstack.networking.domain.ext.NeutronLoadBalancerV2;
import com.huawei.openstack4j.openstack.networking.domain.ext.NeutronLoadBalancerV2Update;

import java.util.List;

public class Loadbalancer {

	public static void main(String[] args) {


		OSClientV3 osclient = HuaweiUtil.getClient();

//Enhanced Loadbalancer Ops starts here!
			
//Create an Enhanced Loadbalancer
		String subnet_id = "8308ddb2-af39-4f28-bad9-0c9d1891e5e1";//specify in which subnet you want to create an enhanced loadbalancer
		LoadBalancerV2 myelb = NeutronLoadBalancerV2.builder().adminStateUp(true)
				.subnetId(subnet_id)
				.name("whtest111")
				//.address("192.168.0.222") // 服务地址：在手动分配时
				.build();
		LoadBalancerV2 loadBalancerCreate = osclient.networking().lbaasV2().loadbalancer().create(myelb);
		System.out.println("loadBalancerCreate="+loadBalancerCreate);

//List all Enhanced Loadbalancer under tenant's project
		List<? extends LoadBalancerV2> list = osclient.networking().lbaasV2().loadbalancer().list();
		System.out.println(JsonUtil.toJson(list));
		List<? extends FloatingIP> listHashInst = osclient.compute().floatingIps().list();
		System.out.println(listHashInst);


//Query info about a specific Loadbalancer
//		String lb_id = "24255471-2bdd-4e31-9893-7df04b5c1db1";//specify your loadbalancer id here
//		osclient.networking().lbaasV2().loadbalancer().get(lb_id);
		
//Update an Enhanced Loadbalancer
//		LoadBalancerV2Update update_myelb = NeutronLoadBalancerV2Update.builder().name("l4_test_lb_mod").build();
//		osclient.networking().lbaasV2().loadbalancer().update(lb_id, update_myelb);
		
//Delete an Enhanced Loadbalancer
//		osclient.networking().lbaasV2().loadbalancer().delete(lb_id);

	}

}