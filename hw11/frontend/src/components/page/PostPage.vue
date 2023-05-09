<template>
    <main v-if="currentPost">
        <Post :post="currentPost"/>
        <div v-if="user" class="comment-form form">
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
        <article v-for="comment in this.currentPost.comments" :key="comment.id" class="comment">
            <div class="information">By {{ comment.user.login }}</div>
            <div class="body">{{ comment.text }}</div>
        </article>
    </main>
</template>

<script>

import Post from "@/components/post/Post.vue";
import axios from "axios";

export default {
    data: function () {
        return {
            currentPost: null,
            text: "",
            error: ""
        };
    },
    name: "PostPage",
    components: {Post},
    props: ["post", "user"],
    methods: {
        onWriteComment: function () {
            this.error = "";
            this.$root.$emit("onWriteComment", this.currentPost, this.text);
            this.$root.$emit("onUpdatePost", this.currentPost.id);
        },
    },
    beforeCreate() {
        this.$root.$on("onUpdatePost", (id) => {
            axios.get("/api/1/posts/" + id).then(response => {
                this.currentPost = response.data;
            });
        })
    },
    created() {
        this.currentPost = this.post;
        this.$root.$emit("onUpdatePost", this.currentPost.id);
        this.$root.$on("onWriteCommentValidationError", error => this.error = error);
    }
}
</script>

<style scoped>

</style>
