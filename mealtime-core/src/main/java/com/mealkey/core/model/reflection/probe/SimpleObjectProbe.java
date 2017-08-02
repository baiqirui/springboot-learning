package com.mealkey.core.model.reflection.probe;


/**
 * 提供访问对象的成员的功能。
 * @author yidi
 */
public class SimpleObjectProbe implements ObjectProbe
{
    private Object obj;
    
    private ProbeInfo probeInfo;

    /**
     * 初始化 SimpleObjectProbe 类的新实例。
     * @param obj 对象。
     */
    public SimpleObjectProbe(Object obj)
    {
        this.obj = obj;
        probeInfo = TypeUtil.getCachedProbeInfo(obj.getClass());
    }
    
    /**
     * 获取对象的 ProbeInfo。
     * @return 对象的 ProbeInfo。
     */
    public ProbeInfo getProbeInfo()
    {
        return probeInfo;
    }
    
    /**
     * 获取具有指定名称的值。
     * @param name 名称。
     * @return 具有指定名称的值。
     */
	public Object get(String name)
    {
        ProbeMember member = probeInfo.getMember(name);
        return member.getValue(obj);
    }
    
    /**
     * 设置指定名称的值。
     * @param name 名称。
     * @param value 值。
     */
    public void set(String name, Object value)
    {
        ProbeMember member = probeInfo.getMember(name);
        member.setValue(obj,value);
    }
}
