FROM node:18 AS frontend-build
WORKDIR /app/web-portal
COPY src src
COPY public public
COPY *.json .

RUN npm install
RUN npm run build

FROM nginx:alpine
COPY --from=build /app/web-portal/build /usr/share/nginx/html

EXPOSE 3000
CMD ["nginx", "-g", "daemon off;"]
