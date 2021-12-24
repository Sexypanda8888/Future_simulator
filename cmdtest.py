import argparse
import requests
import re
from requests.sessions import session
import schedule
import time
# 命令行，分别是程序持续的时间和程序关注的股票代码

#第一步，先查看该程序能够进行购买操作
#测试命令:python cmdtest.py -st "ib" -m 10 -c "hk00863" -t 35 -it 10       -s session  -n --robot_bumber
#第二步
parser = argparse.ArgumentParser('传入参数：***.py')
# 机器人编号
parser.add_argument('-n', '--robot_number')
# 用户session
parser.add_argument('-s', '--user_session')
# 交易股票代码-港股
parser.add_argument('-c', '--stock_code')
# 交易程序持续时间
parser.add_argument('-t', '--last_time', default=-1)
# 交易策略
parser.add_argument('-st', '--strategy')
# 交易参数-买入价格
parser.add_argument('-bp', '--buying_price')
# 交易参数-卖出价格
parser.add_argument('-sp', '--selling_price')
# 交易参数-交易金额
parser.add_argument('-m', '--transaction_amount')
# 交易参数-交易间隔
parser.add_argument('-it', '--interval', default=10)

args = parser.parse_args()
stock_code = args.stock_code
last_time = float(args.last_time)
strategy = args.strategy
buying_price = args.buying_price
selling_price = args.selling_price
transaction_amount = args.transaction_amount
interval = int(args.interval)
robot_number = args.robot_number
session_id=args.user_session


headers={"Cookie":"JSESSIONID={}".format(session_id)}
root="http://localhost:8080/qihuo/business?action=business"  #这里可能会根据端口和文件名字的变化而去修改

# 获取数据
def get_data(stock_code):

    base = "http://hq.sinajs.cn/list="
    url = base+stock_code
    r = requests.get(url)
    pattern = '".*?,.*?,(.*?),(.*?),(.*?),(.*?),(.*?),.*?,.*?,.*?,'  # 这个是港股的正则
    temp = re.findall(pattern, r.text)[0]
    # new = temp[4]
    # close = temp[1]
    # high = temp[2]
    # low = temp[3]
    return temp


def low_then_buy(price, amount):
    now = get_data(stock_code)
    new_price = now[4]
    if price > new_price:
        # 这里写交易请求-买入amount，并且发送机器人编号（看你需不需要）
        data={"future_name": stock_code,
        "do_what": "0",
        "number": amount,
        "price": new_price}
        requests.post(data=data,url=root,headers=headers)
        return


def high_then_sell(price, amount):
    now = get_data(stock_code)
    new_price = now[4]
    if price < new_price:
        # 这里写交易请求-卖出amount，并且发送机器人编号（看你需不需要）
        data={"future_name": stock_code,
        "do_what": "1",
        "number": amount,
        "price": new_price}
        requests.post(data=data,url=root,headers=headers)
        return


def interval_sell(amount):
    now = get_data(stock_code)
    new_price = now[4]
    # 这里写交易请求-在当前价格卖出
    data={"future_name": stock_code,
        "do_what": "1",
        "number": amount,
        "price": new_price}
    requests.post(data=data,url=root,headers=headers)



#需要的参数 interval -it  ,stock_code -c , strategy  -st ,amount -m, 
def interval_buy(amount):
    now = get_data(stock_code)
    print(now)
    new_price = now[4]
    # 这里写交易请求-在当前价格买入
    data={"future_name": stock_code,
            "do_what": "0",
            "number": amount,
            "price": new_price}
    requests.post(data=data,url=root,headers=headers)

if __name__ == '__main__':
    # schedule.every(3).seconds.do(job)
    time_strat = time.time()
    if strategy == "low_buy":
        schedule.every(interval).seconds.do(low_then_buy, buying_price, transaction_amount)
    elif strategy == "high_sell":
        schedule.every(interval).seconds.do(high_then_sell, selling_price, transaction_amount)
    elif strategy == "interval_buy":
        schedule.every(interval).seconds.do(interval_buy, transaction_amount)
    elif strategy == "interval_sell":
        schedule.every(interval).seconds.do(interval_sell, transaction_amount)

    while True:
        time_end = time.time()
        if(time_end - time_strat > last_time and last_time > 0):
            break
        schedule.run_pending()

    
