FROM node:17-alpine3.14

USER root

# Dependencias
COPY package.json ./
COPY yarn.lock ./
RUN npm install -g @quasar/cli && \
    npm install -g @vue/cli && \
    npm install -g @vue/cli-init && \
    npm install -g yarn
RUN yarn install
RUN quasar build

COPY ./dist /home/neso/public_html

CMD /bin/sh

## sudo npm install -g @quasar/cli && sudo npm install -g @vue/cli && sudo npm install -g @vue/cli-init && sudo npm install -g yarn


