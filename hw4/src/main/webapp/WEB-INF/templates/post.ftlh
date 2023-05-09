<#-- @ftlvariable name="post_id" type="java.lang.Long" -->

<#import "./common.ftlh" as c>

<@c.page>
    <link rel="stylesheet" href="/css/user.css">
<#-- @ftlvariable name="p" type="ru.itmo.web.hw4.model.Post" -->
    <#if post_id?has_content>
        <#assign p = c.findBy(posts, "id", post_id)!/>
    </#if>
    <#if p?has_content>
        <@show_post p true/>
    <#else>
        No such post
    </#if>
</@c.page>

<#macro show_post p entire>
    <link rel="stylesheet" href="/css/post.css">
    <article>
        <div class="title">
            <@postLink p p.title/>
        </div>
        <div class="information">
            <#assign u = c.findBy(users, "id", p.user_id)!/>
            By <@c.userlink u/>
        </div>
        <div class="body">
            <#if !entire>
                <@c.shortenedText p.text 350/>
            <#else>
                <p>${p.text}</p>
            </#if>
        </div>
        <ul class="attachment">
            <li>Announcement of <a href="#">Codeforces Round #510 (Div. 1)</a></li>
            <li>Announcement of <a href="#">Codeforces Round #510 (Div. 2)</a></li>
        </ul>
        <div class="footer">
            <div class="left">
                <img src="img/voteup.png" title="Vote Up" alt="Vote Up"/>
                <span class="positive-score">+173</span>
                <img src="img/votedown.png" title="Vote Down" alt="Vote Down"/>
            </div>
            <div class="right">
                <img src="img/date_16x16.png" title="Publish Time" alt="Publish Time"/>
                2 days ago
                <img src="img/comments_16x16.png" title="Comments" alt="Comments"/>
                <a href="#">68</a>
            </div>
        </div>
    </article>
</#macro>

<#macro postLink p text><a href="post?post_id=${p.id}">${text}</a></#macro>