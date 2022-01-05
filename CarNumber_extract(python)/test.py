import base64 
import json 
import requests as req 
import os 
import time
from flask import Flask, redirect, make_response
import re

### host : 172.30.1.20:8090/FlaskTest/ImageSaveService 
# img폴더 내 이미지파일명 접근 및 딕셔너리화

#-*- coding:utf-8 -*-
import cv2
import numpy as np
import math
import argparse
try:
    from PIL import Image
except ImportError:
    import Image
import requests

#pytesseract.pytesseract.tesseract_cmd = r'C:\\Program Files\\Tesseract-OCR\\tesseract.exe' #윈도우에서 실행시

def removeAllFile(filePath):
                if os.path.exists(filePath):
                    for file in os.scandir(filePath):
                        os.remove(file.path)
                        
def regex1(name):                        
    regex = re.compile('[0-9]{2}[가-힣]{1}[0-9]{4}|[0-9]{3}[가-힣]{1}[0-9]{4}')
    mc = regex.match(name)
    if mc:
        name = mc.string
    else :
        pass
# ap = argparse.ArgumentParser()
# ap.add_argument("-i", "--image", type=str, required=True, help="path to image")
# args = vars(ap.parse_args())
# img = cv2.imread(args["image"])



try:
    while True:
        if os.path.isdir('data'):
            for i in range(1,6):
                time.sleep(5)
                img = cv2.imread(f'./data/{i}.jpg')
                img_resize = cv2.resize(img, dsize=(200,200), interpolation=cv2.INTER_CUBIC)
                img_ori = img
                height, width, channel = img.shape
            #     print(height, width, channel)
                gray = cv2.cvtColor(img, cv2.COLOR_BGR2GRAY)

                img_blurred = cv2.bilateralFilter(gray, -1, 10, 5)
                
                img_blur_thresh = cv2.adaptiveThreshold(
                    img_blurred,
                    maxValue=255.0,
                    adaptiveMethod=cv2.ADAPTIVE_THRESH_GAUSSIAN_C,
                    thresholdType=cv2.THRESH_BINARY_INV,
                    blockSize=19,
                    C=9
                )
                img_thresh = cv2.adaptiveThreshold(
                    gray,
                    maxValue=255.0,
                    adaptiveMethod=cv2.ADAPTIVE_THRESH_GAUSSIAN_C,
                    thresholdType=cv2.THRESH_BINARY_INV,
                    blockSize=19,
                    C=9
                )
                
                contours, _ = cv2.findContours(
                    img_blur_thresh,
                    mode=cv2.RETR_LIST,
                    method=cv2.CHAIN_APPROX_SIMPLE
                )
                
                temp_result = np.zeros((height, width, channel), dtype=np.uint8)

                cv2.drawContours(temp_result, contours=contours, contourIdx=-1, color=(255,255,255))

                temp_result = np.zeros((height, width, channel), dtype=np.uint8)

                contours_dict = []

                for contour in contours:
                    x, y, w, h = cv2.boundingRect(contour)
                    cv2.rectangle(temp_result, pt1=(x,y), pt2=(x+w, y+h), color=(255,255,255), thickness=1)

                    contours_dict.append({
                        'contour': contour,
                        'x': x,
                        'y': y,
                        'w': w,
                        'h': h,
                        'cx': x + (w / 2),
                        'cy': y + (h / 2)
                    })

                MIN_AREA = 80
                MIN_WIDTH, MIN_HEIGHT= 3, 8 # 2,8
                MIN_RATIO, MAX_RATIO = 0.15, 1.0 # 0.25, 1.0

                possible_contours = []

                cnt = 0
                for d in contours_dict:
                    area = d['w'] * d['h']
                    ratio = d['w'] / d['h']

                    if area > MIN_AREA     and d['w'] > MIN_WIDTH and d['h'] > MIN_HEIGHT     and MIN_RATIO < ratio < MAX_RATIO:
                        d['idx'] = cnt
                        cnt += 1
                        possible_contours.append(d)

                temp_result = np.zeros((height, width, channel), dtype = np.uint8)

                for d in possible_contours:
                    cv2.rectangle(temp_result, pt1=(d['x'], d['y']), pt2=(d['x']+d['w'], d['y']+d['h']), color=(255, 255, 255), thickness=1)
                
                MAX_DIAG_MULTIPLYER = 5 #5 
                MAX_ANGLE_DIFF = 14.0 #12
                MAX_AREA_DIFF = 0.5 
                MAX_WIDTH_DIFF = 0.7 
                MAX_HEIGHT_DIFF = 0.2 #0.2
                MIN_N_MATCHED = 4 #3
                def find_chars(contour_list):
                    matched_result_idx = []

                    for d1 in contour_list:
                        matched_contours_idx = []
                        for d2 in contour_list:
                            if d1['idx'] == d2['idx']:
                                continue

                            dx = abs(d1['cx'] - d2['cx'])
                            dy = abs(d1['cy'] - d2['cy'])

                            diagonal_length1 = np.sqrt(d1['w'] ** 2 + d1['h'] ** 2)

                            distance = np.linalg.norm(np.array([d1['cx'], d1['cy']]) - np.array([d2['cx'], d2['cy']]))
                            if dx == 0:
                                angle_diff = 90
                            else:
                                angle_diff = np.degrees(np.arctan(dy / dx))
                            area_diff = abs(d1['w'] * d1['h'] - d2['w'] * d2['h']) / (d1['w'] * d1['h'])
                            width_diff = abs(d1['w'] - d2['w']) / d1['w']
                            height_diff = abs(d1['h'] - d2['h']) / d1['h']

                            if distance < diagonal_length1 * MAX_DIAG_MULTIPLYER and angle_diff < MAX_ANGLE_DIFF and area_diff < MAX_AREA_DIFF and width_diff < MAX_WIDTH_DIFF and height_diff < MAX_HEIGHT_DIFF:
                                matched_contours_idx.append(d2['idx'])

                        matched_contours_idx.append(d1['idx'])

                        if len(matched_contours_idx) < MIN_N_MATCHED:
                            continue

                        matched_result_idx.append(matched_contours_idx)

                        unmatched_contour_idx = []
                        for d4 in contour_list:
                            if d4['idx'] not in matched_contours_idx:
                                unmatched_contour_idx.append(d4['idx'])

                        unmatched_contour = np.take(possible_contours, unmatched_contour_idx)

                        recursive_contour_list = find_chars(unmatched_contour)

                        for idx in recursive_contour_list:
                            matched_result_idx.append(idx)

                        break

                    return matched_result_idx
                result_idx = find_chars(possible_contours)

                matched_result = []
                for idx_list in result_idx:
                    matched_result.append(np.take(possible_contours, idx_list))

                temp_result = np.zeros((height, width, channel), dtype=np.uint8)

                for r in matched_result:
                    for d in r:
                        cv2.rectangle(temp_result, pt1=(d['x'], d['y']), pt2=(d['x']+d['w'], d['y']+d['h']), color=(255,255,255), thickness=1)

                PLATE_WIDTH_PADDING = 1.3 # 1.3
                PLATE_HEIGHT_PADDING = 1.5 # 1.5
                MIN_PLATE_RATIO = 3
                MAX_PLATE_RATIO = 10

                plate_imgs = []
                plate_infos = []

                for i, matched_chars in enumerate(matched_result):
                    sorted_chars = sorted(matched_chars, key=lambda x: x['cx'])

                    plate_cx = (sorted_chars[0]['cx'] + sorted_chars[-1]['cx']) / 2
                    plate_cy = (sorted_chars[0]['cy'] + sorted_chars[-1]['cy']) / 2

                    plate_width = (sorted_chars[-1]['x'] + sorted_chars[-1]['w'] - sorted_chars[0]['x']) * PLATE_WIDTH_PADDING

                    sum_height = 0
                    for d in sorted_chars:
                        sum_height += d['h']

                    plate_height = int(sum_height / len(sorted_chars) * PLATE_HEIGHT_PADDING)

                    triangle_height = sorted_chars[-1]['cy'] - sorted_chars[0]['cy']
                    triangle_hypotenus = np.linalg.norm(
                        np.array([sorted_chars[0]['cx'], sorted_chars[0]['cy']]) - 
                        np.array([sorted_chars[-1]['cx'], sorted_chars[-1]['cy']])
                    )

                    angle = np.degrees(np.arcsin(triangle_height / triangle_hypotenus))

                    rotation_matrix = cv2.getRotationMatrix2D(center=(plate_cx, plate_cy), angle=angle, scale=1.0)

                    img_rotated = cv2.warpAffine(img_thresh, M=rotation_matrix, dsize=(width, height))

                    img_cropped = cv2.getRectSubPix(
                        img_rotated, 
                        patchSize=(int(plate_width), int(plate_height)), 
                        center=(int(plate_cx), int(plate_cy))
                    )

                    if img_cropped.shape[1] / img_cropped.shape[0] < MIN_PLATE_RATIO or img_cropped.shape[1] / img_cropped.shape[0] < MIN_PLATE_RATIO > MAX_PLATE_RATIO:
                        continue

                    plate_imgs.append(img_cropped)
                    plate_infos.append({
                        'x': int(plate_cx - plate_width / 2),
                        'y': int(plate_cy - plate_height / 2),
                        'w': int(plate_width),
                        'h': int(plate_height)
                    })

                longest_idx, longest_text = -1, 0
                plate_chars = []

                for i, plate_img in enumerate(plate_imgs):
                    plate_img = cv2.resize(plate_img, dsize=(0, 0), fx=1.6, fy=1.6)
                    _, plate_img = cv2.threshold(plate_img, thresh=0.0, maxval=255.0, type=cv2.THRESH_BINARY | cv2.THRESH_OTSU)

                    # find contours again (same as above)
                    contours, _ = cv2.findContours(plate_img, mode=cv2.RETR_LIST, method=cv2.CHAIN_APPROX_SIMPLE)

                    plate_min_x, plate_min_y = plate_img.shape[1], plate_img.shape[0]
                    plate_max_x, plate_max_y = 0, 0

                    for contour in contours:
                        x, y, w, h = cv2.boundingRect(contour)

                        area = w * h
                        ratio = w / h

                        if area > MIN_AREA         and w > MIN_WIDTH and h > MIN_HEIGHT         and MIN_RATIO < ratio < MAX_RATIO:
                            if x < plate_min_x:
                                plate_min_x = x
                            if y < plate_min_y:
                                plate_min_y = y
                            if x + w > plate_max_x:
                                plate_max_x = x + w
                            if y + h > plate_max_y:
                                plate_max_y = y + h

                    img_result = plate_img[plate_min_y:plate_max_y, plate_min_x:plate_max_x]

                    img_result = cv2.GaussianBlur(img_result, ksize=(3, 3), sigmaX=0)
                    _, img_result = cv2.threshold(img_result, thresh=0.0, maxval=255.0, type=cv2.THRESH_BINARY | cv2.THRESH_OTSU)
                    img_result = cv2.copyMakeBorder(img_result, top=10, bottom=10, left=10, right=10, borderType=cv2.BORDER_CONSTANT, value=(0,0,0))

                    # pytesseract.pytesseract.tesseract_cmd = 'C:\Program Files\Tesseract-OCR/tesseract.exe'
                    # chars = pytesseract.image_to_string(img_result, lang='kor', config='--psm 7 --oem 0')
                    cv2.imwrite('data.jpg', img_result)

                    im = Image.fromarray(np.uint8(img_result))
                    im.save("plate.png")
                    
                    with open("./plate.png", "rb") as f:
                        img = base64.b64encode(f.read())
                    
                #             img = Image.open("plate.png")
                    # text = pytesseract.image_to_string(im, lang='kor')

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
                                "format": "png",
                                "data": img.decode('utf-8')
                            }
                        ]
                    }
                    text = []
                    data = json.dumps(data)
                    response = requests.post(URL, data=data, headers=headers)
                    res = json.loads(response.text)
                    for i in range(0,2):
                        text.append(res['images'][0]['fields'][i]['inferText'])
                    
                    carnum = text[0]+text[1]
                    regex1(carnum)
                    print("text present in images:", carnum)
                    
                    cv2.imwrite(f'./img/{carnum}.jpg', img_ori)
                    #cv2.drawContours(imageContours,[box],0,(0,0,255),2)
                                

                    print(f'GE-001 : {carnum}')
                    cv2.waitKey(0)
                    print('next') 

                    imgFiles = [] 
                    files = {} 
                    # 'img' 폴더의 사진 찾기 
                #     async def access_url():
                #         print('success')
                #         reqeust_url = '172.30.1.9:3000/imageSave'
                #         open(reqeust_url)
                    

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
                            break
            #         removeAllFile('./data/')
                            # files 를 리턴한다. 


except Exception as e:
    print(e)
    pass

# Tomcat서버로 이미지파일 전송 

