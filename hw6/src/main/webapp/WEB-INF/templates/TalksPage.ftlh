<#-- @ftlvariable name="messages" type="java.util.List" -->
<#-- @ftlvariable name="message" type="ru.itmo.wp.model.domain.Talk" -->
<#-- @ftlvariable name="users" type="java.util.List" -->
<#-- @ftlvariable name="user" type="ru.itmo.wp.model.domain.User" -->
<#-- @ftlvariable name="text" type="java.lang.String" -->
<#-- @ftlvariable name="targetUser" type="ru.itmo.wp.model.domain.User" -->
<#-- @ftlvariable name="error" type="java.lang.String" -->
<#-- @ftlvariable name="userService" type="ru.itmo.wp.model.service.UserService" -->
<#import "commons.ftlh" as c>

<@c.page>
    <div class="form-box message-box">
        <div class="header">Send message</div>
        <div class="body">
            <form method="post" action="">
                <input type="hidden" name="action" value="sendMessage"/>
                <div class="field">
                    <div class="name">
                        <label for="targetLogin">Send to</label>
                    </div>
                    <div class="value">
                        <select id="targetLogin" name="targetLogin">
                            <option value="">--Please choose a user--</option>
                            <#list users! as user>
                                <option value="${user.getLogin()}">${user.getLogin()}</option>
                            </#list>
                        </select>
                    </div>
                </div>
                <div class="field">
                    <div class="name">
                        <label for="text">Message</label>
                    </div>
                    <div class="value">
                        <textarea id="text" name="text" rows="7" cols="33">${text!}</textarea>
                    </div>
                </div>
                <#if error??>
                    <div class="error">
                        ${error!}
                    </div>
                </#if>
                <div class="button-field">
                    <input type="submit" value="Send">
                </div>
            </form>
        </div>
    </div>
    <div class="datatable">
        <div class="caption">Messages</div>
        <table>
            <thead>
            <tr>
                <th>Id</th>
                <th>SourceUser</th>
                <th>TargetUser</th>
                <th style="width:40%">Message</th>
                <th>Date</th>
            </tr>
            </thead>
            <tbody>
            <#list messages as message>
                <tr>
                    <td>${message.id}</td>
                    <td>${userService.find(message.sourceUserId).getLogin()}</td>
                    <td>${userService.find(message.targetUserId).getLogin()}</td>
                    <td>${message.text}</td>
                    <td>${message.creationTime}</td>
                </tr>
            </#list>
            </tbody>
        </table>
    </div>
</@c.page>
