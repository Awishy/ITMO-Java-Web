<#macro page>
    <!DOCTYPE html>
    <html lang="ru">
    <head>
        <meta charset="UTF-8">
        <title>Codeforces</title>
        <link rel="stylesheet" href="/css/normalize.css">
        <link rel="stylesheet" href="/css/style.css">
        <link rel="icon" href="/favicon.ico">
    </head>
    <body>
    <@header/>
    <div class="middle">
        <@sidebar/>
        <main>
            <#nested/>
        </main>
    </div>
    <@footer/>
    </body>
    </html>
</#macro>

<#macro header>
    <header>
        <a href="/" class="logo"><img alt="Codeforces" title="Codeforces"
                                      src="/img/codeforces-logo-with-telegram.png"></a>
        <div class="languages">
            <a href="#"><img alt="In English" title="In English"
                             src="/img/gb.png"></a>
            <a href="#"><img alt="In Russian" title="In Russian"
                             src="/img/ru.png"></a>
        </div>
        <div class="enter-or-register-box">
            <#if user??>
                <@userlink user true/>
                |
                <a href="/logout">Logout</a>
            <#else>
                <a href="/enter">Enter</a>
                |
                <a href="/register">Register</a>
            </#if>
        </div>
        <nav>
            <#assign items=[
            {"href": "/index", "name": "Home"},
            {"href": "/misc/help", "name": "Help"},
            {"href": "/contests", "name": "Contests"},
            {"href": "/users", "name": "Users"}
            ]/>
            <ul>
                <#list items as item>
                    <li>
                        <a <#if uri! == item.href>
                            class="active-page"
                        </#if> href="${item.href}">
                            ${item.name}</a>
                    </li>
                </#list>
            </ul>
        </nav>
    </header>
</#macro>

<#macro sidebar>
    <aside>
        <#list posts?reverse as p>
            <@sidebarPost p/>
        </#list>
    </aside>
</#macro>

<#macro sidebarPost p>
<#-- @ftlvariable name="p" type="ru.itmo.web.hw4.model.Post" -->
    <section>
        <div class="header">Post #${p.id}</div>
        <div class="body">
            <@shortenedText p.text 350/>
        </div>
        <div class="footer">
            <a href="/post?post_id=${p.id}">View all</a>
        </div>
    </section>
</#macro>

<#macro footer>
    <footer>
        2010-2021 &copy; by Mike Mirzayanov
    </footer>
</#macro>

<#macro userlink user nameOnly=false>
    <link rel="stylesheet" href="/css/colored-user.css">
<#-- @ftlvariable name="user" type="ru.itmo.web.hw4.model.User" -->
    <#if nameOnly>
        <a href="/user?handle=${user.handle}">${user.name}</a>
    <#else>
        <a class="colored-user user-${user.color}" href="/user?handle=${user.handle}">${user.handle}</a>
    </#if>
</#macro>

<#function findBy items key value>
    <#list items as item>
        <#if item[key] == value>
            <#return item/>
        </#if>
    </#list>
</#function>

<#function countBy items key value>
    <#assign result = 0/>
    <#list items as item>
        <#if item[key] == value>
            <#assign result = result + 1/>
        </#if>
    </#list>
    <#return result/>
</#function>

<#macro shortenedText text len>
    <#if text?length &gt; len>
        <p>${text?substring(0, 350)}...</p>
    <#else>
        <p>${text}</p>
    </#if>
</#macro>
