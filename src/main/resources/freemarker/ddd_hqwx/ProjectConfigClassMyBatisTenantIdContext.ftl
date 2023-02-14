package ${_configCommand.packageMainPath}.${_configClass.configClassPackagePath}.mybatisplus;

/**
 * create time: ${_currentTime}
 */
public class TenantIdContext {

	private static final InheritableThreadLocal<Long> SCH_ID_THREAD_LOCAL = new InheritableThreadLocal();

	private TenantIdContext() {
	}

	public static void setSchId(Long schId) {
		if (schId == null) {
			return;
		}
		SCH_ID_THREAD_LOCAL.set(schId);
	}

	static Long getSchId() {
		return SCH_ID_THREAD_LOCAL.get();
	}

	public static void clear() {
		SCH_ID_THREAD_LOCAL.remove();
	}
}
