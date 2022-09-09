<<<<<<< HEAD
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
=======
module.exports = {
  devServer: {
    port: 80,
    proxy: {
      "/api": {
        target: "http://34.64.41.23",
        changeOrigin: true,
        logLevel: "debug",
        secure: false,
>>>>>>> ac048782d308840093efdf3b8146c990ec2fcf95
      },
    },
  },
};
