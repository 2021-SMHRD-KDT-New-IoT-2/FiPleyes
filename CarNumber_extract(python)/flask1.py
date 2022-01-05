import base64 
import json 
import requests as req 
import os 
import time
from flask import Flask, redirect, make_response
 ### host : 172.30.1.20:8090/FlaskTest/ImageSaveService 

# img폴더 내 이미지파일명 접근 및 딕셔너리화
def getImgUrl(): 
    imgFiles = [] 
    files = {} 
    # 'img' 폴더의 사진 찾기 
    
    if os.path.isfile('/home/pi/exam/data/2.jpg'): 
        imgFiles = os.listdir('img')
    # 'img' 폴더의 사진을 files 배열로 만든다.
        print(imgFiles)
        for i in range(len(imgFiles)): 
            filename = os.path.splitext(os.path.basename(imgFiles[i]))[0]
            base64_filename = base64.b64encode(bytes(filename, 'utf-8')).decode()
            files[f'file{i+1}'] = (base64_filename + '.jpg', open(f'img/{imgFiles[i]}', 'rb').read()) 
        
    # files 를 리턴한다.     
    return files 

# Tomcat서버로 이미지파일 전송 

app = Flask(__name__)
@app.route('/imageSave')
def index():  
    server_url = 'http://172.30.1.7:8087/imageConn/test'
    # server_url = 'http://210.223.239.165:8088/Fipl/FileRes'
    
    # files를 getImgUrl() 로 받은 이유는?
    # 경로를 계속 읽어와야 다음에 보낼 때도, 제대로 보낼 수 있기 때문이다. 
    # 경로를 알 수 없어서 계속 파일이 못가는 오류가 발생했다.  
    # 이런 문제를 해결 할 수 있어야 한다!! 
    files = getImgUrl()  
    path = '/home/pi/exam/data/2.jpg'
    ti_c = time.ctime(os.path.getctime(path))
    obj = {
        "time": ti_c,
        "device":"GE-001"
    }
    res = req.post(server_url, files=files, data=obj)
    
    return 'success' if res.status_code == 200 else 'fail' 
    # host = raspberry pi id, port = 충돌나지 않을 번호로 지정
    
app.run(host = '172.30.1.9', port=3000)