<template>
  <div>
    <v-img src="../assets/logo.png" max-width="300" max-height="300"></v-img>
    <br />
    <button>login</button>
    <br />
    <div class="signup">
      <router-link :to="`/signup`">signup</router-link>
    </div>

    <!-- 검색창 -->
    <!-- hot -->
    <!-- new -->
  </div>
</template>

<script>
export default {};
</script>

<style>
.signup a {
  text-decoration: none;
  color: black;
}
.signup a:hover {
  text-decoration: none;
  color: black;
}
</style>
