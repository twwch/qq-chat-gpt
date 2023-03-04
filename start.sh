docker rm -f qq-robot-chatgpt
docker run -it -m 1800m  --restart=always -d --net=host --name qq-robot-chatgpt \
        -v /etc/hosts:/etc/hosts \
        -v /var/qq-robot/:/app/bots \
        -v /etc/localtime:/etc/localtime \
        -e LANG="en_US.UTF-8" \
        twwch/qq-chat-gpt:master

