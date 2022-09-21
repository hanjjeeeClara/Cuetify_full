const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: [
    'vuetify'
  ]
})

module.exports = {
  outputDir: '../src/main/resources/static',

  // npm run serve 개발 진행시에 포트가 다르기때문에 프록시 설정
  // devServer: {
  //   proxy: 'http://localhost:8080'
  // },

  devServer: {
    port: 8080,
    proxy: {
        //proxy 요청을 보낼 api 시작 부분
        '^/api': {
            target: 'http://localhost:8080',
            changeOrigin: true
        }
    }
}
  
}
