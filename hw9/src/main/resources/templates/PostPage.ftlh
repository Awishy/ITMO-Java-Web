<#-- @ftlvariable name="comment" type="ru.itmo.wp.domain.Comment" -->
<#-- @ftlvariable name="post" type="ru.itmo.wp.domain.Post" -->
<#import "commons.ftlh" as c>

<@c.page>
    <#if post??>
        <@c.post post=post/>
        <#if user??>
            <div class="comment-form form">
                <div class="header">Write Comment</div>
                <div class="body">
                    <form method="post" action="">
                        <input type="hidden" name="action" value="writeComment"/>
                        <div class="field">
                            <div class="name">
                                <label for="text">Text</label>
                            </div>
                            <div class="value">
                                <textarea id="text" name="text">${comment.text!}</textarea>
                            </div>
                            <@c.error "comment.text"/>
                        </div>
                        <div class="button-field">
                            <input type="submit" value="Comment">
                        </div>
                    </form>
                </div>
            </div>
        </#if>
        <div style="margin: 0.6rem 0">Comments:</div>
        <#list post.comments as comment>
            <@c.comment comment/>
        <#else>
            No comments.
        </#list>
    <#else>
        No such post.
    </#if>
</@c.page>
