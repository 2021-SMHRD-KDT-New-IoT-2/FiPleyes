import RPi.GPIO as gp
import time
import picamera as pc
import pygame

gp.setmode(gp.BCM) 
gp.setup(18, gp.OUT)
gp.setup(24, gp.OUT)
p = gp.PWM(18, 50)
p.start(4)

pygame.init()
song = pygame.mixer.music
song.load('/home/pi/Rabi.mp3')

trig = 20
echo = 19

trig2 = 13
echo2 = 12

trig3 = 26
echo3 = 21

A = 30
B = 0
C = 0
A1 = 30
B1 = 0
C1 = 0

cnt1 = 1


gp.setup(trig, gp.OUT)
gp.setup(echo, gp.IN)
# gp.output(trig, False)

gp.setup(trig2, gp.OUT)
gp.setup(echo2, gp.IN)
# gp.output(trig2, False)

gp.setup(trig3, gp.OUT)
gp.setup(echo3, gp.IN)

pulse_start = 0
pulse_end = 0

pulse_start2 = 0
pulse_end2 = 0

pulse_start3 = 0
pulse_end3 = 0

camera = pc.PiCamera()

def Sen1():
    gp.output(trig, True)
    time.sleep(0.1)
    gp.output(trig, False)                        
    while gp.input(echo)==0:
        pulse_start = time.time()
    while gp.input(echo)==1:
        pulse_end = time.time()
    pulse_duration = pulse_end - pulse_start # 초음파 알아먹을수잇게 출력
    distance = pulse_duration * 17000
    distance = round(distance, 2)
    print(f'1번째 센서 {distance}')
    return distance
    
def Sen2():
    gp.output(trig2, True)
    time.sleep(0.1)
    gp.output(trig2, False)    
    while gp.input(echo2)==0:
        pulse_start2 = time.time()            
    while gp.input(echo2)==1:
        pulse_end2 = time.time()
    pulse_duration2 = pulse_end2 - pulse_start2 # 초음파 알아먹을수잇게 출력
    distance2 = pulse_duration2 * 17000
    distance2 = round(distance2, 2) 
    print(f'2번째 센서 {distance2}')
    return distance2
    
def Sen3():
    gp.output(trig3, True)
    time.sleep(0.1)
    
    gp.output(trig3, False)
    while gp.input(echo3)==0:
        pulse_start3 = time.time()            
    while gp.input(echo3)==1:
        pulse_end3 = time.time()
    pulse_duration3 = pulse_end3 - pulse_start3 # 초음파 알아먹을수잇게 출력
    distance3 = pulse_duration3 * 17000
    distance3 = round(distance3, 2) 
    print(f'3번째 센서 {distance3}')
    return distance3

try:
    while True:
        gp.output(trig, True)
        time.sleep(0.1)
        gp.output(trig, False)
                
        while gp.input(echo)==0:
            pulse_start = time.time()
        while gp.input(echo)==1:
            pulse_end = time.time()
            
        gp.output(trig2, True)
        time.sleep(0.1)
        gp.output(trig2, False)
        
        while gp.input(echo2)==0:
            pulse_start2 = time.time()            
        while gp.input(echo2)==1:
            pulse_end2 = time.time()
            
        gp.output(trig3, True)
        time.sleep(0.1)
        gp.output(trig3, False)
        
        while gp.input(echo3)==0:
            pulse_start3 = time.time()
            
        while gp.input(echo3)==1:
            pulse_end3 = time.time()

        
        pulse_duration = pulse_end - pulse_start # 초음파 알아먹을수잇게 출력
        distance = pulse_duration * 17000
        distance = round(distance, 2) 
        print(f'1번째 센서 {distance}')
        
        pulse_duration2 = pulse_end2 - pulse_start2 # 초음파 알아먹을수잇게 출력
        distance2 = pulse_duration2 * 17000
        distance2 = round(distance2, 2) 
        print(f'2번째 센서 {distance2}')
        
        pulse_duration3 = pulse_end3 - pulse_start3 # 초음파 알아먹을수잇게 출력
        distance3 = pulse_duration3 * 17000
        distance3 = round(distance3, 2) 
        print(f'3번째 센서 {distance3}')
        
            
        if distance <= A:
            p.ChangeDutyCycle(4)
            gp.output(24, True)
            time.sleep(0.5)
            if distance <A1:
                camera.start_preview()
                camera.resolution = (640,480)
                camera.framerate = 15
                for i in range(5):
                    camera.capture(f'./data/{i}.jpg')
                    camera.rotation = 180
                    time.sleep(0.5)
                camera.stop_preview()
                time.sleep(3)
#ㅡㅡㅡㅡㅡㅡㅡㅡㅡ알람이 울리고 30초후 노래실행ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ                  
                if distance < A1:
                    for asdf in range(3):
                        song.play()                       
                        while True:
                            gp.output(trig, True)
                            time.sleep(0.1)
                            gp.output(trig, False)                        
                            while gp.input(echo)==0:
                                pulse_start = time.time()
                            while gp.input(echo)==1:
                                pulse_end = time.time()
                            pulse_duration = pulse_end - pulse_start # 초음파 알아먹을수잇게 출력
                            distance = pulse_duration * 17000
                            distance = round(distance, 2)
                            print(f'1번째 센서 {distance}')
