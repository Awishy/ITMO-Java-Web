<template>
    <main>
        <Post v-for="post in posts" :post="post" :userLogin="getUserLogin(post.userId)" :key="post.id"/>
    </main>
</template>

<script>
import Post from "@/components/post/Post.vue";

export default {
    name: "Index",
    components: {Post},
    props: ["posts", "users"],
    methods: {
        getUserLogin: function(userId) {
            const users = Object.values(this.users).filter(u => u.id === userId);
            return users[0].login;
        }
    }
}
</script>

<style scoped>

</style>
