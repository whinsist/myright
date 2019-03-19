package cn.com.cloudstar.rightcloud.infra.cloud.huaweicloud.elb;

import cn.com.cloudstar.rightcloud.infra.cloud.huaweicloud.HuaweiUtil;
import com.alibaba.fastjson.JSON;
import com.huawei.openstack4j.api.OSClient.OSClientV3;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.model.common.Identifier;
import com.huawei.openstack4j.model.network.ext.LbMethod;
import com.huawei.openstack4j.model.network.ext.LbPoolV2;
import com.huawei.openstack4j.model.network.ext.LbPoolV2Update;
import com.huawei.openstack4j.model.network.ext.Protocol;
import com.huawei.openstack4j.openstack.OSFactory;
import com.huawei.openstack4j.openstack.networking.domain.ext.NeutronLbPoolV2;
import com.huawei.openstack4j.openstack.networking.domain.ext.NeutronLbPoolV2Update;

import java.util.List;


/// 后端服务器组
public class Pool {

	public static void main(String[] args) {
		                                                                                                 


	OSClientV3 osclient = HuaweiUtil.getClient();
//		//List all Pools
		List<? extends LbPoolV2> list = osclient.networking().lbaasV2().lbPool().list();
		System.out.println(JSON.toJSONString(list));


//		ActionResponse actionRes = osclient.networking().lbaasV2().lbPool().delete("f6ce9b30-185a-44ac-99ab-a8dc0692b125");
//		System.out.println(actionRes.isSuccess());


//Create a Pool
		String lsnr_id = "******";
//		LbPoolV2 pool = NeutronLbPoolV2.builder().adminStateUp(true).listenerId(lsnr_id).protocol(Protocol.HTTP).name("HTTP-group").lbMethod(LbMethod.LEAST_CONNECTIONS).build();
		LbPoolV2 pool = NeutronLbPoolV2.builder().adminStateUp(true).loadbalancerId("")
				.listenerId(lsnr_id).protocol(Protocol.HTTP).name("HTTP-group").lbMethod(LbMethod.LEAST_CONNECTIONS).build();
//		osclient.networking().lbaasV2().lbPool().create(pool);

//Query info about a Pool
//		String pool_id = "******";
//		osclient.networking().lbaasV2().lbPool().get(pool_id);
//Update a Pool
//		LbPoolV2Update pool_mod = NeutronLbPoolV2Update.builder().name("HTTP-pool").build();
//		osclient.networking().lbaasV2().lbPool().update(pool_id, pool_mod);
//Delete a Pool
//		String pool_id_todel = "******";
//		osclient.networking().lbaasV2().lbPool().delete(pool_id_todel);
		}

}