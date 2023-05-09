<#-- @ftlvariable name="user_id" type="java.lang.Long" -->
<#-- @ftlvariable name="handle" type="java.lang.String" -->
<#import "./common.ftlh" as c>

<@c.page>
    <link rel="stylesheet" href="/css/user.css">
<#-- @ftlvariable name="u" type="ru.itmo.web.hw4.model.User" -->
    <#if handle?has_content>
        <#assign u = c.findBy(users, "handle", handle)!/>
    </#if>
    <#if !u?has_content && user_id?has_content>
        <#assign u = c.findBy(users, "id", user_id)!/>
    </#if>
    <#if u?has_content>
        <div class="user-box">
            <div class="user-info">
                <div class="user-name">
                    <@c.userlink u/>
                </div>
                <ul>
                    <li>id: ${u.id}</li>
                    <li>handle: ${u.handle}</li>
                    <li>name: ${u.name}</li>
                    <#assign posts_count = c.countBy(posts, "user_id", u.id)/>
                    <li>posts count: <a href="/posts?user_id=${u.id}">${posts_count}</a></li>
                </ul>
            </div>
            <img src="/img/no-title.jpg" alt="Profile image"/>
        </div>
        <nav class="previous-next-user">
            <#assign previousUser = findPreviousBy(users, "handle", u.handle)!/>
            <#assign nextUser = findNextBy(users, "handle", u.handle)!/>
            <@navigation previousUser "←"/>
            <@navigation nextUser "→"/>
        </nav>
    <#else>
        No such user
    </#if>
</@c.page>

<#macro navigation user symbol>
<#-- @ftlvariable name="user" type="ru.itmo.web.hw4.model.User" -->
    <#if user?has_content>
        <a href="/user?handle=${user.handle}">${symbol}</a>
    <#else>
        <a>${symbol}</a>
    </#if>
</#macro>

<#function findPreviousBy items key value>
    <#assign prev = ""/>
    <#list items as item>
        <#if item[key] == value>
            <#return prev/>
        </#if>
        <#assign prev = item/>
    </#list>
</#function>

<#function findNextBy items key value>
    <#assign prev = ""/>
    <#list items as item>
        <#if prev == value>
            <#return item/>
        </#if>
        <#assign prev = item[key]/>
    </#list>
</#function>