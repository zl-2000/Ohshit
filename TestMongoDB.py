from pymongo import MongoClient

class Test:
    def __init__(self):
        self.client = MongoClient("localhost",27017)
        print(self.client)

    def doit(self):
        data = self.client["data"]
        test = data.get_collection(("test"))
        test.insert_one()

if __name__ == '__main__':
    test = Test()
    test.getColl()