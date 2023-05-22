#include <bits/stdc++.h>
#include "../ecomm/Portal.h"
using namespace std;
#ifndef DEMOPORTAL_H
#define DEMOPORTAL_H

void splitting(string s, vector<string> &arr);
class DemoPortal : public Portal
{
public:
    DemoPortal();

public:
    string portalID = "IMT2021542";
    int reqID = 0; // portalID same for a portal and req ID increments
    vector<string> arr;
    ofstream out;
    vector<vector<string>> reqhistory;
    map<string,int> mp;
    string Reqid = portalID + "_" + to_string(reqID);
    int num = 0;
    string str;
    string line;
    vector<string> outformat; // stores lines from platformtoportal after splitting
    string prevreqID = "";
    string type = "", sortOrder = "";
    vector<vector<string>> inputData;
    void processUserCommand(string s);
    void checkResponse();
};
#endif