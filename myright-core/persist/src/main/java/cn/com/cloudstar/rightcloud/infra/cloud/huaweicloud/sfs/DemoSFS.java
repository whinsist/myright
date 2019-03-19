package cn.com.cloudstar.rightcloud.infra.cloud.huaweicloud.sfs;

import cn.com.cloudstar.rightcloud.infra.cloud.huaweicloud.HuaweiUtil;
import com.huawei.openstack4j.api.OSClient;
import com.huawei.openstack4j.api.manila.ShareService;
import com.huawei.openstack4j.model.image.v2.Image;
import com.huawei.openstack4j.model.manila.Share;

import java.util.List;

/**
 * @author Hong.Wu
 * @date: 16:03 2019/03/05
 */
public class DemoSFS {
    
    public static void main(String[] args) {
        OSClient.OSClientV3 os = HuaweiUtil.getClient();


        ShareService shareService = os.share();
        List<? extends Share> shareList = shareService.shares().list();
        System.out.println("shareList"+shareList);





    }   
    
}
