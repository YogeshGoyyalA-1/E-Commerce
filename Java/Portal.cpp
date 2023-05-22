#include<bits/stdc++.h>
using namespace std;
class Comparator{ //comparator which sorts on basis of Price and Name
    public:
    Comparator(int index){ //stores index as we sort a vector so we should know on what basis(i.e ref to which ind)
        this->index=index;
    }
    bool operator()(vector<string> v1,vector<string> v2) //cmp which sorts on basis of particular index of vector
    {
        if(index==4) //that is with respect to price sorting
        {   return stoi(v1[index])<stoi(v2[index]); //convert to int and then compare 
        }
        else //any other parameter
        {
            return v1[index]<v2[index];
        }
    }
    private:
    int index; //data member
};
void splitting(string s,vector<string> &arr)//splitting logic
{       //vector passed by ref so string is split and seperate words are stored in vector
        s = s+ ' ';
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
int main(){
    string s; //input 
    ofstream out; //out writes to file
    out.open("PortalToPlatform.txt"); 
    int portalID=1000,reqID=0; //portalID same for a portal and req ID increments 
    vector<vector<string>> reqhistory; //maintains history of requests(it helps us to check when request ID changes for a response)
    while(1){
        getline(cin,s);
        vector<string> arr;
        s = s+ ' ';
        splitting(s,arr);//function call splitting
        ifstream in("PlatformToPortal.txt"); //in to read from a file
        if(arr[0]=="Start"){
            out<<portalID<<" "<<reqID<<" "; //writes these common things to out
            out<<arr[0]<<endl;
            reqhistory.push_back({to_string(portalID),to_string(reqID),arr[0]}); //to maintain which sorting before check commands comes in
            reqID++;
        }
        else if(arr[0]=="List"){
            out<<portalID<<" "<<reqID<<" "; //writes these common things to out
            out<<arr[0]<<" "<<arr[1]<<endl;
            // vector<string> temp={reqID,arr[1],}
            reqhistory.push_back({to_string(portalID),to_string(reqID),arr[0],arr[1],arr[2]}); //to maintain which sorting before check commands comes in
            reqID++;
        }

        else if(arr[0]=="Buy"){
            out<<portalID<<" "<<reqID<<" "; //writes these common things to out
            out<<arr[0]<<" "<<arr[1]<<" "<<arr[2]<<endl;
            reqhistory.push_back({to_string(portalID),to_string(reqID),arr[0],arr[1],arr[2]}); //to maintain which sorting before check commands comes in
            reqID++;
        }
        else if(arr[0]=="Check"){
            //Logic to read from PlatformToPortal
            ifstream in("PlatformToPortal.txt"); 
            string line;
            vector<string> outformat; //stores lines from platformtoportal after splitting
            string prevreqID="";
            string type="",sortOrder="";
            vector<vector<string>>inputData;
            while(getline(in,line))
            {
                splitting(line,outformat);
                // cout<<outformat[1]<<endl;
                // inputData.push_back(outformat);
                // inputreqID=outformat[1];
                // if(prevreqID=="") prevreqID=outformat[1];
                if(prevreqID!=outformat[1] && prevreqID!=""){
                //    prevreqID=outformat[1];
                   for(auto e:reqhistory)
                   {
                        if(e[1]==prevreqID)
                        {
                            type=e[2];
                            // cout<<type<<endl;
                            if(type=="List") 
                            {
                                sortOrder=e[4];
                                // cout<<sortOrder<<endl;
                            }
                            break;
                        }
                   }
                   if(type=="Start"){
                        for(int e=2;e<outformat.size();e++){
                            cout<<inputData[0][e]<<endl;
                        }
                          reqhistory.erase(reqhistory.begin());
                   }
                   else if(type=="List"){
                        if(sortOrder=="Price"){
                            Comparator cmp(4);
                            sort(inputData.begin(),inputData.end(),cmp);
                        }
                        else{
                            Comparator cmp(3);
                            sort(inputData.begin(),inputData.end(),cmp);
                        }
                        for(auto e:inputData){
                            cout<<e[2]<<" "<<e[3]<<" "<<e[4]<<" "<<e[5]<<endl;
                        }
                        cout<<endl;
                         reqhistory.erase(reqhistory.begin());
                         type="";
                   }
                   else if(type=="Buy")
                   {
                        cout<<inputData[0][0]<<" "<<inputData[0][1]<<" "<<inputData[0][2]<<endl;
                        reqhistory.erase(reqhistory.begin());
                   }
                   prevreqID=outformat[1];
                   inputData.clear();
                   inputData.push_back(outformat);
                   type="";

                }
                else{
                    if(prevreqID=="")
                    {
                    prevreqID=outformat[1];
                    }
                    inputData.push_back(outformat);
                }
                outformat.clear();

            }
            // reqhistory.clear();

        }
      
    }

        
    

}