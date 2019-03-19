package cn.com.cloudstar.rightcloud.infra.cloud.huaweicloud;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.huawei.openstack4j.api.Builders;
import com.huawei.openstack4j.api.identity.EndpointURLResolver;
import com.huawei.openstack4j.api.types.ServiceType;
import com.huawei.openstack4j.model.compute.Flavor;
import com.huawei.openstack4j.model.image.v2.Image;
import com.huawei.openstack4j.model.network.AttachInterfaceType;
import com.huawei.openstack4j.model.network.Network;
import com.huawei.openstack4j.model.network.Router;
import com.huawei.openstack4j.model.network.RouterInterface;
import com.huawei.openstack4j.model.network.Subnet;
import com.huawei.openstack4j.openstack.OSFactory;
import com.huawei.openstack4j.api.OSClient.OSClientV3;
import com.huawei.openstack4j.core.transport.Config;
import com.huawei.openstack4j.model.common.Identifier;
import com.huawei.openstack4j.model.compute.Server;

public class Demo {
    public static void main(String[] args) {


// 初始化client
        OSClientV3 os = HuaweiUtil.getClient();

// 设置查询参数
        Map<String, String> filter = new HashMap<String, String>();
// 将需要输入的参数都放入filter里面
//        filter.put("limit", "3");

// 调用查询虚拟机列表的接口
        List<? extends Server> serverList = os.compute().servers().list(filter);
        for (Server server : serverList) {
            System.out.println(server);
        }

        List<? extends Flavor> flavors = os.compute().flavors().list();
        for (Flavor flavor : flavors) {
            System.out.println(flavor.getId() + "  " + flavor.getName() + " " + flavor.getVcpus() + "-" + flavor.getRam());
        }

        List<? extends Image> list = os.imagesV2().list();


    }

    public Network createNetwork(OSClientV3 os) {
        //Create a router
        Router router = os.networking().router().create(Builders.router()
                .name("routerName")
                .build());
        //Create a network
        Network network = os.networking().network().create(Builders
                .network()
                .name("networkName")
                .adminStateUp(true)
                .build());
        //Create a subnet
        Subnet subnet = os.networking().subnet().create(Builders.subnet()
                .networkId(network.getId())
                .name("subnetName")
                .enableDHCP(true)
                .cidr("192.168.0.0/24")
                .addDNSNameServer("8.8.8.8")
                .gateway("192.168.0.1")
                .build());
        //Connect the subnet to the router, make the router connect to the internet.
        RouterInterface routerinf = os.networking().router().attachInterface(router.getId(), AttachInterfaceType.SUBNET, subnet.getId());
        return network;
    }
}