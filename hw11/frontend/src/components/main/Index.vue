<template>
    <main>
        <Post v-for="post in posts" :post="post" :key="post.id"/>
    </main>
</template>

<script>
import Post from "@/components/post/Post.vue";

export default {
    name: "Index",
    components: {Post},
    props: ["posts", "users"]
}
</script>

<style scoped>

</style>
