##ARG REGISTRY=xxx/base-image
##FROM $REGISTRY/nginx:1.23.2-alpine

COPY nginx.conf /etc/nginx/nginx.conf
COPY nginx-custom.conf.template /etc/nginx/conf.d/default.conf.template
COPY Thawte /etc/nginx/ssl/Thawte
COPY run-nginx.sh /

## remove default nginx website
RUN rm -rf /usr/share/nginx/html/*

COPY dist/ /usr/share/nginx/html/dist

RUN chmod 775 /run-nginx.sh

ENTRYPOINT ["/run-nginx.sh"]
CMD ["nginx", "-g", "daemon off;"]
EXPOSE 80 443