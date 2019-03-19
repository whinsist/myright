package cn.com.cloudstar.rightcloud.infra.cloud.huaweicloud.ecs;

import java.util.List;

import cn.com.cloudstar.rightcloud.infra.cloud.huaweicloud.HuaweiUtil;
import com.huawei.openstack4j.api.OSClient.OSClientV3;
import com.huawei.openstack4j.model.common.Identifier;
import com.huawei.openstack4j.openstack.OSFactory;

import com.huawei.openstack4j.model.compute.Flavor;

public class FlavorDemo {
	public static void main(String[] args) {

		 

		// create connection
		OSClientV3 os = HuaweiUtil.getClient();

		//get list of flavor
		List<? extends Flavor> flavors = os.compute().flavors().list();
		String flrId = null;
		if (flavors.size() > 0) {
			flrId = flavors.get(0).getId();
			System.out.println("get flavorList success, size = " + flavors.size());
		} else {
			System.out.println("get flavorList failed");
		}

		//get flavor
		Flavor flavor = os.compute().flavors().get(flrId);
		if (null != flavor) {
			System.out.println("get flavor success, name = " + flavor.getName());
		} else {
			System.out.println("get flavor failed");
		}
	}
}