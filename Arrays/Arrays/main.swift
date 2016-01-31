//
//  main.swift
//  Arrays
//
//  Created by Elad Lavi on 31/01/2016.
//  Copyright © 2016 Elad Lavi. All rights reserved.
//

import Foundation

var myArray:[Int];
myArray = [8,2,1,14,50];
myArray.append(100);
//myArray[50] = 3; // will cause a runtime exception!!!!!

//append always adds at the end to the array
//insertAt, allows us to push a new int between existing members of the array.
var myArray2 = myArray;
myArray2[0] = 9;

//Arrays in Swift a value type! be careful!
var myArray3 = [2,3,4,5,6,"aaa"];
myArray3.append("blabla");
//in Java, all classes inherit from the class Object
//In Swift, types do not necessarily inherit from any class (meaning many classes do not extend any other class).
//A POINTER can be of type AnyObject, which means that the pointer can point to any type of object. WARNING! it is not a type! an object cannot be of type "AnyObject";
var myReference : AnyObject = "5";
myReference = 5;
myArray3.append(true);

func myFunc(inout arr: [Int]){
    arr[0] = 99;
}

myFunc(&myArray);

for i in 0 ..< myArray.count{
    print(myArray[i]);
}



//homework
//write a function that takes as a parameter an array of integers, and sorts the array in an acsending order.











