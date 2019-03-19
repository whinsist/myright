package cn.com.cloudstar.rightcloud.infra.cloud.huaweicloud.ecs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import com.huawei.openstack4j.api.Builders;
import com.huawei.openstack4j.api.OSClient.OSClientV3;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.model.common.Identifier;
import com.huawei.openstack4j.model.network.Network;
import com.huawei.openstack4j.openstack.OSFactory;

import com.huawei.openstack4j.model.compute.Action;
import com.huawei.openstack4j.model.compute.Flavor;
import com.huawei.openstack4j.model.compute.RebootType;
import com.huawei.openstack4j.model.compute.Server;
import com.huawei.openstack4j.model.compute.ServerCreate;
import com.huawei.openstack4j.model.compute.ServerUpdateOptions;
import com.huawei.openstack4j.model.compute.VolumeAttachment;
import com.huawei.openstack4j.model.compute.Server.Status;
import com.huawei.openstack4j.model.compute.actions.RebuildOptions;

public class ServerDemo {
	public static void main(String[] args) {
		
		// Using credentials for authentication
		String authUrl = "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"; //endpoint Url
		String user = "xxxxxx"; //username
		String password = "xxxxxx"; //password
		String projectId = "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"; //projectId
		String userDomainId = "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"; //domainId

		// create connection		
		OSClientV3 os = OSFactory.builderV3()
			.endpoint(authUrl)
			.credentials(user, password, Identifier.byId(userDomainId))
			.scopeToProject(Identifier.byId(projectId)).authenticate();	
		
		String flavorId = "c1.medium";
		String imageId = "228b642c-7538-4364-99b4-a88f271234a4";
		String newflavorid = "c2.medium";
		String newimageid = "af60e0d5-6952-4f3d-b0ed-31bb19d4a692";
		String volId = "bc9aef05-299f-4e87-bd7a-779780020690";
		
		// get list of network
		List<? extends Network> networks = os.networking().network().list();
		ArrayList<String> networkList = new ArrayList<String>();
		networkList.add(networks.get(0).getId());
		
		//create server
		ServerCreate sc = Builders.server()
				.name("test-name")
				.flavor(flavorId)
				.image(imageId)
				.networks(networkList)
				.availabilityZone("az1.dc1")
				.build();
		Server server = os.compute().servers().boot(sc);
		if (null != server) {
			System.out.println("boot the first server success, import java.util.List;\n" +
					"\n" +
					"import com.huawei.openstack4j.api.OSClient.OSClientV3;\n" +
					"import com.huawei.openstack4j.model.common.ActionResponse;\n" +
					"import com.huawei.openstack4j.model.common.Identifier;\n" +
					"import com.huawei.openstack4j.openstack.OSFactory;\n" +
					"\n" +
					"import com.huawei.openstack4j.model.compute.ServerGroup;\n" +
					"\n" +
					"public class ServerGroupDemo {\n" +
					"\tpublic static void main(String[] args) {\n" +
					"\t\t\n" +
					"\t\t// Using credentials for authentication\n" +
					"\t\tString authUrl = \"xxxxxxxxxxxxxxxxxxxxxxxxxxxxx\"; //endpoint Url\n" +
					"\t\tString user = \"xxxxx\"; //username\n" +
					"\t\tString password = \"xxxxx\"; //password\n" +
					"\t\tString projectId = \"xxxxxxxxxxxxxxxxxxxxxxxxxxxxx\"; //projectId\n" +
					"\t\tString userDomainId = \"xxxxxxxxxxxxxxxxxxxxxxxxxxxxx\"; //domainId\n" +
					"\n" +
					"\t\t// create connection\n" +
					"\t\tOSClientV3 os = OSFactory.builderV3()\n" +
					"\t\t.endpoint(authUrl)\n" +
					"\t\t.credentials(user, password, Identifier.byId(userDomainId))\n" +
					"\t\t.scopeToProject(Identifier.byId(projectId)).authenticate();\n" +
					"\t\t\n" +
					"\t\t//create serverGroup\n" +
					"\t\tServerGroup serverGroup = os.compute().serverGroups().create(\"zt-servergroup\", \"anti-affinity\");\n" +
					"\t\tif (null != serverGroup) {\n" +
					"\t\t\tSystem.out.println(\"create serverGroup success, id = \" + serverGroup.getId());\n" +
					"\t\t} else {\n" +
					"\t\t\tSystem.out.println(\"create serverGroup failed\");\n" +
					"\t\t}\n" +
					"\t\t\n" +
					"\t\t//get serverGroup\n" +
					"\t\tServerGroup serverGroupInfo = os.compute().serverGroups().get(serverGroup.getId());\n" +
					"\t\tif (null != serverGroupInfo) {\n" +
					"\t\t\tSystem.out.println(\"get serverGroupInfo success, name = \" + serverGroupInfo.getName());\n" +
					"\t\t} else {\n" +
					"\t\t\tSystem.out.println(\"get serverGroupInfo failed\");\n" +
					"\t\t}\n" +
					"\t\t\n" +
					"\t\t//get list of serverGroup\n" +
					"\t\tList<? extends ServerGroup> list = os.compute().serverGroups().list();\n" +
					"\t\tif (list.size() > 0) {\n" +
					"\t\t\tSystem.out.println(\"get ServerGroupList success, size = \" + list.size());\n" +
					"\t\t} else {\n" +
					"\t\t\tSystem.out.println(\"get ServerGroupList failed\");\n" +
					"\t\t}\n" +
					"\t\t\n" +
					"\t\t//delete serverGroup\n" +
					"\t\tActionResponse rep = os.compute().serverGroups().delete(serverGroup.getId());\n" +
					"\t\tif (rep.isSuccess()) {\n" +
					"\t\t\tSystem.out.println(\"deleteserverGroup success\");\n" +
					"\t\t} else {\n" +
					"\t\t\tSystem.out.println(\"deleteserverGroup failed\");\n" +
					"\t\t}\n" +
					"\t}\n" +
					"}id = " + server.getId());
		} else {
			System.out.println("boot the first server failed");
		}
		
		//wait for server status to ACTIVE
		Server waitServer = os.compute().servers().waitForServerStatus(server.getId(), Status.ACTIVE, 10, TimeUnit.MINUTES);
		Status endStatus = waitServer.getStatus();
		if (endStatus.toString().equalsIgnoreCase(Status.ACTIVE.toString())) {
			System.out.println("waitForServerStatus success");
		} else {
			System.out.println("waitForServerStatus failed");
		}	
		String secServerId = null;
		Server secondServer = os.compute().servers().bootAndWaitActive(sc, 180000);
		if (null != secondServer) {
			secServerId = secondServer.getId();
			System.out.println("boot the second server success, id = " + secServerId);
		} else {
			System.out.println("boot the second server failed");
		}

		//stop server
		ActionResponse repStop = os.compute().servers().action(server.getId(), Action.STOP);
		os.compute().servers().waitForServerStatus(server.getId(), Status.SHUTOFF, 10, TimeUnit.MINUTES);
		if (repStop.isSuccess()) {
			System.out.println("Stop the server failed");
		} else {
			System.out.println("Stop the server failed");
		}
		
		//start server
		ActionResponse repStart = os.compute().servers().action(server.getId(), Action.START);
		os.compute().servers().waitForServerStatus(server.getId(), Status.ACTIVE, 10, TimeUnit.MINUTES);
		if (repStart.isSuccess()) {
			System.out.println("Start the server success");
		} else {
			System.out.println("Start the server failed");
		}
		
		//lock server
		ActionResponse repLock = os.compute().servers().action(server.getId(), Action.LOCK);
		if (repLock.isSuccess()) {
			System.out.println("Lock the server success");
		} else {
			System.out.println("Lock the server failed");
		}
		
		//unlock server
		ActionResponse repUnLock = os.compute().servers().action(server.getId(), Action.UNLOCK);
		if (repUnLock.isSuccess()) {
			System.out.println("Lock the server success");
		} else {
			System.out.println("Unlock the server failed");
		}

		//attach volume
		VolumeAttachment attach = os.compute().servers().attachVolume(server.getId(), volId, "/dev/sdd");
		String attachmentId = null;
		if (null != attach) {
			attachmentId = attach.getId();
			System.out.println("VolumeAttachment success, id = " + attachmentId);
		} else {
			System.out.println("VolumeAttachment failed");
		}
		
		//detach Volume
		ActionResponse repDetach = os.compute().servers().detachVolume(server.getId(), volId);
		if (repDetach.isSuccess()) {
			System.out.println("detachVolume success");
		} else {
			System.out.println("detachVolume failed");
		}
		
		//get server
		Server serverInfo = os.compute().servers().get(server.getId());
		if (null != serverInfo) {
			System.out.println("get serverInfo success, name = " + serverInfo.getName());
		} else {
			System.out.println("get serverInfo server failed");
		}
		
		//get list of server
		List<? extends Server> serverList = os.compute().servers().list();
		if (serverList.size() > 0) {
			System.out.println("get serverList success, size = " + serverList.size());
		} else {
			System.out.println("get serverList failed");
		}
		
		//get list of server(not include detail)
		List<? extends Server> partList = os.compute().servers().list(false);
		if (partList.size() > 0) {
			System.out.println("get partList success, size = " + partList.size());
		} else {
			System.out.println("get partList failed");
		}
		
		//get server with parameter
		Map<String,String> filteringParams = new HashMap<String, String>();
		filteringParams.put("status", "ACTIVE");
		List<? extends Server> filterList = os.compute().servers().list(filteringParams);
		if (filterList.size() > 0) {
			System.out.println("get filterList success, size = " + filterList.size());
		} else {
			System.out.println("get filterList failed");
		}
		
		//reboot server
		ActionResponse repReboot = os.compute().servers().reboot(server.getId(), RebootType.HARD);
		os.compute().servers().waitForServerStatus(server.getId(), Status.ACTIVE, 10, TimeUnit.MINUTES);
		if (repReboot.isSuccess()) {
			System.out.println("Reboot the server success");
		} else {
			System.out.println("Reboot the server failed");
		}
		
		//rebuild server
		ActionResponse repRebuild = os.compute().servers().rebuild(server.getId(), RebuildOptions.create().image(newimageid));
		os.compute().servers().waitForServerStatus(server.getId(), Status.ACTIVE, 10, TimeUnit.MINUTES);
		if (repRebuild.isSuccess()) {
			System.out.println("Rebuild the server success");
		} else {
			System.out.println("Rebuild the server failed");
		}
		
		//update server
		Server updatedServer = os.compute().servers().update(server.getId(), ServerUpdateOptions.create().name("test-name2"));
		if (null != updatedServer) {
			System.out.println("updatedServer success, updated name = " + updatedServer.getName());
		} else {
			System.out.println("updatedServer failed");
		}
		
		//get server metadata
		Map<String,String> meta = os.compute().servers().getMetadata(server.getId());
		if (null != meta) {
			System.out.println("getMetadata success, value= " + meta.values());
		} else {
			System.out.println("getMetadata failed");
		}
		
		//update server metadata
		Map<String,String> newMetadata = new HashMap<String, String>(); 
		newMetadata.put("Group", "MyGroup");
		Map<String,String> updatedMeta = os.compute().servers().updateMetadata(server.getId(), newMetadata);
		if (null != updatedMeta) {
			System.out.println("updatedMeta success, value= " + updatedMeta.values());
		} else {
			System.out.println("updatedMeta failed");
		}
		
		//delete server metadata
		ActionResponse repDeleteMeta = os.compute().servers().deleteMetadataItem(server.getId(), "Group");
		if (repDeleteMeta.isSuccess()) {
			Map<String,String> delMeta = os.compute().servers().getMetadata(server.getId());
			System.out.println("deleteMetadataItem success, value = " + delMeta.values());
		} else {
			System.out.println("deleteMetadataItem failed");
		}
		
		//resize server
		os.compute().servers().action(server.getId(), Action.STOP);
		os.compute().servers().waitForServerStatus(server.getId(), Status.SHUTOFF, 10, TimeUnit.MINUTES);
		ActionResponse repResize = os.compute().servers().resize(server.getId(), newflavorid);
		if (repResize.isSuccess()) {
			os.compute().servers().waitForServerStatus(server.getId(), Status.VERIFY_RESIZE, 10, TimeUnit.MINUTES);
			System.out.println("Resize the server success");
		} else{
			System.out.println("Resize the server failed");
		}
		
		//confirm resize
		ActionResponse repconfirmResize = os.compute().servers().confirmResize(server.getId());
		if (repconfirmResize.isSuccess()) {
			os.compute().servers().waitForServerStatus(server.getId(), Status.VERIFY_RESIZE, 10, TimeUnit.MINUTES);
			System.out.println("ConfirmResize the server success");
		} else{
			System.out.println("ConfirmResize the server failed");
		}
		
		//revert Resize
		ActionResponse repRevertResize = os.compute().servers().revertResize(server.getId());
		if (repRevertResize.isSuccess()) {
			os.compute().servers().waitForServerStatus(server.getId(), Status.VERIFY_RESIZE, 10, TimeUnit.MINUTES);
			System.out.println("RevertResize the server success");
		} else{
			System.out.println("RevertResize the server failed");
		}

		//create image
		String snapshotId = os.compute().servers().createSnapshot(server.getId(), "Clean State Snapshot");
		if (null != snapshotId) {
			System.out.println("createSnapshot success, id = " + snapshotId);
		} else {
			System.out.println("createSnapshot server failed");
		}
		
		//delete server
		ActionResponse repDeleteServer = os.compute().servers().delete(server.getId());
		if (repDeleteServer.isSuccess()) {
			System.out.println("DeleteServer success");
		} else {
			System.out.println("DeleteServer failed");
		}
	}
}