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
//from now and on, no limitations about mathemtical operations.
//5. write a function that will take two parameters, x and y, and will return x raised to the power of y. i.e power(3,3)->27, power(3,0)->1, power(0,0)->Error.
//6. write a function that takes an integer as a parameter, and returns its squared root. i.e sqrt(9)->3, sqrt(16)->4, sqrt(10)->4
//7. write a function that takes an integer as a parameter, and will return its sum of digits. i.e sumOfDigits(123)->6
//8. write a function that takes an integer as a parameter, and will return its largest digit. i.e largestDigit(123)->3
//9. write a function that takes an integer as a parameter, and will return it in reversed order of digits. reverseDigits(123)->321
//10. (hard) write a function that takes an integer as a parameter, and will return the sum of its prime factors 
//11.



let x = 7 % 3; //x == 1


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

//print("distance(5, y: 8)->\(distance(5, y: 8))");

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

func quotient2(x: Int, y: Int)->(Int,Int){ //tuple
    if(y == 0){
        return (-1,-1);
    }
    var result = 0;
    var sum = y;
    var largestProductOfY = 0;
    while(sum <= x){
        result++;
        sum += y;
        largestProductOfY += y;
    }
    return (result, distance(largestProductOfY, y: x));
}

//it works! but we have a performence issue.
func remainder(x: Int, y: Int)->Int{
    if(y == 0){
        return -1;
    }
    return distance(product(quotient(x, y: y), y: y), y: x);
}

//let (q,r) = quotient2(7, y: 3);
//print("3 fits in 7 \(q) times with a remainder of \(r)");
//print(quotient2(7, y: 3));


func power(x: Int, y: Int)->Int{
    if(x == 0){
        if(y == 0){
            return -1;
        }
        return 0;
    }
    if(y == 0 || x == 1){
        return 1;
    }
    var result = x;
    for(var i=1 ; i<y ; i++){
        result *= x;
    }
    return result;
}
func powerNoLoops(x: Int, y: Int)->Int{ //recursive call
    if(x == 0){
        if(y == 0){
            return -1;
        }
        return 0;
    }
    if(y == 0 || x == 1){
        return 1;
    }
    if(y == 1){ //stopping condition
        return x;
    }
    return powerNoLoops(x, y: y-1) * x;
}
//powerNoLoops(7, y: 4);
//powerNoLoops(7, y: 3) * 7;
//(powerNoLoops(7, y: 2) * 7) * 7;
//((powerNoLoops(7, y: 1) * 7) * 7) * 7;
//((7 * 7) * 7) * 7;
func sqrt(x: Int)->Int{
    if(x<0){
        return -1;
    }
    var result = 0;
    while(result * result < x){
        result++;
    }
    return result;
}

func sumOfDigits(var x: Int)->Int{
    var result = 0;
    while(x != 0){
        let lastDigit = x % 10;
        x /= 10
        
        result += lastDigit;
    }
    return result;
}

func largestDigit(var x: Int)->Int{
    var result = 0;
    while(x != 0){
        let lastDigit = x % 10;
        x /= 10
        
        if(lastDigit > result){
            result = lastDigit;
        }
    }
    return result;
}

func reverseDigits(var x: Int)->Int{
    var result = 0;
    while(x != 0){
        let lastDigit = x % 10;
        x /= 10
        result = result * 10 + lastDigit;
    }
    return result;
}








