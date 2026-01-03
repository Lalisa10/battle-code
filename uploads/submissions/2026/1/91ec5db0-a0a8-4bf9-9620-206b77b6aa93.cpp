#include<bits/stdc++.h>

using namespace std;
typedef long long ll;
typedef pair<int, int> II;

const int N = 1e5 + 5;

void Lalisa() {
    string s, t;
    vector<int> rs(26, 0);
    cin >> s >> t;
    for (char c : t) rs[c - 'a'] ++;
    for (char c : s) {
        rs[c - 'a'] --;
        if (rs[c - 'a'] < 0) {
            cout << "Impossible\n";
            return;
        }
    }
    priority_queue<char, vector<char>, greater<char>> pq;
    for (int i = 0; i < 26; i ++) {
        while (rs[i] --) {
            pq.push(char('a' + i)); 
            //cerr << char('a' + i);
        }
        //cerr << "\n";
    }
    //cerr << "\n";
    string ans;
    int j = 0;
    pq.push(s[j]);
    while (!pq.empty()) {
        char best = pq.top();
        pq.pop();
        ans += best;
        if (j < s.size() && best == s[j]) {
            j ++;
            if (j < s.size())
            pq.push(s[j]);
        }
    }
    cout << ans << "\n";
}

int main(){
     #define name "main"
     if (fopen(name".inp", "r")) {
         freopen(name".inp", "r", stdin);
         freopen(name".out", "w", stdout);
     }
    ios_base::sync_with_stdio(0); cin.tie(0); cout.tie(0);
    int nTest = 1;
    cin >> nTest;
    while (nTest --) {
        Lalisa();
    }
    return 0;
}
