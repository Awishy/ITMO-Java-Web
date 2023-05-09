<template>
    <article>
        <div class="title" @click.prevent="onPostPage">{{ post.title }}</div>
        <div class="information">By {{ post.user.login }}</div>
        <div class="body">
            <p>{{ post.text }}</p>
        </div>
        <div class="footer">
            <div class="left">
                <img src="../../assets/img/voteup.png" title="Vote Up" alt="Vote Up"/>
                <span class="positive-score">+173</span>
                <img src="../../assets/img/votedown.png" title="Vote Down" alt="Vote Down"/>
            </div>
            <div class="right">
                <img src="../../assets/img/date_16x16.png" title="Publish Time" alt="Publish Time"/>
                {{ (new Date(Date.parse(post.creationTime)).toLocaleDateString()) }}
                <img src="../../assets/img/comments_16x16.png" title="Comments" alt="Comments"/>
                <a href="#" @click.prevent="onPostPage">{{ post.comments.length }}</a>
            </div>
        </div>
    </article>
</template>

<script>
export default {
    name: "Post",
    props: ["post"],
    methods: {
        onPostPage: function () {
            this.$root.$emit("onChangePage", "PostPage", {post: this.post});
        }
    }
}
</script>

<style scoped>
section {
    margin-bottom: 1rem;
}
</style>