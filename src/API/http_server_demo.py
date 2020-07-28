import json
from flask import Flask, Response
from flask_cors import CORS

from flask_restful import request
import sys
import os
#3 lines of code tot get the import form other files working
PACKAGE_PARENT = '..'
SCRIPT_DIR = os.path.dirname(os.path.realpath(os.path.join(os.getcwd(), os.path.expanduser(__file__))))
sys.path.append(os.path.normpath(os.path.join(SCRIPT_DIR, PACKAGE_PARENT)))
# import service_manager

'''
 Encoding class to encode an object to JSON
'''
class MyEncoder(json.JSONEncoder):
    def default(self, o):
        return o.__dict__


# initialization for the API
app = Flask(__name__)
CORS(app)


def read_json2(json_path):
 
    with open(json_path) as json_file:
        data = json.load(json_file)

    return data

def read_json(json_path):
    data = {}
    data['publications']= []
    data['projects']= []
    num_lines = sum(1 for line in open(json_path))
    with open(json_path, 'r') as file:

        for i in range(0,num_lines):
            line = file.readline()
            print(type(line))

            

            
            line_obj = json.loads(line)
            if "uuid" in line:
                print("pub ok")
                data['publications'].append(line_obj)
                print(data['publications'])

            if "englishKeywords" in line:
                print("proj ok")
                data['projects'].append(line_obj)

    return data
'''
 test route to check whether the server is alive
'''
@app.route("/")
def hello():
    return {'hello': 'world'}, 200

@app.route("/research")
def getResearch():

    json = read_json2('./src/publications.json')
    return json, 200



@app.route('/research/<research_ID>')
def getResearchbyID(research_ID):

    research_id = research_ID

    json_path = './src/publications.json'

    json_data = read_json2(json_path)

    research = object

    for record in json_data["publications"]:
        if record["uuid"] == research_id:
            return record

    for record in json_data["projects"]:
        if record["id"] == research_id:
            research = record

    return research


'''
 POST route to enrich publication data.
 It will build a Publication object from Form-Data sent via the POST request, enrich it and return enriched data as JSON
'''
@app.route("/api/researchs", methods=["POST"])
def enrich_pub_data():

    print("ok")
    




@app.errorhandler(500)
def internal_error(error):
    return f"500 error: {repr(error)}", 500


@app.errorhandler(404)
def not_found(error):
    return f"404 error: {repr(error)}", 404


def run(debug=False, threaded=True):
    app.run(debug=debug, threaded=threaded)


if __name__ == '__main__':
    '''Launch the http server'''
    run(True, False)
