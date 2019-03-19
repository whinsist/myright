package cn.com.cloudstar.rightcloud.infra.cloud.huaweicloud.elb;

import cn.com.cloudstar.rightcloud.infra.cloud.huaweicloud.HuaweiUtil;
import com.clouds.util.JsonUtil;
import com.huawei.openstack4j.api.OSClient.OSClientV3;
import com.huawei.openstack4j.model.common.Identifier;
import com.huawei.openstack4j.model.compute.FloatingIP;
import com.huawei.openstack4j.model.compute.Server;
import com.huawei.openstack4j.model.network.ext.LbPoolV2;
import com.huawei.openstack4j.model.network.ext.ListenerV2;
import com.huawei.openstack4j.openstack.OSFactory;

import com.huawei.openstack4j.model.network.ext.LoadBalancerV2;
import com.huawei.openstack4j.model.network.ext.LoadBalancerV2Update;
import com.huawei.openstack4j.openstack.networking.domain.ext.NeutronLoadBalancerV2;
import com.huawei.openstack4j.openstack.networking.domain.ext.NeutronLoadBalancerV2Update;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Mytest {

    public static void main(String[] args) {


        OSClientV3 osclient = HuaweiUtil.getClient();

        List<? extends Server> servers = osclient.compute().servers().list();
        System.out.println("虚拟机列表："+JsonUtil.toJson(servers));

        List<? extends FloatingIP> listHashInst = osclient.compute().floatingIps().list();
        System.out.println("公网列表："+listHashInst);

        List<? extends LoadBalancerV2> list = osclient.networking().lbaasV2().loadbalancer().list();
        System.out.println("负载均衡列表："+JsonUtil.toJson(list));


//        Map<String, String> map = new HashMap<>();
//        map.get()

        List<? extends ListenerV2> listenerV2s = osclient.networking().lbaasV2().listener().list();
        System.out.println("监听器列表："+JsonUtil.toJson(listenerV2s));


        List<? extends LbPoolV2> lbPools = osclient.networking().lbaasV2().lbPool().list();
        System.out.println("后端服务器组列表："+JsonUtil.toJson(lbPools));



    }

}