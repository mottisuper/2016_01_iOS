//
//  main.swift
//  Learning Dictionaries
//
//  Created by Elad Lavi on 07/02/2016.
//  Copyright © 2016 Elad Lavi. All rights reserved.
//

import Foundation


//DICTIONARIES

//instead of identifying a member by its index/position, the key is what ever you want it to be.
var d1:[String:Int] = [String:Int]();
d1["elad"] = 80;
d1["alexander"] = 100;
var d2 = ["elad" : 80, "alexander" : 100];
print(d1["elad"]);
d1["elad"] = nil;
print(d1.keys.contains("elad"));
d1["elad"] = 70;
for (key, value) in d1{
    print("the key is \(key) and the value is \(value)");
}
for key in d1.keys{

}






