package cn.com.cloudstar.rightcloud.infra.cloud.huaweicloud.ecs;

import java.util.List;

import cn.com.cloudstar.rightcloud.infra.cloud.huaweicloud.HuaweiUtil;
import com.huawei.openstack4j.api.OSClient.OSClientV3;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.model.common.Identifier;
import com.huawei.openstack4j.openstack.OSFactory;

import com.huawei.openstack4j.model.compute.FloatingIP;

public class FloatingIPDemo {
	public static void main(String[] args) {


		
		// create connection
		OSClientV3 os = HuaweiUtil.getClient();
		
		String server_id = "ec66f750-0aee-4c43-811a-c5a76b849d1a";
		
		// allocate EIP
		List<String> pools = os.compute().floatingIps().getPoolNames();
		FloatingIP ip = os.compute().floatingIps().allocateIP(pools.get(0));
		if(null != ip) {
			System.out.println("allocateIP success, ip=" + ip.getFloatingIpAddress());
		}else {
			System.out.println("allocateIP failed");
		}
		
		//do not specify fixedIP add EIP	
		if(null != ip) {
			ActionResponse rep1 = os.compute().floatingIps().addFloatingIP(server_id, ip.getFloatingIpAddress());
			if (rep1.isSuccess()) {
				System.out.println("addFloatingIp2Param success");
				System.out.println("allocateIP success, ip=" + ip.getFloatingIpAddress());
			} else {
				System.out.println("addFloatingIp2Param failed");
			}
		} else {
			System.out.println("allocateIP failed");
		}
			
		//specify fixedIp add EIP 
		String fixedIp = "192.168.0.15";
		if(null != ip) {
			ActionResponse rep2 = os.compute().floatingIps().addFloatingIP(server_id, fixedIp, ip.getFloatingIpAddress());
			if (rep2.isSuccess()) {
				System.out.println("addFloatingIp3Param success, fixedIP = " + fixedIp);
			} else {
				System.out.println("addFloatingIp3Param failed");
			}
		} else {
			System.out.println("allocateIP failed");
		}
		
		//get list of EIP
		List<? extends FloatingIP> ips = os.compute().floatingIps().list();
		if (null != ips) {
			System.out.println("GetFloatingIpList success, list size=" + ips.size());
		} else {
			System.out.println("GetFloatingIpList failed");
		}
		
		//deallocate EIP
		ActionResponse rep3 = os.compute().floatingIps().deallocateIP(ip.getId());
		if (rep3.isSuccess()) {
			System.out.println("DeallocateIP success");
		} else {
			System.out.println("DeallocateIP failed");
		}
		
		//remove EIP
		ActionResponse rep4 = os.compute().floatingIps().removeFloatingIP(server_id, ip.getFloatingIpAddress());
		if (rep4.isSuccess()) {
			System.out.println("RemoveFloatingIP success");
		} else {
			System.out.println("RemoveFloatingIP failed");
		}
	}
}