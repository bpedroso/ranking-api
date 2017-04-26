 #!/bin/bash

size=$1
i=0

while [  $i -lt $size ]; do
  echo Adding User: userId=xpto$i, score=$i

  curl -XPUT http://localhost:8080/ranking/update/xpto$i?score=$i
  let i=i+1
done

