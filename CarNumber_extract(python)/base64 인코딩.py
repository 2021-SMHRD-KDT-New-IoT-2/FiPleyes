import time
import os
import base64
import requests as req
import requests
def File_Encoding():
imgFiles = [] 
files = {} 
while True:
    time.sleep(1)
    if os.path.isdir('img') == True:
        imgFiles = os.listdir('img')
        print(imgFiles)
        if imgFiles:
            print('img exists')

        # 'img' 폴더의 사진을 files 배열로 만든다.
            for i in range(len(imgFiles)): 
                filename = os.path.splitext(os.path.basename(imgFiles[i]))[0]
                base64_filename = base64.b64encode(bytes(filename, 'utf-8')).decode()
                files[f'file{i+1}'] = (base64_filename + '.jpg', open(f'img/{imgFiles[i]}', 'rb').read())

            path = '/home/pi/exam/data/2.jpg'
            ti_c = time.ctime(os.path.getctime(path))
            obj = {
                "time": ti_c,
                "device":"GE-001"
            }
            
            requests.post('http://172.30.1.7:8087/imageConn/test', files=files, data=obj)
#                     requests.post('http://210.223.239.165:8088/Fipl/FileRes', files=files, data=obj)
            
            time.sleep(1)
        removeAllFile('./img/')
    else:
        print('파일 없음')
        break
