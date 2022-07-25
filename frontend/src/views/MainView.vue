<template>
  <div>
    Main
    <br />
    <button>login</button>
    <br />
    <div class="signup">
      <router-link :to="`/signup`">signup</router-link>
    </div>
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
