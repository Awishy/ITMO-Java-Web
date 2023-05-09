<#-- @ftlvariable name="login" type="java.lang.String" -->
<#-- @ftlvariable name="password" type="java.lang.String" -->
<#-- @ftlvariable name="error" type="java.lang.String" -->
<#import "commons.ftlh" as c>

<@c.page>
    <div class="form-box article-box">
        <div class="header">Write an article</div>
        <div class="body">
            <form method="post" action="">
                <input type="hidden" name="action" value="saveArticle"/>
                <div class="field">
                    <div class="name">
                        <label for="title">Title</label>
                    </div>
                    <div class="value">
                        <input id="title" name="title"/>
                    </div>
                </div>
                <div class="field">
                    <div class="name">
                        <label for="text">Text</label>
                    </div>
                    <div class="value">
                        <textarea id="text" name="text" rows="7" cols="33"></textarea>
                    </div>
                </div>
                <div class="error"></div>
                <div class="button-field">
                    <input type="submit" value="Submit">
                </div>
            </form>
        </div>
    </div>

    <script>
        $(function () {
            $(".article-box form").submit(function () {
                const $title = $(this).find("input[name='title']");
                const $text = $(this).find("textarea[name='text']");
                const $error = $(this).find(".error");
                makePost("saveArticle",
                    {
                        title: $title.val(),
                        text: $text.val()
                    },
                    function (response) {
                        if (response["error"]) {
                            $error.text(response["error"]);
                        } else {
                            $title.val('')
                            $text.val('')
                            $error.remove()
                        }
                    }
                )
                return false;
            });
        })
    </script>
</@c.page>
