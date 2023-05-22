#ifndef ANIMAL_
#define ANIMAL_
#include <bits/stdc++.h>
using namespace std;
class Portal
{
public:
    virtual void processUserCommand(string command) = 0;
    virtual void checkResponse() = 0;
};
#endif

// g++ PortalMain.cpp demo/*.cpp ecomm/*.cpp