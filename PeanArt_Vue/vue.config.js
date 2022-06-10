const { defineConfig } = require('@vue/cli-service')
const path = require("path");
module.exports = defineConfig({
  transpileDependencies: [
    'vuetify'
  ],
  // devServer: {
  //   proxy: {
  //       '/':{
  //           "target":'http://localhost:8080', // Spring boot의 주소 및 포트
  //           "pathRewrite":{'^/':''},
  //           "changeOrigin":true,
  //           logLevel: 'debug', // 터미널에 proxy 로그가 찍힌다.
  //           ws: true,
  //       }
  //   }
  // },
    // Template for index.html
    // 기존 경로: index: path.resolve(__dirname, '../dist/index.html'),
    //  SpringBoot Static Folder
  // indexPath: "../templates/index.html",
  // assetsDir: "vuestatic",
    // Paths
    // 기존 경로: assetsRoot: path.resolve(__dirname, '../dist'),
    //  SpringBoot Static Folder
  outputDir: '../src/main/resources/static',
  // publicPath: '/',
})
