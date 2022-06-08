const { defineConfig } = require('@vue/cli-service')
const path = require("path");
module.exports = defineConfig({
  transpileDependencies: [
    'vuetify'
  ],
    // Template for index.html
    // 기존 경로: index: path.resolve(__dirname, '../dist/index.html'),
    //  SpringBoot Static Folder
  indexPath: "../templates/index.html",
  assetsDir: "vuestatic",
    // Paths
    // 기존 경로: assetsRoot: path.resolve(__dirname, '../dist'),
    //  SpringBoot Static Folder
  outputDir: '../src/main/resources/static',
  publicPath: '/',
})
