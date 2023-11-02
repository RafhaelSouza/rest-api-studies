FROM nginx:latest

COPY my-nginx.conf /etc/nginx/conf.d/default.conf

COPY front /usr/share/nginx/html

EXPOSE 80

CMD ["nginx", "-g", "daemon off;"]

# docker build -t js-client-oauth2-authorization-code .
# docker run -d -p 8082:80 js-client-oauth2-authorization-code

