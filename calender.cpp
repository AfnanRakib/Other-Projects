#include<bits/stdc++.h>
using namespace std;
/*
20/02/2024 Tue

|---------------------------|
|Sun|Mon|Tue|Wed|Thu|Fri|Sat|
|---------------------------|
| - | - | - | - |  1|  2|  3|
|---------------------------|
|  4|  5|  6|  7|  8|  9| 10|
|---------------------------|
| 11| 12| 13| 14| 15| 16| 17|
|---------------------------|
| 18| 19| 20| 21| 22| 23| 24|
|---------------------------|
| 25| 26| 27| 28| 29| - | - |
|---------------------------|
*/
bool isLeapYear(int year) {
    if (year % 4 == 0) {
        if (year % 100 == 0) {
            if (year % 400 == 0) {
                return true;
            } else {
                return false;
            }
        } else {
            return true;
        }
    } else {
        return false;
    }
}

int main(){
    map<string,int>daymp,monthmp;
    daymp["Sun"]=1;
    daymp["Mon"]=2;
    daymp["Tue"]=3;
    daymp["Wed"]=4;
    daymp["Thu"]=5;
    daymp["Fri"]=6;
    daymp["Sat"]=7;

    monthmp["01"]=31;
    monthmp["03"]=31;
    monthmp["05"]=31;
    monthmp["07"]=31;
    monthmp["08"]=31;
    monthmp["10"]=31;
    monthmp["12"]=31;

    monthmp["04"]=30;
    monthmp["06"]=30;
    monthmp["09"]=30;
    monthmp["11"]=30;

    string s;
    getline(cin,s);

    string da = s.substr(0, 2);
    string m = s.substr(3, 2);
    string y=  s.substr(6,4);
    string dd= s.substr(11,3);
    int days=0,ddi;
    ddi=daymp[dd];
    int d=stoi(da);
    vector<string>cal(36);

    while(d>0){
        d-=7;
    }
    int strt=(ddi+(1-d))%7;
    strt=strt==0?7:strt;


    if(m=="02"){
        if(isLeapYear(stoi(y))){
            days=29;
        }else{
            days=28;
        }
    }else{
        days=monthmp[m];
    }

    int extra=days-(29+(7-strt));

    int started=0;
    for(int j=0;j<35;){
            if(!started){
                if(extra==1){
                    string temp="30";
                    cal[j++]=temp;
                }else if(extra==2){
                    cal[j++]="30";
                    //cout<<j-1<<"->"<<cal[j-1]<<endl;
                    cal[j++]="31";
                    //cout<<" "<<j-1<<"->"<<cal[j-1]<<endl;
                }
                while(j<strt-1 && j<7){
                    cal[j++]="-";
                    //cout<<" "<<j-1<<"->"<<cal[j-1]<<endl;
                }
                started=1;
                cal[j++]=to_string(started);
                //cout<<" "<<j-1<<"->"<<cal[j-1]<<endl;
                started++;
            }else{
                if(started<=days){
                    if(started>9){
                        cal[j++]=to_string(started);
                    }else{
                        string t=" ";
                        cal[j++]=to_string(started);
                    }
                    started++;
                }else{
                    string t="-";
                    cal[j++]=t;
                   // cout<<" "<<j-1<<"->"<<cal[j-1]<<endl;
            }
        }
    }
    cout<<"|---------------------------|"<<endl;
    cout<<"|Sun|Mon|Tue|Wed|Thu|Fri|Sat|"<<endl;
    cout<<"|---------------------------|"<<endl;
    for(int i=0;i<35;){
        cout<<"|";
        for(int j=0;j<7;j++){
            if(cal[i]=="-")cout<<" "<<cal[i++]<<" ";
            else if(cal[i].size()<2){
                cout<<"  "<<cal[i++];
            }else{
                cout<<" "<<cal[i++];
            }
            cout<<"|";
        }
        cout<<endl;
        cout<<"|---------------------------|"<<endl;
    }
}