#                           Sen1()
                            time.sleep(0.5)
                            if distance > 50 :
                                song.stop()
                                break
                            else :
                                cnt=1
                                while cnt <= 12:
                                    time.sleep(1)
                                    print(distance)
                                    if distance > 50:
                                        song.stop()
                                        print('주차 차량이 멀어짐(변수 초기화)')
                                    cnt = cnt + 1
                                cnt1 = cnt1 + 1
                                print(f'{cnt1}번째 울림')
                            song.stop()
                            time.sleep(2)                        
                            if cnt1 > 3:
                                gp.output(24, False)
                                print("신고신고")
                                song.stop()
                                time.sleep(2)
                            break
                        if distance > 50:
                            gp.output(24, False)
                            song.stop()
                            break
                        
            
        elif distance2 < B :
            p.ChangeDutyCycle(7)
            gp.output(24, True)
            if distance2 < B1:
                camera.start_preview()
                camera.resolution = (640,480)
                camera.framerate = 15
                for j in range(5):
                    camera.capture(f'./data/{i}.jpg')
                    camera.rotation = 180 
                    time.sleep(0.5)
                camera.stop_preview()
                time.sleep(3)
#ㅡㅡㅡㅡㅡㅡㅡㅡㅡ알람이 울리고 30초후 노래실행ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ                
                if distance2 < B1:
                    for asdf in range(3):
                        song.play()                       
                        while True:
                            gp.output(trig2, True)
                            time.sleep(0.1)
                            gp.output(trig2, False)                        
                            while gp.input(echo2)==0:
                                pulse_start2 = time.time()
                            while gp.input(echo2)==1:
                                pulse_end2 = time.time()
                            pulse_duration2 = pulse_end2 - pulse_start2 # 초음파 알아먹을수잇게 출력
                            distance2 = pulse_duration2 * 17000
                            distance2 = round(distance2, 2)
                            print(f'2번째 센서 {distance2}')
#                           Sen1()
                            time.sleep(0.5)
                            if distance2 > 50 :
                                song.stop()
                                break
                            else :
                                cnt=1
                                while cnt <= 12:
                                    time.sleep(1)
                                    print(distance2)
                                    if distance2 > 50:
                                        song.stop()
                                        print('주차 차량이 멀어짐(변수 초기화)')
                                    cnt = cnt + 1
                                cnt1 = cnt1 + 1
                                print(f'{cnt1}번째 울림')
                            song.stop()
                            time.sleep(2)                        
                            if cnt1 > 3:
                                gp.output(24, False)
                                print("신고신고")
                                song.stop()
                                time.sleep(2)
                            break
                        if distance2 > 50:
                            gp.output(24, False)
                            song.stop()
                            break
                    
                        
        elif distance3 < C:
            p.ChangeDutyCycle(10)
            gp.output(24, True)
            #song.play()
            if distance3 <C1:
                camera.start_preview()
                camera.resolution = (640,480)
                camera.framerate = 15
                for h in range(5):
                    camera.capture(f'./data/{i}.jpg')
                    camera.rotation = 180 
                    time.sleep(0.5)
                camera.stop_preview()
                time.sleep(3)
#ㅡㅡㅡㅡㅡㅡㅡㅡㅡ알람이 울리고 30초후 노래실행ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ                
                if distance3 < C1:
                    for asdf in range(3):
                        song.play()                       
                        while True:
                            gp.output(trig3, True)
                            time.sleep(0.1)
                            gp.output(trig3, False)                        
                            while gp.input(echo3)==0:
                                pulse_start3 = time.time()
                            while gp.input(echo3)==1:
                                pulse_end3 = time.time()
                            pulse_duration3 = pulse_end3 - pulse_start3 # 초음파 알아먹을수잇게 출력
                            distance3 = pulse_duration3 * 17000
                            distance3 = round(distance3, 2)
                            print(f'3번째 센서 {distance3}')
#                           Sen1()
                            time.sleep(0.5)
                            if distance3 > 50 :
                                song.stop()
                                break
                            else :
                                cnt=1
                                while cnt <= 12:
                                    time.sleep(1)
                                    print(distance3)
                                    if distance3 > 50:
                                        song.stop()
                                        print('주차 차량이 멀어짐(변수 초기화)')
                                    cnt = cnt + 1
                                cnt1 = cnt1 + 1
                                print(f'{cnt1}번째 울림')
                            song.stop()
                            time.sleep(2)                        
                            if cnt1 > 3:
                                gp.output(24, False)
                                print("신고신고")
                                song.stop()
                                time.sleep(2)
                            break
                        if distance3 > 50:
                            gp.output(24, False)
                            song.stop()
                            break
                        
        gp.output(24, False)            
    camera.close()
except KeyboardInterrupt:
    gp.cleanup()