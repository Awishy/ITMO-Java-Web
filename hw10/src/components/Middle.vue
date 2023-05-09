<template>
    <div class="middle">
        <Sidebar :posts="sidebarPosts"/>
        <main>
            <Index v-if="page === 'Index'" :posts="viewPosts" :users="users"/>
            <PostPage v-if="page === 'PostPage'" :userId="userId" :post="data.post" :users="users"/>
            <Enter v-if="page === 'Enter'"/>
            <Register v-if="page === 'Register'"/>
            <Users v-if="page === 'Users'" :users="users"/>
            <WritePost v-if="page === 'WritePost'"/>
            <EditPost v-if="page === 'EditPost'"/>
        </main>
    </div>
</template>

<script>
import Sidebar from "./sidebar/Sidebar";
import Index from "./page/Index";
import Enter from "./page/Enter";
import WritePost from "./page/WritePost";
import EditPost from "./page/EditPost";
import Register from "@/components/page/Register.vue";
import Users from "@/components/page/Users.vue";
import PostPage from "@/components/page/PostPage.vue";

export default {
    name: "Middle",
    data: function () {
        return {
            page: "Index",
            data: {}
        }
    },
    components: {
        PostPage,
        Users,
        Register,
        WritePost,
        Enter,
        Index,
        Sidebar,
        EditPost
    },
    props: ["posts", "users", "userId"],
    computed: {
        viewPosts: function () {
            return Object.values(this.posts).sort((a, b) => b.id - a.id);
        },
        sidebarPosts: function () {
            return this.viewPosts.slice(0, 2);
        }
    }, beforeCreate() {
        this.$root.$on("onChangePage", (page, data) => {
            this.page = page;
            this.data = data;
        })
    }
}
</script>

<style scoped>

</style>
