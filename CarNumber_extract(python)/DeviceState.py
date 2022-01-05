import os
import requests
import time

while True: 
    print(os.path.isfile('/home/pi/exam/data/2.jpg'))
    time.sleep(1)
    obj = {"state":"0", "device":"GE-002"}
    #         requests.post('http://172.30.1.7:8087/imageConn/FiplState', data=obj)
    requests.post('http://210.223.239.165:8088/FiplState/DeviceState', data=obj) 
