<#import "./common.ftlh" as c>

<@c.page>
    <link rel="stylesheet" href="/css/users.css">
    <div class="datatable">
        <div class="caption">Submissions</div>
        <table>
            <thead>
            <tr>
                <th>Id</th>
                <th>Handle</th>
                <th>Name</th>
            </tr>
            </thead>
            <tbody>
            <#list users as u>
                <tr>
                    <td>${u.id}</td>
                    <td><@c.userlink u/></td>
                    <td>${u.name}</td>
                </tr>
            </#list>
            </tbody>
        </table>
    </div>
</@c.page>
