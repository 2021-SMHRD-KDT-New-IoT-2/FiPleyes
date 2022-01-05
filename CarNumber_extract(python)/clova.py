import json
import base64
import requests

with open("./data/2.jpg", "rb") as f:
    img = base64.b64encode(f.read())

 # 본인의 APIGW Invoke URL로 치환
URL = "https://cca95788640d4eec88bc8cf970cd218a.apigw.ntruss.com/custom/v1/12993/d6a9fc457f60947d599921461d75adba44916eece6b90c7f19793427f8fbf563/general"
    
 # 본인의 Secret Key로 치환
KEY = "aXh1b0d6ZG5Bd1hPZnJPbnVnT0ltZXh3elNzeWJQY3E="
    
headers = {
    "Content-Type": "application/json",
    "X-OCR-SECRET": KEY
}
    
data = {
    "version": "V2", #V2로 고정
    "requestId": "sample_id", # 요청을 구분하기 위한 ID, 사용자가 정의
    "timestamp": 0, # 현재 시간값
    "images": [
        {
            "name": "sample_image",
            "format": "jpg",
            "data": img.decode('utf-8')
        }
    ]
}
data = json.dumps(data)
response = requests.post(URL, data=data, headers=headers)
res = json.loads(response.text)
print(res)