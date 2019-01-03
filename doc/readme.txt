#简单的创建参考

1.创建project
File - new - project - maven(不选中maven) - GroupId:com.test|ArtifactId:myright - Next ....  删除创建的project下src文件夹

2.创建Module（myright-core）
选中当前 - new - Module - Next - GroupId：使用默认com.test|ArtifactId:myright-core - Next -(修改module name:myright-core,与上一步填的ArtifactId保持一致且content root,location跟着变化) -  Finish 删除创建的myright-core下src文件夹
myright-core-persist(com.test|ArtifactId:myright-core-persist   [module name:myright-core-persist  content root：地址后面加\persist location：\persist] )
myright-core-pojo
myright-core-service
myright-core-rest (该目录下src文件夹删除)
    myright-core-rest-hybrid(web项目)
    选中当前 - new - Module(选中Create from archetype| apache:maven-archetype-webapp) - Next - ArtifactId:myright-core-rest-hybrid - Next - (设置maven home和setting file) - 修改module name:myright-core-rest-hybrid - Finish


3.创建Module（myright-infra）
选中当前 - new - Module - Next - GroupId：使用默认com.test|ArtifactId:myright-infra - Next -(修改module name:myright-infra) -  Finish 删除创建的myright-infra下src文件夹
myright-infra-common
myright-infra-mq
myright-infra-cloud
myright-infra-security





附：
可以参考http://www.cnblogs.com/wangmingshun/p/6383576.html

注意：
ArtifactId和module name保持一致一定要规范如myright-core-persist，但保存的代码文件夹和root文件夹不一定（两个文件夹名称要一样），可以写成D:\...\persist简单格式


























