# server
ncat -l -p 1234 -c ./run.sh

# client
stty -icanon && ncat localhost 1234
