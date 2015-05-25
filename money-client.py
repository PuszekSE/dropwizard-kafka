import http.client
import json
import time
URL = 'localhost'
PORT = 8080
connection = http.client.HTTPConnection(URL,PORT)
headers = {'Content-type': 'application/json'}

FILE = open('drop-data.csv',"r")

headers = {'Content-type': 'application/json'}

for line in FILE.readlines():
    data = line.split(",")
    stock = {'currency': 'USD', 'rate':data[2][1:]+'.'+data[3][:-1],'time':str(round(time.time()))}
    json_money = json.dumps(stock)
    connection.request('POST', '/stock', json_money, headers)
    response = connection.getresponse()
    print (data)
    print (json_money)
    print(response.read().decode())
    break


FILE.close()

