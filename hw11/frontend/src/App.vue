<template>
    <div id="app">
        <Header :user="user"/>
        <Middle :users="users" :posts="posts" :user="user"/>
        <Footer/>
    </div>
</template>

<script>
import Header from "./components/Header";
import Middle from "./components/Middle";
import Footer from "./components/Footer";
import axios from "axios"

export default {
    name: 'App',
    components: {
        Footer,
        Middle,
        Header
    },
    data: function () {
        return {
            user: null,
            users: [],
            posts: []
        }
    },
    methods: {
        updateUsers: function () {
            axios.get("/api/1/users").then(response => {
                this.users = response.data;
            });
        },
        updatePosts: function () {
            axios.get("/api/1/posts").then(response => {
                this.posts = response.data;
            });
        }
    },
    beforeMount() {
        if (localStorage.getItem("jwt") && !this.user) {
            this.$root.$emit("onJwt", localStorage.getItem("jwt"));
        }

        this.updateUsers();

        this.updatePosts();
    },
    beforeCreate() {
        this.$root.$on("onUpdateUsers", () => {
            this.updateUsers();
        })

        this.$root.$on("onUpdatePosts", () => {
            //alert()
            this.updatePosts();
        })

        this.$root.$on("onEnter", (login, password) => {
            axios.post("/api/1/jwt", {
                    login, password
            }).then(response => {
                localStorage.setItem("jwt", response.data);
                this.$root.$emit("onJwt", response.data);
            }).catch(error => {
                this.$root.$emit("onEnterValidationError", error.response.data);
            });
        });

        this.$root.$on("onRegister", (login, name, password) => {
            axios.post("/api/1/users/register", {
                login, name, password
            }).then(response => {
                localStorage.setItem("jwt", response.data);
                this.$root.$emit("onJwt", response.data);
                this.updateUsers();
            }).catch(error => {
                this.$root.$emit("onRegisterValidationError", error.response.data);
            });
        });

        this.$root.$on("onWritePost", (title, text) => {
            if (this.user) {
                const user = this.user;
                axios.post("/api/1/posts/writePost", {
                    title, text, user
                }).then(() => {
                    this.updatePosts();
                }).catch(error => {
                    this.$root.$emit("onWritePostValidationError", error.response.data);
                });
            } else {
                this.$root.$emit("onWritePostValidationError", "No access");
            }
        });

        this.$root.$on("onWriteComment", (post, text) => {
            if (this.user) {
                const user = this.user;
                axios.post("/api/1/posts/writeComment", {
                    text, user, post
                }).then(() => {
                    this.updatePosts();
                }).catch(error => {
                    this.$root.$emit("onWriteCommentValidationError", error.response.data);
                });
            } else {
                this.$root.$emit("onWriteCommentValidationError", "No access");
            }
        });

        this.$root.$on("onJwt", (jwt) => {
            localStorage.setItem("jwt", jwt);

            axios.get("/api/1/users/auth", {
                params: {
                    jwt
                }
            }).then(response => {
                this.user = response.data;
                this.$root.$emit("onChangePage", "Index");
            }).catch(() => this.$root.$emit("onLogout"))
        });

        this.$root.$on("onLogout", () => {
            localStorage.removeItem("jwt");
            this.user = null;
        });

    }
}
</script>

<style>
#app {

}
</style>
