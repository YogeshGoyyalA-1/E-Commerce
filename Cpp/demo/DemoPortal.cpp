#include <bits/stdc++.h>
#include "../ecomm/Portal.h"
using namespace std;
#include "DemoPortal.h"
#include "comparator.h"

void splitting(string s, vector<string> &arr) // splitting logic
{                                             // vector passed by ref so string is split and seperate words are stored in vector
    s = s + ' ';
    string w = "";
    for (int i = 0; i < s.size(); i++)
    {
        if (s[i] == ' ')
        {
            arr.push_back(w);
            w = "";
        }
        else
        {
            w += s[i];
        }
    }
}

DemoPortal::DemoPortal()
{
    out.open("PortalToPlatform.txt");
}

void DemoPortal::processUserCommand(string s)
{
    string Reqid = portalID + "_" + to_string(reqID);
    vector<string> arr;
    splitting(s, arr); // function call splitting
    if (arr[0] == "Start")
    {
        out << portalID << " " << Reqid << " "; // writes these common things to out
        out << arr[0] << endl;
        reqhistory.push_back({portalID, Reqid, arr[0]}); // to maintain which sorting before check commands comes in
        reqID++;
    }
    else if (arr[0] == "List")
    {
        out << portalID << " " << Reqid << " "; // writes these common things to out
        out << arr[0] << " " << arr[1] << endl;
        // vector<string> temp={reqID,arr[1],}
        reqhistory.push_back({portalID, Reqid, arr[0], arr[1], arr[2]}); // to maintain which sorting before check commands comes in
        reqID++;
    }

    else if (arr[0] == "Buy")
    {
        out << portalID << " " << Reqid << " "; // writes these common things to out
        out << arr[0] << " " << arr[1] << " " << arr[2] << endl;
        reqhistory.push_back({portalID, Reqid, arr[0], arr[1], arr[2]}); // to maintain which sorting before check commands comes in
        reqID++;
    }
}

void DemoPortal::checkResponse()
{
    ifstream file("PlatformToPortal.txt");
    int num = 0;
    string str;
    // num counts the total number of lines in PlatformToPortal.txt
    while (getline(file, str))
    {
        num++;
    }
    file.close();

    // Logic to read from PlatformToPortal
    ifstream in("PlatformToPortal.txt");

    //line temporarily stores one line of input
    string line;
    vector<string> outformat; // stores lines from platformtoportal after splitting

    // request id of the previous command
    string prevreqID = "";

    // type of request (i.e. list, buy etc)
    string type = "", sortOrder = "";

    // a request can have multiple lines of output
    // each inputData[i] contatins all the lines of response for each request
    vector<vector<string>> inputData;

    while (getline(in, line))
    {
        num--;
        // splitting the input line by spaces
        splitting(line, outformat);

        // if the current request id is different from the previous request id, we go into this block
        // when the request id changes, we know that we have to give response for a new request
        // so we start processing the current request

        if ((prevreqID != outformat[1] && prevreqID != ""))
        {

            for (auto e : reqhistory)
            {
                if (e[1] == prevreqID)
                {
                    type = e[2];
                    if (type == "List")
                    {
                        sortOrder = e[4];
                    }
                    break;
                }
            }
            if (type == "Start")
            {
                // cout<<outformat.size()<<endl;
                for (int e = 0; e < outformat.size(); e++)
                {
                    cout << inputData[0][e] << " ";
                }
                cout << endl;
                reqhistory.erase(reqhistory.begin());
            }
            else if (type == "List")
            {
                if (sortOrder == "Price")
                {
                    Comparator cmp(4);
                    sort(inputData.begin(), inputData.end(), cmp);
                }
                else
                {
                    Comparator cmp(3);
                    sort(inputData.begin(), inputData.end(), cmp);
                }
                for (auto e : inputData)
                {
                    cout << e[0] << " " << e[1] << " " << e[2] << " " << e[3] << " " << e[4] << " " << e[5] << endl;
                }
                cout << endl;
                reqhistory.erase(reqhistory.begin());
                type = "";
            }
            else if (type == "Buy")
            {
                cout << inputData[0][0] << " " << inputData[0][1] << " " << inputData[0][2] << endl;
                reqhistory.erase(reqhistory.begin());
            }
            prevreqID = outformat[1];
            inputData.clear();
            inputData.push_back(outformat);
            type = "";
        }
        else
        {
            if (prevreqID == "")
            {
                prevreqID = outformat[1];
            }
            inputData.push_back(outformat);
        }

        // num == 0 indicates that the last line of the input file has been reached
        if (num == 0)
        {
            prevreqID = outformat[1];
            for (auto e : reqhistory)
            {
                if (e[1] == prevreqID)
                {
                    type = e[2];
                    if (type == "List")
                    {
                        sortOrder = e[4];
                    }
                    break;
                }
            }
            if (type == "Start")
            {
                for (int e = 0; e < outformat.size(); e++)
                {
                    cout << inputData[0][e] << " ";
                }
                cout << endl;
                reqhistory.erase(reqhistory.begin());
            }
            else if (type == "List")
            {
                if (sortOrder == "Price")
                {
                    Comparator cmp(4);
                    sort(inputData.begin(), inputData.end(), cmp);
                }
                else
                {
                    Comparator cmp(3);
                    sort(inputData.begin(), inputData.end(), cmp);
                }
                for (auto e : inputData)
                {
                    cout << e[0] << " " << e[1] << " " << e[2] << " " << e[3] << " " << e[4] << " " << e[5] << endl;
                }
                cout << endl;
                reqhistory.erase(reqhistory.begin());
                type = "";
            }
            else if (type == "Buy")
            {
                cout << inputData[0][0] << " " << inputData[0][1] << " " << inputData[0][2] << endl;
                reqhistory.erase(reqhistory.begin());
            }
            prevreqID = outformat[1];
            inputData.clear();
            inputData.push_back(outformat);
            type = "";
        }
        outformat.clear(); //we clear it for next line to be stored
    }
}
