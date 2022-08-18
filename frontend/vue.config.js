module.exports = {
  devServer: {
    port: 80,
    proxy: {
      "/api": {
        target: "http://34.64.41.23",
        changeOrigin: true,
        logLevel: "debug",
        secure: false,
      },
    },
  },
};
