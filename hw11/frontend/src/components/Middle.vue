<template>
    <div class="middle">
        <Sidebar :posts="sidebarPosts"/>
        <main>
            <Index v-if="page === 'Index'" :posts="viewPosts" :users="users"/>
            <Register v-if="page === 'Register'"/>
            <Enter v-if="page === 'Enter'"/>
            <Users v-if="page === 'Users'" :users="users"/>
            <PostPage v-if="page === 'PostPage'" :post="data.post" :user="user"/>
            <WritePost v-if="page === 'WritePost'"/>
        </main>
    </div>
</template>

<script>
import Sidebar from "./sidebar/Sidebar";
import Index from "./main/Index";
import Enter from "./main/Enter";
import Register from "./page/Register.vue";
import Users from "@/components/page/Users.vue";
import PostPage from "@/components/page/PostPage.vue";
import WritePost from "@/components/page/WritePost.vue";

export default {
    name: "Middle",
    data: function () {
        return {
            page: "Index",
            data: {}
        }
    },
    components: {
        WritePost,
        PostPage,
        Users,
        Register,
        Enter,
        Index,
        Sidebar
    },
    props: ["users", "posts", "user"],
    computed: {
        viewPosts: function () {
            return Object.values(this.posts).sort((a, b) => b.id - a.id);
        },
        sidebarPosts: function () {
            return this.viewPosts.slice(0, 2);
        }
    }, beforeCreate() {
        this.$root.$on("onChangePage", (page, data, updateData) => {
            if (updateData) {
                this.$root.$emit("onUpdatePosts");
                this.$root.$emit("onUpdateUsers");
            }
            this.page = page;
            this.data = data;
        })
    }
}
</script>

<style scoped>

</style>
