// const { defineConfig } = require("@vue/cli-service");
// module.exports = defineConfig({
//   transpileDependencies: ["vuetify"],
// });
module.exports = {
  devServer: {
    port: 80,
    proxy: {
      "/api": {
        target: "34.64.41.23",
        changeOrigin: true,
        logLevel: "debug",
        secure: false,
        // pathRewrite: {     "^/api": "" }
      },
    },
  },
};
