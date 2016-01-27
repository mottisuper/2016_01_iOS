//
//  main.swift
//  Swift Basics
//
//  Created by Elad Lavi on 24/01/2016.
//  Copyright © 2016 Elad Lavi. All rights reserved.
//

import Foundation
//printing to the console
print("Hello" + " world"); //prints a string to the console and then goes down by one line.

let i:Int = -25;
let f:Float = 3.14;
let d:Double = 99.99;
let b:Bool = true;
print("i = \(i), f = \(f), d = \(d), b = \(b)");

//comments

/*
comments block
    /*
        internal comments block
    */

*/
//Numbers:
let i1:Int = 5;
let i2 = 7;
//let i3:Int64 = 10*1234567890123456789; //overflow
let i4:UInt64 = 10*1234567890123456789;

let c1:Character = "A";
print("\(c1 == "A")");

//Tuples
let rectangle1 = (0,0,200,100);
print(rectangle1.2);
var rectangle2 = (x: 0, y: 0, width: 200, height: 100);
print(rectangle2.width);
let (_, _, width, height) = rectangle1;
print(width);




//Optionals
var s:String? = nil;
s = "hello";
//s = nil;
//unwrapping
if s == nil{
    print("nil");
}else{
    //....
    let theValueOfS = s!;
    print(theValueOfS);
}


//conditional unwrap
if let theValueOfS = s{
    print(theValueOfS);
}else{
    print("nil");
}


//type alias    //nickname for a type
typealias Identity = Int;
var id:Identity = 123456787;
typealias Note = String;
var myNote: Note = "my note";

var x=9;


var y = x++;
print("\(y)");

//var z = x==9 ? 20 : 3;
var z:Int;

if x==9{
    z = 20;
}else{
    z = 3;
}

//range operators
// 1...10  (from 1 to 10 inclusive)
// 1..<10 (from 1 to 10 exclusive)

for i in 1...10{
    print("the value of i is \(i)");
}

for _ in 1...10{
    print("hi");
}


func myFunc(x: Int, y: Int)->Int{
    var result = x - y;
    if(result < 0){
        result *= -1;
    }
    return result;
}











