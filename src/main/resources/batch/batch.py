import sys
import io
import json
import pymysql as pg
import numpy as np


from datetime import datetime


def insert_data(yyyymmddHH):
    with io.open("datasource.json", "r") as reader:
        datasource = json.load(reader)
    with pg.connect(
            host=datasource["host"],
            port=datasource["port"],
            db=datasource["database"],
            user=datasource["username"],
            passwd=datasource["password"]) as conn:
        cursor = conn.cursor()

        with io.open("searchKeys.sql", "r", encoding="utf8") as query_reader:
            query = "".join(query_reader.readlines())

        cursor.execute(query.format(datetime=yyyymmddHH))
        rows = cursor.fetchall()

        arr = np.asarray(rows)

        with io.open("insertData.sql", "r", encoding="utf8") as query_reader:
            query = "".join(query_reader.readlines())

        for i in arr:
            seqdata = str(i)[1:-1] 
            print(seqdata)
            cursor.execute(query.format(seq=seqdata, datetime=yyyymmddHH))
            conn.commit()
        

if __name__ == "__main__":
    insert_data(sys.argv[1])
    