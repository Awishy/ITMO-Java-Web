<#-- @ftlvariable name="user_id" type="java.lang.Long" -->
<#-- @ftlvariable name="u" type="ru.itmo.web.hw4.model.User" -->
<#import "./common.ftlh" as c>
<#import "./post.ftlh" as post_template>

<@c.page>
    <#if user_id?has_content>
        <#assign u = c.findBy(users, "id", user_id)!>
    </#if>
    <#if !u?has_content>
        No such post.
    <#else>
        <h1 style="font-size: 1.3rem">Posts from <@c.userlink u/>:</h1>
        <#if c.countBy(posts, "user_id", user_id) != 0>
            <#list posts?reverse as p>
                <#if p.user_id == user_id>
                    <@post_template.show_post p false/>
                </#if>
            </#list>
        <#else>
            No posts from this user.
        </#if>
    </#if>
</@c.page>
