import pymongo
import requests
from bs4 import BeautifulSoup
import re
import json
import pygal
from country_codes import get_country_code
# 发送请求获取首页
response = requests.get('https://ncov.dxy.cn/ncovh5/view/pneumonia')
#print(response.text)
home_page = response.content.decode()
# 从当前页提出数据
soup = BeautifulSoup(home_page, 'lxml')
script = soup.find(id="getListByCountryTypeService2true")
text = script.text

json_str = re.findall(r'\[.+\]', text)[0]
# print(json_str)
last_day_corona_virus = json.loads(json_str)

client = pymongo.MongoClient("localhost", 27017)
data = client["data"]
test = data["test"]
json_data = json.loads(json_str)
#print(type(json_data[0]))
test.insert_many(json.loads(json_str))
cc_confirmedCount={}
for json_dict in json_data:
    if json_dict['provinceId']=="10":
        country_name = json_dict['countryFullName']
        confirmedCount = int(float(json_dict["confirmedCount"]))
        print(country_name + ":" + str(confirmedCount))
        code = get_country_code(country_name)
        if code:
            cc_confirmedCount[code] = int(confirmedCount)
wm = pygal.maps.world.World()
wm.title = "全球疫情数据"
wm.add('2021.12', cc_confirmedCount)
wm.render_to_file("world_population.svg")