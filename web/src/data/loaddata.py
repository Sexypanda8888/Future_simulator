import pymysql
import json
with open("./futuremenu.json",'r',encoding='utf-8') as f:
    data=f.read()
data=json.loads(data)
config={
"host" : '103.229.126.165',
"user" : 'commitor',
"password" : '123456',
"port" : 3306,
# which database to use.
"db" : 'future simulator'
}
try:
    conn=pymysql.connect(host=config["host"],
                    port=config["port"],
                    user=config["user"],
                    password=config["password"],
                    database=config["db"])
except:
    print("数据库连接失败")
    exit(0)
with conn.cursor(pymysql.cursors.DictCursor) as cursor:
    threads=[]
    datalist=[]
    for i in data["futuremenu"]:
        holder=i["holder"]
        for j in i["data"]:
            sql="insert into future_menu(holder,name,urlCode) values('{}','{}','{}')".format(holder,j["name"],j["urlCode"])
            cursor.execute(sql)
conn.commit()