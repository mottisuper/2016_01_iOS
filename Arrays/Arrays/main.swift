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
/*
myFunc(&myArray);

for i in 0 ..< myArray.count{
    print(myArray[i]);
}
*/


//homework
//write a function that takes as a parameter an array of integers, and sorts the array in an acsending order.
//DO NOT create an array!!! not even a temp one

var myArray4 = [Int]();
myArray4.append(1000);


//let's try to understand better what is "inout"
//If you don't write anything before the parameter name, then it is a "let".
//If you write "var", then you can mutate (change) the parameter's value, but, anyway it is a local copy of the original one, meaning, any change will NOT be reflected to the original variable.
//If you write "inout", then, the function receives a pointer to the original object. meaning, it receives a reference to the object. Meaning, any change that is done from within the function, is reflected to the original object, hence, after the function ends, when you look at the variable you have sent to function, you can see the change. when you use "inout", when you invoke the function, you must send the parameter with &.
//  Another important thing about inout: not only that you mutate the original object's value, but also, you can mutate the actual pointer.

var x:Int = 8;
func change(var param: Int){
    param = 13;
    print(x);
    
}

//change(x);
//print("the value of x is \(x)");



//exercise 1: return the largest int in a given array
//exercise 2: return(double) the average of integers in a given array
//exercise 3: reverse the order of the numbers in a given array
//exercise 4: a function takes two arrays of integers, and returns true if the second parameter is contained in the first one.
//param1 :   [3,57,213,84,12,56,96]
//param2 :   [213,84,2]

func largestNumber(numbers: [Int])->Int?{
    if(numbers.count == 0){
        return nil;
    }
    var result = numbers[0];
    for(var i=1; i<numbers.count; i++){
        if(numbers[i] > result){
            result = numbers[i];
        }
    }
    return result;
}

func average(numbers: [Int])throws->Double{
    if(numbers.isEmpty){
        throw NSError(domain: "empty set", code: 1, userInfo: nil);
    }
    var sum = 0;
    for(var i=0;i<numbers.count;i++){
        sum += numbers[i];
    }
    return Double(sum) / Double(numbers.count);
}

func reverseDigits(inout numbers: [Int]){
    for(var i=0; i < numbers.count/2; i++){
        let temp = numbers[i];
        numbers[i] = numbers[numbers.count-1-i];
        numbers[numbers.count-1-i] = temp;
    }
}
myArray = [];
//reverseDigits(&myArray);
//print(myArray);
do{
    let avg = try average(myArray);
    print(avg);
}catch{
    print("there was an error");
}
//i=1
let arr1 = [3,7,19,2,7,19,2,56];
let arr2 = [7,19,2];
func doesContain(arr1: [Int], arr2: [Int])->Bool{
    for(var i=0;i<arr1.count - arr2.count + 1;i++){
        if(arr1[i] == arr2[0]){
            var f = false;
            for(var j=i+1;j<i+arr2.count;j++){
                if(arr1[j] != arr2[j-i]){
                    f = true;
                    break;
                }
            }
            if(f==false){
                return true;
            }
        }
    }
    return false;
}
print("does contain: \(doesContain(arr1, arr2: arr2))");
for x in arr1{
    
}

/*
//##Elad Lavi##

smb://10.0.0.241/n/
hackeru
777

MAC
hackeru
Aa123456
*/
func sortArray(inout arr: [Int]){
    // a   b   c
    var isSorted = false;
    var counter = arr.count - 1;
    while(!isSorted){
        isSorted = true;
        for(var i=0;i<counter;i++){
            if(arr[i] > arr[i+1]){
                isSorted = false;
                let temp = arr[i];
                arr[i] = arr[i+1];
                arr[i+1] = temp;
            }
        }
        counter--;
    }
}











