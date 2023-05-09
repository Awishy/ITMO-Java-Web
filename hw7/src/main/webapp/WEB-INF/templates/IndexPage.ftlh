<#import "commons.ftlh" as c>

<@c.page>
    <div class="articles">
        <p class="noData"></p>
        <template>
            <article>
                <div class="title"></div>
                <div class="information"></div>
                <div class="body"></div>
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
                        <a class="publishTime"></a>
                        <img src="img/comments_16x16.png" title="Comments" alt="Comments"/>
                        <a href="#">68</a>
                    </div>
                </div>
            </article>
        </template>
    </div>

    <script>
        $(function () {
            const $noData = $(".articles .noData");
            $noData.text("Welcome to Codeforces!");
            makePost("findAllArticles", {},
                function (response) {
                    const loginById = response["loginById"];
                    response["articles"].forEach(function (article) {
                        if (article["hidden"])
                            return;
                        $noData.hide();
                        const $article = $($(".articles template").prop("content")).clone();
                        $article.find(".title").text(article["title"]);
                        $article.find(".body").text(article["text"]);
                        $article.find(".information").text("By " + loginById[article["userId"]]);
                        $article.find(".publishTime").append(article["creationTime"])
                        $(".articles").append($article);
                    });
                });
        })
    </script>
</@c.page>
