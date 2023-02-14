package ${_configCommand.packageMainPath}.shared.constants;

/**
 * 项目常量定义
 * create time: ${_currentTime}
 */
public interface ProjectConstants {
    /**
     * 当前微服务应用标识ID, 可用于接口调用出错时返回给调用方来源服务ID等
     * APP_ID 需要唯一标识, 不能与其它微服务应用ID相同而产生冲突
     */
     int APP_ID = 60000;  //TODO 请修改该APP_ID的值, 需要唯一标识, 不能与其它微服务应用ID相同而产生冲突
}