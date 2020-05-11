<#assign
known = Session.SPRING_SECURITY_CONTEXT??
>

<#if known>
    <#assign
    user = Session.SPRING_SECURITY_CONTEXT.authentication.principal
    name = user.getName()
    avatar = user.getUserPicture()
    />
<#else>
    <#assign
    email = "unknown"
    isAdmin = false
    >
</#if>