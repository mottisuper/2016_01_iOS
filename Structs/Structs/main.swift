//
//  main.swift
//  Structs
//
//  Created by Elad Lavi on 07/02/2016.
//  Copyright © 2016 Elad Lavi. All rights reserved.
//

import Foundation

struct Rectangle {
    var x:Int = 0;
    var y:Int = 0;
    var width:Int = 0;
    var height:Int = 0;
    func printMe(x: Int){
        print("x: \(x), y: \(self.y), width: \(self.width), height: \(self.height)");
    }
}

var rect = Rectangle();
rect.y = 18;
rect.width = 90;
rect.x = 30;
rect.height = 15;
rect.printMe(19);

//struct is a value type. 
var rect2 = rect; //creates a copy
rect2.x++;

print("x: \(rect.x), y: \(rect.y), width: \(rect.width), height: \(rect.height)");
//objects created from struct, lives on the stack, as apose to objects created from class that lives on the heap.
//struct is very fast for construction







