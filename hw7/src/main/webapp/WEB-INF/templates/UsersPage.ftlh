<#-- @ftlvariable name="user" type="ru.itmo.wp.model.domain.User" -->
<#-- @ftlvariable name="users" type="ru.itmo.wp.model.domain.User[]" -->
<#import "commons.ftlh" as c>

<@c.page>
    <div class="users datatable">
        <div class="caption">User</div>
        <table>
            <thead>
            <tr>
                <th>Id</th>
                <th>Login</th>
                <#if user?? && user.admin>
                    <th>Admin</th>
                </#if>
            </tr>
            </thead>
            <tbody>
            <template>
                <tr>
                    <td class="user_id"></td>
                    <td class="user_login"></td>
                    <#if user?? && user.admin>
                        <td class="user_admin">
                            <button></button>
                        </td>
                    </#if>
                </tr>
            </template>
            <tr class="noData">
                <td colspan="3">
                    No data
                </td>
            </tr>
            </tbody>
        </table>
    </div>

    <script>
        $(function () {
            const $noData = $(".users .noData");
            $noData.find("td").text("Loading...");

            makePost("findAll", {},
                function (response) {
                    response["users"].forEach(function (user) {
                        $noData.hide();
                        const $tr = $($(".users template").prop("content")).clone();
                        $tr.find(".user_id").text(user["id"]);
                        $tr.find(".user_login").text(user["login"]);
                        const $button = $tr.find(".user_admin button");
                        $button.text(user["admin"] ? "Disable" : "Enable");
                        $button.attr("data-userId", user["id"]);
                        $tr.find("tr").attr("data-userId", user["id"]);
                        $(".users tbody").append($tr);
                    });
                    $(".users tr").click(function () {
                        makePost("findUser", {userId: $(this).attr("data-userId")},
                            function (response) {
                                notify(response["user"].creationTime);
                            })
                    });
                    const $button = $(".user_admin button");
                    $button.click(function () {
                        if ($(this).text().trim() === "Enable") {
                            makePost("setAdmin", {userId: $(this).attr("data-userId")})
                            $(this).text("Disable")
                        } else {
                            makePost("unsetAdmin", {userId: $(this).attr("data-userId")})
                            $(this).text("Enable")
                        }
                    });
                });
        })
    </script>
</@c.page>
