// const { defineConfig } = require("@vue/cli-service");
// module.exports = defineConfig({
//   transpileDependencies: ["vuetify"],
// });
module.exports = {
  devServer: {
    port: 8080,
    proxy: {
      "/api": {
        target: "http://localhost:7070",
        changeOrigin: true,
        logLevel: "debug",
        secure: false,
        // pathRewrite: {     "^/api": "" }
      },
    },
  },
};
