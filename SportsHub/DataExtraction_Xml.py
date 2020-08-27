# -*- coding: utf-8 -*-
"""
Created on Tue Nov 19 07:57:46 2019

@author: swathi
"""

# -*- coding: utf-8 -*-
"""
Created on Mon Nov 18 13:22:49 2019

@author: swathi
"""
import urllib.request
import json
import re
from dicttoxml import dicttoxml

headers = {
  'Accept': 'application/json'
}


request = urllib.request.Request('https://app.ticketmaster.com/discovery/v2/events.json?keyword=sports&countryCode=US&startDateTime=2019-11-18T20:00:00Z&endDateTime=2030-12-31T20:00:00Z&size=200&apikey=iIqPlApNvWLnDDJQLNP4sShVbMdXPtVu', headers=headers)
response_body = urllib.request.urlopen(request).read()

event_search_data = response_body.decode('utf8').replace('\"', '"')
#print(event_search_data)

event_info ={}
event_list = []
event_search = json.loads(event_search_data)
#print(event_search["_embedded"]["events"][0]["id"])
for elements in event_search["_embedded"]["events"]:
    event_info ={}
    event_info["event_id"] = elements["id"]
    event_info["event_name"] = elements["name"]
    event_info["event_image"] = elements["images"][0]
    for keys in elements["sales"]["public"].keys():
        if keys == 'startDateTime':
            event_info["sales_start_date"] = elements["sales"]["public"][keys]
        elif keys == 'endDateTime':
            event_info["sales_end_date"] = elements["sales"]["public"][keys]
    for keys in elements["dates"]["start"].keys():
        if keys == 'localDate':
            event_info["event_date"] = elements["dates"]["start"][keys]
        elif keys == 'localTime':
            event_info["event_time"] = elements["dates"]["start"][keys]
    event_info["event_sale_status"] = elements["dates"]["status"]["code"]
    event_info["genre"] = elements["classifications"][0]["genre"]["name"]
    event_info["subGenre"] = elements["classifications"][0]["subGenre"]["name"]
    for item in range(len(elements["_embedded"]["venues"])):
        event_info["venues_name"] = elements["_embedded"]["venues"][item]["name"]
        event_info["venues_postalCode"] = elements["_embedded"]["venues"][item]["postalCode"]
        event_info["venues_city"] = elements["_embedded"]["venues"][item]["city"]["name"]
        event_info["venues_state"] = elements["_embedded"]["venues"][item]["state"]["name"]
        event_info["state_code"] = elements["_embedded"]["venues"][item]["state"]["stateCode"]
        event_info["contry_name"] = elements["_embedded"]["venues"][item]["country"]["name"]
        event_info["country_code"] = elements["_embedded"]["venues"][item]["country"]["countryCode"]
        event_info["address"] = elements["_embedded"]["venues"][item]["address"]["line1"]
        event_info["location_longitude"] = elements["_embedded"]["venues"][item]["location"]["longitude"]
        event_info["location_latitude"] = elements["_embedded"]["venues"][item]["location"]["latitude"]
        for keys in elements["_embedded"]["venues"][item].keys():
            if keys == 'boxOfficeInfo':
                for i in elements["_embedded"]["venues"][item][keys].keys():
                    if i == 'phoneNumberDetail':
                        event_info["phoneNumberDetail"] = elements["_embedded"]["venues"][item][keys][i]
                    elif i == 'openHoursDetail':
                        event_info["openHoursDetail"] = elements["_embedded"]["venues"][item][keys][i]
                    elif i == 'acceptedPaymentDetail':
                        event_info["acceptedPaymentDetail"] = elements["_embedded"]["venues"][item][keys][i]
                    elif i == 'willCallDetail':
                        event_info["willCallDetail"] = elements["_embedded"]["venues"][item][keys][i]
            elif keys == 'parkingDetail':
                event_info["parkingDetail"] = elements["_embedded"]["venues"][item][keys]
            elif keys == 'accessibleSeatingDetail':
                event_info["accessibleSeatingDetail"] = elements["_embedded"]["venues"][item][keys]    
    for id in elements.keys():
        if id == 'priceRanges':
             event_info["min_price"] = elements[id][0]['min']
             event_info["max_price"] = elements[id][0]['max']
        if id == 'seatmap':
            event_info["seatmap"] = elements[id]["staticUrl"]
        if id == 'pleaseNote':
            event_info["pleaseNote"] = elements[id]
        if id == 'promoter':
            event_info["promoter_name"] = elements[id]['name']
            event_info["promoter_description"] = elements[id]['description']
    #events["Event"] = event_info
    event_list.append(event_info)
    


file = open("C:/apache-tomcat-7.0.34/webapps/EWA_Project/Event_Data.xml", "w")
file.writelines("<Events>")

for i in range(1,len(event_list)):
        file.writelines("<Event id=\""+str(i)+"\"></Event>")

file.writelines("</Events>")
#file.close()
#file = open("D:/2nd Sem Courses/Web/apache-tomcat-7.0.34/webapps/SportHub/Event_Data.xml", "w")
file = open("C:/apache-tomcat-7.0.34/webapps/EWA_Project/Event_Data.xml", "w")
xml = dicttoxml(event_list, custom_root='Events', ids=True, attr_type=False,item_func= lambda x: 'Event')
#print(xml.decode('utf8'))
xml = re.sub(r"[^A-Za-z0-9\/:;&-. <>=\"_'$@?!#*]+","",xml.decode('utf8'))
file.write(xml)
file.close()

import xml.etree.ElementTree as ET
from xml.etree.ElementTree import tostring

#tree = ET.parse('D:/2nd Sem Courses/Web/apache-tomcat-7.0.34/webapps/SportHub/Event_Data.xml')
tree = ET.parse('C:/apache-tomcat-7.0.34/webapps/EWA_Project/Event_Data.xml')
root = tree.getroot()

id = 1
for rank in root.iter('Event'):
    rank.set('id', str(id))
    id +=1

#tree.write('D:/2nd Sem Courses/Web/apache-tomcat-7.0.34/webapps/SportHub/Event_Data.xml')
tree.write('C:/apache-tomcat-7.0.34/webapps/EWA_Project/Event_Data.xml')  
    

