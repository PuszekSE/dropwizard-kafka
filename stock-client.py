import http.client
import json
import time
import sys

if(len(sys.argv)<2):
    print ("USAGE %s: <Company symbols...>",sys.argv[0])



URL = 'localhost'
PORT = 8080
dropwizard_connection = http.client.HTTPConnection(URL,PORT)

STOCK_URL = 'dev.markitondemand.com'
stock_connection = http.client.HTTPConnection(STOCK_URL)
print (stock_connection)

STOCK_CONTEXT = '/Api/v2/Quote/json?symbol='


headers = {'Content-type': 'application/json'}

COMPANIES = sys.argv[1:]

while(True):
    for SYMBOL in COMPANIES:
        retries = 0;
        stock_connection.request('GET',(STOCK_CONTEXT+SYMBOL))
        response = stock_connection.getresponse()
        stock_ratings = response.read().decode()
        print (stock_ratings+"\n")
        json_stock = json.dumps(stock_ratings)
        print (json_stock+"\n")
        dropwizard_connection.request('POST', '/stock', stock_ratings, headers)
        response=dropwizard_connection.getresponse().read().decode()
        print (response+"\n")

        while(response!="OK" and retries < 1):
            print (response)
            time.sleep(1)
            dropwizard_connection.request('POST', '/stock', json_stock, headers)
            response = dropwizard_connection.getresponse().read().decode()
            retries+=1

        time.sleep(3)
