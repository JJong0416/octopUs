<template>
  <div>
    <v-img src="../assets/Logo.png" max-width="300" max-height="300"></v-img>
    <div class="login">
    <router-link :to="`/login`"><button>login</button></router-link>
    </div>
    <div class="signup">
      <router-link :to="`/signup`">signup</router-link>
    </div>

    <!-- 검색창 -->
    <input type="text" placeholder="검색창" /><button>검색</button>
    <!-- hot -->
    <br />
    hot <router-link :to="`/search`">더보기</router-link>
    <br />
    <!-- new -->
    <div class="mx-auto">
      new <router-link :to="`/search`">더보기</router-link>
    </div>
    <br />
  </div>
</template>

<script>
export default {};
</script>

<style>
.login a {
  text-decoration: none;
  color: black;
}
.login a:hover {
  text-decoration: none;
  color: black;
}
.signup a {
  text-decoration: none;
  color: black;
}
.signup a:hover {
  text-decoration: none;
  color: black;
}
</style>
