#!/bin/bash

#Initialize service
cp ../target/ranking-api-0.0.1-SNAPSHOT.jar .
nohup java -jar ranking-api-0.0.1-SNAPSHOT.jar &
sleep 2

filename="$1"
while read -r line
do
  if [[ $line == *"user:"* ]]; then
    user=${line#*user:}
    user=${user%% *}
    score=${line#*score:}

    echo Adding User: userId=$user, score=$score
    curl -XPUT http://localhost:8080/ranking/update/$user?score=$score 
  fi
done < "testdata.txt"

top3=$(curl http://localhost:8080/ranking/list/absolute/Top3)
echo "Top3 = $top3"

#Kill Ranking Application
kill $(ps aux | grep 'ranking-api' | awk '{print $2}')
