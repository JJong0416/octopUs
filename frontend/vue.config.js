// const { defineConfig } = require("@vue/cli-service");
// module.exports = defineConfig({
//   transpileDependencies: ["vuetify"],
// });

module.exports = {
  devServer: {
    proxy: {
      // proxyTable 설정
      "/api": {
        target: "https://localhost:7070",
        changeOrigin: true,
      },
    },
  },
};
