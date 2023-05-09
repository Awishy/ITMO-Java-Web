<#-- @ftlvariable name="user" type="ru.itmo.wp.model.domain.User" -->
<#-- @ftlvariable name="articles" type="ru.itmo.wp.model.domain.Article[]" -->
<#import "commons.ftlh" as c>

<@c.page>
    <div class="datatable myArticles">
        <div class="caption">My articles</div>
        <table>
            <thead>
            <tr>
                <th>Id</th>
                <th>Title</th>
                <th>Hide/Show</th>
            </tr>
            </thead>
            <tbody>
            <#list articles as article>
                <#if user.id == article.userId>
                    <tr>
                        <td class="article-id">${article.id}</td>
                        <td>${article.title}</td>
                        <td>
                            <button class="hideOrShow" article-id="${article.id}">
                                <#if article.hidden>
                                    Show
                                <#else>
                                    Hide
                                </#if>
                            </button>
                        </td>
                    </tr>
                </#if>
            </#list>
            </tbody>
        </table>
    </div>

    <script>
        $(function () {
            const $button = $(".myArticles .hideOrShow");
            $button.click(function () {
                if ($(this).text().trim() === "Hide") {
                    makePost("setHidden", {articleId: $(this).attr("article-id")})
                    $(this).text("Show")
                } else {
                    makePost("unsetHidden", {articleId: $(this).attr("article-id")})
                    $(this).text("Hide")
                }
            });
        })
    </script>
</@c.page>
