<#import "./common.ftlh" as c>
<#import "./post.ftlh" as post_template>

<@c.page>
    <#list posts?reverse as p>
        <@post_template.show_post p false/>
    </#list>
</@c.page>
