#include <bits/stdc++.h>
#include "demo/DemoPortal.h"
#include "ecomm/Portal.h"
#include "demo/DemoPortal.h"
using namespace std;
int main()
{
    // instantiating demo portal object
    Portal *p = new DemoPortal();
    while (1)
    {
        string s;
        getline(cin, s);
        if (s[0] == 'C') // if Check call checkresponse
            p->checkResponse();
        else
            p->processUserCommand(s); // otherwise process that particular command
    }
}