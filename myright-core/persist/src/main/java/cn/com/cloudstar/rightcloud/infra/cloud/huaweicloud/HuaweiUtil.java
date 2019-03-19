package cn.com.cloudstar.rightcloud.infra.cloud.huaweicloud;

import com.huawei.openstack4j.api.OSClient;
import com.huawei.openstack4j.model.common.Identifier;
import com.huawei.openstack4j.openstack.OSFactory;

/**
 * @author Hong.Wu
 * @date: 16:04 2019/03/05
 */
public class HuaweiUtil {



    public static OSClient.OSClientV3 getClient(){
        String authUrl = "https://iam.cn-north-1.myhuaweicloud.com/v3"; //endpoint Url
        String user = "cloudstar"; //用户名
        String password = "Pioneer@2017"; //用户密码
        String userDomainId = "5cb5a74683664d51b3563e44ab1fee6e"; //账号ID


        //String projectId = "cbd04f1ab6764fba831cc603ab68b49f"; //项目ID
        String projectId = "103f550f68c94cb1b413eac0b1c18123"; //项目ID 上海二
        // 初始化client
        OSClient.OSClientV3 os = OSFactory.builderV3()
                .endpoint(authUrl)
                .credentials(user, password, Identifier.byId(userDomainId))
                .scopeToProject(Identifier.byId(projectId))
                .authenticate();
        String AuthToken = os.getToken().getId();
        System.out.println("AuthToken="+AuthToken);
        return os;
    }
}
