// const { defineConfig } = require("@vue/cli-service");
// module.exports = defineConfig({
//   transpileDependencies: ["vuetify"],
// });
module.exports = {
  outputDir: "../src/main/resources/static",
  indexPath: "../static/index.html",

  devServer: {
    proxy: {
      "/": {
        target: "http://localhost:8080/",
        pathRewrite: { "^/": "" },
        changeOrigin: true,
        secure: false,
      },
    },
  },
};
