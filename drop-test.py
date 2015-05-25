import http.client
import json

connection = http.client.HTTPConnection('localhost',8080)

headers = {'Content-type': 'application/json'}

foo = {'text': 'Hello world github/linguist#1 **cool**, and #1!'}
json_foo = json.dumps(foo)

connection.request('POST', '/stock', json_foo, headers)

response = connection.getresponse()
print(response.read().decode())
