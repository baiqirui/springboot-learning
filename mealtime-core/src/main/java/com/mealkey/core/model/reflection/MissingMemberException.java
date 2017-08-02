package com.mealkey.core.model.reflection;


import com.mealkey.core.exception.ExceptionBase;
import com.mealkey.core.util.resources.ResourceUtil;

public class MissingMemberException extends ExceptionBase
{
    private static final long serialVersionUID = 1L;
    
    private String memberName;

    public MissingMemberException(String memberName)
    {
        this(ResourceUtil.getString(MissingMemberException.class,
            "MissingMember"),memberName);
    }

    public MissingMemberException(String message, String memberName) 
    {
        super(message);
        this.memberName = memberName;
    }

    @Override
    public String getMessage()
    {
        if ((null != memberName) && (memberName.length() != 0))
        {
            return (super.getMessage() + "\r\n" 
                + ResourceUtil.getString(MissingMemberException.class,
                "MissingMember_Name",new String[] {memberName}));
        }
        return super.getMessage();
    }

    public String getMemberName()
    {
        return memberName;
    }
}
