const { createProxyMiddleware } = require('http-proxy-middleware');

module.exports = function(app) {
  app.use(
    createProxyMiddleware("/users", {
      target: "https://jsonplaceholder.typicode.com",
      secure: false,
      changeOrigin: true
    })
  );
  app.use(
    createProxyMiddleware("/demoProject", {
      target: "http://localhost:8080",
      secure: false,
      changeOrigin: true
    })
  );

};