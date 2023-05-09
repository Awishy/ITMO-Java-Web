<template>
    <main>
        <Post :post="post" :userLogin="getUserLogin(post.userId)"/>
        <div v-if="userId" class="comment-form form">
            <div class="header">Write a comment</div>
            <div class="body">
                <form @submit.prevent="onWriteComment">
                    <input type="hidden" name="action" value="writeComment"/>
                    <div class="field">
                        <div class="name">
                            <label for="text">Text</label>
                        </div>
                        <div class="value">
                            <textarea id="text" name="text" v-model="text"></textarea>
                        </div>
                        <div class="field error">{{ error }}</div>
                    </div>
                    <div class="button-field">
                        <input type="submit" value="Comment">
                    </div>
                </form>
            </div>
        </div>
        <p>Comments:</p>
        <article v-for="comment in Object.values(post.comments).reverse()" :key="comment.id" class="comment">
            <div class="information">By {{ getUserLogin(comment.userId) }}</div>
            <div class="body">{{ comment.text }}</div>
        </article>
    </main>
</template>

<script>

import Post from "@/components/post/Post.vue";

export default {
    data: function () {
        return {
            text: "",
            error: ""
        };
    },
    name: "PostPage",
    components: {Post},
    props: ["userId", "post", "users"],
    methods: {
        onWriteComment: function () {
            this.error = "";
            if (this.userId) {
                if (!this.text || this.text.length < 5 || !this.text.replace(/\s/g, '').length) {
                    this.error = "Text is too short";
                } else if (this.text.length > 200) {
                    this.error = "Text is too long";
                } else {
                    let id = 0;
                    if (Object.keys(this.post.comments).length !== 0) {
                        id = Math.max(...Object.keys(this.post.comments)) + 1;
                    }
                    this.$root.$set(this.post.comments, id, {
                        id, text: this.text, userId: this.userId
                    });
                    this.text = "";
                }
            } else {
                this.error = "No access";
            }
        },
        getUserLogin: function (userId) {
            const users = Object.values(this.users).filter(u => u.id === userId);
            return users[0].login;
        }
    },
}
</script>

<style scoped>

</style>