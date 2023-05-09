<#-- @ftlvariable name="currentUser" type="ru.itmo.wp.domain.User" -->
<#import "commons.ftlh" as c>

<@c.page>
    <div class="user-data">
        <#if currentUser??>
            <div>Id: ${currentUser.id}</div>
            <div>Login: ${currentUser.login}</div>
            <div>Creation: ${currentUser.creationTime}</div>
        <#else>
            No such user.
        </#if>
    </div>
</@c.page>
