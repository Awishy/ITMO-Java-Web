<template>
    <footer>
        <div>
            <a href="/">Codeforces</a> &copy; 2022 by Mike Mirzayanov
        </div>
        <div>
            Users: {{ usersCount }} Posts: {{ postsCount }}
        </div>
    </footer>
</template>

<script>
export default {
    name: "Footer",
    props: ["usersCount", "postsCount"],
}
</script>

<style scoped>

</style>