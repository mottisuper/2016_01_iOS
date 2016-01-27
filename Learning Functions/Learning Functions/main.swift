//
//  main.swift
//  Learning Functions
//
//  Created by Elad Lavi on 27/01/2016.
//  Copyright © 2016 Elad Lavi. All rights reserved.
//
//in the following four exercises, the functions takes two integer parameters. You may assume the arguments are non-negative. In all four exercises, you may NOT use any mathematical operator, other than + (plus). Meaning, you may not use - * / %

//1. write a function that will take two parameters, x and y, and will return the distance between them. i.e distance(5,8)->3
//2. write a function that will take two parameters, x and y, and will return their product. product(5,8)->40
//3. write a function that will take two parameters, x and y, and will return how many whole times, y fits in x. i.e quotient(7,3)->2, quotient(3,7)->0
//4. write a function that will take two parameters, x and y, and will return the remainder of x divided by y (this is actually x%y). i.e  remainder(7,3)->1, remainder(3,7)->3


func distance(x: Int, y: Int)->Int{
    var small = x;
    var large = y;
    if(x > y){
        small = y;
        large = x;
    }
    var result = 0;
    while(small + result < large){
        result++;
    }
    return result;
}

print("distance(5, y: 8)->\(distance(5, y: 8))");

func product(x: Int, y: Int)->Int{
    var small = x;
    var large = y;
    if(x > y){
        small = y;
        large = x;
    }
    var result = 0;
    for(var i=0; i<small; i++){
        result += large;
    }
    return result;
}


func quotient(x: Int, y: Int)->Int{
    if(y == 0){
        return -1;
    }
    var result = 0;
    var sum = y;
    while(sum <= x){
        result++;
        sum += y;
    }
    return result;
}

func remainder(x: Int, y: Int)->Int{
    if(y == 0){
        return -1;
    }
    return distance(product(quotient(x, y: y), y: y), y: x);
}

















